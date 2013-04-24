/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.sklepssm.baza.facade;

import com.atm.sklepssm.baza.domain.Kategorie;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Maciej_Paszkowski
 */
@Stateless
public class KategorieFacade extends AbstractFacade<Kategorie> {
    @PersistenceContext(unitName = "sklep_ssm_baza")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public KategorieFacade() {
        super(Kategorie.class);
    }
    
}
