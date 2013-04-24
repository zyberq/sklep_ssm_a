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
@Table(name = "klient")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Klient.findAll", query = "SELECT k FROM Klient k"),
    @NamedQuery(name = "Klient.findByKlientId", query = "SELECT k FROM Klient k WHERE k.klientId = :klientId"),
    @NamedQuery(name = "Klient.findByImie", query = "SELECT k FROM Klient k WHERE k.imie = :imie"),
    @NamedQuery(name = "Klient.findByNazwisko", query = "SELECT k FROM Klient k WHERE k.nazwisko = :nazwisko"),
    @NamedQuery(name = "Klient.findByRabat", query = "SELECT k FROM Klient k WHERE k.rabat = :rabat"),
    @NamedQuery(name = "Klient.findByIloscPunktow", query = "SELECT k FROM Klient k WHERE k.iloscPunktow = :iloscPunktow"),
    @NamedQuery(name = "Klient.findByAdresIdFk", query = "SELECT k FROM Klient k WHERE k.adresIdFk = :adresIdFk")})
public class Klient implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "klient_id")
    private Integer klientId;
    @Size(max = 45)
    @Column(name = "imie")
    private String imie;
    @Size(max = 45)
    @Column(name = "nazwisko")
    private String nazwisko;
    @Column(name = "rabat")
    private Integer rabat;
    @Column(name = "ilosc_punktow")
    private Integer iloscPunktow;
    @Column(name = "adres_id_fk")
    private Integer adresIdFk;
    @OneToMany(mappedBy = "klientIdFk")
    private Collection<Transakcja> transakcjaCollection;
    @JoinColumn(name = "dane_firmy_id_fk", referencedColumnName = "dane_firmy_id")
    @ManyToOne
    private DaneFirmy daneFirmyIdFk;
    @JoinColumn(name = "user_id_fk", referencedColumnName = "user_id")
    @ManyToOne
    private User userIdFk;

    public Klient() {
    }

    public Klient(Integer klientId) {
        this.klientId = klientId;
    }

    public Integer getKlientId() {
        return klientId;
    }

    public void setKlientId(Integer klientId) {
        this.klientId = klientId;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Integer getRabat() {
        return rabat;
    }

    public void setRabat(Integer rabat) {
        this.rabat = rabat;
    }

    public Integer getIloscPunktow() {
        return iloscPunktow;
    }

    public void setIloscPunktow(Integer iloscPunktow) {
        this.iloscPunktow = iloscPunktow;
    }

    public Integer getAdresIdFk() {
        return adresIdFk;
    }

    public void setAdresIdFk(Integer adresIdFk) {
        this.adresIdFk = adresIdFk;
    }

    @XmlTransient
    public Collection<Transakcja> getTransakcjaCollection() {
        return transakcjaCollection;
    }

    public void setTransakcjaCollection(Collection<Transakcja> transakcjaCollection) {
        this.transakcjaCollection = transakcjaCollection;
    }

    public DaneFirmy getDaneFirmyIdFk() {
        return daneFirmyIdFk;
    }

    public void setDaneFirmyIdFk(DaneFirmy daneFirmyIdFk) {
        this.daneFirmyIdFk = daneFirmyIdFk;
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
        hash += (klientId != null ? klientId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Klient)) {
            return false;
        }
        Klient other = (Klient) object;
        if ((this.klientId == null && other.klientId != null) || (this.klientId != null && !this.klientId.equals(other.klientId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.atm.sklepssm.baza.Klient[ klientId=" + klientId + " ]";
    }
    
}
