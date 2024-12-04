package com.educacional.educacional.Controller;

import com.educacional.educacional.dto.AlunoRequestDTO;
import com.educacional.educacional.dto.CursoRequestDTO;
import com.educacional.educacional.dto.ProfessorRequestDto;
import com.educacional.educacional.model.Aluno;
import com.educacional.educacional.model.Curso;
import com.educacional.educacional.model.Professor;
import com.educacional.educacional.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crusos")
public class CursoController {
    @Autowired
    private CursoRepository repository;

    @GetMapping
    public List<Curso> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Curso findById(@PathVariable Integer id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não foi possivel encontrar o curso"));
    }

    @PostMapping
    public Curso save(@RequestBody CursoRequestDTO dto) {
        Curso curso = new Curso();

        curso.setNome(dto.nome());
        curso.setCodigo(dto.codigo());
        curso.setCarga_horaria(dto.carga_horaria());

        return this.repository.save(curso);
    }

    @PutMapping("/{id}")
    public Curso update(@PathVariable Integer id, @RequestBody CursoRequestDTO dto) {
        Curso curso = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não foi possivel encontrado o curso"));

        curso.setNome(dto.nome());
        curso.setCodigo(dto.codigo());
        curso.setCarga_horaria(dto.carga_horaria());

        return this.repository.save(curso);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        Curso curso = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não foi possivel encontrado o curso"));

        this.repository.delete(curso);
    }
}
