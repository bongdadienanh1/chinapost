package com.ylife.data.json.json;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.annotations.Expose;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by InThEnd on 2016/4/22.
 * <p/>
 * 字段过滤策略。
 */
public class FieldExclusionStrategy implements ExclusionStrategy {

    private Set<String> ignoreSet;

    private Set<String> leaveSet;

    //标记此策略的行为为忽略或者留存。
    private boolean ignore;

    private FieldExclusionStrategy(boolean ignore, Set<String> nameSet) {
        this.ignore = ignore;
        if (ignore) {
            ignoreSet = nameSet;
        } else {
            leaveSet = nameSet;
        }
    }

    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        Expose expose = f.getAnnotation(Expose.class);
        if (expose != null) {
            return false;
        }
        String name = f.getName();
        if (ignore) {
            return ignoreSet.contains(name);
        } else {
            return !leaveSet.contains(name);
        }
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }

    public static FieldExclusionStrategy skip(String... fieldNames) {
        Set<String> nameSet = new HashSet<>();
        for (String string : fieldNames) {
            nameSet.add(string);
        }
        return new FieldExclusionStrategy(true, nameSet);
    }

    public static FieldExclusionStrategy leave(String... fieldNames) {
        Set<String> nameSet = new HashSet<>();
        for (String string : fieldNames) {
            nameSet.add(string);
        }
        return new FieldExclusionStrategy(false, nameSet);
    }

}
