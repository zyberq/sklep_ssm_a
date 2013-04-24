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
public class ProduktyHasKategoriePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "produkty_produkty_id")
    private int produktyProduktyId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "kategorie_kategorie_id")
    private int kategorieKategorieId;

    public ProduktyHasKategoriePK() {
    }

    public ProduktyHasKategoriePK(int produktyProduktyId, int kategorieKategorieId) {
        this.produktyProduktyId = produktyProduktyId;
        this.kategorieKategorieId = kategorieKategorieId;
    }

    public int getProduktyProduktyId() {
        return produktyProduktyId;
    }

    public void setProduktyProduktyId(int produktyProduktyId) {
        this.produktyProduktyId = produktyProduktyId;
    }

    public int getKategorieKategorieId() {
        return kategorieKategorieId;
    }

    public void setKategorieKategorieId(int kategorieKategorieId) {
        this.kategorieKategorieId = kategorieKategorieId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) produktyProduktyId;
        hash += (int) kategorieKategorieId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProduktyHasKategoriePK)) {
            return false;
        }
        ProduktyHasKategoriePK other = (ProduktyHasKategoriePK) object;
        if (this.produktyProduktyId != other.produktyProduktyId) {
            return false;
        }
        if (this.kategorieKategorieId != other.kategorieKategorieId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.atm.sklepssm.baza.ProduktyHasKategoriePK[ produktyProduktyId=" + produktyProduktyId + ", kategorieKategorieId=" + kategorieKategorieId + " ]";
    }
    
}
