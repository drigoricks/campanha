package br.com.programa.campanha.mock;

import br.com.programa.campanha.entity.TorcedorCampanha;

public class TorcedorCampanhaRequestMock {
	public static TorcedorCampanha getTorcedorCampanhaRequest(){
		TorcedorCampanha torcedorcampanhaRequest = new TorcedorCampanha();
		torcedorcampanhaRequest.setEmail("teste@gmail.com");
		torcedorcampanhaRequest.setNomeTime("Santo Andre");
		torcedorcampanhaRequest.setIdCampanha(1L);
		return torcedorcampanhaRequest;
	}
}
