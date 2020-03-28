<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="w" clstag="homepage|keycount|home2013|37a">
	<div id="service-2013">
		<dl class="fore1">
			<dt><b></b><strong>指南</strong></dt>
			<dd>
				<div><a href="http://help.jd.com/help/question-56.html" target="_blank" rel="nofollow">流程</a></div>
				<div><a href="http://help.jd.com/help/question-57.html" target="_blank" rel="nofollow">会员介绍</a></div>
				<div><a href="http://help.jd.com/help/question-61.html" target="_blank" rel="nofollow">常见问题</a></div>
				<div><a href="http://help.jd.com/index.html" target="_blank" rel="nofollow">联系客服</a></div>
			</dd>
		</dl>
		<dl class="fore4">		
			<dt><b></b><strong>售后服务</strong></dt>
			<dd>
				<div><a href="http://myjd.jd.com/afs/help/afshelp.action" target="_blank" rel="nofollow">售后政策</a></div>
				<div><a href="http://help.jd.com/help/question-99.html" target="_blank" rel="nofollow">价格保护</a></div>
				<div><a href="http://help.jd.com/help/question-881.html" target="_blank" rel="nofollow">取消预约</a></div>
			</dd>
		</dl>
</div>
<div class="w" clstag="homepage|keycount|home2013|38a">
	<jsp:include page="footer-links.jsp"></jsp:include>
</div>
<script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
<script type="text/javascript" src="/js/jquery-extend.js"></script>
<script type="text/javascript" src="/js/lib-v1.js" charset="utf-8"></script>
<script type="text/javascript" src="/js/taotao.js" charset="utf-8"></script>
<script type="text/javascript"> (function(){
var B=["三厢车","两厢车","SUV","新能源车"];
B=pageConfig.FN_GetRandomData(B);
var _searchValue = "${query}";
if(_searchValue.length == 0){
	_searchValue = B;
}
$("#key").val(_searchValue).bind("focus",function(){if (this.value==B){this.value="";this.style.color="#333"}}).bind("blur",function(){if (!this.value){this.value=B;this.style.color="#999"}});
})();
</script>
</div>