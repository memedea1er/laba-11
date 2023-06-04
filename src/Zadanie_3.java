import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Zadanie_3 extends Application {

    private TextField fromTextField;
    private TextField toTextField;
    private Button plotButton;
    private LineChart<Number, Number> lineChart;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("График функции");

        // Создание осей
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        lineChart = new LineChart<>(xAxis, yAxis);

        // Создание текстовых полей и кнопки
        Label fromLabel = new Label("От:");
        Label toLabel = new Label("До:");
        fromTextField = new TextField();
        toTextField = new TextField();
        plotButton = new Button("Построить график");

        // Обработка события нажатия на кнопку
        plotButton.setOnAction(e -> plotGraph());

        // Создание панели с элементами управления
        HBox inputPane = new HBox(10);
        inputPane.setAlignment(Pos.CENTER);
        inputPane.getChildren().addAll(fromLabel, fromTextField, toLabel, toTextField, plotButton);

        // Создание главной панели
        VBox mainPane = new VBox(10);
        mainPane.setPadding(new Insets(10));
        mainPane.getChildren().addAll(inputPane, lineChart);

        Scene scene = new Scene(mainPane, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void plotGraph() {
        try {
            double from = Double.parseDouble(fromTextField.getText());
            double to = Double.parseDouble(toTextField.getText());

            if (from > to) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Недопустимый интервал: значение \"От\" должно быть меньше значения \"До\".");
                alert.showAndWait();
                return;
            }

            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            series.setName("График функции");

            for (double x = from; x <= to; x += 0.1) {
                double y = 20 * Math.pow(x, 2) - 5 * Math.pow((Math.sin(x) + x), 2);
                series.getData().add(new XYChart.Data<>(x, y));
            }
            lineChart.getData().clear();
            lineChart.setCreateSymbols(false);
            lineChart.getData().add(series);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("График успешно построен.");
            alert.showAndWait();
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Некорректный ввод данных.");
            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}