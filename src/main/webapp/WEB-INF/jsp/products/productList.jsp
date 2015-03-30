<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="datatables"
	uri="http://github.com/dandelion/datatables"%>

<html lang="en">


<jsp:include page="../fragments/staticFiles.jsp" />

<body>
	<div class="container">
		<jsp:include page="../fragments/bodyHeader.jsp" />

		<h2>Quotes</h2>

		<datatables:table id="products" data="${products.productList}" row="product"
			theme="bootstrap2" cssClass="table table-striped" pageable="false"
			info="false">
			<datatables:column title="Product Description">
				<c:out value="${product.description}"></c:out>
			</datatables:column>
			<datatables:column title="Retailer">
				<c:out value="${product.author}"></c:out>
			</datatables:column>
			<datatables:column title="Topics" sortable="false">
				<c:if test="${product.nrOfTopics > 0}">
					<table>
						<c:forEach var="topic" items="${product.topics}">
							<tr>
								<td><c:out value="${topic.description}" /></td>
								<td><c:out value="${topic.type.name}" /></td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
				<c:if test="${product.nrOfTopics == 0}">none</c:if>
			</datatables:column>
		</datatables:table>

		<!--  table class="table-buttons">
        <tr>
            <td>
                <a href="<spring:url value="/products.xml" htmlEscape="true" />">View as XML</a>
            </td>
            <td>
                <a href="<spring:url value="/products.atom" htmlEscape="true" />">Subscribe to Atom feed</a>
            </td>
        </tr>
    </table -->

		<jsp:include page="../fragments/footer.jsp" />
	</div>
</body>

</html>
