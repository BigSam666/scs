$(function () {
	$('.input-time').unbind('focus');
	$(".input-time").bind('focus', function () { WdatePicker({ dateFmt: 'yyyy-MM', autoPickDate: true }); });
	$('#timetype').combobox({
		readonly: false,
	    data: [{'key':'日','val':'日'},{'key':'月','val':'月'},{'key':'年','val':'年'}],
	    valueField:'val',
	    textField:'key',
	    // 默认选中
	    value: '月',
	    panelHeight: 'auto',
	    editable: false,
		onChange:function(newValue, oldValue){
			vm.timetypechange(newValue);
	    }
	});
	$.get("../sys/user/selectForZtree", function(r) {
		if (r.code != 0) {
			alert(r.msg);
			vm.reload();
		}
		ztreePerson = $.fn.zTree.init($("#personTree"), settingPerson, r.userList);
	});
	options = {
			title: {
		        text: '扣分统计',
		        align: 'left'
		    },
		    xAxis: {
		        categories: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']
		    },
		    yAxis: {
		    	lineWidth: 2,
		        title: {
		            text: '单位：件'
		        },
		        plotLines: [{
		            value: 0,
		            width: 1,
		            color: '#808080'
		        }]
		    },
		    tooltip: {
		        valueSuffix: '件'
		    },
		    series: [
			    {
			        name: '东京',
			        data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
			    }, {
			        name: '纽约',
			        data: [-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5]
			    }, {
			        name: '柏林',
			        data: [-0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9, 1.0]
			    }, {
			        name: '伦敦',
			        data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
			    }
		    ]
		};
	chart = new Highcharts.Chart('container', options);
});
var chart;
var options;
var daysOfMonth = [31,28,31,30,31,30,31,31,30,31,30,31]; // 每个月有多少天

// 判断是否是闰年
function isLeapYear (Year) {
	if (((Year % 4)==0) && ((Year % 100)!=0) || ((Year % 400)==0)) {
		return (true);
	} else { 
		return (false); 
	}
}

var ztreePerson;

var settingPerson = {
	data : {
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
	check : {
		enable : true,
		nocheckInherit : true,
		chkboxType : {
			"Y" : "ps",
			"N" : "ps"
		},
		chkStyle : "checkbox",
		radioType : "all"
	}
};


var vm = new Vue({
	el:'#rrapp',
	data:{
		q : {
			name : null
		},
		showSecondTime:false,
		title: null,
		person:[],
		personName:''
	},
	methods: {
		query: function () {
			// 1.销毁现有的图
			chart.destroy();
			_data = null;
			// 2.更新横轴
			var timetype = $("input[name='timetype']").val();
			if (timetype == '月') {
				var _daysOfMonth = daysOfMonth;
				var ftime = $("input[name='ftime']").val(); // 2017-02
				var year = parseInt(ftime.slice(0,4));  // 年
				var month = parseInt(ftime.slice(5)); // 月
				if (isLeapYear(year)) { // 闰年
					_daysOfMonth[1] = 29;
				}
				var _categories  = new Array();
				for (var i = 1;i<=_daysOfMonth[month-1];i++) {
					_categories.push(i + '日')
				}
				options.xAxis.categories = _categories;
			} else if (timetype == '年') {
				options.xAxis.categories = ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'];
			} else {
				var ftime = parseDate($("input[name='ftime']").val()); // 2017-02-12
				var stime = parseDate($("input[name='stime']").val()); // 2017-02-25
				if (ftime > stime) {
					alert("后面的时间不能比前面的时间早");
					return;
				}
				var _categories  = new Array();
				while(ftime <= stime) {
					_categories.push(ftime.Format('MM月dd日'));
					ftime.setDate(ftime.getDate() + 1); // 将日期向后推一天
				}
				options.xAxis.categories = _categories;
				debugger
			}
			// 3.请求数据
			var _json = $("#form-horizontal").form2json();
			
			// ---------------------------------------------------------------------------------------请求后台数据--------------
			
			
			$.ajax({  
		        url : 'https://data.jianshukeji.com/jsonp?filename=csv/analytics.csv&callback=?',
		        type: "POST",
		        //data: param,
			    async: false,
		        success : function(rntData){
		        	if (rntData == null) {
						alert("没有查询到数据，请联系管理员");
						return;
					}
//					options.series = rntData;

		        	// 测试数据--------------
		        	if (timetype == '月') {
		        		options.series = [{
		    			        name: 'aaa',
		    			        data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3,7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3,7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3,20]
		    			    },{
		    			    	name: 'bbb',
		    			        data: [26.5, 23.3, 18.3,7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3,7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3,7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2,20]
		    			    }];
		        	} else if (timetype == '年') {
		        		options.series = [{
	    			        name: 'aaa',
	    			        data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3,7.0, 6.9]
	    			    },{
	    			    	name: 'bbb',
	    			        data: [18.2, 21.5, 25.2, 18.3,7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2,20]
	    			    }];
		        	} else {
		        		options.series = [{
	    			        name: 'aaa',
	    			        data: [7.0, 6.9, 9.5, 14.5, 18.2, 22.6, 10.2]
	    			    },{
	    			    	name: 'bbb',
	    			        data: [25.2, 18.3,7.0, 6.9, 9.5, 3.2, 17.3]
	    			    }];
		        	}
		        	// 测试数据--------------
		        	
		        	
		        	
		        	
					// 4.渲染
					chart = new Highcharts.Chart('container', options);
		        }
			});
			
		},
		timetypechange: function (e) {
			if (e=='月') {
				vm.showSecondTime = false;
				$(".input-time").css("width","55%");
				$('.input-time').val('');
				$('.input-time').unbind('focus');
				$(".input-time").bind('focus', function () { WdatePicker({ dateFmt: 'yyyy-MM', autoPickDate: true }); });
			} else if (e=="年"){
				vm.showSecondTime = false;
				$(".input-time").css("width","55%");
				$('.input-time').val('');
				$('.input-time').unbind('focus');
				$(".input-time").bind('focus', function () { WdatePicker({ dateFmt: 'yyyy', autoPickDate: true }); });
			} else {
				vm.showSecondTime = true;
				$(".input-time").css("width","26%");
				$('.input-time').val('');
				$('.input-time').unbind('focus');
				$(".input-time").bind('focus', function () { WdatePicker({ dateFmt: 'yyyy-MM-dd', autoPickDate: true }); });
			}
		},
		personTree : function() {
			layer.open({
				type : 1,
				offset : '50px',
				skin : 'layui-layer-molv',
				title : "选择办理人员",
				area : [ '300px', '450px' ],
				shade : 0,
				shadeClose : false,
				content : jQuery("#personLayer"),
				btn : [ '确定', '取消' ],
				btn1 : function(index) {
					// 获取选择的菜单
					var nodes = ztreePerson.getCheckedNodes();
					var _person = new Array();
					var _pName = [];
					var _personName = "";
					if(nodes.length>0){
						nodes.forEach(function (node) {
							if (node.ztid == 9223372036854776000) {
								if ($.inArray(node.id.toString(), _person) == -1) {
									_person.push(node.id.toString());
									_pName.push(node.name.toString());
									_personName = _pName.toString();
								}
							}
						});
						if (_person.length < 1) {
							alert("请选择人");
							return;
						} 
						vm.person = _person;
						vm.personName = _personName;
						layer.close(index);
					}
				}
			});
		},
		reload: function (event) {

		}
	}
});
