/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.sklepssm.vaadin.ui.login;

import com.atm.sklepssm.vaadin.ui.Kategoria.HierarchicalKategorieContainer;
import com.atm.sklepssm.vaadin.ui.Kategoria.KategoriaView;
import com.atm.sklepssm.baza.domain.Kategorie;
import com.atm.sklepssm.baza.domain.Produkty;
import com.atm.sklepssm.baza.domain.User;
import com.atm.sklepssm.vaadin.MyApplication;
import com.atm.sklepssm.vaadin.i18n.MyApplicationMessages;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Select;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 *
 * @author Maciej_Paszkowski
 */
public class UserView extends HorizontalSplitPanel implements
        ComponentContainer  {

    private static final long serialVersionUID = 2245321552391782379L;
    private static final Logger logger = Logger.getLogger(UserView.class.getName());
    User user;
    Select kategorieSelect;
    JPAContainer<Kategorie> kategorieContainer;
    Button editKategorie;
    Button deleteCar;
    KategoriaView kategorieView;
    Panel kategoriePanel;
    MyApplication app;
    
    private Tree groupTree;

    private Table personTable;

    private TextField searchField;

    private Button newButton;
    private Button deleteButton;
    private Button editButton;

    private JPAContainer<Kategorie> kategories;
    private JPAContainer<Produkty> produkty;

    private Kategorie kategorieFilter;
    private String textFilter; 

    public UserView(final User user) {

        this.user = user;
        this.app = MyApplication.getInstance();

        ResourceBundle i18n = app.getBundle();

        


        HorizontalLayout kategorieBar = new HorizontalLayout();
        addComponent(kategorieBar);
        
        kategorieSelect = new Select(i18n.getString(MyApplicationMessages.Kategorie)); 

        
        kategories=new HierarchicalKategorieContainer();
        produkty=JPAContainerFactory.make(Produkty.class,
                MyApplication.PERSISTENCEUNIT); 
        
        
        
         buildTree();
        buildMainArea();

        setSplitPosition(30); 
        
    }

    private void buildTree() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void buildMainArea() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
