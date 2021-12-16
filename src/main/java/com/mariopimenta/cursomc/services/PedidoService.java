package com.mariopimenta.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mariopimenta.cursomc.domain.Pedido;
import com.mariopimenta.cursomc.repositories.PedidoRepository;
import com.mariopimenta.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> 
			new ObjectNotFoundException(String.format("Objeto não encontrado! Id: %s, Tipo: %s", id, Pedido.class.getName()))
	 	);
	}
}
