package com.mariopimenta.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.mariopimenta.cursomc.domain.Categoria;
import com.mariopimenta.cursomc.repositories.CategoriaRepository;
import com.mariopimenta.cursomc.services.exceptions.DataIntegrityException;
import com.mariopimenta.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> 
			new ObjectNotFoundException(String.format("Objeto não encontrado! Id: %s, Tipo: %s", id, Categoria.class.getName()))
	 	);
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null); 
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException ex) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos"); 
		} 
	}
}
