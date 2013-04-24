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
public class ProduktyHasZamowieniaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "produkty_produkty_id")
    private int produktyProduktyId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "zamowienia_zamowienia_id")
    private int zamowieniaZamowieniaId;

    public ProduktyHasZamowieniaPK() {
    }

    public ProduktyHasZamowieniaPK(int produktyProduktyId, int zamowieniaZamowieniaId) {
        this.produktyProduktyId = produktyProduktyId;
        this.zamowieniaZamowieniaId = zamowieniaZamowieniaId;
    }

    public int getProduktyProduktyId() {
        return produktyProduktyId;
    }

    public void setProduktyProduktyId(int produktyProduktyId) {
        this.produktyProduktyId = produktyProduktyId;
    }

    public int getZamowieniaZamowieniaId() {
        return zamowieniaZamowieniaId;
    }

    public void setZamowieniaZamowieniaId(int zamowieniaZamowieniaId) {
        this.zamowieniaZamowieniaId = zamowieniaZamowieniaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) produktyProduktyId;
        hash += (int) zamowieniaZamowieniaId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProduktyHasZamowieniaPK)) {
            return false;
        }
        ProduktyHasZamowieniaPK other = (ProduktyHasZamowieniaPK) object;
        if (this.produktyProduktyId != other.produktyProduktyId) {
            return false;
        }
        if (this.zamowieniaZamowieniaId != other.zamowieniaZamowieniaId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.atm.sklepssm.baza.ProduktyHasZamowieniaPK[ produktyProduktyId=" + produktyProduktyId + ", zamowieniaZamowieniaId=" + zamowieniaZamowieniaId + " ]";
    }
    
}
