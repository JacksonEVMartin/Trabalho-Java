package com.educacional.educacional.model;

import jakarta.persistence.*;

@Entity
@Table(name = "matriculas")
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "alunos", referencedColumnName = "id")
    private Aluno aluno_id;

    @ManyToOne
    @JoinColumn(name = "turmas", referencedColumnName = "id")
    private Turma turma_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Aluno getAluno_id() {
        return aluno_id;
    }

    public void setAluno_id(Aluno aluno_id) {
        this.aluno_id = aluno_id;
    }

    public Turma getTurma_id() {
        return turma_id;
    }

    public void setTurma_id(Turma turma_id) {
        this.turma_id = turma_id;
    }
}
