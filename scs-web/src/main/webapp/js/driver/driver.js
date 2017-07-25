$(function () {
    $("#jqGrid").jqGrid({
        url: '../driver/showList',
        datatype: "json",
        colModel: [			
			// 隐藏主键
			{ label: '主键id', name: 'id', width: 50, key: true ,hidden : true },
			{ label: '驾照编号', name: 'dno', width: 80 }, 
			{ label: '准驾车型', name: 'dclassName', width: 80 }, 
			{ label: '领证日期', name: 'bdate', width: 80, formatter:'date' }, 
			{ label: '到期日期', name: 'edate', width: 80, formatter:'date' }, 
			{ label: '司机名', name: 'drealname', width: 80 }, 
			{ label: '身份证号', name: 'dcardno', width: 80 }, 
			{ label: '手机号', name: 'dmobile', width: 80 }, 
			{ label: '邮箱', name: 'demail', width: 80 }, 
			{ label: '所属部门', name: 'ddeptTextList', width: 80 }, 
			{ label: '所属角色', name: 'droleTextList', width: 80 }, 
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
 		  dno: {
			   required: true
		   },
 		  bdate: {
			   required: true
		   },
 		  edate: {
			   required: true
		   },
 		  driverId: {
			   required: true
		   },
 		   dclass: {
 			   required: true
 		   }
 	   },
 	   messages: {
 		  dno: {
 			 required: "必填，请输入驾照编号"
		   },
		  bdate: {
			  required: "必填，请输入领证日期"
		   },
		  edate: {
			  required: "必填，请输入到期日期"
		   },
		  driverId: {
			  required: "必填，请输入司机名"
		   },
 		   dclass: {
 		       required: "必填，请输入准驾车型"
 		   },
 	   },
 	   showErrors: function(errorMap, errorList) {
			if ( errorList && errorList.length > 0 ) {  //如果存在错误信息
				layer.msg(errorList[0].message, function(){});
			}
		}
    });
    // 填充数据到页面
	$('#dclass').combobox({
		readonly: false,
	    data: Util.loadData("../basecode/selectByType/dclass").data,
	    multiple:false,
	    valueField:'no',
	    textField:'cnm',
	    panelHeight: 'auto',
	    editable: false,
		onChange:function(newValue, oldValue){
			
	    }
	});
});

var ztreeDriver;

var settingDriver = {
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
		q:{
			condition:''
		},
		showList: true,
		upd: false,
		title: null,
		driver: {
			status:1,
			driverId:'',
			driverName:'',
			bdate:'',
			edate:'',
			card:'',
			phone:'',
			email:'',
			dept:'',
			role:''
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		getDriver : function(id) {
			$.get("../sys/department/selectForCmn", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				ztreeDriver = $.fn.zTree.init($("#driverTree"), settingDriver, r.deptList);
	            
			});
		},
		getDclass : function(id) {
			$.get("../basecode/selectByType/dclass", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				// 填充数据到页面
				$('#dclass').combobox({
					readonly: false,
				    data: r.data,
				    multiple:false,
				    valueField:'id',
				    textField:'cnm',
				    panelHeight: 'auto',
				    editable: false,
		   			onChange:function(newValue, oldValue){
		   		    }
				});
			})
		},
		add: function(){
			vm.showList = false;
			vm.upd = true;
			vm.title = "新增";
			vm.driver = {
				status:1,
				driverId:'',
				driverName:'',
				bdate:'',
				edate:'',
				card:'',
				phone:'',
				email:'',
				dept:'',
				role:''
			}
			vm.getDclass(); // 准驾车型
			vm.getDriver(); // 司机名
			$("#bdate").val('');
			$("#edate").val('');
		},
		update: function (id, isupdate) {
			if(id == null){
				return ;
			}
			vm.showList = false;
			vm.upd = isupdate;
            vm.title = isupdate ? "修改" : "查看";
			vm.getDriver(id);
			vm.getInfo(id);
		},
		saveOrUpdate: function (event) {
			if(validate.form()) {
				var url = vm.driver.id == null ? "../driver/save" : "../driver/update";
				var _json = $(".form-horizontal").form2json();
				_json.id = vm.driver.id;
				var dclassArray = new Array();
				$("input[name='dclass']").each(function(i){
					dclassArray.push(this.value);
				});
				_json.dclass = dclassArray.toString();
				console.log(JSON.stringify(_json));
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
		    	$.get("../driver/delete/"+id, function(r){
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
			   url: "../driver/info/"+id,
			   async: false,
			   success: function(r) {
				    var node = ztreeDriver.getNodeByParam("userid", r.driver.driverId );
					ztreeDriver.checkNode(node, true, true);
					r.driver.driverName = node.name;
					$('#dclass').combobox('setValue', r.driver.dclass);
				    vm.getDriverMsg(r.driver.driverId);
					vm.driver = r.driver;
			   }
			});
		},
		getDriverMsg: function(userid){
			// 获取司机
			$.get("../sys/user/info/"+userid, function(r){
				if(r.code == 0){
					var _driver = r.user;
					
					$("input[name='card']").val( _driver.cardno);
					$("input[name='phone']").val( _driver.mobile);
					$("input[name='email']").val( _driver.email);
					$("input[name='dept']").val(_driver.deptTextList);
					$("input[name='role']").val(_driver.roleTextList);
				}else{
					alert(r.msg);
				}
			});
		},
		driverTree : function() {
			layer.open({
				type : 1,
				offset : '50px',
				skin : 'layui-layer-molv',
				title : "选择司机",
				area : [ '300px', '450px' ],
				shade : 0,
				shadeClose : false,
				content : jQuery("#driverLayer"),
				btn : [ '确定', '取消' ],
				btn1 : function(index) {
					// 获取选择的菜单
					var nodes = ztreeDriver.getSelectedNodes();
					if(nodes.length>0){
						var node = nodes[0];
						if (node.userid) {
							vm.driver.driverId = node.userid.toString();
							vm.driver.driverName = node.name.toString();
							vm.getDriverMsg(node.userid);
							layer.close(index);
						} else {
							layer.msg('请选择一个人作为队长', function(){});
						}
					}
				}
			});
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'condition': vm.q.condition
					
				},
                page:page
            }).trigger("reloadGrid");
		}
	}
});
