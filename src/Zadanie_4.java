import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Zadanie_4 extends Application {

    private TextField fromTextField;
    private TextField toTextField;
    private TextArea resultTextArea;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Генератор случайных чисел");

        Label fromLabel = new Label("От:");
        Label toLabel = new Label("До:");
        fromTextField = new TextField();
        toTextField = new TextField();

        Button generateButton = new Button("Генерировать");
        generateButton.setOnAction(e -> generateNumbers());

        resultTextArea = new TextArea();
        resultTextArea.setEditable(false);

        GridPane inputPane = new GridPane();
        inputPane.setHgap(10);
        inputPane.setVgap(10);
        inputPane.setPadding(new Insets(10));
        inputPane.addRow(0, fromLabel, fromTextField);
        inputPane.addRow(1, toLabel, toTextField);

        VBox mainPane = new VBox(10);
        mainPane.setPadding(new Insets(10));
        mainPane.getChildren().addAll(inputPane, generateButton, resultTextArea);

        Scene scene = new Scene(mainPane, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void generateNumbers() {
        try {
            int from = Integer.parseInt(fromTextField.getText());
            int to = Integer.parseInt(toTextField.getText());

            List<Integer> randomNumbers = new ArrayList<>();
            Random random = new Random();

            for (int i = 0; i < 7; i++) {
                int randomNumber = random.nextInt(to - from + 1) + from;
                randomNumbers.add(randomNumber);
            }

            StringBuilder sb = new StringBuilder();
            for (int number : randomNumbers) {
                sb.append(number).append("\n");
            }
            resultTextArea.setText(sb.toString());
        } catch (NumberFormatException ex) {
            resultTextArea.setText("Ошибка: некорректный ввод интервала.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}