package com.test.test2.converter;

import com.test.test2.dto.ConvertResponse;
import com.test.test2.model.CommonData;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ConverterAdapter implements Converter {
    StringConverter stringConverter = new StringConverter();
    Base64Converter base64Converter = new Base64Converter();
    HexConverter hexConverter = new HexConverter();
    ByteConverter byteConverter = new ByteConverter();
    @Override
    public Object convert(CommonData commonData) throws Exception {
        String format = commonData.getFormat();
        String to = commonData.getTo();
        Object data = commonData.getData();
        String result;
            switch (format){
                case "string" :
                    switch (to){
                        case "base64" :
                            result = stringConverter.convertToBase64(data.toString());
                            break;
                        case "hex" :
                            result = stringConverter.convertToHex(data.toString());
                            break;
                        case "byte[]" :
                            result = stringConverter.convertToByteArray(data.toString());
                            break;
                        default:
                            return ResponseEntity.badRequest().body(new ConvertResponse("Chuyển đổi chưa được hỗ trợ"));
                    }
                    break;
                case "base64" :
                    switch (to){
                        case "string" :
                            result = base64Converter.convertToString(data.toString());
                            break;
                        case "hex" :
                            result = base64Converter.convertToHex(data.toString());
                            break;
                        case "byte[]" :
                            result = base64Converter.convertToByteArray(data.toString());
                            break;
                        default:
                            return ResponseEntity.badRequest().body(new ConvertResponse("Chuyển đổi chưa được hỗ trợ"));
                    }
                    break;
                case "hex" :
                    switch (to){
                        case "string" :
                            result = hexConverter.convertToString(data.toString());
                            break;
                        case "base64" :
                            result = hexConverter.convertToBase64(data.toString());
                            break;
                        case "byte[]" :
                            result = hexConverter.convertToByteArray(data.toString());
                            break;
                        default:
                            return ResponseEntity.badRequest().body(new ConvertResponse("Chuyển đổi chưa được hỗ trợ"));
                    }
                    break;
                case "byte[]" :
                    switch (to){
                        case "string" :
                            result = byteConverter.convertToString(data.toString());
                            break;
                        case "base64" :
                            result = byteConverter.convertToBase64(data.toString());
                            break;
                        case "hex" :
                            result = byteConverter.convertToHex(data.toString());
                            break;
                        default:
                            return ResponseEntity.badRequest().body(new ConvertResponse("Chuyển đổi chưa được hỗ trợ"));
                    }
                    break;
                default:
                    return ResponseEntity.badRequest().body(new ConvertResponse("Định dạng đầu vào không hỗ trợ"));
            }
            return result;

    }


}
