<div>
	<div id="alert-sucesso" class="alert alert-success alert-blog"></div>
	<div id="alert-info" class="alert alert-warning alert-blog"></div>
	<div id="alert-erro" class="alert alert-danger alert-blog"></div>
</div>
<script>
	jQuery(function () {
		recolheAlerts();
		mostraMensagemVindaDoServidor();
	});
	
	function alertSucesso(msg) {
		recolheAlerts();
		jQuery('#alert-sucesso').html(msg);
		jQuery('#alert-sucesso').show();
	}
	
	function alertInformacao(msg) {
		recolheAlerts();
		jQuery('#alert-info').html(msg);
		jQuery('#alert-info').show();
	}
	
	function alertErro(msg) {
		recolheAlerts();
		jQuery('#alert-erro').html(msg);
		jQuery('#alert-erro').show();
	}
	
	function recolheAlerts() {
		jQuery('#alert-sucesso').hide();		
		jQuery('#alert-info').hide();
		jQuery('#alert-erro').hide();		
	}
	
	function mostraMensagemVindaDoServidor() {
		var mensagemInformacao = '${requestScope.MSG_INFORMACAO}';
		if (mensagemInformacao.length > 0) {
			alertInformacao(mensagemInformacao);
			return;
		}
		
		var mensagemSucesso = '${requestScope.MSG_SUCESSO}';
		if (mensagemSucesso.length > 0) {
			alertSucesso(mensagemSucesso);
			return;
		}
	}
	
</script>