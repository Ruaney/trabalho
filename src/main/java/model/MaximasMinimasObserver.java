package model;

import java.util.ArrayList;
import java.util.List;

public class MaximasMinimasObserver implements Painel {

   private List<DadoClima> dadosClima;

   public MaximasMinimasObserver() {
       dadosClima = new ArrayList<>();
   }
   @Override
   public void atualizar(DadoClima dadoClima) {
       dadosClima.add(dadoClima);
       exibirMaximasMinimas();
   }
 private void exibirMaximasMinimas() {
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
