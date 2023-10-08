package com.test.test2.controller;


import com.test.test2.adapter.ConverterAdapter;
import com.test.test2.contans.DataType;
import com.test.test2.converter.Converter;

import com.test.test2.dto.ConvertRequest;
import com.test.test2.dto.ConvertResponse;
import com.test.test2.model.CommonData;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ConvertController {
    @Autowired
    ConverterAdapter convert;

    @PostMapping("/convert")
    public ResponseEntity<Object> convertData(@Valid @RequestBody ConvertRequest request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            Map<String,String> error = new HashMap<>();
             bindingResult.getFieldErrors().stream().forEach((item)->{
                 error.put(item.getField(),item.getDefaultMessage());
            });
             return ResponseEntity.badRequest().body(error);
        }

        CommonData commonData = request.getCommonData();
        Object result;
        try {
          result = convert.convert(commonData);
        }catch (Exception exception){
            System.out.println(exception);
            return ResponseEntity.badRequest().body(new ConvertResponse(exception.getMessage()));
        }

        ConvertResponse response = new ConvertResponse(result);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/datatype")
    public Object test(){
        Map<String,String> map = new HashMap<>();
        for (DataType dataType:DataType.values()){
            map.put(dataType.getStringValue(), dataType.getStringValue().substring(0,1).toUpperCase() +dataType.getStringValue().substring(1));
        }
        return map;
    }
}
