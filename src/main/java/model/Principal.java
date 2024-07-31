package model;

import observer.PainelClimaObserver;
import observer.EstatisticaClimaObserver;
import observer.MaximasMinimasObserver;
import observer.EstacaoClimatica;
import java.time.LocalDate;
import observer.RegistroLabelObserver;
import observer.RegistrosTabelaObserver;
import presenter.DadosClimaticosPresenter;
import view.ViewDadosClima;

public class Principal {

    public static void main(String[] args) {
        EstacaoClimatica estacaoClimatica = new EstacaoClimatica();
        Painel painelClimaObserver = new PainelClimaObserver();
        Painel estatisticaClimaObserver = new EstatisticaClimaObserver();
        Painel maximasMinimasObserver = new MaximasMinimasObserver();
        Painel registroLabelObserver = new RegistroLabelObserver();
        Painel registrosTabelaObserver = new RegistrosTabelaObserver();
        
        estacaoClimatica.registrarPainel(painelClimaObserver);
        estacaoClimatica.registrarPainel(estatisticaClimaObserver);
        estacaoClimatica.registrarPainel(maximasMinimasObserver);
        estacaoClimatica.registrarPainel(registroLabelObserver);
        estacaoClimatica.registrarPainel(registrosTabelaObserver);
        
        DadosClimaticosPresenter presenter = new DadosClimaticosPresenter(estacaoClimatica);

    }
}
