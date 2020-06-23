package com.voting.system.src.impl.integration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Integration {

    public static String consumerCPF(String cpf) {
        String retorno = null;
        try {
            URL url = new URL(
                    "https://user-info.herokuapp.com/users/" + cpf);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            con.setDoOutput(true);

            try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {

                StringBuilder response = new StringBuilder();
                String responseLine = null;

                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                    retorno = response.toString();
                    retorno = retorno.substring(11, 12);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorno;
    }

}
