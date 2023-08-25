package br.com.trabalho.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.trabalho.exception.ClienteNotFoundException;
import br.com.trabalho.model.Cliente;
import br.com.trabalho.repository.ClienteRepository;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente not found with id: " + id));
    }

    public Cliente getClienteByNome(String nome) {
        return clienteRepository.findByNome(nome)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente not found with name: " + nome));
    }

    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente updateCliente(Long id, Cliente cliente) {
        Optional<Cliente> existingClienteOptional = clienteRepository.findById(id);
        if (existingClienteOptional.isPresent()) {
            Cliente existingCliente = existingClienteOptional.get();
            existingCliente.setNome(cliente.getNome());
            existingCliente.setDataNascimento(cliente.getDataNascimento());
            existingCliente.setEndereco(cliente.getEndereco());
            existingCliente.setCep(cliente.getCep());
            existingCliente.setEmail(cliente.getEmail());
            existingCliente.setTelefone(cliente.getTelefone());
            return clienteRepository.save(existingCliente);
        } else {
            throw new ClienteNotFoundException("Cliente not found with id: " + id);
        }
    }

    public void deleteCliente(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
        } else {
            throw new ClienteNotFoundException("Cliente not found with id: " + id);
        }
    }
}
