package com.educacional.educacional.Controller;

import com.educacional.educacional.dto.MatriculaRequestDTO;
import com.educacional.educacional.model.Aluno;
import com.educacional.educacional.model.Matricula;
import com.educacional.educacional.model.Turma;
import com.educacional.educacional.repository.AlunoRepository;
import com.educacional.educacional.repository.MatriculaRepository;
import com.educacional.educacional.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matricula")
public class MatriculaController {
    @Autowired
    private MatriculaRepository repository;
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private TurmaRepository turmaRepository;

    @GetMapping
    public List<Matricula> findAll() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Matricula findById(@PathVariable Integer id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não foi possivel encontrado a matricula"));
    }

    @PostMapping
    public Matricula save(@RequestBody MatriculaRequestDTO dto) {
        Matricula matricula = new Matricula();

        Aluno aluno = this.alunoRepository.findById(dto.aluno_id())
                .orElseThrow(() -> new IllegalArgumentException("Não foi possivel encontrado o aluno"));

        Turma turma = this.turmaRepository.findById(dto.turma_id())
                .orElseThrow(() -> new IllegalArgumentException("Não foi possivel encontrado a turma"));

        matricula.setAluno_id(aluno);
        matricula.setTurma_id(turma);

        return this.repository.save(matricula);
    }

    @PutMapping("/{id}")
    public Matricula update(@PathVariable Integer id, @RequestBody MatriculaRequestDTO dto) {
        Matricula matricula = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não foi possivel encontrado a matricula"));

        Aluno aluno = this.alunoRepository.findById(dto.aluno_id())
                .orElseThrow(() -> new IllegalArgumentException("Não foi possivel encontrado o aluno"));

        Turma turma = this.turmaRepository.findById(dto.turma_id())
                .orElseThrow(() -> new IllegalArgumentException("Não foi possivel encontrado a turma"));

        matricula.setAluno_id(aluno);
        matricula.setTurma_id(turma);

        return this.repository.save(matricula);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        Matricula matricula = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não foi possivel encontrado a matricula"));

        this.repository.delete(matricula);
    }


}
