package com.educacional.educacional.Controller;

import com.educacional.educacional.dto.DisciplinaRequestDTO;
import com.educacional.educacional.model.Curso;
import com.educacional.educacional.model.Disciplina;
import com.educacional.educacional.model.Professor;
import com.educacional.educacional.repository.CursoRepository;
import com.educacional.educacional.repository.DisciplinaRepository;
import com.educacional.educacional.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {
    @Autowired
    private DisciplinaRepository repository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private ProfessorRepository professorRepository;

    // Buscando todos os alunos
    @GetMapping
    public List<Disciplina> findAll() {
        return this.repository.findAll();
    }

    // Buscando os alunos por id
    @GetMapping("/{id}")
    public Disciplina findById(@PathVariable Integer id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não foi possivel encontrado a disciplina"));
    }

    // criar um aluno
    @PostMapping
    public Disciplina save(@RequestBody DisciplinaRequestDTO dto) {
        Disciplina disciplina = new Disciplina();

        Curso curso = this.cursoRepository.findById(dto.curso_id())
                .orElseThrow(() -> new IllegalArgumentException("Não foi possivel encontrado o curso"));

        Professor professor = this.professorRepository.findById(dto.professor_id())
                .orElseThrow(() -> new IllegalArgumentException("Não foi possivel encontrado o professor"));


        disciplina.setNome(dto.nome());
        disciplina.setCodigo(dto.codigo());
        disciplina.setCurso(curso);
        disciplina.setProfessor(professor);

        return this.repository.save(disciplina);
    }

    // atualizar um aluno
    @PutMapping("/{id}")
    public Disciplina update(@PathVariable Integer id, @RequestBody DisciplinaRequestDTO dto) {
        Disciplina disciplina = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não foi possivel encontrado o disciplina"));

        Curso curso = this.cursoRepository.findById(dto.curso_id())
                .orElseThrow(() -> new IllegalArgumentException("Não foi possivel encontrado o curso"));

        Professor professor = this.professorRepository.findById(dto.professor_id())
                .orElseThrow(() -> new IllegalArgumentException("Não foi possivel encontrado o professor"));


        disciplina.setNome(dto.nome());
        disciplina.setCodigo(dto.codigo());
        disciplina.setCurso(curso);
        disciplina.setProfessor(professor);

        return this.repository.save(disciplina);
    }

    // deletar um aluno
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        Disciplina disciplina = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não foi possivel encontrado o disciplina"));

        this.repository.delete(disciplina);
    }


}
