/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.sklepssm.baza.domain;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Maciej_Paszkowski
 */
@Entity
@Table(name = "faktury_has_produkty")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FakturyHasProdukty.findAll", query = "SELECT f FROM FakturyHasProdukty f"),
    @NamedQuery(name = "FakturyHasProdukty.findByFakturyFakturyId", query = "SELECT f FROM FakturyHasProdukty f WHERE f.fakturyHasProduktyPK.fakturyFakturyId = :fakturyFakturyId"),
    @NamedQuery(name = "FakturyHasProdukty.findByProduktyProduktyId", query = "SELECT f FROM FakturyHasProdukty f WHERE f.fakturyHasProduktyPK.produktyProduktyId = :produktyProduktyId")})
public class FakturyHasProdukty implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FakturyHasProduktyPK fakturyHasProduktyPK;
    @JoinColumn(name = "user_id_fk", referencedColumnName = "user_id")
    @ManyToOne
    private User userIdFk;
    @JoinColumn(name = "produkty_produkty_id", referencedColumnName = "produkty_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produkty produkty;
    @JoinColumn(name = "faktury_faktury_id", referencedColumnName = "faktury_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Faktury faktury;

    public FakturyHasProdukty() {
    }

    public FakturyHasProdukty(FakturyHasProduktyPK fakturyHasProduktyPK) {
        this.fakturyHasProduktyPK = fakturyHasProduktyPK;
    }

    public FakturyHasProdukty(int fakturyFakturyId, int produktyProduktyId) {
        this.fakturyHasProduktyPK = new FakturyHasProduktyPK(fakturyFakturyId, produktyProduktyId);
    }

    public FakturyHasProduktyPK getFakturyHasProduktyPK() {
        return fakturyHasProduktyPK;
    }

    public void setFakturyHasProduktyPK(FakturyHasProduktyPK fakturyHasProduktyPK) {
        this.fakturyHasProduktyPK = fakturyHasProduktyPK;
    }

    public User getUserIdFk() {
        return userIdFk;
    }

    public void setUserIdFk(User userIdFk) {
        this.userIdFk = userIdFk;
    }

    public Produkty getProdukty() {
        return produkty;
    }

    public void setProdukty(Produkty produkty) {
        this.produkty = produkty;
    }

    public Faktury getFaktury() {
        return faktury;
    }

    public void setFaktury(Faktury faktury) {
        this.faktury = faktury;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fakturyHasProduktyPK != null ? fakturyHasProduktyPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FakturyHasProdukty)) {
            return false;
        }
        FakturyHasProdukty other = (FakturyHasProdukty) object;
        if ((this.fakturyHasProduktyPK == null && other.fakturyHasProduktyPK != null) || (this.fakturyHasProduktyPK != null && !this.fakturyHasProduktyPK.equals(other.fakturyHasProduktyPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.atm.sklepssm.baza.FakturyHasProdukty[ fakturyHasProduktyPK=" + fakturyHasProduktyPK + " ]";
    }
    
}
