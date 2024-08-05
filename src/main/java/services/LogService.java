/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

/**
 *
 * @author ruaney
 */
import adapter.log.Configuracao;
import adapter.log.ILogAdapter;
import java.io.IOException;
import model.Operacao;

public class LogService {
 
    private Configuracao config;

    public LogService(Configuracao config) {
        this.config = config;
    }

    public void addData() throws IOException {
        ILogAdapter log = config.getLogAdapter();
        log.registrarLog(Operacao.INSERCAO);
    }

    public void removeData() throws IOException {
        ILogAdapter log = config.getLogAdapter();
        log.registrarLog(Operacao.REMOCAO);
    }

    public void setFormatoArquivo(String formato) {
        config.setFormato(formato);
    }
}
