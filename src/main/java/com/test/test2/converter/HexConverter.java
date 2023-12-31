package com.test.test2.converter;

import com.test.test2.model.CommonData;
import org.springframework.stereotype.Component;
import org.apache.commons.codec.binary.Hex;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class HexConverter implements  Converter {

    public String convertToString(String hex)throws  Exception {
        try {
            byte[] bytes = Hex.decodeHex(hex);
            return new String(bytes, "UTF-8");
        }catch (Exception e){
            throw new IllegalArgumentException("Không thể chuyển đổi dữ liệu Hex sang String.");
        }

    }
    public String convertToBase64(String hex)throws  Exception {
        try {
            byte[] hexBytes = hexToByteArray(hex);
            byte[] base64Bytes = Base64.getEncoder().encode(hexBytes);
            return new String(base64Bytes, StandardCharsets.UTF_8);
        }catch (Exception e){
            throw new IllegalArgumentException("Không thể chuyển đổi dữ liệu Hex sang Base64.");
        }


    }

    public String convertToByteArray(String hex)throws  Exception {
        byte[]bytes=  Hex.decodeHex(hex);
        try {
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : bytes){
                stringBuffer.append(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));;
            }
            return stringBuffer.toString();
        }catch (Exception e){
            throw new IllegalArgumentException("Không thể chuyển đổi dữ liệu Hex sang byte[].");
        }
    }



    public CommonData convertToCommon(String data) {
        CommonData commonData = new CommonData();
        commonData.setData(data);
        commonData.setFormat("string");
        return commonData;
    }
    private byte[] hexToByteArray(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4) + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }
    @Override
    public String encode(String input) {
        return bytesToHex(input.getBytes()); // Chuyển đổi String thành định dạng Hex
    }

    @Override
    public String decode(String input) {
        return new String(hexToBytes(input)); // Chuyển đổi từ Hex thành String
    }

    private static String bytesToHex(byte[] bytes) {
        try {
            return Hex.encodeHexString(bytes);
        } catch (Exception e) {
            throw e;
        }
    }

    private static byte[] hexToBytes(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4) + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
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
}