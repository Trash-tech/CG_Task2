package com.cgvsu.rasterization;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.List;

public class DrawManager {

    private final Canvas canvas;
    private final List<DrawObj> objects;

    public DrawManager(Canvas canvas, List<DrawObj> objects) {
        this.canvas = canvas;
        this.objects = objects;
    }

    public void drawAll() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (DrawObj obj : objects) {
            obj.draw(gc);
        }
    }
}