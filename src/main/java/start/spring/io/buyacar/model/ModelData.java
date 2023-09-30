package start.spring.io.buyacar.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record ModelData(List<VehicleData> modelos) {
}
