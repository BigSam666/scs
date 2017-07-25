var map = null;
$(function () {
    $("#jqGrid").jqGrid({
        url: '../monitordev/list',
        datatype: "json",
        colModel: [			
			// 隐藏主键
			{ label: '主键id', name: 'id', width: 50, key: true ,hidden : true },
			{ label: '设备厂商', name: 'devfrimName', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '设备型号', name: 'devmodelName', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '设备编号', name: 'devid', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '监控地点', name: 'addr', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '修改人', name: 'updName', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '修改时间', name: 'updtm', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
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
 		  devfrim: {
 			   required: true
 		   },
 		  devmodel: {
 			   required: true
 		   },
 		  devid: {
 			   required: true
 		   },
 		  addr: {
 			   required: true
 		   }
 	   },
 	   messages: {
 		  devfrim: {
 		       required: "必填，请输入设备厂商"
 		   },
 		  devmodel: {
			   required: "必填，请输入设备型号"
		   },
		   devid: {
			   required: "必填，请输入设备编号"
		   },
		   addr: {
			   required: "必填，请选择监控地点"
		   }
 	   },
 	   showErrors: function(errorMap, errorList) {
			if ( errorList && errorList.length > 0 ) {  //如果存在错误信息
				layer.msg(errorList[0].message, function(){});
			}
		}
    });
    // 表单验证结束
    
    map = new AMap.Map('container', {
	    resizeEnable: true,
	    center: [113.858708, 22.56675],
	    zoom: 10,
	});
	map.setDefaultCursor("pointer");
	var clickEventListener = map.on('click', function(e) {
		showlnglat(e.lnglat.getLng(), e.lnglat.getLat());
	});
	var auto = new AMap.Autocomplete({
	    input: "tipinput"
	});
	AMap.event.addListener(auto, "select", select);
});

function select(e) {
	debugger
    if (e.poi && e.poi.location) {
        map.setCenter(e.poi.location);
        showlnglat(e.poi.location.getLng(), e.poi.location.getLat());
    }
}

function showlnglat(lng, lat){
    map.clearMap();
    map.setZoom(18);
    //alert("" + lng + ',' + lat);
    $("input[name='lng']").val( lng );
    $("input[name='lat']").val( lat );
	var marker = new AMap.Marker({
        map: map,
        position: [lng, lat],
        icon: new AMap.Icon({    
            image: "http://webapi.amap.com/theme/v1.3/markers/n/mark_r.png",
        })
    });
    marker.setAnimation('AMAP_ANIMATION_BOUNCE');
    regeocoder([lng, lat]);
}

function regeocoder(lnglatXY){
	var geocoder = new AMap.Geocoder({
        radius: 1000,
        extensions: "all"
    });        
    geocoder.getAddress(lnglatXY, function(status, result) {
        if (status === 'complete' && result.info === 'OK') {
            geocoder_CallBack(result);
        }
    });        
    map.setFitView();
}

function geocoder_CallBack(data){
	 var address = data.regeocode.formattedAddress;
	 $("input[name='addr']").val(address);
}

var vm = new Vue({
	el:'#rrapp',
	data:{
		q : {qdevid:null},
		showPic: false, // 显示图片
		showUploadPic: false, // 显示上传图片的input组件
		showList: true,
		upd: false,
		title: null,
		monitordev: {status:1,img:'',addr:''}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		getDevfrim : function(id) {
			$.get("../basecode/selectByType/monitordevfrim", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				// 填充数据到页面
				$('#devfrim').combobox({
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
		getDevmodel : function(id) {
			$.get("../basecode/selectByType/monitordevmodel", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				// 填充数据到页面
				$('#devmodel').combobox({
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
		    		vm.monitordev.pic = data.data[0];
		    	},
		    	error:function( err ) {
		    		alert("服务器忙，请稍候再试。");
		    	}
		    });
		},
		add: function(){
			$('#pic').val('');
			vm.showPic = false;
			vm.showUploadPic = true;
			vm.showList = false;
			vm.upd = true;
			vm.title = "新增";
			vm.monitordev = {status:1,img:'',addr:''};
			
			vm.getDevfrim(); // 设备厂商
			vm.getDevmodel(); // 设备型号
			
			vm.uploadfile('#uploadPic'); // 上传图片
			
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
            
            showlnglat(vm.monitordev.lng,vm.monitordev.lat);
            map.setZoom(15);
            $(".amap").show();
            
            // 设备厂商
			$.get("../basecode/selectByType/monitordevfrim", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				var _data = r.data;
				_data.forEach(function (e){
					if (e.id == vm.monitordev.devfrim) {
						e.selected = true;
					}
				});
				$('#devfrim').combobox({
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
			// 设备型号
			$.get("../basecode/selectByType/monitordevmodel", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				var _data = r.data;
				_data.forEach(function (e){
					if (e.id == vm.monitordev.devmodel) {
						e.selected = true;
					}
				});
				$('#devmodel').combobox({
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
			// 图片
			$("#showPic").attr({ src: Util.consts.downloadPath + "/" + vm.monitordev.pic + "/v4/w/75/h/75/"});
			$("#showPic").unbind("click");
			$("#showPic").bind("click", function() {
				vm.showpic(vm.monitordev.pic);
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
		saveOrUpdate: function (event) {
			if(validate.form()) {
				var url = vm.monitordev.id == null ? "../monitordev/save" : "../monitordev/update";
				// 如果是保存，则必须要上传图片，更新的话就不需要
				var isSave = vm.monitordev.id == null;
				if (isSave) {
					if (vm.monitordev.pic == null || vm.monitordev.pic == '') {
						layer.msg("必填，请选择图片", function(){});
						return;
					}
				}
				
				var _json = $(".form-horizontal").form2json();
				_json.id = vm.monitordev.id;
				_json.pic = vm.monitordev.pic;
				debugger
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
		del: function (id) {
			confirm('确定要删除选中的记录？', function(){
		    	$.get("../monitordev/delete/"+id, function(r){
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
	        // document.getElementById("lnglat").value = lng + ',' + lat;
	        vm.monitordev.lng = lng;
	        vm.monitordev.lat = lat;
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
			 vm.monitordev.addr = address;
		},
		getInfo: function(id){
			$.ajax({
			   type: "POST",
			   url: "../monitordev/info/"+id,
			   async: false,
			   success: function(r) {
				   vm.monitordev = r.monitordev;
			   }
			});
		},
		reload: function (event) {
			vm.showList = true;
			vm.showPic = false; // 显示图片
			vm.showUploadPic = false; // 显示上传图片的input组件
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData : {
					'qdevid' : vm.q.qdevid
				},
                page:page
            }).trigger("reloadGrid");
			$(".amap").hide();
		}
	}
});
