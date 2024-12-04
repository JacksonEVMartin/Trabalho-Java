create table notas (
    id            int not null primary key auto_increment,
    matricula_id  int,
    disciplina_id int,
    constraint Fk_notas_matricula_id
        FOREIGN KEY (matricula_id) references matriculas (id),
    constraint Fk_notas_disciplina_id
        FOREIGN KEY (disciplina_id) references disciplinas (id)
);