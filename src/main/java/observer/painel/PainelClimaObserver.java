package observer.painel;

import java.time.LocalDate;
import java.util.List;
import model.DadoClima;
import model.Painel;
import view.ViewDadosClima;

public class PainelClimaObserver implements Painel {

    private List<Painel> paineis;

    public PainelClimaObserver() {

    }

    @Override
    public void atualizar(DadoClima dadoClima, ViewDadosClima view) {

        if (dadoClima.getData().isEqual(LocalDate.now())) {
         
            String data = String.valueOf(dadoClima.getData());
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
