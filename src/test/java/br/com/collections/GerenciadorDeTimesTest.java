package br.com.collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import br.com.collections.exception.CapitaoNaoInformadoException;
import br.com.collections.exception.IdentificadorUtilizadoException;
import br.com.collections.exception.JogadorNaoEncontradoException;
import br.com.collections.exception.TimeNaoEncontradoException;

public class GerenciadorDeTimesTest {
	
	private GerenciadorDeTimes gerenciador;
	
	@Before
	public void iniciaGerenciador() {
		gerenciador = new GerenciadorDeTimes();
		gerenciador.incluirTime(1L, "Time 1", LocalDate.of(2000, 05, 20), "Azul", "Branco");
		gerenciador.incluirJogador(1L, 1L, "Jogador 1", LocalDate.of(1991, 02, 14), 80, new BigDecimal("2000.00"));
	}
	
	@Test
	public void testaCadastroDeNovoTime() {
		gerenciador.incluirTime(2L, "Time 2", LocalDate.of(2000, 05, 20), "Verde", "Branco");
		
		assertEquals(2, gerenciador.buscarTimes().size());
		assertEquals(2L, gerenciador.buscarTimes().get(1).longValue());
	}
	
	@Test(expected = IdentificadorUtilizadoException.class)
	public void testaCadastroDeTimeExistente() {
		gerenciador.incluirTime(1L, "Time 1", LocalDate.of(2000, 05, 20), "Azul", "Branco");
		gerenciador.incluirTime(1L, "Time 2", LocalDate.of(2008, 01, 15), "Verde", "Branco");
	}
	
	@Test
	public void testaCadastroDeNovoJogador() {
		gerenciador.incluirJogador(2L, 1L, "Jogador 2", LocalDate.of(1991, 02, 14), 75, new BigDecimal("1980.00"));
		
		assertEquals(2, gerenciador.buscarJogadoresDoTime(1L).size());
		assertEquals(2L,  gerenciador.buscarJogadoresDoTime(1L).get(1).longValue());
	}
	
	@Test(expected = IdentificadorUtilizadoException.class)
	public void testaCadastroDeJogadorExistente() {
		gerenciador.incluirTime(1L, "Time 1", LocalDate.of(2000, 05, 20), "Azul", "Branco");
		gerenciador.incluirJogador(1L, 1L, "Jogador 1", LocalDate.of(1991, 02, 14), 80, new BigDecimal("2000.00"));
		gerenciador.incluirJogador(1L, 1L, "Jogador 2", LocalDate.of(1989, 7, 8), 75, new BigDecimal("1800.00"));
	}
	
	@Test(expected = TimeNaoEncontradoException.class)
	public void testaCadastroDeJogadorEmTimeInexistente() {
		gerenciador.incluirJogador(2L, 2L, "Jogador 1", LocalDate.of(1991, 02, 14), 80, new BigDecimal("2000.00"));

	}
	
	@Test
	public void testaDefinirCapitaoDotime() {
		gerenciador.incluirJogador(2L, 1L, "Jogador 2", LocalDate.of(1989, 7, 8), 75, new BigDecimal("1800.00"));
		gerenciador.definirCapitao(2L);
		
		assertEquals(2L, gerenciador.buscarCapitaoDoTime(1L).longValue());
		assertFalse(gerenciador.buscarCapitaoDoTime(1L).equals(1L));
	}
	
	@Test(expected = JogadorNaoEncontradoException.class)
	public void testaDefinirCapitaoJogadorInexistente() {
		gerenciador.definirCapitao(2L);
		
	}
	
	@Test
	public void testaBuscarCapitaoDoTime() {
		gerenciador.incluirJogador(2L, 1L, "Jogador 2", LocalDate.of(1989, 7, 8), 75, new BigDecimal("1800.00"));
		gerenciador.definirCapitao(2L);
		
		assertFalse(gerenciador.buscarCapitaoDoTime(1L).equals(1L));
	}
	
	@Test(expected = TimeNaoEncontradoException.class)
	public void testaBuscarCapitaoDeTimeInexistente() {
		gerenciador.incluirJogador(2L, 1L, "Jogador 2", LocalDate.of(1989, 7, 8), 75, new BigDecimal("1800.00"));
		gerenciador.definirCapitao(2L);
		
		assertFalse(gerenciador.buscarCapitaoDoTime(2L).equals(1L));
	}
	
	@Test(expected = CapitaoNaoInformadoException.class)
	public void testaBuscarCapitaoDeTimeSemCapitao() {
		gerenciador.buscarCapitaoDoTime(1L);
	}
	
}
