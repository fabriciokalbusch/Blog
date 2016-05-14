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
		<div id="postagens" class="painel-postagem" >
			<c:forEach var="postagem" items="${postagens}">
				<div class="panel panel-primary">
					<div class="panel-heading" style="cursor : pointer;" onclick="redirecionaParaPostagem(${postagem.id})">
						<h3 class="panel-title">${postagem.titulo} <small style="color : white;">&nbsp&nbsp&nbsp por ${postagem.usuario.nome } as ${postagem.dataPostagemFormatado }</small></h3>
					</div>
					<div class="panel-body">
						${postagem.conteudo}
					</div>
				</div>
			</c:forEach>
		</div>
	</body>
</html>

<script>
	
	function redirecionaParaPostagem(id) {
		location.href="/Blog/postagem?id=" + id;
	}

</script>