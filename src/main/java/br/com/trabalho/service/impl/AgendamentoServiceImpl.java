package br.com.trabalho.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.trabalho.model.Agendamento;
import br.com.trabalho.repository.AgendamentoRepository;
import br.com.trabalho.service.AgendamentoService; 

@Service
public class AgendamentoServiceImpl implements AgendamentoService { 

    // Injeta o AgendamentoRepository usando a anotação @Autowired
    private final AgendamentoRepository agendamentoRepository;

    // Construtor que recebe um AgendamentoRepository injetado automaticamente
    @Autowired
    public AgendamentoServiceImpl(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

    // Método para salvar um agendamento no banco de dados
    @Override
    public void salvarAgendamento(Agendamento agendamento) {
        agendamentoRepository.save(agendamento);
    }

    // Método para listar todos os agendamentos do banco de dados
    @Override
    public List<Agendamento> listarTodosAgendamentos() {
        return agendamentoRepository.findAll();
    }
    
    // Método para editar um agendamento no banco de dados com base no ID
    @Override
    public void editarAgendamento(Long id, Agendamento agendamentoAtualizado) {
        agendamentoRepository.editarAgendamento(id, agendamentoAtualizado.getNome(), agendamentoAtualizado.getData());
    }
    
    // Método para buscar um agendamento por ID no banco de dados
    @Override
    public Agendamento buscarAgendamentoPorId(Long id) {
        return agendamentoRepository.findById(id).orElse(null);
    }

    // Método para buscar um agendamento por nome no banco de dados
    @Override
    public Agendamento buscarAgendamentoPorNome(String nome) {
        return agendamentoRepository.findByNome(nome).orElse(null);
    }

    // Método para excluir um agendamento do banco de dados por ID
    @Override
    public void excluirAgendamento(Long id) {
        agendamentoRepository.deleteById(id);
    }
}
