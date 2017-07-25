Array.prototype.removeByValue = function(val) {
  for(var i=0; i<this.length; i++) {
    if(this[i] == val) {
      this.splice(i, 1);
      break;
    }
  }
}

$(function () {
    $("#jqGrid").jqGrid({
        url: '../accident/list',
        datatype: "json",
        colModel: [			
			// 隐藏主键
			{ label: '主键id', name: 'id', width: 50, key: true ,hidden : true },
			{ label: '肇事车辆', name: 'carNo', width: 80 }, 
			{ label: '所属车队', name: 'fleettName', width: 80 }, 
				{ label: '肇事人', name: 'personName', width: 80 }, 
				{ label: '肇事日期', name: 'adate', width: 80, formatter:'date' }, 
				{ label: '肇事地点', name: 'addr', width: 80 }, 
				{ label: '事故责任', name: 'aresName', width: 80 }, 
				{ label: '应赔金额', name: 'money', width: 80 }, 
				{ label: '实赔金额', name: 'rmoney', width: 80 }, 
//				{ label: '事故经过', name: 'content', width: 80 }, 
//				{ label: '现场照片', name: 'pics', width: 80 }, 
		// 状态
//			{ label: '状态', name: 'status', width: 50, hidden: Util.consts.isadmin, formatter: function(value, options, row){
//			return value == 0 ? 
//				'<span class="label label-danger">	无效</span>' : 
//				'<span class="label label-success">有效</span>';
//			}},
//			{ label: '创建时间', name: 'crttm', width: 80 }, 
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
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });

	 // 表单验证
    validate = $(".form-horizontal").validate({
  	   onfocusout: false,
 	   onkeyup: false,
 	   rules: {
 		  car: {
 			   required: true
 		   },
 		  person: {
 			   required: true
 		   },
 		  adate: {
 			   required: true
 		   },
 		  addr: {
 			   required: true
 		   },
 		  ares: {
 			   required: true
 		   }
 	   },
 	   messages: {
 		  car: {
			   required: "必填，请输入车牌号"
		   },
		  person: {
			   required: "必填，请输入肇事人"
		   },
		  adate: {
			   required: "必填，请输入肇事日期"
		   },
		  addr: {
			   required: "必填，请输入肇事地点"
		   },
		  ares: {
			   required: "必填，请输入事故责任"
		  }
 	   },
 	   showErrors: function(errorMap, errorList) {
			if ( errorList && errorList.length > 0 ) {  //如果存在错误信息
				layer.msg(errorList[0].message, function(){});
			}
		}
    });
    // 表单验证结束
});

var ztreePerson;

var settingPerson = {
	data : {
		simpleData : {
			enable : true,
			idKey : "id",
			pIdKey : "pid",
			rootPId : -1
		},
		key : {
			url : "nourl"
		}
	},
	check : {
		enable : false,
		nocheckInherit : true,
		chkboxType : {
			"Y" : "",
			"N" : ""
		},
		chkStyle : "radio",
		radioType : "all"
	}
};

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{condition:'',
			starttime:'',
			endtime:''},
		showList: true,
		showUploadPic:true,
		showPic:false,
		upd: false,
		title: null,
		accident: {status:1,person:'',personName:'',fleett:'',picKeyArray:[],picKeys:''}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		getCno : function(id) {
			$.get("../car/listAll", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				// 填充数据到页面
				$('#car').combobox({
					readonly: false,
				    data: r.data,
				    valueField:'id',
				    textField:'cno',
				    panelHeight: 'auto',
				    editable: false,
		   			onChange:function(newValue, oldValue){
		   				var fleettId = null;
		   				r.data.forEach(function(e){
		   					if(e.id == newValue) {
		   						fleettId = e.fleett;
		   					}
		   				});
						$.ajax({
						   type: "GET",
						   url: "../fleett/info/" + fleettId,
						   async: false,
						   success: function(r) {
							   vm.accident.fleett = r.fleett.nm;
						   }
						});
		   				
		   		    }
				});
			})
		},
		getPerson: function (){
			$.get("../sys/department/selectForCmn", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				ztreePerson = $.fn.zTree.init($("#personTree"), settingPerson, r.deptList);
			});
		},
		getAres: function(id) {
			$.get("../basecode/selectByType/ares", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				// 填充数据到页面
				$('#ares').combobox({
					readonly: false,
				    data: r.data,
				    valueField:'id',
				    textField:'cnm',
				    panelHeight: 'auto',
				    editable: false,
		   			onChange:function(newValue, oldValue){
		   		    }
				});
			})
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
		uploadfile: function(element) {
			$(element).diyUpload({
		    	url: Util.consts.filePath,
		    	success:function( data ) {
		    		vm.accident.picKeyArray.push(data.data[0]);
		    		vm.accident.picKeys = vm.accident.picKeyArray.toString();
		    	},
		    	error:function( err ) {
		    		alert("服务器忙，请稍候再试。");
		    	}
		    });
		},
		add: function(){
			picKeys = '';
			vm.showList = false;
			vm.showUploadPic = true;
			vm.showPic = false;
			vm.upd = true;
			vm.title = "新增";
			vm.accident = {status:1,person:'',personName:'',fleett:'',picKeyArray:[],picKeys:''};
			
			vm.getCno(); // 车牌号
			vm.getPerson(); // 肇事人
			vm.getAres(); // 事故责任
			vm.uploadfile('#uploadPic');
			
			$("#adate").val('');
			$("#uploadPic").siblings().remove();
		},
		deletePic: function(key) {
			$("img[d='"+key+"']").remove();
			vm.accident.picKeyArray.removeByValue(key);
    		vm.accident.picKeys = vm.accident.picKeyArray.toString();
		},
		update: function (id, isupdate) {
			if(id == null){
				return ;
			}
			vm.showList = false;
			vm.upd = isupdate;
            vm.title = isupdate ? "修改" : "查看";
            
            vm.getInfo(id);
            // 车牌号
            $.get("../car/listAll", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				var _data = r.data;
				_data.forEach(function (e){
					if (e.id == vm.accident.car) {
						e.selected = true;
					}
				});
				$('#car').combobox({
					readonly: false,
				    data: r.data,
				    valueField:'id',
				    textField:'cno',
				    panelHeight: 'auto',
				    editable: false,
		   			onChange:function(newValue, oldValue){
		   		    }
				});
			});
            vm.accident.fleett = vm.accident.fleettName;
            // 肇事人
            $.get("../sys/department/selectForCmn", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				ztreePerson = $.fn.zTree.init($("#personTree"), settingPerson, r.deptList);
				var nodes = ztreePerson.transformToArray(ztreePerson.getNodes());
				nodes.forEach(function(e){
					if(e.userid==vm.accident.person) {
						vm.accident.person = e.userid;
						vm.accident.personName = e.name.toString();
					}
				});
			});
            // 肇事日期
            var date = new Date(vm.accident.adate);
            $("#adate").val(date.Format("yyyy-MM-dd"));
            // 事故责任
			$.get("../basecode/selectByType/ares", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				var _data = r.data;
				_data.forEach(function (e){
					if (e.id == vm.accident.ares) {
						e.selected = true;
					}
				});
				$('#ares').combobox({
					readonly: false,
				    data: r.data,
				    valueField:'id',
				    textField:'cnm',
				    panelHeight: 'auto',
				    editable: false,
		   			onChange:function(newValue, oldValue){
		   		    }
				});
			});
			// 现场照片
			var pics = vm.accident.pics.split(",");
			vm.accident.picKeys = vm.accident.pics;
			vm.accident.picKeyArray = pics;
			
			$("#uploadPic").siblings().remove();
			
			$("#rowPic").children().remove();
			pics.forEach(function (key) {
//				$("#rowPic").append("<img onclick='vm.showpic(\""+key+"\")' src=Util.consts.downloadPath/" + key + "/v4/w/75/h/75/>");
				$("#rowPic").append('<img d='+key+' src="'+Util.consts.downloadPath+'/'+key+'/v4/w/75/h/75/" style="margin:5px;width:75px;height:75px; display: inline-block;" onclick="vm.showpic(\''+key+'\')">');
				if (vm.upd) {
					$("#rowPic").append('<img d='+key+' src="/statics/img/x.png" alt="删除图片" style="position: relative;right: 12px;top:-38px;cursor:pointer;" onclick="vm.deletePic(\''+key+'\')"/></div>');
				}
			});
			if(vm.upd) {
				// 修改
				vm.showUploadPic = true;
				vm.showPic = true;
				vm.uploadfile('#uploadPic');
			} else {
				// 查看
				vm.showUploadPic = false;
				vm.showPic = true;
			}
		
		},
		saveOrUpdate: function (event) {
			if(validate.form()) {
				var url = vm.accident.id == null ? "../accident/save" : "../accident/update";
				var _json = $(".form-horizontal").form2json();
				if (vm.accident.picKeyArray.length>5) {
					alert("图片数量不能超过5张");
					return;
				}
				_json.id = vm.accident.id;
				_json.pics = vm.accident.picKeys;
				$.ajax({
					type: "POST",
				    url: url,
				    data: JSON.stringify(_json),
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
			}
		},
		del: function (id) {
			confirm('确定要删除选中的记录？', function(){
		    	$.get("../accident/delete/"+id, function(r){
					if(r.code == 0){
						$("#jqGrid").trigger("reloadGrid");
						top.layer.closeAll();
					}else{
						alert(r.msg);
					}
				});
			});
		},
		personTree : function() {
			layer.open({
				type : 1,
				offset : '50px',
				skin : 'layui-layer-molv',
				title : "选择办理人员",
				area : [ '300px', '450px' ],
				shade : 0,
				shadeClose : false,
				content : jQuery("#personLayer"),
				btn : [ '确定', '取消' ],
				btn1 : function(index) {
					// 获取选择的菜单
					var nodes = ztreePerson.getSelectedNodes();
					if(nodes.length>0){
						var node = nodes[0];
						if (node.userid) {
							vm.accident.person = nodes[0].userid.toString();
							vm.accident.personName = nodes[0].name.toString();
							layer.close(index);
						} else {
							alert('请选择肇事人');
						}
					}
				}
			});
		},
		getInfo: function(id){
//			$.get("../accident/info/"+id, function(r){
//                vm.accident = r.accident;
//            });
			$.ajax({
			   type: "GET",
			   url: "../accident/info/"+id,
			   async: false,
			   success: function(r) {
				   vm.accident = r.accident;
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
