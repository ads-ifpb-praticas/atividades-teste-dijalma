package br.com.dijalmasilva.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 28/03/17 - 08:04
 */
@Entity
public class Aluguel implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDate dataDoAluguel;
    private LocalDate dataDeDevolucao;
    @OneToOne
    private Filme filme;

    public Aluguel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataDoAluguel() {
        return dataDoAluguel;
    }

    public void setDataDoAluguel(LocalDate dataDoAluguel) {
        this.dataDoAluguel = dataDoAluguel;
    }

    public LocalDate getDataDeDevolucao() {
        return dataDeDevolucao;
    }

    public void setDataDeDevolucao(LocalDate dataDeDevolucao) {
        this.dataDeDevolucao = dataDeDevolucao;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    @Override
    public String toString() {
        return "Aluguel{" +
                "id=" + id +
                ", dataDoAluguel=" + dataDoAluguel +
                ", dataDeDevolucao=" + dataDeDevolucao +
                ", filme=" + filme +
                '}';
    }
}
