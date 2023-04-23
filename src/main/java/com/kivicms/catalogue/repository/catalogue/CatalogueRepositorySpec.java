package com.kivicms.catalogue.repository.catalogue;

import com.kivicms.catalogue.models.catalogue.Catalogue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CatalogueRepositorySpec extends JpaRepository<Catalogue, UUID>, JpaSpecificationExecutor {
}
