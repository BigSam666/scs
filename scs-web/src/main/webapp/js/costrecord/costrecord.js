$.validator.addMethod("verifyPositiveNumber",function(value,element,params){  
	if (parseFloat(value) < 0) return false;
	return true;
}, "不能是负数");
$(function () {
    $("#jqGrid").jqGrid({
        url: '../costrecord/list',
        datatype: "json",
        colModel: [			
			// 隐藏主键
			{ label: '主键id', name: 'id', width: 50, key: true ,hidden : true },
			{ label: '申报日期', name: 'ddate', width: 80 , formatter: function(value, options, row){
				return new Date(value).Format("yyyy-MM-dd");
			}}, 
				{ label: '费用类型', name: 'dtypeName', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '金额', name: 'money', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '申报人员', name: 'personName', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '费用说明', name: 'rmk', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '创建时间', name: 'crttm', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '操作', name: 'operation', width: 40, formatter: function(value, options, row){
				var oper = "<a onClick=\"javascript:vm.update("+options.rowId+", false);\" title='查看' ><i class=\"fa fa-info-circle\" style='color:violet;'></i></a>&nbsp;&nbsp;";
				oper += update ? "<a onClick=\"javascript:vm.update("+options.rowId+", true);\" title='修改'  ><i class=\"fa fa-pencil-square-o\" style='color:green;'></i></a>&nbsp;&nbsp;":"";
				oper += del ? "<a onClick=\"javascript:vm.del("+options.rowId+");\" title='删除'  ><i class=\"fa fa-trash-o\" style='color:red;'></i></a>" : "";
				return oper;
			}},
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList : [10,30,50],
        rownumbers: false, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: false,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
    // 条件查询的费用类型
    $.get("../bditem/selectForZtree", function(r) {
    	if (r.code != 0) {
    		alert(r.msg);
    		vm.reload();
    	}
    	ztreeQdtype = $.fn.zTree.init($("#qdtypeTree"), settingDtype, r.data);
    });
	 // 表单验证
    validate = $(".form-horizontal").validate({
  	   onfocusout: false,
 	   onkeyup: false,
 	   rules: {
 		  ddate: {
 			   required: true
 		   },
 		  dtype: {
 			   required: true
 		   },
 		  money: {
 			   required: true,
 			   number: true,
 			   verifyPositiveNumber: ''
 		   },
 		  person: {
 			   required: true
 		   },
 		  rmk: {
 			   required: true
 		   }
 	   },
 	   messages: {
 		  ddate: {
 		       required: "必填，请选择申报日期"
 		   },
 		  dtype: {
			   required: "必填，请选择费用类型"
		   },
		   money: {
			   required: "必填，请输入金额",
			   number: "金额只能是数字",
			   verifyPositiveNumber: "金额不能是负数"
		   },
		   person: {
			   required: "必填，请选择申报人员"
		   },
		   rmk: {
			   required: "必填，请输入费用说明"
		   }
 	   },
 	   showErrors: function(errorMap, errorList) {
			if ( errorList && errorList.length > 0 ) {  //如果存在错误信息
				layer.msg(errorList[0].message, function(){});
			}
		}
    });
    // 表单验证结束
});

var ztreePerson;
var ztreeDtype;
var ztreeQdtype;

var settingPerson = {
	data : {
		simpleData : {
			enable : true,
			idKey : "ztid",
			pIdKey : "ztpid",
			rootPId : -1
		},
		key : {
			url : "nourl"
		}
	},
	check : {
		enable : false,
		nocheckInherit : true,
		chkboxType : {
			"Y" : "",
			"N" : ""
		},
		chkStyle : "radio",
		radioType : "all"
	}
};
var settingDtype = {
		data : {
			simpleData : {
				enable : true,
				idKey : "ztid",
				pIdKey : "ztpid",
				rootPId : -1
			},
			key : {
				url : "nourl",
				name:"cnm"
			}
		},
		check : {
			enable : false,
			nocheckInherit : true,
			chkboxType : {
				"Y" : "",
				"N" : ""
			},
			chkStyle : "radio",
			radioType : "all"
		}
};

var vm = new Vue({
	el:'#rrapp',
	data:{
		q : {dtype:null,dtypeName:null},
		showList: true,
		upd: false,
		title: null,
		costrecord: {status:1,person:'',personName:'',dtype:'',dtypeName:''}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		getDtype: function () {
			$.get("../bditem/selectForZtree", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				ztreeDtype = $.fn.zTree.init($("#dtypeTree"), settingDtype, r.data);
			});
		},
		getPerson: function () {
			$.get("../sys/user/selectForZtree", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				ztreePerson = $.fn.zTree.init($("#personTree"), settingPerson, r.userList);
			});
		},
		add: function(){
			vm.showList = false;
			vm.upd = true;
			vm.title = "新增";
			vm.costrecord = {status:1,person:'',personName:'',dtype:'',dtypeName:''};
			
			vm.getPerson(); // 申报人员
			vm.getDtype(); // 费用类型
		},
		update: function (id, isupdate) {
			if(id == null){
				return ;
			}
			vm.showList = false;
			vm.upd = isupdate;
            vm.title = isupdate ? "修改" : "查看";
            
            vm.getInfo(id);
            
            // 申报人员
            $.get("../sys/user/selectForZtree", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				ztreePerson = $.fn.zTree.init($("#personTree"), settingPerson, r.userList);
				var nodes = ztreePerson.transformToArray(ztreePerson.getNodes());
				nodes.forEach(function(e){
					if(e.id==vm.costrecord.person) {
						vm.costrecord.person = e.id.toString();
						vm.costrecord.personName = e.name.toString();
					}
				});
			});
            
            // 费用类型
            $.get("../bditem/selectForZtree", function(r) {
            	if (r.code != 0) {
            		alert(r.msg);
            		vm.reload();
            	}
            	ztreeDtype = $.fn.zTree.init($("#dtypeTree"), settingDtype, r.data);
            	var nodes = ztreeDtype.transformToArray(ztreeDtype.getNodes());
            	
            	nodes.forEach(function(e){
            		if(e.code==vm.costrecord.dtype) {
            			vm.costrecord.dtype = e.code.toString();
            			// vm.costrecord.dtypeName = e.cnm.toString(); 这么不行。。
            			$("input[name='dtypeName']").val(e.cnm);
            		}
            	});
            });
            
            // 投保日期
            var date = new Date(vm.costrecord.ddate);
            $("#ddate").val(date.Format("yyyy-MM-dd"));
            
		},
		saveOrUpdate: function (event) {
			if(validate.form()) {
				var url = vm.costrecord.id == null ? "../costrecord/save" : "../costrecord/update";
				var _json = $(".form-horizontal").form2json();
				_json.id = vm.costrecord.id;
				
				$.ajax({
					type: "POST",
				    url: url,
				    data: JSON.stringify(_json),
				    success: function(r){
				    	if(r.code === 0){
							alert('操作成功', function(index){
								vm.reload();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			}
		},
		del: function (id) {
			confirm('确定要删除选中的记录？', function(){
		    	$.get("../costrecord/delete/"+id, function(r){
					if(r.code == 0){
						$("#jqGrid").trigger("reloadGrid");
						top.layer.closeAll();
					}else{
						alert(r.msg);
					}
				});
			});
		},
		personTree : function() {
			layer.open({
				type : 1,
				offset : '50px',
				skin : 'layui-layer-molv',
				title : "选择申报人员",
				area : [ '300px', '450px' ],
				shade : 0,
				shadeClose : false,
				content : jQuery("#personLayer"),
				btn : [ '确定', '取消' ],
				btn1 : function(index) {
					// 获取选择的菜单
					var nodes = ztreePerson.getSelectedNodes();
					if(nodes.length>0){
						var node = nodes[0];
						if (node.ztid == 9223372036854776000) {
							vm.costrecord.person = nodes[0].id.toString();
							vm.costrecord.personName = nodes[0].name.toString();
							layer.close(index);
						} else {
							alert('请选择一个人作为申报人员');
						}
					}
				}
			});
		},
		dtypeTree : function() {
			layer.open({
				type : 1,
				offset : '50px',
				skin : 'layui-layer-molv',
				title : "选择费用类型",
				area : [ '300px', '450px' ],
				shade : 0,
				shadeClose : false,
				content : jQuery("#dtypeLayer"),
				btn : [ '确定', '取消' ],
				btn1 : function(index) {
					// 获取选择的菜单
					var nodes = ztreeDtype.getSelectedNodes();
					if(nodes.length>0){
						var node = nodes[0];
						vm.costrecord.dtype = nodes[0].code.toString();
						vm.costrecord.dtypeName = nodes[0].cnm.toString();
						layer.close(index);
					}
				}
			});
		},
		qdtypeTree : function() {
			layer.open({
				type : 1,
				offset : '50px',
				skin : 'layui-layer-molv',
				title : "选择费用类型",
				area : [ '300px', '450px' ],
				shade : 0,
				shadeClose : false,
				content : jQuery("#qdtypeLayer"),
				btn : [ '确定', '取消' ],
				btn1 : function(index) {
					// 获取选择的菜单
					var nodes = ztreeQdtype.getSelectedNodes();
					if(nodes.length>0){
						var node = nodes[0];
						vm.q.dtype = nodes[0].code.toString();
						vm.q.dtypeName = nodes[0].cnm.toString();
						layer.close(index);
					}
				}
			});
		},
		getInfo: function(id){
			$.ajax({
			   type: "GET",
			   url: "../costrecord/info/"+id,
			   async: false,
			   success: function(r) {
				   vm.costrecord = r.costrecord;
			   }
			});
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData : {
					'qdtype' : vm.q.dtype
				},
                page:page
            }).trigger("reloadGrid");
		}
	}
});
