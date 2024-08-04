/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import model.IRegistraLog;
import model.Operacao;

/**
 *
 * @author ruaney
 */
public class LogJSON implements IRegistraLog {

    public LogJSON() {

    }

    @Override
    public void log(Operacao operation) throws IOException {
        String log = String.format("{ \"timestamp\": \"%s\", \"operation\": \"%s\"}",
                LocalDateTime.now().toString(), operation.toString());
        saveToFile(log);
    }

    private void saveToFile(String log) throws IOException {
        try (FileWriter writer = new FileWriter("log.json", true)) {
            writer.write(log + System.lineSeparator());
        } catch (IOException e) {
           throw new IOException("erro ao salvar arquivo");
        }
    }
}
