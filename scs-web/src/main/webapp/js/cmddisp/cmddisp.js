/**
 *  _____       ___  ___       ___   _____    _____   _____   _   _____  __    __  _____   _____   _____    _     _   _   _____   _____  
 * /  ___/     /   |/   |     /   | |  _  \  |_   _| /  ___| | | |_   _| \ \  / / /  ___/ | ____| |  _  \  | |   / / | | /  ___| | ____| 
 * | |___     / /|   /| |    / /| | | |_| |    | |   | |     | |   | |    \ \/ /  | |___  | |__   | |_| |  | |  / /  | | | |     | |__   
 * \___  \   / / |__/ | |   / / | | |  _  /    | |   | |     | |   | |     \  /   \___  \ |  __|  |  _  /  | | / /   | | | |     |  __|  
 *  ___| |  / /       | |  / /  | | | | \ \    | |   | |___  | |   | |     / /     ___| | | |___  | | \ \  | |/ /    | | | |___  | |___  
 * /_____/ /_/        |_| /_/   |_| |_|  \_\   |_|   \_____| |_|   |_|    /_/     /_____/ |_____| |_|  \_\ |___/     |_| \_____| |_____| 
 * 
 */
 
/**
 * Dear maintainer:
 * 
 * Once you are done trying to 'optimize' this routine,
 * and have realized what a terrible mistake that was,
 * please increment the following counter as a warning
 * to the next guy:
 * 
 * total_hours_wasted_here = 40
 */
var marker,marker2, map = new AMap.Map("container", {
    resizeEnable: true,
    isHotspot: false,
    center: [116.397428, 39.90923]
});
var ii = 0;
var radius = 100;
var eventRow = new Array(), rows = new Array(), rows2 = new Array();
var markers = new Array(),marker2s = new Array();
var circle = new AMap.Circle({
    map: map,
	radius: radius,
	zIndex: 1,
    strokeColor: "#000000",
    strokeOpacity: 0,
    strokeWeight: 0,
    fillColor: "#3a6bdb",
    fillOpacity: 0.4
});

var setting = {
	data: {
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
	callback: {
		onClick: zTreeOnClick
	}
};
var setting2 = {
		data: {
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
		callback: {
			onClick: zTreeOnClick2
		}
};
var ztree;
$(function () {
    $("#jqGrid").jqGrid({
        url: '../event/list',
        datatype: "json",
        colModel: [			
			// 隐藏主键
			{ label: '主键id', name: 'id', width: 50, key: true ,hidden : true },
			{ label: '业务板块', name: 'busseg', width: 50, formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
			{ label: '来源', name: 'sour', width: 90 ,hidden : true, formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
			{ label: '任务类型', name: 'currentType', width: 90 ,hidden : true, formatter: function(value, options, row){
				return value == 'sjpd' ? "事件派单" : "复核派单";
			}}, 
			{ label: '当前环节', name: 'current', width: 80, hidden : true},
			{ label: '现场照片', name: 'qimg', width: 80, hidden : true},
			{ label: '详细地址', name: 'addr', width: 90 ,hidden : true}, 
			{ label: '精确地址', name: 'accaddr', width: 90 ,hidden : true}, 
			{ label: '纬度', name: 'lat', width: 50 ,hidden : true }, 
			{ label: '经度', name: 'lng', width: 50 ,hidden : true }, 
			{ label: '说明', name: 'qdescribe', width: 100 }, 
			{ label: '处理反馈', name: 'des', width: 100 }, 
			{ label: '时间', name: 'updtm', width: 100 }, 
			{ label: '操作', name: 'operation', width: 80, formatter: function(value, options, row){
				var oper = "<a onclick=\"javascript:dl_("+row.id+")\" style=\"cursor:pointer;\" >"+(row.currentType == 'sjpd' ? "事件派单" : "复核派单") + "</a>";
				addMarker(row);
				return oper;
			}},
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList : [10],
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
        	addMarkers();
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        },
        onSelectRow : function(id) {
        	openInfo($("#jqGrid").getRowData(id));
        },
        onPaging: function(pgButton) {
        	rows = new Array();
        	map.remove(markers);
        	markers = new Array();
        }
    });
    $("#jqGrid2").jqGrid({
		url: '../event/info',
		datatype: "json",
		colModel: [	
	       // 隐藏主键
	       { label: '用户id', name: 'id', width: 60, key: true ,hidden : true },
	       { label: '角色', name: 'rolename', width: 60 },
	       { label: '姓名', name: 'realname', width: 40},
	       { label: '待处理', name: 'pending', width: 40 }, 
	       { label: '已处理', name: 'ap', width: 40 }, 
	       { label: '距离(m)', name: 'distance', width: 50, formatter: function(value, options, row){
	    	   addMarker2(row);
	    	   return value;
	       }}, 
	       { label: 'name', name: 'name', width: 40 ,hidden : true},
	       { label: '邮箱', name: 'email', width: 40 ,hidden : true }, 
	       { label: '手机号码', name: 'mobile', width: 40 ,hidden : true }, 
	       { label: '纬度', name: 'lat', width: 50 ,hidden : true }, 
	       { label: '经度', name: 'lng', width: 50 ,hidden : true }, 
	       { label: '操作', name: 'operation', width: 40, formatter: function(value, options, row){
		       	var oper = "<a onclick=\"javascript:dl('"+row.id+"')\" style=\"cursor:pointer;\" >派单</a>";
			   	addMarker(row);
				return oper;
	       }},
       ],
       viewrecords: true,
       height: 340,
       rowNum: 10,
       rowList : [10],
       rownumbers: true, 
       rownumWidth: 25, 
       autowidth:true,
       multiselect: false,
       pager: "#jqGridPager2",
       jsonReader : {
    	   root: "page.list",
    	   page: "page.currPage",
    	   total: "page.totalPage",
    	   records: "page.totalCount",
       },
       prmNames : {
    	   page:"page", 
    	   rows:"limit", 
    	   order: "order",
       },
       gridComplete:function(){
    	   addMarkers2();
    	   $("#jqGrid2").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
       },
       onSelectRow : function(id) {
    	   openInfo2($("#jqGrid2").getRowData(id));
       },
       onPaging: function(pgButton) {
    	   rows2 = new Array();
    	   map.remove(marker2s);
    	   marker2s = new Array();
       }
	});
	$('#radius').combobox({
		readonly: false,
	    data: [{id:100,text:'0.1km'},{id:500,text:'0.5km'},{id:1000,text:'1km'},{id:1500,text:'1.5km'},{id:2000,text:'2km'},{id:3000,text:'3km'},{id:5000,text:'5km'},{id:10000,text:'10km'}],
	    valueField:'id',
	    textField:'text',
	    value: radius,
	    editable: false,
		panelHeight: 'auto',
		onChange:function(newValue, oldValue){
			radius = newValue
	    }
	});
	$('#serviceType').combobox({
		readonly: false,
		data: [{no:'', cnm:'所有'}].concat(Util.loadData("../basecode/selectByType/biztype").data),
		valueField:'no',
		textField:'cnm',
		editable: false,
		panelHeight: 'auto',
	});
	$('#btype').change(function(){
		if($(this).val() == 1){
			$(".additional").show();
		} else {
			$(".additional").hide();
		}
	});
	// 得到详细数据
	$("[name='handle_']").click(function(){
		$.get("../sys/department/selectForCmn", function(r) {
			ztree = $.fn.zTree.init($("#deptTree"), setting, r.deptList);
		});
		layer.open({
			type: 1,
			offset: '14%',
			skin: 'layui-layer-lan',
			title: "选择用户",
			area: ['300px', '450px'],
			shadeClose: true,
			content: $("#deptLayer"),
			btn: ['关闭'],
			btn1: function (index) {
				layer.close(index);
            }
		});
	});
	$("[name='auxiliary_']").click(function(){
		$.get("../sys/department/selectForCmn", function(r) {
			ztree = $.fn.zTree.init($("#deptTree"), setting2, r.deptList);
		});
		layer.open({
			type: 1,
			offset: '14%',
			skin: 'layui-layer-lan',
			title: "选择用户",
			area: ['300px', '450px'],
			shadeClose: true,
			content: $("#deptLayer"),
			btn: ['关闭'],
			btn1: function (index) {
				layer.close(index);
			}
		});
	});
});
function addMarker(row){
	rows.push(row);
}
function addMarker2(row){
	rows2.push(row);
}
function addMarkers(){
	for(var i=0; i< rows.length; i++){
		marker = new AMap.Marker({
	        position: [rows[i].lng, rows[i].lat],
	        icon: 'http://webapi.amap.com/theme/v1.3/markers/n/mark_r'+(i+1)+'.png',
	        topWhenClick: true,
	        zIndex: 50,
	    });
		marker.content = rows[i];
	    marker.setMap(map);
	    marker.on('click', markerClick);
        markers.push(marker);
	}
	map.setFitView(markers);
}
function addMarkers2(){
	map.remove(marker2s);
	for(var i=0; i< rows2.length; i++){
		if(rows2[i].lng && rows2[i].lat){
			marker2 = new AMap.Marker({
				position: [rows2[i].lng, rows2[i].lat],
				icon: 'http://webapi.amap.com/theme/v1.3/markers/n/mark_b'+(i+1)+'.png',
				topWhenClick: true,
				animation: 'AMAP_ANIMATION_DROP',
		        zIndex: 60,
			});
			marker2.content = rows2[i];
			marker2.setMap(map);
			marker2.on('click', markerClick2);
			marker2s.push(marker2);
		}
	}
}
function openInfo(row) {
	var info = [];
	info.push("<div>");
    info.push("<div style=\"text-align:center;\"><b>"+row.busseg+"----"+row.updtm+"</b></div><br />");
    info.push("<div><b>任务来源 : </b>"+row.sour+"</div>");
    info.push("<div><b>任务地点 : </b>"+row.addr+"</div>");
    info.push("<div><b>任务详情 : </b>"+row.qdescribe+"</div>");
    info.push("<div><a onclick=\"javascript:dl()\" style=\"cursor:pointer;\" >派单</a></div>");
    info.push("</div>");
    infoWindow = new AMap.InfoWindow({
        content: info.join(""),
        offset: new AMap.Pixel(0, -30),
    });
    // 把主表所选中的数据保存起来，等待调用
    eventRow = row;
	getGrid2(row.id);
	circle.setRadius(radius);
	circle.setCenter([row.lng, row.lat]);
	map.setCenter([row.lng, row.lat]);
    infoWindow.open(map, [row.lng, row.lat]);
}
function markerClick(e) {
	row = e.target.content;
	row.busseg = $.isPlainObject(row.busseg) ? row.busseg.value : row.busseg ;
	row.sour = $.isPlainObject(row.sour) ? row.sour.value : row.sour ;
	openInfo(row);
}
function openInfo2(row) {
	var info = [];
	info.push("<div>");
	info.push("<div style=\"text-align:center;\"><b>"+row.rolename+"</b></div><br />");
	info.push("<div><b>姓名 : </b>"+row.realname+"</div>");
    info.push("<div><b>手机号码 : </b>"+row.mobile+"</div>");
    info.push("<div><b>待处理 : </b>"+row.pending+"</div>");
    info.push("<div><b>已处理 : </b>"+row.ap+"</div>");
	info.push("<div><a onclick=\"javascript:dl('"+row.id+"')\" style=\"cursor:pointer;\" >派单</a></div>");
	info.push("</div>");
	infoWindow = new AMap.InfoWindow({
		content: info.join(""),
		offset: new AMap.Pixel(0, -30),
	});
	infoWindow.open(map, [row.lng, row.lat]);
}
function markerClick2(e) {
	row = e.target.content;
	openInfo2(row);
}
function query(){
	rows = new Array();
	map.remove(markers);
	markers = new Array();
	var page = $("#jqGrid").jqGrid('getGridParam','page');
	$("#jqGrid").jqGrid('setGridParam',{ 
        postData:{'busseg': $("[name='busseg']").val(), 'expl': $("[name='expl']").val()},
        page:page
    }).trigger("reloadGrid");
}
function getGrid2(id){
	rows2 = new Array();
	map.remove(marker2s);
	marker2s = new Array();
	var page = $("#jqGrid2").jqGrid('getGridParam','page');
	$("#jqGrid2").jqGrid('setGridParam',{ 
        postData:{'radius': radius, 'id': id},
        page: page
    }).trigger("reloadGrid");
}
function dl_(id){
	openInfo($("#jqGrid").getRowData(id));
	dl();
}
function dl(userid){
	for ( var name in eventRow) {
		if(name == 'qimg'){
			if(eventRow[name]){
				imgs = eventRow[name].split(',');
				s = "";
				for(var i in imgs){
					s += "<img src='"+Util.consts.downloadPath+"/"+imgs[i]+"/v4/w/30/h/30' onclick='showPic(\""+imgs[i]+"\")' />&nbsp;&nbsp;"
				}
				$(".dl").find("[name='img']").html(s);
			}
		} else {
			$(".dl").find("[name='" + name+ "']").text(eventRow[name]);
			$(".dl").find("[name='" + name+ "']").val(eventRow[name]);
		}
	}
	marker2 = new AMap.Marker({
		position: [eventRow.lng, eventRow.lat],
		icon: 'http://webapi.amap.com/theme/v1.3/markers/n/mark_r.png',
		animation: 'AMAP_ANIMATION_DROP',
	});
	var map2 = new AMap.Map("container2", {
	    resizeEnable: true,
	    isHotspot: false,
	    center: marker2.getPosition(),
	    zoom: 18
	});
	marker2.setMap(map2);
	layer.open({
	  type: 1,
	  skin: 'layui-layer-molv',
	  title: '派单',
	  shadeClose: true,
	  area: ['40%', '80%'],
	  content: $(".dl")
	});
}
function showPic(src){
	if(src){
		top.layer.open({
		  type: 1,
		  title: '图片查看',
		  shadeClose: true,
		  area: ['600px', '400px'],
		  content: "<a href='"+Util.consts.downloadPath+"/"+src+"' download='"+src+"'><img src='"+Util.consts.downloadPath+"/"+src+"/v4/w/600/h/358' /> </a>"
		});
	}
}
function zTreeOnClick(event, treeId, treeNode) {
	if(treeNode.userid){
		$("[name='handle']").val(treeNode.userid);
		$("[name='handle_']").val(treeNode.name);						
	} else {
		layer.msg("处理人应该是一个人，而不是一个部门或者组织", function(){});
	}
}
function zTreeOnClick2(event, treeId, treeNode) {
	if(treeNode.userid){
		$("[name='auxiliary']").val(treeNode.userid);
		$("[name='auxiliary_']").val(treeNode.name);						
	} else {
		layer.msg("辅办人应该是一个人，而不是一个部门或者组织", function(){});
	}
}
function sub(){
	if($('#btype').val() == 1){
		if(!$("[name='estimatetm']").val()){
			layer.msg("派单情况下请选择预计完成时间", function(){});
			return false;
		}
		if(!$("[name='handle']").val()){
			layer.msg("派单情况下请选择处理人", function(){});
			return false;
		}
		if($("[name='handle']").val() == $("[name='auxiliary']").val()){
			layer.msg("处理人和辅办人不能是同一人", function(){});
			return false;
		}
	}
	data = Util.loadData("../cmddisp/savecmddisp", $( "#cmddisp" ).form2json());
	alert('操作成功', function(index){
		location.reload();
	});
}