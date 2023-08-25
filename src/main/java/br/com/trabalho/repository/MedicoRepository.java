package br.com.trabalho.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.trabalho.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    List<Medico> findByEspecialidade(String especialidade);
    List<Medico> findByNomeContaining(String nome);
    Optional<Medico> findByNome(String nome); 
    List<Medico> findAll();
}
