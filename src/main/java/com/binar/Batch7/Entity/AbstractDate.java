package com.binar.Batch7.Entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"created_at", "deleted_at"},
        allowGetters = true
)
public abstract class AbstractDate implements Serializable {

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "Asia/javax")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false, updatable = false)
    @CreationTimestamp
    private Date created_date;

    @JsonIgnore
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "Asia/javax")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted_date", nullable = true)
    private Date deleted_date;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "Asia/javax")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date", nullable = false)
    @UpdateTimestamp
    private Date updatedDate;

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }
    @JsonIgnore
    public Date getDeleted_date() {
        return deleted_date;
    }

    public void setDeleted_date(Date deleted_date) {
        this.deleted_date = deleted_date;
    }

    public Date getUpdated_date() {
        return updatedDate;
    }

    public void setUpdated_date(Date updated_date) {
        this.updatedDate = updated_date;
    }
}

