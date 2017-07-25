$(function () {
    $("#jqGrid").jqGrid({
        url: '../qltsuaauditee/list',
        datatype: "json",
        colModel: [			
			//隐藏主键
			{ label: '主键id', name: 'id', width: 50, key: true ,hidden : true },
			{ label: '扣分职员', name: 'personName', width: 80 }, 
			{ label: '所属部门', name: 'deptName', width: 80 }, 
			{ label: '业务板块', width: 80, formatter: function(value, options, row) {return '清扫保洁'} }, 
			{ label: '分数', name: 'scoreName', width: 80 }, 
			{ label: '扣分时间', name: 'stime', width: 80 }, 
			{ label: '现场照片', name: 'img', width: 80, formatter: function(value, options, row) {
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
			} }, 
			{ label: '详细地址', name: 'accaddr', width: 80 }, 
			{ label: '创建人', name: 'crtName', width: 80 }, 
			// 状态
			{ label: '审核状态', name: 'status', width: 50, hidden: Util.consts.isadmin, formatter: function(value, options, row){
			return value == 0 ? 
					'<font color="pink">待审核</font>' : 
						value == 1 ? 
							'<font color="green">通过</font>' : 
							'<font color="red">未通过</font>' ;
			}},
			//{ label: '创建时间', name: 'crttm', width: 80 }, 
				{ label: '操作', name: 'operation', width: 40, formatter: function(value, options, row){
				return "<a onClick=\"javascript:vm.auditing("+options.rowId+", false);\" title='审核' ><i class=\"fa fa-stack-overflow\" style='color:blue;'></i></a>&nbsp;&nbsp;";
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
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		q : {
			name : null
		},
		showList: true,
		upd: false,
		title: null,
		qltsuaAuditee: {status:1,}
	},
	methods: {
		query: function () {
			vm.reload();
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
		auditing: function (id, isupdate) {
			if(id == null){
				return ;
			}
			vm.showList = false;
			vm.upd = isupdate;
            vm.title = '审核';
            
            vm.getInfo(id);
            
            // 扣分时间
            var date = new Date(vm.qltsuaAuditee.stime);
            $("#stime").val(date.Format("yyyy-MM-dd hh:mm:ss"));
            
            // 照片
            $("#showPic").attr({ src: Util.consts.downloadPath + "/" + vm.qltsuaAuditee.img + "/v4/w/75/h/75/"});
			$("#showPic").unbind("click");
			$("#showPic").bind("click", function() {
				vm.showpic(vm.qltsuaAuditee.img);
			});	
            
            // 审核是否通过
			var sdata = [{'text':'通过',value:'1'},{'text':'不通过',value:'0'}];
			if (vm.qltsuaAuditee.status == 1) {
				sdata[0].selected = true;
			} else {
				sdata[1].selected = true;
			}
			$('#status').combobox({
				readonly: false,
			    data: sdata,
			    valueField:'value',
			    textField:'text',
			    panelHeight: 'auto',
			    editable: false,
	   			onChange:function(newValue, oldValue){
	   		    }
			});
		},
		doAuditing: function (event) {
			var url = "../qltsuaauditee/auditing";
			
			var _data = {};
			_data['id'] = vm.qltsuaAuditee.id;
			_data['status'] = $("input[name='status']").val();
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(_data),
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
		    	$.get("../qltsuaauditee/delete/"+id, function(r){
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
			$.ajax({
			   type: "GET",
			   url: "../qltsuaauditee/info/"+id,
			   async: false,
			   success: function(r) {
				   vm.qltsuaAuditee = r.qltsuaAuditee;
				   console.log(JSON.stringify(vm.qltsuaAuditee));
			   }
			});
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData : {
					'name' : vm.q.name
				},
                page:page
            }).trigger("reloadGrid");
		}
	}
});
