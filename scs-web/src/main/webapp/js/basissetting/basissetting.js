$(function() {
	// 表单验证
	validate = $(".form-horizontal").validate({
		onfocusout : false,
		onkeyup : false,
		rules : {
			czm : {
				required : true,
				digits : true,
				min:1
			},
			czf : {
				required : true,
				digits : true,
				min:1
			},
			cym : {
				required : true,
				digits : true,
				min:1
			},
			ccg : {
				required : true,
				digits : true,
				min:1
			},
			kzm : {
				required : true,
				digits : true,
				min:1
			},
			kzf : {
				required : true,
				digits : true,
				min:1
			},
			kym : {
				required : true,
				digits : true,
				min:1
			},
		},
		messages : {
			czm : {
				required : "车辆滞留范围项必填!"
			},
			czf : {
				required : "车辆滞留时间项必填!"
			},
			cym : {
				required : "车辆越界项必填!"
			},
			ccg : {
				required : "车辆超速项必填!"
			},
			kzm : {
				required : "考勤滞留范围项必填!"
			},
			kzf : {
				required : "考勤滞留时间项必填!"
			},
			kym : {
				required : "考勤越界项必填!"
			},
		},
		showErrors : function(errorMap, errorList) {
			if (errorList && errorList.length > 0) { // 如果存在错误信息
				layer.msg(errorList[0].message, function() {
				});
			}
		}
	});
	vm.getInfo();
});

var vm = new Vue({
	el : '#rrapp',
	data : {
		jcsz : {
			czm : '',
			czf : '',
			cym : '',
			ccg : '',
			kzm : '',
			kzf : '',
			kym : ''
		},
		bid:null,
		vfc:true
	},
	watch:{
		jcsz : {
			 handler: function (val, oldVal) {
				 vfc = (val == oldVal);
			 },
			 deep: true
		}
	},
	methods : {
		saveUpdate : function(evt) {
			if(vfc){
				if(evt != null){
					var url = "../basissetting/update";
					var data = JSON.stringify(vm.jcsz).replace('{','{'+'\"id\":'+'\"'+evt+'\"'+',');
				}else{
					var url = "../basissetting/save";
					var data = JSON.stringify(vm.jcsz);
				}
				if (validate.form()) {
					$.post(url,data,function(r){
						if(r.code == 0){
							layer.msg('基础设置保存成功!');
							vm.getInfo();
						}else{
							alert('服务器异常,请联系管理员!');
						}
					})
				}
			}else{
				layer.msg('你没有作任何修改!');
			}
		},
        getInfo: function() {
        	$.post("../basissetting/getid",function(e){
        		if(e.id == null){
        			alert('基础设置没有数据,请填写相关数据信息!');
        		}else{
        			$.get("../basissetting/info/" + e.id, function(r) {
        				vm.jcsz = r.basissetting;
        			});
        		}
        	});
        },
		getId : function () {
			$.post("../basissetting/getid",function(e){
				vm.bid = e.id;
				vm.saveUpdate(vm.bid);
			})
		}
	}
});
