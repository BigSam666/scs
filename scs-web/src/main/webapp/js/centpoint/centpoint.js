var map, geolocation,radius = 2000, lng1, lat1;
map = new AMap.Map('container', {
    resizeEnable: true,
    zoom: 10,
});
var circle = new AMap.Circle({
	radius: radius,
	zIndex: 1,
    strokeColor: "#000000",
    strokeOpacity: 0,
    strokeWeight: 0,
    fillColor: "#3a6bdb",
    fillOpacity: 0.4
});
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
	lng1 = lng;
	lat1 = lat
    map.clearMap();
    $("input[name='lng']").val( lng1 );
    $("input[name='lat']").val( lat1 );
	var marker = new AMap.Marker({
        map: map,
        position: [lng1, lat1],
        icon: new AMap.Icon({    
            image: "http://webapi.amap.com/theme/v1.3/markers/n/mark_r.png",
        })
    });
    marker.setAnimation('AMAP_ANIMATION_BOUNCE');
    circle.setRadius(radius);
	circle.setCenter([lng1, lat1]);
    circle.setMap(map);
	map.setCenter([lng1, lat1]);
    map.setZoom(18);
}


$(function () {
    $("#jqGrid").jqGrid({
        url: '../centpoint/list',
        datatype: "json",
        colModel: [			
			// 隐藏主键
			{ label: '主键id', name: 'id', width: 50, key: true ,hidden : true },
			{ label: '项目部', name: 'name', width: 80}, 
			{ label: '负责业务', name: 'busText', width: 80}, 
			{ label: '项目描述', name: 'des', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
			{ label: '经度', name: 'lng', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
			{ label: '维度', name: 'lat', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
			{ label: '半径', name: 'radius', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value + "公里";
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
	$('#serviceType').combobox({
		readonly: false,
		data: Util.loadData("../basecode/selectByType/biztype").data,
		valueField:'no',
		textField:'cnm',
		editable: false,
		multiple: true,
		panelHeight: 'auto',
	});
	$('#radiusList').combobox({
		readonly: false,
		data: Util.loadData("../basecode/selectByType/cpRadius").data,
		valueField:'no',
		textField:'cnm',
		value:'2',
		editable: false,
		panelHeight: 'auto',
		onChange:function(newValue, oldValue){
		    circle.setRadius(newValue * 1000);
			circle.setCenter([lng1, lat1]);
		    circle.setMap(map);
	    }
	});
});

var setting = {
	data: {
		simpleData: {
			enable: true,
			idKey: "id",
			pIdKey: "pid",
			rootPId: -1
		},
		key: {
			url:"nourl"
		}
	},
	callback: {
		onClick: zTreeBeforeCheck
	}
};
var ztree;

function zTreeBeforeCheck(event, treeId, treeNode){
	if(treeNode.otype == '3'){
		return true;
	} else {
		layer.msg('只能选择一个项目部', function(){});
		return false;
	}
}

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		upd: false,
		title: null,
		bizList:{},
		centpoint: {status:1,}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.upd = true;
			vm.title = "新增";
			vm.bizList = {};
			vm.centpoint = {status:1};
			this.getBizList();
			this.getDept(null);
			// 清空地图，
		    map.clearMap();
			$("div[name='amap']").show();
		},
		update: function (id, isupdate) {
			if(id == null){
				return ;
			}
			vm.showList = false;
			vm.upd = isupdate;
            vm.title = isupdate ? "修改" : "查看";
            this.getBizList();
			this.getDept(id);
			// 清空地图，
		    map.clearMap();
			$("div[name='amap']").show();
		},
		getBizList: function(){
			$.get("../basecode/selectByType/biztype", function(r){
				vm.bizList = r.data;
			});
		},
		getDept: function(id){
			$.get("../sys/department/select", function(r){
				ztree = $.fn.zTree.init($("#deptTree"), setting, r.deptList);
    			if(id != null){
					vm.getInfo(id);
				}
			})
		},
		saveOrUpdate: function (event) {
			var url = vm.centpoint.id == null ? "../centpoint/save" : "../centpoint/update";
			vm.centpoint.bus = $('#serviceType').combobox('getValues').toString();
			vm.centpoint.radius = $('#radiusList').combobox('getValue');
			vm.centpoint.lng = $("input[name='lng']").val();
			vm.centpoint.lat = $("input[name='lat']").val();
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.centpoint),
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
		deptTree: function(){
			layer.open({
				type: 1,
				offset: '50px',
				skin: 'layui-layer-molv',
				title: "选择部门/角色",
				area: ['300px', '450px'],
				shade: 0,
				shadeClose: false,
				content: jQuery("#deptLayer"),
				btn: ['确定', '取消'],
				btn1: function (index) {
					var nodes = ztree.getSelectedNodes();
					if (nodes.length > 0 && nodes[0].pid != -1) {
						vm.centpoint.proj = nodes[0].id.toString();
						$("input[name='proj']").val(nodes[0].name.toString());
						layer.close(index);
					} else {
						layer.msg('请选择一个部门', function(){});
					}
	            }
			});
		},
		del: function (id) {
			confirm('确定要删除选中的记录？', function(){
		    	$.get("../centpoint/delete/"+id, function(r){
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
			$.get("../centpoint/info/"+id, function(r){
                vm.centpoint = r.centpoint;
    			var proj = vm.centpoint.proj;
				var node = ztree.getNodeByParam("id", proj);
				$("input[name='proj']").val(node.name.toString());
				ztree.checkNode(node, true, true);
				$('#serviceType').combobox('setValues', vm.centpoint.bus.split(','));
				$('#radiusList').combobox('setValue', vm.centpoint.radius);
				circle.setRadius(vm.centpoint.radius * 1000);
				circle.setCenter([vm.centpoint.lng, vm.centpoint.lat]);
			    circle.setMap(map);
			    marker = new AMap.Marker({
			        map: map,
			        position: [vm.centpoint.lng, vm.centpoint.lat],
			        icon: new AMap.Icon({    
			            image: "http://webapi.amap.com/theme/v1.3/markers/n/mark_r.png",
			        })
			    });
			    marker.setAnimation('AMAP_ANIMATION_BOUNCE');
				map.setCenter([vm.centpoint.lng, vm.centpoint.lat]);
			    map.setZoom(18);
            });
		},
		reload: function (event) {
			vm.showList = true;
			$("div[name='amap']").hide();
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});
