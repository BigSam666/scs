$(function () {
    $("#jqGrid").jqGrid({
        url: '../repair/list',
        datatype: "json",
        colModel: [			
			// 隐藏主键
			{ label: '主键id', name: 'id', width: 50, key: true ,hidden : true },
			{ label: '车牌号', name: 'cnoText', width: 80 }, 
				{ label: '维修日期', name: 'rtm', width: 80, formatter:'date' }, 
				{ label: '维修内容', name: 'content', width: 80 }, 
				{ label: '维修人员', name: 'personName', width: 80 }, 
				{ label: '维修费用', name: 'cost', width: 80 }, 
				{ label: '维修地点', name: 'addr', width: 80 }, 
				{ label: '维修门店', name: 'store', width: 80 }, 
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
 		  rtm: {
 			   required: true
 		   },
 		  redetail: {
			   required: true
		   },
 		  person: {
 			   required: true
 		   }
 	   },
 	   messages: {
 		  cno: {
 		       required: "必填，请输入车牌号"
 		   },
 		  rtm: {
 			   required: "必填，请输入维修日期"
 		   },
 		  redetail: {
			   required: "必填，请输入维修明细"
		   },
 		  person: {
 			   required: "必填，请输入维修人员"
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
		repair: {status:1,person:'',personName:'',redetail:'',redetailText:'',redetailJson:null}
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
		add: function(){
			vm.showList = false;
			vm.upd = true;
			vm.title = "新增";
			vm.repair = {status:1,person:'',personName:'',redetail:'',redetailText:'',redetailJson:null};
			
			vm.getCno(); // 车牌号
			vm.getPerson(); // 维修人员
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
					if (e.id == vm.repair.cno) {
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
            // 维修人员
            $.get("../sys/department/selectForCmn", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				ztreePerson = $.fn.zTree.init($("#personTree"), settingPerson, r.deptList);
				var nodes = ztreePerson.transformToArray(ztreePerson.getNodes());
				nodes.forEach(function(e){
					if(e.userid==vm.repair.person) {
						vm.repair.person = e.userid.toString();
						vm.repair.personName = e.name.toString();
					}
				});
			});
            // 维修日期
            var date = new Date(vm.repair.rtm);
            $("#rtm").val(date.Format("yyyy-MM-dd"));
            // 维修明细
            $.get("../redetail/listByReid/" + id, function(r) {
            	if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
            	var _data = r.data;
            	var key = '';
            	var value= '';
            	var _jsonObj = {};
            	var _redetail = "";
            	_data.forEach(function(e){
            		key = e.itemtype + "_" + e.itemcode;
            		value = e.money;
            		_jsonObj[key] = value;
            		_redetail+="#" + key + "=" + value;
            	});
            	vm.repair.redetailJson = _jsonObj;
            	vm.repair.redetail = _redetail;
            });
		},
		saveOrUpdate: function (event) {
			if(validate.form()){
				var url = vm.repair.id == null ? "../repair/save" : "../repair/update";
				var _json = $(".form-horizontal").form2json();
				_json.id = vm.repair.id;
				_json.redetail = vm.repair.redetail.replace(/&/g,"#");
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
		    	$.get("../repair/delete/"+id, function(r){
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
			   url: "../repair/info/"+id,
			   async: false,
			   success: function(r) {
				   vm.repair = r.repair;
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
							vm.repair.person = nodes[0].userid.toString();
							vm.repair.personName = nodes[0].name.toString();
							layer.close(index);
						} else {
							alert('请选择维修人员');
						}
					}
				}
			});
		},
		redetail: function() {
		   $(".table1").nextAll().remove();
		   $(".table2").nextAll().remove();
			// 获取维修明细
			// 维修明细
			$.ajax({
			   type: "GET",
			   url: "../basecode/selectByType/crepaire",
			   async: false,
			   success: function(r) {
				   table1 = "";
				   if (r.code != 0) {
						alert(r.msg);
						vm.reload();
				   }
				   r.data.forEach(function (e) {
					   table1 += '<tr><td class=\"tname\">'+ e.cnm +'</td>'+
						'<td class=\"tvalue\"><input type="text" name="'+ e.type +'_'+ e.no +'" />元</td></tr>';
				   });
				   $(".table1").after(table1);
			   }
			});
			// 常见维修细分
			$.ajax({
				type: "GET",
				url: "../basecode/selectByType/srepaire",
				async: false,
				success: function(r) {
					table2 = "";
					if (r.code != 0) {
						alert(r.msg);
						vm.reload();
					}
					r.data.forEach(function (e) {
						table2 += '<tr><td class=\"tname\">'+ e.cnm +'</td>'+
								'<td class=\"tvalue\"><input type="text" name="'+ e.type +'_'+ e.no +'" />元</td></tr>';
					});
					   $(".table2").after(table2);
				}
			});
			// 填充数据，即如果用户已经向维修明细中填写了数据，当再次打开维修明细时，应该可以看到刚才填写的数据
			if(vm.repair.redetailJson != null) {
				$.each($("#redetail-form input"), function(i, n){
					if(n.name in vm.repair.redetailJson){
						$(n).val(vm.repair.redetailJson[n.name]);
					}
				});
			}
			
			layer.open({
				type : 1,
				offset : '50px',
				skin : 'layui-layer-molv',
				title : "投保明细",
				area : [ '500px', '450px' ],
				shade : 0,
				shadeClose : false,
				content : jQuery("#redetailLayer"),
				btn : [ '确定', '取消' ],
				btn1 : function(index) {
					vm.repair.redetail = $("#redetail-form").serialize();
					var _json = $("#redetail-form").form2json();
					vm.repair.redetailJson = _json;
					var sum = 0;
					for(key in _json){
						sum+=parseFloat(_json[key]);
					}
					vm.repair.cost = sum;
					layer.close(index);
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
