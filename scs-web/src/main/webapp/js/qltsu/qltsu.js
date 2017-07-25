var map = new AMap.Map("container", {
    resizeEnable: true,
    center: [116.403322, 39.900255],
    zoom: 13
});
map.setDefaultCursor("pointer");
var clickEventListener = map.on('click', function(e) {
	vm.showlnglat(e.lnglat.getLng(), e.lnglat.getLat());
});
var auto = new AMap.Autocomplete({
    input: "tipinput"
});
$(function () {
    $("#jqGrid").jqGrid({
        url: '../qltsu/list',
        datatype: "json",
        colModel: [			
			// 隐藏主键
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
//			{ label: '创建时间', name: 'crttm', width: 80 }, 
				{ label: '操作', name: 'operation', width: 40, formatter: function(value, options, row){
				var oper = "<a onClick=\"javascript:vm.update("+options.rowId+", false);\" title='查看' ><i class=\"fa fa-info-circle\" style='color:violet;'></i></a>&nbsp;&nbsp;";
//				oper += update ? "<a onClick=\"javascript:vm.update("+options.rowId+", true);\" title='修改'  ><i class=\"fa fa-pencil-square-o\" style='color:green;'></i></a>&nbsp;&nbsp;":"";
//				oper += del ? "<a onClick=\"javascript:vm.del("+options.rowId+");\" title='删除'  ><i class=\"fa fa-trash-o\" style='color:red;'></i></a>" : "";
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
 		  scoretype: {
 			   required: true
 		   },
 		  addr: {
 			   required: true
 		   },
 		  score: {
 			   required: true
 		   },
 		  stime: {
 			   required: true
 		   },
 		  person: {
 			   required: true
 		   },
 		  accaddr: {
			   required: true
		   },
		   img : {
			   required: true
		   }
 	   },
 	   messages: {
 		  scoretype: {
			   required: "必填，请输入扣分类型"
		   },
		  addr: {
			   required: "必填，请选择地址"
		   },
		  score: {
			   required: "必填，请输入扣分"
		   },
		  stime: {
			   required: "必填，请输入扣分时间"
		   },
		  person: {
			   required: "必填，请输入选人"
		   },
		  accaddr: {
			   required: "必填，请输入详细地址"
		   },
		   img : {
			   required: "必填，请上传图片"
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
			idKey : "ztid",
			pIdKey : "ztpid",
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
		q : {
			name : null
		},
		showPic: false, // 显示图片
		showUploadPic: false, // 显示上传图片的input组件
		showList: true,
		upd: false,
		title: null,
		qltsu: {status:1,person:'',personName:'',img:'',addr:''}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		getScoretype : function(id) {
			$.get("../basecode/selectByType/scoretype", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				// 填充数据到页面
				$('#scoretype').combobox({
					readonly: false,
				    data: r.data,
				    valueField:'no',
				    textField:'cnm',
				    panelHeight: 'auto',
				    editable: false,
		   			onChange:function(newValue, oldValue){
		   		    }
				});
			})
		},
		getScore : function(id) {
			$.get("../basecode/selectByType/score", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				// 填充数据到页面
				$('#score').combobox({
					readonly: false,
					data: r.data,
					valueField:'no',
					textField:'cnm',
					panelHeight: 'auto',
					editable: false,
					onChange:function(newValue, oldValue){
					}
				});
			})
		},
		getPerson: function (){
			$.get("../sys/user/selectForZtree", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				ztreePerson = $.fn.zTree.init($("#personTree"), settingPerson, r.userList);
			});
		},
		fileupload: function (selector){
			$(selector + " ~ div").remove();
			$(selector).diyUpload({
		    	url: Util.consts.filePath,
		    	pick: {
		    		id:$(selector),
		    		innerHTML: '请选择图片',
		    		multiple: false
		    	},
		    	fileNumLimit:1,
		    	success:function( r ) {
		    		vm.qltsu.img = r.data[0].toString();
		    	},
		    	error:function( err ) {
		    		alert(err);
		    	},
		    	buttonText : '选择现场照片',
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
		add: function(){
			vm.showPic = false;
			vm.showUploadPic = true;
			vm.showList = false;
			vm.upd = true;
			vm.title = "新增";
			vm.qltsu = {status:1,person:'',personName:'',img:'',addr:''};
			
			
			
			vm.getScoretype(); // 扣分类型
			vm.getScore(); // 扣分
			vm.getPerson(); // 选人
			vm.fileupload("#uploadPic");
			
			
			AMap.event.addListener(auto, "select", vm.select);
			$(".amap").show();
		},
		update: function (id, isupdate) {
			if(id == null){
				return ;
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
            
            // 扣分类型
			$.get("../basecode/selectByType/scoretype", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				var _data = r.data;
				_data.forEach(function (e){
					if (e.no == vm.qltsu.scoretype) {
						e.selected = true;
					}
				});
				$('#scoretype').combobox({
					readonly: false,
				    data: r.data,
				    valueField:'no',
				    textField:'cnm',
				    panelHeight: 'auto',
				    editable: false,
		   			onChange:function(newValue, oldValue){
		   		    }
				});
			});

			// 扣分
			$.get("../basecode/selectByType/score", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				var _data = r.data;
				_data.forEach(function (e){
					if (e.no == vm.qltsu.score) {
						e.selected = true;
					}
				});
				$('#score').combobox({
					readonly: false,
					data: r.data,
					valueField:'no',
					textField:'cnm',
					panelHeight: 'auto',
					editable: false,
					onChange:function(newValue, oldValue){
					}
				});
			});
			// 扣分时间
            var date = new Date(vm.qltsu.stime);
            $("#stime").val(date.Format("yyyy-MM-dd hh:mm"));
            // 选人
            $.get("../sys/user/selectForZtree", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				ztreePerson = $.fn.zTree.init($("#personTree"), settingPerson, r.userList);
				var nodes = ztreePerson.transformToArray(ztreePerson.getNodes());
				nodes.forEach(function(e){
					if(e.id==vm.qltsu.person) {
						vm.qltsu.person = e.id.toString();
						vm.qltsu.personName = e.name.toString();
					}
				});
			});
            // 图片
			$("#showPic").attr({ src: Util.consts.downloadPath + "/" + vm.qltsu.img + "/v4/w/75/h/75/"});
			$("#showPic").unbind("click");
			$("#showPic").bind("click", function() {
				vm.showpic(vm.qltsu.img);
			});		
			// 上传图片
			vm.fileupload("#uploadPic");
		},
		saveOrUpdate: function (event) {
			if(validate.form()) {
				var url = vm.qltsu.id == null ? "../qltsu/save" : "../qltsu/update";
				var _json = $(".form-horizontal").form2json();
				_json.id = vm.qltsu.id;
				_json.status = '0';
				
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
		    	$.get("../qltsu/delete/"+id, function(r){
					if(r.code == 0){
						$("#jqGrid").trigger("reloadGrid");
						top.layer.closeAll();
					}else{
						alert(r.msg);
					}
				});
			});
		},
		select:function (e) {
	        if (e.poi && e.poi.location) {
	            map.setCenter(e.poi.location);
	            vm.showlnglat(e.poi.location.getLng(), e.poi.location.getLat());
	        } else {
	        	document.getElementById("lnglat").value = '';
	        }
	    },
	    showlnglat: function (lng, lat){
	        map.clearMap();
	        map.setZoom(16);
	        document.getElementById("lnglat").value = lng + ',' + lat;
	        vm.qltsu.lng = lng;
	        vm.qltsu.lat = lat;
	    	var marker = new AMap.Marker({
	            map: map,
	            position: [lng, lat],
	            icon: new AMap.Icon({            
		            image: "http://webapi.amap.com/theme/v1.3/markers/n/mark_r.png",
		        })
	        });
	        marker.setAnimation('AMAP_ANIMATION_BOUNCE');
	        vm.regeocoder([lng, lat]);
	    },
	    regeocoder: function(lnglatXY){
			var geocoder = new AMap.Geocoder({
	            radius: 1000,
	            extensions: "all"
	        });        
	        geocoder.getAddress(lnglatXY, function(status, result) {
	            if (status === 'complete' && result.info === 'OK') {
	                vm.geocoder_CallBack(result);
	            }
	        });        
	        /*var marker = new AMap.Marker({  //加点
	            map: map,
	            position: lnglatXY
	        });*/
	        map.setFitView();
		},
		geocoder_CallBack: function(data){
			 var address = data.regeocode.formattedAddress;
			 vm.qltsu.addr = address;
		},
		getInfo: function(id){
			$.ajax({
			   type: "GET",
			   url: "../qltsu/info/"+id,
			   async: false,
			   success: function(r) {
				   vm.qltsu = r.qltsu;
			   }
			});
		},
		personTree : function() {
			layer.open({
				type : 1,
				offset : '50px',
				skin : 'layui-layer-molv',
				title : "请选人",
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
						if (node.ztid == 9223372036854776000) {
							vm.qltsu.person = nodes[0].id.toString();
							vm.qltsu.personName = nodes[0].name.toString();
							layer.close(index);
						} else {
							alert('请选择一个人');
						}
					}
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
			$(".amap").hide();
		}
	}
});
