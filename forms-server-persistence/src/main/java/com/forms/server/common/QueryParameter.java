package com.forms.server.common;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Hoa Ho
 * Date: 2/21/13
 * Time: 10:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class QueryParameter {
    private Map<String, Object> parameters = null;

    private QueryParameter(String name, Object value) {
        this.parameters = new HashMap<String, Object>();
        this.parameters.put(name, value);
    }

    public static QueryParameter with(String name, Object value) {
        return new QueryParameter(name, value);
    }

    public QueryParameter and(String name, Object value) {
        this.parameters.put(name, value);
        return this;
    }

    public Map<String, Object> parameters() {
        return this.parameters;
    }
}
