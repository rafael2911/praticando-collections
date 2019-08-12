package br.com.collections.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Jogador {

	private Long id;
	private String nome;
	private LocalDate dataNascimento;
	private Integer nivelHabilidade;
	private BigDecimal salario;
	private Time time;

	public Jogador(Long id, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario, Time time) {
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.nivelHabilidade = nivelHabilidade;
		this.salario = salario;
		this.time = time;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public Integer getNivelHabilidade() {
		return nivelHabilidade;
	}

	public BigDecimal getSalario() {
		return salario;
	}
	
	public Time getTime() {
		return time;
	}

}
