package com.kivicms.catalogue.repository.catalogue;

import com.kivicms.catalogue.filters.catalogue.CatalogueFilter;
import com.kivicms.catalogue.models.catalogue.Catalogue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogueRepositoryCustom {
    Page<Catalogue> getAllByFilter(CatalogueFilter filter, Pageable pageable);
}
