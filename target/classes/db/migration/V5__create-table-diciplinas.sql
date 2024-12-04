create table disciplinas (
    id           int not null primary key auto_increment,
    nome         varchar(100),
    codigo       varchar(20),
    curso_id     int,
    professor_id int,
    constraint Fk_disciplinas_curso_id
        FOREIGN KEY (curso_id) references cursos (id),
    constraint Fk_disciplinas_professor_id
        FOREIGN KEY (professor_id) references professores (id)
);