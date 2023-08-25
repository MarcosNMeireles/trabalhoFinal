package br.com.trabalho.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.trabalho.exception.ProcedimentoNotFoundException;
import br.com.trabalho.model.Procedimento;
import br.com.trabalho.repository.ProcedimentoRepository;

@Service
public class ProcedimentoService {

    private final ProcedimentoRepository procedimentoRepository;

    @Autowired
    public ProcedimentoService(ProcedimentoRepository procedimentoRepository) {
        this.procedimentoRepository = procedimentoRepository;
    }

    public List<Procedimento> getAllProcedimentos() {
        return procedimentoRepository.findAll();
    }

    public Procedimento getProcedimentoById(Long id) {
        return procedimentoRepository.findById(id)
                .orElseThrow(() -> new ProcedimentoNotFoundException("Procedimento not found with id: " + id));
    }
    
    public Procedimento getProcedimentoByNome(String nome) {
        return procedimentoRepository.findByNome(nome)
                .orElseThrow(() -> new ProcedimentoNotFoundException("Procedimento not found with name: " + nome));
    }

    public Procedimento saveProcedimento(Procedimento procedimento) {
        return procedimentoRepository.save(procedimento);
    }

    public Procedimento updateProcedimento(Long id, Procedimento procedimento) {
        Optional<Procedimento> existingProcedimentoOptional = procedimentoRepository.findById(id);
        if (existingProcedimentoOptional.isPresent()) {
            Procedimento existingProcedimento = existingProcedimentoOptional.get();
            existingProcedimento.setNome(procedimento.getNome());
            existingProcedimento.setDescricao(procedimento.getDescricao());
            return procedimentoRepository.save(existingProcedimento);
        } else {
            throw new ProcedimentoNotFoundException("Procedimento not found with id: " + id);
        }
    }

    public void deleteProcedimento(Long id) {
        if (procedimentoRepository.existsById(id)) {
            procedimentoRepository.deleteById(id);
        } else {
            throw new ProcedimentoNotFoundException("Procedimento not found with id: " + id);
        }
    }
}
