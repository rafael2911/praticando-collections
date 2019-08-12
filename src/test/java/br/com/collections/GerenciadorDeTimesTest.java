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
	
	@Test
	public void testaBuscarNomeDoJogador() {
		assertEquals("Jogador 1", gerenciador.buscarNomeJogador(1L));
	}
	
	@Test(expected = JogadorNaoEncontradoException.class)
	public void testaBuscarNomeDoJogadorIdInexistente() {
		assertEquals("Jogador 1", gerenciador.buscarNomeJogador(2L));
	}
	
	@Test
	public void testaBuscarNomeDotime() {
		assertEquals("Time 1", gerenciador.buscarNomeTime(1L));
	}
	
	@Test(expected = TimeNaoEncontradoException.class)
	public void testaBuscarNomeDotimeIdInexistente() {
		assertEquals("Time 1", gerenciador.buscarNomeTime(2L));
	}
	
	@Test
	public void testaBuscarJogadoresDoTime() {
		gerenciador.incluirJogador(10L, 1L, "Jogador 2", LocalDate.of(1989, 7, 8), 75, new BigDecimal("1800.00"));
		gerenciador.incluirJogador(5L, 1L, "Jogador 3", LocalDate.of(1994, 6, 21), 90, new BigDecimal("3000.00"));
		
		assertEquals(3, gerenciador.buscarJogadoresDoTime(1L).size());
		assertEquals(1L, gerenciador.buscarJogadoresDoTime(1L).get(0).longValue());
		assertEquals(5L, gerenciador.buscarJogadoresDoTime(1L).get(1).longValue());
		assertEquals(10L, gerenciador.buscarJogadoresDoTime(1L).get(2).longValue());
	}
	
	@Test(expected = TimeNaoEncontradoException.class)
	public void testaBuscarJogadoresDoTimeIdInexistente() {		
		assertEquals(3, gerenciador.buscarJogadoresDoTime(2L).size());
	}
	
	@Test
	public void testaBuscarMelhorJogadorDotime() {
		gerenciador.incluirJogador(10L, 1L, "Jogador 2", LocalDate.of(1989, 7, 8), 75, new BigDecimal("1800.00"));
		gerenciador.incluirJogador(5L, 1L, "Jogador 3", LocalDate.of(1994, 6, 21), 90, new BigDecimal("3000.00"));
		gerenciador.incluirJogador(7L, 1L, "Jogador 4", LocalDate.of(1990, 8, 27), 63, new BigDecimal("1300.00"));
		
		assertEquals(5L, gerenciador.buscarMelhorJogadorDoTime(1L).longValue());
	}
	
	@Test
	public void testaBuscarMelhorJogadorDotimeEmpatado() {
		gerenciador.incluirJogador(7L, 1L, "Jogador 2", LocalDate.of(1989, 7, 8), 90, new BigDecimal("1800.00"));
		gerenciador.incluirJogador(5L, 1L, "Jogador 3", LocalDate.of(1994, 6, 21), 90, new BigDecimal("3000.00"));
		gerenciador.incluirJogador(3L, 1L, "Jogador 4", LocalDate.of(1990, 8, 27), 75, new BigDecimal("1300.00"));
		
		assertEquals(5L, gerenciador.buscarMelhorJogadorDoTime(1L).longValue());
	}
	
	@Test(expected = TimeNaoEncontradoException.class)
	public void testaBuscarMelhorJogadorIdInexistente() {
		
		assertEquals(5L, gerenciador.buscarMelhorJogadorDoTime(2L).longValue());
	}
	
	@Test
	public void testaBuscarJogadorMaisVelhoDotime() {
		gerenciador.incluirJogador(10L, 1L, "Jogador 2", LocalDate.of(1989, 7, 8), 75, new BigDecimal("1800.00"));
		gerenciador.incluirJogador(5L, 1L, "Jogador 3", LocalDate.of(1994, 6, 21), 90, new BigDecimal("3000.00"));
		gerenciador.incluirJogador(7L, 1L, "Jogador 4", LocalDate.of(1974, 8, 27), 63, new BigDecimal("1300.00"));
		
		assertEquals(7L, gerenciador.buscarJogadorMaisVelho(1L).longValue());
	}
	
	@Test
	public void testaBuscarJogadorMaisVelhoDotimeEmpatado() {
		gerenciador.incluirJogador(10L, 1L, "Jogador 2", LocalDate.of(1989, 7, 8), 75, new BigDecimal("1800.00"));
		gerenciador.incluirJogador(5L, 1L, "Jogador 3", LocalDate.of(1974, 8, 27), 90, new BigDecimal("3000.00"));
		gerenciador.incluirJogador(7L, 1L, "Jogador 4", LocalDate.of(1974, 8, 27), 63, new BigDecimal("1300.00"));
		
		assertEquals(5L, gerenciador.buscarJogadorMaisVelho(1L).longValue());
	}
	
	@Test(expected = TimeNaoEncontradoException.class)
	public void testaBuscarJogadorMaisVelhoDotimeIdInexistente() {
		assertEquals(7L, gerenciador.buscarJogadorMaisVelho(2L).longValue());
	}
	
}
