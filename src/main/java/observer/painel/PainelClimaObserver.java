package observer.painel;

import clima.collection.ClimaCollection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import model.DadoClima;
import model.Painel;
import view.ViewDadosClima;

public class PainelClimaObserver implements Painel {

    private List<Painel> paineis;

    public PainelClimaObserver() {

    }

    @Override
    public void atualizar(ClimaCollection climas, ViewDadosClima view) {
        DadoClima dadoClima = climas.getClimas().get(climas.getClimas().size()-1);
        if (dadoClima.getData().isEqual(LocalDate.now())) {
         
            String data =  String.valueOf(dadoClima.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            String pressao = String.valueOf(dadoClima.getPressao());
            String umidade = String.valueOf(dadoClima.getUmidade());
            String temperatura = String.valueOf(dadoClima.getTemperatura());
            
            view.getLblDataUltimaAtt().setText(data);
            view.getLblPressaoUltimaAtt().setText(pressao);
            view.getLblUmidadeUltimaAtt().setText(umidade);
            view.getLblTemperaturaUltimaAtt().setText(temperatura);
        }
    }
}
