package com.educacional.educacional.Controller;

import com.educacional.educacional.dto.NotaRequestDTO;
import com.educacional.educacional.model.Disciplina;
import com.educacional.educacional.model.Matricula;
import com.educacional.educacional.model.Nota;
import com.educacional.educacional.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notas")
public class NotaController {
    @Autowired
    private NotaRepository repository;
    @Autowired
    private MatriculaRepository matriculaRepository;
    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @GetMapping
    public ResponseEntity<List<Nota>> findAll() {
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nota> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não foi possivel encontrado a Nota")));
    }

    @PostMapping
    public ResponseEntity<Nota> save(@RequestBody NotaRequestDTO dto) {
        Nota nota = new Nota();

        Matricula matricula = this.matriculaRepository.findById(dto.matricula_id())
                .orElseThrow(() -> new IllegalArgumentException("Não foi possivel encontrado a matricula"));

        Disciplina disciplina = this.disciplinaRepository.findById(dto.matricula_id())
                .orElseThrow(() -> new IllegalArgumentException("Não foi possivel encontrado a Disciplina"));

        nota.setNota(dto.nota());
        nota.setMatricula_id(matricula);
        nota.setDisciplina_id(disciplina);
        nota.setData_lancamento(dto.data_lancamento());

        return ResponseEntity.ok(this.repository.save(nota));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nota> update(@PathVariable Integer id, @RequestBody NotaRequestDTO dto) {
        Nota nota = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não foi possivel encontrado a nota"));

        Matricula matricula = this.matriculaRepository.findById(dto.matricula_id())
                .orElseThrow(() -> new IllegalArgumentException("Não foi possivel encontrado a matricula"));

        Disciplina disciplina = this.disciplinaRepository.findById(dto.matricula_id())
                .orElseThrow(() -> new IllegalArgumentException("Não foi possivel encontrado a Disciplina"));

        nota.setNota(dto.nota());
        nota.setMatricula_id(matricula);
        nota.setDisciplina_id(disciplina);
        nota.setData_lancamento(dto.data_lancamento());

        return ResponseEntity.ok(this.repository.save(nota));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Nota nota = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não foi possivel encontrado a nota"));

        this.repository.delete(nota);
        return ResponseEntity.noContent().build();
    }
}
