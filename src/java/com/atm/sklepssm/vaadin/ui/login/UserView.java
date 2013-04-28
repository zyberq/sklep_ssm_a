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
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.ui.Alignment;
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
        ComponentContainer {

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
    private Table produktTable;
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


        kategories = new HierarchicalKategorieContainer();
        produkty = JPAContainerFactory.make(Produkty.class,
                MyApplication.PERSISTENCEUNIT);



        buildTree();
        //buildMainArea();

        setSplitPosition(30);

    }

    private void buildTree() {
        groupTree = new Tree(null, kategories);
        groupTree.setItemCaptionPropertyId("name");

        groupTree.setImmediate(true);
        groupTree.setSelectable(true);
        groupTree.addListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                Object id = event.getProperty().getValue();
                if (id != null) {
                    Kategorie entity = kategories.getItem(id).getEntity();
                    kategorieFilter = entity;
                } else if (kategorieFilter != null) {
                    kategorieFilter = null;
                }
                //            updateFilters();

                setFirstComponent(groupTree);
            }

            private void updateFilters() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

    }

    private void buildMainArea() {
        VerticalLayout verticalLayout = new VerticalLayout();
        setSecondComponent(verticalLayout);

        produktTable = new Table(null, produkty);
        produktTable.setSelectable(true);
        produktTable.setImmediate(true);
        produktTable.addListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                setModificationsEnabled(event.getProperty().getValue() != null);
            }

            private void setModificationsEnabled(boolean b) {
                deleteButton.setEnabled(b);
                editButton.setEnabled(b);
            }
        });

        produktTable.setSizeFull();
        // personTable.setSelectable(true);
        produktTable.addListener(new ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                if (event.isDoubleClick()) {
                    produktTable.select(event.getItemId());
                }
            }
        });

        produktTable.setVisibleColumns(new Object[]{"firstName", "lastName",
            "department", "phoneNumber", "street", "city", "zipCode"});

        HorizontalLayout toolbar = new HorizontalLayout();
        newButton = new Button("Add");
        newButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                final BeanItem<Produkty> newProduktItem = new BeanItem<Produkty>(
                        new Produkty());
                ProduktEditor produktEditor = new ProduktEditor(newProduktItem);
                produktEditor.addListener(new EditorSavedListener() {
                    public void editorSaved(EditorSavedEvent event) {
                        produkty.addEntity(newProduktItem.getBean());
                    }
                });
              getApplication().getMainWindow().addWindow(produktEditor);
            }

           
        });

        deleteButton = new Button("Delete");
        deleteButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                produkty.removeItem(produktTable.getValue());
            }

           
        });
        deleteButton.setEnabled(false);

        editButton = new Button("Edit");
        editButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                getApplication().getMainWindow().addWindow(
                        new ProduktEditor(produktTable.getItem(produktTable
                        .getValue())));
            }
        });
        editButton.setEnabled(false);

        searchField = new TextField();
        searchField.setInputPrompt("Search by name");
        searchField.addListener(new TextChangeListener() {
            @Override
            public void textChange(FieldEvents.TextChangeEvent event) {
                textFilter = event.getText();
                //updateFilters();
            }

           
        });

        toolbar.addComponent(newButton);
        toolbar.addComponent(deleteButton);
        toolbar.addComponent(editButton);
        toolbar.addComponent(searchField);
        toolbar.setWidth("100%");
        toolbar.setExpandRatio(searchField, 1);
        toolbar.setComponentAlignment(searchField, Alignment.TOP_RIGHT);

        verticalLayout.addComponent(toolbar);
        verticalLayout.addComponent(produktTable);
        verticalLayout.setExpandRatio(produktTable, 1);
        verticalLayout.setSizeFull();


    }
}
