var ztreeCaptain;

var settingCaptain = {
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

var map, geolocation;
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
function select(e) {
    if (e.poi && e.poi.location) {
        map.setCenter(e.poi.location);
        showlnglat(e.poi.location.getLng(), e.poi.location.getLat());
    }
}

function showlnglat(lng, lat){
    map.clearMap();
    map.setZoom(18);
	map.setCenter([lng, lat]);
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
	 $("input[name='addrs']").val(address);
}

function deptTreeCaptain() {
	layer.open({
		type : 1,
		offset : '50px',
		skin : 'layui-layer-molv',
		title : "选择来源对象",
		area : [ '300px', '450px' ],
		shade : 0,
		shadeClose : false,
		content : jQuery("#deptLayer1"),
		btn : [ '确定', '取消' ],
		btn1 : function(index) {
			// 获取被勾选的选项
			var nodes = ztreeCaptain.getSelectedNodes();
			if(nodes.length > 0) {
				var node = nodes[0];
				if (node.userid) {
					$("input[name='userid']").val( node.userid );
					$("input[name='sourOject']").val( node.name );
					$("input[name='mobile']").val( node.id );
					layer.close(index);							
				} else {
					layer.msg("只能选择用户", function(){});
				}
			}

		}
	});
}

function save(){
	var r = Util.loadData("../cmddisp/addEvent", $(".form-horizontal").form2json());
	if(r.code == 0){
		alert("事件添加成功", function(index){
			location.reload();
		});
	} else {
		alert(r.msg);
	}
}

$(function () {
	$('#serviceType').combobox({
		readonly: false,
		data: Util.loadData("../basecode/selectByType/biztype").data,
		valueField:'no',
		textField:'cnm',
		editable: false,
		value:"请选择业务类型",
		panelHeight: 'auto',
	});
	$('#sourType').combobox({
		readonly: false,
		data: Util.loadData("../basecode/selectByType/caseSource").data,
		valueField:'no',
		textField:'cnm',
		editable: false,
		value:"请选择事件来源",
		panelHeight: 'auto',
		onChange:function(newValue, oldValue){
			//alert($('#sourType').combobox('getText')  );
			if(newValue != '0'){
				$("input[name='sourOject']").attr("readonly", "readonly");
				$("input[name='mobile']").attr("readonly", "readonly");
			} else {
				$("input[name='sourOject']").removeAttr("readonly");
				$("input[name='mobile']").removeAttr("readonly");
			}
			switch (newValue) {
			case '0':
				$.get("../citizen/getUserTree", function(r){
					if (r.code == 0) {
						ztreeCaptain = $.fn.zTree.init($("#deptTreeCaptain"), settingCaptain, r.userList);
					} else {
						ztreeCaptain = $.fn.zTree.init($("#deptTreeCaptain"), settingCaptain, null);
					}
				});
				break;
			case '1':
			case '3':
				$.get("../sys/department/selectForCmn", function(r){
					if (r.code == 0) {
						ztreeCaptain = $.fn.zTree.init($("#deptTreeCaptain"), settingCaptain, r.deptList);
					} else {
						ztreeCaptain = $.fn.zTree.init($("#deptTreeCaptain"), settingCaptain, null);
					}
				});
				break;
			case '2':
				$.get("../party/getPartyTree", function(r){
					if (r.code == 0) {
						ztreeCaptain = $.fn.zTree.init($("#deptTreeCaptain"), settingCaptain, r.userList);
					} else {
						ztreeCaptain = $.fn.zTree.init($("#deptTreeCaptain"), settingCaptain, null);
					}
				});
				break;

			default:
				ztreeCaptain = $.fn.zTree.init($("#deptTreeCaptain"), settingCaptain, null);
			}
	    }
	});
	$("input[name='sourOject']").dblclick(function(){
		if($('#sourType').val() != "请选择事件来源"){
			deptTreeCaptain();
		} else {
			layer.msg("请先选中一个事件来源。", function(){});
		}
	});
	$("input[name='sourOject']").change(function(){
		$("input[name='mobile']").val("");
		$("input[name='userid']").val("");
	});
	$("input[name='mobile']").change(function(){
		$("input[name='userid']").val("");
	});
});
