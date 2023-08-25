package br.com.trabalho.repository;

import java.sql.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.trabalho.model.Agendamento;

// Define a interface AgendamentoRepository que estende JpaRepository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    
    // Método para buscar um agendamento pelo nome
    Optional<Agendamento> findByNome(String nome);
	
    // Anotação que indica que este método irá modificar os dados no banco
    @Modifying
    // Consulta personalizada para atualizar um agendamento pelo ID
    @Query("UPDATE Agendamento a SET a.nome = ?2, a.data = ?3 WHERE a.id = ?1")
    void editarAgendamento(Long id, String novoNome, String novaData);
}
