package com.ylife.data.json.message;


import com.google.gson.annotations.Expose;
import com.ylife.data.json.json.Renderer;
import com.ylife.data.json.json.SimpleRenderer;
import com.ylife.data.json.json.SkipFieldRenderer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 返回json的统一Bean.
 */
public class JsonResponseBean {

    @Expose
    private String response = "success";

    @Expose
    private Object data = null;

    private static final JsonResponseBean SUCCESS = new JsonResponseBean();

    private transient Renderer renderer = new SimpleRenderer();

    private static final Map<String, Renderer> rendererCache = new ConcurrentHashMap<>();

    private static final String PREFIX_SKIP = "_0_";

    private static final String PREFIX_LEAVE = "_1_";

    public JsonResponseBean() {
    }

    public JsonResponseBean(Object data) {
        if (data != null) {
            this.data = data;
        } else {
            this.data = "";
        }
    }

    public JsonResponseBean(Object data, Renderer renderer) {
        if (data != null) {
            this.data = data;
        } else {
            this.data = "";
        }
        this.renderer = renderer;
    }

    public JsonResponseBean(String response, Object data) {
        this.response = response;
        this.data = data;
    }

    public String toJson() {
        return renderer.render(this);
    }

    public static JsonResponseBean getSuccessResponse() {
        return SUCCESS;
    }

    public static JsonResponseBean getErrorResponse(ErrorCode code, String text) {
        JsonResponseBean errorBean = new JsonResponseBean();
        Error error = new Error(code.getCode(), text);
        errorBean.setResponse("error");
        errorBean.setData(error);
        return errorBean;
    }

    public static JsonResponseBean getErrorResponse(ErrorCode code, String text, Object object) {
        JsonResponseBean errorBean = new JsonResponseBean();
        Error error = new Error(code.getCode(), text, object);
        errorBean.setResponse("error");
        errorBean.setData(error);
        return errorBean;
    }


    /**
     * 标记略过哪些类字段。如果要忽略的字段较多于被保留的字段，请使用leave方法，效率更好。
     *
     * @see #leave(String... fieldNames)
     */
    public JsonResponseBean skip(String... fieldNames) {
        StringBuilder keyBuilder = new StringBuilder(PREFIX_SKIP);
        for (String name : fieldNames) {
            keyBuilder.append(name);
        }
        String key = keyBuilder.toString();
        Renderer renderer1 = rendererCache.get(key);
        if (renderer1 == null) {
            renderer1 = SkipFieldRenderer.skip(fieldNames);
            rendererCache.put(key, renderer1);
        }
        renderer = renderer1;
        return this;
    }

    /**
     * 标记保留哪些类字段。如果要保留的字段较多与被忽略的字段，请使用skip方法，效率更好。
     *
     * @see #skip(String... fieldNames)
     */
    public JsonResponseBean leave(String... fieldNames) {
        StringBuilder keyBuilder = new StringBuilder(PREFIX_LEAVE);
        for (String name : fieldNames) {
            keyBuilder.append(name);
        }
        String key = keyBuilder.toString();
        Renderer renderer1 = rendererCache.get(key);
        if (renderer1 == null) {
            renderer1 = SkipFieldRenderer.leave(fieldNames);
            rendererCache.put(key, renderer1);
        }
        renderer = renderer1;
        return this;
    }

    public String getResponse() {
        return response;
    }

    private void setResponse(String response) {
        this.response = response;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private static class Error {

        @SuppressWarnings("unused")
        private int errorcode;

        @SuppressWarnings("unused")
        private String text;

        private Object extendMsg;

        public Error(int errorcode, String text) {
            this.errorcode = errorcode;
            this.text = text;
        }

        public Error(int errorcode, String text, Object extendMsg) {
            this.errorcode = errorcode;
            this.text = text;
            this.extendMsg = extendMsg;
        }
    }

}
