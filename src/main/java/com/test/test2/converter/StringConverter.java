package com.test.test2.converter;

import com.test.test2.model.CommonData;
import org.springframework.stereotype.Component;
import org.apache.commons.codec.binary.Hex;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class StringConverter  {

    public String convertToBase64(String string)throws  Exception {
        try {
            return Base64.getEncoder().encodeToString(string.getBytes());
        }catch (Exception e){
            throw new IllegalArgumentException("Không thể chuyển đổi dữ liệu String sang Base64.");
        }
    }

    public String convertToHex(String string)throws  Exception {
        try {
            byte[] bytes = string.getBytes();
            return Hex.encodeHexString(bytes);
        }catch (Exception e){
            throw new IllegalArgumentException("Không thể chuyển đổi dữ liệu String sang Hex.");
        }
    }
    public String convertToByteArray(String string)throws  Exception {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
            for (byte b : bytes){
               stringBuffer.append(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));;
            }
            return stringBuffer.toString();
        }catch (Exception e){
            throw new IllegalArgumentException("Không thể chuyển đổi dữ liệu String sang Hex.");
        }
    }




    public CommonData convertToCommon(String data) {
        CommonData commonData = new CommonData();
        commonData.setData(data);
        commonData.setFormat("string");
        return commonData;
    }
}