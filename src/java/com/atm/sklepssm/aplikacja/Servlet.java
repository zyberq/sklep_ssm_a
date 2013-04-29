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
    
    @EJB
    private DaneFirmyEntityProvider daf;
    private DostawcyEntityProvider dos;
    
    @Override
    protected Application getNewApplication(HttpServletRequest request) throws ServletException {
        return new MyApplication(daf,dos);
    }

    @Override
    protected Class<? extends Application> getApplicationClass() throws ClassNotFoundException {
        return MyApplication.class;
    }
}
