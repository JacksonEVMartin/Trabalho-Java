package com.educacional.educacional.Controller;

import com.educacional.educacional.dto.TurmaRequestDTO;
import com.educacional.educacional.model.Curso;
import com.educacional.educacional.model.Turma;
import com.educacional.educacional.repository.CursoRepository;
import com.educacional.educacional.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turmas")
public class TurmaController {
    @Autowired
    private TurmaRepository repository;
    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<Turma> findAll() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Turma findById(@PathVariable Integer id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não foi possivel encontrado a Turma"));
    }

    @PostMapping
    public Turma save(@RequestBody TurmaRequestDTO dto) {
        Turma turma = new Turma();

        Curso curso = this.cursoRepository.findById(dto.curso_id())
                .orElseThrow(() -> new IllegalArgumentException("Não foi possivel encontrado o curso"));

        turma.setAno(dto.ano());
        turma.setSemestre(dto.semestre());
        turma.setCurso_id(curso);

        return this.repository.save(turma);
    }

    @PutMapping("/{id}")
    public Turma update(@PathVariable Integer id, @RequestBody TurmaRequestDTO dto) {
        Turma turma = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não foi possivel encontrado o turma"));

        Curso curso = this.cursoRepository.findById(dto.curso_id())
                .orElseThrow(() -> new IllegalArgumentException("Não foi possivel encontrado o curso"));

        turma.setAno(dto.ano());
        turma.setSemestre(dto.semestre());
        turma.setCurso_id(curso);


        return this.repository.save(turma);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        Turma turma = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não foi possivel encontrado o turma"));

        this.repository.delete(turma);
    }
}
