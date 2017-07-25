$(function () {
    $("#jqGrid").jqGrid({
        url: '../feedback/list',
        datatype: "json",
        colModel: [			
			// 隐藏主键
			{ label: '主键id', name: 'id', width: 50, key: true ,hidden : true },
			{ label: '信息来源', name: 'infosourceText', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '问题类型', name: 'problemtypeText', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '图片', name: 'img', width: 80 , formatter: function(value, options, row){
					if (value==null) {
						return '没有照片';
					}
					imgs = value.split(',');
					s = "";
					if(value){
						for(var i in imgs){
							s += "<img src='"+Util.consts.downloadPath+"/"+imgs[i]+"/v4/w/30/h/30' onclick='vm.showpic(\""+imgs[i]+"\")' />&nbsp;&nbsp;"
						}
					} else {
						s = "无";
					}
					return s;
			}}, 
				{ label: '问题详情', name: 'content', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '姓名', name: 'name', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
			{ label: '微信号', name: 'wechatid', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
			{ label: '手机号', name: 'mobile', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
			{ label: '邮箱', name: 'email', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '反馈时间', name: 'crttm', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
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
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
    
    $.get("../basecode/selectByType/infosource", function(r) {
		if (r.code != 0) {
			alert(r.msg);
			vm.reload();
		}
		// 填充数据到页面
		$('#info').combobox({
			readonly: false,
		    data: r.data,
		    multiple:false,
		    valueField:'no',
		    textField:'cnm',
		    panelHeight: 'auto',
		    editable: false,
   			onChange:function(newValue, oldValue){
   		    }
		});
	});
	$.get("../basecode/selectByType/problemtype", function(r) {
		if (r.code != 0) {
			alert(r.msg);
			vm.reload();
		}
		// 填充数据到页面
		$('#type').combobox({
			readonly: false,
			data: r.data,
			multiple:false,
			valueField:'no',
			textField:'cnm',
			panelHeight: 'auto',
			editable: false,
			onChange:function(newValue, oldValue){
			}
		});
	});
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		q : {info:null,type:null,name:null},
		showList: true,
		upd: false,
		title: null,
		feedback: {status:1,}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.upd = true;
			vm.title = "新增";
			vm.feedback = {status:1};
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
			var url = vm.feedback.id == null ? "../feedback/save" : "../feedback/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.feedback),
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
		    	$.get("../feedback/delete/"+id, function(r){
					if(r.code == 0){
						$("#jqGrid").trigger("reloadGrid");
						top.layer.closeAll();
					}else{
						alert(r.msg);
					}
				});
			});
		},
		showpic: function(src){
			if(src){
				top.layer.open({
				  type: 1,
				  title: '图片查看',
				  shadeClose: true,
				  area: ['600px', '400px'],
				  content: "<a href='"+Util.consts.downloadPath+"/"+src+"' download='"+src+"'><img src='"+Util.consts.downloadPath+"/"+src+"/v4/w/600/h/358' /> </a>"
				});
			}
		},
		getInfo: function(id){
			$.get("../feedback/info/"+id, function(r){
                vm.feedback = r.feedback;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			var _info = $("#info").val();
			var _type = $("#type").val();
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData : {
					'name' : vm.q.name,
					'info' : _info,
					'type': _type 
				},
                page:page
            }).trigger("reloadGrid");
		}
	}
});
