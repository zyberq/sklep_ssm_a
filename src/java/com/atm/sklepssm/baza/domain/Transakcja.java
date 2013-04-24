/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.sklepssm.baza.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Maciej_Paszkowski
 */
@Entity
@Table(name = "transakcja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transakcja.findAll", query = "SELECT t FROM Transakcja t"),
    @NamedQuery(name = "Transakcja.findByTransakcjaId", query = "SELECT t FROM Transakcja t WHERE t.transakcjaId = :transakcjaId"),
    @NamedQuery(name = "Transakcja.findByKwota", query = "SELECT t FROM Transakcja t WHERE t.kwota = :kwota"),
    @NamedQuery(name = "Transakcja.findByData", query = "SELECT t FROM Transakcja t WHERE t.data = :data"),
    @NamedQuery(name = "Transakcja.findByStatus", query = "SELECT t FROM Transakcja t WHERE t.status = :status")})
public class Transakcja implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "transakcja_id")
    private Integer transakcjaId;
    @Column(name = "kwota")
    private Integer kwota;
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Size(max = 45)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "user_id_fk", referencedColumnName = "user_id")
    @ManyToOne
    private User userIdFk;
    @JoinColumn(name = "klient_id_fk", referencedColumnName = "klient_id")
    @ManyToOne
    private Klient klientIdFk;
    @OneToMany(mappedBy = "transakcjaIdFk")
    private Collection<Faktury> fakturyCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transakcja")
    private Collection<TransakcjaHasProdukty> transakcjaHasProduktyCollection;

    public Transakcja() {
    }

    public Transakcja(Integer transakcjaId) {
        this.transakcjaId = transakcjaId;
    }

    public Integer getTransakcjaId() {
        return transakcjaId;
    }

    public void setTransakcjaId(Integer transakcjaId) {
        this.transakcjaId = transakcjaId;
    }

    public Integer getKwota() {
        return kwota;
    }

    public void setKwota(Integer kwota) {
        this.kwota = kwota;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUserIdFk() {
        return userIdFk;
    }

    public void setUserIdFk(User userIdFk) {
        this.userIdFk = userIdFk;
    }

    public Klient getKlientIdFk() {
        return klientIdFk;
    }

    public void setKlientIdFk(Klient klientIdFk) {
        this.klientIdFk = klientIdFk;
    }

    @XmlTransient
    public Collection<Faktury> getFakturyCollection() {
        return fakturyCollection;
    }

    public void setFakturyCollection(Collection<Faktury> fakturyCollection) {
        this.fakturyCollection = fakturyCollection;
    }

    @XmlTransient
    public Collection<TransakcjaHasProdukty> getTransakcjaHasProduktyCollection() {
        return transakcjaHasProduktyCollection;
    }

    public void setTransakcjaHasProduktyCollection(Collection<TransakcjaHasProdukty> transakcjaHasProduktyCollection) {
        this.transakcjaHasProduktyCollection = transakcjaHasProduktyCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transakcjaId != null ? transakcjaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transakcja)) {
            return false;
        }
        Transakcja other = (Transakcja) object;
        if ((this.transakcjaId == null && other.transakcjaId != null) || (this.transakcjaId != null && !this.transakcjaId.equals(other.transakcjaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.atm.sklepssm.baza.Transakcja[ transakcjaId=" + transakcjaId + " ]";
    }
    
}
