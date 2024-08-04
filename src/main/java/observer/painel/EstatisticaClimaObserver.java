package observer.painel;

import clima.collection.ClimaCollection;
import java.util.ArrayList;
import java.util.List;
import model.DadoClima;
import model.Painel;
import view.ViewDadosClima;

public class EstatisticaClimaObserver implements Painel {

    @Override
    public void atualizar(ClimaCollection climas, ViewDadosClima view) {
        float somaTemperaturas = 0;
        float somaUmidades = 0;
        float somaPressoes = 0;
        
        List<DadoClima> dadosClima = climas.getClimas();
        
        for (DadoClima dadoClima : dadosClima) {
            somaTemperaturas += dadoClima.getTemperatura();
            somaUmidades += dadoClima.getUmidade();
            somaPressoes += dadoClima.getPressao();
        }

        int tamanho = dadosClima.size();
        view.getLblPressaoMedia().setText(String.valueOf(somaPressoes / tamanho));
        view.getLblTemperaturaMedia().setText(String.valueOf(somaTemperaturas / tamanho));
        view.getLblUmidadeMedia().setText(String.valueOf(somaUmidades / tamanho));
        view.getLblRegistrosMedia().setText(String.valueOf(tamanho));
    }

}
