/**
 * @author NZQ
 * @data 2018/10/11
 * @Description : ajax 封装,会在引入public的时候自动加载
 *type               请求的方式  默认为get
 * url               发送请求的地址
 * data             发送请求的参数
 * contentType       客户端发送数据类型
 * isShowLoader      是否显示loader动画  默认为false
 * dataType          返回数据类型  默认为JSON格式数据
 * sucCallback       请求成功的回调函数
 * errorCallback     请求错误的回掉函数
 * timeout           超时时长,默认5秒
 * processData       序列化处理
 * progress          相应的进度条处理函数
 * https://www.cnblogs.com/tnnyang/p/5742199.html (thanks)
 */

(function () {
    function AjaxRequest (opts) {
        console.log(opts)
        this.type = opts.type || 'GET';
        this.url = opts.url;
        this.data = opts.data;
        this.contentType = (function () { if (opts.contentType !== undefined) { return opts.contentType } else { return "application/json" } })();
        this.isShowLoader = opts.isShowLoader || false;
        this.dataType = opts.dataType || "json";
        this.sucCallback = opts.sucCallback;
        this.errorCallback = opts.errorCallback;
        this.timeout = opts.timeout || 10000;
        // 这个是来源于  AMAZUI 的进度条
        this.progress = $.AMUI.progress;
        this.processData = (function () {
            if(opts.processData !== undefined ){
                return opts.processData;
            }else {
                return true;
            }
        })();
        this.sendRequest();
    }

    AjaxRequest.prototype = {
        // 渲染loader
        showLoader : function () {
            if (this.isShowLoader) {
                this.progress.start();
            }
        },
        hideLoader : function () {
            if (this.isShowLoader) {
                this.progress.done();
            }
        },
        sendRequest : function () {
            var self = this ;
            $.ajax({
                type : self.type,
                url : self.url,
                async : true,
                contentType : self.contentType,
                data : self.data,
                dataType : self.dataType,
                processData : self.processData,
                beforeSend : function () {
                    self.showLoader();
                },
                timeout : self.timeout,
                success : function (res) {
                    console.log(res)
                    if (self.sucCallback) {
                        //Object.prototype.toString.call方法--精确判断对象的类型
                        if (Object.prototype.toString.call(self.sucCallback) === "[object Function]") {
                            self.sucCallback(res);
                        } else {
                            console.log("callBack is not a function");
                        }
                    }
                },
                error : function () {
                    if (self.errorCallback) {
                        //Object.prototype.toString.call方法--精确判断对象的类型
                        if (Object.prototype.toString.call(self.errorCallback) === "[object Function]") {
                            self.errorCallback();
                        }else {
                            console.log("callBack is not a function");
                        }
                    }
                },
                complete : function(XMLHttpRequest,status){ //请求完成后最终执行参数
                    if(status === 'timeout'){//超时,status还有success,error等值的情况
                        if (self.errorCallback) {
                            //Object.prototype.toString.call方法--精确判断对象的类型
                            if (Object.prototype.toString.call(self.errorCallback) === "[object Function]") {
                                self.errorCallback();
                            }else {
                                console.log("callBack is not a function");
                            }
                        }
                    }else {
                        self.hideLoader();
                    }
                }
            })
        }
    };

    window.AjaxRequest = AjaxRequest;
})();

/**
 * @author NZQ
 * @date 2018/10/12
 * @Description :
 * @Param :  得到URL 文件
*/

function getObjectURL (file) {
    var url = null;
    if (window.createObjectURL !== undefined) {
        url = window.createObjectURL(file);
    } else if (window.URL !== undefined) {
        url = window.URL.createObjectURL(file) ;
    } else if (window.webkitURL !== undefined) {
        url = window.webkitURL.createObjectURL(file);
    }
    return url;
}

// queryString to object
function qStrToObj () {
    var url = window.location.href;
    if (url) {
        var qStr = url.substring(url.indexOf('?') + 1);
        var qArr = qStr.split('&');
        var obj = {};
        for (var i = 0, length = qArr.length; i < length; i++) {
            var index = qArr[i].indexOf('=');
            obj[qArr[i].slice(0, index)] = qArr[i].substring(index + 1);
        }
        return obj;
    }
}

function openModal (opts) {
    var b = $('body');
    if(b.length) {
        var modal = $('#my-confirm');
        if (modal.length)  {
            $("#modalContent").text(opts.describe);
            modal.modal({
                relatedElement: this,
                onConfirm: function () {
                    if(opts.confirmCallback && Object.prototype.toString.call(opts.confirmCallback) === "[object Function]"){
                            opts.confirmCallback();
                    }
                },
                onCancel: function () {
                    if (opts.cancelCallback && Object.prototype.toString.call(opts.cancelCallback) === "[object Function]") {
                            opts.cancelCallback()
                    }
                }
            })
        }else {
            b.append($('<div class="am-modal am-modal-confirm" tabindex="-1" id="my-confirm">\n' +
                '        <div class="am-modal-dialog" style="width: 400px;">\n' +
                '        <div class="am-modal-hd" style="color: red;">提示</div>\n' +
                '        <div class="am-modal-bd" id="modalContent">\n' +
                '\n' +
                '        </div>\n' +
                '        <div class="am-modal-footer">\n' +
                '        <span class="am-modal-btn" data-am-modal-cancel>取消</span>\n' +
                '    <span class="am-modal-btn" data-am-modal-confirm>确定</span>\n' +
                '    </div>\n' +
                '    </div>\n' +
                '    </div>'));
            $("#modalContent").text(opts.describe);
            $("#my-confirm").modal({
                relatedElement: this,
                onConfirm: function () {
                    if(opts.confirmCallback && Object.prototype.toString.call(opts.confirmCallback) === "[object Function]"){
                        opts.confirmCallback();
                    }
                },
                onCancel: function () {
                    if (opts.cancelCallback && Object.prototype.toString.call(opts.cancelCallback) === "[object Function]") {
                        opts.cancelCallback()
                    }
                }
            });
        }
    }

}

