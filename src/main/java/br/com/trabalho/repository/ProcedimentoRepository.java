package br.com.trabalho.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import br.com.trabalho.model.Procedimento;

public interface ProcedimentoRepository extends JpaRepository<Procedimento, Long> {
    Optional<Procedimento> findByNome(String nome);
}
