/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.sklepssm.aplikacja;

import com.atm.sklepssm.vaadin.MyApplication;
import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.AbstractApplicationServlet;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Maciej_Paszkowski
 */
public class Servlet extends AbstractApplicationServlet {
    
    private Providers pro;
    
    @EJB
    private DaneFirmyEntityProvider daf;
    @EJB
    private DostawcyEntityProvider dos;
    
   
    @Override
    protected Application getNewApplication(HttpServletRequest request) throws ServletException {
        pro=new Providers();
        pro.setDaneFirmyProvider(daf);
        
        return new MyApplication(pro);
    }

    @Override
    protected Class<? extends Application> getApplicationClass() throws ClassNotFoundException {
        return MyApplication.class;
    }
}
