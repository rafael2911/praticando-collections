package br.com.collections.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Time {

	private Long id;
	private String nome;
	private LocalDate dataCriacao;
	private String corUniformePrincipal;
	private String corUniformeSecundario;
	private List<Jogador> jogadores = new ArrayList<>();
	private Jogador capitao;

	public Time(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal,
			String corUniformeSecundario) {
		this.id = id;
		this.nome = nome;
		this.dataCriacao = dataCriacao;
		this.corUniformePrincipal = corUniformePrincipal;
		this.corUniformeSecundario = corUniformeSecundario;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public String getCorUniformePrincipal() {
		return corUniformePrincipal;
	}

	public String getCorUniformeSecundario() {
		return corUniformeSecundario;
	}
	
	public List<Jogador> getJogadores() {
		return Collections.unmodifiableList(jogadores);
	}
	
	public void adicionaIdJogador(Jogador jogador) {
		this.jogadores.add(jogador);	
	}
	
	public Jogador getCapitao() {
		return capitao;
	}
	
	public void setCapitao(Jogador jogador) {
		this.capitao = jogador;
	}

}
