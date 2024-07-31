package model;

import java.time.LocalDate;

public class Principal {

   public static void main(String[] args) {
       EstacaoClimatica estacaoClimatica = new EstacaoClimatica();
       Painel painelClimaObserver = new PainelClimaObserver();
       Painel estatisticaClimaObserver = new EstatisticaClimaObserver();
       Painel maximasMinimasObserver = new MaximasMinimasObserver();

       estacaoClimatica.registrarPainel(painelClimaObserver);
       estacaoClimatica.registrarPainel(estatisticaClimaObserver);
       estacaoClimatica.registrarPainel(maximasMinimasObserver);

       estacaoClimatica.atualizarMedicoes(25.5f, 65f, 1013.1f, LocalDate.of(2023, 5, 1));
       estacaoClimatica.atualizarMedicoes(26.0f, 68f, 1012.5f, LocalDate.of(2023, 5, 2));
       estacaoClimatica.atualizarMedicoes(24.0f, 60f, 1015.5f, LocalDate.of(2023, 5, 3));
   }
}

