<jsp:include page="includeBibliotecas.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>Listagem de Autores</title>
	</head>
	<body>
		<jsp:include page="cabecalho.jsp"></jsp:include>
		<jsp:include page="mensagem.jsp"></jsp:include>
		<jsp:include page="menuLateral.jsp"></jsp:include>
		
		<div class="conteudo-privado">
			<h1 class="text-center" style="width: 50%;">Listagem de Autores</h1>
			<div class="table-responsive table-privado">
				<table class="table table-bordered">
					<tr class="active">
						<th>Autor</th>
						<th>Login</th>
						<th style="width: 8%;">Ações</th>
					</tr>
					<c:forEach var="autor" items="${autores}">  
						<tr> 
							<td><c:out value="${autor.usuario.nome }"></c:out></td>
							<td><c:out value="${autor.usuario.login }"></c:out></td>
							<td>
								<button type="button" class="btn btn-link" style="padding: 10%;" onclick="redirecionaParaEdicao(${autor.id});" title="Editar">
									<span class="glyphicon glyphicon-pencil"></span>
								</button>
								<button type="button" class="btn btn-link" style="padding: 10%;" onclick="deleteAutor(this, ${autor.id})" title="Excluir">
									<span class="glyphicon glyphicon-remove" style="color: red;"></span>	
								</button>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<br>
			<br>
			<button type="button" class="btn btn-primary" style="float: right;margin-right: 20%;" id="novoAutor" >Novo Autor</button>
		</div>
	</body>
</html>

<script>

	jQuery('#novoAutor').click(function () {
		location.href="/Blog/cadAutor";		
	});

	function redirecionaParaEdicao(id) {
		location.href="/Blog/editAutor?id=" + id;
	}
	
	function deleteAutor(objButton, id) {
		if (desejaExcluirOAutor(objButton)) {
			location.href="/Blog/deleteAutor?id=" + id;
		}
	}
	
	function desejaExcluirOAutor(objButton) {
		var nomeDoAutor = jQuery(objButton).closest('tr').find('td').first().html();
		return confirm("Deseja excluir o(a) autor(a) " + nomeDoAutor + "?");
	}
</script>