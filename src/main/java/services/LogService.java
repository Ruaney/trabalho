/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

/**
 *
 * @author ruaney
 */
import adapter.log.LogAdapter;
import model.Operacao;
public class LogService {
    private LogAdapter logAdapter;

    public LogService(LogAdapter logAdapter) {
       
        this.logAdapter = logAdapter;
    }

    public void addData() {
      
        logAdapter.registrarLog(Operacao.INSERCAO);
    }

    public void removeData() {
     
        logAdapter.registrarLog(Operacao.REMOCAO);
    }
}
