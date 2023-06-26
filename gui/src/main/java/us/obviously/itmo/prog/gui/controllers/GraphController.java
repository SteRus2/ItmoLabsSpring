package us.obviously.itmo.prog.gui.controllers;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import us.obviously.itmo.prog.gui.Main;
import us.obviously.itmo.prog.gui.i18n.Language;
import us.obviously.itmo.prog.gui.views.ViewsManager;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import static java.lang.Thread.sleep;

public class GraphController implements Initializable, Translatable {
    public static final Paint[] colors = {Color.ORANGE, Color.GREEN, Color.BLACK, Color.BLUE, Color.YELLOW, Color.LIGHTBLUE, Color.LIGHTCYAN, Color.CORAL, Color.CADETBLUE, Color.CORNSILK};
    public StackPane layout;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Axes axes = new Axes(600, 300, -400, 400, 50, -400, 400, 50);

        Circles plot = new Circles(axes);

        layout.getChildren().add(plot);
        layout.setPadding(new Insets(20));
//        layout.setStyle("-fx-background-color: rgb(35, 39, 50);");
    }

    @Override
    public void setBundle(Language language) {

    }

    static class Circles extends Pane {
        private final Axes axes;

        public Circles(Axes axes) {
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(this::rebuild);
            this.axes = axes;
        }

        private void rebuild() {

            Map<Integer, Paint> colorMap = new HashMap<>();

//            while (true) {
            if (Main.currentStudyGroups.isEmpty()) {
                try {
                    sleep(1000);
                    rebuild();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            Platform.runLater(() -> {
                setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
                setPrefSize(axes.getPrefWidth(), axes.getPrefHeight());
                setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);

                getChildren().clear();
                getChildren().add(axes);
            });
            AtomicInteger maxStudentsAtomic = new AtomicInteger();
            AtomicInteger minStudentsAtomic = new AtomicInteger(Integer.MAX_VALUE);
            double MAX_RADIUS = 15;
            double MIN_RADIUS = 5;
            Main.currentStudyGroups.forEach((id, studyGroup) -> {
                Integer students = studyGroup.getStudentsCount();
                if (students == null) students = 0;
                if (students > maxStudentsAtomic.get()) maxStudentsAtomic.set(students);
                if (students < minStudentsAtomic.get()) minStudentsAtomic.set(students);
            });
            int maxStudents = maxStudentsAtomic.get();
            int minStudents = minStudentsAtomic.get();
            Main.currentStudyGroups.forEach((id, group) -> {
                Integer studentCount = group.getStudentsCount();
                if (studentCount == null) studentCount = 0;

                Circle circle = new Circle();
                circle.setCenterX(mapX(group.getCoordinates().getX(), axes));
                circle.setCenterY(mapY(group.getCoordinates().getY(), axes));

                circle.setOnMouseClicked(event -> {
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    if (group.getOwnerId() == Main.client.getId()) {
                        try {
                            ViewsManager.showUpdateToolView(stage, group);
                        } catch (IOException e) {
                            throw new RuntimeException(e); // TOOD: show error
                        }
                    } else {
                        try {
                            ViewsManager.showReadToolView(stage, group);
                        } catch (IOException e) {
                            throw new RuntimeException(e); // TOOD: show error
                        }
                    }
                });


                double studentsPercent = (studentCount - minStudents) / (maxStudents * 1.0);
                double studentsRadius = MIN_RADIUS + (MAX_RADIUS - MIN_RADIUS) * studentsPercent;
                circle.setRadius(studentsRadius);
//              group.getGroupAdmin().getEyeColor();
                Paint color;
                if (!colorMap.containsKey(group.getOwnerId())) {
                    colorMap.put(group.getOwnerId(), colors[colorMap.size()]);
                }
                color = colorMap.get(group.getOwnerId());
                circle.setStroke(color);
                circle.setFill(color);
                circle.setStrokeWidth(2);
                try {
                    Platform.runLater(() -> {
                        getChildren().add(circle);
                    });
                } catch (Exception e) {
                    System.out.println(e);
                }

            });
            try {
                sleep(1000);
                rebuild();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//            }

        }

        private double mapX(double x, Axes axes) {
            double tx = axes.getPrefWidth() / 2;
            double sx = axes.getPrefWidth() / (axes.getXAxis().getUpperBound() - axes.getXAxis().getLowerBound());

            return x * sx + tx;
        }

        private double mapY(double y, Axes axes) {
            double ty = axes.getPrefHeight() / 2;
            double sy = axes.getPrefHeight() / (axes.getYAxis().getUpperBound() - axes.getYAxis().getLowerBound());

            return -y * sy + ty;
        }
    }

    static class Axes extends Pane {
        private final NumberAxis xAxis;
        private final NumberAxis yAxis;

        public Axes(int width, int height, double xLow, double xHi, double xTickUnit, double yLow, double yHi, double yTickUnit) {
            setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
            setPrefSize(width, height);
            setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);

            xAxis = new NumberAxis(xLow, xHi, xTickUnit);
            xAxis.setSide(Side.BOTTOM);
            xAxis.setMinorTickVisible(false);
            xAxis.setPrefWidth(width);
            xAxis.setLayoutY(height / 2);

            yAxis = new NumberAxis(yLow, yHi, yTickUnit);
            yAxis.setSide(Side.LEFT);
            yAxis.setMinorTickVisible(false);
            yAxis.setPrefHeight(height);
            yAxis.layoutXProperty().bind(Bindings.subtract((width / 2) + 1, yAxis.widthProperty()));

            getChildren().setAll(xAxis, yAxis);
        }

        public NumberAxis getXAxis() {
            return xAxis;
        }

        public NumberAxis getYAxis() {
            return yAxis;
        }
    }

    static class Plot extends Pane {

        public Plot(Function<Double, Double> f, double xMin, double xMax, double xInc, Axes axes) {
            Path path = new Path();
            path.setStroke(Color.ORANGE.deriveColor(0, 1, 1, 0.6));
            path.setStrokeWidth(2);
            path.setClip(new Rectangle(0, 0, axes.getPrefWidth(), axes.getPrefHeight()));

            double x = xMin;
            double y = f.apply(x);

            path.getElements().add(new MoveTo(mapX(x, axes), mapY(y, axes)));

            x += xInc;
            while (x < xMax) {
                y = f.apply(x);

                path.getElements().add(new LineTo(mapX(x, axes), mapY(y, axes)));

                x += xInc;
            }

            setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
            setPrefSize(axes.getPrefWidth(), axes.getPrefHeight());
            setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);

            getChildren().setAll(axes, path);
        }

        private double mapX(double x, Axes axes) {
            double tx = axes.getPrefWidth() / 2;
            double sx = axes.getPrefWidth() / (axes.getXAxis().getUpperBound() - axes.getXAxis().getLowerBound());

            return x * sx + tx;
        }

        private double mapY(double y, Axes axes) {
            double ty = axes.getPrefHeight() / 2;
            double sy = axes.getPrefHeight() / (axes.getYAxis().getUpperBound() - axes.getYAxis().getLowerBound());

            return -y * sy + ty;
        }
    }
}
