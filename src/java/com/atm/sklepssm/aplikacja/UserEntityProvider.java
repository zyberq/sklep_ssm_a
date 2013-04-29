/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.sklepssm.aplikacja;

import com.atm.sklepssm.baza.domain.User;



/**
 *
 * @author Maciej_Paszkowski
 */
public class UserEntityProvider extends EjbEntityProvider{
 
    
      public UserEntityProvider() {
        super(User.class);
    }
    
}

