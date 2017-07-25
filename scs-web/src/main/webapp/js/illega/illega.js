$(function () {
    $("#jqGrid").jqGrid({
        url: '../illega/list',
        datatype: "json",
        colModel: [			
			// 隐藏主键
			{ label: '主键id', name: 'id', width: 50, key: true ,hidden : true },
			{ label: '车牌号', name: 'cnoText', width: 80 }, 
				{ label: '违章日期', name: 'illtm', width: 80, formatter:'date' }, 
				{ label: '违章原因', name: 'causeName', width: 80 }, 
				{ label: '违章人员', name: 'personName', width: 80 }, 
				{ label: '处罚金额', name: 'amount', width: 80 }, 
				{ label: '扣分', name: 'score', width: 80 }, 
				{ label: '处罚单位', name: 'unit', width: 80 }, 
//				{ label: '违章地点', name: 'addr', width: 80 }, 
		// 状态
//			{ label: '状态', name: 'status', width: 50, hidden: Util.consts.isadmin, formatter: function(value, options, row){
//			return value == 0 ? 
//				'<span class="label label-danger">无效</span>' : 
//				'<span class="label label-success">有效</span>';
//			}},
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
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
 // 表单验证
    validate = $(".form-horizontal").validate({
  	   onfocusout: false,
 	   onkeyup: false,
 	   rules: {
 		  cno: {
 			   required: true
 		   },
 		  illtm: {
 			   required: true
 		   },
 		  cause: {
 			   required: true
 		   },
 		  person: {
 			   required: true
 		   },
 		  amount: {
 			   required: true
 		   },
 		  score: {
			   required: true
		   }
 	   },
 	   messages: {
 		  cno: {
 		       required: "必填，请输入车牌号"
 		   },
 		  illtm: {
			   required: "必填，请输入违章日期"
		   },
		   cause: {
			   required: "必填，请输入违章原因"
		   },
		   person: {
			   required: "必填，请输入违章人员"
		   },
		   amount: {
			   required: "必填，请输入处罚金额"
		   },
 		   score: {
 			   required: "必填，请输入扣分"
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
			idKey : "id",
			pIdKey : "pid",
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
		q:{condition:'',
			starttime:'',
			endtime:''},
		showList: true,
		upd: false,
		title: null,
		illega: {status:1,person:'',personName:''}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		getCno : function(id) {
			$.get("../car/listAll", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				// 填充数据到页面
				$('#cno').combobox({
					readonly: false,
				    data: r.data,
				    valueField:'id',
				    textField:'cno',
				    panelHeight: 'auto',
				    editable: false,
		   			onChange:function(newValue, oldValue){
		   		    }
				});
			})
		},
		getCause : function(id) {
			$.get("../basecode/selectByType/illegaCause", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				// 填充数据到页面
				$('#cause').combobox({
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
			$.get("../sys/department/selectForCmn", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				ztreePerson = $.fn.zTree.init($("#personTree"), settingPerson, r.deptList);
			});
		},
		add: function(){
			vm.showList = false;
			vm.upd = true;
			vm.title = "新增";
			vm.illega = {status:1,person:'',personName:''};
			
			vm.getCno(); // 车牌号
			vm.getPerson(); // 违章人员
			vm.getCause(); // 违章原因
		},
		update: function (id, isupdate) {
			if(id == null){
				return ;
			}
			vm.showList = false;
			vm.upd = isupdate;
            vm.title = isupdate ? "修改" : "查看";
            
            vm.getInfo(id);
            
            // 车牌号
            $.get("../car/listAll", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				var _data = r.data;
				_data.forEach(function (e){
					if (e.id == vm.illega.cno) {
						e.selected = true;
					}
				});
				$('#cno').combobox({
					readonly: false,
				    data: r.data,
				    valueField:'id',
				    textField:'cno',
				    panelHeight: 'auto',
				    editable: false,
		   			onChange:function(newValue, oldValue){
		   		    }
				});
			});
            // 违章人员
            $.get("../sys/department/selectForCmn", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				ztreePerson = $.fn.zTree.init($("#personTree"), settingPerson, r.deptList);
				var nodes = ztreePerson.transformToArray(ztreePerson.getNodes());
				nodes.forEach(function(e){
					if(e.userid == vm.illega.person) {
						vm.illega.person = e.userid.toString();
						vm.illega.personName = e.name.toString();
					}
				});
			});
            // 违章日期
            var date = new Date(vm.illega.illtm);
            $("#illtm").val(date.Format("yyyy-MM-dd"));
            // 违章原因
			$.get("../basecode/selectByType/illegaCause", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				var _data = r.data;
				_data.forEach(function (e){
					if (e.id == vm.illega.cause) {
						e.selected = true;
					}
				});
				$('#cause').combobox({
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
		},
		saveOrUpdate: function (event) {
			if(validate.form()) {
				var url = vm.illega.id == null ? "../illega/save" : "../illega/update";
				var _json = $(".form-horizontal").form2json();
				_json.id = vm.illega.id;
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
		    	$.get("../illega/delete/"+id, function(r){
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
			   url: "../illega/info/"+id,
			   async: false,
			   success: function(r) {
				   vm.illega = r.illega;
			   }
			});
		},
		personTree : function() {
			layer.open({
				type : 1,
				offset : '50px',
				skin : 'layui-layer-molv',
				title : "选择违章人员",
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
						if (node.userid) {
							vm.illega.person = nodes[0].userid.toString();
							vm.illega.personName = nodes[0].name.toString();
							layer.close(index);
						} else {
							alert('请选择违章人员');
						}
					}
				}
			});
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'condition': vm.q.condition,
					'starttime':$("#starttime").val(),
					'endtime':$("#endtime").val()
				},
                page:page
            }).trigger("reloadGrid");
		}
	}
});
