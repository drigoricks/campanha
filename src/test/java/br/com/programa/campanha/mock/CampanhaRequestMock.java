package br.com.programa.campanha.mock;

import java.util.Calendar;

import br.com.programa.campanha.entity.Campanha;

public class CampanhaRequestMock {
	public static Campanha getCampanhaRequest(){
		Campanha campanhaRequest = new Campanha();
		campanhaRequest.setNomeCampanha("Campanha de fim de ano");
		campanhaRequest.setIdTimeCoracao(5L);
		campanhaRequest.setDataInicio(Calendar.getInstance());
		campanhaRequest.setDataFim(Calendar.getInstance());
		return campanhaRequest;
	}
}
