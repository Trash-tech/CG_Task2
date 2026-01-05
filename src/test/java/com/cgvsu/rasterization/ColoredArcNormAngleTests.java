package com.cgvsu.rasterization;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

import javafx.scene.paint.Color;

import java.lang.reflect.Method;

public class ColoredArcNormAngleTests {

    private ColoredArc currArc;

    @Before
    public void setUp() {
        currArc = new ColoredArc(400,  300, 80, -30, 90, Color.CYAN, Color.BLUE, 2);
    }

    //Для отрицательных углов
    @Test
    public void testNormAngleWithIntNegative() throws Exception {
        Method method = ColoredArc.class.getDeclaredMethod("normAngle", double.class);
        method.setAccessible(true);
        double result = (double) method.invoke(currArc, Math.toRadians(-90));
        double excepted = Math.toRadians(270);
        assertEquals(excepted, result, 1e-9);
    }

    @Test
    public void testNormAngleWithDoubleNegative() throws Exception {
        Method method = ColoredArc.class.getDeclaredMethod("normAngle", double.class);
        method.setAccessible(true);
        double result = (double) method.invoke(currArc, Math.toRadians(-30));
        double excepted = Math.toRadians(330);
        assertEquals(excepted, result, 1e-9);
    }

    //Для угла > 360
    @Test
    public void testNormAngleWithBigPositive() throws Exception {
        Method method = ColoredArc.class.getDeclaredMethod("normAngle", double.class);
        method.setAccessible(true);
        double result = (double) method.invoke(currArc, Math.toRadians(390));
        double excepted = Math.toRadians(30);
        assertEquals(excepted, result, 1e-9);
    }

    //Для угла = 0 и угла = 360
    @Test
    public void testNormAngleWithForZero() throws Exception {
        Method method = ColoredArc.class.getDeclaredMethod("normAngle", double.class);
        method.setAccessible(true);
        double result = (double) method.invoke(currArc, 2 * Math.PI);
        assertEquals(0.0, result, 1e-9);
    }

    //Для угла не требующего нормировки
    @Test
    public void testNormAngle() throws Exception {
        Method method = ColoredArc.class.getDeclaredMethod("normAngle", double.class);
        method.setAccessible(true);
        double result = (double) method.invoke(currArc, Math.toRadians(90));
        double excepted = Math.toRadians(90);
        assertEquals(excepted, result, 1e-9);
    }
}
