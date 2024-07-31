/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package observer;

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

    private List<DadoClima> dadosClima;

    public RegistroLabelObserver() {
         dadosClima = new ArrayList<>();
    }

    @Override
    public void atualizar(DadoClima dadoClima, ViewDadosClima view) {
        dadosClima.add(dadoClima);
        attLabel(view);
    }

    private void attLabel(ViewDadosClima view) {
        String total = String.valueOf(dadosClima.size());
        view.getLblNumeroRegistros().setText(total);
    }
}
