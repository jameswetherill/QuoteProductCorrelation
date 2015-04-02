<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="qpc" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="datatables"
	uri="http://github.com/dandelion/datatables"%>


<html lang="en">

<jsp:include page="../fragments/staticFiles.jsp" />
<body>

	<script>
		$(function() {
			$("#birthDate").datepicker({
				dateFormat : 'yy/mm/dd'
			});
		});
	</script>
	<div class="container">
		<jsp:include page="../fragments/bodyHeader.jsp" />
		<c:choose>
			<c:when test="${quote['new']}">
				<c:set var="method" value="post" />
			</c:when>
			<c:otherwise>
				<c:set var="method" value="put" />
			</c:otherwise>
		</c:choose>

		<h2>
			<c:if test="${quote['new']}">New </c:if>
			Quote
		</h2>

		<form:form modelAttribute="quote" method="${method}"
			class="form-horizontal">
			<div class="control-group" id="description">
				<qpc:textAreaField name="description" label="Description" rows="10"
					columns="80" cssStyle="width:350px;" />

			</div>
			<qpc:inputField label="Author" name="author" />
			<div class="control-group">
				<c:if test="${quote.nrOfTopics > 0}">
					<datatables:table id="topics" data="${quote.topics}" row="topic"
						theme="bootstrap2" cssClass="table table-striped" pageable="false"
						info="false">
						<datatables:column title="Description">
							<c:out value="${topic.description}"></c:out>
						</datatables:column>
						<datatables:column title="Description">
							<c:out value="${topic.type.name}"></c:out>
						</datatables:column>
						<datatables:column title="Description">
							<c:out value="${topic.type.name}"></c:out>
						</datatables:column>
					</datatables:table>
				</c:if>
				<c:if test="${quote.nrOfTopics == 0}">none</c:if>
			</div>
			<div class="form-actions">
				<c:choose>
					<c:when test="${quote['new']}">
						<button type="submit">Process Quote</button>
					</c:when>
					<c:otherwise>
						<button type="submit">Save Quote</button>
					</c:otherwise>
				</c:choose>
			</div>
		</form:form>
		<jsp:include page="../fragments/footer.jsp" />
	</div>
</body>

</html>
