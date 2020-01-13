package com.example.carros.api;

import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.carros.model.Carro;
import com.example.carros.service.CarroService;


@RestController
@RequestMapping("/api/vl/carros")
public class CarrosController {
	
	@Autowired
	private CarroService service;
	
	@GetMapping()
	public ResponseEntity<Iterable<Carro>> get() {
		return ResponseEntity.ok(service.getCarros());
		//return  new ResponseEntity<>(service.getCarros(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public Optional<Carro> get(@PathVariable("id") Long id) {
		return service.getCarroById(id);
	}
	
	@GetMapping("/tipo/{tipo}")
	public Optional<Carro> getCarrosByTipo(@PathVariable("tipo") String tipo) {
		return service.getCarroByTipo(tipo);
	}
	
	@PostMapping
	public String post (@RequestBody Carro carro){
		
		Carro c = service.insert(carro);
		
		return "Carro salvo com sucesso: " + c.getId();
	}
	
	@PutMapping("/{id}")
	public String put (@PathVariable ("id") Long id, @RequestBody Carro carro){
		
		Carro c = service.update(carro, id);
		
		return "Carro atualizado com sucesso: " + c.getId();
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") Long id) {
		
		service.delete(id);
		
		return "Carro deletado com sucesso";
	}
}