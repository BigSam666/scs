$(function () {
    $("#jqGrid").jqGrid({
        url: '../carterminal/list',
        datatype: "json",
        colModel: [			
			// 隐藏主键
			{ label: '主键id', name: 'id', width: 50, key: true ,hidden : true },
			{ label: '设备厂商', name: 'devfrimName', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '设备型号', name: 'devmodelName', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '设备ID', name: 'devid', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '2G/SIM卡号', name: 'g2no', width: 80 , formatter: function(value, options, row){
					if (value == null) return '';
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '3G/SIM卡号', name: 'g3no', width: 80 , formatter: function(value, options, row){
					if (value == null) return '';
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '修改人', name: 'updName', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '修改时间', name: 'updtm', width: 80 , formatter: function(value, options, row){
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
	 // 表单验证
    validate = $(".form-horizontal").validate({
  	   onfocusout: false,
 	   onkeyup: false,
 	   rules: {
 		  devfrim: {
 			   required: true
 		   },
 		  devmodel: {
 			   required: true
 		   },
 		  devid: {
 			   required: true
 		   }
 	   },
 	   messages: {
 		  devfrim: {
 		       required: "必填，请输入设备厂商"
 		   },
 		  devmodel: {
			   required: "必填，请输入设备型号"
		   },
		   devid: {
			   required: "必填，请输入设备ID"
		   }
 	   },
 	   showErrors: function(errorMap, errorList) {
			if ( errorList && errorList.length > 0 ) {  //如果存在错误信息
				layer.msg(errorList[0].message, function(){});
			}
		}
    });
    // 表单验证结束
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		q : {qdevid:null},
		showList: true,
		upd: false,
		title: null,
		carterminal: {status:1,devid:'',g2no:'',g3no:''}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		getDevfrim : function(id) {
			$.get("../basecode/selectByType/devfrim", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				// 填充数据到页面
				$('#devfrim').combobox({
					readonly: false,
				    data: r.data,
				    valueField:'id',
				    textField:'cnm',
				    panelHeight: 'auto',
				    editable: false,
		   			onChange:function(newValue, oldValue){
		   		    }
				});
			})
		},
		getDevmodel : function(id) {
			$.get("../basecode/selectByType/devmodel", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				// 填充数据到页面
				$('#devmodel').combobox({
					readonly: false,
				    data: r.data,
				    valueField:'id',
				    textField:'cnm',
				    panelHeight: 'auto',
				    editable: false,
		   			onChange:function(newValue, oldValue){
		   		    }
				});
			})
		},
		add: function(){
			vm.showList = false;
			vm.upd = true;
			vm.title = "新增";
			vm.carterminal = {status:1,devid:'',g2no:'',g3no:''};
			
			
			vm.getDevfrim(); // 设备厂商
			vm.getDevmodel(); // 设备型号
		},
		update: function (id, isupdate) {
			if(id == null){
				return ;
			}
			vm.showList = false;
			vm.upd = isupdate;
            vm.title = isupdate ? "修改" : "查看";
            
            vm.getInfo(id);
            
            // 设备厂商
			$.get("../basecode/selectByType/devfrim", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				var _data = r.data;
				_data.forEach(function (e){
					if (e.id == vm.carterminal.devfrim) {
						e.selected = true;
					}
				});
				$('#devfrim').combobox({
					readonly: false,
				    data: r.data,
				    valueField:'id',
				    textField:'cnm',
				    panelHeight: 'auto',
				    editable: false,
		   			onChange:function(newValue, oldValue){
		   		    }
				});
			});
			// 设备型号
			$.get("../basecode/selectByType/devmodel", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				var _data = r.data;
				_data.forEach(function (e){
					if (e.id == vm.carterminal.devmodel) {
						e.selected = true;
					}
				});
				$('#devmodel').combobox({
					readonly: false,
					data: r.data,
					valueField:'id',
					textField:'cnm',
					panelHeight: 'auto',
					editable: false,
					onChange:function(newValue, oldValue){
					}
				});
			});
		},
		saveOrUpdate: function (event) {
			if(validate.form()) {
				var url = vm.carterminal.id == null ? "../carterminal/save" : "../carterminal/update";
				var _json = $(".form-horizontal").form2json();
				_json.id = vm.carterminal.id;
				$.ajax({
					type: "POST",
				    url: url,
				    data: JSON.stringify(_json),
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
			}
		},
		del: function (id) {
			confirm('确定要删除选中的记录？', function(){
		    	$.get("../carterminal/delete/"+id, function(r){
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
			$.ajax({
			   type: "GET",
			   url: "../carterminal/info/"+id,
			   async: false,
			   success: function(r) {
				   vm.carterminal = r.carterminal;
			   }
			});
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData : {
					'qdevid' : vm.q.qdevid
				},
                page:page
            }).trigger("reloadGrid");
		}
	}
});
