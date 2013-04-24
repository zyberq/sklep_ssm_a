/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.sklepssm.baza.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Maciej_Paszkowski
 */
@Entity
@Table(name = "zamowienia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zamowienia.findAll", query = "SELECT z FROM Zamowienia z"),
    @NamedQuery(name = "Zamowienia.findByZamowieniaId", query = "SELECT z FROM Zamowienia z WHERE z.zamowieniaId = :zamowieniaId"),
    @NamedQuery(name = "Zamowienia.findByZrealizowane", query = "SELECT z FROM Zamowienia z WHERE z.zrealizowane = :zrealizowane"),
    @NamedQuery(name = "Zamowienia.findByDataZamowienia", query = "SELECT z FROM Zamowienia z WHERE z.dataZamowienia = :dataZamowienia"),
    @NamedQuery(name = "Zamowienia.findByDataRealizacji", query = "SELECT z FROM Zamowienia z WHERE z.dataRealizacji = :dataRealizacji")})
public class Zamowienia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "zamowienia_id")
    private Integer zamowieniaId;
    @Column(name = "zrealizowane")
    private Boolean zrealizowane;
    @Column(name = "data_zamowienia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataZamowienia;
    @Column(name = "data_realizacji")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRealizacji;
    @JoinColumn(name = "dostawca_id_fk", referencedColumnName = "dostawcy_id")
    @ManyToOne
    private Dostawcy dostawcaIdFk;
    @JoinColumn(name = "user_id_fk", referencedColumnName = "user_id")
    @ManyToOne
    private User userIdFk;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zamowienia")
    private Collection<ProduktyHasZamowienia> produktyHasZamowieniaCollection;

    public Zamowienia() {
    }

    public Zamowienia(Integer zamowieniaId) {
        this.zamowieniaId = zamowieniaId;
    }

    public Integer getZamowieniaId() {
        return zamowieniaId;
    }

    public void setZamowieniaId(Integer zamowieniaId) {
        this.zamowieniaId = zamowieniaId;
    }

    public Boolean getZrealizowane() {
        return zrealizowane;
    }

    public void setZrealizowane(Boolean zrealizowane) {
        this.zrealizowane = zrealizowane;
    }

    public Date getDataZamowienia() {
        return dataZamowienia;
    }

    public void setDataZamowienia(Date dataZamowienia) {
        this.dataZamowienia = dataZamowienia;
    }

    public Date getDataRealizacji() {
        return dataRealizacji;
    }

    public void setDataRealizacji(Date dataRealizacji) {
        this.dataRealizacji = dataRealizacji;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (zamowieniaId != null ? zamowieniaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zamowienia)) {
            return false;
        }
        Zamowienia other = (Zamowienia) object;
        if ((this.zamowieniaId == null && other.zamowieniaId != null) || (this.zamowieniaId != null && !this.zamowieniaId.equals(other.zamowieniaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.atm.sklepssm.baza.Zamowienia[ zamowieniaId=" + zamowieniaId + " ]";
    }
    
}
