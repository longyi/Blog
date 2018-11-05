<%@ page import="daoImpl.articleDaoImpl" %>
<%@ page import="Entity.Article" %>
<%@ page import="daoImpl.articletypeDaoImpl" %><%--
  Created by IntelliJ IDEA.
  User: longyi
  Date: 2018/10/27
  Time: 20:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    articleDaoImpl articleDao = new articleDaoImpl();
    articletypeDaoImpl articletypeDao = new articletypeDaoImpl();
    int id = Integer.parseInt(request.getParameter("id"));
    Article article = articleDao.findbyid(id);
    String type = articletypeDao.getArticletype(article.getArticle_typeid());
    article.setArticle_type(type);
    request.setAttribute("article",article);
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>写文章</title>
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="apple-touch-icon-precomposed" href="../assets/i/app-icon72x72@2x.png">
    <link media="all" rel="stylesheet" type="text/css" href="../simditor/site/assets/styles/simditor.css" />
    <meta name="apple-mobile-web-app-title" content="管理员" />

    <link rel="stylesheet" href="../assets/css/amazeui.min.css" />
    <link rel="stylesheet" href="../assets/css/amazeui.datatables.min.css" />
    <link rel="stylesheet" href="../assets/css/app.css">
    <link rel="stylesheet" href="../assets/css/mineModify.css">
    <link rel="stylesheet" href="../assets/font/aliicon/iconfont.css">
    <link rel="stylesheet" href="../assets/font/aliicon/iconfont.css">
    <!-- Include external CSS. -->
    <link href="../froala_editor/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="../froala_editor/codemirror.min.css">

    <!-- Include Editor style. -->
    <link href="../froala_editor/froala_editor.pkgd.min.css" rel="stylesheet" type="text/css" />
    <link href="../froala_editor/froala_style.min.css" rel="stylesheet" type="text/css" />
    <script src="../assets/js/jquery.min.js"></script>
    <script src="../assets/js/jquery.min.js"></script>
</head>

<body data-type="widgets" class="theme-black">
<script src="../assets/js/theme.js"></script>
<div class="am-g tpl-g">
    <!-- 头部 -->
    <header>
        <!-- logo -->
        <!-- 右侧内容 -->
        <div class="tpl-header-fluid">
            <!-- 其它功能-->
            <div class="am-fr tpl-header-navbar">
                <ul>
                    <!-- 欢迎语 -->
                    <li class="am-text-sm tpl-header-navbar-welcome">
                        <a href="javascript:;">欢迎你, <span><%=session.getAttribute("username")%></span> </a>
                    </li>

                    <!-- 退出 -->
                    <li class="am-text-sm">
                        <a href="archive.jsp">
                            <span class="am-icon-sign-out"></span> 返回我的文章
                        </a>
                    </li>
                </ul>
            </div>
        </div>

    </header>
    <div class="tpl-content-wrapper">
        <ol class="am-breadcrumb">
            <li><a href="javascript:void(0);" class="am-icon-home" style="color: #999999;">我的文章</a></li>
            <li class="am-active">编辑文章</li>
        </ol>
        <div class="row-content am-cf">
            <div class="row">
                <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                    <div class="widget am-cf">
                        <div class="widget-body am-fr">
                            <form id="mainForm"  class="am-form tpl-form-border-form"  >
                                <div class="am-form-group">
                                    <label for="title" class="am-u-sm-12 am-form-label am-text-left">标题 <span class="tpl-form-line-small-title"></span></label>
                                    <div class="am-u-sm-12">
                                        <input type="text" class="tpl-form-input am-margin-top-xs" id="title" name="title" placeholder="请输入标题文字" value="${article.title}">
                                        <small>请填写标题文字10-20字左右。</small>
                                    </div>
                                </div>
                                <%--封面--%>
                                <div class="am-form-group">
                                    <label class="am-u-sm-12 am-form-label  am-text-left">封面图 <span class="tpl-form-line-small-title"></span></label>
                                    <div class="am-u-sm-12 am-margin-top-xs">
                                        <div class="am-form-group am-form-file">
                                            <div class="tpl-form-file-img">
                                                <!-- 上传图片并显示 -->
                                                <img id="mineUploadImg"  src="${article.imgurl}" style="width: 50rem;" alt="">
                                            </div>
                                            <button id="mineUploadButton" type="button" style="margin-top: 1rem;" class="am-btn am-btn-danger am-btn-sm ">
                                                <i class="am-icon-cloud-upload"></i> 修改封面图片</button>
                                            <input class="mineUploadInput" id="doc-form-file1" type="file">
                                        </div>

                                    </div>
                                </div>
                                <%--作者--%>
                                <div class="am-form-group">
                                    <label for="user-name1" class="am-u-sm-12 am-form-label am-text-left" value="">作者 <span class="tpl-form-line-small-title"></span></label>
                                    <div class="am-u-sm-12">
                                        <input type="text" name="author" class="tpl-form-input am-margin-top-xs" value="<%=session.getAttribute("username")%>" id="user-name1" placeholder="作者">
                                        <small>发布作者自动填充</small>
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <label class="am-u-sm-12 am-form-label am-text-left">分类<span class="tpl-form-line-small-title"></span></label>
                                    <div class="am-u-sm-12">
                                        <input type="text" class="tpl-form-input am-margin-top-xs" id="article_type" name="article_type" placeholder="请输入文章类别" value="${article.article_type}">
                                        <small style="display: block;">必填</small>
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <div class="am-form-group">
                                        <label for="editor" class="am-u-sm-12 am-form-label  am-text-left"></label>
                                        <div class="am-u-sm-12 am-margin-top-xs">
                                            <textarea class="" name="content"  id='editor' style="margin-top: 30px;" rows="10">${article.content}</textarea>
                                        </div>
                                    </div>

                                    <div class="am-form-group">
                                        <div class="am-u-sm-12 am-u-sm-push-12">
                                            <button type="button" id="submitContent"  class="am-btn am-btn-primary tpl-btn-bg-color-success ">提交</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 内容区域 -->
</div>
<!--css引用-->
<script type="text/javascript" src="../froala_editor/jquery-3.3.1.js"></script>
<script src="../assets/js/amazeui.min.js"></script>
<script src="../assets/js/app.js"></script>
<script src="../assets/js/adminCommon.js"></script>
<script type="text/javascript" src="../froala_editor/codemirror.min.js"></script>
<script type="text/javascript" src="../froala_editor/xml.min.js"></script>
<script type="text/javascript" src="../froala_editor/froala_editor.pkgd.min.js"></script>
<script src="../assets/js/utils.js"></script>
<script>


    var form = document.forms['mainForm'];
    var formEle = document.querySelector("#mainForm").elements;
    $('#editor').froalaEditor({
        placeholderText: '请输入内容',
        charCounterMax: 3000,
        toolbarButtons: [
            'bold', 'italic', 'underline', 'paragraphFormat', 'align','color','fontSize','insertImage','insertTable','undo', 'redo'
        ],
        height : "300px",
    });
    $('#editor').on('froalaEditor.image.inserted', function (e, editor, $img, response) {

        function dataURLtoFile(dataurl, filename) {
            var arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],
                bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
            while(n--){
                u8arr[n] = bstr.charCodeAt(n);
            }
            return new File([u8arr], filename, {type:mime});
        }

        function getBase64(url){
            //通过构造函数来创建的 img 实例，在赋予 src 值后就会立刻下载图片，相比 createElement() 创建 <img> 省去了 append()，也就避免了文档冗余和污染
            var Img = new Image(),
                dataURL='';
            Img.src=url;
            Img.onload=function(){ //要先确保图片完整获取到，这是个异步事件
                var canvas = document.createElement("canvas"), //创建canvas元素
                    width=Img.width, //确保canvas的尺寸和图片一样
                    height=Img.height;
                canvas.width=width;
                canvas.height=height;
                canvas.getContext("2d").drawImage(Img,0,0,width,height); //将图片绘制到canvas中
                dataURL=canvas.toDataURL('image/jpeg'); //转换图片为dataURL
                var formData = new FormData();
                formData.append("file", dataURLtoFile(dataURL, 'DataURLToFile.jpeg'));
                new AjaxRequest({
                    // wz img
                    url : '../img',
                    type : "POST",
                    isShowLoader: true,
                    data : formData,
                    processData : false,
                    contentType : false,
                    sucCallback : function (res) {
                        console.log(res)
                        if (true) {
                            $($img).attr({
                                src :  res.file_path
                            })
                        }else {
                            openModal({
                                describe : "上传出错"
                            })
                        }
                    }
                });
            };
        }

        getBase64($($img).attr("src"))
    });

    $("#submitContent").click(function () {
        var id = ${article.id};
        var title = formEle.title.value;
        var author = formEle.author.value;
        var src = $("#mineUploadImg")[0].src;
        var content = $('#editor').froalaEditor('html.get');
        var type = formEle.article_type.value;
        if (title && author && content && type ) {
            console.log("nz")
            new AjaxRequest({
                url: '../update',
                type: 'POST',
                isShowLoader : true,
                data: JSON.stringify({
                    id:id,
                    title: title,
                    user_name: author,
                    article_type : type,
                    imgurl : src,
                    content: content
                }),
                sucCallback : function (res) {
                    alert("修改成功");
                    window.location.href = 'archive.jsp'
                }
            })
        }else {
            openModal({
                describe : "你输入的内容有空白项！请检查！"
            })
        }
    });

</script>
<script>
    $('body').attr('class', 'theme-black')
</script>
</body>

</html>
