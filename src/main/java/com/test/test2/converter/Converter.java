package com.test.test2.converter;

import com.test.test2.model.CommonData;
import org.springframework.stereotype.Service;

@Service
public interface Converter<T> {
    T convert(CommonData commonData) throws Exception;

}