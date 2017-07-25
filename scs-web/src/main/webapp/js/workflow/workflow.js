$(function () {
    $("#jqGrid").jqGrid({
        url: '../workflow/list',
        datatype: "json",
        colModel: [			
			// 隐藏主键
			{ label: '主键id', name: 'id', width: 50, key: true ,hidden : true },
			{ label: '节点名称', name: 'nodename', width: 60 }, 
				{ label: '节点编号', name: 'nodeno', width: 50 }, 
				{ label: '流程名称', name: 'nm', width: 50 }, 
				{ label: '流程编号', name: 'number', width: 50 }, 
				{ label: '上一节点编号', name: 'prevno', width: 50 }, 
				{ label: '下一节点编号', name: 'nextno', width: 50 }, 
				{ label: '回退节点编号', name: 'returnno', width: 50 }, 
				{ label: '角色列表', name: 'roleids', width: 50 }, 
				{ label: '节点类型', name: 'nodetype', width: 50 }, 
				{ label: '版本序号', name: 'vseq', width: 50 }, 
				{ label: '版本名称', name: 'vname', width: 50 }, 
				{ label: '创建时间', name: 'crttm', width: 80 }, 
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

});

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
		nocheckInherit:true,
		chkboxType: { "Y": "", "N": "" }
	},
	callback: {
		beforeCheck: zTreeBeforeCheck
	}
};
var ztree;

function zTreeBeforeCheck(treeId, treeNode){
	 if(treeNode.id.toString().indexOf('r') != '-1'){
		return true;
	} else {
		layer.msg("只能选择角色，不能选择一个部门。", function(){});
		return false;
	}
}

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		upd: false,
		title: null,
		workflow: {status:1, roleTList:[]}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.upd = true;
			vm.title = "新增";
			vm.roleList = {};
			vm.workflow = {status:1, roleTList: ''};
			this.getDept(null);
		},
		update: function (id, isupdate) {
			if(id == null){
				return ;
			}
			vm.showList = false;
			vm.upd = isupdate;
            vm.title = isupdate ? "修改" : "查看";
            vm.getInfo(id);
			this.getDept(id);
		},
		saveOrUpdate: function (event) {
			var url = vm.workflow.id == null ? "../workflow/save" : "../workflow/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.workflow),
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
		del: function (id) {
			confirm('确定要删除选中的记录？', function(){
		    	$.get("../workflow/delete/"+id, function(r){
					if(r.code == 0){
						$("#jqGrid").trigger("reloadGrid");
						top.layer.closeAll();
					}else{
						alert(r.msg);
					}
				});
			});
		},
		getDept: function(id){
			$.get("../sys/department/selectDR", function(r){
				ztree = $.fn.zTree.init($("#deptTree"), setting, r.deptList);
    			if(id != null){
					vm.getInfo(id);
				}
			})
		},
		roleTree: function(){
			layer.open({
				type: 1,
				offset: '50px',
				skin: 'layui-layer-molv',
				title: "选择角色",
				area: ['300px', '450px'],
				shade: 0,
				shadeClose: false,
				content: jQuery("#deptLayer"),
				btn: ['确定', '取消'],
				btn1: function (index) {
					var nodes = ztree.getCheckedNodes(true);
					var roleIds = new Array();
					var roleTList = new Array();
					
					for(var i=0; i<nodes.length; i++) {
						if(nodes[i].id != 0){
							// 这里判断角色和部门
							b = nodes[i];
							// 有角色就添加角色
							// 没有角色则不做任何操作
							if(b.id.toString().indexOf('r') != '-1'){
								// 表示选中的是角色
								roleIds.push(b.id.replace('r', ''));
								roleTList.push(b.name);
							}
						}
					}
					vm.workflow.roleids = roleIds.toString();
					vm.workflow.roleTList = roleTList.toString();
					
					layer.close(index);
	            }
			});
		},
		/*getRoleList: function(){
			$.get("../sys/role/select", function(r){
				vm.roleList = r.list;
			});
		},*/
		getInfo: function(id){
			$.get("../workflow/info/"+id, function(r){
                vm.workflow = r.workflow;
    			var roleids = vm.workflow.roleids.split(',');
				var roleTList = new Array();
    			for(var i=0; i<roleids.length; i++) {
    				var node = ztree.getNodeByParam("id", 'r'.concat(roleids[i]));
    				ztree.checkNode(node, true, true);
					roleTList.push(node.name);
    			}
				vm.workflow.roleTList = roleTList.toString();
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});
