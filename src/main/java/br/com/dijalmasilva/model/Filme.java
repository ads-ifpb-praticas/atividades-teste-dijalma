package br.com.dijalmasilva.model;

import br.com.dijalmasilva.enums.EstadoEnum;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 28/03/17 - 07:59
 */
@Entity
public class Filme implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String titulo;
    private String genero;
    private long duracao;
    private EstadoEnum estado;

    public Filme() {

    }

    public Filme(String titulo, String genero, long duracao, EstadoEnum estado) {
        this.titulo = titulo;
        this.genero = genero;
        this.duracao = duracao;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public long getDuracao() {
        return duracao;
    }

    public void setDuracao(long duracao) {
        this.duracao = duracao;
    }

    public EstadoEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoEnum estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Filme{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", genero='" + genero + '\'' +
                ", duracao=" + duracao +
                ", estado=" + estado +
                '}';
    }
}
