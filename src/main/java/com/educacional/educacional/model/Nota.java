package com.educacional.educacional.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "notas")
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private BigDecimal nota;

    @Column
    private LocalDate data_lancamento;

    @ManyToOne
    @JoinColumn(name = "matricula_id", referencedColumnName = "id")
    private Matricula matricula_id;

    @ManyToOne
    @JoinColumn(name = "disciplina_id", referencedColumnName = "id")
    private Disciplina disciplina_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getNota() {
        return nota;
    }

    public void setNota(BigDecimal nota) {
        this.nota = nota;
    }

    public LocalDate getData_lancamento() {
        return data_lancamento;
    }

    public void setData_lancamento(LocalDate data_lancamento) {
        this.data_lancamento = data_lancamento;
    }

    public Matricula getMatricula_id() {
        return matricula_id;
    }

    public void setMatricula_id(Matricula matricula_id) {
        this.matricula_id = matricula_id;
    }

    public Disciplina getDisciplina_id() {
        return disciplina_id;
    }

    public void setDisciplina_id(Disciplina disciplina_id) {
        this.disciplina_id = disciplina_id;
    }
}
