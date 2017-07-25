var vm = new Vue({
	el : '#row',
	data : {
		callpopsel:{
			source:'',
			name:'',
			type:'',
			phone:'',
			adds:'',
			content:'',
			img:'',
			date:''
		}
	},
	methods : {
		save : function() {
			if (validate.form()) {
			 //文件上传队列文件数量判断
			 var uploaderdata = $('#imgurl').data('zui.uploader');
			 var imgfiles = uploaderdata.getTotal().queued;
			 if(imgfiles !=0){
				 layer.msg('您有'+imgfiles+'张图片没有上传,请上传后提交!', function(){});
			 }else{
				//置灰提交按钮
				 $('.submit .btn-success').attr('disabled',true);
				 $('.submit .btn-success').text('已提交');
				 //填充字段
				 vm.callpopsel.date = Date.parse(new Date());
				 $.ajax({
			        	type : 'POST',
			        	url : '../callpopsel/save',
			        	data:JSON.stringify(vm.callpopsel),
			        	contentType: "application/json",
			        	async:false,
			        	success : function(s) {
			        		if(!$.isPlainObject(s))
			        		var s = JSON.parse(s);
			        		if(s.code == 0){
			        			layer.msg('保存成功!', function(){});
			        		}else{
			        			layer.msg('未知异常,请联系管理员!', function(){});
			        		}
			        	}
			        });
			 	}
			}
		},
	}
});
// 表单验证
validate = $(".form-horizontal").validate({
	onfocusout : false,
	onkeyup : false,
	rules : {
		source : {
			required : true,
			chinese:true
		},
		name : {
			required : true,
			chinese:true,
			rangelength:[2,5]
		},
		type : {
			required : true,
			chinese:true
		},
		phone : {
			required : true,
			digits:true,
			mobile:true
		},
		adds : {
			required : true,
			maxlength: 50
		},
		content : {
			required : true,
			maxlength: 100
		}
	},
	messages : {
		source : {
			required : "来源必选!"
		},
		name : {
			required : "姓名必填!"
		},
		type : {
			required : "类型必填!"
		},
		phone : {
			required : "电话必填!"
				
		},
		adds : {
			required : "地点必填!"
		},
		content : {
			required : "内容必填!"
		}
	},
	showErrors : function(errorMap, errorList) {
		if (errorList && errorList.length > 0) { // 如果存在错误信息
			layer.msg(errorList[0].message, function() {
			});
		}
	}
});

// 图片上传验证
var uploader = $('#imgurl').uploader({
	url:Util.consts.filePath,
	filters:{
	    mime_types: [
	        {title: '图片', extensions: 'jpg,gif,png'},
	    ],
	    max_file_size: '5mb',
	    prevent_duplicates: true
	},
	limitFilesCount:5,
	responseHandler:function(responseObject, file){
		var filename = JSON.parse(responseObject.response).data[0];
		vm.callpopsel.img += filename +',';
	}
});

//高德地图
var lnglatXY,map,geolocation,listMap;
map = new AMap.Map("container", {
    resizeEnable: true,
    zoom: 17
});
//为地图注册click事件获取鼠标点击出的经纬度坐标
var clickEventListener = map.on('click', function (e) {
    getAddress(lnglatXY = [e.lnglat.getLng(), e.lnglat.getLat()]);
});

//输入提示
var autoOptions = {
	input: "listMap"

};
var auto = new AMap.Autocomplete(autoOptions);

var placeSearch = new AMap.PlaceSearch({
	map: map
}); //构造地点查询类
AMap.event.addListener(auto, "select", select); //注册监听，当选中某条记录时会触发

function select(e) {
	listMap = e.poi.district + e.poi.name;
	setTimeout(function () {
		$('#listMap').val(listMap)
	}, 300);
}

function queryMap() {
	placeSearch.search($('#listMap').val()); //关键字查询查询
}

AMap.service('AMap.Geocoder', function () {
    geocoder = new AMap.Geocoder({
        extensions: 'all'
    });
});

function getAddress(lnglatXY) {
    geocoder.getAddress(lnglatXY, function (status, result) {
        if (status === 'complete' && result.info === 'OK') {
        	vm.callpopsel.adds = (result.regeocode.formattedAddress);
        } else {
        	layer.msg('获取地址失败!', function(){});
        }
    });
}

(function () {
    map.plugin('AMap.Geolocation', function () {
        geolocation = new AMap.Geolocation({
            enableHighAccuracy: true, //是否使用高精度定位，默认:true
            timeout: 10000, //超过10秒后停止定位，默认：无穷大
            maximumAge: 0, //定位结果缓存0毫秒，默认：0
            convert: true, //自动偏移坐标，偏移后的坐标为高德坐标，默认：true
            showButton: false, //显示定位按钮，默认：true
            showMarker: true, //定位成功后在定位到的位置显示点标记，默认：true
            showCircle: true, //定位成功后用圆圈表示定位精度范围，默认：true
            panToLocation: true, //定位成功后将定位到的位置作为地图中心点，默认：true
            zoomToAccuracy: true //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
        });
        map.addControl(geolocation);
        geolocation.getCurrentPosition();
    });
})();






