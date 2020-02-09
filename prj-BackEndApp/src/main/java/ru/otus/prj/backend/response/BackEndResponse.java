package ru.otus.prj.backend.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class BackEndResponse {
    @NotNull
    private Integer paramD;
}
