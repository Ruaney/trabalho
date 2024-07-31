/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public DadosClimaticosPresenter(EstacaoClimatica estacaoClimatica, LogService logService) {
        view = new ViewDadosClima();
        view.setVisible(true);

        this.estacaoClimatica = estacaoClimatica;
        this.logService = logService;
        view.getBtnIncluir().addActionListener((ActionEvent e) -> {
            try {
                inserirClima();
            } catch (ParseException ex) {
                Logger.getLogger(DadosClimaticosPresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        view.getBtnRemover().addActionListener((ActionEvent e) -> {
            removerClima();
        });

        view.getBtnDataAtual().addActionListener((ActionEvent e) -> {
            inserirDataAtualNoCampo();
        });
    }

    private void inserirDataAtualNoCampo() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        view.getTxtData().setText(date.format(formatter));
    }

    private void inserirClima() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);

        String dateStr = view.getTxtData().getText();
        Date date = dateFormat.parse(dateStr);
        Float pressao = Float.valueOf(view.getTxtPresao().getText());
        Float temperatura = Float.valueOf(view.getTxtTemperatura().getText());
        Float umidade = Float.valueOf(view.getTxtUmidade().getText());
        DadoClima clima = new DadoClima(temperatura, umidade, pressao, date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        logService.addData();
        
        estacaoClimatica.atualizar(clima, view);
    }

    private void removerClima() {
        logService.removeData();
    }

}
