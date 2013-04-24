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
@Table(name = "produkty_has_kategorie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProduktyHasKategorie.findAll", query = "SELECT p FROM ProduktyHasKategorie p"),
    @NamedQuery(name = "ProduktyHasKategorie.findByProduktyProduktyId", query = "SELECT p FROM ProduktyHasKategorie p WHERE p.produktyHasKategoriePK.produktyProduktyId = :produktyProduktyId"),
    @NamedQuery(name = "ProduktyHasKategorie.findByKategorieKategorieId", query = "SELECT p FROM ProduktyHasKategorie p WHERE p.produktyHasKategoriePK.kategorieKategorieId = :kategorieKategorieId")})
public class ProduktyHasKategorie implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProduktyHasKategoriePK produktyHasKategoriePK;
    @JoinColumn(name = "user_id_fk", referencedColumnName = "user_id")
    @ManyToOne
    private User userIdFk;
    @JoinColumn(name = "kategorie_kategorie_id", referencedColumnName = "kategorie_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Kategorie kategorie;
    @JoinColumn(name = "produkty_produkty_id", referencedColumnName = "produkty_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produkty produkty;

    public ProduktyHasKategorie() {
    }

    public ProduktyHasKategorie(ProduktyHasKategoriePK produktyHasKategoriePK) {
        this.produktyHasKategoriePK = produktyHasKategoriePK;
    }

    public ProduktyHasKategorie(int produktyProduktyId, int kategorieKategorieId) {
        this.produktyHasKategoriePK = new ProduktyHasKategoriePK(produktyProduktyId, kategorieKategorieId);
    }

    public ProduktyHasKategoriePK getProduktyHasKategoriePK() {
        return produktyHasKategoriePK;
    }

    public void setProduktyHasKategoriePK(ProduktyHasKategoriePK produktyHasKategoriePK) {
        this.produktyHasKategoriePK = produktyHasKategoriePK;
    }

    public User getUserIdFk() {
        return userIdFk;
    }

    public void setUserIdFk(User userIdFk) {
        this.userIdFk = userIdFk;
    }

    public Kategorie getKategorie() {
        return kategorie;
    }

    public void setKategorie(Kategorie kategorie) {
        this.kategorie = kategorie;
    }

    public Produkty getProdukty() {
        return produkty;
    }

    public void setProdukty(Produkty produkty) {
        this.produkty = produkty;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (produktyHasKategoriePK != null ? produktyHasKategoriePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProduktyHasKategorie)) {
            return false;
        }
        ProduktyHasKategorie other = (ProduktyHasKategorie) object;
        if ((this.produktyHasKategoriePK == null && other.produktyHasKategoriePK != null) || (this.produktyHasKategoriePK != null && !this.produktyHasKategoriePK.equals(other.produktyHasKategoriePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.atm.sklepssm.baza.ProduktyHasKategorie[ produktyHasKategoriePK=" + produktyHasKategoriePK + " ]";
    }
    
}
