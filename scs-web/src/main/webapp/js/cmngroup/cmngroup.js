$(function () {
    $("#jqGrid").jqGrid({
        url: '../cmngroup/list',
        datatype: "json",
        colModel: [			
		// 隐藏主键
		{ label: 'id', name: 'id', width: 50, key: true ,hidden : true },
		{ label: '组名', name: 'nm', width: 80 }, 
		{ label: '用户id', name: 'userid', width: 80 }, 
		{ label: '用户', name: 'usernames', width: 100,ormatter: function(value, options, row){
			return row.usernames;
			}},
		// 状态
		{ label: '状态', name: 'status', width: 30, hidden: Util.consts.isadmin, formatter: function(value, options, row){
			return value == 0 ? 
				'<span class="label label-danger">无效</span>' : 
				'<span class="label label-success">有效</span>';
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
		nocheckInherit:true
	}
};
var ztree;

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			nm:null//组名
		},
		showList: true,
		upd: false,
		title: null,
		userid:{},
		usernm:{},
		cmngroup: {status:1, userid:'', usernm:'' }
	},
	methods: {
		query: function () {
			vm.reload();
		},
		getDept: function(id){
			//加载菜单树
			$.get("../sys/department/selectForCmn", function(r){
				if(r.code!=0){
					alert(r.msg);
					vm.reload();
				}
				ztree = $.fn.zTree.init($("#deptTree"), setting, r.deptList);
    			if(id != null){
					vm.getInfo(id);
				}
			})
		},
		add: function(){
			vm.showList = false;
			vm.upd = true;
			vm.title = "新增";
			vm.cmngroup = {status:1, userid:'', usernm:''};
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
			var url = vm.cmngroup.id == null ? "../cmngroup/save" : "../cmngroup/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.cmngroup),
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
		    	$.get("../cmngroup/delete/"+id, function(r){
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
			$.get("../cmngroup/info/"+id, function(r){
                vm.cmngroup = r.cmngroup;
                //勾选电话
    			var userid = vm.cmngroup.userid.split(',');
    			for(var i=0; i<userid.length; i++) {
    				var node = ztree.getNodeByParam("userid", userid[i]);
    				ztree.checkNode(node, true, true);
    			}
    			//获取选择的菜单
				var nodes = ztree.getCheckedNodes(true);
				var usernmList = new Array();
				var userList = new Array();
				for(var i=0; i<nodes.length; i++) {
					if(nodes[i].userid){
						if ( userList.indexOf(nodes[i].userid) ==-1) {
							userList.push(nodes[i].userid);
							usernmList.push(nodes[i].name);
						}
					}
				}
				vm.cmngroup.usernm = usernmList.toString();
            });
		},
		deptTree: function(){
			layer.open({
				type: 1,
				offset: '50px',
				skin: 'layui-layer-molv',
				title: "选择用户",
				area: ['300px', '450px'],
				shade: 0,
				shadeClose: false,
				content: jQuery("#deptLayer"),
				btn: ['确定', '取消'],
				btn1: function (index) {
					//获取选择的菜单
					var nodes = ztree.getCheckedNodes(true);
					var userList = new Array();
					var usernmList = new Array();
					for(var i=0; i<nodes.length; i++) {
						if(nodes[i].userid){
							if ( userList.indexOf(nodes[i].userid) ==-1) {
								if(userList.length>=99){
									alert("通讯录用户不能大于100个");
								} else {
									userList.push(nodes[i].userid);
									usernmList.push(nodes[i].name);
								}
							}
						}
					}
					vm.cmngroup.userid = userList.toString();
					vm.cmngroup.usernm = usernmList.toString();
					layer.close(index);
	            }
			});
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
				postData:{nm:vm.q.nm},
                page:page
            }).trigger("reloadGrid");
		}
	}
});
