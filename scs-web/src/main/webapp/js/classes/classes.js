$(function() {
	$("#jqGrid")
			.jqGrid(
					{
						url : '../classes/list',
						datatype : "json",
						colModel : [
								// 隐藏主键
								{
									label : 'id',
									name : 'id',
									width : 50,
									key : true,
									hidden : true
								},
								{
									label : '班次时段',
									name : 'classes',
									width : 80
								},
								{
									label : '开始时间',
									name : 'starttime',
									width : 80
								},
								{
									label : '结束时间',
									name : 'emdtime',
									width : 80,
								},
								{
									label : '创建人',
									name : 'crtid',
									width : 80
								},
								{
									label : '所属项目部',
									name : 'pjdept',
									width : 80
								},
								{
									label : '业务板块',
									name : 'business',
									width : 80
								},
								{
									label : '备注',
									name : 'remark',
									width : 80
								},
								{
									label : '操作',
									name : 'operation',
									width : 40,
									formatter : function(value, options, row) {
										var oper = "<a onClick=\"javascript:vm.update("
												+ options.rowId
												+ ", false);\" title='查看' ><i class=\"fa fa-info-circle\" style='color:violet;'></i></a>&nbsp;&nbsp;";
										oper += update ? "<a onClick=\"javascript:vm.update("
												+ options.rowId
												+ ", true);\" title='修改'  ><i class=\"fa fa-pencil-square-o\" style='color:green;'></i></a>&nbsp;&nbsp;"
												: "";
										oper += del ? "<a onClick=\"javascript:vm.del("
												+ options.rowId
												+ ");\" title='删除'  ><i class=\"fa fa-trash-o\" style='color:red;'></i></a>"
												: "";
										return oper;
									}
								}, ],
						viewrecords : true,
						height : 385,
						rowNum : 10,
						rowList : [ 10, 30, 50 ],
						rownumbers : false,
						rownumWidth : 25,
						autowidth : true,
						multiselect : false,
						pager : "#jqGridPager",
						jsonReader : {
							root : "page.list",
							page : "page.currPage",
							total : "page.totalPage",
							records : "page.totalCount"
						},
						prmNames : {
							page : "page",
							rows : "limit",
							order : "order"
						},
						gridComplete : function() {
							// 隐藏grid底部滚动条
							$("#jqGrid").closest(".ui-jqgrid-bdiv").css({
								"overflow-x" : "hidden"
							});
						}
					});
	// 表单验证
	validate = $(".form-horizontal").validate({
		onfocusout : false,
		onkeyup : false,
		rules : {
			busseg : {
				required : true
			},
			project : {
				required : true
			},
			classes : {
				required : true
			},
			starttime : {
				required : true
			},
			emdtime : {
				required : true
			},
			remark : {
				required : true,
				minlength:5,
				maxlength:50
			},
		},
		messages : {
			busseg : {
				required : "业务板块必填!"
			},
			project : {
				required : "所属项目部必选"
			},
			classes : {
				required : "班次名称必填!"
			},
			starttime : {
				required : "开始时间必选!"
			},
			emdtime : {
				required : "结束时间必选!"
			},
			remark : {
				required : "备注必填!",
			},
		},
		showErrors : function(errorMap, errorList) {
			if (errorList && errorList.length > 0) { // 如果存在错误信息
				layer.msg(errorList[0].message, function() {
				});
			}
		},
	});
    //获取业务板块
	$('#serviceType').combobox({
		readonly: true,
		disabled:true,
		data: Util.loadData("../basecode/selectByType/"+'biztype').data,
		valueField:'no',
		textField:'cnm',
		editable: false,
		value: 0,
		panelHeight: 'auto'
	});
    // 获取我当前项目部位置；
	var data = Util.loadData("../gridmng/getUserProjectMsg").data;
	$('#project').combobox({
		readonly: false,
		data: data,
		valueField:'id',
		textField:'name',
		value: data[0].id,
		editable: false,
		panelHeight: 'auto',
		onSelect:function(t){
			vm.classes.pjdept = t.id;
		}
	});
});

var vm = new Vue({
	el : '#rrapp',
	data : {
		q : {
			name : null
		},
		showList : true,
		upd : false,
		title : null,
		starttime : '',
		emdtime : '',
		classes : {
			starttime : '',
			emdtime : '',
			business:'',
			pjdept:''
		}
	},
	methods : {
		query : function() {
			vm.reload();
		},
		add : function() {
			$('#project').combobox('enable'); //新增
			vm.showList = false;
			vm.upd = true;
			vm.title = "新增";
			vm.classes = {
				starttime : '',
				emdtime : '',
			};
		},
		update : function(id, isupdate) {
			if (id == null) {
				return;
			}
			vm.showList = false;
			vm.upd = isupdate;
			vm.title = isupdate ? "修改" : "查看";
			if(isupdate){
				$('#project').combobox('enable'); //修改
			}else{
				$('#project').combobox('disable'); //查看
			}
			vm.getInfo(id);
		},
		saveOrUpdate : function(event) {
			var url = vm.classes.id == null ? "../classes/save" : "../classes/update";
			vm.classes.starttime = $("[name='starttime']").val();
			vm.classes.emdtime = $("[name='emdtime']").val();
			vm.classes.business = $('#serviceType').combobox('getValue');
			vm.classes.pjdept = $('#project').combobox('getValue');
			if (validate.form()) {
				$.ajax({
					type : "POST",
					url : url,
					data : JSON.stringify(vm.classes),
					success : function(r) {
						if (r.code === 0) {
							alert('操作成功', function(index) {
								vm.reload();
							});
						} else {
							alert(r.msg);
						}
					}
				});
			}
		},
		del : function(id) {
			confirm('确定要删除选中的记录？', function() {
				$.get("../classes/delete/" + id, function(r) {
					if (r.code == 0) {
						$("#jqGrid").trigger("reloadGrid");
						top.layer.closeAll();
					} else {
						alert(r.msg);
					}
				});
			});
		},
		getInfo : function(id) {
			$.get("../classes/info/" + id, function(r) {
				vm.classes = r.classes[0];
				$('#project').combobox('setText', vm.classes.pjdept);
			});
		},
		reload : function(event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				postData : {
					'name' : vm.q.name
				},
				page : page
			}).trigger("reloadGrid");
		}
	}
});
