/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.sklepssm.aplikacja;

/**
 *
 * @author Maciej_Paszkowski
 */
public class Providers {

    private EjbEntityProvider daneFirmyProvider;
    private EjbEntityProvider dostawcyProvider;
    private EjbEntityProvider fakturyProvider;
    private EjbEntityProvider fakturyHasProduktyProvider;
    private EjbEntityProvider kategorieProvider;
    private EjbEntityProvider klientProvider;
    private EjbEntityProvider produktyProvider;
    private EjbEntityProvider produktyHasZamowieniaProvider;
    private EjbEntityProvider regalProvider;
    private EjbEntityProvider tranzakcjaProvider;
    private EjbEntityProvider transakcjaHasProduktyProvider;
    private EjbEntityProvider userProvider;
    private EjbEntityProvider zamowieniaProvider;

    public EjbEntityProvider getDaneFirmyProvider() {
        return daneFirmyProvider;
    }

    public void setDaneFirmyProvider(EjbEntityProvider daneFirmyProvider) {
        this.daneFirmyProvider = daneFirmyProvider;
    }

    public EjbEntityProvider getDostawcyProvider() {
        return dostawcyProvider;
    }

    public void setDostawcyProvider(EjbEntityProvider dostawcyProvider) {
        this.dostawcyProvider = dostawcyProvider;
    }

    public EjbEntityProvider getFakturyProvider() {
        return fakturyProvider;
    }

    public void setFakturyProvider(EjbEntityProvider fakturyProvider) {
        this.fakturyProvider = fakturyProvider;
    }

    public EjbEntityProvider getFakturyHasProduktyProvider() {
        return fakturyHasProduktyProvider;
    }

    public void setFakturyHasProduktyProvider(EjbEntityProvider fakturyHasProduktyProvider) {
        this.fakturyHasProduktyProvider = fakturyHasProduktyProvider;
    }

    public EjbEntityProvider getKategorieProvider() {
        return kategorieProvider;
    }

    public void setKategorieProvider(EjbEntityProvider kategorieProvider) {
        this.kategorieProvider = kategorieProvider;
    }

    public EjbEntityProvider getKlientProvider() {
        return klientProvider;
    }

    public void setKlientProvider(EjbEntityProvider klientProvider) {
        this.klientProvider = klientProvider;
    }

    public EjbEntityProvider getProduktyProvider() {
        return produktyProvider;
    }

    public void setProduktyProvider(EjbEntityProvider produktyProvider) {
        this.produktyProvider = produktyProvider;
    }

    public EjbEntityProvider getProduktyHasZamowieniaProvider() {
        return produktyHasZamowieniaProvider;
    }

    public void setProduktyHasZamowieniaProvider(EjbEntityProvider produktyHasZamowieniaProvider) {
        this.produktyHasZamowieniaProvider = produktyHasZamowieniaProvider;
    }

    public EjbEntityProvider getRegalProvider() {
        return regalProvider;
    }

    public void setRegalProvider(EjbEntityProvider regalProvider) {
        this.regalProvider = regalProvider;
    }

    public EjbEntityProvider getTranzakcjaProvider() {
        return tranzakcjaProvider;
    }

    public void setTranzakcjaProvider(EjbEntityProvider tranzakcjaProvider) {
        this.tranzakcjaProvider = tranzakcjaProvider;
    }

    public EjbEntityProvider getTransakcjaHasProduktyProvider() {
        return transakcjaHasProduktyProvider;
    }

    public void setTransakcjaHasProduktyProvider(EjbEntityProvider transakcjaHasProduktyProvider) {
        this.transakcjaHasProduktyProvider = transakcjaHasProduktyProvider;
    }

    public EjbEntityProvider getUserProvider() {
        return userProvider;
    }

    public void setUserProvider(EjbEntityProvider userProvider) {
        this.userProvider = userProvider;
    }

    public EjbEntityProvider getZamowieniaProvider() {
        return zamowieniaProvider;
    }

    public void setZamowieniaProvider(EjbEntityProvider zamowieniaProvider) {
        this.zamowieniaProvider = zamowieniaProvider;
    }

    public Providers() {
    }
    
    
}
