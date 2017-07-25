$(function () {
    $("#jqGrid").jqGrid({
        url: '../gas/list',
        datatype: "json",
        colModel: [			
			// 隐藏主键
			{ label: '主键id', name: 'id', width: 50, key: true ,hidden : true },
			{ label: '车牌号', name: 'cnoText', width: 80 }, 
				{ label: '加油日期', name: 'gastm', width: 80, formatter:'date' }, 
				{ label: '加油单价', name: 'price', width: 80 }, 
				{ label: '加油人员', name: 'personName', width: 80 }, 
				{ label: '加油费用', name: 'cost', width: 80 }, 
				{ label: '加油地点', name: 'addr', width: 80 }, 
				{ label: '加油单位', name: 'unitName', width: 80 }, 
				{ label: '加油类型', name: 'typeName', width: 80 }, 
//				{ label: '起里程数', name: 'bmile', width: 80 }, 
//				{ label: '止里程数', name: 'emile', width: 80 }, 
		// 状态
//			{ label: '状态', name: 'status', width: 50, hidden: Util.consts.isadmin, formatter: function(value, options, row){
//			return value == 0 ? 
//				'<span class="label label-danger">无效</span>' : 
//				'<span class="label label-success">有效</span>';
//			}},
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
 		  cno: {
 			   required: true
 		   },
 		  gastm: {
 			   required: true
 		   },
 		  person: {
 			   required: true
 		   },
 		  price: {
 			   required: true
 		   },
 		  unit: {
 			   required: true
 		   },
 		  type: {
			   required: true
		   },
 		  num: {
			   required: true
		   },
 		  addr: {
 			   required: true
 		   },
 		  bmile: {
 			   required: true
 		   },
 		  emile: {
 			   required: true
 		   }
 	   },
 	   messages: {
 		  cno: {
 		       required: "必填，请输入车牌号"
 		   },
 		  gastm: {
			   required: "必填，请输入加油日期"
		   },
		   person: {
			   required: "必填，请输入加油人员"
		   },
		   price: {
			   required: "必填，请输入加油单价"
		   },
		   unit: {
			   required: "必填，请输入加油单位"
		   },
		   type: {
			   required: "必填，请输入加油类型"
		   },
 		   num: {
			   required: "必填，请输入数量"
		   },
		   addr: {
			   required: "必填，请输入加油地点"
		   },
		   bmile: {
			   required: "必填，请输入起里程数"
		   },
		   emile: {
			   required: "必填，请输入止里程数"
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
		q:{condition:'',
			starttime:'',
			endtime:''},
		showList: true,
		upd: false,
		title: null,
		gas: {status:1,person:'',personName:'',price:null,num:null,cost:null}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		getCno : function(id) {
			$.get("../car/listAll", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				// 填充数据到页面
				$('#cno').combobox({
					readonly: false,
				    data: r.data,
				    valueField:'id',
				    textField:'cno',
				    panelHeight: 'auto',
				    editable: false,
		   			onChange:function(newValue, oldValue){
		   		    }
				});
			})
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
		getUnit : function(id) {
			$.get("../basecode/selectByType/gasunit", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				// 填充数据到页面
				$('#unit').combobox({
					readonly: false,
				    data: r.data,
				    valueField:'id',
				    textField:'cnm',
				    panelHeight: 'auto',
				    editable: false,
		   			onChange:function(newValue, oldValue){
		   		    }
				});
			})
		},
		getType : function(id) {
			$.get("../basecode/selectByType/gastype", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				var jsonArray = new Array();
				
				var _data = r.data;
				_data.forEach(function(e) {
					var obj = {};
					var cnm = e.cnm.split("-");  // 汽油-国V_98# ->  [汽油,国V_98#]
					obj.value = e.id;
					obj.text = cnm[1]; // 国V_98#
					obj.group = cnm[0]; // 汽油
					jsonArray.push(obj);
				});
				// 填充数据到页面
				$('#type').combobox({
					readonly: false,
					data: jsonArray,
					valueField:'value',
					textField:'text',
					groupField:'group',
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
			vm.gas = {status:1,person:'',personName:'',price:null,num:null,cost:null}
			
			vm.getCno(); // 车牌号
			vm.getPerson(); // 办理人员
			vm.getUnit(); // 加油单位
			vm.getType(); // 加油类型
		},
		update: function (id, isupdate) {
			if(id == null){
				return ;
			}
			vm.showList = false;
			vm.upd = isupdate;
            vm.title = isupdate ? "修改" : "查看";
            
            vm.getInfo(id);
            // 车牌号
            $.get("../car/listAll", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				var _data = r.data;
				_data.forEach(function (e){
					if (e.id == vm.gas.cno) {
						e.selected = true;
					}
				});
				$('#cno').combobox({
					readonly: false,
				    data: r.data,
				    valueField:'id',
				    textField:'cno',
				    panelHeight: 'auto',
				    editable: false,
		   			onChange:function(newValue, oldValue){
		   		    }
				});
			});
            // 加油单位
			$.get("../basecode/selectByType/gasunit", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				var _data = r.data;
				_data.forEach(function (e){
					if (e.id == vm.gas.unit) {
						e.selected = true;
					}
				});
				$('#unit').combobox({
					readonly: false,
				    data: r.data,
				    valueField:'id',
				    textField:'cnm',
				    panelHeight: 'auto',
				    editable: false,
		   			onChange:function(newValue, oldValue){
		   		    }
				});
			});
			// 办理人员
            $.get("../sys/department/selectForCmn", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				ztreePerson = $.fn.zTree.init($("#personTree"), settingPerson, r.deptList);
				var nodes = ztreePerson.transformToArray(ztreePerson.getNodes());
				nodes.forEach(function(e){
					if(e.userid==vm.gas.person) {
						vm.gas.person = e.userid.toString();
						vm.gas.personName = e.name.toString();
					}
				});
			});
            // 加油日期
            var date = new Date(vm.gas.gastm);
            $("#gastm").val(date.Format("yyyy-MM-dd"));
            // 加油类型
            $.get("../basecode/selectByType/gastype", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				var jsonArray = new Array();
				var _data = r.data;
				_data.forEach(function(e) {
					var obj = {};
					var cnm = e.cnm.split("-");  // 汽油-国V_98# ->  [汽油,国V_98#]
					obj.value = e.id;
					obj.text = cnm[1]; // 国V_98#
					obj.group = cnm[0]; // 汽油
					if (obj.value == vm.gas.type) {
						obj.selected = true;
					}
					jsonArray.push(obj);
				});
				// 填充数据到页面
				$('#type').combobox({
					readonly: false,
					data: jsonArray,
					valueField:'value',
					textField:'text',
					groupField:'group',
					panelHeight: 'auto',
					editable: false,
					onChange:function(newValue, oldValue){
					}
				});
			})
		},
		saveOrUpdate: function (event) {
			if(validate.form()) {
				var url = vm.gas.id == null ? "../gas/save" : "../gas/update";
				var _json = $(".form-horizontal").form2json();
				_json.id = vm.gas.id;
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
		    	$.get("../gas/delete/"+id, function(r){
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
			   url: "../gas/info/"+id,
			   async: false,
			   success: function(r) {
				   vm.gas = r.gas;
			   }
			});
		},
		personTree : function() {
			layer.open({
				type : 1,
				offset : '50px',
				skin : 'layui-layer-molv',
				title : "选择办理人员",
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
							vm.gas.person = nodes[0].userid.toString();
							vm.gas.personName = nodes[0].name.toString();
							layer.close(index);
						} else {
							alert('请选择一个人作为办理人员');
						}
					}
				}
			});
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'condition': vm.q.condition,
					'starttime':$("#starttime").val(),
					'endtime':$("#endtime").val()
				},
                page:page
            }).trigger("reloadGrid");
		}
	}
});

vm.$watch('gas.price', function (val) {
	this.gas.cost = val * this.gas.num;
})

vm.$watch('gas.num', function (val) {
	this.gas.cost = val * this.gas.price;
})