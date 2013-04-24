/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.sklepssm.vaadin.i18n;

/**
 *
 * @author Maciej_Paszkowski
 */
import java.io.Serializable;
import java.util.ListResourceBundle;

public class MyApplicationMessages extends ListResourceBundle implements Serializable {
    private static final long serialVersionUID = -1381196948880320212L;

    public static final String OkKey = generateId(); 
    public static final String CancelKey = generateId();
    public static final String Save = generateId(); 
    public static final String Reset = generateId(); 
    
    // Application
    public static final String AppTitle = generateId();
    
    // LoginScreen
    public static final String Username = generateId();
    public static final String Password = generateId();
    public static final String Login = generateId();
    public static final String LoginButton = generateId();
    public static final String RegisterNewUser = generateId();
    public static final String ForgotPassword = generateId();
    public static final String InvalidUserOrPassword = generateId();
    public static final String InvalidUserOrPasswordLong = generateId();
    public static final String DemoUsernameHint = generateId();
    
    // UserEditor
    public static final String UserNameError = generateId();
    public static final String InvalidPasswordFormat = generateId();
    public static final String PasswordAgain = generateId();
    public static final String PasswordHint = generateId();
    public static final String Email = generateId();
    public static final String InvalidEmail = generateId();
    
    // UserView
    public static final String MyCars = generateId();
    public static final String EditCar = generateId();
    public static final String NewCar = generateId();
    public static final String DeleteCar = generateId();
    public static final String CarDetails = generateId();
    
    // CarView
    public static final String Fills = generateId();
    public static final String NewFill = generateId();
    public static final String EditFill = generateId();
    public static final String DateTimeFormat = generateId();
    public static final String DateShort = generateId();
    public static final String Station = generateId();
    public static final String AmountShort = generateId();
    public static final String PartialFillShort = generateId();
    public static final String TotalPriceShort = generateId();
    public static final String MeterShort = generateId();
    public static final String TripMeterShort = generateId();
    public static final String TripResetShort = generateId();
    
    // CarEditor 
    public static final String RegisterNumber = generateId();
    public static final String RegisterNumberError = generateId();
    public static final String MustBeGiven = generateId();
    public static final String MustBeInteger = generateId();
    public static final String Manufacturer = generateId();
    public static final String Model = generateId();
    public static final String Year = generateId();
    
    // FillEditor
    public static final String DateAndTime = generateId();
    public static final String NotADate = generateId();
    public static final String TooOldDate = generateId();
    public static final String DateMustBeGiven = generateId();
    public static final String StationMustBeGiven = generateId();
    public static final String Amount = generateId();
    public static final String MustBeAmount = generateId();
    public static final String AmountMustBeGiven = generateId();
    public static final String TotalPrice = generateId();
    public static final String MustBePrice = generateId();
    public static final String TotalMustBeGiven = generateId();
    public static final String PartialFill = generateId();
    public static final String Meter = generateId();
    public static final String MustBeKmReading = generateId();
    public static final String TripMeter = generateId();
    public static final String MustBeKmTripReading = generateId();
    public static final String TripReset = generateId();
    public static final String Comment = generateId();
    public static final String CommentPrompt = generateId();
    
    // FillEditor: station editor
    public static final String NewStation = generateId();
    public static final String StationName = generateId();
    public static final String EmptyStationName = generateId();
    
    public static String generateId() {
        return new Integer(ids++).toString();
    }
    
    static int ids = 0;

    @Override
    public Object[][] getContents() {
        return null;
    }
} 
