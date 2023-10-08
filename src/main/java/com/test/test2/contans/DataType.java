package com.test.test2.contans;

import com.test.test2.converter.*;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;

public enum DataType {

    STRING("string", new StringConverter()),
    HEX("hex", new HexConverter()),
    BASE64("base64", new Base64Converter()),
    BYTE_ARRAY("byte[]", new ByteConverter());

    private final String stringValue;
    private final Converter converter;

    DataType(String stringValue, Converter converter) {
        this.stringValue = stringValue;
        this.converter = converter;
    }

    public String getStringValue() {
        return stringValue;
    }
    public Converter getConverter() {
        return converter;
    }

    public static DataType fromString(String text) {
        for (DataType dataType : DataType.values()) {
            if (dataType.stringValue.equalsIgnoreCase(text)) {
                return dataType;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }
}
