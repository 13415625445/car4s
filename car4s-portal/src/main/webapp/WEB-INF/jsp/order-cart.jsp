<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<meta name="format-detection" content="telephone=no" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<meta name="format-detection" content="telephone=no" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>预约信息</title>
	<!--结算页面样式-->
	<link rel="stylesheet" type="text/css" href="/css/base.css" media="all" />
	<link type="text/css" rel="stylesheet"  href="/css/order-commons.css" source="widget"/>
	<script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
	<script type="text/javascript" src="/js/base.js"></script>
	<script type="text/javascript" src="/js/order.common.js"></script>
	<script type="text/javascript" src="/js/jquery.checkout.js"></script>
</head>	<body id="mainframe">
<jsp:include page="commons/shortcut.jsp" />
<!--shortcut end-->

<div class="w" id="headers">
	<div id="logo"><a href="/"><img alt="首页" src="/images/tubiao.jpg" height="100" width="100"></a></div>
	<div class="clr"></div>
</div>

<form id="orderForm" class="hide" action="/order/create.html" method="post">
	<input type="hidden" name="paymentType" value="1"/>
	<c:forEach items="${cartList }" var="cart" varStatus="status">
		<c:set var="totalPrice"  value="${ totalPrice + (cart.price)}"/>
		<input type="hidden" name="orderItems[${status.index}].itemId" value="${cart.id}"/>
		<input type="hidden" name="orderItems[${status.index}].num" value="${cart.num }"/>
		<input type="hidden" name="orderItems[${status.index}].price" value="${cart.price}"/>
		<input type="hidden" name="orderItems[${status.index}].totalFee" value="${cart.price * cart.num}"/>
		<input type="hidden" name="orderItems[${status.index}].title" value="${cart.title}"/>
		<input type="hidden" name="orderItems[${status.index}].picPath" value="${cart.image}"/>
	</c:forEach>
	<input type="hidden" name="payment" value="<fmt:formatNumber groupingUsed="false" maxFractionDigits="2" minFractionDigits="2" value="${totalPrice/100 }"/>"/>
	<input type="hidden" name="orderShipping.receiverName" value="入云龙"/>
	<input type="hidden" name="orderShipping.receiverMobile" value="15891588888"/>
	<input type="hidden" name="orderShipping.receiverState" value="北京"/>
	<input type="hidden" name="orderShipping.receiverCity" value="北京"/>
	<input type="hidden" name="orderShipping.receiverDistrict" value="昌平区"/>
	<input type="hidden" name="orderShipping.receiverAddress" value="西三旗 xxxxxxxxx"/>
</form>

<!-- main -->
<div id="container">
	<div id="content" class="w">
		<div class="m">
			<div class="mt">
				<h2>填写并核对预约信息</h2>
			</div>
			<div class="mc">
				<div class="checkout-steps">
					<!--  /widget/consignee-step/consignee-step.tpl -->
					<div class="step-tit">
						<h3>预约人信息</h3>
					</div>
					<div class="step-cont">
						<div class="consignee-list" id="consignee-list1">
							<a href="#none" id="prev" class="prev arrow-btns"></a>
							<a href="#none" id="next" class="next arrow-btns"></a>
							<div id="consignee1" class="list-cont ui-switchable-body">
								<div id="consignee-ret"></div>
								<ul class="ui-switchable-panel-main" id="consignee-list">
									<!---->
									<li class="ui-switchable-panel" id="consignee_index_137617472"
										selected="selected" style="cursor: pointer;">
										<div class="consignee-item item-selected"
											 consigneeId="137617472" id="consignee_index_div_137617472">
											<b></b>
											<div class="user-name">
												<div class="fl">
													<strong limit="4">入云龙</strong>&nbsp;&nbsp;收
												</div>
												<div class="fr">158****8888</div>
												<div class="clr"></div>
											</div>
											<div class="mt10" limit="15">北京 昌平区 五环外六环里</div>
											<div class="adr-m" limit="30">西三旗 xxxxxxxxx</div>
											<div class="op-btns ar">
												<a href="#none"
												   class="ftx-05 mr10 setdefault-consignee hide"
												   fid="137617472">设为默认地址</a> <a href="#none"
																				 class="ftx-05 mr10 edit-consignee" fid="137617472">编辑</a>
												<a href="#none" class="ftx-05 del-consignee hide"
												   fid="137617472">删除</a>
											</div>
										</div>
									</li>
									<!---->
								</ul>
							</div>
						</div>
					</div>
					<!--/ /widget/consignee-step/consignee-step.tpl -->

					<!--/ /widget/shopping-list/shopping-list.tpl -->
					<div id="shipAndSkuInfo">
						<div id="payShipAndSkuInfo">
							<!--/ /widget/payment-step/payment-step.tpl -->
							<div class="step-tit">
								<h3>预约车辆</h3>
								<div class="extra-r">
									<a href="/cart/cart.html" id="cartRetureUrl" class="return-edit ftx-05">返回购物车</a>
								</div>
							</div>
							<div class="step-cont" id="skuPayAndShipment-cont">
								<!--添加商品清单  zhuqingjie -->
								<div class="shopping-lists" id="shopping-lists">
									<div class="shopping-list ABTest">
										<div class="goods-list">
											<div class="goods-suit goods-last">
												<c:forEach items="${cartList }" var="cart">
													<div class="goods-item goods-item-extra">

														<div class="p-img">
															<a target="_blank" href="/item/${cart.id}.html">
																<img src="${cart.image}" alt="">
															</a>
														</div>
														<div class="goods-msg">
															<div class="p-name">
																<a href="/item/${cart.id}.html" target="_blank">
																		${cart.title }
																</a>
															</div>
															<div class="p-price">
																<!--增加预售金额显示 begin   预售分阶段支付类型（1：一阶梯全款支付；2：一阶梯定金支付(全款或定金可选)；3：三阶梯仅定金支付） -->
																<strong>￥<fmt:formatNumber
																		groupingUsed="false" maxFractionDigits="2"
																		minFractionDigits="2" value="${cart.price / 100 }" /></strong>
																<!--增加预售金额显示 end-->
																<span class="ml20 p-inventory" skuId="11555193">有货</span>
															</div>
														</div>
														<div class="clr"></div>
													</div>
												</c:forEach>
											</div>
										</div>
										<!--goods-list 结束-->
										<div class="dis-modes">
											<!--配送方式-->
											<div class="mode-item mode-tab">
												<div class="mode-tab-nav">
												</div>
											</div>
										</div>
										<!--dis-modes 结束-->
										<div class="clr"></div>
										<div class="freight-cont">
										</div>
									</div>

								</div>
							</div>
						</div>
					</div>
					<!--  /widget/invoice-step/invoice-step.tpl -->
					<div class="order-summary">
						<!--  预售 计算支付展现方式 begin -->
						<div class="clr"></div>
					</div>
				</div>
			</div>
			<div class="trade-foot">
				<div id="checkout-floatbar" class="group">
					<div class="ui-ceilinglamp checkout-buttons">
						<div class="sticky-placeholder hide" style="display: none;">
						</div>
						<div class="sticky-wrap">
							<div class="inner">
								<button type="submit" class="checkout-submit btn-1"
										id="order-submit"	onclick="$('#orderForm').submit()">
									提交订单
								</button>
								<span class="total">预计金额：<strong id="payPriceId">￥<fmt:formatNumber value="${totalPrice / 100}" maxFractionDigits="2" minFractionDigits="2" groupingUsed="true"/></strong>
          </span>
								<span id="checkCodeDiv"></span>
							</div>
							<span id="submit_message" style="display:none" class="submit-error" ></span>
							<div class="submit-check-info" id="submit_check_info_message" style="display:none"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>
</div>

<!-- /main -->
<jsp:include page="commons/footer.jsp" />
</body>
</html>