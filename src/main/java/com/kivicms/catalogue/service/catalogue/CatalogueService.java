package com.kivicms.catalogue.service.catalogue;

import com.kivicms.catalogue.dto.catalogue.CatalogueDto;
import com.kivicms.catalogue.filters.catalogue.CatalogueFilter;
import com.kivicms.catalogue.models.catalogue.Catalogue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface CatalogueService {
    List<Catalogue> index();
    Optional<Catalogue> show(UUID id);
    Catalogue store(Catalogue catalogue);
    Catalogue update(UUID id, Catalogue catalogue);
    void delete(UUID id);

    List<Catalogue> search(UUID createdBy, String keyword, Integer format, Integer number, Integer status, int page, int size);

    List<Catalogue> generate(Integer count);

    Page<CatalogueDto> getAllByFilter(CatalogueFilter catalogueFilter, Pageable pageable);
}
