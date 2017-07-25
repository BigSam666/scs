/**
 *  .--,       .--,
 * ( (  \.---./  ) )
 *  '.__/o   o\__.'
 *     {=  ^  =}
 *      >  -  <
 *     /       \
 *    //       \\
 *   //|   .   |\\
 *   "'\       /'"_.-~^`'-.
 *      \  _  /--'         `
 *    ___)( )(___
 *   (((__) (__)))    高山仰止,景行行止.虽不能至,心向往之。
 *   
 */
var map = new AMap.Map("container", {
    resizeEnable: true,
    center: [116.403322, 39.900255],
    zoom: 13
});
var mouseTool = new AMap.MouseTool(map);
var editor={};
var usernmList,userList;
AMap.event.addListener( mouseTool,'draw',function(e){
	mouseTool.close();
	vm.cleangridmng.lnglatList = new Array();
	for(var i = 0; i < e.obj.getPath().length; i++){
		vm.cleangridmng.lnglatList.push(e.obj.getPath()[i].lng + "," + e.obj.getPath()[i].lat);
	}
	$.log(vm.cleangridmng.lnglatList);
});
$(function () {
    $("#jqGrid").jqGrid({
        url: '../gridmng/cleanList',
        datatype: "json",
        colModel: [			
			// 隐藏主键
			{ label: '网格ID', name: 'id', width: 50, hidden: true, key: true },
			{ label: '网格名称', name: 'name', width: 80 }, 
			{ label: '创建时间', name: 'crttm', width: 80 }, 
			{ label: '操作', name: 'operation', width: 40, formatter: function(value, options, row){
				var oper = "<a onClick=\"javascript:vm.update('"+row.id+"', false);\" title='查看' ><i class=\"fa fa-info-circle\" style='color:violet;'></i></a>&nbsp;&nbsp;";
				oper += update ? "<a onClick=\"javascript:vm.update('"+row.id+"', true);\" title='修改'  ><i class=\"fa fa-pencil-square-o\" style='color:green;'></i></a>&nbsp;&nbsp;":"";
				oper += del ? "<a onClick=\"javascript:vm.del('"+row.id+"');\" title='删除'  ><i class=\"fa fa-trash-o\" style='color:red;'></i></a>" : "";
				vm.fullmap(row);
				return oper;
			}},
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 40, 
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
        },
    });
    jQuery("#jqGrid").jqGrid('setLabel',0 , '序号', 'labelstyle');
    //vm.getMylocation();
	vm.getDept();
	// 表单验证 开始
    validate = $(".form-horizontal").validate({
  	   onfocusout: false,
 	   onkeyup: false,
 	   rules: {
 		   name: {
 			   rangelength: [3,10],
 			   required: true
 		   },
 		   uid: {
 			   required: true
 		   },
 	   },
 	   messages:{
 		   name:{
 		       rangelength: $.validator.format("网格名称请输入 {0} 到 {1} 个字符."),
 		       required: "必填，请输入网格名称"
 		   },
 		   uid: {
 			   required: "必填，请选择对应用户"
 		   },
 	   },
 	  showErrors: function(errorMap, errorList) {
			if ( errorList && errorList.length > 0 ) {  //如果存在错误信息
				layer.msg(errorList[0].message, function(){});
			}
		}
    });
    // 表单验证结束
    // 获取我当前项目部位置；
    data = Util.loadData("../gridmng/getUserProjectMsg").data;
	$('#project').combobox({
		readonly: false,
		data: data,
		valueField:'id',
		textField:'name',
		value: data[0].id,
		editable: false,
		panelHeight: 'auto',
		onChange:function(newValue, oldValue){
			// 获取一次
			r = Util.loadData("../centpoint/infoByProject/"+newValue).centpoint;
			if(r){
				map.setCenter([r.lng, r.lat]);
			} else {
				map.setCenter([116.403322, 39.900255]);
			}
		    map.setZoom(15);
		    vm.reload();
	    }
	});
	// 获取一次
	r = Util.loadData("../centpoint/infoByProject/"+data[0].id).centpoint;
	if(r){
		map.setCenter([r.lng, r.lat]);
	} else {
		map.setCenter([116.403322, 39.900255]);
	}
    map.setZoom(15);
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
	check:{
		enable:true,
		nocheckInherit:true
	}
};
var ztree;

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		upd: true,
		title: null,
		cleangridmng: {status:'1', lnglatList:[], uid:'', unm:'' },
		showstatus: true,
		accurate: false,
		value: "绘制"
	},
	methods: {
		query: function () {
			vm.reload();
		},
		update: function (id, isupdate) {
			if(id == null){
				return ;
			}
			vm.upd = isupdate;
			if(isupdate){
				$("[name='status']").attr('disabled', 'disabled');
			} else {
				$("[name='status']").removeAttr('disabled');
			}
            vm.title = isupdate ? "修改" : "查看";
			vm.getInfo(id);
		},
		getDept: function(){
			$.get("../sys/department/selectForCmn", function(r){
				if(r.code!=0){
					alert(r.msg);
					vm.reload();
				}
				ztree = $.fn.zTree.init($("#deptTree"), setting, r.deptList);
			})
		},
		saveOrUpdate: function (event) {
			if(validate.form()){
				if(vm.cleangridmng.lnglatList != ''){
					var url = vm.cleangridmng.id == null ? "../gridmng/save" : "../gridmng/update";
					vm.cleangridmng.dept = $('#project').combobox('getValue');
					vm.cleangridmng.id != null ? vm.closeEditPolygon() : accurate ? vm.closeEditPolygon2() : '';
					$.ajax({
						type: "POST",
					    url: url,
					    data: JSON.stringify(vm.cleangridmng),
					    success: function(r){
					    	if(r.code === 0){
								alert('操作成功', function(index){
									location=location;
								});
							}else{
								alert(r.msg);
							}
						}
					});
				} else {
		    		layer.msg('请在地图中画一个网格。', function(){});
		    	}
			}
		},
		getMylocation: function(){
	    	map.plugin('AMap.Geolocation', function() {
		        geolocation = new AMap.Geolocation({
		            enableHighAccuracy: true,
		            timeout: 10000, 
		            buttonOffset: new AMap.Pixel(10, 20),
		            zoomToAccuracy: true,
		            buttonPosition:'LB'
		        });
		        map.addControl(geolocation);
		        geolocation.getCurrentPosition();
		    });
	    },
	    change_: function() {
	    	vm.cleangridmng.lnglatList = '';
	    	vm.cleangridmng.uid = '';
	    	vm.cleangridmng.unm = '';
	    	vm.cleangridmng.name = '';
	    	if(vm.value!="绘制"){
	    		vm.value = "绘制";
	    		vm.reload();
	    	}
		},
		clear: function(){
			map.clearMap();
		},
		fullmap: function(row){
			lng_ = row.lon.split(',');
			lat_ = row.lat.split(',');
			lnglats = new Array();
			for(var i=0;i<lng_.length;i++){
				lnglat = new Array();
				lnglat.push(lng_[i]);
				lnglat.push(lat_[i]);
				lnglats.push(lnglat);
			}
			editor._polygon=(function(){
		        return new AMap.Polygon({
		            map: map,
		            path: lnglats,
		            strokeColor: "#ff0000",
		            strokeOpacity: 1,
		            strokeWeight: 1,
		            fillColor: "#ffffff",
		            fillOpacity: 0.35
		        });
		    })();
			map.setFitView();
		},
		startEditPolygon: function(){
			editor._polygonEditor.open();
		},
		closeEditPolygon: function(){
			vm.cleangridmng.lnglatList = new Array();
			//temp = editor._polygonEditor.Me.getPath();
			temp = editor._polygonEditor.nl[0];
			for(var i = 0; i < temp.length; i++){
				vm.cleangridmng.lnglatList.push(temp[i].lng + "," + temp[i].lat);
			}
			editor._polygonEditor.close();
		},
		startEditPolygon2: function(){
			editor._polygonEditor2.open();
		},
		closeEditPolygon2: function(){
			vm.cleangridmng.lnglatList = new Array();
			temp = editor._polygonEditor2.Me.getPath();
			for(var i = 0; i < temp.length; i++){
				vm.cleangridmng.lnglatList.push(temp[i].lng + "," + temp[i].lat);
			}
			editor._polygonEditor2.close();
		},
	    generateGrid: function(){
	    	if(vm.cleangridmng.lnglatList){
				$.ajax({
					type: "POST",
				    url: "../gridmng/generateGrid",
				    data: JSON.stringify(vm.cleangridmng),
				    success: function(r){
				    	if(r.code === 0){
				    		layer.msg('网格生成完成，请按照需求进行修改。');
							vm.generateEdit(r.data);
						}else{
							alert(r.msg);
						}
					}
				});
	    	} else {
	    		layer.msg('请在地图中画一个网格。', function(){});
	    	}
	    },
	    addmouse: function(_this){
	    	if(vm.value=="绘制"){
	    		status = vm.cleangridmng.status;
	    		if(status=='1'){
	    			mouseTool.polygon();
	    			accurate = false;
	    		} else {
	    			mouseTool.polyline();
	    			accurate = true;
	    		}
	    		vm.value="重新绘制";
	    	} else {
	    		vm.value="绘制";
				vm.reload();
	    	}
	    },
	    amapedit: function(cleangridmng){
	    	lng_ = cleangridmng.lon.split(',');
			lat_ = cleangridmng.lat.split(',');
			lnglats = new Array();
			for(var i=0;i<lng_.length;i++){
				lnglat = new Array();
				lnglat.push(lng_[i]);
				lnglat.push(lat_[i]);
				lnglats.push(lnglat);
			}
			editor._polygon_edit=(function(){
		        return new AMap.Polygon({
		            map: map,
		            path: lnglats,
		            strokeColor: "#0000ff",
		            strokeOpacity: 1,
		            strokeWeight: 1,
		            fillColor: "#ff0000",
		            fillOpacity: 0.35
		        });
		    })();
			editor._polygonEditor = new AMap.PolyEditor(map, editor._polygon_edit);			
			map.setFitView(editor._polygon_edit);
			if(vm.upd){
				vm.startEditPolygon();
			}
	    },
	    generateEdit: function(data){
	    	$.log(JSON.stringify(data));
	    	editor._polygon_edit2=(function(){
	    		return new AMap.Polygon({
	    			map: map,
	    			path: data,
	    			strokeColor: "#0000ff",
	    			strokeOpacity: 1,
	    			strokeWeight: 1,
	    			fillColor: "#ff0000",
	    			fillOpacity: 0.35
	    		});
	    	})();
	    	editor._polygonEditor2 = new AMap.PolyEditor(map, editor._polygon_edit2);			
	    	map.setFitView(editor._polygon_edit2);
	    	if(vm.upd){
	    		vm.startEditPolygon2();
	    	}
	    },
		del: function (id) {
			confirm('确定要删除选中的记录？', function(){
		    	$.get("../gridmng/delete/"+id, function(r){
					if(r.code == 0){
						$("#jqGrid").trigger("reloadGrid");
						top.layer.closeAll();
						vm.reload();
					}else{
						alert(r.msg);
					}
				});
			});
		},
		getInfo: function(id){
			vm.reload();
			setTimeout(function(){
				url = "../gridmng/info/"+id;
				$.get(url, function(r){
	                vm.cleangridmng = r.gridmng;
	                vm.amapedit(r.gridmng);
					var usernmList = new Array();
					var userList = new Array();
	    			var userid = r.gridmng.uid.split(',');
	    			for(var i=0; i<userid.length; i++) {
	    				var node = ztree.getNodeByParam("userid", userid[i]);
	    				ztree.checkNode(node, true, true);
	    			}
					var nodes = ztree.getCheckedNodes(true);
					for(var i=0; i<nodes.length; i++) {
						if(nodes[i].userid){
							if ( userList.indexOf(nodes[i].userid) ==-1) {
								userList.push(nodes[i].userid);
								usernmList.push(nodes[i].name);
							}
						}
					}
					vm.cleangridmng.uid = userList.toString();
					vm.cleangridmng.unm = usernmList.toString();
					vm.cleangridmng.status = '1';
	            });
			}, 100)
		},
		deptTree: function(){
			layer.open({
				type: 1,
				offset: '50px',
				skin: 'layui-layer-molv',
				title: "选择用户",
				area: ['300px', '450px'],
				shade: 0,
				shadeClose: false,
				content: jQuery("#deptLayer"),
				btn: ['确定', '取消'],
				btn1: function (index) {
					//获取选择的菜单
					var nodes = ztree.getCheckedNodes(true);
					userList = new Array();
					usernmList = new Array();
					for(var i=0; i<nodes.length; i++) {
						if(nodes[i].userid){
							if ( userList.indexOf(nodes[i].userid) ==-1) {
								if(userList.length>=99){
									alert("网格用户不能大于100个");
								} else {
									userList.push(nodes[i].userid);
									usernmList.push(nodes[i].name);
								}
							}
						}
					}
					vm.cleangridmng.uid = userList.toString();
					vm.cleangridmng.unm = usernmList.toString();
					layer.close(index);
	            }
			});
		},
		reload: function (event) {
			vm.clear();
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'dept': $('#project').combobox('getValue')},
                page:page
            }).trigger("reloadGrid");
		}
	}
});
