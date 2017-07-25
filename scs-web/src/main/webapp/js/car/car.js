$(function() {
	$("#jqGrid").jqGrid({
		url : '../car/list',
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
					label : '所属车队',
					name : 'fleettName',
					width : 80
				},
				{
					label : '车辆类型',
					name : 'ctypeName',
					width : 80
				},
				{
					label : '车牌号',
					name : 'cno',
					width : 80
				},
				{
					label : '车架号',
					name : 'cin',
					width : 80
				},
				{
					label : '发动机号',
					name : 'engno',
					width : 80
				},
				{
					label : '绑定设备（车载）',
					name : 'binddev',
					width : 80
				},
				{
					label : '绑定设备（RFID）',
					name : 'binddevrfid',
					width : 80
				},
				{
					label : '责任人',
					name : 'personName',
					width : 80
				},
//				{
//					label : '联系电话',
//					name : 'phone',
//					width : 80
//				},
//				{
//					label : '备注',
//					name : 'rmk',
//					width : 80
//				},
//				// 状态
//				{
//					label : '状态',
//					name : 'status',
//					width : 50,
//					hidden : Util.consts.isadmin,
//					formatter : function(value, options, row) {
//						return value == 0 ? '<span class="label label-danger">无效</span>'
//								: '<span class="label label-success">有效</span>';
//					}
//				},
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
			// 隐藏grid底部滚动条
			$("#jqGrid").closest(".ui-jqgrid-bdiv").css({
				"overflow-x" : "hidden"
			});
		}
	});
	 // 表单验证
    validate = $(".form-horizontal").validate({
  	   onfocusout: false,
 	   onkeyup: false,
 	   rules: {
 		   fleett: {
 			   required: true
 		   },
 		   ctype: {
 			   required: true
 		   },
 		   cno: {
 			   required: true
 		   },
 		   cin: {
 			   required: true
 		   },
 		   engno: {
 			   required: true
 		   }
 	   },
 	   messages: {
 		   fleett: {
 		       required: "必填，请输入车队名称"
 		   },
 		   ctype: {
			   required: "必填，请输入车辆类型"
		   },
		   cno: {
			   required: "必填，请输入车牌号"
		   },
		   cin: {
			   required: "必填，请输入车架号"
		   },
		   engno: {
			   required: "必填，请输入发动机号"
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

var setting = {
	data : {
		simpleData : {
			enable : true,
			idKey : "id",
			pIdKey : "pid",
			rootPId : 0
		},
		key : {
			url : "nourl"
		}
	},
};

var ztree; // 责任人

var vm = new Vue({
	el : '#rrapp',
	data : {
		   options: [
		    { text: '苹果', value: 'apple' },
		    { text: '香蕉', value: 'banana' },
		    { text: '西瓜', value: 'watermelon' }
		   ]
			,
		q : {
			condition:null,
			carType:'all' //车辆类型
		},
		showPic: false, // 显示图片
		showUploadPic: false, // 显示上传图片的input组件
		showList : true,
		upd : false,
		title : null,
		car : {
			status : 1,
			person : '',
			person: '',
			personName: '',
			fleett: '',
			pic: ''
		}
	},
	watch:{
		  q :{
			  deep: true,
			  handler: function(val, oldVal){
	                console.log(val);
	                //值改变
	                vm.reload();
	                
	            }
			
		  }
	  },
	methods : {
		query : function() {
			vm.reload();
		},
		getUser : function(id) {
			$.get("../sys/department/selectForCmn", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				ztree = $.fn.zTree.init($("#deptTree"), setting,
						r.deptList);
			})
		},
		getCtype : function(id) {
			$.get("../cartype/listall", function(r) {
				
				
				
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				console.log(r);
				vm.options = r.data;
				
				// 填充数据到页面
				$('#ctype').combobox({
					readonly: false,
				    data: r.data,
				    valueField:'id',
				    textField:'nm',
				    panelHeight: 'auto',
				    editable: false,
		   			onChange:function(newValue, oldValue){
		   				
		   		    }
				});
			})
		},
		getCtypeForList : function() {
			$.get("../cartype/listall", function(r) {
				
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				console.log(r);
				r.data.splice(0, 0, {id:'all',nm:'-请筛选车辆类型-'});
				vm.options = r.data;
				
				
			
			})
		},
		getFleett : function(id) {
			$.get("../fleett/listall", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				// 填充数据到页面
				$('#fleett').combobox({
					readonly: false,
				    data: r.data,
				    valueField:'id',
				    textField:'nm',
				    panelHeight: 'auto',
				    editable: false,
		   			onChange:function(newValue, oldValue) {}
				});
			})
		},
		add : function() {
			$('#pic').val('');
			vm.showPic = false;
			vm.showUploadPic = true;
			vm.showList = false;
			vm.upd = true;
			vm.title = "新增";
			vm.car = {
				status : 1,
				person : '',
				person: '',
				personName: '',
				pic: ''
			};
			vm.getUser(null); // 责任人
			vm.getCtype(null); // 车辆类型
			vm.getFleett(null); // 车队
			vm.uploadfile('#uploadPic'); // 上传图片
		},
		uploadfile: function(element){
			$(element + " ~ div").remove();
			$(element).diyUpload({
		    	url: Util.consts.filePath,
		    	pick: {
		    		id:$(element),
		    		innerHTML: '请选择图片',
		    		multiple: false
		    	},
		    	fileNumLimit:1,
		    	success:function( data ) {
		    		vm.car.pic = data.data[0];
		    	},
		    	error:function( err ) {
		    		alert("服务器忙，请稍候再试。");
		    	}
		    });
		},
		deptTree : function() {
			layer.open({
				type : 1,
				offset : '50px',
				skin : 'layui-layer-molv',
				title : "选择责任人",
				area : [ '300px', '450px' ],
				shade : 0,
				shadeClose : false,
				content : jQuery("#deptLayer"),
				btn : [ '确定', '取消' ],
				btn1 : function(index) {
					// 获取选择的菜单
					var nodes = ztree.getSelectedNodes();
					if(nodes.length>0){
						var node = nodes[0];
						if (node.userid) {
							vm.car.person = nodes[0].userid.toString();
							vm.car.personName = nodes[0].name.toString();
							layer.close(index);
						} else {
							alert('请选择一个人作为负责人');
						}
					}
				}
			});
		},
		update : function(id, isupdate) {
			if (id == null) {
				return;
			}
			vm.showList = false;
			vm.upd = isupdate;
			vm.showPic = true;
			vm.showUploadPic = isupdate ? true : false;
            vm.title = isupdate ? "修改" : "查看";
			vm.getInfo(id);
			
			if (vm.upd) {
            	$(".amap").show();
            } else {
            	$(".amap").hide();
            }
			
			// 所属车队
			$.get("../fleett/listall", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				var _data = r.data;
				_data.forEach(function (e){
					if (e.id == vm.car.fleett) {
						e.selected = true;
					}
				});
				$('#fleett').combobox({
					readonly: false,
				    data: _data,
				    valueField:'id',
				    textField:'nm',
				    panelHeight: 'auto',
				    editable: false,
		   			onChange:function(newValue, oldValue) {}
				});
			});
			// 车辆类型
			$.get("../cartype/listall", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				var _data = r.data;
				_data.forEach(function (e){
					if (e.id == vm.car.ctype) {
						e.selected = true;
					}
				});
				// 填充数据到页面
				$('#ctype').combobox({
					readonly: false,
				    data: r.data,
				    valueField:'id',
				    textField:'nm',
				    panelHeight: 'auto',
				    editable: false,
		   			onChange:function(newValue, oldValue){
		   				
		   		    }
				});
			});
			// 责任人
			$.get("../sys/department/selectForCmn", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				r.deptList.forEach(function (e){
					if (e.userid && e.userid == vm.car.person) {
						vm.car.personName = e.name;
					}
				});
				ztree = $.fn.zTree.init($("#deptTree"), setting, r.deptList);
			});
			// 图片
			$("#showPic").attr({ src: Util.consts.downloadPath + "/" + vm.car.pic + "/v4/w/75/h/75/"});
			$("#showPic").unbind("click");
			$("#showPic").bind("click", function() {
				vm.showpic(vm.car.pic);
			});		
			// 上传图片
			vm.uploadfile('#uploadPic');
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
		saveOrUpdate : function(event) {
			if(validate.form()) {
				var url = vm.car.id == null ? "../car/save" : "../car/update";
				// 如果是保存，则必须要上传图片，更新的话就不需要
				var isSave = vm.car.id == null;
				if (isSave) {
					if (vm.car.pic == null || vm.car.pic == '') {
						layer.msg("必填，请选择图片", function(){});
						return;
					}
				}
				
				var _json = $(".form-horizontal").form2json();
				_json.id = vm.car.id;
				_json.pic = vm.car.pic;
				$.ajax({
					type : "POST",
					url : url,
					data : JSON.stringify(_json),
					success : function(r) {
						if (r.code == 0) {
							alert('操作成功', function(index) {
								vm.reload();
							});
						} else {
							alert(r.msg);
							return;
						}
					}
				});
				
			}
		},
		del : function(id) {
			confirm('确定要删除选中的记录？', function() {
				$.get("../car/delete/" + id, function(r) {
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
			$.ajax({
			   type: "POST",
			   url: "../car/info/" + id,
			   async: false,
			   success: function(r) {
					vm.car = r.car;
			   }
			});
		},
		reload : function(event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				postData:{'condition': vm.q.condition,
					carType : vm.q.carType == 'all' ?'' : vm.q.carType
				},
				page : page
			}).trigger("reloadGrid");
			$(".amap").hide();
		}
	}
	
});
vm.getCtypeForList();

