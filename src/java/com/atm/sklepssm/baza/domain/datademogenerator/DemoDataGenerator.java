/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.sklepssm.baza.domain.datademogenerator;

import com.atm.sklepssm.baza.domain.DaneFirmy;
import com.atm.sklepssm.baza.domain.User;
import com.atm.sklepssm.vaadin.MyApplication;
import com.vaadin.addon.jpacontainer.provider.CachingMutableLocalEntityProvider;
import com.vaadin.addon.jpacontainer.provider.jndijta.JndiAddresses;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author Maciej_Paszkowski
 */
public class DemoDataGenerator {

    private static String nazwa_firmy;
    final static String[] nazwa1 = {"Czerwona", "Zielona", "Niebieska", "Żółta", "Pomarańczowa"};
    final static String[] nazwa2 = {"Graficzna", "Animacyjna", "Programowa", "Administracyjna", "Porzątkowa", "Kamerowa"};
    final static String[] nazwa3 = {"Grupa", "Firma", "Korporacja", "Zołza", "Małpa", "Telewizja", "Policja", "Szmalcownia"};
    final static String miasta[] = {"Gdynia", "Gdańsk", "Sopot",
        "Słupsk", "Elbląg", "Bydgoszcz", "Toruń", "Władysławowo", "Wejherowo",
        "Hel", "Łeba", "Lębork", "Kartuzy"};
    final static String ulice[] = {"Batorego", "Stoczniowców",
        "Kołobrzeska", "Dzielna", "Grunwaldzka",
        "Działdowska", "Armii Krajowej",
        "Hiszpańska", "Niemiecka",
        "Rosyjska", "Polska", "Morska",
        "29 Lutego", "Kopernika",
        "Newtona", "Leona Zawodowca",
        "Obi Wan Kenobiego", "Vadera",
        "Chłopska", "Opolska",
        "Olsztyńska"};

    public static void create() throws NamingException, NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException {

          EntityManager em = Persistence.createEntityManagerFactory(MyApplication.PERSISTENCEUNIT).createEntityManager();

        InitialContext initialContext = new InitialContext();
        //EntityManager em = (EntityManager) initialContext.lookup(JndiAddresses.DEFAULTS.getEntityManagerName());
        //UserTransaction utx;
        //utx = (UserTransaction) (new InitialContext()).lookup(JndiAddresses.DEFAULTS.getUserTransactionName());

        //utx.begin();
         
        CachingMutableLocalEntityProvider<User> entityProvider =
                new CachingMutableLocalEntityProvider<User>(User.class,
                em);
        CachingMutableLocalEntityProvider<DaneFirmy> entityProvider2 =
                new CachingMutableLocalEntityProvider<DaneFirmy>(DaneFirmy.class,
                em);
        em.getTransaction().begin();
        Random r = new Random(0);
        //Set<DaneFirmy> gDane = new HashSet<DaneFirmy>();
        for (String o : nazwa1) {
            for (String oo : nazwa2) {
                for (String ooo : nazwa3) {
                    nazwa_firmy = o + " " + oo + " " + ooo;
                    DaneFirmy df = new DaneFirmy();
                    df.setNazwaFirmy(nazwa_firmy);
                    int losowa = r.nextInt(100);
                    String numer_domu = new Integer(losowa).toString();
                    df.setNumerDomu(numer_domu);
                    losowa = r.nextInt(miasta.length);
                    df.setMiasto(miasta[losowa]);
                    losowa = r.nextInt(ulice.length);
                    df.setUlica(ulice[losowa]);
                    em.persist(df);
                }
            }
            User demoUser = new User();
            demoUser.setLogin("demo");
            demoUser.setPassword("demo");
            demoUser.setImie("Klient");
            demoUser.setNazwisko("Demonstracyjny");
            demoUser.setStatus("aktywny");
            demoUser.setUprawnienia(1);

            em.persist(demoUser);
            demoUser = new User();

            demoUser.setLogin("maciej");
            demoUser.setPassword("maciej");
            demoUser.setImie("Maciej");
            demoUser.setNazwisko("Paszkowski");
            demoUser.setStatus("aktywny");
            demoUser.setUprawnienia(1);

            em.persist(demoUser);

            em.getTransaction().commit();
          //  utx.commit();
        }
    }
}