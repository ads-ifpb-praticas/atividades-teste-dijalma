package br.com.dijalmasilva.validation;

import br.com.dijalmasilva.exception.AluguelException;
import br.com.dijalmasilva.model.Aluguel;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 04/04/17 - 07:55
 */
@Component
public class AluguelValidation {


    public void validaEmprestimo(Aluguel aluguel) throws AluguelException {

        LocalDate hoje = LocalDate.now();

        if (aluguel.getDataDoAluguel().isBefore(hoje)) {
            throw new AluguelException("O empréstimo do filme não pode ocorrer em datas retroativas");
        }
    }

    public void validaDevolucao(Aluguel aluguel) throws AluguelException {

        if (aluguel.getDataDeDevolucao().isBefore(aluguel.getDataDoAluguel())) {
            throw new AluguelException("A devolução não pode ser em data anterior a locação");
        }
    }
}
