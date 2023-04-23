package com.kivicms.catalogue.dto.catalogue;

import com.kivicms.catalogue.dto.AbstractFullDto;
import com.kivicms.catalogue.enums.catalogue.CatalogueFormat;
import com.kivicms.catalogue.enums.catalogue.CatalogueStatus;
import com.kivicms.catalogue.models.catalogue.Catalogue;
import com.kivicms.catalogue.models.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.Date;

@Getter
public class CatalogueDto extends AbstractFullDto {
    @Schema(description = "Название", example = "")
    private final String title;
    @Schema(description = "Описание", example = "")
    private final String description;
    @Schema(description = "Формат", example = "")
    private final CatalogueFormat format;
    @Schema(description = "Номер", example = "")
    private final Integer number;
    @Schema(description = "Кол-во страниц", example = "")
    private final Integer pageCount;
    @Schema(description = "Статус", example = "")
    private final CatalogueStatus status;
    @Schema(description = "Создано", example = "")
    private final Date createdAt;
    @Schema(description = "Обновлено", example = "")
    private final Date updatedAt;
    @Schema(description = "Автор", example = "")
    private final User author;
    @Schema(description = "Редактор", example = "")
    private final User updater;

    public CatalogueDto(Catalogue catalogue) {
        this.id = catalogue.getId();
        this.title = catalogue.getTitle();
        this.description = catalogue.getDescription();
        this.format = catalogue.getFormat();
        this.number = catalogue.getNumber();
        this.pageCount = catalogue.getPageCount();
        this.status = catalogue.getStatus();
        this.createdAt = catalogue.getCreatedAt();
        this.updatedAt = catalogue.getUpdatedAt();
        this.author = catalogue.getAuthor();
        this.updater = catalogue.getUpdater();
    }
}
