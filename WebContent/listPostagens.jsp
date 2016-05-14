<jsp:include page="includeBibliotecas.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>Listagem de Postagem</title>
	</head>
	<body>
		<jsp:include page="cabecalho.jsp"></jsp:include>
		<jsp:include page="mensagem.jsp"></jsp:include>
		<jsp:include page="menuLateral.jsp"></jsp:include>
		<div class="conteudo-privado">
			<h1 class="text-center" style="width: 55%;">Listagem de Postagem</h1>
			<div class="table-responsive">
				<table class="table table-bordered table-privado">
					<tr class="active">
						<th>Autor</th>
						<th>Título</th>
						<th style="width: 8%;">Ações</th>
					</tr>				
					<c:forEach var="postagem" items="${postagens}">  
						<tr> 
							<td><c:out value="${postagem.usuario.nome }"></c:out></td>
							<td><c:out value="${postagem.titulo }"></c:out></td>
							<td>
								<button type="button" class="btn btn-link" style="padding: 10%;" onclick="redirecionaParaEdicao(${postagem.id});" title="Editar">
									<span class="glyphicon glyphicon-pencil"></span>
								</button>
								<button type="button" class="btn btn-link" style="padding: 10%;" onclick="deletePostagem(this, ${postagem.id})" title="Excluir">
									<span class="glyphicon glyphicon-remove" style="color: red;"></span>	
								</button>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<br>
			<br>
			<button type="button" class="btn btn-primary" style="float: right;margin-right: 20%;" id="novaPostagem" >Nova Postagem</button>
		</div>
	</body>
</html>

<script>

	jQuery('#novaPostagem').click(function () {
		location.href="/Blog/cadPostagem";		
	});

	function redirecionaParaEdicao(id) {
		location.href="/Blog/editPostagem?id=" + id;
	}
	
	function deletePostagem(objButton, id) {
		if (desejaExcluirAPostagem(objButton)) {
			location.href="/Blog/deletePostagem?id=" + id;
		}
	}
	
	function desejaExcluirAPostagem(objButton) {
		var tituloDaPostagem = jQuery(jQuery(objButton).closest('tr').find('td').get(1)).html();
		return confirm("Deseja excluir a postagem \"" + tituloDaPostagem + "\"?");
	}
</script>