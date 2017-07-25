$(function() {
	$("#jqGrid")
			.jqGrid(
					{
						url : '../calltel/list',
						datatype : "json",
						colModel : [
								// 隐藏主键
								{
									label : '主键id',
									name : 'id',
									width : 50,
									key : true,
									hidden : true
								},
								{
									label : '类型',
									name : 'rtype',
									width : 80
								},
								{
									label : '姓名',
									name : 'name',
									width : 80
								},
								{
									label : '性别',
									name : 'sex',
									width : 80,
									formatter : function(value, options, row) {
										return value == 0 ? '<span class="label label-danger">未知</span>'
												: value == 1 ? '<span class="label label-success">男</span>'
														: '<span class="label label-success">女</span>';
									}
								},
								{
									label : '手机号码',
									name : 'phone',
									width : 80
								},
								{
									label : '操作',
									name : 'operation',
									width : 40,
									formatter : function(value, options, row) {
										var oper = '<button type="button" onClick="call('
												+ row.phone
												+ ')" class="btn btn-block btn-danger btn-xs">拨打电话</button>';
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
							$("#jqGrid").closest(".ui-jqgrid-bdiv").css({
								"overflow-x" : "hidden"
							});
						}
					});
});

var vm = new Vue({
	el : '#rrapp',
	data : {
		q : {
			query : null
		},
		showList : true,
		upd : false,
		title : null,
		calltel : {}
	},
	methods : {
		query : function() {
			vm.reload();
		},
		call : function(phone) {
			alert(phone)
		},
		reload : function(event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				postData : {
					'query' : vm.q.query
				},
				page : page
			}).trigger("reloadGrid");
		}
	}
});

function call(phone) {

	if (phone == null || phone == '') {
		alert('请输入正确的号码!');
		return;
	}
	// 生成签名
	function get_sign(appkey, str, secret, time) {
		var str2 = appkey + str + secret + time;
		return md5(str2);
	}
	;
	// 电话呼出
	var extension = '11100'; // 分机号
	var extensionDst = phone;
	var str = 'interfaces' + 'cti' + 'dial' + extension + extensionDst;
	var appkey = 'bcc784b371a99cd6dae5ac2b8707ef03';
	var secret = 'SWiU28';
	var act = 'dial';
	var time = Date.parse(new Date());
	var sign = get_sign(appkey, str, secret, time); // 签名信息

	var url = 'http://api.mixcaller.com/?m=interfaces&c=cti&a=index&act=' + act
			+ '&appkey=' + appkey + '&sign=' + sign + '&time=' + time
			+ '&extension=' + extension + '&extensionDst=' + extensionDst + '';

	$.ajax({
		type : 'GET',
		url : url,
		cache : false,
		dataType : 'jsonp',
		jsonp : 'callback',
		success : function(data) {
			if (data.code == 200) {
				alert("电话正在接通中...")
			}
		}
	});
};

var doc = document, btns = doc.querySelectorAll(".caculate .row a"), input = doc
		.querySelector('.input-wrap .input'), delBtn = doc
		.getElementById('cacu-del'), callBtn = doc.getElementById('call');
btns.forEach(function(btn) {
	btn.addEventListener('click', function() {
		input.innerHTML += this.dataset.val;
	});
	btn.addEventListener('mousedown', function() {
		this.classList.add('active');
	});
	btn.addEventListener('mouseup', function() {
		btns.forEach(function(btn) {
			btn.classList.remove('active');
		});
	});
});
delBtn.addEventListener('click', function() {
	var val = input.innerHTML;
	input.innerHTML = val.substr(0, val.length - 1);
});
callBtn.addEventListener('click', function() {
	var phoneNum = input.innerHTML;
	call(phoneNum);
});