package ru.otus.prj.backend.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@ApiModel(description = "Result multiplication of tree numbers")
public class BackEndResponse {

    @ApiModelProperty(notes = "Multiplication of parameter A, parameter B, parameter C")
    @NotNull
    private Integer paramD;
}
