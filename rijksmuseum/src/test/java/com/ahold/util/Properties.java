package com.ahold.util;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public final class Properties {

    private Properties() {}

    public static final Config CONF = ConfigFactory.load();

    public static final String URL_EN = CONF.getString("endpoints.en");
    public static final String URL_NL = CONF.getString("endpoints.nl");
    public static final String API_KEY = CONF.getString("secrets.api_key");
}
