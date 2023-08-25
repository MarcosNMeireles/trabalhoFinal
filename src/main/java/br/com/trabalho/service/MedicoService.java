package br.com.trabalho.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.trabalho.exception.MedicoNotFoundException;
import br.com.trabalho.model.Medico;
import br.com.trabalho.repository.MedicoRepository;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    @Autowired
    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public List<Medico> getAllMedicos() {
        return medicoRepository.findAll();
    }

    public Medico getMedicoById(Long id) {
        Optional<Medico> medicoOptional = medicoRepository.findById(id);
        if (medicoOptional.isPresent()) {
            return medicoOptional.get();
        } else {
            throw new MedicoNotFoundException("Médico não encontrado com ID: " + id);
        }
    }

    public Medico getMedicoByNome(String nome) {
        return medicoRepository.findByNome(nome)
                .orElseThrow(() -> new MedicoNotFoundException("Médico não encontrado com nome: " + nome));
    }

    public Medico saveMedico(Medico medico) {
        return medicoRepository.save(medico);
    }

    public Medico updateMedico(Long id, Medico medico) {
        Optional<Medico> existingMedicoOptional = medicoRepository.findById(id);
        if (existingMedicoOptional.isPresent()) {
            Medico existingMedico = existingMedicoOptional.get();
            existingMedico.setNome(medico.getNome());
            existingMedico.setCrm(medico.getCrm());
            existingMedico.setEspecialidade(medico.getEspecialidade());
            existingMedico.setEmail(medico.getEmail());
            existingMedico.setTelefone(medico.getTelefone());
            return medicoRepository.save(existingMedico);
        } else {
            throw new MedicoNotFoundException("Médico not found with id: " + id);
        }
    }

    public void deleteMedico(Long id) {
        if (medicoRepository.existsById(id)) {
            medicoRepository.deleteById(id);
        } else {
            throw new MedicoNotFoundException("Médico not found with id: " + id);
        }
    }
}

