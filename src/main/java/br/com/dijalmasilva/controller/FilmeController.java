package br.com.dijalmasilva.controller;

import br.com.dijalmasilva.exception.FilmeException;
import br.com.dijalmasilva.model.Filme;
import br.com.dijalmasilva.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 28/03/17 - 08:31
 */
@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeService service;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody Filme filme) {

        try {
            Filme save = service.save(filme);
            return new ResponseEntity(save, HttpStatus.OK);
        } catch (FilmeException e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public List<Filme> listarFilmes() {
        return service.listAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity buscarPorId(@RequestParam Long id) {
        Filme filme = service.findById(id);
        if (filme == null) {
            return new ResponseEntity(id, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(filme, HttpStatus.OK);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity atualizarFilme(@RequestParam Long id, @RequestBody Filme filme) {

        try {
            Filme update = service.update(id, filme);
            return new ResponseEntity(update, HttpStatus.OK);
        } catch (FilmeException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deletarFilme(@RequestParam Long id) {
        boolean b = service.remove(id);
        if (b) {
            return new ResponseEntity(id, HttpStatus.OK);
        } else {
            return new ResponseEntity(id, HttpStatus.NOT_FOUND);
        }
    }
}
