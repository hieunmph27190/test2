package com.test.test2.converter;


import com.test.test2.model.CommonData;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
@Component
public class Base64Converter  {

    public String convertToString(String base64)  throws  Exception{
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(base64);
            String result = new String(decodedBytes);
            return result;
        }catch (Exception e){
            throw new IllegalArgumentException("Không thể chuyển đổi dữ liệu Base64 sang String.");
        }

    }
    public String convertToHex(String base64)  throws  Exception{
        try {
            byte[] base64Bytes = base64.getBytes(StandardCharsets.UTF_8);
            byte[] hexBytes = Base64.getDecoder().decode(base64Bytes);
            StringBuilder hexString = new StringBuilder();
            for (byte b : hexBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        }catch (Exception e){
            throw new IllegalArgumentException("Không thể chuyển đổi dữ liệu Base64 sang Hex.");
        }

    }
    public String convertToByteArray(String base64)throws  Exception {
        byte[] base64Bytes = base64.getBytes(StandardCharsets.UTF_8);
        byte[] bytes = Base64.getDecoder().decode(base64Bytes);
        try {
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : bytes){
                stringBuffer.append(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));;
            }
            return stringBuffer.toString();
        }catch (Exception e){
            throw new IllegalArgumentException("Không thể chuyển đổi dữ liệu Base64 sang Hex.");
        }
    }


    public CommonData convertToCommon(String data) {
        byte[] base64Bytes = Base64.getEncoder().encode(data.getBytes());
        CommonData commonData = new CommonData();
        commonData.setData(new String(base64Bytes));
        commonData.setFormat("base64");
        return commonData;
    }
}