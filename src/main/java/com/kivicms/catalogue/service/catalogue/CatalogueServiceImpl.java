package com.kivicms.catalogue.service.catalogue;

import com.github.javafaker.Faker;
import com.kivicms.catalogue.dto.catalogue.CatalogueDto;
import com.kivicms.catalogue.enums.catalogue.CatalogueFormat;
import com.kivicms.catalogue.enums.catalogue.CatalogueStatus;
import com.kivicms.catalogue.filters.catalogue.CatalogueFilter;
import com.kivicms.catalogue.models.catalogue.Catalogue;
import com.kivicms.catalogue.models.user.User;
import com.kivicms.catalogue.repository.catalogue.CatalogueRepository;
import com.kivicms.catalogue.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CatalogueServiceImpl implements CatalogueService {
    private final CatalogueRepository catalogueRepository;

    @Autowired
    public CatalogueServiceImpl(CatalogueRepository catalogueRepository, UserRepository userRepository) {
        this.catalogueRepository = catalogueRepository;
    }

    @Override
    public List<Catalogue> index() {
        return catalogueRepository.findAll();
    }

    @Override
    public Optional<Catalogue> show(UUID id) {
        return catalogueRepository.findById(id);
    }

    @Override
    public Catalogue store(Catalogue catalogue) {
        return null;
    }

    @Override
    public Catalogue update(UUID id, Catalogue catalogue) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public List<Catalogue> search(UUID createdBy, String keyword, Integer format, Integer number, Integer status, int page, int size) {
        return null;
    }

    @Override
    public List<Catalogue> generate(Integer count) {
        Faker faker = new Faker();

        for (int i = 0; i < count; i++) {
            Catalogue catalogue = new Catalogue();
            catalogue.setTitle(faker.name().title());
            catalogue.setDescription(faker.lorem().sentence());
            catalogue.setNumber(i + 1);
            catalogue.setFormat(CatalogueFormat.A4);
            catalogue.setStatus(CatalogueStatus.ONWORK);
            catalogueRepository.save(catalogue);
        }
        return null;
    }

    /**
     * @return
     */
    @Override
    public Page<CatalogueDto> getAllByFilter(CatalogueFilter filter, Pageable pageable) {
        Page<Catalogue> cataloguePage = catalogueRepository.getAllByFilter(filter, pageable);

        return cataloguePage.map(CatalogueDto::new);
    }
}
