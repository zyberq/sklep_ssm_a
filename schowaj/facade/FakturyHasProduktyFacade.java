/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.sklepssm.baza.facade;

import com.atm.sklepssm.baza.domain.FakturyHasProdukty;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Maciej_Paszkowski
 */
@Stateless
public class FakturyHasProduktyFacade extends AbstractFacade<FakturyHasProdukty> {
    @PersistenceContext(unitName = "sklep_ssm_baza")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FakturyHasProduktyFacade() {
        super(FakturyHasProdukty.class);
    }
    
}
