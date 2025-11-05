package com.cgvsu.fortask;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.image.PixelWriter;

public class ColoredArc implements DrawObj {

    private final int xC, yC, r, sizePx;
    private final double angle1, angle2;
    private final Color startColor, endColor;
    private final double fullCircle = 2 * Math.PI;
    private final Interpolation currInterColor;

    public ColoredArc(int xC, int yC, int r, double angle1Deg, double angle2Deg, Color startColor, Color endColor, int pxLine) {
        this.xC = xC;
        this.yC = yC;
        this.r = r;
        this.angle1 = Math.toRadians(angle1Deg);
        this.angle2 = Math.toRadians(angle2Deg);
        this.startColor = startColor;
        this.endColor = endColor;
        this.sizePx = pxLine;
        currInterColor = new Interpolation(startColor, endColor);
    }

    @Override
    public void draw(GraphicsContext gc) {
        rasterizeCircleBresenham(gc);
    }

    private void rasterizeCircleBresenham(GraphicsContext gc) {
        int x = 0;
        int y = r;
        int d = 1 - r;

        while (x <= y) {
            drawPartOfArc(gc, x, y);

            if (d < 0) {
                d += 2 * x + 3;
            } else {
                y--;
                d += 2 * (x - y) + 5;
            }
            x++;
        }
    }

    private void drawPartOfArc(GraphicsContext gc, int x, int y) {
        PixelWriter pixelWriter = gc.getPixelWriter();

        drawPixel(pixelWriter, x, y);
        drawPixel(pixelWriter, -x, y);
        drawPixel(pixelWriter, x, -y);
        drawPixel(pixelWriter, -x, -y);
        drawPixel(pixelWriter, y, x);
        drawPixel(pixelWriter, -y, x);
        drawPixel(pixelWriter, y, -x);
        drawPixel(pixelWriter, -y, -x);
    }

    private void drawPixel(PixelWriter pixelWriter, int x, int y) {
        double currAngle = Math.atan2(-y, x);
        double normCurrAngle = normAngle(currAngle);
        double normAngle1 = normAngle(angle1);
        double normAngle2 = normAngle(angle2);

        if (Math.abs(angle2 - angle1) < 1e-9 ||
                Math.abs((angle2 - angle1) % (2 * Math.PI)) < 1e-9) {
            normAngle1 = 0;
            normAngle2 = fullCircle;
        }

        if (normAngle2 < normAngle1) {
            normAngle2 += fullCircle;
            if (normCurrAngle < normAngle1) {
                normCurrAngle += fullCircle;
            }
        }

        if (normCurrAngle < normAngle1 || normCurrAngle > normAngle2) return;

        double arcLength = normAngle2 - normAngle1;
        double currDist = normCurrAngle - normAngle1;
        double t = currDist / arcLength;

        if (t < 0) t = 0;
        if (t > 1) t = 1;

        //pixelWriter.setColor(xC + x, yC + y, currInterColor.getCurrColor(t));

        for (int dx = 0; dx < sizePx; dx++) {
            for (int dy = 0; dy < sizePx; dy++) {
                pixelWriter.setColor(xC + x * sizePx + dx, yC + y * sizePx + dy, currInterColor.getCurrColor(t));
            }
        }
    }

    private boolean checkAngle(double currAngle) {
        double normCurrAngle = normAngle(currAngle);
        double normAngle1 = normAngle(angle1);
        double normAngle2 = normAngle(angle2);
        if (normAngle1 <= normAngle2) {
            return normCurrAngle >= normAngle1 && normCurrAngle <= normAngle2;
        } else {
            return normCurrAngle >= normAngle1 || normCurrAngle <= normAngle2;
        }
    }

    private double normAngle(double angle) {
        return (angle % fullCircle + fullCircle) % fullCircle;
    }
}