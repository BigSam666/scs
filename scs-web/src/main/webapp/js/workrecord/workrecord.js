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
        url: '../workrecord/list',
        datatype: "json",
        colModel: [			
			// 隐藏主键
			{ label: '主键id', name: 'id', width: 50, key: true ,hidden : true },
			{ label: '创建人', name: 'realname', width: 90 }, 
			{ label: '所属部门', name: 'deptNames', width: 90 }, 
			
			{ label: '业务板块', name: 'busseg', width: 50 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}},  
			{ label: '现场照片', name: 'img', width: 80, formatter: function(value, options, row){
				imgs = value.split(',');
				s = "";
				if(value){
					for(var i in imgs){
						s += "<img src='"+Util.consts.downloadPath+"/"+imgs[i]+"/v4/w/30/h/30' onclick='vm.showPic(\""+imgs[i]+"\")' />&nbsp;&nbsp;"
					}
				} else {
					s = "无";
				}
				return s;
			}},
			{ label: '详细地址', name: 'addr', width: 90 }, 
			{ label: '精确地址', name: 'accaddr', width: 90 }, 
			/*{ label: '纬度', name: 'lat', width: 50 }, 
			{ label: '经度', name: 'lng', width: 50 }, */
			{ label: '说明', name: 'expl', width: 100 }, 
			{ label: '创建时间', name: 'crttm', width: 80 }, 
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
        rownumbers: true, 
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
		q:{
			condition:''
		},
		showList: true,
		upd: false,
		title: null,
		url_: Util.consts.filePath,
		imgs: '',
		workrecord: {status:1,img:[],addr:'',sour:''},
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.upd = true;
			vm.title = "新增";
			vm.imgs = new Array(),
			vm.workrecord = {status:1,img:'',busseg:1,addr:'',sour:''};
			vm.fileupload("#img");
			AMap.event.addListener(auto, "select", vm.select);
			$(".amap").show();
		},
		update: function (id, isupdate) {
			if(id == null){
				return ;
			}
			vm.showList = false;
			vm.upd = isupdate;
            vm.title = isupdate ? "修改" : "查看";
            vm.getInfo(id);
			vm.fileupload("#img");
			AMap.event.addListener(auto, "select", vm.select);
			$(".amap").show();
		},
		showPic: function(src){
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
		fileupload: function (selector){
			$(selector).diyUpload({
		    	url: Util.consts.filePath,
		    	success:function( r ) {
		    		vm.imgs.push(r.data[0]);
		    		vm.workrecord.img = vm.imgs.toString();
		    	},
		    	error:function( err ) {
		    		alert(err);
		    	},
		    	buttonText : '选择现场图片',
		    });
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
			 vm.workrecord.addr = address;
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
	        vm.workrecord.lng = lng;
	        vm.workrecord.lat = lat;
	    	var marker = new AMap.Marker({
	            map: map,
	            position: [lng, lat],
	            icon: new AMap.Icon({            
		            image: "http://webapi.amap.com/theme/v1.3/markers/n/mark_r.png",
		        })
	        });
	    	//marker.setAnimation('AMAP_ANIMATION_BOUNCE');
	        marker.setAnimation('AMAP_ANIMATION_DROP');
	        vm.regeocoder([lng, lat]);
	    },
		saveOrUpdate: function (event) {
			var url = vm.workrecord.id == null ? "../workrecord/save" : "../workrecord/update";
			data = JSON.stringify(vm.workrecord);
			$.ajax({
				type: "POST",
			    url: url,
			    data: data,
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
		    	$.get("../workrecord/delete/"+id, function(r){
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
			$.get("../workrecord/info/"+id, function(r){
                vm.workrecord = r.workrecord;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'condition': vm.q.condition
				},
                page:page
            }).trigger("reloadGrid");
			$(".amap").hide();
		}
	}
});
