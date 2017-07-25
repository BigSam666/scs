var marker,map = new AMap.Map("container", {
    resizeEnable: true,
    isHotspot: false,
    center: [116.397428, 39.90923],
    zoom: 13
});
$(function () {
    $("#jqGrid").jqGrid({
        url: '../event/ctdiList',
        datatype: "json",
        colModel: [			
			// 隐藏主键
			{ label: '主键id', name: 'id', width: 50, key: true ,hidden : true },
			{ label: '事件编号', name: 'no', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
			{ label: '业务板块', name: 'busseg', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
			{ label: '事件来源', name: 'sour', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
			{ label: '派单时间', name: 'dltm', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
			{ label: '反馈图片', name: 'img', width: 80 ,hidden : true },
			{ label: '案件图片', name: 'qimg', width: 80, formatter: function(value, options, row){
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
			{ label: '问题描述', name: 'qdescribe', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
			{ label: '意见反馈', name: 'des', width: 80 , formatter: function(value, options, row){
			return $.isPlainObject(value) ? value.value : value;
			}}, 
			{ label: '创建时间', name: 'crttm', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
			{ label: '操作', name: 'operation', width: 40, formatter: function(value, options, row){
				var oper = "<a onClick=\"javascript:vm.view("+options.rowId+");\" title='查看' ><i class=\"fa fa-search\" style='color:violet;'></i></a>&nbsp;&nbsp;";
				oper += recall ? "<a onClick=\"javascript:vm.recall("+options.rowId+");\" title='撤回'  ><i class=\"fa fa fa-reply\" style='color:green;'></i></a>&nbsp;&nbsp;":"";
				oper += treat ? "<a onClick=\"javascript:vm.treat("+options.rowId+");\" title='处理'  ><i class=\"fa fa fa-arrow-right\" style='color:green;'></i></a>" : "";
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
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		showvd: false,
		imgs: '',
		event: {status:1,},
		showOther: true,
	},
	methods: {
		query: function () {
			vm.reload();
		},
		view: function (id, isupdate) {
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "查看";
            
            vm.getInfo(id);
		},
		recall: function (id) {
			if(id){
	        	vm.event = $("#jqGrid").getRowData(id);
			}
			vm.showOther = false;
			vm.openHandle("撤回");
		},
		treat: function (id) {
			if(id){
	        	vm.event = $("#jqGrid").getRowData(id);
			}
			vm.showOther = true;
			vm.openHandle("处理");
		},
		openHandle: function (title){
			vm.imgs = new Array();
			vm.fileupload("#img");
			layer.open({
				  type: 1,
				  title: title,
				  skin: 'layui-layer-molv',
				  anim: 2,
				  closeBtn: 1,
			      maxmin: true,
				  shade: 0.3,
				  shadeClose: true,
				  area: ['40%', '60%'],
				  content: $(".handle")
			});
		},
		fileupload: function (selector){
			$(selector).diyUpload({
		    	url: Util.consts.filePath,
		    	success:function( r ) {
		    		vm.imgs.push(r.data[0]);
		    		$("[name='img']").val(vm.imgs.toString());
		    	},
		    	error:function( err ) {
		    		alert(err);
		    	},
		    	buttonText : '选择现场图片',
		    });
		},
		save: function (event) {
			data = $(".handle_from").form2json();
			data.btype = vm.showOther ? 1 : 0;
			debugger
			$.ajax({
				type: "POST",
			    url: "../event/upcsave",
			    data: JSON.stringify(data),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							location.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
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
		getInfo: function(id){
			$.get("../event/ctdiinfo/"+id, function(r){
                vm.event = r.event;
    			imgs = vm.event.qimg.split(',');
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
			$.get("../event/getSpeed/"+id, function(r){
				$("#firstLi").siblings().remove();
				if(r.code == 0){
					list = r.speedList;
					s = "";
					// <br/>是否下一步："+(list[i].flag == '1' ? "是" : "否")+"
					for(i=0; i<list.length; i++){
						if(list[i].nodename == "结案"){
							s += "<li><b>"+list[i].nodename+"</b><br/>"+list[i].updtm+"</li>";
						} else if(i == list.length-1){
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
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});
