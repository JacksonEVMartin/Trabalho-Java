create table matriculas (
    id       int not null primary key auto_increment,
    aluno_id int,
    turma_id int,
    constraint Fk_matriculas_aluno_id
        FOREIGN KEY (aluno_id) references alunos (id),
    constraint Fk_matriculas_turma_id
        FOREIGN KEY (turma_id) references turmas (id)
);