package br.unisul.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Postagem")
public class Postagem {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	@Column(columnDefinition = "TEXT")
	private String conteudo;
	@OneToOne
	private Usuario usuario;
	@OneToMany(mappedBy="postagem")
	private List<Tag> tags;
	@OneToMany(mappedBy="postagem")
	private List<Comentario> comentarios;
	private Date dataPostagem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Tag> getTags() {
		return tags;
	}
	
	public String getDescricaoTags() {
		if (this.tags == null) {
			return "";
		}
		
		StringBuilder descricao = new StringBuilder();
		for (Tag tag : this.tags) {
			descricao.append(tag.getDescricao());
			descricao.append(";");
		}
		return descricao.substring(0, descricao.length() - 1);
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Date getDataPostagem() {
		return dataPostagem;
	}

	public void setDataPostagem(Date dataPostagem) {
		this.dataPostagem = dataPostagem;
	}
	
	public String getDataPostagemFormatado() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return sdf.format(this.dataPostagem);
	}
	
}
