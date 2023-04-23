package com.kivicms.catalogue.filters.catalogue;

import com.kivicms.catalogue.enums.catalogue.CatalogueFormat;
import com.kivicms.catalogue.enums.catalogue.CatalogueStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Фильтр списка каталогов")
public class CatalogueFilter {
    @Schema(description = "Название", example = "")
    private String title;

    @Schema(description = "Номер", example = "")
    private Integer number;

    @Schema(description = "Статус", example = "")
    private CatalogueStatus status;

    @Schema(description = "Формат", example = "")
    private CatalogueFormat format;
}
