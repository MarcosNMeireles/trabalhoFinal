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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.trabalho.model.Agendamento;
import br.com.trabalho.service.AgendamentoService;
import br.com.trabalho.service.ClienteService;
import br.com.trabalho.service.MedicoService;
import br.com.trabalho.service.ProcedimentoService;
import lombok.RequiredArgsConstructor;

// Define que a classe é um controlador Spring
@Controller
// Adiciona construtor obrigatório para as dependências
@RequiredArgsConstructor
public class AgendamentoController {

    // Injeção das dependências de serviço
    private final AgendamentoService agendamentoService;
    private final ClienteService clienteService;
    private final MedicoService medicoService;
    private final ProcedimentoService procedimentoService;
    
    // Construtor com injeção das dependências
    @Autowired 
    public AgendamentoController(AgendamentoService agendamentoService, ClienteService clienteService,
                                 MedicoService medicoService, ProcedimentoService procedimentoService) {
        this.agendamentoService = agendamentoService;
        this.clienteService = clienteService;
        this.medicoService = medicoService;
        this.procedimentoService = procedimentoService;
    }
    
    // Mapeamento para exibir o formulário de cadastro de agendamento
    @GetMapping("/cadastrarAgendamento")
    public String cadastrarAgendamentoForm(Model model) {
        model.addAttribute("agendamento", new Agendamento());
        model.addAttribute("clientes", clienteService.getAllClientes());
        model.addAttribute("medicos", medicoService.getAllMedicos());
        model.addAttribute("procedimentos", procedimentoService.getAllProcedimentos());
        return "cadastrarAgendamento";
    }

    // Mapeamento para salvar um novo agendamento
    @PostMapping("/salvarAgendamento")
    public String salvarAgendamento(@ModelAttribute("agendamento") @Valid Agendamento agendamento, BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.agendamento", result);
            return "redirect:/cadastrarAgendamento";
        }

        agendamentoService.salvarAgendamento(agendamento);
        return "redirect:/consultarAgendamento";
    }

	// Mapeamento para consultar todos os agendamentos
	@GetMapping("/consultarAgendamento")
	public String consultarAgendamentos(Model model) {
		model.addAttribute("agendamentos", agendamentoService.listarTodosAgendamentos());
		return "consultarAgendamento";
	}

	// Mapeamento para exibir o formulário de edição de um agendamento
	
	@GetMapping("/editarAgendamento/{id}")
	public String editarAgendamentoForm(@PathVariable Long id, Model model) {
	    Agendamento agendamento = agendamentoService.buscarAgendamentoPorId(id);
	    model.addAttribute("agendamento", agendamento);
	    model.addAttribute("clientes", clienteService.getAllClientes());
	    model.addAttribute("medicos", medicoService.getAllMedicos());
	    model.addAttribute("procedimentos", procedimentoService.getAllProcedimentos());
	    return "editarAgendamento";
	}
	
	/*
	 * @GetMapping("/editarAgendamento/{id}") public String
	 * editarAgendamentoForm(@PathVariable Long id, Model model) { Agendamento
	 * agendamento = agendamentoService.buscarAgendamentoPorId(id);
	 * model.addAttribute("agendamento", agendamento); return "editarAgendamento"; }
	 */

	// Mapeamento para salvar as alterações de um agendamento editado
	@PostMapping("/editarAgendamento/{id}")
	public String editarAgendamento(@PathVariable Long id,
	        @ModelAttribute("agendamento") @Valid Agendamento agendamento, BindingResult result,
	        RedirectAttributes redirectAttributes) {
	    if (result.hasErrors()) {
	        redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.agendamento", result);
	        return "editarAgendamento/" + id;
	    }

	    Agendamento existingAgendamento = agendamentoService.buscarAgendamentoPorId(id);
	    existingAgendamento.setNome(agendamento.getNome()); 
	    existingAgendamento.setCliente(agendamento.getCliente());
	    existingAgendamento.setMedico(agendamento.getMedico());
	    existingAgendamento.setProcedimento(agendamento.getProcedimento());
	    existingAgendamento.setData(agendamento.getData());
	    existingAgendamento.setHora(agendamento.getHora());

	    agendamentoService.salvarAgendamento(existingAgendamento);
	    return "redirect:/consultarAgendamento";
	}

	// Mapeamento para excluir um agendamento
	@GetMapping("/excluirAgendamento/{id}")
	public String excluirAgendamento(@PathVariable Long id) {
		agendamentoService.excluirAgendamento(id);
		return "redirect:/consultarAgendamento";
	}

	// Método que popula o modelo com dados comuns para várias páginas
	@ModelAttribute
	public void populateModel(Model model) {
		model.addAttribute("clientes", clienteService.getAllClientes());
		model.addAttribute("medicos", medicoService.getAllMedicos());
		model.addAttribute("procedimentos", procedimentoService.getAllProcedimentos());
	}
}
