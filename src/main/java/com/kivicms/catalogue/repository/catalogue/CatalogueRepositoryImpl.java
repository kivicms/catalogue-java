package com.kivicms.catalogue.repository.catalogue;

import com.kivicms.catalogue.filters.catalogue.CatalogueFilter;
import com.kivicms.catalogue.models.catalogue.Catalogue;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CatalogueRepositoryImpl implements CatalogueRepositoryCustom {

    @Autowired
    CatalogueRepositorySpec catalogueRepositorySpec;

    public Page<Catalogue> getAllByFilter(CatalogueFilter filter, Pageable pageable) {
        return this.catalogueRepositorySpec.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter.getTitle() != null) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + filter.getTitle() + "%"));
            }
            if (filter.getNumber() != null) {
                predicates.add(criteriaBuilder.equal(root.get("number"), filter.getNumber()));
            }
            if (filter.getStatus() != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), filter.getStatus()));
            }
            if (filter.getFormat() != null) {
                predicates.add(criteriaBuilder.equal(root.get("format"), filter.getFormat()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
        }, pageable);
    }
}
