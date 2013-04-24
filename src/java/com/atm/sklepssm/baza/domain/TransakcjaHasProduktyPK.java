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
public class TransakcjaHasProduktyPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "transakcja_transakcja_id")
    private int transakcjaTransakcjaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "produkty_produkty_id")
    private int produktyProduktyId;

    public TransakcjaHasProduktyPK() {
    }

    public TransakcjaHasProduktyPK(int transakcjaTransakcjaId, int produktyProduktyId) {
        this.transakcjaTransakcjaId = transakcjaTransakcjaId;
        this.produktyProduktyId = produktyProduktyId;
    }

    public int getTransakcjaTransakcjaId() {
        return transakcjaTransakcjaId;
    }

    public void setTransakcjaTransakcjaId(int transakcjaTransakcjaId) {
        this.transakcjaTransakcjaId = transakcjaTransakcjaId;
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
        hash += (int) transakcjaTransakcjaId;
        hash += (int) produktyProduktyId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransakcjaHasProduktyPK)) {
            return false;
        }
        TransakcjaHasProduktyPK other = (TransakcjaHasProduktyPK) object;
        if (this.transakcjaTransakcjaId != other.transakcjaTransakcjaId) {
            return false;
        }
        if (this.produktyProduktyId != other.produktyProduktyId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.atm.sklepssm.baza.TransakcjaHasProduktyPK[ transakcjaTransakcjaId=" + transakcjaTransakcjaId + ", produktyProduktyId=" + produktyProduktyId + " ]";
    }
    
}
