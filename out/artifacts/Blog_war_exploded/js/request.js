/**
 * @author NZQ
 * @data 2018/10/11
 * @Description : 请求
 */

// 解决 Date 的 toLocalString  在不同浏览器显示不一致的问题
Date.prototype.toLocaleString = function () {
    return this.getFullYear()+'-'+(this.getMonth()+1)+'-'+this.getDate()+'&nbsp;&nbsp'+this.getHours()+':'+this.getMinutes()+':'+this.getSeconds()
};

// login
(function () {
    $("#loginBtn").on("click", function () {
        new AjaxRequest({
            type: 'POST',
            url : '../login',
            data: JSON.stringify({
                adminAccount : $("#doc-ipt-email-1").val(),
                adminPassword : $("#doc-ipt-email-2").val()
            }),
            sucCallback : function (res) {
                if (res.status === true) {
                    sessionStorage.setItem("earthAdminState", "on");
                    window.location.href = "../adminUi/index.html";
                }else {
                    alert("账号或密码有误！");
                }
            }
        });
    })
})();
//
(function () {
        $(".am-text-sm:eq(1)").click(function (event) {
            event.preventDefault();
            sessionStorage.removeItem("earthAdminState");
            window.location.href = "index.jsp";
        })
    })();

// 点赞
function addLikeRequest (id, callback) {
    new AjaxRequest({
        type :'PUT',
        url :'../greatNum/' + id,
        sucCallback : function (res) {
            if (res.status === true) {
                callback()
            }
        }
    })
}

//  三个页面共有的部分
function userUiRealtedList(id, curPage, callback) {
    new AjaxRequest({
        type : "GET",
        url : '../type?infoType=' + id + "&pn=" + curPage,
        sucCallback : function (res) {
            if (res.status === true) {
                console.log(res);
                if (res.datas[0].list.length) {
                    callback(res.datas[0].list);
                } else {
                    $(".pet_article_like").css({
                        display : 'none'
                    })
                }
                // 没有下一页的时候清除scroll事件
                if (!res.datas[0].nextPage) {
                    window.scroll = null;
                    $(".not-have-more").text("没有更多！")
                }
            }
        }
    })
}
function checkDetail(selector, url) {
    var items = $(selector);
    for(var i=0,length = items.length;i<length;i++) {
        $(items[i]).on("click", function () {
            window.location.href = url + "?id=" + $(this).data("itemid");
        })
    }
}

// 用户界面获取 “震情信息” 列表
function userEarthInfoRequest(curPage) {
    userUiRealtedList(1, curPage,  function (jd) {
        var list = $("#earthInfoList"),
            htmlStr = '';
        for (var i = 0, length=jd.length;i<length;i++) {
            htmlStr += '<li data-itemid="'+jd[i].infoId +'" class="earth-info-item am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-right pet_list_one_block">\n' +
                '              <div class=" am-u-sm-8 am-list-main pet_list_one_nr" style="height: 100px;">\n' +
                '                <h3 class="am-list-item-hd pet_list_one_bt">\n' +
                '                    <a href="###" class="">' + jd[i].infoHeadline + '</a>\n' +
                '                </h3>\n' +
                '                <div class="am-list-item-text pet_list_one_text">\n' + jd[i].infoContent +
                '                </div>\n' +
                '            </div>\n' +
                '              <div class="am-u-sm-4 am-list-thumb">\n' +
                '                <a href="###" class="">\n' +
                '                  <!--<img src="' + jd[i].infoPic + '" style="min-height: 80px;" class="pet_list_one_img" alt="">-->\n' +
                '                </a>\n' +
                '              </div>\n' +
                '              <div class="pet_list_one_info">\n' +
                '                  <div class="pet_list_one_info_l">\n' +
                '                      <div style="    border-left: 3px solid #fe9700;border-right:none;padding-left: 5px;" class="pet_list_tag pet_list_tag_xxs">发表时间：' + (new Date(Number(jd[i].infoReltime ))).toLocaleString() + '</div>\n' +
                '                  </div>\n' +
                '                  <div class="pet_list_one_info_r">\n' +
                '                      <div class="pet_list_tag pet_list_tag_xxs">今日访问量：' + getTodayN(jd[i].infoPv) +'</div>\n' +
                '                  </div>\n' +
                '              </div>\n' +
                '          </li>'
        }
        list.html(list.html() + htmlStr);
        checkDetail(".earth-info-item", './earthInfoDetail.html');
    })
}

// 用户界面获取 “新闻事件” 列表
function userNewsRequest(curPage) {
    userUiRealtedList(2, curPage, function (jd) {
        console.log(jd);
        var list = $("#newsList"),
            htmlStr = '';
        for (var i = 0, length=jd.length;i<length;i++) {
            htmlStr += '<li data-itemid="'+jd[i].infoId +'" class="earth-news-item am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-right pet_list_one_block">\n' +
                '              <div class=" am-u-sm-8 am-list-main pet_list_one_nr" style="height: fit-content;">\n' +
                '                <h3 class="am-list-item-hd pet_list_one_bt">\n' +
                '                    <a style="font-weight: 500" href="###" class="">' + jd[i].infoHeadline + '</a>\n' +
                '                </h3>\n' +
                '            </div>\n' +
                '              <div class="am-u-sm-4 am-list-thumb">\n' +
                '                <a href="###" class="">\n' +
                '                  <!--<img src="' + jd[i].infoPic + '" style="min-height: 80px;" class="pet_list_one_img" alt=""-->\n' +
                '                </a>\n' +
                '              </div>\n' +
                '              <div class="pet_list_one_info">\n' +
                '                  <div class="pet_list_one_info_l">\n' +
                '                      <div style="    border-left: 3px solid #fe9700;border-right:none;padding-left: 5px;" class="pet_list_tag pet_list_tag_xxs">发表时间：' +
                (new Date(Number(jd[i].infoReltime ))).toLocaleString() + '</div>\n' +
                '                  </div>\n' +
                '                  <div class="pet_list_one_info_r">\n' +
                '                      <div class="pet_list_tag pet_list_tag_xxs">今日访问量：' + getTodayN(jd[0].infoPv) +'</div>\n' +
                '                  </div>\n' +
                '              </div>\n' +
                '          </li>'
        }
        list.html(list.html() + htmlStr);
        checkDetail(".earth-news-item", './newsEventDetail.html');
    })
}

// 用户界面获取 “地震科普” 列表
function userKnowledgeRequest(curPage) {
    userUiRealtedList(3, curPage, function (jd) {
        var list = $("#earthKnowledgeList"),
            htmlStr = '';

        // 内容相关的       '                                    <div class="am-list-item-text pet_list_two_text">' + jd[i].infoContent +'</div>\n' +
        for (var i = 0, length=jd.length;i<length;i++) {
            htmlStr += '<li class="earth-knowledge-item am-g am-list-item-desced pet_list_one_block" data-itemid="'+ jd[i].infoId +'" >\n' +
                '                                <div class=" am-list-main">\n' +
                '                                    <h3 class="am-list-item-hd pet_list_one_bt"><a href="###" class="">' + jd[i].infoHeadline +'</a></h3>\n' +
                '                                    <div class="pet_list_zt_img" style="max-height: 250px;height: fit-content;overflow: hidden"><img src="' + jd[i].infoPic +'" alt=""></div>\n' +
                '\n' +
                '\n' +
                '                                </div>\n' +
                '                                <div class="pet_list_one_info">\n' +
                '                                    <div class="pet_list_one_info_l">\n' +
                '                                        <div style="    border-left: 3px solid #fe9700;border-right:none;padding-left: 5px;" class="pet_list_tag pet_list_tag_xxs">发表时间：' + (new Date(Number(jd[i].infoReltime ))).toLocaleString() +'</div>\n' +
                '                                    </div>\n' +
                '                                    <div class="pet_list_one_info_r">\n' +
                '                                        <div class="pet_list_tag pet_list_tag_xxs">今日访问量：' + getTodayN(jd[i].infoPv) +'</div>\n' +
                '                                    </div>\n' +
                '                                </div>\n' +
                '                            </li>'
        }
        list.html(list.html() + htmlStr);
        checkDetail(".earth-knowledge-item", './knowledgeDetail.html');
    })
}

function modifyLike (id) {
    var likeBtn = $(".like_share_block li:nth-of-type(1) a");
    likeBtn.on("click", function () {
        if ((this.className).indexOf("like") === -1) {
            addLikeRequest(id, function () {
                $(".share_ico_link").css({
                    color : 'red'
                });
                var num = $("#detailLikeNumber");
                num.text(Number(num.text()) + 1)
            })
        }
    })
}


function getAllN (data) {
    return data.substring(0, data.indexOf('-'))
}
function getTodayN  (data) {
    return data.substring(data.indexOf('-')+1)
}
function checkDetailInfo (id, callback) {
    var formData = new FormData();
    formData.append("infoId",id);
    new AjaxRequest({
        url : '../alone',
        type : "POST",
        processData : false,
        contentType : false,
        data : formData,
        sucCallback : function (res) {
            if (res.status === true) {
                console.log(res);
                callback(res.datas[0]);
            } else {
                openModal({
                    describe : "访问页面出错，3s就返回",
                    confirmCallback : function () {
                        history.back(-1);
                    }
                });
                setTimeout(function () {
                    history.back(-1);
                }, 3000)
            }
        }
    })
}


// 展示知识
function displayArticle (id) {
    checkDetailInfo(id, function (res) {
        $('#detailName').text(res.infoAuthor);
        $("#detailTitle").text(res.infoHeadline);
        $('#detailArticle').html(res.infoContent);
        $('#detailInfo').html('发表于：' + (new Date(Number(res.infoReltime ))).toLocaleString()  +'&nbsp;&nbsp;|&nbsp;&nbsp;浏览量：' + getAllN(res.infoPv) +'&nbsp;&nbsp;|&nbsp;&nbsp;今日浏览量：' + getTodayN(res.infoPv));
        $("#detailLikeNumber").text(res.greatNumber)
    })
}

// 展示新闻
function displayNews(id) {
    checkDetailInfo(id, function (res) {
        console.log(res);
        var content = res.infoContent.replace('id="content"', 'id="contentWrap"');

        $('#name').text(res.name);
        $("#title").text(res.infoHeadline);
        $('#content').html(content);
        $('#info').html('发表于：' + (new Date(Number(res.infoReltime ))).toLocaleString() +'&nbsp;&nbsp;|&nbsp;&nbsp;浏览量：' + getAllN(res.infoPv) +'&nbsp;&nbsp;|&nbsp;&nbsp;今日浏览量：' + getTodayN(res.infoPv))
        $("#contentWrap img").each(function (index, item) {
            item.src = res.infoUrl + item.src.substring(item.src.lastIndexOf("\/"))
        })
    })
}
