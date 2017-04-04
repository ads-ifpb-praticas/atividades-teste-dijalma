package br.com.dijalmasilva.validation;

import br.com.dijalmasilva.enums.EstadoEnum;
import br.com.dijalmasilva.exception.FilmeException;
import br.com.dijalmasilva.model.Filme;
import org.springframework.stereotype.Component;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 04/04/17 - 07:32
 */
@Component
public class FilmeValidation {

    private String especial = ",.;:><+_=-[{]})(*&%$@/|";

    public void validateMovieSave(Filme filme) throws FilmeException {

        String titulo = filme.getTitulo();

        if (titulo.length() > 50) {
            throw new FilmeException("Título do filme não pode conter mais que 50 caracteres!");
        }

        for (int i = 0; i < titulo.length(); i++) {
            String letter = titulo.substring(i, i+1);
            if (especial.contains(letter)) {
                throw new FilmeException("Título do filme não pode conter caracteres especiais, exceto #,!,?");
            }
        }

        if (filme.getGenero().length() == 0) {
            throw new FilmeException("É obrigatório que o filme possua um gênero!");
        }

        if (filme.getDuracao() == 0) {
            throw new FilmeException("O filme deve ter duração acima de 0 minutos!");
        }
    }


    public void validaEdicaoDoFilme(Filme filme) throws FilmeException {

        validateMovieSave(filme);

        if (filme.getEstado().equals(EstadoEnum.EMPRESTADO)) {
            throw new FilmeException("Filme não pode ser editado, pois o mesmo está emprestado!");
        }
    }
}
