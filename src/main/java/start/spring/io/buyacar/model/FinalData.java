package start.spring.io.buyacar.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record FinalData(
        @JsonAlias("Marca")
        String marca,
        @JsonAlias("Modelo")
        String modelo,
        @JsonAlias("AnoModelo")
        Integer anoModelo,
        @JsonAlias("Combustivel")
        String combustivel,
        @JsonAlias("Valor")
        String valor) {
}
