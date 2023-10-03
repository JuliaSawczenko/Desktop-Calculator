package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Calculator extends JFrame implements ActionListener {
    JButton Seven = new JButton("7");
    JButton Eight = new JButton("8");
    JButton Nine = new JButton("9");
    JButton Divide = new JButton("\u00F7");
    JButton Four = new JButton("4");
    JButton Five = new JButton("5");
    JButton Six = new JButton("6");
    JButton Multiply = new JButton("\u00D7");
    JButton One = new JButton("1");
    JButton Two = new JButton("2");
    JButton Three = new JButton("3");
    JButton Add = new JButton("+");
    JButton Dot = new JButton(".");
    JButton Zero = new JButton("0");
    JButton Equals = new JButton("=");
    JButton Subtract = new JButton("-");
    JButton Clear = new JButton("C");
    JButton Delete = new JButton("Del");
    JButton Parentheses = new JButton("( )");
    JButton SquareRoot = new JButton("\u221A");
    JButton PowerTwo = new JButton("x\u00B2");
    JButton PowerY = new JButton("x\u02b8");
    JButton PlusMinus = new JButton("±");
    JLabel EquationLabel = new JLabel();
    JLabel ResultLabel = new JLabel();


    public Calculator() {
        super("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLocationRelativeTo(null);
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        setContentPane(mainPanel);
        JPanel buttonPanel = new JPanel(new GridLayout(6,4));
        buttonPanel.add(new JLabel());

        ResultLabel.setName("ResultLabel");
        ResultLabel.setBounds(10,10,150,60);
        ResultLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        ResultLabel.setFont(new Font("Serif", Font.BOLD, 36));
        ResultLabel.setOpaque(false);
        ResultLabel.setBorder(null);
        topPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        topPanel.add(ResultLabel);
        topPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        EquationLabel.setName("EquationLabel");
        EquationLabel.setBounds(40, 20, 150, 30);
        EquationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        EquationLabel.setForeground(Color.GRAY);
        EquationLabel.setBorder(null);
        EquationLabel.setOpaque(false);
        topPanel.add(EquationLabel);

        Parentheses.setName("Parentheses");
        Parentheses.addActionListener(this::parenthesesPerformed);
        Parentheses.setOpaque(true);
        Parentheses.setBackground(Color.lightGray);
        Parentheses.setBorder(null);
        buttonPanel.add(Parentheses);

        Clear.setName("Clear");
        Clear.addActionListener(this::clearPerformed);
        Clear.setOpaque(true);
        Clear.setBackground(Color.lightGray);
        Clear.setBorder(null);
        buttonPanel.add(Clear);

        Delete.setName("Delete");
        Delete.addActionListener(this::deletePerformed);
        Delete.setOpaque(true);
        Delete.setBackground(Color.lightGray);
        Delete.setBorder(null);
        buttonPanel.add(Delete);

        PowerTwo.setName("PowerTwo");
        PowerTwo.addActionListener(this::powerPerformed);
        PowerTwo.setOpaque(true);
        PowerTwo.setBackground(Color.lightGray);
        PowerTwo.setBorder(null);
        buttonPanel.add(PowerTwo);

        PowerY.setName("PowerY");
        PowerY.addActionListener(this::powerPerformed);
        PowerY.setOpaque(true);
        PowerY.setBackground(Color.lightGray);
        PowerY.setBorder(null);
        buttonPanel.add(PowerY);

        SquareRoot.setName("SquareRoot");
        SquareRoot.addActionListener(this::squareRootPerformed);
        SquareRoot.setOpaque(true);
        SquareRoot.setBackground(Color.lightGray);
        SquareRoot.setBorder(null);
        buttonPanel.add(SquareRoot);

        Divide.setName("Divide");
        Divide.addActionListener(this);
        Divide.setOpaque(true);
        Divide.setBackground(Color.lightGray);
        Divide.setBorder(null);
        buttonPanel.add(Divide);

        Seven.setName("Seven");
        Seven.addActionListener(this);
        buttonPanel.add(Seven);

        Eight.setName("Eight");
        Eight.addActionListener(this);
        buttonPanel.add(Eight);

        Nine.setName("Nine");
        Nine.addActionListener(this);
        buttonPanel.add(Nine);

        Multiply.setName("Multiply");
        Multiply.addActionListener(this);
        Multiply.setOpaque(true);
        Multiply.setBackground(Color.lightGray);
        Multiply.setBorder(null);
        buttonPanel.add(Multiply);

        Four.setName("Four");
        Four.addActionListener(this);
        buttonPanel.add(Four);

        Five.setName("Five");
        Five.addActionListener(this);
        buttonPanel.add(Five);

        Six.setName("Six");
        Six.addActionListener(this);
        buttonPanel.add(Six);

        Subtract.setName("Subtract");
        Subtract.addActionListener(this);
        Subtract.setOpaque(true);
        Subtract.setBackground(Color.lightGray);
        Subtract.setBorder(null);
        buttonPanel.add(Subtract);

        One.setName("One");
        One.addActionListener(this);
        buttonPanel.add(One);

        Two.setName("Two");
        Two.addActionListener(this);
        buttonPanel.add(Two);

        Three.setName("Three");
        Three.addActionListener(this);
        buttonPanel.add(Three);

        Add.setName("Add");
        Add.addActionListener(this);
        Add.setOpaque(true);
        Add.setBackground(Color.lightGray);
        Add.setBorder(null);
        buttonPanel.add(Add);

        PlusMinus.setName("PlusMinus");
        PlusMinus.addActionListener(this::negationPerformed);
        buttonPanel.add(PlusMinus);


        Zero.setName("Zero");
        Zero.addActionListener(this);
        buttonPanel.add(Zero);

        Dot.setName("Dot");
        Dot.addActionListener(this);
        buttonPanel.add(Dot);

        Equals.setName("Equals");
        Equals.addActionListener(this);
        Equals.setOpaque(true);
        Equals.setBackground(Color.cyan);
        Equals.setBorder(null);
        buttonPanel.add(Equals);


        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);

    }



    @Override
        public void actionPerformed(ActionEvent e) {
        String buttonText = ((JButton) e.getSource()).getText();
        String currentText = EquationLabel.getText();
        boolean isOperator = (buttonText.equals("-")) || (buttonText.equals("+")) || (buttonText.equals("\u00D7")) || (buttonText.equals("\u00F7"));

        if (((isOperator) || (buttonText.equals("="))) && (currentText.isEmpty())) {
            EquationLabel.setText("");
        } else if ((!currentText.isEmpty()) && ((buttonText.equals("=")))) {
                try {
                    double result = EquationProcessor.evaluateExpression(EquationProcessor.infixToPostfix(currentText));
                    if (result % 1 != 0) {
                        ResultLabel.setText(String.valueOf(result));
                    } else {
                        ResultLabel.setText(String.valueOf((int) result));
                    }
                } catch (IllegalArgumentException ex) {
                    EquationLabel.setForeground(Color.RED.darker());
                    ResultLabel.setText("wrong input");
                }
        } else if ((!currentText.isEmpty()) && (isOperator) && (((currentText.charAt(currentText.length() - 1)) == '+') || (currentText.charAt(currentText.length() - 1) == '-') || (currentText.charAt(currentText.length() - 1) == '\u00D7') || (currentText.charAt(currentText.length() - 1) == '\u00F7'))) {
                String copy = currentText.substring(0, currentText.length() - 1);
                EquationLabel.setText(copy + buttonText);
        } else if ((currentText.length() > 2) && (isOperator) && (Character.isDigit(currentText.charAt(currentText.length() - 1))) && (currentText.charAt(currentText.length() - 2) == ('.')) && ((!Character.isDigit(currentText.charAt(currentText.length() - 3))))) {
            char[] copy = new char[currentText.length()];
            currentText.getChars(0, currentText.length() - 2, copy, 0);
            EquationLabel.setText(String.valueOf(copy) + "0." + currentText.charAt(currentText.length() - 1) + buttonText);
        } else if ((currentText.length() == 2) && (isOperator) && (Character.isDigit(currentText.charAt(currentText.length() - 1))) && (currentText.charAt(currentText.length() - 2) == ('.'))) {
            EquationLabel.setText("0." + currentText.charAt(currentText.length() - 1) + buttonText);
        } else if ((!currentText.isEmpty()) && (isOperator) && (currentText.charAt(currentText.length() - 1) == ('.'))) {
                EquationLabel.setText(currentText + "0" + buttonText);
        } else {
                EquationLabel.setText(currentText + buttonText);
        }
    }

        public void clearPerformed(ActionEvent e){
            EquationLabel.setText("");
            ResultLabel.setText("");
        }

        public void deletePerformed(ActionEvent e){
            StringBuilder currentText = new StringBuilder(EquationLabel.getText());
            currentText.deleteCharAt(currentText.length()-1);
            EquationLabel.setText(String.valueOf(currentText));
        }

        public void parenthesesPerformed(ActionEvent e){
            String currentText = EquationLabel.getText();
            long countOfLeft = currentText.chars().filter(i -> i == '(').count();
            long countOfRight = currentText.chars().filter(i -> i == ')').count();

            if ((countOfRight == countOfLeft) || ((currentText.charAt(currentText.length() - 1) == '(') || (((currentText.charAt(currentText.length() - 1)) == '+') || (currentText.charAt(currentText.length() - 1) == '-') || (currentText.charAt(currentText.length() - 1) == '\u00D7') || (currentText.charAt(currentText.length() - 1) == '\u00F7')))) {
                EquationLabel.setText(currentText + "(");
            } else {
                EquationLabel.setText(currentText + ")");
            }
        }

        public void squareRootPerformed(ActionEvent e){
        String currentText = EquationLabel.getText();
        EquationLabel.setText(currentText + "\u221A" + "(");
        }

        public void powerPerformed(ActionEvent e){
        String buttonText = ((JButton) e.getSource()).getText();
        String currentText = EquationLabel.getText();

        if (buttonText.equals("x\u00B2")){
            EquationLabel.setText(currentText + "^(2)");
        } else if (buttonText.equals("x\u02b8")){
            EquationLabel.setText(currentText + "^(");
        }
        }

    public void negationPerformed(ActionEvent e) {
        String currentText = EquationLabel.getText();

        // Find the last occurrence of "(-" in the expression
        int lastNegationIndex = currentText.lastIndexOf("(-");

        if (lastNegationIndex >= 0) {
            // If "(-" is found, remove it to toggle the negation
            EquationLabel.setText(currentText.substring(0, lastNegationIndex) +
                    currentText.substring(lastNegationIndex + 2));
        } else if (currentText.isEmpty() || endsWithOperatorOrOpenParenthesis(currentText)) {
            // If the expression is empty or ends with an operator or (, add a negation followed by (
            EquationLabel.setText(currentText + "(-");
        } else {
            // If the expression ends with a number or ), add "(-" before it
            int lastNumberStartIndex = findLastNumberStartIndex(currentText);
            EquationLabel.setText(currentText.substring(0, lastNumberStartIndex) +
                    "(-" + currentText.substring(lastNumberStartIndex));
        }
    }

// Helper methods remain unchanged

    // Helper method to check if the expression ends with an operator or (
    private boolean endsWithOperatorOrOpenParenthesis(String expression) {
        char lastChar = expression.charAt(expression.length() - 1);
        return lastChar == '+' || lastChar == '-' || lastChar == '\u00D7' || lastChar == '\u00F7' || lastChar == '(';
    }

    // Helper method to find the start index of the last number in the expression
    private int findLastNumberStartIndex(String expression) {
        int endIndex = expression.length() - 1;
        while (endIndex >= 0 && Character.isDigit(expression.charAt(endIndex))) {
            endIndex--;
        }
        return endIndex + 1;
    }

}






