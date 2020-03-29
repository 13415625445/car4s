<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>少强4S店门户网站</title>
<link href="/css/taotao.css" rel="stylesheet"/>
<script type="text/javascript">
	window.pageConfig={
	compatible:true,
	navId:"home",
	enableArea: true
	};
</script>
<style type="text/css">
#categorys-2013 .mc {
	display: block;
}
#categorys-2013 .mt {
	background: 0
}
</style>
</head>
<body>
<!-- header start -->
<jsp:include page="commons/header.jsp" />
<!-- header end -->
<div class="w">
<div id="o-slide">
<div class="slide" id="slide" style="height: 1000px">
<script type="text/javascript">
;(function(cfg, doc) {
    if ( !cfg.DATA_MSlide ) {
        cfg.DATA_MSlide=[];
    }
	var data = ${ad1};

    cfg.DATA_MSlide = data;
    // 初始化一个广告信息
    if ( cfg.DATA_MSlide.length > 0 ) {
    	var first = pageConfig.FN_GetCompatibleData( cfg.DATA_MSlide[0] );
        var TPL = ''
            +'<ul class="slide-items" style="height:402px;">'
            +'<li clstag="homepage|keycount|home2013|09a1" style="height: 402px">'
            +'<a href="'+ first.href +'" target="_blank" title="'+ first.alt +'">'
            +'<img src="'+ first.src +'" width="'+ first.width +'" height="'+ first.height +'">'
            +'</a>'
            +'</li>'
            +'</ul><div class="slide-controls"><span class="curr">1</span></div>';
        doc.write(TPL);
    }
})(pageConfig, document);;</script>
</div><!--slide end-->
<div class="jscroll" id="mscroll">
<div class="ctrl" id="mscroll-ctrl-prev"><b></b></div>
<div class="ctrl" id="mscroll-ctrl-next"><b></b></div>
<div class="o-list">
<div class="list" id="mscroll-list"></div>
</div>
</div><!--mscroll end-->
<%--<script type="text/javascript">--%>
<%--pageConfig.DATA_MScroll =[--%>
    <%--{--%>
        <%--"alt": "",--%>
        <%--"href": "http://c.fa.jd.com/adclick?sid=2&cid=601&aid=3679&bid=4127&unit=36312&advid=107474&guv=&url=http://sale.jd.com/act/hG3N4B2nt6XUCA.html",--%>
        <%--"index": 0,--%>
        <%--"src": "http://img11.360buyimg.com/da/jfs/t382/296/691255709/13325/afe321fd/542907d2Nedd5849c.jpg",--%>
        <%--"ext": ""--%>
    <%--},--%>
    <%--{--%>
        <%--"alt": "",--%>
        <%--"href": "http://c.fa.jd.com/adclick?sid=2&cid=601&aid=3679&bid=4147&unit=36313&advid=109021&guv=&url=http://sale.jd.com/act/CMNjF21UasZ5ouD.html",--%>
        <%--"index": 1,--%>
        <%--"src": "http://img13.360buyimg.com/da/jfs/t283/161/1609640628/12590/ecd295d3/543f2a46N876265d2.jpg",--%>
        <%--"ext": ""--%>
    <%--},--%>
    <%--{--%>
        <%--"alt": "",--%>
        <%--"href": "http://c.fa.jd.com/adclick?sid=2&cid=601&aid=3679&bid=4117&unit=36438&advid=108949&guv=&url=http://sale.jd.com/act/u7nZbvw8pcX.html",--%>
        <%--"index": 2,--%>
        <%--"src": "http://img14.360buyimg.com/da/jfs/t343/107/1701086212/14927/28286262/54407540N2aace14b.jpg",--%>
        <%--"ext": ""--%>
    <%--},--%>
    <%--{--%>
        <%--"alt": "",--%>
        <%--"href": "http://c.fa.jd.com/adclick?sid=2&cid=601&aid=3679&bid=4186&unit=36439&advid=109213&guv=&url=http://jmall.jd.com/shop/fotile/index.html",--%>
        <%--"index": 3,--%>
        <%--"src": "http://img10.360buyimg.com/da/jfs/t310/224/1720371440/7690/f8d25a3d/5440a78bN79d1c1c0.jpg",--%>
        <%--"ext": ""--%>
    <%--},--%>
    <%--{--%>
        <%--"alt": "",--%>
        <%--"href": "http://c.fa.jd.com/adclick?sid=2&cid=601&aid=3679&bid=4195&unit=36440&advid=109092&guv=&url=http://jmall.jd.com/p203378.html",--%>
        <%--"index": 4,--%>
        <%--"src": "http://img11.360buyimg.com/da/jfs/t325/95/1665113883/11374/acc43523/543f97a1N5caa7267.jpg",--%>
        <%--"ext": ""--%>
    <%--},--%>
    <%--{--%>
        <%--"alt": "",--%>
        <%--"href": "http://c.fa.jd.com/adclick?sid=2&cid=601&aid=3679&bid=4205&unit=36441&advid=109294&guv=&url=http://sale.jd.com/act/B1wcFZqvaeg.html",--%>
        <%--"index": 5,--%>
        <%--"src": "http://img13.360buyimg.com/da/jfs/t304/3/1721407024/12521/225e8303/5440ebe8N8e04f88d.jpg",--%>
        <%--"ext": ""--%>
    <%--},--%>
    <%--{--%>
        <%--"alt": "",--%>
        <%--"href": "http://c.fa.jd.com/adclick?sid=2&cid=601&aid=3679&bid=4071&unit=36632&advid=109125&guv=&url=http://sale.jd.com/act/azgJFt1nOK.html",--%>
        <%--"index": 6,--%>
        <%--"src": "http://img13.360buyimg.com/da/jfs/t349/295/1695471355/9379/2325a0a1/54407591Nb4735d70.jpg",--%>
        <%--"ext": "1"--%>
    <%--},--%>
    <%--{--%>
        <%--"alt": "",--%>
        <%--"href": "http://c.fa.jd.com/adclick?sid=2&cid=601&aid=3679&bid=4210&unit=36443&advid=109215&guv=&url=http://sale.jd.com/act/HJyfM0nqQOz.html",--%>
        <%--"index": 7,--%>
        <%--"src": "http://img13.360buyimg.com/da/jfs/t316/141/1726543031/5543/46404f6f/5440aa17N1b411341.jpg",--%>
        <%--"ext": ""--%>
    <%--},--%>
    <%--{--%>
        <%--"alt": "",--%>
        <%--"href": "http://c.fa.jd.com/adclick?sid=2&cid=601&aid=3679&bid=0&unit=36612&advid=108828&guv=&url=http://sale.jd.com/act/Crv8iTP0zjsaVYR.html",--%>
        <%--"index": 8,--%>
        <%--"src": "http://img11.360buyimg.com/da/jfs/t313/170/1681775134/10831/7f4b7161/5440715aN1f03f497.jpg",--%>
        <%--"ext": ""--%>
    <%--}--%>
<%--] ;--%>
<%--(function(object, data) {--%>
    <%--var a = data, b = [], c = [], d, h;--%>
    <%--a.sort(function(a, b) {--%>
        <%--return a.ext - b.ext--%>
    <%--});--%>
    <%--while (a.length > 0) {--%>
        <%--d = a.shift();--%>
        <%--if (d.ext) {--%>
            <%--b.push(d)--%>
        <%--} else {--%>
            <%--c.push(d)--%>
        <%--}--%>
    <%--}--%>
    <%--c.sort(function() {--%>
        <%--return 0.5 - Math.random()--%>
    <%--});--%>
    <%--h = b.length;--%>
    <%--if (h >= 3) {--%>
        <%--for (var i = 0; i < 3; i++) {--%>
            <%--a.push(b.shift())--%>
        <%--}--%>
    <%--} else {--%>
        <%--for (var i = 0; i < h; i++) {--%>
            <%--a.push(b.shift())--%>
        <%--}--%>
    <%--}--%>
    <%--var f = a.length, g = c.length;--%>
    <%--for (var i = 0; i < 18 - f; i++) {--%>
        <%--if (i > g - 1) {--%>
            <%--continue;--%>
        <%--}--%>
        <%--a.push(c.shift())--%>
    <%--}--%>
    <%--var e = [], x;--%>
    <%--e.push("<ul class=\"lh\">");--%>
    <%--for (var i = 0; i < 3; i++) {--%>
        <%--x = pageConfig.FN_GetCompatibleData(a[i]);--%>
        <%--e.push("<li class=\"item\"><a href=\"");--%>
        <%--e.push(x.href);--%>
        <%--e.push("\"><img src=\"/images/blank.gif\" style=\"background:url(");--%>
        <%--e.push(x.src);--%>
        <%--e.push(") no-repeat #fff center 0;\" alt=\"");--%>
        <%--e.push(x.alt);--%>
        <%--e.push("\" width=\"");--%>
        <%--e.push(x.width);--%>
        <%--e.push("\" height=\"");--%>
        <%--e.push(x.height);--%>
        <%--e.push("\" /></a></li>")--%>
    <%--}--%>
    <%--e.push("</ul>");--%>
    <%--document.getElementById(object).innerHTML = e.join("");--%>
    <%--pageConfig.DATA_MScroll = a--%>
<%--})("mscroll-list", pageConfig.DATA_MScroll);--%>
<%--</script>--%>
</div>

<div id="jdnews" class="m m1" >
<div class="mt">
<h2>重磅资讯</h2>
<div class="extra" clstag="homepage|keycount|home2013|11a"><a href="https://www.dongfeng-nissan.com.cn/about/news/brand/" target="_blank">更多快报&nbsp;&gt;</a></div>
</div>
<div class="mc">
	<ul>
		<li class="odd" clstag="homepage|keycount|home2013|11b1"><a href="https://www.dongfeng-nissan.com.cn/about/news/brand/2020/02/2020020101" target="_blank" title="感心服务，给你安心">感心服务，给你安心</a></li>
				<li clstag="homepage|keycount|home2013|11b1"><a href="https://www.dongfeng-nissan.com.cn/about/news/brand/2020/01/2020011504" target="_blank" title="轩逸单月销量破6">轩逸单月销量破6.1万</a></li>
				<li class="odd" clstag="homepage|keycount|home2013|11b1"><a href="https://www.dongfeng-nissan.com.cn/about/news/brand/2020/02/2020022601" target="_blank" title="减排技术改造">减排技术改造</a></li>
				<li clstag="homepage|keycount|home2013|11b1"><a href="https://www.dongfeng-nissan.com.cn/about/news/brand/2020/02/2020020701" target="_blank" title="共同战疫">共同战疫</a></li>
	</ul>
</div>
    <div class="m m1 catalogue" data-lazyload="1">
        <div class="mt ld">
            <div class="floor"></b></b><b class="fixpng b4"></b></div>
            <h2>贷款计算器</h2>
        </div>
        <span clstag="homepage|keycount|home2013|18b">
	<a target="_blank" title="" href="https://www.dongfeng-nissan.com.cn/buy/loan-calculator">
		<img data-img="2" data-lazyload="/images/Calculator.jpg" width="300" height="230" alt="贷款计算器" />
	</a>
</span>
    </div>
</div>

<!--virtuals end-->
<span class="clr"></span>
</div>


<!-- footer start -->
<jsp:include page="commons/footer.jsp" />
<!-- footer end -->
 
<script type="text/javascript" src="/js/home.js" charset="utf-8"></script>
</body>
</html>