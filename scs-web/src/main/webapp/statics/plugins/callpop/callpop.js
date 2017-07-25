$(function popscreen() {
    var data;
    $.ajax({
        type: 'POST',
        url: '../callpopsel/calllist',
        async: false,
        success: function (r) {
            if (!$.isPlainObject(r)) {
                var r = JSON.parse(r);
            }
            if (typeof (r.callphone) != "undefined" && r.callphone != '0') {
                var str = 'interfaces' + 'cti' + 'popscreen' + r.callphone;
                var appkey = 'bcc784b371a99cd6dae5ac2b8707ef03';
                var secret = 'SWiU28';
                var time = Date.parse(new Date());
                var sign = md5(appkey + str + secret + time);
                var url = 'http://api.mixcaller.com/?m=interfaces&c=cti&a=index&act=popscreen&appkey=' + appkey + '&sign=' + sign + '&time=' + time + '&extension=' + r.callphone + '&open_type=2&mixcallback=mycallpop';
                $.ajax({
                    type: 'GET',
                    url: url,
                    cache: false,
                    dataType: 'jsonp',
                    jsonp: 'callback',
                    success: function (t) {
                        datas = t;
                    }
                });
            }
        }
    });
});

// 弹屏方法
function mycallpop(data) {
    top.layer.open({
        title: '通话弹屏',
        type: 2,
        shade: 0,
        content: '../callpopsel/callpopsel.html?' + data,
        area: ['1400px', '700px'],
        maxmin: true
    });
};