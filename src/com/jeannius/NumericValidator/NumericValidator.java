package com.jeannius.NumericValidator;



import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.regex.Pattern;



/**
 * Created by Jlarrieux on 1/13/2016.
 */
public class NumericValidator {

    private JTextField currentTextField;
    Object currentType;
    String errorString;
    Type numericType;
    private static final String NO_TEXT = "No text";
    private static final String NOT_INT = "Not of Expected type int";
    private static final String NOT_DOUBLE = "Not of Expected type double";
    private boolean allowPopUpOnError= true;
    JFrame containerFrame;

    public enum Type {
        DOUBLE, INTEGER
    }



    public NumericValidator(NumericValidator.Type numericType) {
        this.numericType = numericType;
    }

    public NumericValidator(NumericValidator.Type numericType, JFrame container){
        this.containerFrame = container;
    }



    public boolean validate(JTextField textField) {
        currentTextField = textField;
        errorString ="";
        textField.setBorder(UIManager.getBorder("TextField.border"));
        boolean result = lengthValidation() && numberValidator();

        if(!result && allowPopUpOnError) showErrorDialog();

        return result;
    }



    private boolean lengthValidation() {
        boolean result = currentTextField.getText().length() != 0;
        if (!result) errorCompilation(NO_TEXT);
        return result;
    }



    private boolean numberValidator(){

        if(numericType==Type.DOUBLE)return isNumericTypeDouble();
        else return isNumericTypeInteger();

    }


    private boolean isNumericTypeInteger() {
        if (Pattern.matches("^\\d*$", currentTextField.getText())) return true;
        else{
            errorCompilation(NOT_INT);
            return false;
        }
    }


    private boolean isNumericTypeDouble(){
        if (Pattern.matches("^\\d*\\.?\\d*", currentTextField.getText())) return true;
        else{
            errorCompilation(NOT_INT);
            return false;
        }
    }


    private void errorCompilation(String text) {
        if(errorString.length()>0) errorString +="\n";
        errorString +=  text;
        Border border = BorderFactory.createLineBorder(Color.red, 2);
        currentTextField.setBorder(border);
    }


    private void showErrorDialog(){
        JOptionPane.showMessageDialog(containerFrame, errorString, "Input Error!", JOptionPane.ERROR_MESSAGE);

    }


    public String getErrorString(){
        return errorString;
    }



}
