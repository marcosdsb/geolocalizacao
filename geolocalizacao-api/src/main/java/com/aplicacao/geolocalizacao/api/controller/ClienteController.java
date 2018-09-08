package com.aplicacao.geolocalizacao.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aplicacao.geolocalizacao.api.mapeador.GeolocalizacaoTemperaturaMapper;
import com.aplicacao.geolocalizacao.api.model.ClienteEntity;
import com.aplicacao.geolocalizacao.api.model.DTO.ClienteEntityDTO;
import com.aplicacao.geolocalizacao.api.repository.ClienteRepository;
import com.aplicacao.geolocalizacao.api.service.ClienteService;

@RestController
@RequestMapping(path = "/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private ClienteService service;
	
	@Autowired
    private HttpServletRequest request;
	
	public Iterable<ClienteEntity> findByAllCliente() {
		return  repository.findAll();
	}
	
	
	@PostMapping
	public ResponseEntity<?> salvarCliente(@RequestBody  ClienteEntity clienteEntity){
		
		GeolocalizacaoTemperaturaMapper temperatura = service.customerProcess(request); 
	
		ClienteEntity clienteSalvo = service.saveCustomer(clienteEntity, temperatura);
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<ClienteEntity> atualizarCliente(@PathVariable Long codigo, @RequestBody ClienteEntity clienteEntity){
		ClienteEntity clienteSalvo = service.upgradeClient(codigo, clienteEntity);
		return ResponseEntity.ok(clienteSalvo);
	}
	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscarPorCodigo(@PathVariable Long codigo){
		ClienteEntityDTO clienteEntityDTO = service.searchCustomerByCode(codigo);
		return clienteEntityDTO!=null ? ResponseEntity.ok(clienteEntityDTO) : ResponseEntity.notFound().build();
	}
	
	@GetMapping
	public List<?> findByAll() {
		List<ClienteEntityDTO> clienteEntityDTO = service.searchCustomerAll();
		return clienteEntityDTO;
	}
	
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT) 
	public void remover(@PathVariable Long codigo) {
		repository.delete(codigo);
	}

}
