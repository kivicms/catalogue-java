package com.kivicms.catalogue.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AbstractFullDto extends AbstractDto {

    @Schema(description = "Идентификатор", example = "")
    protected UUID id;
}
