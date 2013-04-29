/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.sklepssm.aplikacja;

import com.vaadin.addon.jpacontainer.provider.MutableLocalEntityProvider;
import javax.annotation.PostConstruct;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Maciej_Paszkowski
 */

@TransactionManagement
public abstract class EjbEntityProvider <T> extends MutableLocalEntityProvider<T> {

    @PersistenceContext
    private EntityManager em;


    protected EjbEntityProvider(Class entityClass) {
        super(entityClass);
    }
 
    @PostConstruct
    public void init() {
        setTransactionsHandledByProvider(false);
        setEntityManager(em);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    protected void runInTransaction(Runnable operation) {
        super.runInTransaction(operation);
    }

}
