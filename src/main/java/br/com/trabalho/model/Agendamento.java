package br.com.trabalho.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

// Define que a classe Agendamento é uma entidade mapeada para o banco de dados
@Entity
// Define o nome da tabela no banco de dados
@Table(name = "agendamentos")
public class Agendamento {
    
    // Define o ID como chave primária, com geração automática de valores
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Estabelece uma relação muitos-para-um com a entidade Cliente, através da coluna cliente_id
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    // Estabelece uma relação muitos-para-um com a entidade Medico, através da coluna medico_id
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    // Estabelece uma relação muitos-para-um com a entidade Procedimento, através da coluna procedimento_id
    @ManyToOne
    @JoinColumn(name = "procedimento_id")
    private Procedimento procedimento;

    // Define os campos de data e hora como strings
    private String data;
    private String hora;

    // Define o campo para armazenar o nome
    private String nome;

    // Métodos de acesso e modificação para o ID
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Métodos de acesso e modificação para a entidade Cliente
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    // Métodos de acesso e modificação para a entidade Medico
    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    // Métodos de acesso e modificação para a entidade Procedimento
    public Procedimento getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(Procedimento procedimento) {
        this.procedimento = procedimento;
    }

    // Métodos de acesso e modificação para o campo de data
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    // Métodos de acesso e modificação para o campo de hora
    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    // Métodos de acesso e modificação para o campo de nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
