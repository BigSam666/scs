/**
 * Dear maintainer:
 * 
 * Once you are done trying to 'optimize' this routine,
 * and have realized what a terrible mistake that was,
 * please increment the following counter as a warning
 * to the next guy:
 * ﻿
 * total_hours_wasted_here = 50
 */
var map = new AMap.Map("container", {
    resizeEnable: true,
    isHotspot: false,
    center: [116.397428, 39.90923]
});
$(function () {
    $("#jqGrid").jqGrid({
        url: '../event/historyList',
        datatype: "json",
        colModel: [			
			// 隐藏主键
			{ label: '主键id', name: 'id', width: 50, key: true ,hidden : true },
			{ label: '业务类型', name: 'busseg', width: 50, formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
			{ label: '事件来源', name: 'sour', width: 60, formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
			{ label: '事件编号', name: 'no', width: 80},
			{ label: '现场照片', name: 'qimg', width: 80, formatter: function(value, options, row){
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
			{ label: '详细地址', name: 'addr', width: 90}, 
			{ label: '问题描述', name: 'qdescribe', width: 100 }, 
			{ label: '预计完成时间', name: 'estimatetm', width: 90}, 
			{ label: '是否超时', name: 'flg2', width: 60, formatter: function(value, options, row){
				return value == 0 ? "否" : "<span style='color:red;'>是</span>";
			}}, 
			{ label: '处理人员', name: 'handle', width: 100 }, 
			{ label: '辅办人员', name: 'auxiliary', width: 100 }, 
			{ label: '状态', name: 'flag', width: 60, formatter: function(value, options, row){
				return value == 0 ? "<span style='color:red;'>撤回</span>" : "正常";
			}}, 
			{ label: '时间', name: 'updtm', width: 100 }, 
			{ label: '操作', name: 'operation', width: 60, formatter: function(value, options, row){
				var oper = row.tag == 'sjpd' ? "<a onclick=\"javascript:vm.dl("+row.id+", false)\" style=\"cursor:pointer;\">改派</a>&nbsp;&nbsp;" : "";
				oper += "<a onclick=\"javascript:vm.dl("+row.id+", true)\" style=\"cursor:pointer;\">查看</a>";
				return oper;
			}},
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList : [10, 20, 30, 50],
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
        },
    });
	
	// 得到详细数据
	$("[name='handle_']").click(function(){
		$.get("../sys/user/selectForZtree", function(r) {
			ztree = $.fn.zTree.init($("#deptTree"), setting, r.userList);
		});
		if(!vm.view){
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
		}
	});
	$("[name='auxiliary_']").click(function(){
		$.get("../sys/user/selectForZtree", function(r) {
			ztree2 = $.fn.zTree.init($("#deptTree"), setting2, r.userList);
		});
		if(!vm.view){
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
		}
	});
});

var setting = {
	data: {
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
	callback: {
		onClick: zTreeOnClick
	}
};
var setting2 = {
	data: {
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
	callback: {
		onClick: zTreeOnClick2
	}
};
var ztree;
var ztree2;

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		upd: false,
		title:null,
		showvd: false,
		view: true,
		q:{
			name: null
		},
		event:{
			status:1,
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		dl: function (id, view) {
			vm.showList = false;
            vm.title = view ? "查看" : "改派";
            vm.view = view;
            vm.getInfo(id);
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
		viewDetails: function(){
			vm.showvd=!vm.showvd;
		},
		update: function () {
			var url = "../event/modify";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify({id:vm.event.id, handle: $("[name='handle']").val(), auxiliary: $("[name='auxiliary']").val()}),
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
		getInfo: function(id){
			$.get("../event/historyInfo/"+id, function(r){
                vm.event = r.event;
                if(vm.event.qimg){
                	if(vm.event.img){
                        imgs = vm.event.img + "," + vm.event.qimg;
                	} else {
                        imgs = vm.event.qimg;
                	}
                } else {
                	if(vm.event.img){
                		imgs = vm.event.img;
                	}
                }
                imgs = imgs.split(',');
    			if(imgs){
    				for(var i in imgs){
    					$(".form-horizontal").find("[name='qimg']").after("<img src='"+Util.consts.downloadPath+"/"+imgs[i]+"/v4/w/30/h/30' onclick='vm.showPic(\""+imgs[i]+"\")' />&nbsp;&nbsp;");
    				}
    			}
    			map.clearMap();
    			marker = new AMap.Marker({
    		        position: [r.event.lng, r.event.lat],
    		        icon: 'http://webapi.amap.com/theme/v1.3/markers/n/mark_r.png',
    		        topWhenClick: true,
    		        zIndex: 50,
    				animation: 'AMAP_ANIMATION_DROP',
    		    });
    			map.setZoomAndCenter(18, marker.getPosition());
    		    marker.setMap(map);
    			$(".amap_").show();
    			
            });
			//获取进度
			$.get("../event/getSpeed2/"+id, function(r){
				$("#firstLi").siblings().remove();
				if(r.code == 0){
					list = r.speedList;
					s = "";
					// <br/>是否下一步："+(list[i].flag == '1' ? "是" : "否")+"
					for(i=0; i<list.length; i++){
						if(i == list.length-1){
							s += "<li class=\"last\"><b>"+list[i].nodename+"</b><br/>来源："+list[i].uname+"<br/>问题描述："+list[i].qdescribe+"<br/>"+list[i].updtm+"</li>";
						} else {
							s += "<li><b>"+(list[i].flag == '1' ? (list[i].nodename) : ((list[i].nodename) == "事件派单" ? "回复" : "被撤回"))+"</b><br/>经办人："+list[i].uname+"<br/>问题描述："+list[i].des+"<br/>"+list[i].updtm+"</li>";
						}
					}
					$("#firstLi").after(s);
				} else {
					alert(r.msg);
				}
            });
		},
		reload: function (event) {
			$(".amap_").hide();
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{'name': vm.q.name},
                page:page
            }).trigger("reloadGrid");
		}
	}
});

function zTreeOnClick(event, treeId, treeNode) {
	if(!treeNode.children){
		if (treeNode.ztid == 9223372036854776000) {
			$("[name='handle']").val(treeNode.id);
			$("[name='handle_']").val(treeNode.name);						
		} else {
			layer.msg("处理人应该是一个人，而不是一个部门或者组织", function(){});
		}
	}
}
function zTreeOnClick2(event, treeId, treeNode) {
	if(!treeNode.children){
		if (treeNode.ztid == 9223372036854776000) {
			$("[name='auxiliary']").val(treeNode.id);
			$("[name='auxiliary_']").val(treeNode.name);						
		} else {
			layer.msg("辅办人应该是一个人，而不是一个部门或者组织", function(){});
		}
	}
}