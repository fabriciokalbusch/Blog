<jsp:include page="includeBibliotecas.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>Blog</title>
	</head>
	<body>
		<jsp:include page="cabecalhoPublico.jsp"></jsp:include>
		
		<div id="menuLateral" class="painel-menu">
			<div class="panel panel-primary">
				<div class="panel-heading">
				Autor
				</div>	
				<div class="panel-body">
					<div class="media">
						<div class="media-body">
							<h4 class="media-heading">${autor.usuario.nome }</h4>
						</div>
						${autor.perfil}
					</div>
				</div>
			</div>
		</div>
		<div id="postagem" class="painel-postagem" >
			
			<input type="hidden" value="${postagem.id }" id="idPostagem">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">${postagem.titulo} <small style="color : white;">&nbsp&nbsp&nbsp por ${postagem.usuario.nome } as ${postagem.dataPostagemFormatado }</small></h3>
				</div>
				<div class="panel-body">
					${postagem.conteudo}
				</div>
			</div>
			<div style="margin-top: -1%;margin-left: 1.1%;"><label>Tags:</label>
				<c:forEach var="tag" items="${postagem.tags }">
					<a href="/Blog/procurar?procurar=${tag.descricao }">#${tag.descricao }</a>, &nbsp
				</c:forEach>
			</div>
		</div>
		
		<div id="comentarios" class="painel-comentarios">
			<h2>Comentários:</h2>
			<c:forEach var="comentario" items="${postagem.comentarios }">
				<div class="panel panel-primary">
					<div class="panel-body">
						<input type="hidden" value="${comentario.id }">
 						<h4><strong> ${comentario.nome } </strong></h4>
						<br> ${comentario.descricao }
						<br><p class="text-right">${comentario.dataComentarioFormatada }</p>
					</div>
				</div>
			</c:forEach>
		</div>

		<div id="realizaComentario" class="painel-realiza-comentario">
			<div class="panel panel-primary">
				<div class="panel-body">
					<h3><label>Comente esta postagem:</label></h3>
					<table class="form-conteudo">
						<tr>
							<td align="right"><label>Nome:</label></td>
							<td><input type="text" class="form form-control" id="nome" name="nome"></td>
						</tr>
						<tr>
							<td align="right"><label>Comentário:</label></td>
							<td><textarea class="form-control" rows="10" cols="120" name="comentario" id="comentario" style="resize:none;"></textarea></td>
						</tr>
						<tr>
							<td></td>
							<td><button type="button" class="btn btn-primary" onclick="salvarComentario();" style="float: right;">Comentar</button></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		
	</body>
</html>

<script>

	function salvarComentario() {
		
		var nome = jQuery('#nome').val();
		var comentario = jQuery('#comentario').val();
		var idPostagem = jQuery('#idPostagem').val();
		
		if (nome == "") {
			alert("Favor preencher o nome.");
			return;
		} 
			
		if (comentario == "") {
			alert("Favor preencher o comentário.");
			return;
		}
		
		var request = jQuery.ajax({  
			data: {idPostagem : idPostagem, nome : nome, comentario : comentario },  
			url: '/Blog/comentario',  
			async : false,
			type: 'POST'  
 		});
		
		request.done(function (id) {
			var divPostagem = '<div class="panel panel-primary"><div class="panel-body">';
			divPostagem += '<input type="hidden" value="' + id + '">';
			divPostagem += '<h4><strong>' + nome + '</strong></h4>'; 
			divPostagem += '<br>' + comentario;
			divPostagem += '<br><p class="text-right">' + getDataHoraAtual() + '</p>';
			divPostagem += '</div></div>';
			jQuery('#comentarios').append(divPostagem);
			
			jQuery('#nome').val("");
			jQuery('#comentario').val("");
			
			alert("Comentário realizado com sucesso!");
		});
		
		request.fail(function () {
			alert("Erro ao realizar o comentário, tente novamente!");
		});
	}
	
	function getDataHoraAtual() {
		var dformat;
		var d = new Date,
	    dformat = [d.getDate(),
	               d.getMonth()+1,
	               d.getFullYear()].join('/')+
	              ' ' +
	              [d.getHours(),
	               d.getMinutes()].join(':');
		return dformat;
	}

</script>
