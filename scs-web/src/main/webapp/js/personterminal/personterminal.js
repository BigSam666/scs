$(function () {
    $("#jqGrid").jqGrid({
        url: '../personterminal/list',
        datatype: "json",
        colModel: [			
			// 隐藏主键
			{ label: '主键id', name: 'id', width: 50, key: true ,hidden : true },
			{ label: '设备类型', name: 'devtypeName', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '手机号码', name: 'phone', width: 80 , formatter: function(value, options, row){
					if (value == null) return '';
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '设备编号', name: 'devno', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '使用人', name: 'personName', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '修改人', name: 'updName', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '修改时间', name: 'updtm', width: 80 , formatter: function(value, options, row){
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
    $.get("../basecode/selectByType/devtype", function(r) {
		if (r.code != 0) {
			alert(r.msg);
			vm.reload();
		}
		r.data.push({'cnm':'所有','id':-1});
		// 填充数据到页面
		$('#qdevtype').combobox({
			readonly: false,
		    data: r.data,
		    valueField:'id',
		    textField:'cnm',
		    panelHeight: 'auto',
		    editable: false,
   			onChange:function(newValue, oldValue){
   				vm.q.qdevtype = newValue;
   		    }
		});
	});
	 // 表单验证
    validate = $(".form-horizontal").validate({
  	   onfocusout: false,
 	   onkeyup: false,
 	   rules: {
 		   devtype: {
 			   required: true
 		   },
 		  devno: {
 			   required: true
 		   },
 		  person: {
 			   required: true
 		   }
 	   },
 	   messages: {
 		  devtype: {
 		       required: "必填，请输入设备类型"
 		   },
 		  devno: {
			   required: "必填，请输入设备编号"
		   },
		   person: {
			   required: "必填，请输入使用人"
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

var vm = new Vue({
	el:'#rrapp',
	data:{
		q : {qdevtype:null,keyword:null},
		showList: true,
		upd: false,
		title: null,
		personterminal: {status:1,phone:'',devno:'',person:'',personName:''}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		getDevtype : function(id) {
			$.get("../basecode/selectByType/devtype", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				r.data.push({'cnm':'所有','id':-1});
				// 填充数据到页面
				$('#devtype').combobox({
					readonly: false,
				    data: r.data,
				    valueField:'id',
				    textField:'cnm',
				    panelHeight: 'auto',
				    editable: false,
		   			onChange:function(newValue, oldValue){
		   		    }
				});
			})
		},
		getPerson: function (){
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
			vm.personterminal = {status:1,phone:'',devno:'',person:'',personName:''};
			
			vm.getDevtype(); // 设备类型
			vm.getPerson(); // 使用人
		},
		update: function (id, isupdate) {
			if(id == null){
				return ;
			}
			vm.showList = false;
			vm.upd = isupdate;
            vm.title = isupdate ? "修改" : "查看";
            
            vm.getInfo(id);
            
            
            // 设备类型
			$.get("../basecode/selectByType/devtype", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				var _data = r.data;
				_data.push({'cnm':'所有','id':-1});
				_data.forEach(function (e){
					if (e.id == vm.personterminal.devtype) {
						e.selected = true;
					}
				});
				$('#devtype').combobox({
					readonly: false,
				    data: r.data,
				    valueField:'id',
				    textField:'cnm',
				    panelHeight: 'auto',
				    editable: false,
		   			onChange:function(newValue, oldValue){
		   		    }
				});
			});
			// 使用人
            $.get("../sys/user/selectForZtree", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				ztreePerson = $.fn.zTree.init($("#personTree"), settingPerson, r.userList);
				var nodes = ztreePerson.transformToArray(ztreePerson.getNodes());
				nodes.forEach(function(e){
					if(e.id==vm.personterminal.person) {
						vm.personterminal.person = e.id.toString();
						vm.personterminal.personName = e.name.toString();
					}
				});
			});
            
		},
		saveOrUpdate: function (event) {
			if(validate.form()) {
				var url = vm.personterminal.id == null ? "../personterminal/save" : "../personterminal/update";
				var _json = $(".form-horizontal").form2json();
				_json.id = vm.personterminal.id;
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
		    	$.get("../personterminal/delete/"+id, function(r){
					if(r.code == 0){
						$("#jqGrid").trigger("reloadGrid");
						top.layer.closeAll();
					}else{
						alert(r.msg);
					}
				});
			});
		},
		getInfo: function(id){
			$.ajax({
			   type: "GET",
			   url: "../personterminal/info/"+id,
			   async: false,
			   success: function(r) {
				   vm.personterminal = r.personterminal;
			   }
			});
		},
		personTree : function() {
			layer.open({
				type : 1,
				offset : '50px',
				skin : 'layui-layer-molv',
				title : "选择办理人员",
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
							vm.personterminal.person = nodes[0].id.toString();
							vm.personterminal.personName = nodes[0].name.toString();
							layer.close(index);
						} else {
							alert('请选择一个人作为办理人员');
						}
					}
				}
			});
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData : {
					'qdevtype' : vm.q.qdevtype,
					'keyword' : vm.q.keyword
				},
                page:page
            }).trigger("reloadGrid");
		}
	}
});
