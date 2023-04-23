package com.kivicms.catalogue.repository.catalogue;

import com.kivicms.catalogue.filters.catalogue.CatalogueFilter;
import com.kivicms.catalogue.models.catalogue.Catalogue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("catalogueRepository")
public interface CatalogueRepository extends JpaRepository<Catalogue, UUID> {
    List<Catalogue> findCatalogueByAuthor(UUID createdBy);

    @Query(value = "SELECT COALESCE(max(number), 0) FROM Catalogue ")
    int getMaxNumber();

    Page<Catalogue> getAllByFilter(CatalogueFilter filter, Pageable pageable);
}
