//分页插件
(function($){
    var ms = {
        init:function(obj,args){
            return (function(){
                ms.fillHtml(obj,args);
                ms.bindEvent(obj,args);
            })();
        },
        //填充html
        fillHtml:function(obj,args){
            return (function(){
                obj.empty();
                //上一页
                if(args.current > 1){
                    obj.append('<a href="javascript:;" class="prevPage">上一页</a>');
                }else{
                    obj.remove('.prevPage');
                    obj.append('<span class="disabled">上一页</span>');
                }
                //中间页码
                if(args.current != 1 && args.current >= 4 && args.pageCount != 4){
                    obj.append('<a href="javascript:;" class="tcdNumber">'+1+'</a>');
                }
                if(args.current-2 > 2 && args.current <= args.pageCount && args.pageCount > 5){
                    obj.append('<span>...</span>');
                }
                var start = args.current -2,end = args.current+2;
                if((start > 1 && args.current < 4)||args.current == 1){
                    end++;
                }
                if(args.current > args.pageCount-4 && args.current >= args.pageCount){
                    start--;
                }
                for (;start <= end; start++) {
                    if(start <= args.pageCount && start >= 1){
                        if(start != args.current){
                            obj.append('<a href="javascript:;" class="tcdNumber">'+ start +'</a>');
                        }else{
                            obj.append('<span class="current">'+ start +'</span>');
                        }
                    }
                }
                if(args.current + 2 < args.pageCount - 1 && args.current >= 1 && args.pageCount > 5){
                    obj.append('<span>...</span>');
                }
                if(args.current != args.pageCount && args.current < args.pageCount -2  && args.pageCount != 4){
                    obj.append('<a href="javascript:;" class="tcdNumber">'+args.pageCount+'</a>');
                }
                //下一页
                if(args.current < args.pageCount){
                    obj.append('<a href="javascript:;" class="nextPage">下一页</a>');
                }else{
                    obj.remove('.nextPage');
                    obj.append('<span class="disabled">下一页</span>');
                }
                obj.append('<span style="color: black;" >共 '+ args.pageCount + '页</span>');
                obj.append('<span  style="color: black;" >总计'+args.total+'条</span>');
            })();
        },
        //绑定事件
        bindEvent:function(obj,args){
            return (function(){
                obj.on("click","a.tcdNumber",function(){
                    var current = parseInt($(this).text());
                    ms.fillHtml(obj,{"current":current,"pageCount":args.pageCount,"total":args.total});
                    if(typeof(args.backFn)=="function"){
                        args.backFn(current);
                    }
                    window.location.href = urlUpdateParams(window.location.href,"pageNum",current,"pageSize",args.pageSize);
                });
                //上一页
                obj.on("click","a.prevPage",function(){
                    var current = parseInt(obj.children("span.current").text());
                    ms.fillHtml(obj,{"current":current-1,"pageCount":args.pageCount,"total":args.total});
                    if(typeof(args.backFn)=="function"){
                        args.backFn(current-1);
                    }
                    window.location.href = urlUpdateParams(window.location.href,"pageNum",args.current-1,"pageSize",args.pageSize);
                });
                //下一页
                obj.on("click","a.nextPage",function(){
                    var current = parseInt(obj.children("span.current").text());
                    ms.fillHtml(obj,{"current":current+1,"pageCount":args.pageCount,"total":args.total});
                    if(typeof(args.backFn)=="function"){
                        args.backFn(current+1);
                    }
                    window.location.href = urlUpdateParams(window.location.href,"pageNum",args.current+1,"pageSize",args.pageSize);
                });
            })();
        }
    }
    $.fn.createPage = function(options){
        var args = $.extend({
            pageSize : 10,
            total : 100,
            pageCount : 10,
            current : 1,
            backFn : function(){}
        },options);
        ms.init(this,args);
    }

    // 拼接请求链接
    function urlUpdateParams(url, name, value,name1,value1) {
        var r = url;
        if (r != null && r != 'undefined' && r != "") {
            value = encodeURIComponent(value);
            var reg = new RegExp("(^|)" + name + "=([^&]*)(|$)");
            var tmp = name + "=" + value;
            if (url.match(reg) != null) {
                r = url.replace(eval(reg), tmp);
            }
            else {
                if (url.match("[\?]")) {
                    r = url + "&" + tmp;
                } else {
                    r = url + "?" + tmp;
                }
            }
            value1 = encodeURIComponent(value1);
            var reg1 = new RegExp("(^|)" + name1 + "=([^&]*)(|$)");
            var tmp1 = name1 + "=" + value1;
            if (r.match(reg1) != null) {
                r1 = r.replace(eval(reg1), tmp1);
            }
            else {
                if (r.match("[\?]")) {
                    r1 = r + "&" + tmp1;
                } else {
                    r1 = r + "?" + tmp1;
                }
            }
        }
        return r1;
    }
})(jQuery);
