package com.example.zzpj_plugin_v2;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyPluginAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        try {
            String apiUrl = "https://meowfacts.herokuapp.com/";
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }
                reader.close();

                String catFact = response.toString();

                catFact = catFact.substring(10);
                catFact = catFact.substring(0, catFact.length() - 3);

//                System.out.println("Cat Info: " + catFact);
                Messages.showInfoMessage(catFact, "Cat Info");
            } else {
                Messages.showInfoMessage("Something went wrong, sorry!", "Cat Info");
            }
        } catch (IOException ee) {
            ee.printStackTrace();
        }
    }

}