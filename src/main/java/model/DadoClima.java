package model;

import java.time.LocalDate;
import java.util.UUID;

public class DadoClima {

    private static int idCounter = 0;
    private String id;
    private float temperatura;
    private float umidade;
    private float pressao;
    private LocalDate data;

    public DadoClima(float temperatura, float umidade, float pressao, LocalDate data) {
        this.id = String.valueOf(++idCounter);
        this.temperatura = temperatura;
        this.umidade = umidade;
        this.pressao = pressao;
        this.data = data;
    }

    public float getPressao() {
        return pressao;
    }

    public float getUmidade() {
        return umidade;
    }

    public float getTemperatura() {
        return temperatura;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DadoClima dadoClima = (DadoClima) obj;
        return id.equals(dadoClima.id);
    }

    public String getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    // Improved getData method
    public LocalDate getData() {
        return data;
    }
}
