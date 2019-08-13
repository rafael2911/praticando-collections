package br.com.collections;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.collections.exception.CapitaoNaoInformadoException;
import br.com.collections.exception.IdentificadorUtilizadoException;
import br.com.collections.exception.JogadorNaoEncontradoException;
import br.com.collections.exception.TimeNaoEncontradoException;
import br.com.collections.model.Jogador;
import br.com.collections.model.Time;

public class GerenciadorDeTimes {
	
	private Map<Long, Time> times = new HashMap<>();
	private Map<Long, Jogador> jogadores = new HashMap<>();
	
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		identificadorExistente(times.containsKey(id));
		
		times.put(id, new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
	}

	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		identificadorExistente(jogadores.containsKey(id));
		Time time = findTimeById(idTime);
		Jogador jogador = new Jogador(id, nome, dataNascimento, nivelHabilidade, salario, time);
		time.adicionaIdJogador(jogador);
		jogadores.put(id, jogador);
	}

	public void definirCapitao(Long idJogador) {
		Jogador jogador = findJogadorById(idJogador);
		Time time = jogador.getTime();
		time.setCapitao(jogador);
	}
	
	public Long buscarCapitaoDoTime(Long idTime) {
		Jogador capitao = findTimeById(idTime).getCapitao();
		
		if(capitao == null)
			throw new CapitaoNaoInformadoException();
		
		return capitao.getId();
	}
	
	public String buscarNomeJogador(Long idJogador) {
		
		return findJogadorById(idJogador).getNome();
	}
	
	public String buscarNomeTime(Long idTime) {
		
		return findTimeById(idTime).getNome();
	}
	
	public List<Long> buscarJogadoresDoTime(Long idTime){
		List<Long> jogadores = findTimeById(idTime).getJogadores().stream()
				.map(Jogador::getId).collect(Collectors.toList());
		
		jogadores.sort(Long::compareTo);
		
		return jogadores;
	}
	
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		Time time = findTimeById(idTime);
		return time.melhorJogadorDoTime();
	}
	
	public Long buscarJogadorMaisVelho(Long idTime) {
		Time time = findTimeById(idTime);
		return time.jogadorMaisVelho();
	}
	
	public List<Long> buscarTimes(){
		ArrayList<Long> listaDeTimes = new ArrayList<Long>(times.keySet());
		listaDeTimes.sort(Long::compareTo);
		return listaDeTimes;
	}
	
	public Long buscarJogadorMaiorSalario(Long idTime) {
		Time time = findTimeById(idTime);
		return time.jogadorMaiorSalario();
	}
	
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		
		return findJogadorById(idJogador).getSalario();
	}
	
	public List<Long> buscarTopJogadores(Integer top){
		
		return this.jogadores.values().stream().sorted((j1, j2) -> {
			final int comparador = Integer.compare(j2.getNivelHabilidade(), j1.getNivelHabilidade());
			if(comparador == 0)
				return Long.compare(j1.getId(), j2.getId());
			
			return comparador;
		}).limit(top)
				.map(Jogador::getId).collect(Collectors.toList());
		
	}
	
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long TimeDeFora) {
		
		return null;
	}
	
	/*
	 * ########## METODOS PRIVADOS ##########
	 */
	private void identificadorExistente(boolean containsKey) {
		if(containsKey)
			throw new IdentificadorUtilizadoException();
	}
	
	private Time findTimeById(Long idTime) {
		Time time = times.get(idTime);
		
		if(time == null)
			throw new TimeNaoEncontradoException();
		
		return time;
	}
	
	private Jogador findJogadorById(Long idJogador) {
		Jogador jogador = jogadores.get(idJogador);
		
		if(jogador == null)
			throw new JogadorNaoEncontradoException();
		
		return jogador;
	}
	
}
