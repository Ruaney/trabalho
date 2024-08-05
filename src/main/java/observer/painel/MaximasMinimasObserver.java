package observer.painel;

import clima.collection.ClimaCollection;
import java.util.ArrayList;
import java.util.List;
import model.DadoClima;
import model.Painel;
import view.ViewDadosClima;

public class MaximasMinimasObserver implements Painel {

  

    @Override
    public void atualizar(ClimaCollection climas, ViewDadosClima view) {
        List<DadoClima> dadosClima = climas.getClimas();
        if (dadosClima.isEmpty()) {
            System.out.println("Não há dados para exibir.");
            return;
        }

        float maxTemperatura = dadosClima.get(0).getTemperatura();
        float minTemperatura = maxTemperatura;
        float maxUmidade = dadosClima.get(0).getUmidade();
        float minUmidade = maxUmidade;
        float maxPressao = dadosClima.get(0).getPressao();
        float minPressao = maxPressao;

        for (DadoClima dado : dadosClima) {
            maxTemperatura = Math.max(maxTemperatura, dado.getTemperatura());
            minTemperatura = Math.min(minTemperatura, dado.getTemperatura());
            maxUmidade = Math.max(maxUmidade, dado.getUmidade());
            minUmidade = Math.min(minUmidade, dado.getUmidade());
            maxPressao = Math.max(maxPressao, dado.getPressao());
            minPressao = Math.min(minPressao, dado.getPressao());
        }

    }

}
