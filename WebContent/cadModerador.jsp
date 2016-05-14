<jsp:include page="includeBibliotecas.jsp"></jsp:include>
<jsp:useBean id="usuario" class="br.unisul.model.Usuario" scope="request"></jsp:useBean>
<html>
	<head>
		<title>Cadastro de Moderador</title>
	</head>
	<body>
		<jsp:include page="cabecalho.jsp"></jsp:include>
		<jsp:include page="mensagem.jsp"></jsp:include>
		<jsp:include page="menuLateral.jsp"></jsp:include>
		<div class="conteudo-privado">
			<h1 class="text-center" style="width: 70%;">Cadastro de Moderador</h1>
			<form action="${action }" method="post">
				<input type="hidden" name="id" value="${usuario.id }"/>
				<table class="form-conteudo">
					<tr>
						<td align="right"><label>Moderador*:</label></td>
						<td><input type="text" class="form form-control" id="moderador" name="moderador" value="${usuario.nome}" size="117"></td>
					</tr>
					<tr>
						<td align="right"><label>Login*: </label></td>
						<td><input type="text" class="form form-control" id="login" name="login" value="${usuario.login }"></td>
					</tr>
					<tr>
						<td align="right"><label>Senha*: </label></td>
						<td><input type="password" class="form form-control" id="senha" name="senha" value="${usuario.senha }"></td>
					</tr>
					<tr>
						<td align="right"><label>Confirmar senha*: </label></td>
						<td><input type="password" class="form form-control" id="senha-confirmar" name="senha-confirmar" value="${usuario.senha }"></td>
					</tr>
				</table>
				<table class="form-conteudo" style="margin-left: 14%;">
					<tr>
						<td align="right" style="width: 9.5%;"><button type="button" class="btn btn-default" id="voltar">Voltar</button></td>
						<td align="right"><button type="submit" class="btn btn-primary">Salvar</button></td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>
<script>
	jQuery('#voltar').click(function () {
		location.href="/Blog/listModerador";
	});
	
	jQuery('form').submit(function (event) {
		var $moderador = jQuery('#moderador');
		
		if ($moderador.val() == "") {
			alert("Favor preencher o moderador.");
			event.preventDefault();
			return;
		}
		
		var $login = jQuery('#login'); 
		if ($login.val() == "") {
			alert("Favor preencher a login.");
			event.preventDefault();
			return;
		}
		
		var $senha = jQuery('#senha'); 
		if ($senha.val() == "") {
			alert("Favor preencher a senha.");
			event.preventDefault();
			return;
		}
		
		var $senhaConfirmar = jQuery('#senha-confirmar'); 
		if ($senhaConfirmar.val() == "") {
			alert("Favor preencher a confirmação de senha.");
			event.preventDefault();
			return;
		}
		
		if ($senha.val() != $senhaConfirmar.val()) {
			alert("Favor preencher o campo confirmação de senha igual ao campo senha.");
			event.preventDefault();
			return;
		}
	});
	
</script>