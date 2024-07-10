package com.cepeda.utiities;

import com.toedter.calendar.JDateChooser;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Pattern;
import javax.swing.JLabel;

/**
 *
 * @author VIAMATICA
 */
public class Validators {

    public static boolean validateLogin(String email, String password) {
        return !email.isEmpty() || !password.isEmpty();
    }

    public static boolean validateEmail(String email,
            JLabel lblEmailValidator) {
        String regexPattern = "^(.+)@(\\S+)$";
        var mather = Pattern.compile(regexPattern).matcher(email);
        if (mather.find()) {
            lblEmailValidator.setVisible(false);
            System.out.println("El email ingresado es válido.");
            return true;
        } else {
            System.out.println("El email ingresado es inválido.");
            lblEmailValidator.setVisible(true);
            return false;
        }
    }
    
    public static boolean validatePassword (String password,
            JLabel lblPasswordValidator){
        if(password.length()<8){
            lblPasswordValidator.setVisible(true);
            return false;
        }
        lblPasswordValidator.setVisible(false);
        return true;
    }
    
    public static boolean validateCodeTravel(String codeTravel){
        int  MAX_LENGTH_CODE = 7;
        if(codeTravel.length()<=MAX_LENGTH_CODE){
            return true;
        }
        return false;
    }
}
