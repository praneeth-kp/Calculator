package calculator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 *
 * @author Arik
 */
public class FXMLDocumentController implements Initializable {
    
    //initializing for variables
    //first number
    Double a = Double.NaN;
    //second number
    Double b = Double.NaN;
    //used for totaling
    Double c = Double.NaN;
    //textfield
    String operand = null;
    //used in equal sign button
    int equal = 0;
    
    @FXML
    private TextField txtNum1;
    
    //I'm only documenting button 0 and button 1 since they are all the same after
    @FXML
    private void handleButton0(ActionEvent event) {
        //for assigning b after operation or first go around
        handleButtonNumber("0");
    }
    
    @FXML
    private void handleButton1(ActionEvent event) {
        //Makes it look neater
        handleButtonNumber("1");
    }
    
    @FXML
    private void handleButton2(ActionEvent event) {
        handleButtonNumber("2");
    }
    
    @FXML
    private void handleButton3(ActionEvent event) {
        handleButtonNumber("3");
    }
    
    @FXML
    private void handleButton4(ActionEvent event) {
        handleButtonNumber("4");
    }
    
    @FXML
    private void handleButton5(ActionEvent event) {
        handleButtonNumber("5");
    }
    
    @FXML
    private void handleButton6(ActionEvent event) {
        handleButtonNumber("6");
    }
    
    @FXML
    private void handleButton7(ActionEvent event) {
        handleButtonNumber("7");
    }
    
    @FXML
    private void handleButton8(ActionEvent event) {
        handleButtonNumber("8");
    }
    
    @FXML
    private void handleButton9(ActionEvent event) {
        handleButtonNumber("9");
    }
    
    @FXML
    private void handleButtonPoint(ActionEvent event) {
        if(txtNum1.getText().equals("Error") || txtNum1.getText().equals("NaN") || txtNum1.getText().equals("Infinity") || txtNum1.getText().equals("-Infinity")){
            return;
        }

        //only two instances to add a .
        //adds a 0 plus a .
        if(!a.isNaN() && operand != null && b.isNaN()){
            txtNum1.setText("0.");
            b = Double.parseDouble(txtNum1.getText());
        }
        
        //adds a . if not there
        if(!txtNum1.getText().contains(".")){
            txtNum1.setText(txtNum1.getText() + ".");
        }
    }
    
    @FXML
    private void handleButtonEqual(ActionEvent event) throws IOException {
        if(txtNum1.getText().equals("Error") || txtNum1.getText().equals("NaN") || txtNum1.getText().equals("Infinity") || txtNum1.getText().equals("-Infinity")){
            return;
        }

        //sets b, performs calculation, sets summation in textfield, stores calculation in a, clears operand and b
        if(a.isNaN()){
            return;
        }
        
        if(!a.isNaN() && operand == null){
            return;
        }
        
        if(!a.isNaN() && operand != null && b.isNaN()){
            b = Double.parseDouble(txtNum1.getText());
            c = performOperation(a,b,operand);
            writeCalculation();
            txtNum1.setText(String.valueOf(c));
            a = c;
            equal = 1;
            return;
        }
        
        if(!a.isNaN() && operand != null && !b.isNaN()){
            c = performOperation(a,b,operand);
            writeCalculation();
            txtNum1.setText(String.valueOf(c));
            a = c;
            equal = 1;
        }
       }
    
    //only documenting plus since all operand buttons are the same
    @FXML
    private void handleButtonPlus(ActionEvent event) throws IOException {
        handleButtonOperand("plus");
    }
    
    @FXML
    private void handleButtonMinus(ActionEvent event) throws IOException {
        handleButtonOperand("minus");
    }
    
    @FXML
    private void handleButtonMultiply(ActionEvent event) throws IOException {
        handleButtonOperand("multiply");
    }
    
    @FXML
    private void handleButtonDivide(ActionEvent event) throws IOException {
        handleButtonOperand("divide");
    }
    
    @FXML
    private void handleButtonPercent(ActionEvent event) {
        
        if(txtNum1.getText().equals("Error") || txtNum1.getText().equals("NaN") || txtNum1.getText().equals("Infinity") || txtNum1.getText().equals("-Infinity")){
            return;
        }
        
        if(equal == 1){
            a = a * (a/100.0);
            txtNum1.setText(String.valueOf(a));
            return;
        }
        
        //does not perform if a has not been initialized
        if(a.isNaN()){
            txtNum1.setText("0");
            return;
        }
        
        if(!a.isNaN() && operand == null){
            a = 0.0;
            txtNum1.setText(String.valueOf(a));
            return;
        }
        //incase b has not been intialized
        if(!a.isNaN() && operand != null && b.isNaN()){
            b = a*(a/100.0);
            txtNum1.setText(String.valueOf(b));
            return;
        }
        //if b has been initialized
        if(!a.isNaN() && operand != null && !b.isNaN()){
            b = a*(b/100.0);
            txtNum1.setText(String.valueOf(b));            
        }
    }
    
    @FXML
    private void handleButtonsqrt (ActionEvent event){
        if(txtNum1.getText().equals("Error") || txtNum1.getText().equals("NaN") || txtNum1.getText().equals("Infinity")){
            return;
        }
        
        if(equal == 1){
            a = Math.sqrt(a);
            txtNum1.setText(String.valueOf(a));
            return;
        }
        
        if(a.isNaN()){
            if(Double.parseDouble(txtNum1.getText()) == 0){
                txtNum1.setText("0");
                a = Double.parseDouble(txtNum1.getText());
                return;
            }
            if(Double.parseDouble(txtNum1.getText()) < 0){
                txtNum1.setText("Error");
                return;
            }
            txtNum1.setText(String.valueOf(Math.sqrt(Double.parseDouble(txtNum1.getText()))));
            a = Double.parseDouble(txtNum1.getText());
            return;
        }
        
        if(!a.isNaN() && operand == null){
            if(Double.parseDouble(txtNum1.getText()) == 0){
                txtNum1.setText("0");
                a = Double.parseDouble(txtNum1.getText());
                return;
            }
            if(Double.parseDouble(txtNum1.getText()) < 0){
                txtNum1.setText("Error");
                return;
            }
            txtNum1.setText(String.valueOf(Math.sqrt(Double.parseDouble(txtNum1.getText()))));
            a = Double.parseDouble(txtNum1.getText());
            return;
        }
        
        if(!a.isNaN() && operand != null && b.isNaN()){
            if(Double.parseDouble(txtNum1.getText()) == 0){
                txtNum1.setText("0");
                b = Double.parseDouble(txtNum1.getText());
                return;
            }
            if(Double.parseDouble(txtNum1.getText()) < 0){
                txtNum1.setText("Error");
                return;
            }
            txtNum1.setText(String.valueOf(Math.sqrt(Double.parseDouble(txtNum1.getText()))));
            b = Double.parseDouble(txtNum1.getText());
            return;
        }
        
        if(!a.isNaN() && operand != null && !b.isNaN()){
            if(Double.parseDouble(txtNum1.getText()) == 0){
                txtNum1.setText("0");
                b = Double.parseDouble(txtNum1.getText());
                return;
            }
            if(Double.parseDouble(txtNum1.getText()) < 0){
                txtNum1.setText("Error");
                return;
            }
            txtNum1.setText(String.valueOf(Math.sqrt(Double.parseDouble(txtNum1.getText()))));
            
    b = Double.parseDouble(txtNum1.getText());
        }
        
    }
    @FXML
    private void handleButtonNegative(ActionEvent event) {
        //can't make 0 negative
        if(txtNum1.getText().equals("Error") || txtNum1.getText().equals("NaN") || txtNum1.getText().equals("Infinity") || txtNum1.getText().equals("-Infinity")){
            return;
        }
        
        if(equal == 1){
            a = a*(-1);
            txtNum1.setText(String.valueOf(a));
            return;
        }
        
        if(a.isNaN()){
            return;
        }
        
        if(!a.isNaN() && operand == null){
            if(txtNum1.getText().contains("-")){
                txtNum1.setText(txtNum1.getText().substring(1));
                a = Double.parseDouble(txtNum1.getText());
                return;
            } else {
                txtNum1.setText("-" + txtNum1.getText());
                a = Double.parseDouble(txtNum1.getText());
                return;
            }
        }
        
        if(!a.isNaN() && operand != null && b.isNaN()){
            b = a*(-1);
            txtNum1.setText(String.valueOf(b));
            return;
        }
        
        if(!a.isNaN() && operand != null && !b.isNaN()){
            if(txtNum1.getText().contains("-")){
                txtNum1.setText(txtNum1.getText().substring(1));
                b = Double.parseDouble(txtNum1.getText());
            } else {
                txtNum1.setText("-" + txtNum1.getText());
                b = Double.parseDouble(txtNum1.getText());
            }
        }
    }
    
    @FXML
    private void handleButtonClear(ActionEvent event) {
        //clears everything
        a = Double.NaN;
        b = Double.NaN;
        operand = null;
        equal = 0;
        txtNum1.setText("0");
    }
    
    private static double performOperation(double a, double b, String operand){
        //checks for operand, performs operation, returns result
        if(operand.equals("plus")){
            a = a+b;
            return a;
        }
        
        if(operand.equals("minus")){
            a = a-b;
            return a;
        }
        
        if(operand.equals("divide")){
            a = a/b;
            return a;
        }
        //divide
        a = a*b;
        return a;
    }
    
    private void handleButtonNumber(String num){
        if(txtNum1.getText().equals("Error") || txtNum1.getText().equals("NaN") || txtNum1.getText().equals("Infinity")){
            a = Double.NaN;
            b = Double.NaN;
            operand = null;
            txtNum1.setText("0");
            equal = 0;
        }
        
        if(equal == 1){
            equal = 0;
            txtNum1.setText(num);
            a = Double.parseDouble(txtNum1.getText());
            b = Double.NaN;
            operand = null;
            return;
        }
        
        if(a.isNaN()){
            if(txtNum1.getText().equals("0")){
                txtNum1.setText(num);
                a = Double.parseDouble(txtNum1.getText());
                return;
            }
            
            txtNum1.setText(txtNum1.getText() + num);
            a = Double.parseDouble(txtNum1.getText());
            return;
        }
        
        if(!a.isNaN() && operand == null){
            if(txtNum1.getText().equals("0")){
                txtNum1.setText(num);
                a = Double.parseDouble(txtNum1.getText());
                return;
            }
            
            txtNum1.setText(txtNum1.getText() + num);
            a = Double.parseDouble(txtNum1.getText());
            return;
        }
        
        if(!a.isNaN() && operand != null && b.isNaN()){
            if(txtNum1.getText().equals("0")){
                txtNum1.setText(num);
                b = Double.parseDouble(txtNum1.getText());
                return;
            }
            
            txtNum1.setText(num);
            b = Double.parseDouble(txtNum1.getText());
            return;
        }
        
        if(!a.isNaN() && operand != null && !b.isNaN()){
            if(txtNum1.getText().equals("0")){
                txtNum1.setText(num);
                b = Double.parseDouble(txtNum1.getText());
                return;
            }
            
            txtNum1.setText(txtNum1.getText()+num);
            b = Double.parseDouble(txtNum1.getText());
        }
    }
    
    public void handleButtonOperand(String op) throws IOException{
        if(txtNum1.getText().equals("Error") || txtNum1.getText().equals("NaN") || txtNum1.getText().equals("Infinity") || txtNum1.getText().equals("-Infinity")){
            return;
        }

        //stores textfield as a, op as operand
        if(equal == 1){
            operand = op;
            b = Double.NaN;
            equal = 0;
            return;
        }
        
        if(a.isNaN()){
            a = Double.parseDouble(txtNum1.getText());
            operand = op;
            return;
        }
        //stores operand
        if(!a.isNaN() && operand == null) {
            operand = op;
            return;
        }
        //incase you press a different operand and want another
        if(!a.isNaN() && operand != null && b.isNaN()){
            operand = op;
            return;
        }
        //sets b, stores operation in textfield, records calculation, stores summation as a,
        //operand as plus and clears b
        if(!a.isNaN() && operand != null && !b.isNaN()) {
            b = Double.parseDouble(txtNum1.getText());
            c = performOperation(a,b,operand);
            txtNum1.setText(String.valueOf(c));
            writeCalculation();
            operand = op;
            a = c;
            b = Double.NaN;
        }
    }
    
    public void writeCalculation() throws IOException {
        //initializes file, prints calculation based on operand
        File calculations = new File("calculations.txt");
        FileWriter write = new FileWriter(calculations, true);
        try(BufferedWriter bw = new BufferedWriter(write)){
            
            if(operand.equals("plus")) {
                bw.write(String.valueOf(a) + " + " + String.valueOf(b) + " = " + String.valueOf(c));
                bw.newLine();
                
            }
            
            if(operand.equals("minus")) {
                bw.write(String.valueOf(a) + " - " + String.valueOf(b) + " = " + String.valueOf(c));
                bw.newLine();
            }
            
            if(operand.equals("multiply")) {
                bw.write(String.valueOf(a) + " * " + String.valueOf(b) + " = " + String.valueOf(c));
                bw.newLine();
            }
            
            if(operand.equals("divide")) {
                bw.write(String.valueOf(a) + " / " + String.valueOf(b) + " = " + String.valueOf(c));
                bw.newLine();
            }
        } catch (IOException e){
            
        }
            
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
