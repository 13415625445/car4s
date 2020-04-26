<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="/js/base-v1.js" charset="utf-8"></script>
<!--shortcut start-->
<jsp:include page="shortcut.jsp" />
<!--shortcut end-->
<div id="o-header-2013">
	<div class="w" id="header-2013">
		<div id="logo-2013" class="ld"><a href="/" hidefocus="true" clstag="homepage|keycount|home2013|02a"><b></b><img src="/images/car4S-logo.jpg" width="200" height="60" alt="少强汽车4S店"></a></div>
		<!--logo end-->
		<div id="search-2013">
			<div class="i-search ld">
				<ul id="shelper" class="hide">
				</ul>
				<div class="form">
					<input type="text" class="text" accesskey="s" id="key" autocomplete="off" onkeydown="javascript:if(event.keyCode==13) search('key');">
					<input type="button" value="搜索" class="button" onclick="search('key');return false;" clstag="homepage|keycount|home2013|03a">
				</div>
			</div>
		</div>
		<!--search end-->
		<%--<div id="my360buy-2013" clstag="homepage|keycount|home2013|05a">--%>
			<%--<dt class="ld"><s></s><span class="ld"><span></span></span><a href="">个人中心</a> <b></b> </dt>--%>
		<%--</div>--%>
		<!--my360buy end-->
		<div id="settleup-2013" clstag="homepage|keycount|home2013|05a">
				<dt class="ld"><s></s><span class="shopping"><span id="shopping-amount"></span></span><a href="/cart/cart.html" id="settleup-url">去收藏中心</a> <b></b> </dt>
		</div>
		<!--settleup end-->
	</div>
	<!--header end-->
	<div class="w">
		<div id="nav-2013">
			<div id="categorys-2013" class="categorys-2014">
				<div class="mt ld">
					<h2><a href="/" clstag="homepage|keycount|home2013|06a">全部汽车分类<b></b></a></h2>
				</div>
				<div id="_JD_ALLSORT" class="mc">
					<div class="extra">
						<a {if="" pageconfig.ishome}clstag="homepage|keycount|home2013|0614a"
							{="" if}="" href="http://www.jd.com/allSort.aspx">全部商品分类</a>
					</div>
				</div>
			</div>
			<div id="treasure" clstag="homepage|keycount|home2013|08a"></div>
				<ul id="navitems-2013">
					<li class="fore1" id="nav-home" clstag="homepage|keycount|home2013|07a"><a href="/">首页</a></li>
					<li class="fore2" id="nav-fashion" clstag="homepage|keycount|home2013|07b"><a href="http://localhost:8082/search.html?q=三">三厢车</a></li>
					<li class="fore3" id="nav-chaoshi" clstag="homepage|keycount|home2013|07c"><a href="http://localhost:8082/search.html?q=两">两厢车</a></li>
					<li class="fore4" id="nav-tuan" clstag="homepage|keycount|home2013|07d"><a href="http://localhost:8082/search.html?q=SUV">SUV</a></li>
					<li class="fore5" id="nav-auction" clstag="homepage|keycount|home2013|07e"><a href="http://localhost:8082/recommend.html">推荐</a></li>
					<li class="fore6" id="nav-shan" clstag="homepage|keycount|home2013|07f"><a href="http://localhost:8082/contrast/show.html" target="_blank">对比车辆</a></li>
				</ul>
		</div>
	</div>
</div>
<script type="text/javascript">
(function(){if(pageConfig.navId){var object=document.getElementById("nav-"+pageConfig.navId);if(object)object.className+=" curr";}})();
</script>