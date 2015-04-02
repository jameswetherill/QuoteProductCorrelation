<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<spring:url value="/resources/images/banner-graphic.png" var="banner" />
<img src="${banner}" style="width: 1001px;" />

<div class="navbar" style="width: 701px;">
	<div class="navbar-inner">
		<ul class="nav">
			<li style="width: 120px;"><a
				href="<spring:url value="/" htmlEscape="true" />"><i
					class="icon-home"></i> Home</a></li>
			<li style="width: 160px;"><a
				href="<spring:url value="/quotes.html" htmlEscape="true" />"><i
					class="icon-th-list"></i> Quotes</a></li>
			<li style="width: 160px;"><a
				href="<spring:url value="/products.html" htmlEscape="true" />"><i
					class="icon-th-list"></i> Products</a></li>
			<li style="width: 160px;"><a
				href="<spring:url value="/qpcs.html" htmlEscape="true" />"><i
					class="icon-th-list"></i> Quote to Product</a></li>
			<li style="width: 160px;"><a
				href="<spring:url value="/process.html" htmlEscape="true" />"><i
					class="icon-th-list"></i> Process Text</a></li>
		</ul>
	</div>
</div>

