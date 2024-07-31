package model;

import java.util.ArrayList;
import java.util.List;


public class EstatisticaClimaObserver implements Painel {

   private List<DadoClima> dadosClima;

   public EstatisticaClimaObserver() {
       dadosClima = new ArrayList<>();
   }

   @Override
   public void atualizar(DadoClima dadoClima) {
       dadosClima.add(dadoClima);
       exibirMedia();
   }

   private void exibirMedia() {
       float somaTemperaturas = 0;
       float somaUmidades = 0;
       float somaPressoes = 0;

       for (DadoClima dadoClima : dadosClima) {
           somaTemperaturas += dadoClima.getTemperatura();
           somaUmidades += dadoClima.getUmidade();
           somaPressoes += dadoClima.getPressao();
       }

       int tamanho = dadosClima.size();
   }
}
