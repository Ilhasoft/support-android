package br.com.ilhasoft.ilhaandroid.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.lang.reflect.Modifier;

/**
 * Created by johndalton on 09/05/14.
 */
public class JsonSerializer {

    public static final String DATA_MEMBER = "data";
    private String datePattern;

    public String getDatePattern() {
        return datePattern;
    }

    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }

    public <Model> String serializeObject(Model object) throws Exception {
        Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT)
                                     .excludeFieldsWithoutExposeAnnotation()
                                     .setDateFormat(datePattern)
                                     .create();

        return getJsonSerialized(object, gson);
    }

    private <Model> String getJsonSerialized(Model object, Gson gson) {
        JsonElement jsonElementTree = gson.toJsonTree(object);

        JsonObject jsonObject = new JsonObject();
        jsonObject.add(DATA_MEMBER, jsonElementTree);

        return jsonObject.toString();
    }

}
