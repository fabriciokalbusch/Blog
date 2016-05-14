<jsp:include page="includeBibliotecas.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>Listagem de Moderadores</title>
	</head>
	<body>
		<jsp:include page="cabecalho.jsp"></jsp:include>
		<jsp:include page="mensagem.jsp"></jsp:include>
		<jsp:include page="menuLateral.jsp"></jsp:include>
		<div class="conteudo-privado">
			<h1 class="text-center" style="width: 55%;">Listagem de Moderadores</h1>
			<div class="table-responsive table-privado">
				<form action="listModerador" method="post" >
					<table style="width: 100%;">
						<tr>
							<td><input type="text" class="form-control" placeholder="Nome" name="nome" style="width: 100%;"></td>
							<td style="width: 32%;"><input type="text" class="form-control" placeholder="Login" name="login" style="width: 100%;"></td>
							<td style="width: 8%;">
								<button type="submit" class="btn btn-link" style="padding: 10%; margin-left: 20%;" title="Editar">
									<span class="glyphicon glyphicon-search" style="font-size: 150%;"></span>
								</button>
							</td>
						</tr>
					</table>
				</form>
				<br>
				<table class="table table-bordered">
					<tr class="active">
						<th>Moderador</th>
						<th style="width: 32%;">Login</th>
						<th style="width: 8%;">Ações</th>
					</tr>				
					<c:forEach var="usuario" items="${moderadores}">  
						<tr> 
							<td><c:out value="${usuario.nome }"></c:out></td>
							<td><c:out value="${usuario.login }"></c:out></td>
							<td>
								<button type="button" class="btn btn-link" style="padding: 10%;" onclick="redirecionaParaEdicao(${usuario.id});" title="Editar">
									<span class="glyphicon glyphicon-pencil"></span>
								</button>
								<button type="button" class="btn btn-link" style="padding: 10%;" onclick="deleteModerador(this, ${usuario.id})" title="Excluir">
									<span class="glyphicon glyphicon-remove" style="color: red;"></span>	
								</button>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<br>
			<br>
			<button type="button" class="btn btn-primary" style="float: right;margin-right: 20%;" id="novoModerador" >Novo Moderador</button>
		</div>
	</body>
</html>

<script>

	jQuery('#novoModerador').click(function () {
		location.href="/Blog/cadModerador";		
	});

	function redirecionaParaEdicao(id) {
		location.href="/Blog/editModerador?id=" + id;
	}
	
	function deleteModerador(objButton, id) {
		if (desejaExcluirOModerador(objButton)) {
			location.href="/Blog/deleteModerador?id=" + id;
		}
	}
	
	function desejaExcluirOModerador(objButton) {
		var nomeDoModerador = jQuery(objButton).closest('tr').find('td').first().html();
		return confirm("Deseja excluir o(a) moderador(a) " + nomeDoModerador + "?");
	}
</script>