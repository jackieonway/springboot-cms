$(function () {
    $(".loadmore").bind("click",function () {
        $.ajax({
            type:"post",
            dataType:'json',
            url:'/more',
            data:{'search':$('.loadmore').attr('search'),'pageNum':$('.loadmore').attr('pageNum'),
                'pageSize':$('.loadmore').attr('pageSize'),'clazz':$('.loadmore').attr('clazz'),
                'second':$('.loadmore').attr('second')},
            success:function (data) {
                if (data.result){
                    $('.loadmore').attr('pageNum',data['t']['pageNum']);
                    $('.loadmore').attr('pageSize',data['t']['pageSie']);
                    $('.loadmore').attr('clazz',data['t']['clazz']);
                    $('.loadmore').attr('search',data['t']['search']);
                    $('.loadmore').attr('second',data['t']['second']);
                    var pageNum = data['t']['pageNum'];
                    if(pageNum == 0){
                        $('.loadmore').remove();
                    }else {
                        var length = data['t']['articles'].length;
                        for(var i = 0; i < length;i++){
                            $('.js-postlist').append(
                                "<div class=\"postlist-item u-clearfix\" data-id=\"1043196\">\n" +
                                "                <div class=\"post-img\">\n" +
                                "                    <a href=\""+window.location.host+data['t']['articles'][i].url+"\" target=\"_blank\"><img src=\""+window.location.host+data['t']['articles'][i].picture+"\"></a>\n" +
                                "                </div>\n" +
                                "                <div class=\"content\">\n" +
                                "                    <h2 class=\"post-title\"><a title=\""+data['t']['articles'][i].title+"\" href=\""+window.location.host+data['t']['articles'][i].url+"\" target=\"_blank\">"+data['t']['articles'][i].title+"</a></h2>\n" +
                                "                    <p class=\"des\">"+data['t']['articles'][i].summary+"...</p>\n" +
                                "                    <div class=\"stream-list-meta\">\n" +
                                "                        <time>"+data['t']['articles'][i].articleTime+"</time>\n" +
                                "                        <div class=\"u-floatRight post-meta-items\">\n" +
                                "                            <span class=\"post-meta-item\"><span class=\"fa fa-eye\"></span>"+window.location.host+data['t']['articles'][i].viewCount+"</span>\n" +
                                "                        </div>\n" +
                                "                    </div>\n" +
                                "                </div>\n" +
                                "            </div>");
                        }
                    }
                }
            }
        });
    });
});
