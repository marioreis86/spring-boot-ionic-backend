package com.mariopimenta.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.mariopimenta.cursomc.domain.Categoria;
import com.mariopimenta.cursomc.enums.Validacoes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class CategoriaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = Validacoes.REQUIRED)
	@Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;
	
	public CategoriaDTO(Categoria obj) {
		id = obj.getId();
		nome = obj.getNome();
	}
}
