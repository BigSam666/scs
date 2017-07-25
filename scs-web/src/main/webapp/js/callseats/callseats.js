$(function() {
	$("#jqGrid")
			.jqGrid(
					{
						url : '../callseats/list',
						datatype : "json",
						colModel : [
								// 隐藏主键
								{
									label : '主键id',
									name : 'id',
									width : 50,
									key : true,
									hidden : true
								},
								{
									label : '姓名',
									name : 'name',
									width : 80
								},
								{
									label : '分机号码',
									name : 'fphone',
									width : 80
								},
								{
									label : '性别',
									name : 'sex',
									width : 80
								},
								{
									label : '所属项目',
									name : 'xmper',
									width : 80
								},
								{
									label : '坐席编码(自动生成)',
									name : 'zxno',
									width : 80
								},
								{
									label : '部门',
									name : 'depa',
									width : 80
								},
								{
									label : '手机号码',
									name : 'phone',
									width : 80
								},
								{
									label : '身份证号码',
									name : 'sid',
									width : 80
								},
								{
									label : '出生日期',
									name : 'cdate',
									width : 80
								},
								{
									label : '居住地址',
									name : 'jaddes',
									width : 80
								},
								{
									label : '户籍地址',
									name : 'haddes',
									width : 80
								},
								{
									label : '人员类型',
									name : 'rtype',
									width : 80
								},
								{
									label : '备注',
									name : 'rem',
									width : 80
								},
								{
									label : '创建时间',
									name : 'crttm',
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
		callSeats : {
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
			vm.callSeats = {
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
			var url = vm.callSeats.id == null ? "../callseats/save"
					: "../callseats/update";
			$.ajax({
				type : "POST",
				url : url,
				data : JSON.stringify(vm.callSeats),
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
				$.get("../callseats/delete/" + id, function(r) {
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
			$.get("../callseats/info/" + id, function(r) {
				vm.callSeats = r.callSeats;
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
