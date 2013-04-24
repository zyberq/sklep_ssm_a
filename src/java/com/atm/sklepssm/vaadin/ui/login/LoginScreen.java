/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.sklepssm.vaadin.ui.login;

import com.atm.sklepssm.baza.domain.User;
import com.atm.sklepssm.vaadin.MyApplication;
import com.atm.sklepssm.vaadin.i18n.MyApplicationMessages;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.LoginForm.LoginEvent;
import com.vaadin.ui.LoginForm.LoginListener;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.Notification;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Maciej_Paszkowski
 */
public class LoginScreen extends VerticalLayout implements LoginListener {
    private static final long serialVersionUID = 8308506505398889820L;
    private static final Logger logger = Logger.getLogger(LoginScreen.class.getName());
    
    MyApplication app;
    
    @SuppressWarnings("empty-statement")
    public LoginScreen () {
        app = MyApplication.getInstance();

        // The application caption is shown in the caption bar of the
        // browser window. We set it here and not in the application
        // init() to make switching language easier.
        app.getMainWindow().setCaption(app.getMessage(MyApplicationMessages.AppTitle));
        
        setSizeFull();
        
        // Language bar in the top-right corner for selecting
        // user interface language
        final HorizontalLayout languageBar = new HorizontalLayout();
        languageBar.setHeight("50px");
        addComponent(languageBar);
        setComponentAlignment(languageBar, Alignment.TOP_RIGHT);

        // Click listener to switch the application locale
        class SwitchLanguage implements Button.ClickListener {
            private static final long serialVersionUID = 7382925259045032373L;

            public void buttonClick(Button.ClickEvent event) {
                // Switch the application locale
                if (app.getLocale().getLanguage().equals("pl"))
                    app.setLocale(new Locale("en"));
                else
                    app.setLocale(new Locale("pl"));

                // Switch to a new instance of the login screen to
                // rebuild it with the new locale.
                app.getViewManager().switchScreen(LoginScreen.class.getName(),
                                                  new LoginScreen());
            }

          
        };

        // Finnish language selection button
        Button polski = new Button("Po polsku");
        polski.setIcon(new ThemeResource("img/fi.gif"));
        polski.addListener(new SwitchLanguage());
        polski.setEnabled(! app.getLocale().getLanguage().equals("pl"));
        languageBar.addComponent(polski);
        
        // English language selection button
        Button english = new Button("In English");
        english.setIcon(new ThemeResource("img/gb.gif"));
        english.addListener(new SwitchLanguage());
        english.setEnabled(! app.getLocale().getLanguage().equals("en"));
        languageBar.addComponent(english);
        
        Panel loginPanel = new Panel(app.getMessage(MyApplicationMessages.Login));
        loginPanel.setWidth("400px");
        
        LoginForm loginForm = new LoginForm();
        loginForm.setUsernameCaption(app.getMessage(MyApplicationMessages.Username));
        loginForm.setPasswordCaption(app.getMessage(MyApplicationMessages.Password));
        
        loginForm.addListener((LoginForm.LoginListener) this);
        loginForm.setHeight("150px");
        loginPanel.addComponent(loginForm);
        
        loginPanel.addComponent(new Label(app.getMessage(MyApplicationMessages.DemoUsernameHint)));
        
        Button register;
        register = new Button(app.getMessage(MyApplicationMessages.RegisterNewUser));
        register.setStyleName("link");
        register.addListener(new Button.ClickListener() {
            private static final long serialVersionUID = 6933775337848167006L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                registerNewUser();
            }
        });
        loginPanel.addComponent(register);
        
        Button forgot = new Button(app.getMessage(MyApplicationMessages.ForgotPassword));
        forgot.setStyleName("link");
        loginPanel.addComponent(forgot);
        
        addComponent(loginPanel);
        setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
        
        HorizontalLayout footer = new HorizontalLayout();
        footer.setHeight("50px");
        addComponent(footer);
    }

    @Override
        public void onLogin(LoginEvent event) {
        String username = event.getLoginParameter("username");
        String password = event.getLoginParameter("password");

        EntityManager em = JPAContainerFactory.createEntityManagerForPersistenceUnit(MyApplication.PERSISTENCEUNIT);
        
        // Try to find the user
        Query q = em.createQuery("SELECT u FROM User u WHERE u.username = '"+username+"'");
        @SuppressWarnings("unchecked")
        List<User> users = q.getResultList();
        if (users.size() != 1) {
            /*
            // User did not exist, create it
            // TODO: replace with registration
            logger.info("Creating new user automatically: " + username + "/" + password);
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            em.persist(user);
            GasDiaryApplication.getInstance().setAppUser(user);
            
            logger.warning("New user automatically created: " + username);
            getWindow().showNotification("New User", Notification.TYPE_WARNING_MESSAGE);
            */

            logger.warning("User not found: " + username);
            showInvalidPassword();
            return;
        } else {
    
            // User found, authenticate
            User existing = users.get(0);
            if (existing.getPassword().equals(password))
                app.setAppUser(existing);
            else {
                logger.warning("Invalid password for user " + username + ": " + password);
                showInvalidPassword();
                return;
            }
        }

        if (app.getAppUser() == null)
            throw new NullPointerException("User must not be null");

        // Switch to the main view: the user view.
        app.getViewManager().switchScreen(UserView.class.getName(), (Layout) new UserView(app.getAppUser()));
    }

    void showInvalidPassword() {
        getWindow().showNotification(app.getMessage(MyApplicationMessages.InvalidUserOrPassword),
                "<br/>" + app.getMessage(MyApplicationMessages.InvalidUserOrPasswordLong),
                Notification.TYPE_ERROR_MESSAGE);
    }    
    
    void registerNewUser() {
        // Switch to the registration screen
        app.getViewManager().pushScreen(RegistrationScreen.class.getName(),
                new RegistrationScreen());
    }
} 
