package com.kivicms.catalogue.models.page;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kivicms.catalogue.models.AbstractEntity;
import com.kivicms.catalogue.models.catalogue.Catalogue;
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
@Table(name = "pages")
public class Page extends AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator", parameters = {@org.hibernate.annotations.Parameter(name = "uuid_gen_strategy_class", value = "org.hibernate.id.uuid.CustomVersionOneStrategy")})
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catalogue_id", insertable = false, updatable = false, columnDefinition = "UUID")
    private Catalogue catalogue;

    private Integer number;
    private String preview;

/*    @Override
    public void prePersist() {
        super.prePersist();
        number = 1;
    }*/
}
