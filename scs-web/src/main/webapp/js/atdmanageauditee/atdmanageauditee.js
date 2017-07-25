$(function () {
    $("#jqGrid").jqGrid({
        url: '../atdmanageauditee/list',
        datatype: "json",
        colModel: [			
			//隐藏主键
			{ label: '主键id', name: 'id', width: 50, key: true ,hidden : true },
			{ label: '职员', name: 'personName', width: 40 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
			{ label: '所属部门', name: 'deptName', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
			{ label: '业务板块', name: 'deptName', width: 50 , formatter: function(value, options, row){
				return "清扫保洁";
			}}, 
			{ label: '申报类型', name: 'atdtypeName', width: 50 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
			{ label: '起始时间', name: 'startTime', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
			{ label: '结束时间', name: 'endTime', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '时长', name: 'duration', width: 30 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
			{ label: '申请日期', name: 'crttm', width: 60 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
			// 状态
			{ label: '申报状态', name: 'status', width: 30, hidden: Util.consts.isadmin, formatter: function(value, options, row){
			return value == 9 ? 
					'<font color="pink">待审核</font>' : 
					value == 1 ? 
						'<font color="green">通过</font>' : 
						'<font color="red">未通过</font>' ;
			}},
			{ label: '操作', name: 'operation', width: 30, formatter: function(value, options, row){
					return  row.status != 9 ? "-" : "<a onClick=\"javascript:vm.auditing("+options.rowId+", false);\" title='审核' ><i class=\"fa fa-stack-overflow\" style='color:green;'></i></a>";
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
		q:{condition:''},
		showFirstGroupTime:false, // 是否显示第一组时间
		showSecondGroupTime: false, // 是否显示第二组时间
		isleave:false, // 是否显示请假的类型（事假、病假...）
		showList: true,
		upd: false,
		title: null,
		atdManageAuditee: {status:0,person:'',personName:'',duration:''}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		clear: function () {
			$("input[name='ftime']").val("");
			$("input[name='stime']").val("");
			$("input[name='ttime']").val("");
			$("input[name='fotime']").val("");
			$("input[name='duration']").val("");
			$("input[name='ltype']").prop("checked", false);
			
			//console.log($("input[name='ltype']"));
			
		},
		auditing: function (id, isupdate) {
			if(id == null){
				return ;
			}
			vm.showList = false;
			vm.upd = isupdate;
            vm.title = '审核';
            
            vm.getInfo(id);
            // 选人
            $.get("../sys/user/selectForZtree", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				ztreePerson = $.fn.zTree.init($("#personTree"), settingPerson, r.userList);
				var nodes = ztreePerson.transformToArray(ztreePerson.getNodes());
				nodes.forEach(function(e){
					if(e.id==vm.atdManageAuditee.person) {
						vm.atdManageAuditee.person = e.id.toString();
						vm.atdManageAuditee.personName = e.name.toString();
					}
				});
			});
            
            // 申报类型
            var _data = [{key:"请假",value:"1"},{key:"加班",value:"2"},{key:"调班",value:"3"},{key:"调休",value:"4"}];
            _data.forEach(function(e) {
				if (e.value == vm.atdManageAuditee.atdtype) {
					e.selected = true;
				}
			});
			$('#atdtype').combobox({
				readonly: true,
			    data: _data,
			    valueField:'value',
			    textField:'key',
			    panelHeight: 'auto',
			    editable: false,
	   			onChange:function(newValue, oldValue){
	   				vm.showFirstGroupTime = true;
	   				vm.clear();
	   				switch (newValue) {
	   				case '1':  // 请假
	   					vm.isleave = true;
	   					vm.showSecondGroupTime = false;
	   					$("#firstGroupTime").html("起止时间");
	   					break;
	   				case '2':  // 加班
	   					vm.isleave = false;
	   					vm.showSecondGroupTime = false;
	   					$("#firstGroupTime").text("起止时间");
	   					break;
	   				case '3': // 调班
	   					vm.isleave = false;
	   					vm.showSecondGroupTime = true;
	   					$("#firstGroupTime").text("出勤时段");
	   					$("#secondGroupTime").text("调整至");
	   					break;
	   				case '4': // 调休
	   					vm.isleave = false;
	   					vm.showSecondGroupTime = true;
	   					$("#firstGroupTime").text("加班时段");
	   					$("#secondGroupTime").text("抵扣出勤");
	   					break;
	   				}
	   		    }
			});
			// 如果是请假，处理一下那几个复选框
			if (vm.atdManageAuditee.atdtype == "1") {
				var ltype = vm.atdManageAuditee.ltype;
				if(ltype){
					var ltypes = ltype.split(",");
					ltypes.forEach(function (e) {
						$("#ltype-"+e).prop("checked", true);
					});
				}
			}
			// 处理那4个时间
			var date = new Date(vm.atdManageAuditee.ftime);
			$("input[name='ftime']").val(date.Format("yyyy-MM-dd hh:mm:ss"));
			date = new Date(vm.atdManageAuditee.stime);
			$("input[name='stime']").val(date.Format("yyyy-MM-dd hh:mm:ss"));
			if (vm.atdManageAuditee.atdtype > 2 ) {
				date = new Date(vm.atdManageAuditee.ttime);
				$("input[name='ttime']").val(date.Format("yyyy-MM-dd hh:mm:ss"));
				date = new Date(vm.atdManageAuditee.fotime);
				$("input[name='fotime']").val(date.Format("yyyy-MM-dd hh:mm:ss"));
			}
			// 时长
			$("input[name='duration']").val(vm.atdManageAuditee.duration);
            
//           审核是否通过
			var sdata = [{'text':'通过',value:'1'},{'text':'不通过',value:'0'}];
			if (vm.atdManageAuditee.status == 1) {
				sdata[0].selected = true;
			} else {
				sdata[1].selected = true;
			}
			$('#status').combobox({
				readonly: false,
			    data: sdata,
			    valueField:'value',
			    textField:'text',
			    panelHeight: 'auto',
			    editable: false,
	   			onChange:function(newValue, oldValue){
	   		    }
			});
		},
		doAuditing: function (event) {
			var url = "../atdmanageauditee/auditing";
			
			var _data = {};
			_data['id'] = vm.atdManageAuditee.id;
			_data['status'] = $("input[name='status']").val();
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(_data),
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
//		add: function(){
//			vm.showList = false;
//			vm.upd = true;
//			vm.title = "新增";
//			vm.atdManageAuditee = {status:1};
//		},
//		update: function (id, isupdate) {
//			if(id == null){
//				return ;
//			}
//			vm.showList = false;
//			vm.upd = isupdate;
//            vm.title = isupdate ? "修改" : "查看";
//            
//            vm.getInfo(id);
//		},
//		saveOrUpdate: function (event) {
//			var url = vm.atdManageAuditee.id == null ? "../atdmanageauditee/save" : "../atdmanageauditee/update";
//			$.ajax({
//				type: "POST",
//			    url: url,
//			    data: JSON.stringify(vm.atdManageAuditee),
//			    success: function(r){
//			    	if(r.code === 0){
//						alert('操作成功', function(index){
//							vm.reload();
//						});
//					}else{
//						alert(r.msg);
//					}
//				}
//			});
//		},
//		del: function (id) {
//			confirm('确定要删除选中的记录？', function(){
//		    	$.get("../atdmanageauditee/delete/"+id, function(r){
//					if(r.code == 0){
//						$("#jqGrid").trigger("reloadGrid");
//						top.layer.closeAll();
//					}else{
//						alert(r.msg);
//					}
//				});
//			});
//		},
		getInfo: function(id){
			$.ajax({
			   type: "GET",
			   url: "../atdmanageauditee/info/"+id,
			   async: false,
			   success: function(r) {
				   vm.atdManageAuditee = r.atdManageAuditee;
			   }
			});
		},
		personTree : function() {
			layer.open({
				type : 1,
				offset : '50px',
				skin : 'layui-layer-molv',
				title : "请选人",
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
							vm.atdManageAuditee.person = nodes[0].id.toString();
							vm.atdManageAuditee.personName = nodes[0].name.toString();
							layer.close(index);
						} else {
							alert('请选择一个人');
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
