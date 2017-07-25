$(function () {
    $("#jqGrid").jqGrid({
        url: '../basecode/list',
        datatype: "json",
        colModel: [			
			// 隐藏主键
			{ label: 'id', name: 'id', width: 50, key: true ,hidden : true },
			{ label: '代码', name: 'no', width: 80 }, 			
			{ label: '类型', name: 'type', width: 80 }, 			
			{ label: '中文名', name: 'cnm', width: 80 }, 			
			{ label: '附加值', name: 'attv', width: 80 }, 			
			{ label: '是否有效', name: 'valid', width: 80, formatter: function(value, options, row){
			return value == 0 ? 
				'<span class="label label-danger">无效</span>' : 
				'<span class="label label-success">有效</span>';
			}},		
			{ label: '排序号', name: 'sort', width: 80 }, 			
			{ label: '备注', name: 'rmk', width: 80 }, 		
			{ label: '操作', name: 'operation', width: 40, formatter: function(value, options, row){
				var oper = "<a onClick=\"javascript:vm.update("+options.rowId+", false);\" title='查看' ><i class=\"fa fa-info-circle\" style='color:violet;'></i></a>&nbsp;&nbsp;";
				oper += update ? "<a onClick=\"javascript:vm.update("+options.rowId+", true);\" title='修改' ><i class=\"fa fa-pencil-square-o\" style='color:green;'></i></a>&nbsp;&nbsp;":"";
				oper += del ? "<a onClick=\"javascript:vm.del("+options.rowId+");\" title='删除' ><i class=\"fa fa-trash-o\" style='color:red;'></i></a>" : "";
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
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			keyword: null
		},
		showList: true,
		upd: false,
		title: null,
		basecode: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.upd = true;
			vm.title = "新增";
			vm.basecode = {valid:1};
		},
		update: function (id, isupdate) {
			if(id == null){
				return ;
			}
			vm.showList = false;
			vm.upd = isupdate;
            vm.title = isupdate ? "修改" : "查看";
            vm.title = "修改";

            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.basecode.id == null ? "../basecode/save" : "../basecode/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.basecode),
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
		del: function (id) {
			confirm('确定要删除选中的记录？', function(){
		    	$.get("../basecode/delete/"+id, function(r){
					if(r.code == 0){
						/*top.location.reload();*/
						$("#jqGrid").trigger("reloadGrid");
						top.layer.closeAll();
					}else{
						alert(r.msg);
					}
				});
			});
		},
		getInfo: function(id){
			$.get("../basecode/info/"+id, function(r){
                vm.basecode = r.basecode;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'keyword': vm.q.keyword},
                page:page
            }).trigger("reloadGrid");
		}
	}
});