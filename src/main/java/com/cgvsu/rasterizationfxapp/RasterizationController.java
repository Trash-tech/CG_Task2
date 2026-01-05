package com.cgvsu.rasterizationfxapp;

import com.cgvsu.rasterization.ColoredArc;
import com.cgvsu.rasterization.DrawManager;
import com.cgvsu.rasterization.DrawObj;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.image.PixelWriter;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class RasterizationController {

    @FXML
    AnchorPane anchorPane;
    @FXML
    private Canvas canvas;
    @FXML
    private ColorPicker startColorPicker;
    @FXML
    private ColorPicker endColorPicker;
    @FXML
    private TextField angle1Field;
    @FXML
    private TextField angle2Field;
    @FXML
    private TextField pxOfLine;
    @FXML
    private Button drawButton;

    private DrawManager drawManager;
    private List<DrawObj> objects = new ArrayList<>();

    private int xC = 400;
    private int yC = 300;


    @FXML
    private void initialize() {
        startColorPicker.setValue(Color.BLACK);
        endColorPicker.setValue(Color.BLACK);
        angle1Field.setText("0");
        angle2Field.setText("360");
        pxOfLine.setText("2");

        anchorPane.prefWidthProperty().addListener((ov, oldV, newV) -> canvas.setWidth(newV.doubleValue()));
        anchorPane.prefHeightProperty().addListener((ov, oldV, newV) -> canvas.setHeight(newV.doubleValue() - 40));

        drawButton.setOnAction(e -> updateDrawing());

        GraphicsContext gc = canvas.getGraphicsContext2D();
        PixelWriter pixelWriter = gc.getPixelWriter();

        canvas.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
                pixelWriter.setColor(xC, yC, Color.WHITE);
                pixelWriter.setColor(xC - 1, yC, Color.WHITE);
                pixelWriter.setColor(xC + 1, yC, Color.WHITE);
                pixelWriter.setColor(xC, yC - 1, Color.WHITE);
                pixelWriter.setColor(xC, yC + 1, Color.WHITE);
                xC = (int) e.getX();
                yC = (int) e.getY();
                drawCenterCircle(pixelWriter);
            }
        });

        updateDrawing();
    }

    private void drawCenterCircle(PixelWriter pixelWriter) {
        pixelWriter.setColor(xC, yC, Color.BLACK);
        pixelWriter.setColor(xC - 1, yC, Color.BLACK);
        pixelWriter.setColor(xC + 1, yC, Color.BLACK);
        pixelWriter.setColor(xC, yC - 1, Color.BLACK);
        pixelWriter.setColor(xC, yC + 1, Color.BLACK);
    }

    private void updateDrawing() {
        objects.clear();

        Color start = startColorPicker.getValue();
        Color end = endColorPicker.getValue();

        double angle1 = checkValue(angle1Field.getText(), 0);
        double angle2 = checkValue(angle2Field.getText(), 360);

        int pxLine = (int) checkValue(pxOfLine.getText(), 2);

        objects.add(new ColoredArc(xC, yC, 100, angle1, angle2, start, end, pxLine));

        drawManager = new DrawManager(canvas, objects);
        drawManager.drawAll();
    }

    private double checkValue(String text, double defaultVal) {
        try {
            return Double.parseDouble(text.trim());
        } catch (NumberFormatException e) {
            angle1Field.setText(String.valueOf(defaultVal));
            return defaultVal;
        }
    }
}