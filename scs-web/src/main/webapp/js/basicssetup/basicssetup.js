$(function() {
	$("#jqGrid")
			.jqGrid(
					{
						url : '../basicssetup/list',
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
									label : '设置项',
									name : 'setup',
									width : 80,
									formatter : function(value, options, row) {
										return $.isPlainObject(value) ? value.value
												: value;
									}
								},
								{
									label : '滞留时间',
									name : 'zdate',
									width : 80,
									formatter : function(value, options, row) {
										return $.isPlainObject(value) ? value.value
												: value;
									}
								},
								{
									label : '滞留范围',
									name : 'zrange',
									width : 80,
									formatter : function(value, options, row) {
										return $.isPlainObject(value) ? value.value
												: value;
									}
								},
								{
									label : '越界范围',
									name : 'yrange',
									width : 80,
									formatter : function(value, options, row) {
										return $.isPlainObject(value) ? value.value
												: value;
									}
								},
								{
									label : '超速公里',
									name : 'sdkm',
									width : 80,
									formatter : function(value, options, row) {
										return $.isPlainObject(value) ? value.value
												: value;
									}
								},
								// 状态
								{
									label : '状态',
									name : 'status',
									width : 50,
									hidden : Util.consts.isadmin,
									formatter : function(value, options, row) {
										return value == 0 ? '<span class="label label-danger">无效</span>'
												: '<span class="label label-success">有效</span>';
									}
								},
								{
									label : '创建时间',
									name : 'crttm',
									width : 80,
									formatter : function(value, options, row) {
										return $.isPlainObject(value) ? value.value
												: value;
									}
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
							$("#jqGrid").closest(".ui-jqgrid-bdiv").css({
								"overflow-x" : "hidden"
							});
						}
					});
});

var vm = new Vue({
	el : '#rrapp',
	data : {
		showList : true,
		upd : false,
		title : null,
		basicssetup : {
			status : 1,
		}
	},
	methods : {
		query : function() {
			vm.reload();
		},
		add : function() {
			vm.showList = false;
			vm.upd = true;
			vm.title = "新增";
			vm.basicssetup = {
				status : 1
			};
		},
		update : function(id, isupdate) {
			if (id == null) {
				return;
			}
			vm.showList = false;
			vm.upd = isupdate;
			vm.title = isupdate ? "修改" : "查看";

			vm.getInfo(id);
		},
		saveOrUpdate : function(event) {
			var url = vm.basicssetup.id == null ? "../basicssetup/save"
					: "../basicssetup/update";
			$.ajax({
				type : "POST",
				url : url,
				data : JSON.stringify(vm.basicssetup),
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
		},
		del : function(id) {
			confirm('确定要删除选中的记录？', function() {
				$.get("../basicssetup/delete/" + id, function(r) {
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
			$.get("../basicssetup/info/" + id, function(r) {
				vm.basicssetup = r.basicssetup;
			});
		},
		reload : function(event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				page : page
			}).trigger("reloadGrid");
		}
	}
});
