$(function() {
	$("#jqGrid")
			.jqGrid(
					{
						url : '../calljobsel/list',
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
									label : '来源',
									name : 'source',
									width : 80,
									hidden : true
								},
								{
									label : '姓名',
									name : 'name',
									width : 80,
									hidden : true
								},
								{
									label : '电话号码',
									name : 'phone',
									width : 80,
									hidden : true
								},
								{
									label : '最近的地点',
									name : 'adds',
									width : 80,
									hidden : true
								},
								{
									label : '图片',
									name : 'img',
									width : 80,
									formatter:function (value, options, row){
										var itmes,img = '';
										if(value != null){
											itmes = value.split(',');
											for(itm in itmes){
												img +=  value ? "<img src='"+Util.consts.downloadPath+'/'+itmes[itm]+'/v4/w/30/h/30'+"' onclick='vm.imglook(\""+itmes[itm]+"\")'/>&nbsp;&nbsp;" : '无';
											}
											return img;
										}else{
											return '无';
										}
									}
									
								},
								{
									label : '事件内容',
									name : 'content',
									width : 80
								},
								{
									label : '时间',
									name : 'date',
									width : 80
								},
								{
									label : '爆料类型',
									name : 'type',
									width : 80
								},
								{
									label : '创建时间',
									name : 'crttm',
									width : 80,
									hidden : true
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
		callJobsel : {
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
			vm.callJobsel = {
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
			var url = vm.callJobsel.id == null ? "../calljobsel/save"
					: "../calljobsel/update";
			$.ajax({
				type : "POST",
				url : url,
				data : JSON.stringify(vm.callJobsel),
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
				$.get("../calljobsel/delete/" + id, function(r) {
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
			$.get("../calljobsel/info/" + id, function(r) {
				vm.callJobsel = r.callJobsel;
			});
		},
		reload : function(event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				page : page
			}).trigger("reloadGrid");
		},
		imglook : function(event){
			layer.open({
				  type: 1,
				  title:'图片',
				  shadeClose:true,
				  shade: 0,
				  area: ['600px', '400px'], //宽高
				  content: '<a href="'+Util.consts.downloadPath+'/'+event+'" download="'+event+'"><img src="'+Util.consts.downloadPath+'/'+event+'/v4/w/600/h/358'+'"/></a>'
			});
		}
	}
});
