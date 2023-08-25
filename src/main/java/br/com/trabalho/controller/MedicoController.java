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


import br.com.trabalho.model.Medico;
import br.com.trabalho.repository.MedicoRepository;

@Controller
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @GetMapping("/medico")
    public String paginaPrincipal() {
        return "/"; 
    }

    @GetMapping("/cadastrar_Medico")
    public String paginaCadastrarMedico(Model model) {
        Medico medico = new Medico();
        model.addAttribute("medico", medico);
        return "cadastrar_Medico";
    }

    @PostMapping("/cadastrar_Medico")
    public String cadastrarMedico(@ModelAttribute @Valid Medico medico, BindingResult result) {
        if (result.hasErrors()) {
            return "cadastrar_Medico";
        }
        medicoRepository.save(medico);
        return "redirect:/consultarMedico"; 
    }


   @GetMapping("/consultarMedico")
   public String listarMedicos(Model model) {
       model.addAttribute("medicos", medicoRepository.findAll());
       return "consultarMedico";
   }

   @GetMapping("/ver-medico/{id}")
   public String verMedico(@PathVariable("id") Long id, Model model) {
       Medico medico = medicoRepository.findById(id)
               .orElseThrow(() -> new IllegalArgumentException("Identificador do m�dico inv�lido: " + id));

       model.addAttribute("medico", medico);
       return "ver_Medico"; 
   }  
    
   @GetMapping("/editar-medico/{id}")
   public String exibirPaginaEditarMedico(@PathVariable Long id, Model model) {
       Medico medico = medicoRepository.findById(id)
               .orElseThrow(() -> new IllegalArgumentException("Identificador do medico invalido: " + id));

       model.addAttribute("medico", medico);
       return "editar_Medico"; 
   }

   @PostMapping("/editar-medico/{id}")
   public String editarMedico(@PathVariable Long id, @ModelAttribute @Valid Medico medicoAtualizado, BindingResult result) {
       if (result.hasErrors()) {
           return "editar_Medico"; 
       }

       medicoAtualizado.setId(id);
       medicoRepository.save(medicoAtualizado);
       return "redirect:/consultarMedico"; 
   }
    

    @GetMapping("/removerMedico/{id}")
    public String removerMedico(@PathVariable("id") long id, Model model) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Identificador do medico invalido" + id));
        medicoRepository.delete(medico);
        return "redirect:/consultarMedico";
    }

    @PostMapping("/salvar-medico")
    public String salvarMedico(@ModelAttribute @Valid Medico medico, BindingResult result) {
        if (result.hasErrors()) {
            return "cadastrar_Medico";
        }
        medicoRepository.save(medico);
        return "redirect:/consultarMedico"; 
    }
    

    
}
