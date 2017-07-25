package com.ylife.data.json.json;

import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

/**
 * Created by InThEnd on 2016/4/22.
 */
public class SkipFieldRenderer extends SimpleRenderer {

    private SkipFieldRenderer(ExclusionStrategy strategy) {
        Gson gson = new GsonBuilder().setExclusionStrategies(strategy).create();
        setGson(gson);
    }

    public static SkipFieldRenderer skip(String... fieldNames) {
        ExclusionStrategy strategy = FieldExclusionStrategy.skip(fieldNames);
        return new SkipFieldRenderer(strategy);
    }

    public static SkipFieldRenderer leave(String... fieldNames) {
        ExclusionStrategy strategy = FieldExclusionStrategy.leave(fieldNames);
        return new SkipFieldRenderer(strategy);
    }
}
