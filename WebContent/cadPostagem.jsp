<jsp:include page="includeBibliotecas.jsp"></jsp:include>
<jsp:useBean id="postagem" class="br.unisul.model.Postagem" scope="request"></jsp:useBean>
<script src="/Blog/js/tinymce.min.js"></script>
<html>
	<head>
		<title>Cadastro de Postagem</title>
	</head>
	<body>
		<jsp:include page="cabecalho.jsp"></jsp:include>
		<jsp:include page="mensagem.jsp"></jsp:include>
		<jsp:include page="menuLateral.jsp"></jsp:include>
		<div class="conteudo-privado">
			<h1 class="text-center" style="width: 70%;">Cadastro de Postagem</h1>
			<form action="${action }" method="post">
				<input type="hidden" name="id" value="${postagem.id }"/>
				<table class="form-conteudo" style="margin-left: 8%;">
					<tr>
						<td align="right"><label>Título*:</label></td>
						<td><input type="text" class="form form-control" id="titulo" name="titulo" value="${postagem.titulo}" size="117"></td>
					</tr>
					<tr>
						<td align="right"><label>Conteúdo*: </label></td>
						<td><textarea class="form-control" rows="10" cols="120" name="conteudo" id="conteudo">${postagem.conteudo }</textarea></td>
					</tr>
					<tr>
						<td align="right"><label>Tags*: </label></td>
						<td><input type="text" class="form form-control" id="tag" name="tag" value="${postagem.descricaoTags }" placeholder="As tag's deverão estar separadas por ponto e vírgula. Ex.: Copa; Brasil; Grupo A"></td>
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

	tinymce.init({
	    selector: "textarea",
	    plugins: [
	        "advlist autolink lists link image charmap print preview anchor",
	        "searchreplace visualblocks code fullscreen",
	        "insertdatetime media table contextmenu paste"
	    ],
	    toolbar: "insertfile undo redo | styleselect | fontsizeselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image",
	    language: "pt_BR"
	});
	
	jQuery('#voltar').click(function () {
		location.href="/Blog/listPostagem";
	});
	
	jQuery('form').submit(function (event) {
		
		var $titulo = jQuery('#titulo');
		if ($titulo.val() == "") {
			alert("Favor preencher o titulo.");
			event.preventDefault();
			return;
		}
		
		var $tag = jQuery('#tag'); 
		if ($tag.val() == "") {
			alert("Favor preencher as tags.");
			event.preventDefault();
			return;
		}
		
	});
	
</script>