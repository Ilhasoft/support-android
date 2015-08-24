package br.com.ilhasoft.support.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by johndalton on 09/05/14.
 */
public class JsonDeserializer <Model> {

    private static final String DATA_MEMBER = "data";
    private static final String RESULT_MEMBER = "result";

    private Gson gson;
    private final JsonObject jsonObject;

    public JsonDeserializer(String json) {
        gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT)
                                .create();
        jsonObject = getRootJsonObject(json);
    }

    public void setDateFormat(String style) {
        gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT)
                .setDateFormat(style)
                .create();
    }

    public Model get(Class<Model> classParam) {
        return gson.fromJson(jsonObject, classParam);
    }

    public Model getData(Class<Model> classParam) {
        JsonElement dataElement = jsonObject.get(DATA_MEMBER);
        return gson.fromJson(dataElement, classParam);
    }

    public List<Model> getDataList(Type type) {
        JsonElement dataElement = jsonObject.get(DATA_MEMBER);
        return gson.fromJson(dataElement, type);
    }

    public <T> Type getType(T type) {
        return new TypeToken<T>(){}.getType();
    }

    public Result getResult() {
        JsonElement jsonElement = jsonObject.get(RESULT_MEMBER);
        return gson.fromJson(jsonElement, Result.class);
    }

    private JsonObject getRootJsonObject(String response) {
        JsonObject objectResponse;

        JsonParser jsonParser = new JsonParser();
        objectResponse = (JsonObject) jsonParser.parse(response);
        return objectResponse;
    }

}
