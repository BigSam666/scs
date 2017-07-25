$(function () {
    $("#jqGrid").jqGrid({
        url: '../sys/role/list',
        datatype: "json",
        colModel: [			
			{ label: '角色ID', name: 'id', width: 45, key: true },
			{ label: '角色名称', name: 'name', width: 75 },
			{ label: '所属父级', name: 'pname', width: 75 },
			{ label: '所属部门', name: 'deptText', width: 75 },
			{ label: '备注', name: 'rmk', width: 100 },
			{ label: '创建时间', name: 'crttm', width: 80},
			{ label: '操作', name: 'operation', width: 40, formatter: function(value, options, row){
				var oper = "<a onClick=\"javascript:vm.update("+options.rowId+", false);\" title='查看' ><i class=\"fa fa-info-circle\" style='color:violet;'></i></a>&nbsp;&nbsp;";
				oper += update ? "<a onClick=\"javascript:vm.update("+options.rowId+", true);\" title='修改'  ><i class=\"fa fa-pencil-square-o\" style='color:green;'></i></a>&nbsp;&nbsp;":"";
				oper += del ? "<a onClick=\"javascript:vm.del("+options.rowId+");\" title='删除' ><i class=\"fa fa-trash-o\" style='color:red;'></i></a>" : "";
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
});

var setting2 = {
	data: {
		simpleData: {
			enable: true,
			idKey: "id",
			pIdKey: "pid",
		},
		key: {
			url:"nourl"
		}
	}
};
var ztree2;

var setting = {
	data: {
		simpleData: {
			enable: true,
			idKey: "id",
			pIdKey: "pid",
			rootPId: -1
		},
		key: {
			url:"nourl"
		}
	},
	check:{
		enable:true,
		nocheckInherit:true
	}
};
var ztree;
	
var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			name: null
		},
		showList: true,
		showTree: false,
		upd: false,
		title:null,
		role:{ pid:{}, dept:{}}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.upd = true;
			vm.title = "新增";
			vm.role = { pid:'', dept:''};
			vm.getMenuTree(null);
			vm.getDept(null);
		},
		update: function (id, isupdate) {
			if(id == null){
				return ;
			}
			vm.showList = false;
			vm.upd = isupdate;
            vm.title = isupdate ? "修改" : "查看";

            vm.getMenuTree(id);
			vm.getDept(id);
		},
		del: function (id) {
			confirm('确定要删除选中的记录？', function(){
		    	$.get("../sys/role/delete/"+id, function(r){
					if(r.code == 0){
						/*top.location.reload();*/
						$("#jqGrid").trigger("reloadGrid");
						top.layer.closeAll();
					}else{
						alert(r.msg);
					}
				});
			});
		},
		getRole: function(id){
            $.get("../sys/role/info/"+id, function(r){
            	vm.role = r.role;
                
                //勾选角色所拥有的菜单
    			var menuIds = vm.role.menuIdList;
    			for(var i=0; i<menuIds.length; i++) {
    				var node = ztree.getNodeByParam("id", menuIds[i]);
    				ztree.checkNode(node, true, false);
    			}
    		});
		},
		saveOrUpdate: function (event) {
			//获取选择的菜单
			var nodes = ztree.getCheckedNodes(true);
			var menuIdList = new Array();
			for(var i=0; i<nodes.length; i++) {
				menuIdList.push(nodes[i].id);
			}
			vm.role.menuIdList = menuIdList;
			
			var url = vm.role.id == null ? "../sys/role/save" : "../sys/role/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.role),
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
		},
		getDept: function(id){
			//加载部门角色树
			$.get("../sys/department/selectDR", function(r){
				ztree2 = $.fn.zTree.init($("#deptTree"), setting2, r.deptList);
			})
		},
		deptTree: function(){
			layer.open({
				type: 1,
				offset: '50px',
				skin: 'layui-layer-molv',
				title: "选择部门",
				area: ['300px', '450px'],
				shade: 0,
				shadeClose: false,
				content: jQuery("#deptLayer"),
				btn: ['确定', '取消'],
				btn1: function (index) {
					var node = ztree2.getSelectedNodes();
					var b = node[0];
					if(b.id.toString().indexOf('r') != '-1'){
						// 表示选中的是角色
						// 截取id中的r
						//node[0].id.replace('r', '');
						vm.role.pid = b.id.replace('r', '');
					} else {
						vm.role.pid = 0;
					}
					// 如果当前级别没有部门 则需要取上级部门当本级部门
					dept = '';
					if(b.dept){
						dept = b.dept;
					} else {
						if(b.pid.toString().indexOf('r') == '-1'){
							dept = b.pid;
						} else {
							// 循环找到部门(因为他的父级可能是多个角色)
							for(;;){
								b = ztree2.getNodeByParam("id", b.pid);
								if(b.pid.toString().indexOf('r') == '-1'){
									dept = b.pid;
									break;
								}
							}
						}
					}
					vm.role.dept = dept;
					$("input[name='pname']").val(b.name);
					
					layer.close(index);
	            }
			});
		},
		getRoleTree: function(){
			$.get("../sys/department/selectDR", function(r){
				ztree = $.fn.zTree.init($("#deptTree2"), setting2, r.deptList);
				ztree.expandAll(true); 
			})
		},
		showTreeD: function(){
			vm.showTree = true;
			vm.getRoleTree();
		},
		getMenuTree: function(id) {
			//加载菜单树
			$.get("../sys/menu/perms", function(r){
				ztree = $.fn.zTree.init($("#menuTree"), setting, r.menuList);
				//展开所有节点
				//ztree.expandAll(true);
				
				if(id != null){
					vm.getRole(id);
				}
			});
	    },
	    reload: function (event) {
			vm.showList = true;
			vm.showTree = false;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{'name': vm.q.name},
                page:page
            }).trigger("reloadGrid");
		}
	}
});