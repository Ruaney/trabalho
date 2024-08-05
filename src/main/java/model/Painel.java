package model;

import clima.collection.ClimaCollection;
import view.ViewDadosClima;

public interface Painel {

   void atualizar(ClimaCollection climas, ViewDadosClima view);
}

