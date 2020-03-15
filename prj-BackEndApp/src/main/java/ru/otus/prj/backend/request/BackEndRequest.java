package ru.otus.prj.backend.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@ApiModel(description = "Request of tree numbers")
public class BackEndRequest {

    @ApiModelProperty(notes = "Value of parameter A", example = "1", position = 1)
    @NotNull
    private Integer paramA;

    @ApiModelProperty(notes = "Value of parameter B", example = "2", position = 2)
    @NotNull
    private Integer paramB;

    @ApiModelProperty(notes = "Value of parameter C", example = "3", position = 3)
    @NotNull
    private Integer paramC;
}
