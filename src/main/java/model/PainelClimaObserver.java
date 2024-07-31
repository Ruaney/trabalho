
package model;

import java.time.LocalDate;

public class PainelClimaObserver implements Painel {

   @Override
   public void atualizar(DadoClima dadoClima) {
       if (dadoClima.getData().isEqual(LocalDate.now())) {
           System.out.println("Painel Clima - Data: " + dadoClima.getData() 
+ ", Temperatura: " + dadoClima.getTemperatura() 
+ ", Umidade: " + dadoClima.getUmidade() 
+ ", Pressão: " + dadoClima.getPressao());
       }
   }
}

