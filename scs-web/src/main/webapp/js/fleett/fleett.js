var validate = null;
$(function() {
	$("#jqGrid").jqGrid({
		url : '../fleett/list',
		datatype : "json",
		colModel : [
				// 隐藏主键
				{
					label : '主键id',
					name : 'id',
					width : 50,
					key : true,
					hidden : true
				},
				{
					label : '名称',
					name : 'nm',
					width : 80
				},
				{
					label : '所属部门',
					name : 'deptName',
					width : 80
				},
				{
					label : '队长',
					name : 'captainName',
					width : 80
				},
				{
					label : '备注',
					name : 'rmk',
					width : 80
				},
				{
					label : '操作',
					name : 'operation',
					width : 40,
					formatter : function(value, options, row) {
						var oper = "<a onClick=\"javascript:vm.update("
								+ options.rowId
								+ ", false);\" title='查看' ><i class=\"fa fa-info-circle\" style='color:violet;'></i></a>&nbsp;&nbsp;";
						oper += update ? "<a onClick=\"javascript:vm.update("
								+ options.rowId
								+ ", true);\" title='修改'  ><i class=\"fa fa-pencil-square-o\" style='color:green;'></i></a>&nbsp;&nbsp;"
								: "";
						oper += del ? "<a onClick=\"javascript:vm.del("
								+ options.rowId
								+ ");\" title='删除'  ><i class=\"fa fa-trash-o\" style='color:red;'></i></a>"
								: "";
						return oper;
					}
				}, ],
		viewrecords : true,
		height : 385,
		rowNum : 10,
		rowList : [ 10, 30, 50 ],
		rownumbers : false,
		rownumWidth : 25,
		autowidth : true,
		multiselect : false,
		pager : "#jqGridPager",
		jsonReader : {
			root : "page.list",
			page : "page.currPage",
			total : "page.totalPage",
			records : "page.totalCount"
		},
		prmNames : {
			page : "page",
			rows : "limit",
			order : "order"
		},
		gridComplete : function() {
			// 隐藏grid底部滚动条
			$("#jqGrid").closest(".ui-jqgrid-bdiv").css({
				"overflow-x" : "hidden"
			});
		}
	});
    // 表单验证
    validate = $(".form-horizontal").validate({
  	   onfocusout: false,
 	   onkeyup: false,
 	   rules: {
 		   nm: {
 			   required: true
 		   },
 		   dept: {
 			   required: true
 		   },
 		   captain: {
 			   required: true
 		   }
 	   },
 	   messages: {
 		   nm: {
 		       required: "必填，请输入名称"
 		   },
 		   dept: {
 			   required: "必填，请选择一个部门"
 		   },
 		   captain: {
 			   required: "必填，请选择一个队长"
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

var setting = {
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
};
var settingCaptain = {
	data : {
		simpleData : {
			enable : true,
			idKey : "id",
			pIdKey : "pid",
			rootPId : 0
		},
		key : {
			url : "nourl"
		}
	},
};
var ztree;
var ztreeCaptain;

var vm = new Vue({
	el : '#rrapp',
	data : {
		q : {
			condition:null
		},
		showList : true,
		upd : false,
		title : null,
		fleett : {
			status : 1,
			dept : '',
			deptName : '',
			captain : '',
			captainName : ''
		},
		department : {
			status : 1,
			pnm : '',
			pid : 0
		}
	},
	methods : {
		query : function() {
			vm.reload();
		},
		getDepartment : function(id) {
			// 加载菜单树
			$.get("../sys/department/select", function(r) {
				ztree = $.fn.zTree.init($("#deptTree"), setting, r.deptList);
				if(id){
					var node = ztree.getNodeByParam("id", vm.department.pid);
					ztree.selectNode(node);
					vm.department.pnm = node.name;
				}
			})
		},
		getUser : function(id) {
			// 加载菜单树
			$.get("../sys/department/selectForCmn", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				ztreeCaptain = $.fn.zTree.init($("#deptTreeCaptain"), settingCaptain, r.deptList);
				if (id != null) {
					vm.getInfo(id);
				}
			})
		},
		add : function() {
			vm.showList = false;
			vm.upd = true;
			vm.title = "新增";
			vm.fleett = {
				status : 1,
				dept : '',
				deptName : '',
				captain : '',
				captainName : ''
			};
			vm.getDepartment();
			vm.getUser(null);
		},
		update : function(id, isupdate) {
			if (id == null) {
				return;
			}
			vm.showList = false;
			vm.upd = isupdate;
            vm.title = isupdate ? "修改" : "查看";
			vm.getInfo(id);
		},
		saveOrUpdate : function(event) {
			if(validate.form()){
				var url = vm.fleett.id == null ? "../fleett/save" : "../fleett/update";
				$.ajax({
					type : "POST",
					url : url,
					data : JSON.stringify(vm.fleett),
					success : function(r) {
						if (r.code === 0) {
							alert('操作成功', function(index) {
								vm.reload();
							});
						} else {
							alert(r.msg);
						}
					}
				});
			}
		},
		del : function(id) {
			confirm('确定要删除选中的记录？', function() {
				$.get("../fleett/delete/" + id, function(r) {
					if (r.code == 0) {
						$("#jqGrid").trigger("reloadGrid");
						top.layer.closeAll();
					} else {
						alert(r.msg);
					}
				});
			});
		},
		getInfo : function(id) {
			$.get("../fleett/info/" + id, function(r) {
				vm.fleett = r.fleett;
				var node = ztree.getNodeByParam("id", vm.fleett.dept);
				ztree.checkNode(node, true, true);
				var nodeCaptain = ztreeCaptain.getNodeByParam("id", vm.fleett.captain);
				ztree.checkNode(nodeCaptain, true, true);
			});
		},
		deptTree : function() {
			layer.open({
				type : 1,
				offset : '50px',
				skin : 'layui-layer-molv',
				title : "选择部门",
				area : [ '300px', '450px' ],
				shade : 0,
				shadeClose : false,
				content : jQuery("#deptLayer"),
				btn : [ '确定', '取消' ],
				btn1 : function(index) {
					// 获取选择的菜单
					var nodes = ztree.getSelectedNodes();
					if (nodes.length > 0 && nodes[0].pid != -1) {
						if(nodes[0].otype == '3'){
							vm.fleett.dept = nodes[0].id.toString();
							vm.fleett.deptName = nodes[0].name.toString();
							layer.close(index);
						} else {
							layer.msg('请选择一个项目部', function(){});
						}
					} else {
						layer.msg('请选择一个部门', function(){});
					}
				}
			});
		},
		deptTreeCaptain : function() {
			layer.open({
				type : 1,
				offset : '50px',
				skin : 'layui-layer-molv',
				title : "选择队长",
				area : [ '300px', '450px' ],
				shade : 0,
				shadeClose : false,
				content : jQuery("#deptLayer1"),
				btn : [ '确定', '取消' ],
				btn1 : function(index) {
					var nodes = ztreeCaptain.getSelectedNodes();
					if(nodes.length > 0) {
						var node = nodes[0];
						if (node.userid) {
							vm.fleett.captain = node.userid.toString();
							vm.fleett.captainName = node.name.toString();
							layer.close(index);
						} else {
							layer.msg('请选择一个人作为队长', function(){});
						}
					}

				}
			});
		},
		reload : function(event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				postData:{'condition': vm.q.condition},
				page : page
			}).trigger("reloadGrid");
			$.get("../sys/department/select", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				ztree = $.fn.zTree.init($("#deptTree"), setting, r.deptList);
			});
			$.get("../sys/user/selectForZtree", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				ztreeCaptain = $.fn.zTree.init($("#deptTreeCaptain"), settingCaptain, r.userList);
			});
		}
	}
});
