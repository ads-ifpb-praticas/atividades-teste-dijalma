package br.com.dijalmasilva.service;

import br.com.dijalmasilva.enums.EstadoEnum;
import br.com.dijalmasilva.exception.FilmeException;
import br.com.dijalmasilva.model.Filme;
import br.com.dijalmasilva.repository.FilmeRepository;
import br.com.dijalmasilva.validation.FilmeValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 28/03/17 - 08:17
 */
@Service
public class FilmeService {

    @Autowired
    private FilmeRepository dao;
    @Autowired
    private FilmeValidation validation;

    public Filme save(Filme filme) throws FilmeException {
        filme.setEstado(EstadoEnum.DISPONIVEL);
        validation.validateMovieSave(filme);
        return dao.save(filme);
    }

    public Filme update(Long id, Filme filme) throws FilmeException {
        Filme finded = dao.findOne(id);
        validation.validaEdicaoDoFilme(finded);
        finded = filme;
        return dao.save(finded);
    }

    public Filme findById(Long id) {
        return dao.findOne(id);
    }

    public List<Filme> listAll() {
        return (List<Filme>) dao.findAll();
    }

    public boolean remove(Long id) {
        dao.delete(id);
        return true;
    }

}
