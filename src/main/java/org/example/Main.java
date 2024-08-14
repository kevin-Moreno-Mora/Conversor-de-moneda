package org.example;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Scanner;

class CambioDemoneda {

    private final String apiKey = "API_Key";
    private final OkHttpClient client = new OkHttpClient();

    public double getTasaCambio(String monedaBase, String aConvertir) throws IOException {
        String url = String.format("https://api.exchangerate-api.com/v4/latest/%s", monedaBase);

        Request request = new Request.Builder()
                .url(url)
                .addHeader("apikey", apiKey)
                .build();

        try (Response respuesta = client.newCall(request).execute()) {
            if (!respuesta.isSuccessful()) {
                throw new IOException("Código de error " + respuesta);
            }
            String responseBody = respuesta.body().string();
            return parseExchangeRate(responseBody, aConvertir);
        }
    }

    private double parseExchangeRate(String json, String toCurrency) {
        JSONObject jsonObject = new JSONObject(json);
        return jsonObject.getJSONObject("rates").getDouble(toCurrency);
    }

    public static void main(String[] args) {
        int opcion = 0;
        String menu = """
                *****************************************
                ** Escriba el número de la opción deseada **
                1 - Cambio de Dólares a Pesos Argentinos
                2 - Cambio de Pesos Argentinos a Dólares
                3 - Cambio de Dólares a Real Brasileño
                4 - Cambio de Real Brasileño a Dólares
                5 - Cambio de Dólares a Pesos Colombianos
                6 - Cambio de Colombianos a Dólares
                9 - Salir
                *****************************************
                """;
        Scanner teclado = new Scanner(System.in);
        CambioDemoneda servicio = new CambioDemoneda();

        while (opcion != 9) {
            System.out.println(menu);
            opcion = teclado.nextInt();

            if (opcion == 1) {
                System.out.print("Ingrese la cantidad a convertir: ");
                double cantidad = teclado.nextDouble();
                try {
                    double tasaCambio = servicio.getTasaCambio("USD", "ARS");
                    double resultado = cantidad * tasaCambio;
                    System.out.printf("Resultado: %.2f ARS%n", resultado);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (opcion == 2) {
                System.out.print("Ingrese la cantidad a convertir: ");
                double cantidad = teclado.nextDouble();
                try {
                    double tasaCambio = servicio.getTasaCambio("ARS", "USD");
                    double resultado = cantidad * tasaCambio;
                    System.out.printf("Resultado: %.2f USD%n", resultado);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (opcion == 3) {
                System.out.print("Ingrese la cantidad a convertir: ");
                double cantidad = teclado.nextDouble();
                try {
                    double tasaCambio = servicio.getTasaCambio("USD", "BRL");
                    double resultado = cantidad * tasaCambio;
                    System.out.printf("Resultado: %.2f BRL%n", resultado);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (opcion == 4) {
                System.out.print("Ingrese la cantidad a convertir: ");
                double cantidad = teclado.nextDouble();
                try {
                    double tasaCambio = servicio.getTasaCambio("BRL", "USD");
                    double resultado = cantidad * tasaCambio;
                    System.out.printf("Resultado: %.2f USD%n", resultado);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (opcion == 5) {
                System.out.print("Ingrese la cantidad a convertir: ");
                double cantidad = teclado.nextDouble();
                try {
                    double tasaCambio = servicio.getTasaCambio("USD", "COP");
                    double resultado = cantidad * tasaCambio;
                    System.out.printf("Resultado: %.2f COP%n", resultado);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (opcion == 6) {
                System.out.print("Ingrese la cantidad a convertir: ");
                double cantidad = teclado.nextDouble();
                try {
                    double tasaCambio = servicio.getTasaCambio("COP", "USD");
                    double resultado = cantidad * tasaCambio;
                    System.out.printf("Resultado: %.2f USD%n", resultado);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (opcion != 9) {
                System.out.println("Opción inválida");
            }
        }
        teclado.close();
    }
}