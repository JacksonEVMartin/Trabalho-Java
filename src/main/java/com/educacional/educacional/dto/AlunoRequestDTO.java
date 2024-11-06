package com.educacional.educacional.dto;

import java.util.Date;

public record AlunoRequestDTO(String nome, String email, String matricula, Date dataNascimento) {
}
