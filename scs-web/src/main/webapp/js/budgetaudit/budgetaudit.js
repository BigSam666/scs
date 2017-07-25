// 审批不通过     0
// 发起申报/待审批         
// 项目部审批     1
// 事业部审批     2
// 财务部审批     3
// 副总审批         4
// 全部审批通过  5
$(function () {
    $("#jqGrid").jqGrid({
        url: '../budgetaudit/list',
        datatype: "json",
        colModel: [			
			// 隐藏主键
			{ label: '主键id', name: 'id', width: 50, key: true ,hidden : true },
				{ label: '月份', name: 'month', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '申报人员', name: 'personName', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '所属项目', name: 'projectName', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '联系方式', name: 'mobile', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '营业收入预算', name: 'businessIncomeBudget', width: 80 , formatter: function(value, options, row){
				return "<a onClick=\"javascript:vm.showBusinessIncomeBudgetDetail("+options.rowId+", false);\" style='cursor:pointer;'>"+value+"</a>";
			}}, 
				{ label: '费用预算（元）', name: 'costBudget', width: 80 , formatter: function(value, options, row){
				return "<a onClick=\"javascript:vm.showCostBudgetDetail("+options.rowId+", false);\" style='cursor:pointer;'>"+value+"</a>";
			}}, 
				{ label: '净利润（预计）', name: 'np', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '利润率（预计）', name: 'npr', width: 80 , formatter: function(value, options, row){
				return (Math.round(value * 10000)/100).toFixed(2) + '%';
			}}, 
				{ label: '人力成本/收入', name: 'laborCost1', width: 80 , formatter: function(value, options, row){
				return (Math.round(value * 10000)/100).toFixed(2) + '%';
			}}, 
				{ label: '人力成本/总成本', name: 'laborCost2', width: 80 , formatter: function(value, options, row){
				return (Math.round(value * 10000)/100).toFixed(2) + '%';
			}}, 
				{ label: '申报时间', name: 'crttm', width: 80 , formatter: function(value, options, row){
				return $.isPlainObject(value) ? value.value : value;
			}}, 
				{ label: '进度', name: 'schedule', width: 80 , formatter: function(value, options, row){
					var valueStr = "<a onClick=\"javascript:vm.seeSchedule("+options.rowId+");\" style=\"cursor:pointer;\">";
					switch (value) {
						case 0: valueStr += '未通过'; break;
						case 1: valueStr += '项目部审批'; break;
						case 2: valueStr += '事业部审批'; break;
						case 3: valueStr += '财务部审批'; break;
						case 4: valueStr += '副总审批'; break;
						case 5: valueStr += '已通过'; break;
					}
					valueStr += "</a>";
				return valueStr;
			}}, 
				{ label: '操作', name: 'operation', width: 40, formatter: function(value, options, row){
				var oper = "";
				if (auditing) {
					oper += "<a onClick=\"javascript:vm.audit("+options.rowId+");\" title='审批' ><i class=\"fa fa-ravelry\" style='color:violet;'></i></a>&nbsp;&nbsp;";
				} else {
					oper += "<a onClick=\"javascript:vm.showDetail("+options.rowId+");\" title='查看' ><i class=\"fa fa-info-circle\" style='color:violet;'></i></a>&nbsp;&nbsp;";
				}
				return oper;
			}},
        ],
        postData: {'auditing' : true},
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
    
    $.get("../basecode/selectByType/project", function(r) {
		if (r.code != 0) {
			alert(r.msg);
			vm.reload();
		}
		// 填充数据到页面
		$('#project').combobox({
			readonly: false,
		    data: r.data,
		    valueField:'no',
		    textField:'cnm',
		    panelHeight: 'auto',
		    editable: false,
   			onChange:function(newValue, oldValue){
   				vm.q.project = newValue;
   		    }
		});
	});
    
    // 给“审批中”按钮添加事件
    $("#auditing").on("click", function(){
    	auditing = true;
    	vm.reload();
    });
    // 给“已结束”按钮添加事件
    $("#auditFinish").on("click", function(){
    	auditing = false;
//		var page = $("#jqGrid").jqGrid('getGridParam','page');
//		$("#jqGrid").jqGrid('setGridParam',{ 
//			postData : {
//				'auditing' : auditing
//			},
//            page:page
//        }).trigger("reloadGrid");
    	vm.reload();
    });
    
});

function fold (_this){
	var $ele = $(_this);
	var isfold = $ele.text() == '—' ? true :false; // 是否折叠
	$ele.text(isfold ? '+':'—');
	
	var _tr = $ele.parent().parent();
	if (isfold) {
		hideChild(_tr);
		_tr.show();		
	} else {
		showChild(_tr);
	}
}
function hideChild(_tr) {
	
	var _code = $(_tr).attr('code');
	var children = $("[pcode='"+_code+"']");
	if (children == null || children.length < 1) { // 没有子项，直接隐藏自己
		$(_tr).hide();
	} else {
		$.each(children,function(i,n){
			hideChild(n);
		});
		$(_tr).hide();
	}
}
function showChild(_tr) {
	
	var _code = $(_tr).attr('code');
	var children = $("[pcode='"+_code+"']");
	if (children == null || children.length < 1) { // 没有子项，直接显示自己
		$(_tr).show();
	} else {
		$.each(children,function(i,n){
			showChild(n);
		});
		$(_tr).show();
	}
}

var budgetDetail = {};
var init = true;
var initBusinessIncomeBudgetDetail = true;
var initCostBudgetDetail = true;
var initSee = true;
var currentRowId1 = '';
var currentRowId2 = '';
var currentRowId3 = '';
var businessIncomeBudgetRowCount = 0;
var costBudgetRowCount = 0;
var seeRowCount = 0;
var auditing = true;


var vm = new Vue({
	el:'#rrapp',
	data:{
		q: {project: null},
		user:{},
		showList: true,
		upd: false,
		title: null,
		budget: {status:1,person:'',personName:''}
	},
	methods: {
		getUser: function(){
			$.getJSON("../sys/user/info?_"+$.now(), function(r){
				vm.user = r.user;
			});
		},
		query: function () {
			vm.reload();
		},
		getProject : function(id) {
			$.get("../basecode/selectByType/project", function(r) {
				if (r.code != 0) {
					alert(r.msg);
					vm.reload();
				}
				// 填充数据到页面
				$('#project').combobox({
					readonly: false,
				    data: r.data,
				    valueField:'no',
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
			vm.budget = {status:1,person:'',personName:''};
			
			vm.getUser(); // 获取当前登录用户
			vm.getProject(); // 所属项目
			$("#budget-detail-form input").val("");
		},
		showDetail: function (id) {
			vm.audit(id);
			vm.upd = false;
			$("#pass-div").hide();
			$("#rmk-div").hide();
		},
		audit: function (id) {
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "审核";
            vm.upd = true;
            
            vm.getInfo(id);
            
            // 申报人员
            $.get("../sys/user/info/" + vm.budget.person, function(r){
				if(r.code == 0) {
					var user = r.user;
					$("input[name='personName']").val(user.name);
					$("input[name='mobile']").val(user.mobile);
				}else{
					alert(r.msg);
				}
			});
            // 月份
            $("input[name='month']").val(vm.budget.month);
            // 所属项目
			$('#project1').combobox({
				readonly: false,
			    data: [{'key':vm.budget.projectName, 'value':'', 'selected':true}],
			    valueField:'value',
			    textField:'key',
			    panelHeight: 'auto',
			    editable: false,
	   			onChange:function(newValue, oldValue) {}
			});
			// 是否通过
			$('#pass').combobox({
				readonly: false,
				data: [{'key':'通过', 'value':'1'}, {'key':'不通过', 'value':'0'}],
				valueField:'value',
				textField:'key',
				panelHeight: 'auto',
				editable: false,
				onChange:function(newValue, oldValue) {}
			});
            
		},
		doAudit: function () {
			var pass = $("#pass").val();
			var rmk = $("#rmk").val();
			if (pass == null || rmk==null || pass=="" || rmk == "") {
				alert("请选择是否通过并填写补充说明");
				return;
			}
			var _data = {};
			_data['budget'] = vm.budget.id; // 哪一个预算审核
			_data['pass'] = pass; // 是否通过
			_data['rmk'] = rmk; // 补充说明
			_data['schedule'] = vm.budget.schedule; // 当前进度
			$.ajax({
				type: "GET",
				async: false,
			    url: "../sys/user/info?_"+$.now(),
			    success: function(r){
			    	if(r.code === 0){
						_data['person'] = r.user.id; // 谁审核的
					}else{
						alert(r.msg);
					}
				}
			});
			
			$.ajax({
				type: "POST",
				url: "../budgetaudit/doAudit?",
				data: JSON.stringify(_data),
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
		saveOrUpdate: function (event) {
			var url = "#";
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
		},
		del: function (id) {
			confirm('确定要删除选中的记录？', function(){
		    	$.get("../budget/delete/"+id, function(r){
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
			   type: "POST",
			   url: "../budgetaudit/info/" + id,
			   async: false,
			   success: function(r) {
				   vm.budget = r.budget;
			   }
			});
		},
		seeSchedule: function (rowId) {
			$.ajax({
				type: "GET",
				url: "../budget/seeSchedule/" + rowId,
				//async: false,
				success: function(r) {
					if (r.code != 0) {
						alert(r.msg);
						vm.reload();
					}
					var data = r.data[1];
					if (data.length > 0) {
						// 进度条
						//所有步骤的数据
						var stepListJson = new Array();
						stepListJson.push({
							StepNum: 1,
							StepText: parseDate(r.data[0]).Format("MM-dd"),
							text:"√"
						});
						var num = 2;
						data.forEach(function(e){
							var tmp = {};
							tmp['StepNum'] = num++;
							tmp['StepText'] = parseDate(e.updtm).Format("MM-dd");
							tmp['text'] = e.pass?"√" : "×";
							stepListJson.push(tmp);
						});
						for (var i=data.length+2;i<=5;i++) {
							stepListJson.push({
				                StepNum: i,
				                StepText: "",
				                text:""
				            });
						}
						debugger
				        //new一个工具类
				        var StepTool = new Step_Tool_dc("schedule-detail-progress-bar", "null");
				        //使用工具对页面绘制相关流程步骤图形显示
				        StepTool.drawStep(data.length+1, stepListJson);
				        
						// 填充主体
						$(".schedule-detail-content").empty();
						data.forEach(function (e) {
							var passText = e.pass?"审批通过" : "不通过";
							var item = '<div class="schedule-detail-content-item">'+
								'<div class="item-content">'+
									'<div class="item-content-1" style="background: URL(\'/statics/img/head.jpg\') no-repeat;">'+
										'<div class="item-content-person">'+ e.personEntity.name +'</div>'+
										'<div class="item-content-time">'+ parseDate(e.updtm).Format("MM-dd hh:mm") +'</div>'+
									'</div>'+
									'<div class="item-content-2">'+ e.rmk +'</div>'+
									'<div class="item-content-3">'+
										'<div class="item-content-origin">来自：'+ e.scheduleText +'</div>'+
										'<div>'+ passText +'</div>'+
									'</div>'+
								'</div>'+
							'</div>';
							$(".schedule-detail-content").append(item);
						});
						layer.open({
							type : 1,
							offset : '50px',
							skin : 'layui-layer-molv',
							title : "进度详情",
							area : [ '500px', '600px' ],
							shade : 0,
							shadeClose : false,
							content : jQuery("#schedule-detail"),
							btn : [ '关闭' ],
							btn1 : function(index) {
								layer.close(index);
							}
						});
					} else {
						alert("暂无审核意见");
					}
				}
			});
			
		},
		see: function () { // 
			if(currentRowId3 != vm.budget.id || initSee) { // 第一次进入才渲染页面
				currentRowId3 = vm.budget.id;
				initSee = false;
				$("#seeDetailTable").empty();
				var dic = ['一','二','三','四','五','六','七','八','九','十'];
				var columnCount; // 总列数
				
				$.ajax({
					type: "GET",
					url: "../budget/seeDetailTable/" + vm.budget.id,
					async: false,
					success: function(r) {
						
						if (r.code != 0) {
							alert(r.msg);
							vm.reload();
						}
						var level = r.data[0]; // 全部项
						var data = r.data[1];    // 初始金额与新增的金额
						var hasAdd = data.length > 1; // 是否有新增
						var totalMoney = r.data[2]; // 初始金额与新增的金额和，如果没有新增的话这里就是undefined
						
						var levelColumnCount = level.length -1 ; // 全部项一共占多少列，注意第一层不占列
						var dataColumnCount = data.length;       // 全部数据一共有多少列
						columnCount = levelColumnCount + dataColumnCount; // 总列数
						if (hasAdd) { // 如果有总金额列
							columnCount += 1; 
						}
						
					    // 取出第一层
						var firstLevel = level[0];
						$.each(firstLevel,function(i,e){
							$("#seeDetailTable").append("<tr style=\"text-align:left;font-weight: bold;color:RGB(43,131,57);\" code='"+e.code+"' pcode='"+e.pcode+"'><td colspan='"+columnCount+"'>"+e.cnm+"</td></tr>");
						});
						// 处理第二层
						var secondLevel = level[1];
						seeRowCount = secondLevel.length;
						for(var j = secondLevel.length-1; j!=-1; j--) {
							var e = secondLevel[j];
							var _tr = "<tr code='"+e.code+"' pcode='"+e.pcode+"'>";
							if(e.hasChild) { // 有子项
								_tr += "<td colspan='"+columnCount+"' style=\"font-weight: bold;\">"+e.cnm+"<span class=\"fold\" onclick=\"fold(this)\">—</span></td>";
							} else {         // 没有子项
								_tr += "<td style=\"font-weight: bold;\">"+e.cnm+"</td>"; // 第一列
								for (var i=1; i<levelColumnCount; i++){                   // 填充中间的空白列 
									_tr += "<td></td>";
								}
								if (!hasAdd) { // 如果没有新增
									var _data = data[0];
									_tr += "<td><input name='"+e.code+"-"+e.pcode+"' type='text' " + (_data[e.code] ? 'value="'+_data[e.code]['money']+'"' : '') + "/>元</td>";
								} else {       // 如果有新增
									for (var k = 0; k<data.length; k++) {
										_tr += "<td><input name='"+e.code+"-"+e.pcode+"' type='text' " + (data[k][e.code] ? 'value="'+data[k][e.code]['money']+'"' : '') + "/>元</td>";
									}
									// 总金额
									_tr += "<td><input name='"+e.code+"-"+e.pcode+"' type='text' " + (totalMoney[e.code] ? 'value="'+totalMoney[e.code]+'"' : '') + "/>元</td>";
								}
							}
							_tr +="</tr>";
							$("#seeDetailTable [code='"+e.pcode+"']").after(_tr);
						}
						
						// 第3层及之后的层
						for (var currentLevel = 2; currentLevel<levelColumnCount+1; currentLevel++) {
							var list = level[currentLevel]; // 一次取一级的数据
							for(var j = list.length-1; j!=-1; j--) {
								var e = list[j];
								var _level = e.level; // 当前元素在第几级
								var hasChild = e.hasChild; // 当前元素是否有子集
								var _tr = "<tr code='"+e.code+"' pcode='"+e.pcode+"'>";
								for (var i=2;i<=levelColumnCount+1;i++){
									if(i==_level) {
										_tr += "<td>"+e.cnm+"</td>";
									} else {
										_tr += "<td></td>";
									}
								}
								if(hasChild) { // 有子项
									for (var i =0; i<columnCount-levelColumnCount; i++ ) {
										_tr += "<td></td>";
									}
								} else { // 没有子项
									if (!hasAdd) { // 没有新增
										var _data = data[0];
										_tr += "<td><input name='"+e.code+"-"+e.pcode+"' type='text' " + (_data[e.code] ? 'value="'+_data[e.code]['money']+'"' : '') + "/>元</td>";
									} else { // 有新增
										for (var k = 0; k<data.length; k++) {
											_tr += "<td><input name='"+e.code+"-"+e.pcode+"' type='text' " + (data[k][e.code] ? 'value="'+data[k][e.code]['money']+'"' : '') + "/>元</td>";
										}
										// 总金额
										_tr += "<td><input name='"+e.code+"-"+e.pcode+"' type='text' " + (totalMoney[e.code] ? 'value="'+totalMoney[e.code]+'"' : '') + "/>元</td>";
									}
								}
								_tr +="</tr>";
								$("#seeDetailTable [code='"+e.pcode+"']").after(_tr);
							}
						}
						
	//					// 表头   最后再加
						var header = "<tr style=\"font-weight: bold;\"><td>款项（二级）</td>";
						for (var i=0;i<levelColumnCount-1;i++) {
							header += "<td>细分款项（"+dic[i+2]+"级）</td>";
						}
						header += "<td>预算金额</td>";
						if (hasAdd) {
							for (var i=0;i<dataColumnCount-1;i++) {
								header += "<td>新增预算</td>";
							}
							header += "<td>总计</td>";
						}
						header += "</tr>";
						
						$("#seeDetailTable [pcode='-1']").after(header);
					}
				});
			}
			var _height = seeRowCount*50 + 150;
			_height = _height>650 ? 650 : _height;
			// 显示
			layer.open({
				type : 1,
				offset : '50px',
				skin : 'layui-layer-molv',
				title : "预算明细",
				area : [ columnCount*147 + 'px', _height + 'px' ],
				shade : 0,
				shadeClose : false,
				content : jQuery("#seeDetail"),
				btn : [ '关闭' ],
				btn1 : function(index) {
					layer.close(index);
				}
			});
		},
		showCostBudgetDetail: function (rowId) { // 
			if(currentRowId2 != rowId || initCostBudgetDetail) { // 第一次进入才渲染页面
				currentRowId2 = rowId;
				initCostBudgetDetail = false;
				$("#costBudgetDetailTable").empty();
				var dic = ['一','二','三','四','五','六','七','八','九','十'];
				var columnCount;
				// 营业收入预算
				$.ajax({
					type: "GET",
					url: "../budget/costBudgetDetailTable/" + rowId,
					async: false,
					success: function(r) {
						
						if (r.code != 0) {
							alert(r.msg);
							vm.reload();
						}
						var level = r.data[0]; // 全部项
						var data = r.data[1];    // 初始金额与新增的金额
						var hasAdd = data.length > 1; // 是否有新增
						var totalMoney = r.data[2]; // 初始金额与新增的金额和，如果没有新增的话这里就是undefined
						
						var levelColumnCount = level.length -1 ; // 全部项一共占多少列，注意第一层不占列
						var dataColumnCount = data.length;       // 全部数据一共有多少列
						columnCount = levelColumnCount + dataColumnCount; // 总列数
						if (hasAdd) { // 如果有总金额列
							columnCount += 1; 
						}
						
					    // 取出第一层
						var firstLevel = level[0];
						$.each(firstLevel,function(i,e){
							$("#costBudgetDetailTable").append("<tr style=\"text-align:left;font-weight: bold;color:RGB(43,131,57);\" code='"+e.code+"' pcode='"+e.pcode+"'><td colspan='"+columnCount+"'>"+e.cnm
									+"<span style=\"float: right;\">合计："+r.data[r.data.length-1][i]+"元<span></td></tr>");
							// 加一个空行
							$("#costBudgetDetailTable").append("<tr><td colspan='"+columnCount+"'><span style=\"visibility:hidden\">.</span></td></tr>");
//							$("#costBudgetDetailTable").append("<tr style=\"text-align:left;font-weight: bold;color:RGB(43,131,57);\" code='"+e.code+"' pcode='"+e.pcode+"'><td colspan='"+columnCount+"'>"+e.cnm+"</td></tr>");
						});
						// 处理第二层
						var secondLevel = level[1];
						costBudgetRowCount = secondLevel.length;
						for(var j = secondLevel.length-1; j!=-1; j--) {
							var e = secondLevel[j];
							var _tr = "<tr code='"+e.code+"' pcode='"+e.pcode+"'>";
							if(e.hasChild) { // 有子项
								_tr += "<td colspan='"+columnCount+"' style=\"font-weight: bold;\">"+e.cnm+"<span class=\"fold\" onclick=\"fold(this)\">—</span></td>";
							} else {         // 没有子项
								_tr += "<td style=\"font-weight: bold;\">"+e.cnm+"</td>"; // 第一列
								for (var i=1; i<levelColumnCount; i++){                   // 填充中间的空白列 
									_tr += "<td></td>";
								}
								if (!hasAdd) { // 如果没有新增
									var _data = data[0];
									_tr += "<td><input name='"+e.code+"-"+e.pcode+"' type='text' " + (_data[e.code] ? 'value="'+_data[e.code]['money']+'"' : '') + "/>元</td>";
								} else {       // 如果有新增
									for (var k = 0; k<data.length; k++) {
										_tr += "<td><input name='"+e.code+"-"+e.pcode+"' type='text' " + (data[k][e.code] ? 'value="'+data[k][e.code]['money']+'"' : '') + "/>元</td>";
									}
									// 总金额
									_tr += "<td><input name='"+e.code+"-"+e.pcode+"' type='text' " + (totalMoney[e.code] ? 'value="'+totalMoney[e.code]+'"' : '') + "/>元</td>";
								}
							}
							_tr +="</tr>";
							$("#costBudgetDetailTable [code='"+e.pcode+"']").after(_tr);
						}
						
						// 第3层及之后的层
						for (var currentLevel = 2; currentLevel<levelColumnCount+1; currentLevel++) {
							var list = level[currentLevel]; // 一次取一级的数据
							for(var j = list.length-1; j!=-1; j--) {
								var e = list[j];
								var _level = e.level; // 当前元素在第几级
								var hasChild = e.hasChild; // 当前元素是否有子集
								var _tr = "<tr code='"+e.code+"' pcode='"+e.pcode+"'>";
								for (var i=2;i<=levelColumnCount+1;i++){
									if(i==_level) {
										_tr += "<td>"+e.cnm+"</td>";
									} else {
										_tr += "<td></td>";
									}
								}
								if(hasChild) { // 有子项
									for (var i =0; i<columnCount-levelColumnCount; i++ ) {
										_tr += "<td></td>";
									}
								} else { // 没有子项
									if (!hasAdd) { // 没有新增
										var _data = data[0];
										_tr += "<td><input name='"+e.code+"-"+e.pcode+"' type='text' " + (_data[e.code] ? 'value="'+_data[e.code]['money']+'"' : '') + "/>元</td>";
									} else { // 有新增
										for (var k = 0; k<data.length; k++) {
											_tr += "<td><input name='"+e.code+"-"+e.pcode+"' type='text' " + (data[k][e.code] ? 'value="'+data[k][e.code]['money']+'"' : '') + "/>元</td>";
										}
										// 总金额
										_tr += "<td><input name='"+e.code+"-"+e.pcode+"' type='text' " + (totalMoney[e.code] ? 'value="'+totalMoney[e.code]+'"' : '') + "/>元</td>";
									}
								}
								_tr +="</tr>";
								$("#costBudgetDetailTable [code='"+e.pcode+"']").after(_tr);
							}
						}
						
	//					// 表头   最后再加
						var header = "<tr style=\"font-weight: bold;\"><td>款项（二级）</td>";
						for (var i=0;i<levelColumnCount-1;i++) {
							header += "<td>细分款项（"+dic[i+2]+"级）</td>";
						}
						header += "<td>预算金额</td>";
						if (hasAdd) {
							for (var i=0;i<dataColumnCount-1;i++) {
								header += "<td>新增预算</td>";
							}
							header += "<td>总计</td>";
						}
						header += "</tr>";
						
						$("#costBudgetDetailTable [pcode='-1']").after(header);
						$("#costBudgetDetailTable tr").last().remove();
					}
				});
			}
			var _height = costBudgetRowCount*50 + 150;
			_height = _height>650 ? 650 : _height;
			// 显示
			layer.open({
				type : 1,
				offset : '50px',
				skin : 'layui-layer-molv',
				title : "预算明细",
				area : [ columnCount*147 + 'px', _height + 'px' ],
				shade : 0,
				shadeClose : false,
				content : jQuery("#costBudgetDetail"),
				btn : [ '关闭' ],
				btn1 : function(index) {
					layer.close(index);
				}
			});
		},
		showBusinessIncomeBudgetDetail: function (rowId) { // businessIncomeBudgetDetailTable
			
			if(currentRowId1 != rowId || initBusinessIncomeBudgetDetail) { // 第一次进入才渲染页面
				currentRowId1 = rowId;
				initBusinessIncomeBudgetDetail = false;
				$("#businessIncomeBudgetDetailTable").empty();
				var dic = ['一','二','三','四','五','六','七','八','九','十'];
				var columnCount;
				// 营业收入预算
				$.ajax({
					type: "GET",
					url: "../budget/businessIncomeBudgetDetail/" + rowId,
					async: false,
					success: function(r) {
					
						if (r.code != 0) {
							alert(r.msg);
							vm.reload();
						}
						var level = r.data[0]; // 全部项
						var data = r.data[1];    // 初始金额与新增的金额
						var hasAdd = data.length > 1; // 是否有新增
						var totalMoney = r.data[2]; // 初始金额与新增的金额和，如果没有新增的话这里就是undefined
						
						var levelColumnCount = level.length -1 ; // 全部项一共占多少列，注意第一层不占列
						var dataColumnCount = data.length;       // 全部数据一共有多少列
						columnCount = levelColumnCount + dataColumnCount; // 总列数
						if (hasAdd) { // 如果有总金额列
							columnCount += 1; 
						}
						
						
					    // 取出第一层
						var firstLevel = level[0];
						$.each(firstLevel,function(i,e){
							$("#businessIncomeBudgetDetailTable").append("<tr style=\"text-align:left;font-weight: bold;color:RGB(43,131,57);\" code='"+e.code+"' pcode='"+e.pcode+"'><td colspan='"+columnCount+"'>"+e.cnm+"</td></tr>");
						});
						// 处理第二层
						var secondLevel = level[1];
						businessIncomeBudgetRowCount = secondLevel.length;
						for(var j = secondLevel.length-1; j!=-1; j--) {
							var e = secondLevel[j];
							var _tr = "<tr code='"+e.code+"' pcode='"+e.pcode+"'>";
							if(e.hasChild) { // 有子项
								_tr += "<td colspan='"+columnCount+"' style=\"font-weight: bold;\">"+e.cnm+"<span class=\"fold\" onclick=\"fold(this)\">—</span></td>";
							} else {         // 没有子项
								_tr += "<td style=\"font-weight: bold;\">"+e.cnm+"</td>"; // 第一列
								for (var i=1; i<levelColumnCount; i++){                   // 填充中间的空白列 
									_tr += "<td></td>";
								}
								if (!hasAdd) { // 如果没有新增
									var _data = data[0];
									_tr += "<td><input name='"+e.code+"-"+e.pcode+"' type='text' " + (_data[e.code] ? 'value="'+_data[e.code]['money']+'"' : '') + "/>元</td>";
								} else {       // 如果有新增
									for (var k = 0; k<data.length; k++) {
										_tr += "<td><input name='"+e.code+"-"+e.pcode+"' type='text' " + (data[k][e.code] ? 'value="'+data[k][e.code]['money']+'"' : '') + "/>元</td>";
									}
									// 总金额
									_tr += "<td><input name='"+e.code+"-"+e.pcode+"' type='text' " + (totalMoney[e.code] ? 'value="'+totalMoney[e.code]+'"' : '') + "/>元</td>";
								}
							}
							_tr +="</tr>";
							$("#businessIncomeBudgetDetailTable [code='"+e.pcode+"']").after(_tr);
						}
						
						// 第3层及之后的层
						for (var currentLevel = 2; currentLevel<levelColumnCount+1; currentLevel++) {
							var list = level[currentLevel]; // 一次取一级的数据
							for(var j = list.length-1; j!=-1; j--) {
								var e = list[j];
								var _level = e.level; // 当前元素在第几级
								var hasChild = e.hasChild; // 当前元素是否有子集
								var _tr = "<tr code='"+e.code+"' pcode='"+e.pcode+"'>";
								for (var i=2;i<=levelColumnCount+1;i++){
									if(i==_level) {
										_tr += "<td>"+e.cnm+"</td>";
									} else {
										_tr += "<td></td>";
									}
								}
								if(hasChild) { // 有子项
									for (var i =0; i<columnCount-levelColumnCount; i++ ) {
										_tr += "<td></td>";
									}
								} else { // 没有子项
									if (!hasAdd) { // 没有新增
										var _data = data[0];
										_tr += "<td><input name='"+e.code+"-"+e.pcode+"' type='text' " + (_data[e.code] ? 'value="'+_data[e.code]['money']+'"' : '') + "/>元</td>";
									} else { // 有新增
										for (var k = 0; k<data.length; k++) {
											_tr += "<td><input name='"+e.code+"-"+e.pcode+"' type='text' " + (data[k][e.code] ? 'value="'+data[k][e.code]['money']+'"' : '') + "/>元</td>";
										}
										// 总金额
										_tr += "<td><input name='"+e.code+"-"+e.pcode+"' type='text' " + (totalMoney[e.code] ? 'value="'+totalMoney[e.code]+'"' : '') + "/>元</td>";
									}
								}
								_tr +="</tr>";
								$("#businessIncomeBudgetDetailTable [code='"+e.pcode+"']").after(_tr);
							}
						}
						
	//					// 表头   最后再加
						var header = "<tr style=\"font-weight: bold;\"><td>款项（二级）</td>";
						for (var i=0;i<levelColumnCount-1;i++) {
							header += "<td>细分款项（"+dic[i+2]+"级）</td>";
						}
						header += "<td>预算金额</td>";
						if (hasAdd) {
							for (var i=0;i<dataColumnCount-1;i++) {
								header += "<td>新增预算</td>";
							}
							header += "<td>总计</td>";
						}
						header += "</tr>";
						
						$("#businessIncomeBudgetDetailTable [pcode='-1']").after(header);
						
						// 预算合计
						$("#businessIncomeBudgetDetailTable").append("<tr style=\"text-align: right;font-weight: bold;\"><td colspan='"+columnCount+"'>预算合计：" + r.data[r.data.length-1] + "元&nbsp;&nbsp;&nbsp;</td></tr>");
					}
				});
			}
			var _height = businessIncomeBudgetRowCount*50 + 150;
			_height = _height>650 ? 650 : _height;
			// 显示
			layer.open({
				type : 1,
				offset : '50px',
				skin : 'layui-layer-molv',
				title : "预算明细",
				area : [ columnCount*147 + 'px',  _height + 'px' ],
				shade : 0,
				shadeClose : false,
				content : jQuery("#businessIncomeBudgetDetail"),
				btn : [ '关闭' ],
				btn1 : function(index) {
					layer.close(index);
				}
			});
		},
		reload: function (event) {
			vm.showList = true;
			vm.getUser();
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData : {
					'project': vm.q.project,
					'auditing' : auditing
				},
                page:page
            }).trigger("reloadGrid");
		}
	}
});
