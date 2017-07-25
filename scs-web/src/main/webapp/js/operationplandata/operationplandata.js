$(function () {
    $("#jqGrid").jqGrid({
        url: '../operationplandata/list',
        datatype: "json",
        colModel: [			
			// 隐藏主键
			{ label: 'id', name: 'id', width: 50, key: true ,hidden : true },
				{ label: '日期', name: 'optime', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '班次/时段', name: 'shiftText', width: 120 , formatter: function(value, options, row){
				return value;
			}}, 
				{ label: '班级', name: 'claText', width: 80 , formatter: function(value, options, row){
				return value;
			}}, 
				{ label: '成员', name: 'memberText', width: 80 , formatter: function(value, options, row){
				return value;
			}}, 
				{ label: '司机', name: 'driver', width: 80 , formatter: function(value, options, row){
				return value == '1' ? '是' : '否'
			}/*},
				{ label: '车辆', name: 'car', width: 80 , formatter: function(value, options, row){
                return $.isPlainObject(value) ? value.value : value;
            }*/},
		// 状态
			{ label: '状态', name: 'status', width: 50, hidden: Util.consts.isadmin, formatter: function(value, options, row){
			return value == 0 ? 
				'<span class="label label-danger">无效</span>' : 
				'<span class="label label-success">有效</span>';
			}},
			{ label: '创建日期', name: 'crttm', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '操作', name: 'operation', width: 20, formatter: function(value, options, row){
				var oper = del ? "<a onClick=\"javascript:vm.del("+options.rowId+");\" title='删除'  ><i class=\"fa fa-trash-o\" style='color:red;'></i></a>" : "";
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

    //

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

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		upd: false,
		title: null,
		operationplandata: {status:1,},
        pop:{
            business:'',
            time:'',
            banci:[],
            project:''
        },
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
	methods: {
		query: function () {
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
            // if (validate.form()) {
			if (true) {
                $.post('../operationplandata/day',JSON.stringify({'date':vm.pop.time, 'banci': vm.pop.banci}),function (e){
                    if(e.code==0){
                        if(e.date == 0 ){ //没有重复月份的排班
                            vm.grendTable(JSON.stringify(vm.pop));
                        }else{
                            layer.msg(vm.time+' 已有排班,请选择其他月份!',{time:2000}); //如果有本月排班,抛出msg
                        }
                    } else {
                        alert(e.msg);
                    }
                })
            }
        },
        grendTable:function(pop){
            $.post('../operationplandata/paiban', pop,function (e){
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
        //保存作业计划or修改作业计划
        save : function() {
            if(vm.oid == ''){
                //执行保存
                $.post("../operationplandata/save",JSON.stringify({'datas':$('#forms').form2json(),'project':vm.pop.project,'date':vm.pop.time,'bsment':vm.pop.business,'banci':vm.pop.banci}), function(r) {
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
		update: function (id, isupdate) {
			if(id == null){
				return ;
			}
			vm.showList = false;
			vm.upd = isupdate;
            vm.title = isupdate ? "修改" : "查看";
            
            vm.getInfo(id);
		},
		del: function (id) {
			confirm('确定要删除选中的记录？', function(){
		    	$.get("../operationplandata/delete/"+id, function(r){
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
			$.get("../operationplandata/info/"+id, function(r){
                vm.operationplandata = r.operationplandata;
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
