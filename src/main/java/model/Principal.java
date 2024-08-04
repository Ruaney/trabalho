package model;

import clima.collection.ClimaCollection;
import observer.painel.PainelClimaObserver;
import observer.painel.EstatisticaClimaObserver;
import observer.painel.MaximasMinimasObserver;
import observer.painel.EstacaoClimatica;
import java.time.LocalDate;
import observer.painel.RegistroLabelObserver;
import observer.painel.RegistrosTabelaObserver;
import presenter.DadosClimaticosPresenter;
import services.LogService;
import view.ViewDadosClima;

public class Principal {

    public static void main(String[] args) {
        EstacaoClimatica estacaoClimatica = new EstacaoClimatica();
        Painel painelClimaObserver = new PainelClimaObserver();
        Painel estatisticaClimaObserver = new EstatisticaClimaObserver();
        Painel maximasMinimasObserver = new MaximasMinimasObserver();
        Painel registroLabelObserver = new RegistroLabelObserver();
        Painel registrosTabelaObserver = new RegistrosTabelaObserver();
        
        Configuracao config = new Configuracao();
        config.setFormato("xml");
        LogService logService = new LogService(config);
        
        estacaoClimatica.registrarPainel(painelClimaObserver);
        estacaoClimatica.registrarPainel(estatisticaClimaObserver);
        estacaoClimatica.registrarPainel(maximasMinimasObserver);
        estacaoClimatica.registrarPainel(registroLabelObserver);
        estacaoClimatica.registrarPainel(registrosTabelaObserver);
        
        ClimaCollection climas = new ClimaCollection();
        DadosClimaticosPresenter presenter = new DadosClimaticosPresenter(estacaoClimatica, logService,climas);

    }
}
