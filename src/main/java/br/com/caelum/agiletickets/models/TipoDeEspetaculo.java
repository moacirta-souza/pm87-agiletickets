package br.com.caelum.agiletickets.models;

import java.math.BigDecimal;

public enum TipoDeEspetaculo {
	
	CINEMA, SHOW, TEATRO, BALLET, ORQUESTRA;
	
	
	public static BigDecimal calculaPrecoSessaoCinemaShow(Sessao sessao) {
		BigDecimal preco = sessao.getPreco();
		final Integer totalIngressos = sessao.getTotalIngressos();
		final Integer ingressosReservados = sessao.getIngressosReservados();
		final TipoDeEspetaculo tipo = sessao.getEspetaculo().getTipo();
		if (tipo.equals(TipoDeEspetaculo.CINEMA) || tipo.equals(TipoDeEspetaculo.SHOW)) {
			//quando estiver acabando os ingressos... 
			if((totalIngressos - ingressosReservados) / totalIngressos.doubleValue() <= 0.05) { 
				preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			}
		} else if(tipo.equals(TipoDeEspetaculo.BALLET) || tipo.equals(TipoDeEspetaculo.ORQUESTRA)) {
			if((totalIngressos - ingressosReservados) / totalIngressos.doubleValue() <= 0.50) { 
				preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.20)));
			}
			
			if(sessao.getDuracaoEmMinutos() > 60){
				preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			}
		} 
		return preco;
	}
}
