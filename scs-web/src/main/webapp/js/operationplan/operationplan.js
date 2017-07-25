$(function() {
	$("#jqGrid")
			.jqGrid({
			url : '../operationplan/list',
			datatype : "json",
			colModel : [
			{
				label : 'id',
				name : 'id',
				width : 50,
				key : true,
				hidden : true
			},
			{
				label : '排班日期',
				name : 'optime',
				width : 80
			},
			{
				label : '创建人',
				name : 'crtid',
				width : 80
			},
			{
				label : '所属项目部',
				name : 'dept',
				width : 80
			},
			{
				label : '业务板块',
				name : 'bsment',
				width : 80
			},
			{
				label : '班级',
				name : 'cla',
				width : 80
			},
			{
				label : '创建时间',
				name : 'crttm',
				width : 80
			},
			{
				label : '操作',
				name : 'operation',
				width : 40,
				formatter : function(value, options, row) {
					var oper = "<a onClick=\"javascript:vm.getInfo("+options.rowId+");\" title='查看' ><i class=\"fa fa-info-circle\" style='color:violet;'></i></a>&nbsp;&nbsp;";
					oper += update ? "<a onClick=\"javascript:vm.update("+ options.rowId+ ");\" title='修改'  ><i class=\"fa fa-pencil-square-o\" style='color:green;'></i></a>&nbsp;&nbsp;": "";
					oper += del ? "<a onClick=\"javascript:vm.del("+ options.rowId+ ");\" title='删除'  ><i class=\"fa fa-trash-o\" style='color:red;'></i></a>&nbsp;&nbsp;": "";
					oper += copy ? "<a onClick=\"javascript:vm.copy("+ options.rowId+ ");\" title='拷贝'  ><i class=\"fa fa-files-o\" style='color:orange;'></i></a>": "";
					return oper;
				}
			}, ],
		viewrecords : true,
		height : 385,
		rowNum : 10,
		rowList : [ 10, 30, 50 ],
		rownumbers : false,
		rownumWidth : 25,
		autowidth : true,
		multiselect : false,
		pager : "#jqGridPager",
		jsonReader : {
			root : "page.list",
			page : "page.currPage",
			total : "page.totalPage",
			records : "page.totalCount"
		},
		prmNames : {
			page : "page",
			rows : "limit",
			order : "order"
		},
		gridComplete : function() {
			// 隐藏grid底部滚动条
			$("#jqGrid").closest(".ui-jqgrid-bdiv").css({
				"overflow-x" : "hidden"
			});
		}
	});
	// 表单验证
	validate = $("#validate").validate({
		onfocusout : false,
		onkeyup : false,
		rules : {
			busseg : {
				required : true
			},
			project : {
				required : true
			},
			time : {
				required : true
			},
			banci : {
				required : true
			}
		},
		messages : {
			busseg : {
				required : "业务板块必选!"
			},
			project : {
				required : "项目部必选"
			},
			time : {
				required : "月份必选!"
			},
			banci : {
				required : "班次必选!"
			}
		},
		showErrors : function(errorMap, errorList) {
			if (errorList && errorList.length > 0) { // 如果存在错误信息
				layer.msg(errorList[0].message, function() {
				});
			}
		},
	});
    
	//获取业务板块
	$('#serviceType').combobox({
		readonly: true,
		disabled:true,
		data: Util.loadData("../basecode/selectByType/"+'biztype').data,
		valueField:'no',
		textField:'cnm',
		editable: false,
		value: 0,
		panelHeight: 'auto'
	});
	
    // 获取我当前项目部位置；
    var data = Util.loadData("../gridmng/getUserProjectMsg").data;
	$('#project').combobox({
		readonly: false,
		data: data,
		valueField:'id',
		textField:'name',
		value: data[0].id,
		editable: false,
		panelHeight: 'auto',
		onSelect:function(t){
			vm.pop.project = t.id;
		}
	});
});
var vm = new Vue({
	el : '#rrapp',
	data : {
		q : {
			name : null
		},
		operationplan : {
			status : 1,
		},
		pop:{
			business:'',
			time:'',
			banci:[],
			project:''
		},
		showList : true,
		upd : false,
		title : null,
		time:'',
		shift:[],
		datas:'',
		datal:[],
		lengths:'',
		plan:[],
		timevi:'',
		evt:'',
		oid:'',
		index:{
			addData:'',
			add:''
		}
	},
	methods : {
		query : function() {
			vm.reload();
		},
		//新增作业计划
		add : function() {
			vm.time ='';//初始化选择月份
			vm.pop.banci = []; //初始化选择班次
			vm.index.add = layer.open({
				  type: 1,
				  maxmin: true,
				  title: '新增计划',
				  shade: [0.3, '#000'],
				  area:['43%','60%'],
				  shadeClose: true,
				  content: $("#addbanci")
			});
			vm.pop.business = $('#serviceType').combobox('getValue'); //获取业务板块的数据
			//获取所有的班次信息
			$.post('../operationplan/ban',function (e){
				vm.shift = e.bancimap;
			});
		},
		//新增计划数据
		addData : function (){
			$('#addbanci').find('input:checkbox,input:radio').prop('disabled',false);//初始化选择框
			vm.pop.time = vm.time.replace(/[年,月]/g,'');//获取选中的月份
			if (validate.form()) {
				$.post('../operationplan/month',JSON.stringify({'date':vm.pop.time,'bsment':vm.pop.business,'dept':vm.pop.project}),function (e){
					if(e.code == 200){
                        vm.grendTable(JSON.stringify(vm.pop));//没有重复月份的排班
					}else if(e.code == 500){
                        layer.msg(vm.time+' 已有排班,请选择其他月份!',{time:2000});
					}else {
                        alert(e.msg);
                    }
				})
			}
		},
		grendTable:function(pop){
			$.post('../operationplan/paiban', pop,function (e){
				if(e.code==0){
					vm.datas = e.maps;
					//生成表格的合并行数
					var lengths = 0;
					for(var map in e.maps.nmap){
						lengths += e.maps.nmap[map].value.length
					}
					vm.lengths = lengths;
					layer.close(vm.index.add);//关闭上一层
					vm.index.addData = layer.open({
						type: 1,
						maxmin: true,
						title: '制定计划',
						shade: [0.3, '#000'],
						area:['90%','90%'],
						shadeClose: true,
						content: $("#makingplan")
					});
					//对弹窗的处理
					$('#makingplan').find('input:checkbox,input:radio').prop('disabled',false);//显示选择框
					$('input:checkbox,input:radio').prop('checked',false);//初始化所有选择框
					$('#sbutton .btn-success').prop('disabled',false);//显示提交按钮
					$('#sbutton').show();//显示提交
				} else {
					alert(e.msg);
				}
			});
		},
		//回填数据
		fullData:function (oid, isupd){
			//回填数据
			setTimeout( function(){
			$.get("../operationplan/info/" + oid, function(r) {
				for(var item in r.operationplan){
					var optime = r.operationplan[item].optime;
					var shift = r.operationplan[item].shift;
					var cla = r.operationplan[item].cla;
					var member = r.operationplan[item].member;
					var driver =  r.operationplan[item].driver;
					var car =  r.operationplan[item].car;
					$('input[name ="'+optime+'#'+shift+'#'+cla+'#'+member+'" ]').prop('checked',true);
					if(driver == 1){
						$('input[name ="'+optime+'#'+shift+'#'+cla+'#'+member+'#driver'+'" ]').prop('checked',true);
					}
					$('input[name ="'+optime+'#'+shift+'#'+cla+'#'+member+'#driver#car'+'" ][value='+car+']').prop('checked',true);
					if(!isupd){
						$('#sbutton').hide();//隐藏提交,取消按钮;
						$('input:checkbox,input:radio').prop('disabled',true);//禁用选择框
					}
				}
			})}, 500);
		},
		//查看详细
        getInfo: function(oid) {
        	$('input:checkbox,input:radio').prop('checked',false);//初始化所有选择框;
        	//生成表格
        	$.get('../operationplan/datalist/'+oid,function (e){
        		//获取数据并转换成json
        		for(var item in e.operationplan){
        			if(/,/igm.test(e.operationplan[item].banci)){
        				var banci = e.operationplan[item].banci.split(',');
        			}else{
        				var banci = new Array(e.operationplan[item].banci);
        			}
        			var jsondata = JSON.stringify({'banci':banci,'business':e.operationplan[item].bsment,'project':e.operationplan[item].dept,'time':e.operationplan[item].optime})
        		}
        			vm.grendTable(jsondata);
        	});
        	// 等待页面加载后再渲染填充数据
        	vm.fullData(oid, false)
        },
		//保存作业计划or修改作业计划
		save : function() {
			if(vm.oid == ''){
				//执行保存
				$.post("../operationplan/save",JSON.stringify({'datas':$('#forms').form2json(),'project':vm.pop.project,'date':vm.pop.time,'bsment':vm.pop.business,'banci':vm.pop.banci}), function(r) {
					layer.msg('保存成功!',{time:1500});//保存成功处理项
					layer.close(vm.index.addData);//关闭新增计划窗口
					vm.reload(); //刷新列表
				})
			}else{
				//执行修改
				$.post("../operationplan/update",JSON.stringify({'datas':$('#forms').form2json(),'oid':vm.oid}), function(r) {
					layer.msg('修改成功!',{time:1500});//保存成功处理项
					layer.close(vm.index.addData);//关闭新增计划窗口
					vm.reload(); //刷新列表
				})
			}
		},
		//刷新列表
		reload : function() {
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				postData : {
					'name' : vm.q.name
				},
				page : page
			}).trigger("reloadGrid");
		},
		//删除
		del : function(id) {
			layer.confirm('确定要删除选中的记录？', function(index){
				$.get("../operationplan/delete/" + id, function(r) {
					r.code == 0 ? vm.reload() : layer.msg('删除失败!',{time:1000});
				});
				layer.close(index);
			}); 
		},
		//修改
		update : function (oid){
			$('input:checkbox,input:radio').prop('checked',false);//初始化所有选择框;
			$('#sbutton .btn-success').prop('disabled',false);//初始化提交
        	//生成表格
        	$.get('../operationplan/datalist/'+oid,function (e){
        		//获取数据并转换成json
        		for(var item in e.operationplan){
        			if(/,/igm.test(e.operationplan[item].banci)){
        				var banci = e.operationplan[item].banci.split(',');
        			}else{
        				var banci = new Array(e.operationplan[item].banci);
        			}
        			var jsondata = JSON.stringify({'banci':banci,'business':e.operationplan[item].bsment,'project':e.operationplan[item].dept,'time':e.operationplan[item].optime})
        		}
        		vm.grendTable(jsondata);
        	});
        	// 等待页面加载后再渲染填充数据
        	vm.fullData(oid, true)
        	vm.oid = oid; //赋值保存的oid
		},
		//复制
		copy : function (id) {
			$.get("../operationplan/judegtime/" + id,function(r) {
				if(r.map.judegMonth == null){
					//下一个月没有排班
					$.post("../operationplan/copy",JSON.stringify({"id":id,"optime":r.map.dateString}),function(r) {
						if(r.code == 0){
							alert("拷贝成功!");
							vm.reload(); //刷新列表
						}else{
							alert(r.msg);
						}
					})
				}else{
					//下一个月已有排班
					alert(r.map.dateString+" 已有排班,请重新选择其他月份进行拷贝!");
				}
			})
		 }
	}
});


