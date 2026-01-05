package com.cgvsu.rasterization;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import javafx.scene.paint.Color;

public class InterpolationTests {

    private Color startColor;
    private Color endColor;
    private Interpolation interpolation;

    @Before
    public void setUp() {
        startColor = Color.RED;
        endColor = Color.BLUE;
        interpolation = new Interpolation(startColor, endColor);
    }

    //При t = 0, возвращает начальный цвет
    @Test
    public void testInterpolationStartColor() {
        Color currColor = interpolation.getCurrColor(0.0);

        assertEquals(startColor.getRed(), currColor.getRed(), 1e-9);
        assertEquals(startColor.getGreen(), currColor.getGreen(), 1e-9);
        assertEquals(startColor.getBlue(), currColor.getBlue(), 1e-9);
    }

    //При t = 1, возвращает конечный цвет
    @Test
    public void testInterpolationEndColor() {
        Color currColor = interpolation.getCurrColor(1.0);

        assertEquals(endColor.getRed(), currColor.getRed(), 1e-9);
        assertEquals(endColor.getGreen(), currColor.getGreen(), 1e-9);
        assertEquals(endColor.getBlue(), currColor.getBlue(), 1e-9);
    }

    //При t = 0.5, возвращает цвет посередине
    @Test
    public void testInterpolationHalfColor() {
        Color currColor = interpolation.getCurrColor(0.5);

        assertEquals(0.5, currColor.getRed(), 1e-9);
        assertEquals(0.0, currColor.getGreen(), 1e-9);
        assertEquals(0.5, currColor.getBlue(), 1e-9);
    }

    //При t < 0, должен приравнивать t = 0 и возвращать начальный цвет
    @Test
    public void testInterpolationNegativeColor() {
        Color currColor = interpolation.getCurrColor(-2.7);

        assertEquals(startColor.getRed(), currColor.getRed(), 1e-9);
        assertEquals(startColor.getGreen(), currColor.getGreen(), 1e-9);
        assertEquals(startColor.getBlue(), currColor.getBlue(), 1e-9);
    }

    //При t > 1, должен приравнивать t = 1 и возвращать конечный цвет
    @Test
    public void testInterpolationPositiveColor() {
        Color currColor = interpolation.getCurrColor(5.0);

        assertEquals(endColor.getRed(), currColor.getRed(), 1e-9);
        assertEquals(endColor.getGreen(), currColor.getGreen(), 1e-9);
        assertEquals(endColor.getBlue(), currColor.getBlue(), 1e-9);
    }
}
