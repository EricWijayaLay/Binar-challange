package com.binar.Batch7.Entity;

import lombok.Data;


import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.Date;

//@EntityScan
@Data
@Entity
@Table(name = "employee")
@Where(clause = "deleted_date is null")
public class Employee extends AbstractDate implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;

    @Column(name = "address", columnDefinition = "TEXT")
    public String address;

    // 2016-01-01
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    public  String status = "active"; // Value Default


    //    @JsonIgnore
    @OneToOne (targetEntity = DetailKaryawan.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="id_detail_karyawan", referencedColumnName = "id")
    private DetailKaryawan detailKaryawan;

//    // best practice -> jika data yang ditampilkan , karena akan pengarruh performance
//    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Rekening> rekening;

    // field baru


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DetailKaryawan getDetailKaryawan() {
        return detailKaryawan;
    }

    public void setDetailKaryawan(DetailKaryawan detailKaryawan) {
        this.detailKaryawan = detailKaryawan;
    }
}



