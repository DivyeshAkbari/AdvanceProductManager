package co.pragra.productmanager.newproductmanager.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@Builder
@RequiredArgsConstructor
public class ErrorDto {
    private final int errorCode;
    private final String errorMessage;
    private final Date TimeStamp;
}
