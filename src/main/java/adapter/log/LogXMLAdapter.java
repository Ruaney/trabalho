/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapter.log;

import java.io.IOException;
import model.LogXML;
import model.Operacao;

/**
 *
 * @author ruaney
 */
public class LogXMLAdapter implements ILogAdapter {
    private LogXML log;
    
    public LogXMLAdapter(LogXML log) {
        this.log = log;
    }

    @Override
    public void registrarLog(Operacao operation) throws IOException {
        log.log(operation);
    }

}
