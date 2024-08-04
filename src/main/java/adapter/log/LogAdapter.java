/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapter.log;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.IRegistraLog;
import model.Operacao;

/**
 *
 * @author ruaney
 */
public class LogAdapter implements ILogAdapter{
    private IRegistraLog registrarLog;

    public LogAdapter(IRegistraLog registrarLog) {
        this.registrarLog = registrarLog;
    }
    
    @Override
    public void registrarLog(Operacao operation) {
        try {
            registrarLog.log(operation);
        } catch (IOException ex) {
            Logger.getLogger(LogAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
} 
