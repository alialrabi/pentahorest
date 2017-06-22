package com.innvo.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Asset.
 */
@Entity
@Table(name = "asset")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Asset implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "nameshort")
    private String nameshort;

    @Column(name = "description")
    private String description;

    @Column(name = "lastmodifedby")
    private String lastmodifedby;

    @Column(name = "domain")
    private String domain;

    @Column(name = "status")
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Asset name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameshort() {
        return nameshort;
    }

    public Asset nameshort(String nameshort) {
        this.nameshort = nameshort;
        return this;
    }

    public void setNameshort(String nameshort) {
        this.nameshort = nameshort;
    }

    public String getDescription() {
        return description;
    }

    public Asset description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLastmodifedby() {
        return lastmodifedby;
    }

    public Asset lastmodifedby(String lastmodifedby) {
        this.lastmodifedby = lastmodifedby;
        return this;
    }

    public void setLastmodifedby(String lastmodifedby) {
        this.lastmodifedby = lastmodifedby;
    }

    public String getDomain() {
        return domain;
    }

    public Asset domain(String domain) {
        this.domain = domain;
        return this;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getStatus() {
        return status;
    }

    public Asset status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Asset asset = (Asset) o;
        if (asset.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), asset.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Asset{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", nameshort='" + getNameshort() + "'" +
            ", description='" + getDescription() + "'" +
            ", lastmodifedby='" + getLastmodifedby() + "'" +
            ", domain='" + getDomain() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
