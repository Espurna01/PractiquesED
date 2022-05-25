package main.java;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

public class GsonTest {

    public static void main(String[] args) throws IOException {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();


        //Prueba p = new Prueba(null, 21);

        JsonReader jr = new JsonReader(new FileReader("icaen.json"));

        JsonArray ja = JsonParser.parseReader(new FileReader("icaen.json")).getAsJsonArray();

        GrafPR grafpr = new GrafPR();

    }
}
