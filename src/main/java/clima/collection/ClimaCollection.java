/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clima.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.DadoClima;

/**
 *
 * @author ruaney
 */
public class ClimaCollection {

    private ArrayList<DadoClima> climas;

    public ClimaCollection() {
        climas = new ArrayList<>();
    }

    public void add(DadoClima clima) {
        if (climas.contains(clima)) {
            throw new RuntimeException("contato ja existe");
        }
        if (clima != null) {
            climas.add(clima);
        } else {
            throw new RuntimeException("Forneca uma instancia valida de um contat");
        }
    }

    public void removerPorId(String id) {
        boolean removed = climas.removeIf(clima -> clima.getId().equals(id));
        if (!removed) {
            throw new RuntimeException("Clima com o ID fornecido não encontrado");
        }
    }

    public List<DadoClima> getClimas() {
        return Collections.unmodifiableList(climas);
    }
}
