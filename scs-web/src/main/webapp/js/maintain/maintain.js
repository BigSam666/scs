$(function () {
    $("#jqGrid").jqGrid({
        url: '../maintain/list',
        datatype: "json",
        colModel: [			
			// 隐藏主键
			{ label: '主键id', name: 'id', width: 50, key: true ,hidden : true },
			{ label: '车牌号', name: 'cnoText', width: 80 }, 
				{ label: '保养日期', name: 'mtm', width: 80, formatter:'date' }, 
				{ label: '保养里程', name: 'mile', width: 80 }, 
				{ label: '保养人员', name: 'personName', width: 80 }, 
				{ label: '保养费用', name: 'cost', width: 80 }, 
				{ label: '保养地点', name: 'addr', width: 80 }, 
				{ label: '保养门店', name: 'store', width: 80 }, 
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
 		  mtm: {
 			   required: true
 		   },
 		  ntm: {
			   required: true
		   },
 		  mile: {
 			   required: true
 		   },
 		  mtdetail: {
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
 		  mtm: {
 			   required: "必填，请输入保养日期"
 		   },
 		  ntm: {
			   required: "必填，请输入预计下次保养"
		   },
 		  mile: {
 			   required: "必填，请输入保养里程"
 		   },
 		  mtdetail: {
			   required: "必填，请输入保养明细"
		   },
 		  person: {
 			   required: "必填，请输入保养人员"
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
		maintain: {status:1,person:'',personName:'',mtdetail:'',mtdetailText:'',mtdetailJson:null}
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
			vm.maintain = {status:1,person:'',personName:'',mtdetail:'',mtdetailText:'',mtdetailJson:null};
			
			vm.getCno(); // 车牌号
			vm.getPerson(); // 保养人员
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
					if (e.id == vm.maintain.cno) {
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
            // 保养人员
            $.get("../sys/department/selectForCmn", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				ztreePerson = $.fn.zTree.init($("#personTree"), settingPerson, r.deptList);
				var nodes = ztreePerson.transformToArray(ztreePerson.getNodes());
				nodes.forEach(function(e){
					if(e.userid==vm.maintain.person) {
						vm.maintain.person = e.userid.toString();
						vm.maintain.personName = e.name.toString();
					}
				});
			});
            // 保养日期
            var date = new Date(vm.maintain.mtm);
            $("#mtm").val(date.Format("yyyy-MM-dd"));
            // 预计下次日期
            var date = new Date(vm.maintain.ntm);
            $("#ntm").val(date.Format("yyyy-MM-dd"));
            // 保养明细
            $.get("../mtdetail/listByMtid/" + id, function(r) {
            	if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
            	var _data = r.data;
            	var key = '';
            	var value= '';
            	var _jsonObj = {};
            	var _mtdetail = "";
            	_data.forEach(function(e){
            		key = e.itemtype + "_" + e.itemcode;
            		value = e.money;
            		_jsonObj[key] = value;
            		_mtdetail+="#" + key + "=" + value;
            	});
            	vm.maintain.mtdetailJson = _jsonObj;
            	vm.maintain.mtdetail = _mtdetail;
            	debugger
            });
		},
		saveOrUpdate: function (event) {
			if(validate.form()){
				var url = vm.maintain.id == null ? "../maintain/save" : "../maintain/update";
				var _json = $(".form-horizontal").form2json();
				_json.id = vm.maintain.id;
				_json.mtdetail = vm.maintain.mtdetail.replace(/&/g,"#");
				debugger
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
		    	$.get("../maintain/delete/"+id, function(r){
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
			   url: "../maintain/info/"+id,
			   async: false,
			   success: function(r) {
				   vm.maintain = r.maintain;
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
							vm.maintain.person = nodes[0].userid.toString();
							vm.maintain.personName = nodes[0].name.toString();
							layer.close(index);
						} else {
							alert('请选择保养人员');
						}
					}
				}
			});
		},
		mtdetail: function() {
		   $(".table1").nextAll().remove();
		   $(".table2").nextAll().remove();
			// 获取保养明细
			// 小保养
			$.ajax({
			   type: "GET",
			   url: "../basecode/selectByType/smaintain",
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
			// 大保养
			$.ajax({
				type: "GET",
				url: "../basecode/selectByType/bmaintain",
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
			
			// 填充数据，即如果用户已经向投保明细中填写了数据，当再次打开投保明细时，应该可以看到刚才填写的数据
			if(vm.maintain.mtdetailJson != null) {
				$.each($("#mtdetail-form input"), function(i, n){
					if(n.name in vm.maintain.mtdetailJson){
						$(n).val(vm.maintain.mtdetailJson[n.name]);
					}
				});
			}
			
			layer.open({
				type : 1,
				offset : '50px',
				skin : 'layui-layer-molv',
				title : "保养明细",
				area : [ '500px', '450px' ],
				shade : 0,
				shadeClose : false,
				content : jQuery("#mtdetailLayer"),
				btn : [ '确定', '取消' ],
				btn1 : function(index) {
					vm.maintain.mtdetail = $("#mtdetail-form").serialize();
					var _json = $("#mtdetail-form").form2json();
					vm.maintain.mtdetailJson = _json;
					var sum = 0;
					for(key in _json){
						sum+=parseFloat(_json[key]);
					}
					vm.maintain.cost = sum;
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
