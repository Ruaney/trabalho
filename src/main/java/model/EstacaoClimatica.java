package model;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

   public void atualizarMedicoes(float temperatura, float umidade, float pressao, LocalDate data) {
       DadoClima dadoClima = new DadoClima(temperatura, umidade, pressao, data);
       notificarPaineis(dadoClima);
   }

   private void notificarPaineis(DadoClima dadoClima) {
       for (Painel painel : paineis) {
           painel.atualizar(dadoClima);
       }
   }
}
