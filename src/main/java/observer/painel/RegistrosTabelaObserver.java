/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package observer.painel;

import clima.collection.ClimaCollection;
import java.time.format.DateTimeFormatter;
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


    @Override
    public void atualizar(ClimaCollection climas, ViewDadosClima view) {
         DefaultTableModel tmDadosClimaticos = new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Data", "Temperatura", "Umidade", "Pressão"}
        );

        view.getTblRegistros().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListIterator<DadoClima> it = climas.getClimas().listIterator();

        while (it.hasNext()) {
            DadoClima clima = it.next();

            String temperatura = String.valueOf(clima.getTemperatura());
            String data = String.valueOf(clima.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            String umidade = String.valueOf(clima.getUmidade());
            String pressao = String.valueOf(clima.getPressao());
            String id = clima.getId();

            tmDadosClimaticos.addRow(new Object[]{id, data, temperatura, umidade, pressao});
        }
        view.getTblRegistros().setModel(tmDadosClimaticos);
    }

}
