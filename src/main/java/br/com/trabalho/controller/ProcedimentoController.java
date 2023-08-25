package br.com.trabalho.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.trabalho.model.Procedimento;
import br.com.trabalho.repository.ProcedimentoRepository;

@Controller
public class ProcedimentoController {

    @Autowired
    private ProcedimentoRepository procedimentoRepository;

    @GetMapping("/cadastrarProcedimento")
    public String mostrarFormulario(Procedimento procedimento) {
        return "cadastrarProcedimento";
    }

    @PostMapping("/salvarProcedimento")
    public String salvarProcedimento(@Valid Procedimento procedimento, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "cadastrarProcedimento";
        }

        procedimentoRepository.save(procedimento);
        attributes.addFlashAttribute("mensagem", "Procedimento cadastrado com sucesso.");
        return "redirect:/consultarProcedimento";
    }

    @GetMapping("/consultarProcedimento")
    public String consultarProcedimentos(Model model) {
        List<Procedimento> procedimentos = procedimentoRepository.findAll();
        model.addAttribute("procedimentos", procedimentos);
        return "consultarProcedimento";
    }

    @GetMapping("/editarProcedimento/{id}")
    public String mostrarFormularioEdicao(@PathVariable Long id, Model model) {
        Procedimento procedimento = procedimentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Procedimento inválido ID: " + id));

        model.addAttribute("procedimento", procedimento);
        return "editarProcedimento";
    }

    @PostMapping("/atualizarProcedimento/{id}")
    public String atualizarProcedimento(@PathVariable Long id, @Valid Procedimento procedimento,
                                        BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            procedimento.setId(id);
            return "editarProcedimento";
        }

        procedimentoRepository.save(procedimento);
        attributes.addFlashAttribute("mensagem", "Procedimento atualizado com sucesso.");
        return "redirect:/consultarProcedimento";
    }

    @GetMapping("/excluirProcedimento/{id}")
    public String excluirProcedimento(@PathVariable Long id, RedirectAttributes attributes) {
        procedimentoRepository.deleteById(id);
        attributes.addFlashAttribute("mensagem", "Procedimento excluído com sucesso.");
        return "redirect:/consultarProcedimento";
    }
}
