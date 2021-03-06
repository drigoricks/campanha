package br.com.programa.campanha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.programa.campanha.entity.TorcedorCampanha;

@Repository
public interface TorcedorCampanhaRepository extends JpaRepository<TorcedorCampanha, Long>{
	List<TorcedorCampanha> findByIdCampanha(Long idCampanha);
	List<TorcedorCampanha> findByEmail(String email);
	TorcedorCampanha findByIdCampanhaAndEmail(Long idCampanha, String email);
}
