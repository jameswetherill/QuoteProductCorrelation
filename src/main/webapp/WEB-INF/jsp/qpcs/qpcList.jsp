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

		<h2>Quote to Product connector</h2>

		<datatables:table id="qpc" data="${qpcs.qpcList}" row="quote"
			theme="bootstrap2" cssClass="table table-striped" pageable="false"
			info="false">
			<datatables:column title="Quote">
				<c:out value="${qpc.quote.description}"></c:out>
			</datatables:column>
			<datatables:column title="Author">
				<c:out value="${qpc.product.description}"></c:out>
			</datatables:column>			
		</datatables:table>

		<!--  table class="table-buttons">
        <tr>
            <td>
                <a href="<spring:url value="/quotes.xml" htmlEscape="true" />">View as XML</a>
            </td>
            <td>
                <a href="<spring:url value="/quotes.atom" htmlEscape="true" />">Subscribe to Atom feed</a>
            </td>
        </tr>
    </table -->

		<jsp:include page="../fragments/footer.jsp" />
	</div>
</body>

</html>
