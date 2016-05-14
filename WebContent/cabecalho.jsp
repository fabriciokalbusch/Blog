<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="page-header" style="margin: 10px;">
 		<a href="/Blog/"><button type="button" class="btn btn-link">Início</button></a>
 		<a href="sobre.jsp"><button type="button" class="btn btn-link">Sobre</button></a>
		<c:choose>
		  <c:when test="${sessionScope.usuarioLogado ne null }">
 			<a href="login?logout=true" style="float: right;"><button type="button" class="btn btn-link">Logout</button></a>
		  </c:when>
		  <c:otherwise>
 			<a href="login" style="float: right;"><button type="button" class="btn btn-link">Login</button></a>
		  </c:otherwise>
		</c:choose>
</div>