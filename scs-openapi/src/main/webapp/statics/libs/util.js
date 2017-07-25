/**
 * 控制台日志打印
 * 
 * 使用方法 $.log(message); //message 需要打印的数据 可选log、debugger、info、error 四个级别数据进行打印
 * 
 * 
 */
; (function($, undefined) {
    $.extend({
        log: function(message) {
            var now = new Date(),
            y = now.getFullYear(),
            m = now.getMonth() + 1,
            // ！JavaScript中月分是从0开始的
            d = now.getDate(),
            h = now.getHours(),
            min = now.getMinutes(),
            s = now.getSeconds(),
            time = y + '/' + m + '/' + d + ' ' + h + ':' + min + ':' + s;
            console.log(time + ' ' + message);
        },
        debug: function(message) {
            var now = new Date(),
            y = now.getFullYear(),
            m = now.getMonth() + 1,
            // ！JavaScript中月分是从0开始的
            d = now.getDate(),
            h = now.getHours(),
            min = now.getMinutes(),
            s = now.getSeconds(),
            time = y + '/' + m + '/' + d + ' ' + h + ':' + min + ':' + s;
            console.debug(time + ' ' + message);
        },
        info: function(message) {
            var now = new Date(),
            y = now.getFullYear(),
            m = now.getMonth() + 1,
            // ！JavaScript中月分是从0开始的
            d = now.getDate(),
            h = now.getHours(),
            min = now.getMinutes(),
            s = now.getSeconds(),
            time = y + '/' + m + '/' + d + ' ' + h + ':' + min + ':' + s;
            console.info(time + ' ' + message);
        },
        /*error: function(message) {
            var now = new Date(),
            y = now.getFullYear(),
            m = now.getMonth() + 1,
            // ！JavaScript中月分是从0开始的
            d = now.getDate(),
            h = now.getHours(),
            min = now.getMinutes(),
            s = now.getSeconds(),
            time = y + '/' + m + '/' + d + ' ' + h + ':' + min + ':' + s;
            console.error(time + ' ' + message);
        }*/
    })
})(jQuery);

/**
* tips 提示插件 tips
*
* 使用方法
* $( selector ).tips({   //selector 为jquery选择器
*  msg:'your messages!',    //你的提示消息  必填
*  side:1,  //提示窗显示位置  1，2，3，4 分别代表 上右下左 默认为1（上） 可选
*  color:'#FFF', //提示文字色 默认为白色 可选
*  bg:'#F00',//提示窗背景色 默认为红色 可选
*  time:2,//自动关闭时间 默认2秒 设置0则不自动关闭 可选
*  x:0,//横向偏移  正数向右偏移 负数向左偏移 默认为0 可选
*  y:0,//纵向偏移  正数向下偏移 负数向上偏移 默认为0 可选
* })
*
*
*/
;(function ($) {
    $.fn.tips = function(options){
        var defaults = {
            side:1,
            msg:'',
            color:'#FFF',
            bg:'#F00',
            time:2,
            x: 0,
            y: 0
        }
        var options = $.extend(defaults, options);
        if (!options.msg||isNaN(options.side)) {
        throw new Error('params error');
    }
    if(!$('#jquery_tips_style').length){
        var style='<style id="jquery_tips_style" type="text/css">';
        style+='.jq_tips_box{padding:10px;position:absolute;overflow:hidden;display:inline;display:none;z-index:10176523;}';
        style+='.jq_tips_arrow{display:block;width:0px;height:0px;position:absolute;}';
        style+='.jq_tips_top{border-left:10px solid transparent;left:20px;bottom:0px;}';
        style+='.jq_tips_left{border-top:10px solid transparent;right:0px;top:18px;}';
        style+='.jq_tips_bottom{border-left:10px solid transparent;left:20px;top:0px;}';
        style+='.jq_tips_right{border-top:10px solid transparent;left:0px;top:18px;}';
        style+='.jq_tips_info{word-wrap: break-word;word-break:normal;border-radius:4px;padding:5px 8px;max-width:130px;overflow:hidden;box-shadow:1px 1px 1px #999;font-size:12px;cursor:pointer;}';
        style+='</style>';
        $(document.body).append(style);
    }
        this.each(function(){
            var element=$(this);
            var element_top=element.offset().top,element_left=element.offset().left,element_height=element.outerHeight(),element_width=element.outerWidth();
            options.side=options.side<1?1:options.side>4?4:Math.round(options.side);
            var sideName=options.side==1?'top':options.side==2?'right':options.side==3?'bottom':options.side==4?'left':'top';
            var tips=$('<div class="jq_tips_box"><i class="jq_tips_arrow jq_tips_'+sideName+'"></i><div class="jq_tips_info">'+options.msg+'</div></div>').appendTo(document.body);
            tips.find('.jq_tips_arrow').css('border-'+sideName,'10px solid '+options.bg);
            tips.find('.jq_tips_info').css({
                color:options.color,
                backgroundColor:options.bg
            });
            switch(options.side){
                case 1:
                    tips.css({
                        top:element_top-tips.outerHeight()+options.x,
                        left:element_left-10+options.y
                    });
                    break;
                case 2:
                    tips.css({
                        top:element_top-20+options.x,
                        left:element_left+element_width+options.y
                    });
                    break;
                case 3:
                    tips.css({
                        top:element_top+element_height+options.x,
                        left:element_left-10+options.y
                    });
                    break;
                case 4:
                    tips.css({
                        top:element_top-20+options.x,
                        left:element_left-tips.outerWidth()+options.y
                    });
                    break;
                default:
                	tips.css({
                        top:element_top-tips.outerHeight()+options.x,
                        left:element_left-10+options.y
                    });
                	break;
            }
            var closeTime;
            tips.fadeIn('fast').click(function(){
                clearTimeout(closeTime);
                tips.fadeOut('fast',function(){
                    tips.remove();
                })
            })
            if(options.time){
                closeTime=setTimeout(function(){
                    tips.click();
                },options.time*1000);
                tips.hover(function(){
                    clearTimeout(closeTime);
                },function(){
                    closeTime=setTimeout(function(){
                        tips.click();
                    },options.time*1000);
                })
            }
        });
        return this;
    };
})(jQuery);

/**
 * 提供工具类
 * 
 * 使用方法 $.isJson(obj); 判断obj是否json数据返回true false $.obj2Str(mwssage);
 * 将数据返回成string类型的数据，方便数据显示 $( selector ).form2json(); selector
 * 为jquery选择器，将from表单转换成json对象，根据name取值，name需要唯一，否则会被替换
 * 
 */
; (function($, undefined) {
    $.extend({
        isJson: function(obj) {
            return typeof(obj) == "object" && Object.prototype.toString.call(obj).toLowerCase() == "[object object]" && !obj.length;
        },
        obj2Str: function(message) {
            if ($.isJson(message)) {
                message = JSON.stringify(message);
            } else {
                message = message.toString();
            }
            return message;
        },
        cutstr: function(str, len, ext) {
            var temp, icount = 0,
            patrn = /[^\x00-\xff]/,
            strre = "";
            for (var i = 0; i < str.length; i++) {
                if (icount < len - 1) {
                    temp = str.substr(i, 1);
                    if (patrn.exec(temp) == null) {
                        icount = icount + 1
                    } else {
                        icount = icount + 2
                    }
                    strre += temp
                } else {
                    break;
                }
            }
            return strre + (ext ? ext: '');
        },
        jmap: function() {
            var map_ = new Object();
            map_.put = function(key, value) {
                map_[key + '_'] = value;
            };
            map_.get = function(key) {
                return map_[key + '_'];
            };
            map_.remove = function(key) {
                delete map_[key + '_'];
            };
            map_.keyset = function() {
                var ret = "";
                for (var p in map_) {
                    if (typeof p == 'string' && p.substring(p.length - 1) == "_") {
                        ret += ",";
                        ret += p.substring(0, p.length - 1);
                    }
                }
                if (ret == "") {
                    return ret.split(",");
                } else {
                    return ret.substring(1).split(",");
                }
            };
            return map_;
        }
    }),
    $.fn.form2json = function() {
        var json = {};
        this.each(function() {
            var fields = $(this).serializeArray();
            var _this = this;
            jQuery.each(fields,
            function(i, field) {
                if (field.value) {
                    if ($(_this).find("[name='" + field.name + "']:checkbox").length >= 1) {
                        if ($.isArray(json[field.name])) {
                            json[field.name].push(("" + field.value).trim());
                        } else {
                            json[field.name] = [("" + field.value).trim()];
                        }
                    } else {
                        json[field.name] = ("" + field.value).trim();
                    }
                }
            });
        });
        $.log($.obj2Str(json));
        return json;
    }
})(jQuery);

Util = {
    basePath: "/SmartCityService",
    // 系统路径
    /**
	 * 常量表
	 */

    consts: {
        STATUS_SUCCESS: "0",
        filePath: 'http://192.168.1.11:8081/fileService/app/upload/',
        downloadPath: 'http://192.168.1.11:8081/fileService/app/download',
        img: '*.jpg;*.png;*.gif;*.bmp;*.JPG;*.PNG;*.GIF;*.BMP;',
        excel: '*.xlsx;*.XLSX;*.xls;*.XLS',
        word: '*.docx;*.DOCX;*.doc;*.DOC',
        all: '*.*',
        office: '*.xls;*.xlsx;*.ppt;*.pptx;*.doc;*.docx;*.XLS;*.XLSX;*.PPT;*.PPTX;*.DOC;*.DOCX;',
        isadmin : false,
        ages: [],
    },

    request: function(options) {

        var options = $.extend({

            url: "/",

            async: true,

            model: {},

            onSuccess: function(jqXHR, textStatus, data) {

            },

            onError: function(jqXHR, textStatus, data, sendError) {

            }

        },
        options);

        if (jQuery.isPlainObject(options.model)) {

            if ("false" !== options.model["browser"]) {

                options.model["browser"] = "true";

            }

        }
        $.ajax(options.url, {

            async: options.async,

            type: "POST",

            contentType: "application/json; charset=UTF-8",

            data: typeof(options.model) !== "string" && jQuery.isPlainObject(options.model) ? JSON.stringify(options.model) : options.model,

            beforeSend: function(jqXHR, settings) {
                /**
				 * 可以显示请求动画
				 */
                /*layer.open({
                    time: 1000,
                    type: 3,
                    title: false
                });*/
            	/*layer.msg('加载中', {
          		  time: 1000,
        		  icon: 16,
        		  shade: 0.01
        		});*/
            	layer.load(0, {shade: 0.01,time: 1000});
                $.log("数据请求中...");
            },

            success: function(data, textStatus, jqXHR) {

                var outputModel = null;

                try {

                    outputModel = JSON.parse(jqXHR.responseText);

                } catch(e) {

                    // 回调错误处理方法
                    Util._onError(options, jqXHR, "PARSERERROR", {

                        message: "解析服务器返回数据失败（" + e + "），请联系管理员！",

                        detail_message: "返回数据为: " + jqXHR.responseText
                    });

                    return;

                }

                if (outputModel.code == Util.consts.STATUS_SUCCESS) {

                    // 回调成功处理方法
                    Util._onSuccess(options, jqXHR, "success", outputModel);
                } else { // if ( outputModel.status ==
                    // Util.consts.STATUS_ERROR ) {
                    // 回调错误处理方法
                    Util._onError(options, jqXHR, "error", outputModel);
                }
            },

            error: function(jqXHR, textStatus, errorThrown) {

                // 回调错误处理方法
                Util._onError(options, jqXHR, textStatus, {
                    message: errorThrown
                });
            },

            complete: function() {
                $.log("数据请求完成...");
            }
        });
    },

    /**
	 * 请求成功回调方法 (内部方法)
	 * 
	 * @param options
	 *            配置选项 (传递进来，避免多次调用request方法时出现引用错误)
	 * @param jqXHR
	 *            XMLHttpRequest
	 * @param testStatus
	 *            "success", "notmodified", "error", "timeout", "abort", or
	 *            "parsererror"
	 * @param data
	 *            服务器返回数据 json对象
	 */
    _onSuccess: function(options, jqXHR, textStatus, data) {
        if ($.isFunction(options.onSuccess)) {

            // 回调自定义的成功处理方法
            options.onSuccess(jqXHR, textStatus, data);

        }

    },

    /**
	 * 请求失败回调方法 (内部方法)
	 * 
	 * @param options
	 *            配置选项 (传递进来，避免多次调用request方法时出现引用错误)
	 * @param jqXHR
	 *            XMLHttpRequest
	 * @param testStatus
	 *            "success", "notmodified", "error", "timeout", "abort", or
	 *            "parsererror"
	 * @param data
	 *            服务器返回数据 json对象
	 */
    _onError: function(options, jqXHR, textStatus, data) {
        layer.open({
            title: '请求错误',
            btnAlign: 'c',
            resize: false,
            scrollbar: false,
            content: '请求数据出错：' + JSON.stringify(data)
        });
        // 错误信息提示
    },
    loadData: function(url, data) {
        var _data = null;
        Util.request({
            async: false,
            url: url,
            model: data,
            onSuccess: function(jqXHR, textStatus, data) {
                if (data) {
                    _data = data;
                } else {
                    _data = {};
                }
            },
            onError: function(jqXHR, textStatus, data) {
                _data = null;
            }
        });
        return _data;
    },
}