/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.sklepssm.baza.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Maciej_Paszkowski
 */
@Embeddable
public class FakturyHasProduktyPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "faktury_faktury_id")
    private int fakturyFakturyId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "produkty_produkty_id")
    private int produktyProduktyId;

    public FakturyHasProduktyPK() {
    }

    public FakturyHasProduktyPK(int fakturyFakturyId, int produktyProduktyId) {
        this.fakturyFakturyId = fakturyFakturyId;
        this.produktyProduktyId = produktyProduktyId;
    }

    public int getFakturyFakturyId() {
        return fakturyFakturyId;
    }

    public void setFakturyFakturyId(int fakturyFakturyId) {
        this.fakturyFakturyId = fakturyFakturyId;
    }

    public int getProduktyProduktyId() {
        return produktyProduktyId;
    }

    public void setProduktyProduktyId(int produktyProduktyId) {
        this.produktyProduktyId = produktyProduktyId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) fakturyFakturyId;
        hash += (int) produktyProduktyId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FakturyHasProduktyPK)) {
            return false;
        }
        FakturyHasProduktyPK other = (FakturyHasProduktyPK) object;
        if (this.fakturyFakturyId != other.fakturyFakturyId) {
            return false;
        }
        if (this.produktyProduktyId != other.produktyProduktyId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.atm.sklepssm.baza.FakturyHasProduktyPK[ fakturyFakturyId=" + fakturyFakturyId + ", produktyProduktyId=" + produktyProduktyId + " ]";
    }
    
}
