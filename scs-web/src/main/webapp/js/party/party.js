$(function () {
    $("#jqGrid").jqGrid({
        url: '../party/list',
        datatype: "json",
        colModel: [			
			// 隐藏主键
			{ label: 'id', name: 'id', width: 50, key: true ,hidden : true },
			{ label: '名称', name: 'nm', width: 80 }, 			
			{ label: '联系人', name: 'contperson', width: 80 }, 			
			{ label: '备注', name: 'rmk', width: 80 }, 			
			{ label: '电话', name: 'phone', width: 80 }, 			
			{ label: '地址', name: 'addr', width: 80 }, 			
			{ label: '状态', name: 'status', width: 80 }, 		
			{ label: '操作', name: 'operation', width: 40, formatter: function(value, options, row){
				var oper = "<a onClick=\"javascript:vm.update("+options.rowId+", false);\" title='查看' ><i class=\"fa fa-info-circle\" style='color:violet;'></i></a>&nbsp;&nbsp;";
				oper += update ? "<a onClick=\"javascript:vm.update("+options.rowId+", true);\" title='修改'  ><i class=\"fa fa-pencil-square-o\" style='color:green;'></i></a>&nbsp;&nbsp;":"";
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
		showList: true,
		upd: false,
		title: null,
		party: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.upd = true;
			vm.title = "新增";
			vm.party = {};
		},
		update: function (id, isupdate) {
			if(id == null){
				return ;
			}
			vm.showList = false;
			vm.upd = isupdate;
            vm.title = isupdate ? "修改" : "查看";

            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.party.id == null ? "../party/save" : "../party/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.party),
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
		    	$.get("../party/delete/"+id, function(r){
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
			$.get("../party/info/"+id, function(r){
                vm.party = r.party;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});