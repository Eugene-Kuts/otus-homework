package ru.otus.prj.backend.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
public class BackEndRequest {

    @NotNull
    private Integer paramA;
    @NotNull
    private Integer paramB;
    @NotNull
    private Integer paramC;

    public Integer getMultiplication() {
        return paramA * paramB * paramC;
    }
}
