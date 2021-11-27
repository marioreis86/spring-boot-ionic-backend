package com.mariopimenta.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mariopimenta.cursomc.domain.Cliente;
import com.mariopimenta.cursomc.repositories.ClienteRepository;
import com.mariopimenta.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> 
			new ObjectNotFoundException(String.format("Objeto não encontrado! Id: %s, Tipo: %s", id, Cliente.class.getName()))
	 	);
	}
}
