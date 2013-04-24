/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.sklepssm.baza.domain;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Maciej_Paszkowski
 */
@Entity
@Table(name = "regal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Regal.findAll", query = "SELECT r FROM Regal r"),
    @NamedQuery(name = "Regal.findByRegalId", query = "SELECT r FROM Regal r WHERE r.regalId = :regalId"),
    @NamedQuery(name = "Regal.findByNazwa", query = "SELECT r FROM Regal r WHERE r.nazwa = :nazwa"),
    @NamedQuery(name = "Regal.findByMiejsce", query = "SELECT r FROM Regal r WHERE r.miejsce = :miejsce"),
    @NamedQuery(name = "Regal.findByIlosc", query = "SELECT r FROM Regal r WHERE r.ilosc = :ilosc")})
public class Regal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "regal_id")
    private Integer regalId;
    @Size(max = 45)
    @Column(name = "nazwa")
    private String nazwa;
    @Size(max = 45)
    @Column(name = "miejsce")
    private String miejsce;
    @Column(name = "ilosc")
    private Integer ilosc;
    @OneToMany(mappedBy = "regalIdFk")
    private Collection<Produkty> produktyCollection;
    @JoinColumn(name = "user_id_fk", referencedColumnName = "user_id")
    @ManyToOne
    private User userIdFk;

    public Regal() {
    }

    public Regal(Integer regalId) {
        this.regalId = regalId;
    }

    public Integer getRegalId() {
        return regalId;
    }

    public void setRegalId(Integer regalId) {
        this.regalId = regalId;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getMiejsce() {
        return miejsce;
    }

    public void setMiejsce(String miejsce) {
        this.miejsce = miejsce;
    }

    public Integer getIlosc() {
        return ilosc;
    }

    public void setIlosc(Integer ilosc) {
        this.ilosc = ilosc;
    }

    @XmlTransient
    public Collection<Produkty> getProduktyCollection() {
        return produktyCollection;
    }

    public void setProduktyCollection(Collection<Produkty> produktyCollection) {
        this.produktyCollection = produktyCollection;
    }

    public User getUserIdFk() {
        return userIdFk;
    }

    public void setUserIdFk(User userIdFk) {
        this.userIdFk = userIdFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (regalId != null ? regalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Regal)) {
            return false;
        }
        Regal other = (Regal) object;
        if ((this.regalId == null && other.regalId != null) || (this.regalId != null && !this.regalId.equals(other.regalId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.atm.sklepssm.baza.Regal[ regalId=" + regalId + " ]";
    }
    
}
