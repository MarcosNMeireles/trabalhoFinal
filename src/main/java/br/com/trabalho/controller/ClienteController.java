
package br.com.trabalho.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.trabalho.model.Cliente;
import br.com.trabalho.repository.ClienteRepository;

@Controller
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/")
    public String paginaPrincipal() {
        return "index";
    }

    @GetMapping("/cadastrar-cliente")
    public String paginaCadastrarCliente(Model model) {
        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);
        return "cadastrar_Cliente";
    }

    @PostMapping("/cadastrar-cliente")
    public String cadastrarCliente(@ModelAttribute @Valid Cliente cliente, BindingResult result) {
        if (result.hasErrors()) {
            return "cadastrar_Cliente";
        }
        clienteRepository.save(cliente);
        return "redirect:/consultarCliente";
    }

    @GetMapping("/consultarCliente")
    public String consultarClientes(Model model) {
        model.addAttribute("clientes", clienteRepository.findAll());
        return "consultarCliente";
    }
    
    @GetMapping("/editarCliente/{id}")
    public String editarCliente(@PathVariable Long id, Model model) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Identificador do cliente inválido: " + id));
        
        model.addAttribute("cliente", cliente);
        return "editarCliente";
    }
    
    @PostMapping("/atualizar/{id}")
    public String atualizarCliente(@PathVariable Long id, @ModelAttribute @Valid Cliente clienteAtualizado, BindingResult result) {
        if (result.hasErrors()) {
            
            return "editarCliente";
        }

        
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Identificador do cliente inválido: " + id));

        clienteExistente.setNome(clienteAtualizado.getNome());
        clienteExistente.setDataNascimento(clienteAtualizado.getDataNascimento());
        clienteExistente.setEndereco(clienteAtualizado.getEndereco());
        clienteExistente.setCep(clienteAtualizado.getCep());
        clienteExistente.setEmail(clienteAtualizado.getEmail());
        clienteExistente.setTelefone(clienteAtualizado.getTelefone());

        clienteRepository.save(clienteExistente);

        return "redirect:/consultarCliente"; 
    }

    @GetMapping("/delete/{id}")
    public String deletarCliente(@PathVariable("id") long id, Model model) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Identificador do cliente inválido: " + id));
        clienteRepository.delete(cliente);
        return "redirect:/consultarCliente"; 
    }

    @PostMapping("/salvar-cliente")
    public String salvarCliente(@ModelAttribute @Valid Cliente cliente, BindingResult result) {
        if (result.hasErrors()) {
            return "cadastrarCliente";
        }

        clienteRepository.save(cliente);
        return "redirect:/consultarCliente";
    }
}
