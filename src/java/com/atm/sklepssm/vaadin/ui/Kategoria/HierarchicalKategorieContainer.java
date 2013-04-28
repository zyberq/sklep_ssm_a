/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.sklepssm.vaadin.ui.Kategoria;

import com.atm.sklepssm.baza.domain.Kategorie;
import com.atm.sklepssm.vaadin.MyApplication;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.provider.CachingLocalEntityProvider;

/**
 *
 * @author Maciej_Paszkowski
 */
public class HierarchicalKategorieContainer extends JPAContainer<Kategorie> {

   public HierarchicalKategorieContainer() {
        super(Kategorie.class);
        setEntityProvider(new CachingLocalEntityProvider<Kategorie>(
                Kategorie.class,
                JPAContainerFactory
                        .createEntityManagerForPersistenceUnit(MyApplication.PERSISTENCEUNIT)));
        setParentProperty("parent");
    }

  //  @Override
  //  public boolean areChildrenAllowed(Object itemId) {
  //      return super.areChildrenAllowed(itemId)
  //              && getItem(itemId).getEntity().isSuperKategorie();
  //  }

} 

