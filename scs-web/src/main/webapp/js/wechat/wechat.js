$(function () {
    $("#jqGrid").jqGrid({
        url: '../wechat/list',
        datatype: "json",
        colModel: [			
		// 隐藏主键
		{ label: 'id', name: 'id', width: 50, key: true ,hidden : true },
		{ label: '用户标识', name: 'openid', width: 80 }, 
		{ label: '是否订阅', name: 'subscribe', width: 50 , formatter: function(value, options, row){
		return value == 0 ? 
				'<span class="label label-danger">没有关注</span>' : 
				'<span class="label label-success">正常关注</span>';
		}},
		{ label: '用户昵称', name: 'nickname', width: 80 }, 
		{ label: '性别', name: 'sex', width: 40 , formatter: function(value, options, row){
		return value == 0 ? 
				'<span class="label label-danger">未知</span>' : 
					value == 1 ? 
						'<span class="label label-success">男</span>' : 
							'<span class="label label-success">女</span>';
		}},
		{ label: '所在城市', name: 'city', width: 50 }, 
		{ label: '所在国家', name: 'country', width: 50 }, 
		{ label: '所在省份', name: 'province', width: 50 }, 
		{ label: '语言', name: 'language', width: 50 }, 
		{ label: '头像', name: 'headimgurl', width: 80 }, 
		{ label: '关注时间', name: 'subscribeTime', width: 80 }, 
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
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		upd: false,
		title: null,
		wechat: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.upd = true;
			vm.title = "新增";
			vm.wechat = {};
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
			var url = vm.wechat.id == null ? "../wechat/save" : "../wechat/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.wechat),
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
		    	$.get("../wechat/delete/"+id, function(r){
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
			$.get("../wechat/info/"+id, function(r){
                vm.wechat = r.wechat;
                $("[name='headimg']").attr("src", r.wechat.headimgurl);
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
