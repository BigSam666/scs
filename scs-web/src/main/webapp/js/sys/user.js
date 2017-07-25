var validate = null;
$(function () {
    $("#jqGrid").jqGrid({
        url: '../sys/user/list',
        datatype: "json",
        colModel: [			
			{ label: '用户ID', name: 'id', width: 45, key: true },
			{ label: '用户名', name: 'name', width: 75 },
			{ label: '真实姓名', name: 'realname', width: 75 },
			{ label: '邮箱', name: 'email', width: 90 },
			{ label: '手机号', name: 'mobile', width: 80 },
			{ label: '状态', name: 'status', width: 30, formatter: function(value, options, row){
				return value == 0 ? 
					'<span class="label label-danger">禁用</span>' : 
					'<span class="label label-success">正常</span>';
			}},
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
    
    // 表单验证 开始
    validate = $(".form-horizontal").validate({
  	   onfocusout: false,
 	   onkeyup: false,
 	   rules: {
 		   name: {
 			   rangelength: [3,10],
 			   required: true,
 			   letter: true
 		   },
 		  realname: {
 			   rangelength: [2,10],
 			   required: true,
 			   chinese: true
 		   },
 		   email: {
 			   email: true,
 			   required: true
 		   },
 		   mobile: {
 			   mobile: true,
 			   required: true
 		   },
 		   roleid: {
 			   required: true
 		   },
 		   status: {
 			   required: true
 		   },
 		   dept: {
 			   required: true
 		   }
 	   },
 	   messages:{
 		   name:{
 		       rangelength: $.validator.format("用户名请输入 {0} 到 {1} 个字符."),
 		       required: "必填，请输入用户名",
 		       chinese: "用户名只能输入大小写字母"
 		   },
 		  realname:{
 			   rangelength: $.validator.format("用户名请输入 {0} 到 {1} 个字符."),
 			   required: "必填，请输入真实姓名",
 			   letter: "真实姓名只能输入中文"
 		   },
 		   email: {
 			   email: "请输入正确的邮箱格式",
 			   required: "必填，请输入邮箱"
 		   },
 		   mobile: {
 			   mobile: "请输入正确的手机号码格式",
 			   required: "必填，请输入手机号码"
 		   },
 		   roleid: {
 			   required: "必填，请选择角色"
 		   },
 		   status: {
 			   required: "必填，请选择状态"
 		   },
 		   dept: {
 			   required: "必填，请选择所属部门"
 		   }
 	   },
 	  showErrors: function(errorMap, errorList) {
			if ( errorList && errorList.length > 0 ) {  //如果存在错误信息
				//alert(errorList[0].message)
				layer.msg(errorList[0].message, function(){});
			}
		}
    });
    // 表单验证结束
 
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
		upd: false,
		title:null,
		user:{
			status:1,
			roleIdList:[],
			deptList:[],
			deptTextList:'',
			roleTextList:''
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		getDept: function(id){
			//加载部门角色树
			$.get("../sys/department/selectDR", function(r){
				ztree = $.fn.zTree.init($("#deptTree"), setting, r.deptList);
    			if(id != null){
					vm.getUser(id);
				}
			})
		},
		add: function(){
			vm.showList = false;
			vm.upd = true;
			vm.title = "新增";
			vm.roleList = {};
			vm.user = {status:1,roleIdList:'', deptList:'', deptTextList:'', roleTextList:''};
			//获取角色信息
			/*this.getRoleList();*/
			this.getDept(null);
		},
		update: function (id, isupdate) {
			if(id == null){
				return ;
			}
			vm.showList = false;
			vm.upd = isupdate;
            vm.title = isupdate ? "修改" : "查看";

			//获取角色信息
			/*this.getRoleList();*/
			this.getDept(id);
		},
		del: function (id) {
			confirm('确定要删除选中的记录？', function(){
		    	$.get("../sys/user/delete/"+id, function(r){
					if(r.code == 0){
						$("#jqGrid").trigger("reloadGrid");
						top.layer.closeAll();
					}else{
						alert(r.msg);
					}
				});
			});
		},
		saveOrUpdate: function (event) {
			if(validate.form()){
				var url = vm.user.id == null ? "../sys/user/save" : "../sys/user/update";
				$.ajax({
					type: "POST",
				    url: url,
				    data: JSON.stringify(vm.user),
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
		getUser: function(id){
			$.get("../sys/user/info/"+id, function(r){
				vm.user = r.user;
				//勾选角色所拥有的部门，角色
				var deptIds = vm.user.deptList;
				for(var i=0; i<deptIds.length; i++) {
					var node = ztree.getNodeByParam("id", deptIds[i]);
					ztree.checkNode(node, true, true);
				}
    			var roleids = vm.user.roleIdList;
    			for(var i=0; i<roleids.length; i++) {
    				var node = ztree.getNodeByParam("id", 'r'+roleids[i]);
    				ztree.checkNode(node, true, true);
    			}
			});
		},
		/*getRoleList: function(){
			$.get("../sys/role/select", function(r){
				vm.roleList = r.list;
			});
		},*/
		deptTree: function(){
			layer.open({
				type: 1,
				offset: '50px',
				skin: 'layui-layer-molv',
				title: "选择部门/角色",
				area: ['300px', '450px'],
				shade: 0,
				shadeClose: false,
				content: jQuery("#deptLayer"),
				btn: ['确定', '取消'],
				btn1: function (index) {
					var nodes = ztree.getCheckedNodes(true);
					var deptList = new Array();
					var deptText = new Array();
					var roleIdList = new Array();
					var roleTextList = new Array();
					for(var i=0; i<nodes.length; i++) {
						//if(nodes[i].id != 0 && !nodes[i].isParent){
						if(nodes[i].id != 0){
							// 这里判断角色和部门
							b = nodes[i];
							// 有角色就添加角色
							// 没有角色则不做任何操作
							if(b.id.toString().indexOf('r') != '-1'){
								// 表示选中的是角色
								// 截取id中的r
								//node[0].id.replace('r', '');
								roleIdList.push(b.id.replace('r', ''));
								roleTextList.push(b.name);
							}
							// 如果当前级别没有部门 则需要取上级部门当本级部门
							if(b.dept != undefined){
								if(deptList.indexOf(b.dept) == '-1'){
									deptList.push(b.dept);
									deptText.push(b.name);
								}
							} else {
								// 表示他的上级不是一个角色
								if(b.pid.toString().indexOf('r') == '-1'){
									b = ztree.getNodeByParam("id", b.pid);
									if(deptList.indexOf(b.id) == '-1'){
										deptList.push(b.id);
										deptText.push(b.name);
									}
								} else {
									// 循环找到部门(因为他的父级可能是多个角色)
									for(;;){
										b = ztree.getNodeByParam("id", b.pid);
										if(b.pid.toString().indexOf('r') == '-1'){
											if(deptList.indexOf(b.id) == '-1'){
												deptList.push(b.id);
												deptText.push(b.name);
											}
											break;
										}
									}
								}
							}
						}
					}
					// 选中部门
					for(var i=0; i<deptList.length; i++) {
						n = ztree.getNodeByParam("id", deptList[i]);
						ztree.checkNode(n, true, true);
					}
					vm.user.deptList = deptList;
					vm.user.deptTextList = deptText.toString();
					vm.user.roleIdList = roleIdList;
					vm.user.roleTextList = roleTextList;
					layer.close(index);
	            }
			});
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{'name': vm.q.name},
                page:page
            }).trigger("reloadGrid");
		}
	}
});
