package com.viatorfortis.guideme.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.viatorfortis.guideme.model.Region;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public final class JsonUtils {
    public static List<Region> parseRegionListJson(String jsonArray)
            throws JsonSyntaxException {
        Type listType = new TypeToken<ArrayList<Region>>(){}.getType();

        return new Gson().fromJson(jsonArray, listType);

    }
}
