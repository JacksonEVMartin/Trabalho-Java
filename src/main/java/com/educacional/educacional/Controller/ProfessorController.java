package com.educacional.educacional.Controller;


import com.educacional.educacional.dto.ProfessorRequestDto;
import com.educacional.educacional.model.Professor;
import com.educacional.educacional.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/professores")
public class ProfessorController {
    @Autowired
    private ProfessorRepository repository;

    @GetMapping
    public List<Professor> findAll() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Professor findById(@PathVariable Integer id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não foi possivel achar o professor"));
    }

    @PostMapping
    public Professor save(@RequestBody ProfessorRequestDto dto) {
        Professor professor = new Professor();

        professor.setNome(dto.nome());
        professor.setEmail(dto.email());
        professor.setTelefone(dto.telefone());
        professor.setEspecialidade(dto.especialidade());

        return this.repository.save(professor);
    }

    @PutMapping("/{id}")
    public Professor update(@PathVariable Integer id, @RequestBody ProfessorRequestDto dto) {
        Professor professor = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não foi possivel achar o professor"));

        professor.setNome(dto.nome());
        professor.setEmail(dto.email());
        professor.setTelefone(dto.telefone());
        professor.setEspecialidade(dto.especialidade());

        return this.repository.save(professor);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        Professor professor = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não foi possivel achar o professor"));

        this.repository.delete(professor);
    }
}
