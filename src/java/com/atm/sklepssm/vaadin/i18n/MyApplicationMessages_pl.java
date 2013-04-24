/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.sklepssm.vaadin.i18n;

/**
 *
 * @author Maciej_Paszkowski
 */
public class MyApplicationMessages_pl extends MyApplicationMessages {
    private static final long serialVersionUID = -7071215163996244034L;

    @Override
    public Object[][] getContents() {
        return contents_en;
    }
    static final Object[][] contents_en = {
        {OkKey, "OK"},
        {CancelKey, "Cancel"},
        {Save, "Save"},
        {Reset, "Reset"},
    
        // Application
        {AppTitle, "Gas Diary"},
        
        // LoginScreen
        {Username, "Username"},
        {Password, "Password"},
        {Login, "Login"},
        {LoginButton, "Login"},
        {RegisterNewUser, "Register a new user"},
        {ForgotPassword, "Forgot your password?"},
        {InvalidUserOrPassword, "Invalid user or password"},
        {InvalidUserOrPasswordLong, "A user with the given user name does not exist or the password is incorrect"},
        {DemoUsernameHint, "Use demo/demo for a demonstration"},

        // UserEditor
        {UserNameError, "Invalid or missing username. Username may contain only lower-case alphabets, numbers, and underscore (_)."},
        {InvalidPasswordFormat, "Password can contain any letters except spaces and be 6-20 characters long."},
        {PasswordAgain, "Password Again"},
        {PasswordHint, "Password Hint"},
        {Email, "Email Address"},
        {InvalidEmail, "Invalid email address"},
        
        // UserView
        {MyCars, "My Cars"},
        {NewCar, "New Car..."},
        {EditCar, "Edit Car..."},
        {DeleteCar, "Delete Car"},
        {CarDetails, "Car Details"},
        
        // CarView
        {Fills, "Fills"},
        {NewFill, "New Fill"},
        {EditFill, "Edit Fill"},
        {DateTimeFormat, "yyyy/MM/dd HH:mm"},
        {DateShort, "Date"},
        {Station, "Station"},
        {AmountShort, "Amount"},
        {PartialFillShort, "Partial"},
        {TotalPriceShort, "Total"},
        {MeterShort, "Meter"},
        {TripMeterShort, "Trip"},
        {TripResetShort, "Reset?"},
        
        // CarEditor
        {RegisterNumber, "Register Number"},
        {RegisterNumberError, "Register must be given and be a valid register number: letters, dash, and numbers, for example, ABC-123"},
        {MustBeGiven, "Must be given, may not be empty"},
        {MustBeInteger, "Must be a proper year number"},
        {Manufacturer, "Manufacturer"},
        {Model, "Model"},
        {Year, "Year"},
        
        // FillEditor
        {DateAndTime, "Date and Time"},
        {NotADate, "Not a Date"},
        {TooOldDate, "Date is unrealistically old"},
        {DateMustBeGiven, "Date must be given, time is optional (is set to midnight)"},
        {StationMustBeGiven, "Station must be given"},
        {Amount, "Amount (l)"},
        {MustBeAmount, "Must be a positive amount ##.##, with period as decimal separator"},
        {AmountMustBeGiven, "Amount must be given"},
        {TotalPrice, "Total Price"},
        {MustBePrice, "Must be a price ##.## ¤, with period as decimal separator"},
        {TotalMustBeGiven, "Total price must be given"},
        {PartialFill, "Partial fill"},
        {Meter, "Meter reading"},
        {MustBeKmReading, "Must be a kilometer reading ######"},
        {TripMeter, "Trip meter reading"},
        {MustBeKmTripReading, "Must be a kilometer reading with at most one decimal ###.#"},
        {TripReset, "Was trip meter reset after filling?"},
        {Comment, "Comment"},
        {CommentPrompt, "Enter an optional comment"},
        
        // FillEditor: station editor
        {NewStation, "New Station"},
        {StationName, "Station Name"},
        {EmptyStationName, "Station name must not be empty"},
    };
} 
