package br.com.trabalho.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.trabalho.model.Cliente;



public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNomeContaining(String nome);
    Optional<Cliente> findByNome(String nome);
    List<Cliente> findAll();

}

