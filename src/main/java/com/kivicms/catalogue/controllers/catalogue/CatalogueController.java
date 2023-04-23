package com.kivicms.catalogue.controllers.catalogue;

import com.kivicms.catalogue.dto.catalogue.CatalogueDto;
import com.kivicms.catalogue.filters.catalogue.CatalogueFilter;
import com.kivicms.catalogue.models.catalogue.Catalogue;
import com.kivicms.catalogue.service.catalogue.CatalogueService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@Tag(name = "Каталоги")
@RequestMapping("/api/v1/catalogues")
@RestController
public class CatalogueController {

    private final CatalogueService catalogueService;

    @Autowired
    public CatalogueController(CatalogueService catalogueService) {
        this.catalogueService = catalogueService;
    }

    @GetMapping
    public Page<CatalogueDto> index(@ParameterObject CatalogueFilter catalogueFilter, @ParameterObject Pageable pageable) {
        //return ResponseEntity.ok(catalogueService.index());
        return catalogueService.getAllByFilter(catalogueFilter, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Catalogue>> show(@PathVariable UUID id) {
        return new ResponseEntity<Optional<Catalogue>>(catalogueService.show(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Catalogue> store(@RequestBody Catalogue catalogue) {
        return ResponseEntity.ok(catalogueService.store(catalogue));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Catalogue> update(@PathVariable UUID id, @RequestBody Catalogue catalogue) {
        return ResponseEntity.ok(catalogueService.update(id, catalogue));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        catalogueService.delete(id);
        return ResponseEntity.ok("Запись успешно удалена");
    }

    @GetMapping("/search")
    public ResponseEntity<List<Catalogue>> search(UUID createdBy, String keyword, Integer format, Integer number, Integer status, int page, int size) {
        return ResponseEntity.ok(catalogueService.search(createdBy, keyword, format, number, status, page, size));
    }

    @GetMapping("/generate")
    public ResponseEntity<List<Catalogue>> generate(Integer number) {
        return ResponseEntity.ok(catalogueService.generate(number));
    }
}
