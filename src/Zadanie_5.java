import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Zadanie_5 extends Application {

    private LineChart<Number, Number> lineChart;
    private TextField xTextField;
    private TextField yTextField;
    private Label resultLabel;

    public Zadanie_5() {
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("График попадания точки в область");

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        lineChart = new LineChart<>(xAxis, yAxis);

        VBox mainPane = new VBox(20);
        mainPane.setPadding(new Insets(20));

        HBox inputPane = new HBox(10);
        inputPane.setPadding(new Insets(10));

        xTextField = new TextField();
        yTextField = new TextField();
        Button checkButton = new Button("Проверить");
        checkButton.setOnAction(e -> checkPoint());

        inputPane.getChildren().addAll(
                new Label("X: "), xTextField,
                new Label("Y: "), yTextField,
                checkButton
        );

        resultLabel = new Label();

        mainPane.getChildren().addAll(lineChart, inputPane, resultLabel);

        plotGraph();

        Scene scene = new Scene(mainPane, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void plotGraph() {

        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        series1.setName("Серия 1");
        for (double x = 0; x <= 1; x += 0.01) {
            double y=3*x-1;
            series1.getData().add(new XYChart.Data<>(x, y));
        }
        XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
        series2.setName("Серия 2");
        for (double x = -1; x <= 0; x += 0.01) {
            double y=-2*x-1;
            series2.getData().add(new XYChart.Data<>(x, y));
        }
        XYChart.Series<Number, Number> series3 = new XYChart.Series<>();
        series3.setName("Серия 3");
        for (double x = -1; x <= 1; x += 0.01) {
            double y=-1.5*x+3.5;
            series3.getData().add(new XYChart.Data<>(x, y));
        }

        XYChart.Series<Number, Number> series4 = new XYChart.Series<>();
        series4.setName("Серия 4");
        for (double x = -3; x <= -1; x += 0.01) {
            double y=Math.sqrt(4-Math.pow((x+1),2))+3;
            series4.getData().add(new XYChart.Data<>(x, y));
        }

        XYChart.Series<Number, Number> series5 = new XYChart.Series<>();
        series5.setName("Серия 5");
        for (double x = -3; x <= -1; x += 0.01) {
            double y=-Math.sqrt(4-Math.pow((x+1),2))+3;
            series5.getData().add(new XYChart.Data<>(x, y));
        }

        XYChart.Series<Number, Number> series6 = new XYChart.Series<>();
        series6.setName("Серия 6");
        for (double x = 2; x <= 7; x += 0.01) {
            double y=-0.2*x+1.4;
            series6.getData().add(new XYChart.Data<>(x, y));
        }

        XYChart.Series<Number, Number> series7 = new XYChart.Series<>();
        series7.setName("Серия 7");
        for (double x = 2; x <= 3; x += 0.01) {
            double y=-x+3;
            series7.getData().add(new XYChart.Data<>(x, y));
        }

        XYChart.Series<Number, Number> series8 = new XYChart.Series<>();
        series8.setName("Серия 8");
        for (double x = 6; x <= 7; x += 0.01) {
            double y=x-7;
            series8.getData().add(new XYChart.Data<>(x, y));
        }

        XYChart.Series<Number, Number> series9 = new XYChart.Series<>();
        series9.setName("Серия 9");
        for (double x = 2; x <= 3; x += 0.01) {
            double y=x-3;
            series9.getData().add(new XYChart.Data<>(x, y));
        }

        XYChart.Series<Number, Number> series10 = new XYChart.Series<>();
        series10.setName("Серия 10");
        for (double x = 2; x <= 6; x += 0.01) {
            double y = -Math.sqrt(4 - Math.pow(x-4, 2)) - 1;
            series10.getData().add(new XYChart.Data<>(x, y));
        }

        lineChart.getData().clear();
        lineChart.setCreateSymbols(false);
        lineChart.getData().addAll(
                series1, series2, series3, series4, series5, series6, series7, series8, series9, series10
        );
    }
    private void checkPoint() {
        double x = Double.parseDouble(xTextField.getText());
        double y = Double.parseDouble(yTextField.getText());

        boolean isInside = checkInsideArea(x, y);
        resultLabel.setText(isInside ? "Точка попадает в область" : "Точка не попадает в область");
    }

    private boolean checkInsideArea(double x, double y) {
        // Условие для попадания точки в область
        return (x >= -1 && y >= 2.5 * x + 3.5 && y <= -1.5 * x + 3.5)
                || (x <= 0 && y <= 2.5 * x + 3.5 && y >= -2 * x - 1)
                || (x >= 0 && y <= -1.5 * x + 3.5 && y >= 3 * x - 1)
                || (y >= -1 && y <= x - 3 && y <= -1f / 3 * x + 1)
                || (y <= 0 && y >= x - 7 && y >= -1f / 3 * x + 1)
                || (y >= 0 && y >= -x + 3 && y <= - 0.2 * x + 1.4)
                || (Math.pow((x + 1), 2) + Math.pow((y - 3), 2) <= 4 && x <= -1)
                || (Math.pow((x - 4), 2) + Math.pow((y + 1), 2) <= 4 && y <= -1);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
