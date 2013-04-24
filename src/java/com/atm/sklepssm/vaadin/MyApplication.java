/*
 * MyApplication.java
 *
 * Created on 24 kwiecień 2013, 20:17
 */
 
package com.atm.sklepssm.vaadin;           

import com.atm.sklepssm.baza.domain.User;
import com.atm.sklepssm.vaadin.i18n.MyApplicationMessages;
import com.atm.sklepssm.vaadin.ui.login.LoginScreen;
import com.atm.sklepssm.vaadin.ui.viewmanager.ViewManager;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.vaadin.Application;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.provider.CachingMutableLocalEntityProvider;
import com.vaadin.terminal.gwt.server.HttpServletRequestListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Window;
import java.util.logging.Level;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MyApplication  extends Application implements HttpServletRequestListener {
    private static final long serialVersionUID = 7177458298849973108L;
    private static final Logger logger = Logger.getLogger(MyApplication.class.getName());

    /* JPA persistence unit name for the application data */
    public static final String PERSISTENCEUNIT = "sklep_ssm_baza";
  //  public static final String AppTitle="Sklep SSM";
  //  public static final String RegisterNewUser="Rejestruj użytkownika";
  //  public static final String Login="";
  // public static final String Username="";
  //  public static final String Password="";
  //  public static final String DemoUsernameHint="";
  //  public static final String ForgotPassword="";
  //  public static String InvalidUserOrPasswordLong="";
  //  public static String InvalidUserOrPassword="";
    
    /* Currently logged in user. */
    User   user;

    /* View manager that handlers different screens in the UI. */
    ViewManager viewManager;
    
    /* Internationalized strings. */
    ResourceBundle i18nBundle;

    Window mainWindow;
    
    private static ThreadLocal<MyApplication> instance =
        new ThreadLocal<MyApplication>();
    
    public MyApplication() {
        instance.set(this);
    }
    
    @Override
    public void init() {
        setTheme("gasdiary");
        
        createDemoDataIfEmptyDatabase();

        // Initialize the view manager for the main window
        final ResourceBundle i18n = ResourceBundle.getBundle(MyApplicationMessages.class.getName(), getLocale());
        mainWindow = new Window(i18n.getString(MyApplicationMessages.AppTitle));
        setMainWindow(mainWindow);
        
        viewManager = new ViewManager(mainWindow);
        
        // Create the login screen
        viewManager.switchScreen(LoginScreen.class.getName(), (Layout) new LoginScreen());
    }
    
    @Override
    public void setLocale(Locale locale) {
        super.setLocale(locale);
        i18nBundle = ResourceBundle.getBundle(MyApplicationMessages.class.getName(), getLocale());
    }
    
    /** Returns the bundle for the current locale. */
    public ResourceBundle getBundle() {
        return i18nBundle;
    }
 
    /**
     * Returns a localized message from the resource bundle
     * with the current application locale.
     **/ 
    public String getMessage(String key) {
        return i18nBundle.getString(key);
    }

    /** Gets the user of this session. */
    public User getAppUser() {
        return user;
    }
    
    /** Sets the user of this session. */
    public void setAppUser(User user) {
        this.user = user;
    }
    
    public ViewManager getViewManager() {
        return viewManager;
    }
    
    @Override
    public void onRequestStart(HttpServletRequest request,
            HttpServletResponse response) {
        instance.set(this);
    }

    @Override
    public void onRequestEnd(HttpServletRequest request,
            HttpServletResponse response) {
        instance.remove();
    }
    
    public static MyApplication getInstance() {
        return instance.get();
    }
    
    void createDemoDataIfEmptyDatabase() {
        //EntityManager em= 
      //  EntityManagerFactory emf = new JPAContainerFactory();
        EntityManagerFactory emf= Persistence.createEntityManagerFactory(MyApplication.PERSISTENCEUNIT);        
                //.createEntityManagerForPersistenceUnit(MyApplication.PERSISTENCEUNIT);
        
        EntityManager em=emf.createEntityManager();
        
        CachingMutableLocalEntityProvider<User> entityProvider =
    new CachingMutableLocalEntityProvider<User>(User.class,
                                                  em);
        
        
        JPAContainer<User> users =
        new JPAContainer<User> (User.class);
users.setEntityProvider(entityProvider);
        
        em.getTransaction().begin();
        Query q = em.createQuery("SELECT COUNT(u) FROM User u");
        Object result = q.getSingleResult();
        if (result == null) {
            logger.severe("Count result null while trying to get the number of existing users");
            return;
        }
        if (! (result instanceof Long)) {
            logger.log(Level.SEVERE, "Result type is not long integer: {0}", result.getClass().getName());
            return;
        }
        
        if (((Long)result) == 0) {
            logger.warning("Empty database. Creating demo data.");
            User demoUser = new User();
            demoUser.setLogin("demo");
            demoUser.setPassword("demo");
            demoUser.setImie("Klient");
            demoUser.setNazwisko("Demonstracyjny");
            demoUser.setStatus("aktywny");
            demoUser.setUprawnienia(1);
            
            
            
        
            
        

            
           
        
            em.persist(demoUser);
            //em.persist(demoCar);
            em.getTransaction().commit();
        } else
            logger.info("Demo data exists. Users: " + result);
    }
} 