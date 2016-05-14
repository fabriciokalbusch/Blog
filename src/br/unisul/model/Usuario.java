package br.unisul.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Usuario")
public class Usuario {

	public static final String TP_USUARIO_MODERADOR = "M";
	public static final String TP_USUARIO_AUTOR = "A";
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String login;
	private String senha;
	private String tpUsuario;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTpUsuario() {
		return tpUsuario;
	}

	public void setTpUsuario(String tpUsuario) {
		this.tpUsuario = tpUsuario;
	}
	
	public void setTpUsuarioModerador() {
		this.tpUsuario = TP_USUARIO_MODERADOR;
	}
	
	public void setTpUsuarioAutor() {
		this.tpUsuario = TP_USUARIO_AUTOR;
	}
	
}