
package com.atm.sklepssm.vaadin.ui.login;

/**
 *
 * @author Maciej_Paszkowski
 */
import com.atm.sklepssm.baza.domain.User;
import com.atm.sklepssm.vaadin.MyApplication;
import com.atm.sklepssm.vaadin.i18n.MyApplicationMessages;
import javax.persistence.EntityManager;
import javax.persistence.Query;



import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class RegistrationScreen extends VerticalLayout {
    private static final long serialVersionUID = 8308506505398889820L;
    
    MyApplication app;
    
    public RegistrationScreen () {
        app = MyApplication.getInstance();

        // The application caption is shown in the caption bar of the
        // browser window. We set it here and not in the application
        // init() to make switching language easier.
        app.getMainWindow().setCaption(app.getMessage(MyApplicationMessages.RegisterNewUser));
        
        setSizeFull();

        // Put the editor inside a centered panel
        Panel registrationPanel = new Panel("New User");
        registrationPanel.setWidth("400px");
        addComponent(registrationPanel);
        setComponentAlignment(registrationPanel, Alignment.MIDDLE_CENTER);

        // Use the user editor component also for registration
        final User newUser = new User();
        UserEditor editor = new UserEditor(newUser, true);
       
        editor.setListener(new UserEditor.UserEditorListener() {
            private static final long serialVersionUID = 3103809457310930956L;

            public void close(boolean ok) {
                if (ok) {
                    // Check that there is no existing user with the same name
                    EntityManager em = JPAContainerFactory.createEntityManagerForPersistenceUnit(MyApplication.PERSISTENCEUNIT);
                    em.getTransaction().begin();
                    Query q = em.createQuery("SELECT count(u) FROM User u WHERE u.username = '"+newUser.getUsername()+"'");
                    Object result = q.getSingleResult();
                    if (result != null && result instanceof Integer && ((Integer)result) == 1) {
                        getWindow().showNotification("User with the same name already exists");
                        return;
                    }
               //     em.getTransaction().begin();
                    em.persist(newUser);
                    em.getTransaction().commit();
                }
                
                // Go back to the login screen
                app.getViewManager().popScreen();
            }
        });
        
        registrationPanel.addComponent((Component) editor);
    }
} 