package com.wanderlei.moviecatalog.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Wanderlei Santos on 29/07/2016.
 */
public class JsonDateDeserializer implements JsonDeserializer<Date> {

    public static final SimpleDateFormat sServerDateDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json != null) {
            final String jsonString = json.getAsString();
            try {
                return (Date) sServerDateDateFormat.parse(jsonString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
