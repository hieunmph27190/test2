package com.test.test2.dto;

import com.test.test2.model.CommonData;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConvertRequest {
    @NotBlank
    private String format;
    @NotBlank
    private String data;
    @NotBlank
    private String to;

    public CommonData getCommonData() {
        CommonData commonData = new CommonData();
        commonData.setData(data);
        commonData.setFormat(format);
        commonData.setTo(to);
        return commonData;
    }
}

