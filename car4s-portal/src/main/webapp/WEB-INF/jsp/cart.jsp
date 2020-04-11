<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
   <meta http-equiv="pragma" content="no-cache">
   <meta http-equiv="cache-control" content="no-cache">
   <meta http-equiv="expires" content="0"> 
   <meta name="format-detection" content="telephone=no">  
   <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"> 
   <meta name="format-detection" content="telephone=no">
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <link rel="stylesheet" href="/css/base.css">
   <link href="/css/purchase.2012.css?v=201410141639" rel="stylesheet" type="text/css">
   <title>我的收藏</title>
   <script>
   	var pageConfig  = {};
   </script>
<body> 
<!--shortcut start-->
<jsp:include page="commons/shortcut.jsp" />
<!--shortcut end-->
<div class="w" id="headers">
    <div id="logo"><a href="/"><img alt="首页" src="/images/tubiao.jpg" height="100" width="100"></a></div>
    <div class="clr"></div>
</div>
<div class="w">
	<div>
		<h2>我的收藏</h2>
	</div>
	<div id="show">
	
<div class="cart-frame">
    <div class="tl"></div>
    <div class="tr"></div>
</div>
<div class="cart-inner">
    <div class="cart-thead clearfix">
        <div class="column t-checkbox form"><input data-cart="toggle-cb" name="toggle-checkboxes" id="toggle-checkboxes_up" type="checkbox" checked="" value=""><label for="toggle-checkboxes_up">全选</label></div>
        <div class="column t-goods">车辆</div>
        <div class="column t-price">价格</div>
        <div class="column t-inventory">库存</div>
        <div class="column t-action">操作</div>
    </div>
    <div id="product-list" class="cart-tbody">
        <!-- ************************商品开始********************* -->
        <c:set var="totalPrice" value="0"></c:set>
        <c:forEach items="${cartList}" var="cart">
        	<c:set var="totalPrice"  value="${ totalPrice + (cart.price)}"/>
	        <div id="product_11345721" data-bind="rowid:1" class="item item_selected ">
		        <div class="item_form clearfix">
		            <div class="cell p-checkbox"><input data-bind="cbid:1" class="checkbox" type="checkbox" name="checkItem" checked="" value="11345721-1"></div>
		            <div class="cell p-goods">
		                <div class="p-img">
		                	<a href="/item/${cart.id }.html" target="_blank">
		                		<img clstag="clickcart|keycount|xincart|p-imglistcart" src="${cart.image}" alt="${cart.title}" width="52" height="52">
		                	</a>
		                </div>    
		                <div class="p-name">
		                	<a href="/item/${cart.id }.html" clstag="clickcart|keycount|xincart|productnamelink" target="_blank">${cart.title}</a>
		                	<span class="promise411 promise411_11345721" id="promise411_11345721"></span>
		                </div>    
		            </div>
		            <div class="cell p-price"><span class="price">¥<fmt:formatNumber groupingUsed="false" value="${cart.price / 100}" maxFractionDigits="2" minFractionDigits="2"/> </span></div>
		            <div class="cell p-inventory stock-11345721">有货</div>
		            <div class="cell p-remove"><a id="remove-11345721-1" data-more="removed-87.20-1" clstag="clickcart|keycount|xincart|btndel318558" class="cart-remove" href="/cart/delete/${cart.id}.html">删除</a>
		            </div>
		        </div>
	        </div> 
        </c:forEach>
        
    </div><!-- product-list结束 -->
          <div class="cart-toolbar clearfix">
            <div class="total fr">
                <p><span class="totalSkuPrice">¥<fmt:formatNumber value="${totalPrice / 100}" maxFractionDigits="2" minFractionDigits="2" groupingUsed="true"/></span>总计：</p>
            </div>
        </div>
        <div class="ui-ceilinglamp-1" style="width: 988px; height: 49px;"><div class="cart-dibu ui-ceilinglamp-current" style="width: 988px; height: 49px;">
          <div class="control fdibu fdibucurrent">
              <span class="column t-checkbox form">
                  <input data-cart="toggle-cb" name="toggle-checkboxes" id="toggle-checkboxes_down" type="checkbox" checked="" value="" class="jdcheckbox">
                  <label for="toggle-checkboxes_down">
                          全选
                  </label>
              </span>
              <span class="delete">
                  <b>
                  </b>
                  <a href="javascript:void(0);" clstag="clickcart|keycount|xincart|clearcartlink" id="remove-batch">
                          删除选中的商品
                  </a>
              </span>
              <span class="shopping">
                  <b>
                  </b>
                  <a href="/" target="_blank" clstag="clickcart|keycount|xincart|coudanlink" id="continue">继续浏览</a>
              </span>
          </div>
          <div class="cart-total-2014">
              <div class="cart-button">
                  <span class="check-comm-btns" id="checkout-jd">
                      <a class="checkout" href="/order/order-cart.html" clstag="clickcart|keycount|xincart|gotoOrderInfo" id="toSettlement">去结算<b></b></a>
                  </span>
              </div>
              <div class="total fr">
                  总计：
                  <span class="totalSkuPrice">¥<fmt:formatNumber value="${totalPrice / 100}" maxFractionDigits="2" minFractionDigits="2" groupingUsed="true"/></span>
              </div>
          </div>
      </div></div>
</div><!-- cart-inner结束 -->
</div>
</div>
<!--推荐位html修改处-->


<script type="text/javascript" src="/js/base-v1.js"></script>
<!-- footer start -->
<jsp:include page="commons/footer.jsp" />
<!-- footer end -->

<!-- 购物车相关业务 -->
<script type="text/javascript" src="/js/cart.js"></script>
<script type="text/javascript" src="/js/jquery.price_format.2.0.min.js"></script>

</html>