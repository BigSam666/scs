$(function () {
    $("#jqGrid").jqGrid({
        url: '../atdrecord/list',
        datatype: "json",
        colModel: [			
			// 隐藏主键
			{ label: '主键id', name: 'id', width: 50, key: true ,hidden : true },
			{ label: '日期', name: 'optime', width: 50 
			}, 
			{ label: '职员', name: 'realname', width: 60 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.realname : value;
			}},
		
			{ label: '所属部门', name: 'deptNames', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.deptNames : value;
			}},
			{ label: '业务板块', name: 'bsment', width: 50 , formatter: function(cellvalue, options, rowObject){
				if(cellvalue == 0 ){
					return "清扫保洁";
				}
				return cellvalue;
			}},
			{ label: '网格', name: 'gridName', width: 80 , formatter: function(cellvalue){
				return cellvalue;
			}},
			{ label: '班次', name: 'classes', width: 40 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.classes : value;
			}}, 
			{ label: '上班时间', name: 'btime', width: 70 , formatter: function(value, options, rowObject){
				
				if(rowObject.beLate){
					if(rowObject.beLate == 1){
						//红色
						return rowObject.btime? "<font  color='red'>" + rowObject.btime + "</font>" : "";
					}
				}
				
				return rowObject.btime? rowObject.btime : "";
			}}, 
			{ label: '下班时间', name: 'etime', width: 70 , formatter: function(value, options, rowObject){
				
				if(rowObject.leaveEarly){
					if(rowObject.leaveEarly == 1){
						//红色
						return rowObject.etime? "<font  color='red'>" + rowObject.etime + "</font>" : "";
					}
				}
				
				return rowObject.etime? rowObject.etime : "";
				
				
			}}, 
			{ label: '状态', name: 'operation', width: 40, formatter: function(cellvalue, options, rowObject){
				
				if(rowObject.atdtype){
					if(rowObject.atdtype == 3){
						return "正常(调班)";
					}else if(rowObject.atdtype == 4){
						return "正常(调休)";
					}
				}
				var str = '';
				if(rowObject.beLate){
					if(rowObject.beLate == 1){
						str +=',迟到';
					}
				}
				if(rowObject.beLate){
					if(rowObject.leaveEarly == 1){
						str +=',早退';
					}
				}
				if(str.length > 0){
					return str.substring(1, str.length);
				}else{
					if(rowObject.beLate && rowObject.beLate && rowObject.beLate==0 && rowObject.beLate == 0){
						return "正常";
					}
					
				}
				
				return "";
			}}
			
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
        	/*$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); */
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			name: null
		},
		showList: true,
		upd: false,
		title: null,
		atdRecord: {status:1,}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.upd = true;
			vm.title = "新增";
			vm.atdRecord = {status:1};
		},
		update: function (id, isupdate) {
			if(id == null){
				return ;
			}
			vm.showList = false;
			vm.upd = isupdate;
            vm.title = isupdate ? "修改" : "查看";
            
            vm.getInfo(id);
		},
		saveOrUpdate: function (event) {
			var url = vm.atdRecord.id == null ? "../atdrecord/save" : "../atdrecord/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.atdRecord),
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
		    	$.get("../atdrecord/delete/"+id, function(r){
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
			$.get("../atdrecord/info/"+id, function(r){
                vm.atdRecord = r.atdRecord;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'name': vm.q.name},
                page:page
            }).trigger("reloadGrid");
		}
	}
});
