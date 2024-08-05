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
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

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

    private void exibirMaximasMinimas(ViewDadosClima view, List<DadoClima> dadosClima) {
        if (dadosClima.isEmpty()) {
            System.out.println("Não há dados para exibir.");
            return;
        }

        // Calculate maximum and minimum values
        float maxTemperatura = dadosClima.get(0).getTemperatura();
        float minTemperatura = maxTemperatura;
        float maxUmidade = dadosClima.get(0).getUmidade();
        float minUmidade = maxUmidade;
        float maxPressao = dadosClima.get(0).getPressao();
        float minPressao = maxPressao;

        for (DadoClima dado : dadosClima) {
            maxTemperatura = Math.max(maxTemperatura, dado.getTemperatura());
            minTemperatura = Math.min(minTemperatura, dado.getTemperatura());
            maxUmidade = Math.max(maxUmidade, dado.getUmidade());
            minUmidade = Math.min(minUmidade, dado.getUmidade());
            maxPressao = Math.max(maxPressao, dado.getPressao());
            minPressao = Math.min(minPressao, dado.getPressao());
        }

        // Create datasets for temperature, humidity, and pressure
        XYSeries serieTemperaturaMax = new XYSeries("Temperatura Máxima");
        XYSeries serieTemperaturaMin = new XYSeries("Temperatura Mínima");
        XYSeries serieUmidadeMax = new XYSeries("Umidade Máxima");
        XYSeries serieUmidadeMin = new XYSeries("Umidade Mínima");
        XYSeries seriePressaoMax = new XYSeries("Pressão Máxima");
        XYSeries seriePressaoMin = new XYSeries("Pressão Mínima");

        // Add data points to the series (assuming each DadoClima has an index)
        for (int i = 0; i < dadosClima.size(); i++) {
            DadoClima dado = dadosClima.get(i);
            serieTemperaturaMax.add(i, dado.getTemperatura());
            serieTemperaturaMin.add(i, dado.getTemperatura());
            serieUmidadeMax.add(i, dado.getUmidade());
            serieUmidadeMin.add(i, dado.getUmidade());
            seriePressaoMax.add(i, dado.getPressao());
            seriePressaoMin.add(i, dado.getPressao());
        }

        // Create collections for each data type (temperature, humidity, pressure)
        XYSeriesCollection temperaturaCollection = new XYSeriesCollection();
        XYSeriesCollection umidadeCollection = new XYSeriesCollection();
        XYSeriesCollection pressaoCollection = new XYSeriesCollection();

        // Add data series to their respective collections
        temperaturaCollection.addSeries(serieTemperaturaMax);
        temperaturaCollection.addSeries(serieTemperaturaMin);
        umidadeCollection.addSeries(serieUmidadeMax);
        umidadeCollection.addSeries(serieUmidadeMin);
        pressaoCollection.addSeries(seriePressaoMax);
        pressaoCollection.addSeries(seriePressaoMin);

        // Create multiple charts (optional, can be combined into one with subplots)
        JFreeChart temperaturaChart = ChartFactory.createXYLineChart(
                "Temperaturas Máximas e Mínimas",
                "Índice",
                "Temperatura",
                temperaturaCollection
        );
        JFreeChart umidadeChart = ChartFactory.createXYLineChart(
                "Umidade Máxima e Mínima",
                "Índice",
                "Umidade",
                umidadeCollection
        );
        JFreeChart pressaoChart = ChartFactory.createXYLineChart(
                "Pressão Máxima e Mínima",
                "Índice",
                "Pressão",
                pressaoCollection
        );
    }

    private static void showErrorDialog(String errorMessage) {
        // Cria uma modal simples com uma mensagem de erro e um botão OK
        JOptionPane.showMessageDialog(null, errorMessage, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
