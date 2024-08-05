/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adapter.log;

import java.io.IOException;
import model.Operacao;

/**
 *
 * @author ruaney
 */
public interface ILogAdapter {

    public void registrarLog(Operacao operation) throws IOException;
}
