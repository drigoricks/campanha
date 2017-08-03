package br.com.programa.campanha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.programa.campanha.entity.Campanha;

@Repository
public interface CampanhaRepository extends JpaRepository<Campanha, Long>{
	List<Campanha> findByIdTimeCoracao(Long codigoTime);
}
