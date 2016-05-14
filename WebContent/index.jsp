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
				Autores
				</div>	
				<div class="panel-body">
					<c:forEach var="autor" items="${autores}">
						<div class="media">
							<h4><a href="/Blog/postsPorAutor?id=${autor.usuario.id }">${autor.usuario.nome }</a></h4>
						</div>
					</c:forEach>
				</div>
			</div>
			<form action="procurar" method="get">
				<div class="col-xs-12" >
				    <div class="right-inner-addon">
				        <i class="glyphicon glyphicon-search"></i>
				        <input type="search" class="form-control" placeholder="Pesquisar por postagem ou tag." name="procurar" />
				    </div>
				</div>
			</form>
		</div>
		<div id="postagens" class="painel-postagem" >
		
			<c:if test="${empty postagens }"><h2>${mensagem }</h2></c:if>
		
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
		<div>
		</div>
	</body>
</html>

<script>
	
	function redirecionaParaPostagem(id) {
		location.href="/Blog/postagem?id=" + id;
	}

</script>
