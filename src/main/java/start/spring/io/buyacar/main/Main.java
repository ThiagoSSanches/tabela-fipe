package start.spring.io.buyacar.main;

import start.spring.io.buyacar.model.*;
import start.spring.io.buyacar.service.ConsumingApi;
import start.spring.io.buyacar.service.ConvertingData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private Scanner reader = new Scanner(System.in);
    private ConsumingApi consuming = new ConsumingApi();
    private ConvertingData converting = new ConvertingData();
    private final String URL = "https://parallelum.com.br/fipe/api/v1/";


    public void displayingMenu() {
        var menu = """                
                **** OPÇÕES ****
                Carro
                Moto
                Caminhão
                                
                Digite uma das opções para consultar valores:
                                
                """;
        System.out.println(menu);
//        List<ModelData> modelData = new ArrayList<>();


        var nameOfVehicle = reader.nextLine();
        String newAddress;

        if (nameOfVehicle.toLowerCase().contains("carr")) {
            newAddress = URL + "carros/marcas";
        } else if (nameOfVehicle.toLowerCase().contains("mot")) {
            newAddress = URL + "motos/marcas";
        } else {
            newAddress = URL + "caminhoes/marcas";
        }

        var json = consuming.getData(newAddress);

        var brands = converting.getList(json, VehicleData.class);
        brands.stream()
                        .sorted(Comparator.comparing(VehicleData::codigo))
                                .forEach(System.out::println);


        System.out.println("Informe o código da marca para consulta: ");

        var brandCode = reader.nextLine();
        newAddress += "/" + brandCode + "/modelos";
        json = consuming.getData(newAddress);

        var models = converting.getData(json, ModelData.class);

        System.out.println("\n Modelos da marca: ");

        models.modelos().stream()
                .sorted(Comparator.comparing(VehicleData::codigo))
                        .forEach(System.out::println);

//        System.out.println(models);
//        modelData.add(models);
//
//        List<VehicleData> brandData = modelData.stream()
//                .flatMap(t -> t.modelos().stream())
//                .collect(Collectors.toList());
//
//        brandData.forEach(System.out::println);

        System.out.println("Digite o trecho de um veiculo");
        var trechoDoVeiculo = reader.nextLine();

        List<VehicleData> modelos = models.modelos().stream()
                .filter(e -> e.nome().toUpperCase().contains(trechoDoVeiculo.toUpperCase()))
                        .sorted(Comparator.comparing(VehicleData::codigo))
                                .collect(Collectors.toList());

        System.out.println("\nModelos filtrados: ");
        modelos.forEach(System.out::println);


//        models.modelos().stream()
//                .filter(e ->e.nome().toUpperCase().contains(trechoDoVeiculo.toUpperCase()))
//                .sorted(Comparator.comparing(VehicleData::codigo))
//                        .forEach(System.out::println);

//        List<YearData> modelos = modelData.stream()
//                .flatMap(t -> t.modelos().stream()
//                        .map(d -> new YearData(d))
//                ).collect(Collectors.toList());
//
//        modelos.stream()
//                .filter(e -> e.getNome().toUpperCase().contains(trechoDoVeiculo.toUpperCase()))
//                .forEach(System.out::println);

        System.out.println("Digite o código do veiculo desejado para obter avaliação");
        var codigoParaAvaliacao = reader.nextLine();
        newAddress += "/" + codigoParaAvaliacao + "/anos";
        json = consuming.getData(newAddress);


        List<VehicleData> anos = converting.getList(json, VehicleData.class);
        List<FinalData> veiculos = new ArrayList<>();

        for (int i = 0; i < anos.size(); i++) {
            var enderecoAnos = newAddress + "/" + anos.get(i).codigo();
            json = consuming.getData(enderecoAnos);
            FinalData veiculo = converting.getData(json, FinalData.class);
            veiculos.add(veiculo);
        }

        System.out.println("\nTodos os veículos filtrados com avaliações por ano: ");
        veiculos.forEach(System.out::println);


//        var dados = converting.getList(json, VehicleData.class);


//        List<String> dadosParaIterar = dados.stream()
//                .map(e -> e.codigo())
//                .collect(Collectors.toList());
//        System.out.println(dadosParaIterar);

//        var testAdress = newAddress;


//        for (int i = 0; i < dadosParaIterar.size(); i++) {
//
//            var str = dadosParaIterar.get(i);
//            testAdress += "/" + str;
//            json = consuming.getData(testAdress);
//            FinalData avaliaFinal = converting.getData(json, FinalData.class);
//            System.out.println(avaliaFinal);
//            testAdress = newAddress;
//
//        }

    }

}
