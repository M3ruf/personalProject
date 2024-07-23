import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.StringBuilder;
import java.math.BigDecimal;
public class Calculator implements ActionListener {
    JFrame window = new JFrame("Calculator");
    JTextField screen = new JTextField();
    JPanel sPanel = new JPanel();
    JPanel buttons = new JPanel(new GridLayout(5,4));
    JButton[] numButtons = new JButton[10];
    JButton[] fncButtons = new JButton[10];
    StringBuilder screenText = new StringBuilder();
    BigDecimal num1, num2, result;
    Font font = new Font("Arial", Font.PLAIN, 20);
    Dimension size = new Dimension(10,10);
    char operator = '+';

    public static void main(String[] args){
        Calculator calc = new Calculator();
    }
    Calculator(){
        // Calculator Window setup
        window.setSize(400,500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.setLocationRelativeTo(null);
        window.setResizable(false);

        screen.setSize(window.getWidth(), window.getHeight()/10);
        screen.setEditable(false);
        screen.setText(screenText.toString());
        screen.setFont(font);

        for (int i = 0; i < 10; i++) {
            JButton button = new JButton(String.valueOf(i));
            formatButtons(button);
            numButtons[i]=button;
        }

        fncButtons[0] = new JButton("+");
        fncButtons[1] = new JButton("-");
        fncButtons[2] = new JButton("*");
        fncButtons[3] = new JButton("/");
        fncButtons[4] = new JButton("=");
        fncButtons[5] = new JButton(".");
        fncButtons[6] = new JButton("Clear");
        fncButtons[7] = new JButton("Del");
        fncButtons[8] = new JButton("(+/-)");
        fncButtons[9] = new JButton(" ");

        // Add buttons to button panel in specific order
        buttons.add(numButtons[7]);
        buttons.add(numButtons[8]);
        buttons.add(numButtons[9]);
        buttons.add(fncButtons[3]);

        buttons.add(numButtons[4]);
        buttons.add(numButtons[5]);
        buttons.add(numButtons[6]);
        buttons.add(fncButtons[2]);

        buttons.add(numButtons[1]);
        buttons.add(numButtons[2]);
        buttons.add(numButtons[3]);
        buttons.add(fncButtons[1]);

        buttons.add(numButtons[0]);
        buttons.add(fncButtons[5]);
        buttons.add(fncButtons[4]);
        buttons.add(fncButtons[0]);
        buttons.add(fncButtons[8]);
        buttons.add(fncButtons[6]);
        buttons.add(fncButtons[7]);
        buttons.add(fncButtons[9]);

        buttons.setBounds(screen.getX(),screen.getY()+50,400,400);

        for(JButton button: fncButtons){
            formatButtons(button);
        }

        window.add(screen);
        window.add(buttons);
        window.setVisible(true);
    }

    public void formatButtons(JButton button){
        button.setForeground(Color.black);
        button.setBackground(Color.white);
        button.setFont(font);
        button.setVisible(true);
        button.setOpaque(true);
        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Inputting numbers
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numButtons[i]) {
                screenText.append(i);
                screen.setText(screenText.toString());
            }
        }
        // Add button
        if(e.getSource() == fncButtons[0]){
            num1= new BigDecimal(screenText.toString());
            screenText = new StringBuilder();
            screen.setText(screenText.toString());
            operator = '+';
        }
        // subtract button
        if(e.getSource() == fncButtons[1]){
            num1= new BigDecimal(screenText.toString());
            screenText = new StringBuilder();
            screen.setText(screenText.toString());
            operator = '-';
        }
        // Multiply button
        if(e.getSource() == fncButtons[2]){
            num1= new BigDecimal(screenText.toString());
            screenText = new StringBuilder();
            screen.setText(screenText.toString());
            operator = '*';
        }
        // Divide button
        if(e.getSource() == fncButtons[3]){
            num1= new BigDecimal(screenText.toString());
            screenText = new StringBuilder();
            screen.setText(screenText.toString());
            operator = '/';
        }
        //Equal Button
        if(e.getSource() == fncButtons[4]){
            /* if(num1 == null){}
            else{ */
                switch (operator){
                    case '+':
                        num2= new BigDecimal(screenText.toString());
                        result = num1.add(num2);
                        screenText = new StringBuilder(String.valueOf(result));
                        screen.setText(screenText.toString());
                        break;
                    case '-':
                        num2= new BigDecimal(screenText.toString());
                        result = num1.subtract(num2);
                        screenText = new StringBuilder(String.valueOf(result));
                        screen.setText(screenText.toString());
                        break;
                    case '*':
                        num2= new BigDecimal(screenText.toString());
                        result = num1.multiply(num2);
                        screenText = new StringBuilder(String.valueOf(result));
                        screen.setText(screenText.toString());
                        break;
                    case '/':
                        num2= new BigDecimal(screenText.toString());
                        num2.stripTrailingZeros();
                        result = num1.divide(num2);
                        screenText = new StringBuilder(String.valueOf(result));
                        screen.setText(screenText.toString());
                        break;
                    default:
                        break;
                }
            //}
        }
        //Decimal button
        if(e.getSource() == fncButtons[5]){
            screenText.append('.');
            screen.setText(screenText.toString());
        }
        //Clear Button
        if(e.getSource() == fncButtons[6]){
            num1=null;
            num2=null;
            screenText = new StringBuilder();
            screen.setText(screenText.toString());
        }
        //Delete button
        if(e.getSource() ==  fncButtons[7]){
            screenText.delete(screenText.length()-1,screenText.length());
            screen.setText(screenText.toString());
        }
        // Negative button
        if(e.getSource() == fncButtons[8]){
           if(screenText.charAt(0)!= '-'){
               screenText.insert(0,'-');
               screen.setText(screenText.toString());
           }
           else{
               screenText.delete(0,1);
               screen.setText(screenText.toString());
           }
        }
    }

}
