package com.alura.convert_monedas;

import com.alura.convert_monedas.conexion.ExchangerateApi;
import com.alura.convert_monedas.modelos.MonedaOmdb;
import com.alura.convert_monedas.modelos.Monedas;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        double valor;
        double resultado;
        String cantidad;

        while (continuar){

            System.out.println("""
                    **************************************************************
                    
                    Sea bienvenido/a al conversor de moneda (˶ᵔ ᵕ ᵔ˶)
                    
                    1) Dólar =>> Pesos Dominicanos
                    2) Pesos Dominicanos =>> Dólar
                    3) Dólar =>> Peso Argentino
                    4) Pesos Argentinos =>> Dólar
                    5) Dólar =>> Real Brasileño
                    6) Real Brasileño =>> Dólar
                    7) Salir
                    
                    Elija una opción válida:
                    
                    **************************************************************
                    """);
            
            var opcion = scanner.nextLine();

            switch (opcion){
                case "1":
                    System.out.println("Ingresa el valor que desea convertir: ");
                    cantidad = scanner.nextLine();


                    try {
                        valor = Double.parseDouble(cantidad);
                    } catch (NumberFormatException e) {
                        System.out.println("Solo puede colocar números enteros o decimales");
                        break;
                    }


                    resultado = Convercion("USD", "DOP", valor);

                    System.out.println("US$" + cantidad + " Convertido a Pesos Dominicanos son: RD$" + resultado);

                    break;
                case "2":
                    System.out.println("Ingresa el valor que desea convertir: ");
                    cantidad = scanner.nextLine();


                    try {
                        valor = Double.parseDouble(cantidad);
                    } catch (NumberFormatException e) {
                        System.out.println("Solo puede colocar números enteros o decimales");
                        break;
                    }


                    resultado = Convercion("DOP", "USD", valor);

                    System.out.println("RD$" + cantidad + " Convertido a Dólares son: US$" + resultado);

                    break;
                case "3":
                    System.out.println("Ingresa el valor que desea convertir: ");
                    cantidad = scanner.nextLine();


                    try {
                        valor = Double.parseDouble(cantidad);
                    } catch (NumberFormatException e) {
                        System.out.println("Solo puede colocar números enteros o decimales");
                        break;
                    }


                    resultado = Convercion("USD", "ARS", valor);

                    System.out.println("US$" + cantidad + " Convertido a Peso Argentino son: ARS$" + resultado);

                    break;
                case "4":
                    System.out.println("Ingresa el valor que desea convertir: ");
                    cantidad = scanner.nextLine();


                    try {
                        valor = Double.parseDouble(cantidad);
                    } catch (NumberFormatException e) {
                        System.out.println("Solo puede colocar números enteros o decimales");
                        break;
                    }


                    resultado = Convercion("ARS", "USD", valor);

                    System.out.println("ARS$" + cantidad + " Convertido a Dólares son: US$" + resultado);

                    break;
                case "5":
                    System.out.println("Ingresa el valor que desea convertir: ");
                    cantidad = scanner.nextLine();


                    try {
                        valor = Double.parseDouble(cantidad);
                    } catch (NumberFormatException e) {
                        System.out.println("Solo puede colocar números enteros o decimales");
                        break;
                    }


                    resultado = Convercion("USD", "BRL", valor);

                    System.out.println("US$" + cantidad + " Convertido a Real Brasileño son: BRL$" + resultado);

                    break;
                case "6":
                    System.out.println("Ingresa el valor que desea convertir: ");
                    cantidad = scanner.nextLine();


                    try {
                        valor = Double.parseDouble(cantidad);
                    } catch (NumberFormatException e) {
                        System.out.println("Solo puede colocar números enteros o decimales");
                        break;
                    }

                    resultado = Convercion("BRL", "USD", valor);

                    System.out.println("BRL$" + cantidad + " Convertido a Dólares son: US$" + resultado);

                    break;
                case "7":
                    continuar = false;
                    break;
                default:
                    System.out.println("Elija una opción valida");

            }

        }
    }

    private static double Convercion(String codMonedaOrigen, String codMonedaDestino, double valor){
        ExchangerateApi exchangerateApi = new ExchangerateApi();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .create();


        HttpResponse<String> response = exchangerateApi.getRequest(codMonedaOrigen);
        String json = response.body();

        MonedaOmdb monedaOmdb = gson.fromJson(json, MonedaOmdb.class);

        Monedas monedas = new Monedas(monedaOmdb);

        return monedas.getMonedaConvertida(valor, codMonedaDestino);

    }
}
