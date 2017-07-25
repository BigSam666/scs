$(function () {
    $("#jqGrid").jqGrid({
        url: '../atdmanage/list',
        datatype: "json",
        colModel: [			
			// 隐藏主键
			{ label: '主键id', name: 'id', width: 50, key: true ,hidden : true },
			{ label: '职员', name: 'personName', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '所属部门', name: 'deptName', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
			{ label: '业务板块', name: 'deptName', width: 80 , formatter: function(value, options, row){
				return "清扫保洁";
			}}, 
			{ label: '申报类型', name: 'atdtypeName', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
			{ label: '起始时间', name: 'startTime', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
			{ label: '结束时间', name: 'endTime', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '时长', name: 'duration', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
			{ label: '申请日期', name: 'crttm', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
			// 状态
			{ label: '申报状态', name: 'status', width: 50, hidden: Util.consts.isadmin, formatter: function(value, options, row){
			return value == 9 ? 
					'<font color="pink">待审核</font>' : 
					value == 1 ? 
						'<font color="green">通过</font>' : 
						'<font color="red">未通过</font>' ;
			}},
			{ label: '操作', name: 'operation', width: 40, formatter: function(value, options, row){
				var oper = "<a onClick=\"javascript:vm.update("+options.rowId+", false);\" title='查看' ><i class=\"fa fa-info-circle\" style='color:violet;'></i></a>";
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
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
    $("input[name='ftime']").change( function() {
    	vm.calc();
    });
    $("input[name='stime']").change( function() {
    	vm.calc();
    });
    $("input[name='ttime']").change( function() {
    });
    $("input[name='fotime']").change( function() {
    });
 // 表单验证
    validate = $(".form-horizontal").validate({
  	   onfocusout: false,
 	   onkeyup: false,
 	   rules: {
 		  person: {
 			   required: true
 		   },
 		  atdtype: {
 			   required: true
 		   },
 		   ftime: {
 			   required: true
 		   },
 		   stime: {
 			   required: true
 		   }
 	   },
 	   messages: {
 		  person: {
			   required: "必填，请选人"
		   },
		  atdtype: {
			   required: "必填，请选择申报类型"
		   },
 		   ftime: {
 			   required: "必填，请选择时间"
 		   },
 		   stime: {
 			   required: "必填，请选择时间"
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
		q:{condition:''},
		showFirstGroupTime:false, // 是否显示第一组时间
		showSecondGroupTime: false, // 是否显示第二组时间
		isleave:false, // 是否显示请假的类型（事假、病假...）
		showList: true,
		upd: false,
		title: null,
		atdtype: null,
		atdManage: {status:0,person:'',personName:'',duration:'',atdtype:''}
	},
	methods: {
		clear: function () {
			$("input[name='ftime']").val("");
			$("input[name='stime']").val("");
			$("input[name='ttime']").val("");
			$("input[name='fotime']").val("");
			$("input[name='duration']").val("");
			$("input[name='ltype']").prop("checked", false);
		},
		calc: function () {
			var date1 = new Date($("input[name='ftime']").val()).getTime();
	    	var date2 = new Date($("input[name='stime']").val()).getTime();
	    	if(isNaN(date1) || isNaN(date2)) {
	    		return ;
	    	}
	    	var time = Math.round((date2 - date1) / (1000 * 60 * 60));
	    	
	    	if (time < 0){
	    		$("input[name='duration']").val("后面的时间不能比前面的时间早");
	    		return;
	    	}
	    	var ret = "";
	    	if (parseInt(time/24) > 0) {
	    		ret += parseInt(time/24) + '天';
	    	}
	    	if (parseInt(time%24) > 0) {
	    		ret += parseInt(time%24) + '小时';
	    	}
	    	if (ret == "") {
	    		ret = "小于1小时";
	    	}
	    	$("input[name='duration']").val(ret);
			vm.showSecondGroupTime = !vm.showSecondGroupTime;
	    	vm.atdManage.rmk = vm.atdtype + ":" + ret;
			vm.showSecondGroupTime = !vm.showSecondGroupTime;
		},
		query: function () {
			vm.reload();
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
		getAtdtype: function () {
			var _data = [{key:"请假",value:"1"},{key:"加班",value:"2"},{key:"调班",value:"3"},{key:"调休",value:"4"}];
			$('#atdtype').combobox({
				readonly: false,
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
	   					$("#firstGroupTime").text("起止时间");
	   					vm.atdtype = "请假";
	   					break;
	   				case '2':  // 加班
	   					vm.isleave = false;
	   					vm.showSecondGroupTime = false;
	   					$("#firstGroupTime").text("起止时间");
	   					vm.atdtype = "加班";
	   					break;
	   				case '3': // 调班
	   					vm.isleave = false;
	   					vm.showSecondGroupTime = false;
	   					$("#firstGroupTime").text("出勤时段");
	   					$("#secondGroupTime").text("调整至");
	   					vm.atdtype = "调班";
	   					vm.showSecondGroupTime = true;
	   					break;
	   				case '4': // 调休
	   					vm.isleave = false;
	   					vm.showSecondGroupTime = false;
	   					$("#firstGroupTime").text("加班时段");
	   					$("#secondGroupTime").text("抵扣出勤");
	   					vm.atdtype = "调休";
	   					vm.showSecondGroupTime = true;
	   					break;
	   				}
	   				vm.atdManage.rmk = vm.atdtype;
	   		    }
			});
		},
		add: function(){
			vm.showList = false;
			vm.upd = true;
			vm.title = "新增";
			vm.atdManage = {status:0,person:'',personName:''};
			
			vm.clear();
			vm.showFirstGroupTime = false;
			vm.showSecondGroupTime = false;
			
			vm.getPerson(); // 职员
			vm.getAtdtype(); // 申报类型
		},
		update: function (id, isupdate) {
			if(id == null){
				return ;
			}
			vm.showList = false;
			vm.upd = isupdate;
            vm.title = isupdate ? "修改" : "查看";
            
            vm.getInfo(id);
            
            // 选人
            $.get("../sys/department/selectForCmn", function(r) {
                if (r.code != 0) {
                    alert(r.msg);
                    vm.reload();
                }
                ztreePerson = $.fn.zTree.init($("#personTree"), settingPerson, r.deptList);
                var nodes = ztreePerson.transformToArray(ztreePerson.getNodes());
                nodes.forEach(function(e){
                    if(e.id==vm.atdManage.person) {
                        vm.atdManage.person = e.id.toString();
                        vm.atdManage.personName = e.name.toString();
                    }
                });

            });
            
            // 申报类型
            var _data = [{key:"请假",value:"1"},{key:"加班",value:"2"},{key:"调班",value:"3"},{key:"调休",value:"4"}];
            _data.forEach(function(e) {
				if (e.value == vm.atdManage.atdtype) {
					e.selected = true;
				}
			});
			$('#atdtype').combobox({
				readonly: false,
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
	   					$("#firstGroupTime").text("起止时间");
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
			if (vm.atdManage.atdtype == "1") {
				var ltype = vm.atdManage.ltype;
				var ltypes = ltype.split(",");
				ltypes.forEach(function (e) {
					$("#ltype-"+e).prop("checked", true);
				});
			}
			// 处理那4个时间
			var date = new Date(vm.atdManage.ftime);
			$("input[name='ftime']").val(date.Format("yyyy-MM-dd hh:mm:ss"));
			date = new Date(vm.atdManage.stime);
			$("input[name='stime']").val(date.Format("yyyy-MM-dd hh:mm:ss"));
			if (vm.atdManage.atdtype > 2 ) {
				date = new Date(vm.atdManage.ttime);
				$("input[name='ttime']").val(date.Format("yyyy-MM-dd hh:mm:ss"));
				date = new Date(vm.atdManage.fotime);
				$("input[name='fotime']").val(date.Format("yyyy-MM-dd hh:mm:ss"));
			}
			// 时长
			$("input[name='duration']").val(vm.atdManage.duration);
		},
		saveOrUpdate: function (event) {
			if(validate.form()) {
				// 手动校验后面的两个时间
				var atdtype = $("input[name='atdtype']").val();
				if (parseInt > 2) {
					var ttime = $("input[name='ttime']").val();
					var fotime = $("input[name='fotime']").val();
					if (ttime == '' || fotime == '') {
						layer.msg("必填，请选择时间");
					}
				}
				
				var url = vm.atdManage.id == null ? "../atdmanage/save" : "../atdmanage/update";
				var _json = $(".form-horizontal").form2json();
				_json.id = vm.atdManage.id;
				_json.status = '0';
				if (_json.ltype) {
					_json.ltype = _json.ltype.toString();
				}
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
							alert(r.code + ":" + r.msg);
						}
					}
				});
			}
		},
		del: function (id) {
			confirm('确定要删除选中的记录？', function(){
		    	$.get("../atdmanage/delete/"+id, function(r){
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
			   url: "../atdmanage/info/"+id,
			   async: false,
			   success: function(r) {
				   vm.atdManage = r.atdManage;
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
                        if (node.userid) {
							vm.atdManage.person = node.userid.toString();
							vm.atdManage.personName = node.name.toString();
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
