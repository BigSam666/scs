$(function () {
    $("#jqGrid").jqGrid({
        url: '../sys/menu/list',
        datatype: "json",
        colModel: [			
			{ label: '菜单ID', name: 'id', width: 40, key: true },
			{ label: '排序号', name: 'sort', width: 50},
			{ label: '菜单名称', name: 'name', width: 60 },
			{ label: '上级菜单', name: 'pnm', width: 60, sortable:false, formatter: function(value, options, row){
			return value ? value : '一级菜单';
			}},	
			{ label: '菜单图标', name: 'icon', width: 50, formatter: function(value, options, row){
				return value == null ? '' : '<i class="'+value+' fa-lg"></i>';
			}},
			{ label: '菜单URL', name: 'url', width: 100 },
			{ label: '授权标识', name: 'perms', width: 100 },
			{ label: '类型', name: 'type', width: 50, formatter: function(value, options, row){
				if(value === 0){
					return '<span class="label label-primary">目录</span>';
				}
				if(value === 1){
					return '<span class="label label-success">菜单</span>';
				}
				if(value === 2){
					return '<span class="label label-warning">按钮</span>';
				}
			}},
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
	}
};
var ztree;

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			name: null,
			pnm: null,
			pid: null
		},
		showList: true,
		showTree: false,
		upd: false,
		title: null,
		menu:{
			pnm:null,
			pid:0,
			type:1,
			sort:0
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		getMenu: function(id){
			//加载菜单树
			$.get("../sys/menu/select", function(r){
				ztree = $.fn.zTree.init($("#menuTree"), setting, r.menuList);
				var node = ztree.getNodeByParam("id", vm.menu.pid);
				ztree.selectNode(node);
				
				vm.menu.pnm = node.name;
			})
		},
		add: function(){
			vm.showList = false;
			vm.upd = true;
			vm.title = "新增";
			vm.menu = {pnm:null,pid:0,type:1,sort:0};
			vm.getMenu();
		},
		update: function (id, isupdate) {
			if(id == null){
				return ;
			}
			$.get("../sys/menu/info/"+id, function(r){
    			vm.showList = false;
    			vm.upd = isupdate;
                vm.title = isupdate ? "修改" : "查看";
                vm.menu = r.menu;
            });
			vm.getMenu();
		},
		showTreeD: function(){
			vm.showTree = true;
			vm.getMTree();
		},
		getMTree: function(){
			$.get("../sys/menu/selectAll", function(r){
				ztree = $.fn.zTree.init($("#deptTree2"), setting, r.menuList);
				/*ztree.expandAll(true); */
			})
		},
		del: function (id) {
			confirm('确定要删除选中的记录？', function(){
		    	$.get("../sys/menu/delete/"+id, function(r){
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
		saveOrUpdate: function (event) {
			var url = vm.menu.id == null ? "../sys/menu/save" : "../sys/menu/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.menu),
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
		menuTree2: function(){
			vm.getMenu();
			layer.open({
				type: 1,
				offset: '50px',
				skin: 'layui-layer-molv',
				title: "选择上级菜单",
				area: ['300px', '450px'],
				shade: 0,
				shadeClose: false,
				content: jQuery("#menuLayer"),
				btn: ['确定', '取消'],
				btn1: function (index) {
					var node = ztree.getSelectedNodes();
					//选择上级菜单
					vm.q.pid = node[0].id;
					vm.q.pnm = node[0].name;
					vm.query();
					layer.close(index);
				}
			});
		},
		menuTree: function(){
			layer.open({
				type: 1,
				offset: '50px',
				skin: 'layui-layer-molv',
				title: "选择菜单",
				area: ['300px', '450px'],
				shade: 0,
				shadeClose: false,
				content: jQuery("#menuLayer"),
				btn: ['确定', '取消'],
				btn1: function (index) {
					var node = ztree.getSelectedNodes();
					//选择上级菜单
					vm.menu.pid = node[0].id;
					vm.menu.pnm = node[0].name;
					
					layer.close(index);
	            }
			});
		},
		reload: function (event) {
			vm.showList = true;
			vm.showTree = false;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'name': vm.q.name,'pid': vm.q.pid},
                page:page
            }).trigger("reloadGrid");
		}
	}
});