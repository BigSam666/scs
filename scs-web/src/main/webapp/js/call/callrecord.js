$(function() {
	$("#jqGrid")
			.jqGrid(
					{
						url : '../call/list',
						datatype : "json",
						colModel : [
								// 隐藏主键
								{
									label : '序号',
									name : 'id',
									width : 50,
									key : true
								},
								{
									label : '通话时间',
									name : 'calldate',
									width : 80
								},
								{
									label : '呼叫类型',
									name : 'calltype',
									width : 80
								},
								{
									label : '手机号码',
									name : 'phone',
									width : 80
								},
								{
									label : '通话时长',
									name : 'arttime',
									width : 80
								},
								{
									label : '坐席编号',
									name : 'seatsid',
									width : 80
								},
								{
									label : '坐席姓名',
									name : 'seatsname',
									width : 80
								},
								{
									label : '通话详情',
									name : 'operation',
									width : 40,
									formatter : function(value, options, row) {
										var oper = '<a onClick="javascript:0;" title="查看" style="color:#33CC66;text-decoration:none;cursor:pointer">查看</a>';
										return oper;
									}
								},
								{
									label : '通话录音',
									name : 'calltapesurl',
									width : 80,
									formatter : function(value, options, row) {
										var oper = '<i class="fa fa-play-circle fa-lg" aria-hidden="true" style="cursor:pointer;color:#33CC66"></i>';
										return oper;
									}
								},
								{
									label : '操作',
									name : 'operation',
									width : 40,
									formatter : function(value, options, row) {
										var oper = '<button type="button" class="btn btn-block btn-danger btn-xs">拨打电话</button>';
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
});

var vm = new Vue({
	el : '#rrapp',
	data : {
		q : {
			name : null,
			pnm : null,
			pid : null
		},
		showList : true,
		upd : false,
		title : null,
		callRecord : {
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
			vm.callRecord = {
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
			var url = vm.callRecord.id == null ? "../call/save"
					: "../call/update";
			$.ajax({
				type : "POST",
				url : url,
				data : JSON.stringify(vm.callRecord),
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
				$.get("../call/delete/" + id, function(r) {
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
			$.get("../call/info/" + id, function(r) {
				vm.callRecord = r.callRecord;
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
