/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package observer;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import model.DadoClima;
import model.Painel;
import view.ViewDadosClima;

/**
 *
 * @author ruaney
 */
public class RegistrosTabelaObserver implements Painel {

    private List<DadoClima> dadosClima;

    public RegistrosTabelaObserver() {
         dadosClima = new ArrayList<>();
    }

    @Override
    public void atualizar(DadoClima dadoClima, ViewDadosClima view) {
        dadosClima.add(dadoClima);
        attTabela(view);
    }

    private void attTabela(ViewDadosClima view) {
        DefaultTableModel tmDadosClimaticos = new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Data", "Temperatura", "Umidade", "Pressão"}
        );

        view.getTblRegistros().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListIterator<DadoClima> it = dadosClima.listIterator();

        while (it.hasNext()) {
            DadoClima clima = it.next();
            String temperatura = String.valueOf(clima.getTemperatura());
            String data = String.valueOf(clima.getData());
            String umidade = String.valueOf(clima.getUmidade());
            String pressao = String.valueOf(clima.getPressao());
            tmDadosClimaticos.addRow(new Object[]{"0", data, temperatura, umidade, pressao});
        }
        view.getTblRegistros().setModel(tmDadosClimaticos);
    }

}
