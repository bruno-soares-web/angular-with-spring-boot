package com.bruno.helpdesk.domains;

import com.bruno.helpdesk.domains.enuns.Prioridade;
import com.bruno.helpdesk.domains.enuns.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.swing.tree.TreeCellEditor;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Chamado {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonFormat(pattern = "dd/mm/yyyy")
    private LocalDate dataAbertura = LocalDate.now();
    @JsonFormat(pattern = "dd/mm/yyyy")
    private LocalDate dataFechamento;

    private Prioridade prioridade;
    private Status status;
    private String titulo;
    private String observacao;
    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Chamado() {
        super();
    }

    public Chamado(Integer id, Prioridade prioridade, Status status, String titulo, String observacao, Tecnico tecnico, Cliente cliente) {
        super();
        this.id = id;
        this.prioridade = prioridade;
        this.status = status;
        this.titulo = titulo;
        this.observacao = observacao;
        this.tecnico = tecnico;
        this.cliente = cliente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDate getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chamado chamado = (Chamado) o;
        return Objects.equals(getId(), chamado.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
