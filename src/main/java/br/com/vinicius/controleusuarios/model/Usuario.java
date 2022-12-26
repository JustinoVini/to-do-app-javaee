package br.com.vinicius.controleusuarios.model;

/**
 * Usuario.java Classe de modelo, que conterá os atributos do usuario.
 * 
 * @author Vinicius Justino
 *
 */

public class Usuario {
	private int id;
	private String nome;
	private String email;
	private String pais;

	public Usuario() {
	}

	public Usuario(int id, String nome, String email, String pais) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.pais = pais;
	}
	
	public Usuario(String nome, String email, String pais) {
		super();
		this.nome = nome;
		this.email = email;
		this.pais = pais;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

}
