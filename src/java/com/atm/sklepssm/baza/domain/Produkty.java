/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.sklepssm.baza.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Maciej_Paszkowski
 */
@Entity
@Table(name = "produkty")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produkty.findAll", query = "SELECT p FROM Produkty p"),
    @NamedQuery(name = "Produkty.findByProduktyId", query = "SELECT p FROM Produkty p WHERE p.produktyId = :produktyId"),
    @NamedQuery(name = "Produkty.findByKategorieIdFk", query = "SELECT p FROM Produkty p WHERE p.kategorieIdFk = :kategorieIdFk"),
    @NamedQuery(name = "Produkty.findByNazwa", query = "SELECT p FROM Produkty p WHERE p.nazwa = :nazwa"),
    @NamedQuery(name = "Produkty.findByCena", query = "SELECT p FROM Produkty p WHERE p.cena = :cena"),
    @NamedQuery(name = "Produkty.findByVat", query = "SELECT p FROM Produkty p WHERE p.vat = :vat"),
    @NamedQuery(name = "Produkty.findByRabatKwotowy", query = "SELECT p FROM Produkty p WHERE p.rabatKwotowy = :rabatKwotowy"),
    @NamedQuery(name = "Produkty.findByRabatProcentowy", query = "SELECT p FROM Produkty p WHERE p.rabatProcentowy = :rabatProcentowy"),
    @NamedQuery(name = "Produkty.findByKodProduktu", query = "SELECT p FROM Produkty p WHERE p.kodProduktu = :kodProduktu")})
public class Produkty implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "produkty_id")
    private Integer produktyId;
    @Column(name = "kategorie_id_fk")
    private Integer kategorieIdFk;
    @Size(max = 45)
    @Column(name = "nazwa")
    private String nazwa;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cena")
    private BigDecimal cena;
    @Size(max = 2)
    @Column(name = "vat")
    private String vat;
    @Column(name = "rabat_kwotowy")
    private BigDecimal rabatKwotowy;
    @Column(name = "rabat_procentowy")
    private Integer rabatProcentowy;
    @Size(max = 45)
    @Column(name = "kod_produktu")
    private String kodProduktu;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produkty")
    private Collection<ProduktyHasKategorie> produktyHasKategorieCollection;
    @JoinColumn(name = "regal_id_fk", referencedColumnName = "regal_id")
    @ManyToOne
    private Regal regalIdFk;
    @JoinColumn(name = "dostawca_id_fk", referencedColumnName = "dostawcy_id")
    @ManyToOne
    private Dostawcy dostawcaIdFk;
    @JoinColumn(name = "user_id_fk", referencedColumnName = "user_id")
    @ManyToOne
    private User userIdFk;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produkty")
    private Collection<ProduktyHasZamowienia> produktyHasZamowieniaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produkty")
    private Collection<FakturyHasProdukty> fakturyHasProduktyCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produkty")
    private Collection<TransakcjaHasProdukty> transakcjaHasProduktyCollection;

    public Produkty() {
    }

    public Produkty(Integer produktyId) {
        this.produktyId = produktyId;
    }

    public Integer getProduktyId() {
        return produktyId;
    }

    public void setProduktyId(Integer produktyId) {
        this.produktyId = produktyId;
    }

    public Integer getKategorieIdFk() {
        return kategorieIdFk;
    }

    public void setKategorieIdFk(Integer kategorieIdFk) {
        this.kategorieIdFk = kategorieIdFk;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public BigDecimal getRabatKwotowy() {
        return rabatKwotowy;
    }

    public void setRabatKwotowy(BigDecimal rabatKwotowy) {
        this.rabatKwotowy = rabatKwotowy;
    }

    public Integer getRabatProcentowy() {
        return rabatProcentowy;
    }

    public void setRabatProcentowy(Integer rabatProcentowy) {
        this.rabatProcentowy = rabatProcentowy;
    }

    public String getKodProduktu() {
        return kodProduktu;
    }

    public void setKodProduktu(String kodProduktu) {
        this.kodProduktu = kodProduktu;
    }

    @XmlTransient
    public Collection<ProduktyHasKategorie> getProduktyHasKategorieCollection() {
        return produktyHasKategorieCollection;
    }

    public void setProduktyHasKategorieCollection(Collection<ProduktyHasKategorie> produktyHasKategorieCollection) {
        this.produktyHasKategorieCollection = produktyHasKategorieCollection;
    }

    public Regal getRegalIdFk() {
        return regalIdFk;
    }

    public void setRegalIdFk(Regal regalIdFk) {
        this.regalIdFk = regalIdFk;
    }

    public Dostawcy getDostawcaIdFk() {
        return dostawcaIdFk;
    }

    public void setDostawcaIdFk(Dostawcy dostawcaIdFk) {
        this.dostawcaIdFk = dostawcaIdFk;
    }

    public User getUserIdFk() {
        return userIdFk;
    }

    public void setUserIdFk(User userIdFk) {
        this.userIdFk = userIdFk;
    }

    @XmlTransient
    public Collection<ProduktyHasZamowienia> getProduktyHasZamowieniaCollection() {
        return produktyHasZamowieniaCollection;
    }

    public void setProduktyHasZamowieniaCollection(Collection<ProduktyHasZamowienia> produktyHasZamowieniaCollection) {
        this.produktyHasZamowieniaCollection = produktyHasZamowieniaCollection;
    }

    @XmlTransient
    public Collection<FakturyHasProdukty> getFakturyHasProduktyCollection() {
        return fakturyHasProduktyCollection;
    }

    public void setFakturyHasProduktyCollection(Collection<FakturyHasProdukty> fakturyHasProduktyCollection) {
        this.fakturyHasProduktyCollection = fakturyHasProduktyCollection;
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
        hash += (produktyId != null ? produktyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produkty)) {
            return false;
        }
        Produkty other = (Produkty) object;
        if ((this.produktyId == null && other.produktyId != null) || (this.produktyId != null && !this.produktyId.equals(other.produktyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.atm.sklepssm.baza.Produkty[ produktyId=" + produktyId + " ]";
    }
    
}
