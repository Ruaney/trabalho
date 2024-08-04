/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package observer.painel;

import clima.collection.ClimaCollection;
import java.util.ArrayList;
import java.util.List;
import model.DadoClima;
import model.Painel;
import view.ViewDadosClima;

/**
 *
 * @author ruaney
 */
public class RegistroLabelObserver implements Painel {

    public RegistroLabelObserver() {
    }

    @Override
    public void atualizar(ClimaCollection climas, ViewDadosClima view) {
        String total = String.valueOf(climas.getClimas().size());
        view.getLblNumeroRegistros().setText(total);
    }

}
