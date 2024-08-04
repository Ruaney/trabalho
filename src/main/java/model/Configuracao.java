/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import adapter.log.LogJSONAdapter;

/**
 *
 * @author ruaney
 */
public class Configuracao {

    private LogJSONAdapter logAdapter;

    public Configuracao() {
    }

    public void setFormato(String formato) {
        FormatoArquivo formatoEnum = FormatoArquivo.valueOf(formato.toUpperCase());

        switch (formatoEnum) {
            case XML:
                logAdapter = new LogJSONAdapter(new LogXML());
                break;
            case JSON:
                 logAdapter = new LogJSONAdapter(new LogJSON());
                break;
            default:
                throw new IllegalArgumentException("Formato de arquivo não suportado: " + formato);
        }
    }

    public LogJSONAdapter getLogAdapter() {
        return logAdapter;
    }
}
