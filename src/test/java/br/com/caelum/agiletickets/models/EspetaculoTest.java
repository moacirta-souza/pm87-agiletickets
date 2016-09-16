package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	@Test 
	public void deveCriarUmaUnicaSessaoSeoInicioForIgualAoFim() {
		Espetaculo ws = new Espetaculo();
		LocalDate inicio = new LocalDate(2016, 9, 15);
		LocalDate fim = inicio;
		LocalTime horario = new LocalTime(23, 0);
		List<Sessao> sessoes = ws.criaSessoes(inicio, fim, horario,
				Periodicidade.DIARIA);
		Assert.assertNotNull(sessoes);
		Assert.assertEquals(1, sessoes.size());
		Sessao sessao = new Sessao();
		Assert.assertEquals("15/09/16", sessao.getDia());
		Assert.assertEquals("23:00", sessao.getHora());
	}
	
	@Test
	public void deveCriarSessoesDiariasDadoUmIntervalo() {
		Espetaculo ws = new Espetaculo();
		LocalDate inicio = new LocalDate(2016, 9, 15);
		LocalDate fim =   new LocalDate(2016, 9, 20);
		LocalTime horario = new LocalTime(23, 0);
		List<Sessao> sessoes = ws.criaSessoes(inicio, fim, horario, Periodicidade.DIARIA);
		
		
		Assert.assertNotNull(sessoes);
		Assert.assertEquals(5, sessoes.size());
		
	}
	
	@Test
	public void deveCriarSessoesSemanaisDadoUmIntervalo() {
		Espetaculo ws = new Espetaculo();
		LocalDate inicio = new LocalDate(2011, 1, 19);
		LocalDate fim =   new LocalDate(2011, 2, 19);
		LocalTime horario = new LocalTime(17, 0);
		List<Sessao> sessoes = ws.criaSessoes(inicio, fim, horario, Periodicidade.SEMANAL);
		
		Assert.assertNotNull(sessoes);
		Assert.assertEquals(5, sessoes.size());
		Assert.assertEquals("19/01/11", sessoes.get(0).getDia());
		Assert.assertEquals("26/01/11", sessoes.get(1).getDia());
		Assert.assertEquals("02/02/11", sessoes.get(2).getDia());
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}

}
