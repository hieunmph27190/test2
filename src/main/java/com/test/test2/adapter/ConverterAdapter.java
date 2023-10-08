package com.test.test2.adapter;

import com.test.test2.contans.DataType;
import com.test.test2.converter.*;
import com.test.test2.dto.ConvertResponse;
import com.test.test2.model.CommonData;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ConverterAdapter{
    public static String convert(CommonData commonData) {
        Converter sourceConverter = (DataType.fromString(commonData.getFormat())).getConverter();
        Converter targetConverter = (DataType.fromString(commonData.getTo())).getConverter();

        // Chuyển đổi từ kiểu nguồn sang String
        String stringData = sourceConverter.decode(commonData.getData().toString());

        // Chuyển đổi từ String sang kiểu đích
        return  targetConverter.encode(stringData);
    }
}

