package observer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.DadoClima;
import model.Painel;
import view.ViewDadosClima;

public class EstacaoClimatica {

    private List<Painel> paineis;

    public EstacaoClimatica() {
        paineis = new ArrayList<>();
    }

    public void registrarPainel(Painel painel) {
        paineis.add(painel);
    }

    public void removerPainel(Painel painel) {
        paineis.remove(painel);
    }

    public void atualizar(DadoClima clima, ViewDadosClima view) {
        for (Painel painel : paineis) {
            painel.atualizar(clima, view);
        }
    }

}
