/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.sklepssm.baza.facade;

import com.atm.sklepssm.baza.domain.TransakcjaHasProdukty;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Maciej_Paszkowski
 */
@Stateless
public class TransakcjaHasProduktyFacade extends AbstractFacade<TransakcjaHasProdukty> {
    @PersistenceContext(unitName = "sklep_ssm_baza")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TransakcjaHasProduktyFacade() {
        super(TransakcjaHasProdukty.class);
    }
    
}
