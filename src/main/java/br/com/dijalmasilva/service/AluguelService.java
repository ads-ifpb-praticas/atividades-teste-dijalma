package br.com.dijalmasilva.service;

import br.com.dijalmasilva.enums.EstadoEnum;
import br.com.dijalmasilva.exception.AluguelException;
import br.com.dijalmasilva.exception.FilmeException;
import br.com.dijalmasilva.model.Aluguel;
import br.com.dijalmasilva.repository.AluguelRepository;
import br.com.dijalmasilva.validation.AluguelValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 28/03/17 - 08:22
 */
@Service
public class AluguelService {

    @Autowired
    private AluguelRepository dao;
    @Autowired
    private FilmeService filmeService;
    @Autowired
    private AluguelValidation validation;

    public Aluguel save(Aluguel aluguel) throws FilmeException, AluguelException {
        validation.validaEmprestimo(aluguel);
        aluguel.getFilme().setEstado(EstadoEnum.EMPRESTADO);
        filmeService.update(aluguel.getFilme().getId(), aluguel.getFilme());
        return dao.save(aluguel);
    }

    public Aluguel update(Long id, Aluguel aluguel) {
        Aluguel finded = dao.findOne(id);
        finded = aluguel;
        return dao.save(finded);
    }

    public Aluguel findById(Long id) {
        return dao.findOne(id);
    }

    public List<Aluguel> findAll() {
        return (List<Aluguel>) dao.findAll();
    }

    public boolean remove(Long id) throws FilmeException, AluguelException {
        Aluguel one = dao.findOne(id);
        validation.validaDevolucao(one);
        one.getFilme().setEstado(EstadoEnum.DISPONIVEL);
        filmeService.update(one.getFilme().getId(), one.getFilme());
        dao.delete(id);
        return true;
    }
}
