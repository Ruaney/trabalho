/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.IRegistraLog;
import model.Operacao;

/**
 *
 * @author ruaney
 */
public class LogXML implements IRegistraLog {

    public LogXML() {
    }

    @Override
    public void log(Operacao operation) throws IOException {
        String log = String.format("<log><timestamp>%s</timestamp><operation>%s</operation></log>",
                LocalDateTime.now().toString(), operation.toString());
        saveToFile(log);
    }

    private void saveToFile(String log) throws IOException {
        try (FileWriter writer = new FileWriter("logoperacoes.xml", true)) {
            writer.write(log + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Erro ao salvar o log em XML: " + e.getMessage());
        }
    }
}
