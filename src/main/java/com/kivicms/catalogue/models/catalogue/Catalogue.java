package com.kivicms.catalogue.models.catalogue;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kivicms.catalogue.enums.catalogue.CatalogueFormat;
import com.kivicms.catalogue.enums.catalogue.CatalogueStatus;
import com.kivicms.catalogue.models.AbstractEntity;
import com.kivicms.catalogue.models.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name = "catalogues")
public class Catalogue extends AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator", parameters = {@org.hibernate.annotations.Parameter(name = "uuid_gen_strategy_class", value = "org.hibernate.id.uuid.CustomVersionOneStrategy")})
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String title;
    private String description;
    private Integer number;
    private Integer pageCount;
    private CatalogueStatus status;
    private CatalogueFormat format;
}
