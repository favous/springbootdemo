/**
 * Created by lzx on 2016/4/17.
 */
var scrollLoad = (function (options) {
    var defaults = (arguments.length == 0) ? { src: 'xSrc', time: 300} : { src: options.src || 'xSrc', time: options.time ||300};
    var camelize = function (s) {
        return s.replace(/-(\w)/g, function (strMatch, p1) {
            return p1.toUpperCase();
        });
    };
    this.getStyle = function (element, property) {
        if (arguments.length != 2) return false;
        var value = element.style[camelize(property)];
        if (!value) {
            if (document.defaultView && document.defaultView.getComputedStyle) { //��IE��������Ԫ���ⲿ��ʽ�ķ���-->
                var css = document.defaultView.getComputedStyle(element, null);//��IE��������Ԫ���ⲿ��ʽ�ķ���-->
                value = css ? css.getPropertyValue(property) : null;
            } else if (element.currentStyle) {//IE��������Ԫ���ⲿ��ʽ�ķ���-->
                value = element.currentStyle[camelize(property)];
            }
        }
        return value == 'auto' ? '' : value;
    };
    var _init = function () {
        var offsetPage = window.pageYOffset ? window.pageYOffset : window.document.documentElement.scrollTop,//��õ�ǰҳ������ڴ������Ͻ�Y��λ�ã�IE8�Լ�IE8����İ汾��֧��ǰ�ߵ���֧�ֺ���
            offsetWindow = offsetPage + Number(window.innerHeight ? window.innerHeight : document.documentElement.clientHeight),//���ش��ڵ��ĵ���ʾ��ĸ߶�,�����ǿɼ�����ĸ߶ȣ�������ie�ð�
            docImg = document.images,//����ҳ����ͼƬ�ļ���
            _len = docImg.length;//���ͼƬ������
        if (!_len) return false;
        for (var i = 0; i < _len; i++) {
            var attrSrc = docImg[i].getAttribute(defaults.src),
                o = docImg[i], tag = o.nodeName.toLowerCase();
            if (o) {
                postPage = o.getBoundingClientRect().top + window.document.documentElement.scrollTop + window.document.body.scrollTop;//�ȸ�Ϊ�˶����߽��м��ݣ�����scrollTop��ֵ���������
                postWindow = postPage + Number(this.getStyle(o, 'height').replace('px', ''));
                if ((postPage > offsetPage && postPage < offsetWindow) || (postWindow > offsetPage && postWindow < offsetWindow)) {
                    if (tag === "img" && attrSrc !== null) {
                        o.setAttribute("src", attrSrc);
                    }
                    o = null;
                }
            }
        };
        window.onscroll = function () {
            setTimeout(function () {
                _init();
            }, defaults.time);
        }
    };
    return _init();
});
scrollLoad(1);
