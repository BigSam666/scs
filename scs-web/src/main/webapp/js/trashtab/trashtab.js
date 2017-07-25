$(function () {
    $("#jqGrid").jqGrid({
        url: '../trashtab/list',
        datatype: "json",
        colModel: [			
			// 隐藏主键
			{ label: '主键id', name: 'id', width: 50, key: true ,hidden : true },
			{ label: '路段名', name: 'street', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '标签纸编号', name: 'no', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '经度', name: 'lng', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '纬度', name: 'lat', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '地址', name: 'addr', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '备注', name: 'rmk', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '图片', name: 'img', width: 80 , formatter: function(value, options, row){
					if (value==null) {
						return '无';
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
				{ label: '创建时间', name: 'crttm', width: 80 , formatter: function(value, options, row){
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
    
    map = new AMap.Map('container', {
	    resizeEnable: true,
	    center: [113.858708, 22.56675],
	    zoom: 10,
	});
	map.setDefaultCursor("pointer");
	var clickEventListener = map.on('click', function(e) {
		showlnglat(e.lnglat.getLng(), e.lnglat.getLat());
	});
	auto = new AMap.Autocomplete({
	    input: "input_id"
	});
	AMap.event.addListener(auto, "select", select);
	
});

var map;
var auto;

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
		q: {qstreet:""},
		showPic: false, // 显示图片
		showUploadPic: false, // 显示上传图片的input组件
		showList: true,
		upd: false,
		title: null,
		trashtab: {status:1,img:""}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			$('#pic').val('');
			vm.showPic = false;
			vm.showUploadPic = true;
			vm.showList = false;
			vm.upd = true;
			vm.title = "新增";
			vm.trashtab = {status:1};
			
			vm.trashtab.img = "";
			vm.uploadfile('#uploadPic'); // 上传图片
			
			$(".amap").show();
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
		uploadfile: function(element){
			$(element + " ~ div").remove();
			$(element).diyUpload({
		    	url: Util.consts.filePath,
		    	pick: {
		    		id:$(element),
		    		innerHTML: '请选择图片',
		    		multiple: true
		    	},
		    	fileNumLimit:5,
		    	success:function( data ) {
		    		if (vm.trashtab.img=="") {
		    			vm.trashtab.img = data.data[0];
		    		} else {
		    			vm.trashtab.img = vm.trashtab.img + "," + data.data[0];
		    		}
		    		debugger
		    	},
		    	error:function( err ) {
		    		alert("服务器忙，请稍候再试。");
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
            vm.showPic = true;
			vm.showUploadPic = false;
            
            vm.getInfo(id);
            
            // 照片
            debugger
            $("#showPic").empty();
            var img = vm.trashtab.img;
            if (img != null && img != '') {
            	var imgs = img.split(",");
            	for (i in imgs) {
            		$("#showPic").append('<img alt="" src="'+Util.consts.downloadPath + "/" + imgs[i] + "/v4/w/75/h/75/"+'" onclick="vm.showpic(\'' + imgs[i] + '\')">');
            	}
            } else {
            	$("#showPic").append('<input type="text" class="form-control" value="无" readonle="readonly">');
            }
            
            // 地图
            showlnglat(vm.trashtab.lng,vm.trashtab.lat);
            map.setZoom(14);
            $(".amap").show();

		},
		saveOrUpdate: function (event) {
			var url = vm.trashtab.id == null ? "../trashtab/save" : "../trashtab/update";
			var _json = $(".form-horizontal").form2json();
			_json['img'] = vm.trashtab.img;
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
		},
		del: function (id) {
			confirm('确定要删除选中的记录？', function(){
		    	$.get("../trashtab/delete/"+id, function(r){
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
			   url: "../trashtab/info/"+id,
			   async: false,
			   success: function(r) {
				   vm.trashtab = r.trashtab;
			   }
			});
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData : {
					'qstreet' : vm.q.qstreet
				},
                page:page
            }).trigger("reloadGrid");
			$(".amap").hide();
		}
	}
});
