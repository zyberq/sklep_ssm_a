/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.sklepssm.baza.domain;

import java.io.Serializable;
import javax.persistence.Column;
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
@Table(name = "produkty_has_zamowienia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProduktyHasZamowienia.findAll", query = "SELECT p FROM ProduktyHasZamowienia p"),
    @NamedQuery(name = "ProduktyHasZamowienia.findByProduktyProduktyId", query = "SELECT p FROM ProduktyHasZamowienia p WHERE p.produktyHasZamowieniaPK.produktyProduktyId = :produktyProduktyId"),
    @NamedQuery(name = "ProduktyHasZamowienia.findByZamowieniaZamowieniaId", query = "SELECT p FROM ProduktyHasZamowienia p WHERE p.produktyHasZamowieniaPK.zamowieniaZamowieniaId = :zamowieniaZamowieniaId"),
    @NamedQuery(name = "ProduktyHasZamowienia.findByIlosc", query = "SELECT p FROM ProduktyHasZamowienia p WHERE p.ilosc = :ilosc"),
    @NamedQuery(name = "ProduktyHasZamowienia.findByIloscZrealizowana", query = "SELECT p FROM ProduktyHasZamowienia p WHERE p.iloscZrealizowana = :iloscZrealizowana"),
    @NamedQuery(name = "ProduktyHasZamowienia.findByZrealizowanoProdukt", query = "SELECT p FROM ProduktyHasZamowienia p WHERE p.zrealizowanoProdukt = :zrealizowanoProdukt")})
public class ProduktyHasZamowienia implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProduktyHasZamowieniaPK produktyHasZamowieniaPK;
    @Column(name = "ilosc")
    private Integer ilosc;
    @Column(name = "ilosc_zrealizowana")
    private Integer iloscZrealizowana;
    @Column(name = "zrealizowano_produkt")
    private Boolean zrealizowanoProdukt;
    @JoinColumn(name = "user_id_fk", referencedColumnName = "user_id")
    @ManyToOne
    private User userIdFk;
    @JoinColumn(name = "zamowienia_zamowienia_id", referencedColumnName = "zamowienia_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Zamowienia zamowienia;
    @JoinColumn(name = "produkty_produkty_id", referencedColumnName = "produkty_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produkty produkty;

    public ProduktyHasZamowienia() {
    }

    public ProduktyHasZamowienia(ProduktyHasZamowieniaPK produktyHasZamowieniaPK) {
        this.produktyHasZamowieniaPK = produktyHasZamowieniaPK;
    }

    public ProduktyHasZamowienia(int produktyProduktyId, int zamowieniaZamowieniaId) {
        this.produktyHasZamowieniaPK = new ProduktyHasZamowieniaPK(produktyProduktyId, zamowieniaZamowieniaId);
    }

    public ProduktyHasZamowieniaPK getProduktyHasZamowieniaPK() {
        return produktyHasZamowieniaPK;
    }

    public void setProduktyHasZamowieniaPK(ProduktyHasZamowieniaPK produktyHasZamowieniaPK) {
        this.produktyHasZamowieniaPK = produktyHasZamowieniaPK;
    }

    public Integer getIlosc() {
        return ilosc;
    }

    public void setIlosc(Integer ilosc) {
        this.ilosc = ilosc;
    }

    public Integer getIloscZrealizowana() {
        return iloscZrealizowana;
    }

    public void setIloscZrealizowana(Integer iloscZrealizowana) {
        this.iloscZrealizowana = iloscZrealizowana;
    }

    public Boolean getZrealizowanoProdukt() {
        return zrealizowanoProdukt;
    }

    public void setZrealizowanoProdukt(Boolean zrealizowanoProdukt) {
        this.zrealizowanoProdukt = zrealizowanoProdukt;
    }

    public User getUserIdFk() {
        return userIdFk;
    }

    public void setUserIdFk(User userIdFk) {
        this.userIdFk = userIdFk;
    }

    public Zamowienia getZamowienia() {
        return zamowienia;
    }

    public void setZamowienia(Zamowienia zamowienia) {
        this.zamowienia = zamowienia;
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
        hash += (produktyHasZamowieniaPK != null ? produktyHasZamowieniaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProduktyHasZamowienia)) {
            return false;
        }
        ProduktyHasZamowienia other = (ProduktyHasZamowienia) object;
        if ((this.produktyHasZamowieniaPK == null && other.produktyHasZamowieniaPK != null) || (this.produktyHasZamowieniaPK != null && !this.produktyHasZamowieniaPK.equals(other.produktyHasZamowieniaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.atm.sklepssm.baza.ProduktyHasZamowienia[ produktyHasZamowieniaPK=" + produktyHasZamowieniaPK + " ]";
    }
    
}
