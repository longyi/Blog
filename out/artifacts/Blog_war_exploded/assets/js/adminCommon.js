/**
 * @author NZQ
 * @data 2018/9/18
 * @Description : 图片上传  以及进度条显示
 */


$(".mineUploadInput").on("change", function () {
    var image = $("#mineUploadImg");
    var files = $(this)[0].files;
    var fd = new FormData();
    /*if(files.length > 0){
        [].slice.call(files).forEach(function(value,index){
            fd.append('img' + index,value,value.name) //遍历添加数据
        })
    }*/
    fd.append("file", files[0]);
    // FormData打印出来就是空的,传到后台看看是否能接收到,或者在调试工具的netWork里面去看传送参数,
    // console.log(fd.get("img0"));
    new AjaxRequest({
        // wz img
        url : '../img',
        type : "POST",
        isShowLoader: true,
        data : fd,
        processData : false,
        contentType : false,
        sucCallback : function (res) {
            console.log(res);
            if (true) {
                $("#mineUploadImg").attr({
                    src : res.file_path
                })
            }
        }
    });
    image.attr({
        src : getObjectURL(files[0])
    });
    $("#mineUploadButton").html("<i class=\"am-icon-cloud-upload\"></i> 修改封面图片</button>")
});

/**
 * @author NZQ
 * @date 2018/9/18
 * @Description :
 * @Param : 二维码
*/

// 退出
(function () {
    if ($(".am-text-sm:eq(1)").length) {
        $(".am-text-sm:eq(1)").click(function (event) {
            event.preventDefault();
            sessionStorage.removeItem('earthAdminState');
           openModal({
                describe : "是否退出？",
                confirmCallback : function () {
                    window.location.href = "/jsp/index.jsp"
                }
            })
        })
    }
})();

function _qrcode(content) {
    var dark = "#000000";
    var light = "#ffffff";
    var img = document.getElementById("qrImg");
    new QRCode(img, {
        text: content,
        width: img.offsetWidth, //生成的二维码的宽度
        height: img.offsetHeight, //生成的二维码的高度
        colorDark : dark, // 生成的二维码的深色部分
        colorLight : light, //生成二维码的浅色部分
        correctLevel : QRCode.CorrectLevel.H
    });
}

function browserIsIe() {
    if (!!window.ActiveXObject || "ActiveXObject" in window){
        return true;
    }
    else{
        return false;
    }
}

function createIframe(imgSrc) {
    //如果隐藏的iframe不存在则创建
    if ($("#IframeReportImg").length === 0){
        $('<iframe style="display:none;" id="IframeReportImg" name="IframeReportImg" onload="ieDdownloadImg();" width="0" height="0" src="about:blank"></iframe>').appendTo("body");
    }
    //iframe的src属性如不指向图片地址,则手动修改,加载图片
    if ($('#IframeReportImg').attr("src") !== imgSrc) {
        $('#IframeReportImg').attr("src",imgSrc);
    } else {
        //如指向图片地址,直接调用下载方法
    }
}
//下载图片的函数
function ieDdownloadImg() {
    if(document.readyState === "complete" && window.frames["IframeReportImg"]){
        if ($('#IframeReportImg').src !== "about:blank") {
            window.frames["IframeReportImg"].document.execCommand("Saveas");
        }
    }
    }


function downloadClick () {
    if ($('#qrImg').children('canvas').length === 0) {
        return;
    }
    var image = document.querySelector('#qrImg img');

    // 获取URL
    var canvas = document.createElement('canvas');
    canvas.width = image.width;
    canvas.height = image.height;
    canvas.getContext('2d').drawImage(image, 0, 0);
    var url = canvas.toDataURL('image/png');
   if (!browserIsIe()) {
       // 模拟下载
       var downloadLink = document.getElementById('downloadClick');
       downloadLink.href =  url;
       downloadLink.download = '二维码.png';
       console.log(downloadLink)
       // 模拟依次单击
       downloadLink.click();
   }else {
       new AjaxRequest({
           type : 'POST',
           url : '',
           data : {
               url : url
           },
           isShowLoader : true,
           timeout : 10000,
           sucCallback : function (res) {
               var downloadLink = document.getElementById('downloadClick');
               downloadLink.href =  res.url;
               downloadLink.download = '二维码.png';
               console.log(downloadLink)
               // 模拟依次单击
               downloadLink.click();
           }
       })
   }

}
