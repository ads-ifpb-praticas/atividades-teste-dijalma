package br.com.dijalmasilva.controller;

import br.com.dijalmasilva.exception.AluguelException;
import br.com.dijalmasilva.exception.FilmeException;
import br.com.dijalmasilva.model.Aluguel;
import br.com.dijalmasilva.service.AluguelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 28/03/17 - 08:48
 */
@RestController
@RequestMapping("/alugueis")
public class AluguelController {

    @Autowired
    private AluguelService service;

    @PostMapping(consumes = "application/json")
    public ResponseEntity alugar(Aluguel aluguel) {

        try {
            Aluguel save = service.save(aluguel);
            return new ResponseEntity(save, HttpStatus.OK);
        } catch (FilmeException | AluguelException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public List<Aluguel> listarFilmes() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity buscarPorId(@RequestParam Long id) {
        Aluguel aluguel = service.findById(id);
        if (aluguel == null) {
            return new ResponseEntity(id, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(aluguel, HttpStatus.OK);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity atualizarFilme(@RequestParam Long id, Aluguel aluguel) {
        Aluguel update = service.update(id, aluguel);
        if (update == null) {
            return new ResponseEntity(id, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(update, HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity devolverFilme(@RequestParam Long id) {
        try {
            boolean b = service.remove(id);
            return new ResponseEntity(id, HttpStatus.OK);
        } catch (FilmeException | AluguelException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
