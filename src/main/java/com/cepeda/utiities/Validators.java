package com.cepeda.utiities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author VIAMATICA
 */
public class Validators {

    public static boolean validateLogin(String email, String password) {
        return !email.isEmpty() || !password.isEmpty();
    }

    public static boolean validateEmail(String email, JLabel lblEmailValidator) {
        String regexPattern = "^(.+)@(\\S+)$";
        var mather = Pattern.compile(regexPattern).matcher(email);
        if (mather.find()) {
            System.out.println("El email ingresado es válido.");
            return true;
        } else {
            System.out.println("El email ingresado es inválido.");
            lblEmailValidator.setVisible(true);
            return false;
        }
    }
    
    public static boolean validatePassword (String password, JLabel lblPasswordValidator){
        if(password.length()<8){
            lblPasswordValidator.setVisible(true);
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
    }
}
