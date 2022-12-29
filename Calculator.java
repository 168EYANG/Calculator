import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.Collections;
/**
 *
 * @author Ethan
 * @version 1.0
 * javac --module-path "C:\Users\168et\JavaPrograms\javafx-sdk-11.0.2\lib" --add-modules javafx.controls,javafx.fxml Calculator.java
 * java --module-path "C:\Users\168et\JavaPrograms\javafx-sdk-11.0.2\lib" --add-modules javafx.controls,javafx.fxml Calculator
 *
 */
public class Calculator extends Application {
    private String originalNumber = "";
    private String currentNumber = "";
    private String operation;
    TextField output = new TextField();


    /**
     * main method
     * @param args from user input
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * start method
     * @param stage from main method
     */
    public void start(Stage stage) {
        stage.setTitle("Calculator");

        Button times = new Button();
        times.setText("x");
        times.setMinSize(40, 40);
        Button divides = new Button();
        divides.setText("/");
        divides.setMinSize(40, 40);
        Button seven = new Button();
        seven.setText("7");
        seven.setMinSize(40, 40);
        Button eight = new Button();
        eight.setText("8");
        eight.setMinSize(40, 40);
        Button nine = new Button();
        nine.setText("9");
        nine.setMinSize(40, 40);
        Button minus = new Button();
        minus.setText("-");
        minus.setMinSize(40, 40);
        Button four = new Button();
        four.setText("4");
        four.setMinSize(40, 40);
        Button five = new Button();
        five.setText("5");
        five.setMinSize(40, 40);
        Button six = new Button();
        six.setText("6");
        six.setMinSize(40, 40);
        Button plus = new Button();
        plus.setText("+");
        plus.setMinSize(40, 40);
        Button three = new Button();
        three.setText("3");
        three.setMinSize(40, 40);
        Button two = new Button();
        two.setText("2");
        two.setMinSize(40, 40);
        Button one = new Button();
        one.setText("1");
        one.setMinSize(40, 40);
        Button equals = new Button();
        equals.setText("=");
        equals.setMinSize(40, 85);
        Button negate = new Button();
        negate.setText("+/-");
        negate.setMinSize(40, 40);
        Button zero = new Button();
        zero.setText("0");
        zero.setMinSize(90, 40);
        Button clear = new Button();
        clear.setText("C");
        clear.setMinSize(40, 40);
        Button sqrt = new Button();
        sqrt.setText("\u221A");
        sqrt.setMinSize(40, 40);


        VBox column1 = new VBox();
        column1.setAlignment(Pos.CENTER);
        column1.setSpacing(5);
        column1.getChildren().addAll(negate, seven, four, one);

        VBox column2 = new VBox();
        column2.setAlignment(Pos.CENTER);
        column2.setSpacing(5);
        column2.getChildren().addAll(clear, eight, five, two);

        VBox column3 = new VBox();
        column3.setAlignment(Pos.CENTER);
        column3.setSpacing(5);
        column3.getChildren().addAll(divides, nine, six, three);

        VBox column4 = new VBox();
        column4.setAlignment(Pos.CENTER);
        column4.setSpacing(5);
        column4.getChildren().addAll(times, minus, plus, equals);

        HBox grid = new HBox();
        grid.setAlignment(Pos.CENTER);
        grid.setSpacing(10);
        grid.getChildren().addAll(column1, column2, column3);

        HBox lastRow = new HBox();
        lastRow.setAlignment(Pos.CENTER);
        lastRow.setSpacing(10);
        lastRow.getChildren().addAll(zero, sqrt);

        VBox leftSide = new VBox();
        leftSide.setAlignment(Pos.CENTER);
        leftSide.setSpacing(5);
        leftSide.getChildren().addAll(output, grid, lastRow);

        HBox layout = new HBox();
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10);
        layout.getChildren().addAll(leftSide, column4);

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(5);
        root.getChildren().addAll(output, layout);


        one.setOnAction((ActionEvent event) -> {
            updateOutput("1");
        });
        two.setOnAction((ActionEvent event) -> {
            updateOutput("2");
        });
        three.setOnAction((ActionEvent event) -> {
            updateOutput("3");
        });
        four.setOnAction((ActionEvent event) -> {
            updateOutput("4");
        });
        five.setOnAction((ActionEvent event) -> {
            updateOutput("5");
        });
        six.setOnAction((ActionEvent event) -> {
            updateOutput("6");
        });
        seven.setOnAction((ActionEvent event) -> {
            updateOutput("7");
        });
        eight.setOnAction((ActionEvent event) -> {
            updateOutput("8");
        });
        nine.setOnAction((ActionEvent event) -> {

            updateOutput("9");
        });
        zero.setOnAction((ActionEvent event) -> {
            if (!(currentNumber.equals("")) || (!(originalNumber.equals("")))) {
                updateOutput("0");
            }
        });

        clear.setOnAction((ActionEvent event) -> {
            currentNumber = "";
            output.setText("");
        });
        negate.setOnAction((ActionEvent event) -> {
            int newNumber = Integer.parseInt(currentNumber) * -1;
            currentNumber = String.valueOf(newNumber);
            output.setText(currentNumber);
        });

        times.setOnAction((ActionEvent event) -> {
            operationInput("*");
        });
        divides.setOnAction((ActionEvent event) -> {
            operationInput("/");
        });
        plus.setOnAction((ActionEvent event) -> {
            operationInput("+");
        });
        minus.setOnAction((ActionEvent event) -> {
            operationInput("-");
        });
        sqrt.setOnAction((ActionEvent event) -> {
            try {
                double squareRoot = Math.sqrt(Integer.parseInt(currentNumber));
                currentNumber = String.valueOf(squareRoot);
                output.setText(currentNumber);
            } catch (NumberFormatException e) {
                currentNumber = "";
                originalNumber = "";
                operation = "";
                output.setText("SYNTAX ERROR");
            }
        });

        equals.setOnAction((ActionEvent event) -> {
            calculation();
        });

        Scene scene = new Scene(root, 200, 400);
        stage.setTitle("Calculator");
        stage.setScene(scene);
        stage.show();
    }

    
    public void updateOutput(String number) {
        currentNumber += number;
        output.setText(currentNumber);
    }

    public void operationInput(String operation) {
        this.operation = operation;
        originalNumber = currentNumber;
        currentNumber = "";
    }

    public void calculation() {
        double firstNumber = Double.parseDouble(originalNumber);
        double secondNumber = Double.parseDouble(currentNumber);
        double result = 0;
        double quotient = 0;
        switch (operation) {
            case "+":
                result = firstNumber + secondNumber;
                output.setText(String.valueOf(result));
                break;
            case "-":
                result = firstNumber - secondNumber;
                output.setText(String.valueOf(result));
                break;
            case "/":
                try {
                    quotient = firstNumber / (double)secondNumber;
                    System.out.println(quotient);
                    output.setText(String.valueOf(quotient));
                } catch (ArithmeticException e) {
                    output.setText("SYNTAX ERROR");
                } finally {
                    break;
                }
            case "*":
                result = firstNumber * secondNumber;
                output.setText(String.valueOf(result));
                break;
        }
        currentNumber = "";
        originalNumber = "";
        operation = "";
    }
}