$(function () {
    $("#jqGrid").jqGrid({
        url: '../jobwarning/list',
        datatype: "json",
        colModel: [
            // 隐藏主键
            {label: '主键id', name: 'id', width: 50, key: true, hidden: true},
            {
                label: '所属项目部', name: 'projectText', width: 80, formatter: function (value, options, row) {
                return $.isPlainObject(value) ? value.value : value;
            }
            },
            {
                label: '所属板块', name: 'businessText', width: 80, formatter: function (value, options, row) {
                return $.isPlainObject(value) ? value.value : value;
            }
            },
            {
                label: '业务类型', name: 'btypeText', width: 80, formatter: function (value, options, row) {
                return $.isPlainObject(value) ? value.value : value;
            }
            },
            {
                label: '预警类型', name: 'wtypeText', width: 80, formatter: function (value, options, row) {
                return $.isPlainObject(value) ? value.value : value;
            }
            },
            /*	{ label: '是否推送本人', name: 'flag', width: 80 , formatter: function(value, options, row){
                return $.isPlainObject(value) ? value.value : value;
            }},
                { label: '时间设置', name: 'intv', width: 80 , formatter: function(value, options, row){
                return $.isPlainObject(value) ? value.value : value;
            }},*/
            {
                label: '修改人', name: 'updidText', width: 80, formatter: function (value, options, row) {
                return $.isPlainObject(value) ? value.value : value;
            }
            },
            {
                label: '修改时间', name: 'updtm', width: 80, formatter: function (value, options, row) {
                return $.isPlainObject(value) ? value.value : value;
            }
            },
            {
                label: '操作', name: 'operation', width: 40, formatter: function (value, options, row) {
                var oper = "<a onClick=\"javascript:vm.update(" + options.rowId + ", false);\" title='查看' ><i class=\"fa fa-info-circle\" style='color:violet;'></i></a>&nbsp;&nbsp;";
                oper += update ? "<a onClick=\"javascript:vm.update(" + options.rowId + ", true);\" title='修改'  ><i class=\"fa fa-pencil-square-o\" style='color:green;'></i></a>&nbsp;&nbsp;" : "";
                oper += del ? "<a onClick=\"javascript:vm.del(" + options.rowId + ");\" title='删除'  ><i class=\"fa fa-trash-o\" style='color:red;'></i></a>" : "";
                return oper;
            }
            },
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: false,
        rownumWidth: 25,
        autowidth: true,
        multiselect: false,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
    // 表单验证
    validate = $(".form-horizontal").validate({
        onfocusout: false,
        onkeyup: false,
        rules: {
            busseg: {
                required: true
            },
            project: {
                required: true
            },
            time: {
                required: true
            },
            banci: {
                required: true
            }
        },
        messages: {
            busseg: {
                required: "业务板块必选!"
            },
            project: {
                required: "项目部必选"
            },
            time: {
                required: "月份必选!"
            },
            banci: {
                required: "班次必选!"
            }
        },
        showErrors: function (errorMap, errorList) {
            if (errorList && errorList.length > 0) { // 如果存在错误信息
                layer.msg(errorList[0].message, function () {
                });
            }
        },
    });
    // 获取项目部
    var data = Util.loadData("../gridmng/getUserProjectMsg").data;
    $('#project').combobox({
        readonly: false,
        data: data,
        valueField: 'id',
        textField: 'name',
        value: data[0].id,
        editable: false,
        panelHeight: 'auto',
        onSelect: function (t) {
            vm.pop.project = t.id;
        }
    });
    //获取业务板块
    $('#serviceType').combobox({
        readonly: true,
        disabled: true,
        data: Util.loadData("../basecode/selectByType/" + 'biztype').data,
        valueField: 'no',
        textField: 'cnm',
        editable: false,
        value: 0,
        panelHeight: 'auto',
        onSelect: function (t) {
            vm.pop.servicetype = t.id;
        }
    });
    // 获取业务类型
    $('#businesstype').combobox({
        readonly: false,
        data: Util.loadData('../basecode/selectByType/' + 'businesstype').data,
        valueField: 'no',
        textField: 'cnm',
        value: '请选择',
        editable: false,
        panelHeight: 'auto',
        onSelect: function (t) {
            vm.pop.businesstype = t.id;
            ewtype(t.attv);
            $('#ewtype').combo('enable');
        },
        onLoadSuccess: function () {
            $('#ewtype').combo('disable');
        }
    });

    // 获取预警类型
    function ewtype(attv) {
        $('#ewtype').combobox({
            readonly: false,
            data: Util.loadData('../basecode/selectByType/' + attv).data,
            valueField: 'no',
            textField: 'cnm',
            value: '请选择',
            editable: false,
            panelHeight: 'auto',
            onSelect: function (t) {
                vm.pop.ewtype = t.id;
            },

        });
    }

    // 获取是否推送本人
    $('#flag').combobox({
        readonly: false,
        data: Util.loadData('../basecode/selectByType/' + 'oneself').data,
        valueField: 'no',
        textField: 'cnm',
        value: '请选择',
        editable: false,
        panelHeight: 'auto',
        onSelect: function (t) {
            vm.pop.flag = t.id;
        }
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        upd: false,
        title: null,
        pop: {
            project: null,
            servicetype: null,
            businesstype: null,
            ewtype: null,
            flag: null,

        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.upd = true;
            vm.title = "新增";
        },
        update: function (id, isupdate) {
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.upd = isupdate;
            vm.title = isupdate ? "修改" : "查看";

            vm.getInfo(id);
        },
        saveOrUpdate: function (event) {
            var url = vm.jobwarning.id == null ? "../jobwarning/save" : "../jobwarning/update";
            $.ajax({
                type: "POST",
                url: url,
                data: JSON.stringify(vm.pop),
                success: function (r) {
                    if (r.code === 0) {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        del: function (id) {
            confirm('确定要删除选中的记录？', function () {
                $.get("../jobwarning/delete/" + id, function (r) {
                    if (r.code == 0) {
                        $("#jqGrid").trigger("reloadGrid");
                        top.layer.closeAll();
                    } else {
                        alert(r.msg);
                    }
                });
            });
        },
        getInfo: function (id) {
            $.get("../jobwarning/info/" + id, function (r) {
                vm.jobwarning = r.jobwarning;
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                page: page
            }).trigger("reloadGrid");
        }
    }
});
