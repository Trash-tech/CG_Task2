package com.cgvsu.rasterization;

import javafx.scene.paint.Color;

public class Interpolation {

    private Color startColor;
    private Color endColor;

    public Interpolation(Color startColor, Color endColor) {
        this.startColor = startColor;
        this.endColor = endColor;
    }

    public Color getCurrColor(double t) {
        if (t > 1 || t < 0) {
            t = Math.max(0.0, Math.min(1.0, t));
        }
        return interpolation(t);
    }

    private Color interpolation(double t) {
        double currRed = startColor.getRed() + t * (endColor.getRed() - startColor.getRed());
        double currGreen = startColor.getGreen() + t * (endColor.getGreen() - startColor.getGreen());
        double currBlue = startColor.getBlue() + t * (endColor.getBlue() - startColor.getBlue());
        return Color.color(currRed, currGreen, currBlue);
    }
}
