$(function () {
    $("#jqGrid").jqGrid({
        url: '../sys/department/list',
        datatype: "json",
        colModel: [
			// 隐藏主键
			{ label: 'id', name: 'id', width: 50, key: true},
			{ label: '部门名称', name: 'name', width: 80 }, 			
			{ label: '上级部门', name: 'pnm', width: 80, formatter: function(value, options, row){
				return value ? value : '-';
			}},	 
			{ label: '是否有效', name: 'valid', width: 80, hidden: !Util.consts.isadmin, formatter: function(value, options, row){
				return value == 0 ? 
				'<span class="label label-danger">无效</span>' : 
				'<span class="label label-success">有效</span>';
			}},			
			{ label: '排序号', name: 'sort', width: 80 }, 
			{ label: '组织类型', name: 'otype', width: 80, formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}},
			{ label: '责任人', name: 'pname', width: 80},
			{ label: '备注', name: 'rmk', width: 80 }, 		
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
        autowidth: true,  
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
        },
    });

	$('#otype').combobox({
		readonly: false,
		disabled: false,
		data: Util.loadData("../basecode/selectByType/otype").data,
		valueField:'no',
		textField:'cnm',
		editable: false,
		value: 0,
		panelHeight: 'auto'
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
var pnameztree;

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		showTree: false,
		upd: false,
		title: null,
		department: {status:1, pnm:'', pid: 0 }
	},
	methods: {
		query: function () {
			vm.reload();
		},
		getDepartment: function(id){
			//加载菜单树
			$.get("../sys/department/select", function(r){
				ztree = $.fn.zTree.init($("#deptTree"), setting, r.deptList);
				if(id != null){
					vm.getInfo(id);
				}
			})
		},
		pTree: function(id){
			//加载责任人
			$.get("../sys/department/selectForCmn", function(r){
				pnameztree = $.fn.zTree.init($("#pTree"), setting, r.deptList);
				if(id != null){
					vm.getInfo(id);
				}
			})
		},
		getDepartment2: function(){
			$.get("../sys/department/select", function(r){
				ztree = $.fn.zTree.init($("#deptTree2"), setting, r.deptList);
				ztree.expandAll(true); 
			})
		},
		add: function(){
			vm.showList = false;
			vm.upd = true;
			vm.title = "新增";
			vm.department = {valid:1, pnm:'', pid: 0};
			vm.getDepartment();
			vm.pTree();
		},
		showTreeD: function(){
			vm.showTree = true;
			vm.getDepartment2();
		},
		update: function (id, isupdate) {
			if(id == null){
				return ;
			}
			vm.showList = false;
			vm.upd = isupdate;
            vm.title = isupdate ? "修改" : "查看";

            vm.getInfo(id)
            vm.getDepartment(id);
			vm.pTree(id);
		},
		del: function (id) {
			confirm('确定要删除选中的记录？', function(){
		    	$.get("../sys/department/delete/"+id, function(r){
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
			var url = vm.department.id == null ? "../sys/department/save" : "../sys/department/update";
			vm.department.otype = $('#otype').combobox('getValue');
			debugger
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.department),
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
		getInfo: function(id){
			$.get("../sys/department/info/"+id, function(r){
                vm.department = r.department;
                if(id){
					var node = ztree.getNodeByParam("id", r.department.pid);
					ztree.selectNode(node);
					vm.department.pnm = node.name;
					$('#otype').combobox('setValues', vm.department.otype);
				}
            });
		},
		deptTree: function(){
			layer.open({
				type: 1,
				offset: '50px',
				skin: 'layui-layer-molv',
				title: "选择上级部门",
				area: ['300px', '450px'],
				shade: 0,
				shadeClose: false,
				content: jQuery("#deptLayer"),
				btn: ['确定', '取消'],
				btn1: function (index) {
					var node = ztree.getSelectedNodes();
					//选择上级菜单
					vm.department.pid = node[0].id;
					vm.department.pnm = node[0].name;
					
					layer.close(index);
	            }
			});
		},
		pnameTree: function(){
			layer.open({
				type: 1,
				offset: '50px',
				skin: 'layui-layer-molv',
				title: "选择负责人",
				area: ['300px', '450px'],
				shade: 0,
				shadeClose: false,
				content: jQuery("#pnameLayer"),
				btn: ['确定', '取消'],
				btn1: function (index) {
					var node = pnameztree.getSelectedNodes();
					if(node[0].userid){
						vm.department.pesponsible = node[0].userid;
						$("input[name='pname']").val(node[0].name);
						layer.close(index);
					} else {
						layer.msg("负责人只能选择人，不能选择部门！", function(){});
						return false
					}
				}
			});
		},
		reload: function (event) {
			vm.showList = true;
			vm.showTree = false;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});
