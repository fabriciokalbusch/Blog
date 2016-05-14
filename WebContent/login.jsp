<jsp:include page="includeBibliotecas.jsp"></jsp:include>
<html>
	<head>
		<title>Login</title>
	</head>
	<body>
		<jsp:include page="cabecalho.jsp"></jsp:include>
		<br>
		<jsp:include page="mensagem.jsp"></jsp:include>
		<form method="post" action="login" >
			<div class="panel panel-default" style="width: 30%; margin-top: 10%; margin-left: 35%; padding-bottom: 1%; padding-top: 1%;">
				<div class="form-group" style="width: 92%; margin-left: 4%;">
					<label for="login">Login</label>
					<input type="text" name="login" class="form-control" id="login" placeholder="Login">
					<br>
					<label for="senha">Senha</label>
				  	<input type="password" name="senha" class="form-control" id="senha" placeholder="Senha">
				</div>
				<button id="entrar" type="submit" class="btn btn-default" style="margin-left: 4%;">Entrar</button>
			</div>
		</form>
	</body>
</html>
<script>

	jQuery('form').submit(function (event) {
		var $login = jQuery('#login');
		var $senha = jQuery('#senha'); 
		console.log($login.val());
		console.log($senha.val());
		
		if ($login.val() == "") {
			alert("Favor preencher o login.");
			event.preventDefault();
			return;
		} else if ($senha.val() == "") {
			alert("Favor preencher a senha.");
			event.preventDefault();
			return;
		}
	});
	
	function alertInformacao(msg) {
		recolheAlerts();
		jQuery('#alert-info').html(msg);
		jQuery('#alert-info').show();
		jQuery('#alert-info').attr('style', 'width: 98%');
	}
	
</script>