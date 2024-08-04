/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import clima.collection.ClimaCollection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.DadoClima;
import observer.painel.EstacaoClimatica;
import services.LogService;
import view.ViewDadosClima;

/**
 *
 * @author ruaney
 */
public class DadosClimaticosPresenter {

    private ViewDadosClima view;
    private EstacaoClimatica estacaoClimatica;
    private LogService logService;
    private ClimaCollection climas;

    public DadosClimaticosPresenter(EstacaoClimatica estacaoClimatica, LogService logService, ClimaCollection climas) {
        view = new ViewDadosClima();
        view.setVisible(true);

        this.estacaoClimatica = estacaoClimatica;
        this.logService = logService;
        this.climas = climas;

        view.getBtnIncluir().addActionListener((ActionEvent e) -> {
            inserirClima();
        });
        view.getBtnRemover().addActionListener((ActionEvent e) -> {
            removerClima();
        });

        view.getBtnDataAtual().addActionListener((ActionEvent e) -> {
            inserirDataAtualNoCampo();
        });
        view.getBtnSalvar().addActionListener((ActionEvent e) -> {
            logService.setFormatoArquivo((String) view.getCbFormatoLog().getSelectedItem());
        });
    }

    private void inserirDataAtualNoCampo() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        view.getTxtData().setText(date.format(formatter));
    }

    private void inserirClima() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);

        String dateStr = view.getTxtData().getText();
        Date date = new Date();
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException ex) {
            showErrorDialog("Erro ao converter data informe no formato DD/MM/YYYY, ex: 10/11/2024");
            return;
        }

        String pres = view.getTxtPresao().getText();
        String temp = view.getTxtTemperatura().getText();
        String umi = view.getTxtUmidade().getText();

        if (pres.equals("") || temp.equals("") || umi.equals("")) {
            showErrorDialog("informe os dados faltantes para inclusão.");
            return;
        }
        Float pressao;
        Float temperatura;
        Float umidade;
        try {
            pressao = Float.valueOf(pres);
            temperatura = Float.valueOf(temp);
            umidade = Float.valueOf(umi);
        } catch (RuntimeException e) {
            showErrorDialog("Informe numeros em temperatura/umidade/pressão");
            return;
        }

        DadoClima clima = new DadoClima(temperatura, umidade, pressao, date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        try {
            climas.add(clima);
            logService.addData();
        } catch (IOException ex) {
            showErrorDialog(ex.getMessage());
            return;
        }

        estacaoClimatica.atualizar(climas, view);
    }

    private void removerClima() {
        try {
            if (view.getTblRegistros().getSelectedRow() == -1) {
                showErrorDialog("selecione um registro para remoção.");
                return;
            }
            String id = (String) ((DefaultTableModel) view.getTblRegistros().getModel()).getValueAt(view.getTblRegistros().getSelectedRow(), 0);

            climas.removerPorId(id);
            logService.removeData();
        } catch (IOException ex) {
            showErrorDialog(ex.getMessage());
            return;
        }

        estacaoClimatica.atualizar(climas, view);
    }

    private static void showErrorDialog(String errorMessage) {
        // Cria uma modal simples com uma mensagem de erro e um botão OK
        JOptionPane.showMessageDialog(null, errorMessage, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
