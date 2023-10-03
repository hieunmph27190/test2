package com.test.test2.converter;

import com.test.test2.model.CommonData;
import org.springframework.stereotype.Component;
import org.apache.commons.codec.binary.Hex;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class ByteConverter  {

    public String convertToString(String bitString)throws  Exception {
        try {

            StringBuilder result = new StringBuilder();
            for (int i = 0; i < bitString.length(); i += 8) {
                String eightBits = bitString.substring(i, Math.min(i + 8, bitString.length()));
                int decimalValue = Integer.parseInt(eightBits, 2);
                result.append((char) decimalValue);
            }
            return result.toString();

        }catch (Exception e){
            throw new IllegalArgumentException("Không thể chuyển đổi dữ liệu byte[] sang String.");
        }
    }
    public  String convertToBase64(String bitString) {
        try {
            byte[] bytes = toByteArray(bitString);
            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            throw new IllegalArgumentException("Không thể chuyển đổi dữ liệu byte[] sang Base64.");
        }
    }

    public  String convertToHex(String bitString) {
        try {
            byte[] bytes = toByteArray(bitString);
            return Hex.encodeHexString(bytes);
        } catch (Exception e) {
            throw new IllegalArgumentException("Không thể chuyển đổi dữ liệu byte[] sang Hex.");
        }
    }
    public byte[] toByteArray(String bitString) {
        int length = bitString.length();
        byte[] byteArray = new byte[length / 8];

        for (int i = 0; i < length; i += 8) {
            String byteString = bitString.substring(i, i + 8);
            byte byteValue = (byte) Integer.parseInt(byteString, 2);
            byteArray[i / 8] = byteValue;
        }
        return byteArray;
    }


    public CommonData convertToCommon(byte[] data) {
        CommonData commonData = new CommonData();
        commonData.setData(data);
        commonData.setFormat("byte[]");
        return commonData;
    }
}