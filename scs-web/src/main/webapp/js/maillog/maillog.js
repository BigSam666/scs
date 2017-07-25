$(function () {
    $("#jqGrid").jqGrid({
        url: '../maillog/list',
        datatype: "json",
        colModel: [			
			// 隐藏主键
			{ label: 'id', name: 'id', width: 50, key: true ,hidden : true },
			{ label: '接收人', name: 'unm', width: 50 }, 
				{ label: '发送类型', name: 'type', width: 40, formatter: function(value, options, row){
				return value == 1 ? 
					'<span class="label label-danger">系统</span>' : 
					'<span class="label label-success">人工</span>';
				}}, 
				{ label: '内容', name: 'content', width: 80, formatter: function(value, options, row){
					return value.replace(/\ +/g,"").replace(/[ ]/g,"").replace(/[\r\n]/g,"");
				}}, 
		// 状态
			{ label: '发送状态', name: 'status', width: 50, hidden: Util.consts.isadmin, formatter: function(value, options, row){
			return value == 0 ? 
				'<span class="label label-danger">失败</span>' : 
				'<span class="label label-success">成功</span>';
			}},
			{ label: '发送日志', name: 'log', width: 80, formatter: function(value, options, row){
				return value.replace(/\ +/g,"").replace(/[ ]/g,"").replace(/[\r\n]/g,"");
			}},
			{ label: '发送时间 ', name: 'crttm', width: 80 },
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

var setting = {
	data : {
		simpleData : {
			enable : true,
			idKey : "ztid",
			pIdKey : "ztpid",
			rootPId : -1
		},
		key : {
			url : "nourl"
		}
	},
	check : {
		enable : true,
		nocheckInherit : true,
	}
};
var ztree;

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{condition:'',
			starttime:'',
			endtime:''},
		showList: true,
		save: true,
		upd: false,
		title: null,
		maillog: {status:1, content:"",receiver:'',receiverName:'',}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.save = true,
			vm.upd = true;
			vm.title = "新增";
			vm.maillog = {status:1, content:"",receiver:'',receiverName:'',};
			$.get("../sys/user/selectForZtree", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				ztree = $.fn.zTree.init($("#deptTree"),
						setting, r.userList);
			});
			$.get("../mailtemplate/getSendTemplate", function(r){
				if(r.code != 0){
					alert(r.msg);
				} else {
					rdata = r.data;
					$('#tid').combobox({
						readonly: false,
					    data: rdata,
					    valueField:'id',
					    textField:'text',
					    // 默认选中
					    editable: false,
						panelHeight:'auto',
						onChange:function(newValue, oldValue){
							// 再请求一次做进一步处理
							$.get("../mailtemplate/info2/" + newValue, function(r2){
								if(r2.code != 0){
									alert(r2.msg);
								} else {
									rdata2 = r2.data;
									//$("[name='content']").text(rdata2.mailtemplate.content);
									vm.maillog.content = rdata2.mailtemplate.content;
									temp = $("#par");
									temp.siblings().remove();
									temp_="";
									for(var i in rdata2.par){
										temp_ += "<div class='col-sm-2 control-label'>"+rdata2.par[i]+"</div><div class='col-sm-10'>"
										temp_ += "<input type='text' class='form-control' name='"+rdata2.par[i]+"' placeholder='"+rdata2.par[i]+"' />&nbsp;&nbsp;"
										temp_ += "</div>"
									}
									temp.after(temp_);
								}
							});
					    }
					});
				}
            });
		},
		deptTree : function() {
			layer.open({
				type : 1,
				offset : '50px',
				skin : 'layui-layer-molv',
				title : "选择接收人",
				area : [ '300px', '450px' ],
				shade : 0,
				shadeClose : false,
				content : jQuery("#deptLayer"),
				btn : [ '确定', '取消' ],
				btn1 : function(index) {
					var nodes = ztree.getCheckedNodes(true);
					var receiver = new Array();
					var receiverName = new Array();
					for (var i = 0; i < nodes.length; i++) {
						if (nodes[i].ztid == 9223372036854776000 && !receiver.includes(nodes[i].id)) {
							receiver.push(nodes[i].id);
							receiverName.push(nodes[i].name);
						}
					}
					vm.maillog.receiver = receiver.toString();
					vm.maillog.receiverName = receiverName.toString();
					layer.close(index);
				}
			});
		},
		update: function (id, isupdate) {
			if(id == null){
				return ;
			}
			vm.showList = false;
			vm.upd = isupdate;
            vm.title = isupdate ? "修改" : "查看";
			vm.save = isupdate;
            
            vm.getInfo(id);
		},
		saveOrUpdate: function (event) {
			var url = vm.maillog.id == null ? "../maillog/save" : "../maillog/update";
			maillog = $('.form-horizontal').form2json();
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(maillog),
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
		    	$.get("../maillog/delete/"+id, function(r){
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
			$.get("../maillog/info/"+id, function(r){
                for(var name in r.maillog) {
					if(name == 'status'){
						$( ".form-horizontal" ).find( "[name='" + name + "']" ).text(r.maillog[ name ] == '1' ? '成功' : '失败' );
					} else {
						$( ".form-horizontal" ).find( "[name='" + name + "']" ).text(r.maillog[ name ] );
					}
				}
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'condition': vm.q.condition,
					'starttime':$("#starttime").val(),
					'endtime':$("#endtime").val()
				},
                page:page
            }).trigger("reloadGrid");
		}
	}
});
