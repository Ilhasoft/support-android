package br.com.ilhasoft.support.json;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Created by johndalton on 09/05/14.
 */
public class JsonParser<Model> {

    private final Gson gson;
    private final JsonObject jsonObject;

    public JsonParser(String json) {
        gson = new Gson();
        jsonObject = getRootJsonObject(json);
    }

    public Model getObject(Class<Model> classParam) {
        return gson.fromJson(jsonObject, classParam);
    }

    private JsonObject getRootJsonObject(String response) {
        JsonObject objectResponse;

        com.google.gson.JsonParser jsonParser = new com.google.gson.JsonParser();
        objectResponse = (JsonObject) jsonParser.parse(response);
        return objectResponse;
    }

}
