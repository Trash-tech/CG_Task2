package com.cgvsu.rasterization;

import org.junit.Test;
import java.lang.reflect.Method;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import javafx.scene.paint.Color;

public class ColoredArcCheckAngleTests {

    //Проверка нахождения угла в 1-ой четверти (от 0 до 90)
    @Test
    public void testCheckAngleInFirstQuarter() throws Exception {
        ColoredArc currArc = new ColoredArc(400,  300, 80, 0, 90, Color.CYAN, Color.BLUE, 2);
        Method method = ColoredArc.class.getDeclaredMethod("checkAngle", double.class);
        method.setAccessible(true);

        //Нахождения в диапазоне
        boolean inside = (boolean) method.invoke(currArc, Math.toRadians(45));
        assertTrue(inside);

        //Выход из диапазона
        boolean outside = (boolean) method.invoke(currArc, Math.toRadians(180));
        assertFalse(outside);
    }

    //Проверка нахождения угла в 1-ой четверти (от 0 до 90) на границах
    @Test
    public void testCheckAngleInFirstQuarterOnBorders() throws Exception {
        ColoredArc currArc = new ColoredArc(400,  300, 80, 0, 90, Color.CYAN, Color.BLUE, 2);
        Method method = ColoredArc.class.getDeclaredMethod("checkAngle", double.class);
        method.setAccessible(true);

        boolean inside = (boolean) method.invoke(currArc, Math.toRadians(0));
        assertTrue(inside);

        boolean outside = (boolean) method.invoke(currArc, Math.toRadians(90));
        assertTrue(outside);
    }

    //Проверка нахождения угла на дуге проходящей через 0
    @Test
    public void testCheckAngleAfterZero() throws Exception {
        ColoredArc currArc = new ColoredArc(400,  300, 80, -60, 90, Color.CYAN, Color.BLUE, 2);
        Method method = ColoredArc.class.getDeclaredMethod("checkAngle", double.class);
        method.setAccessible(true);

        //Находится в диапазоне
        boolean insideNegative = (boolean) method.invoke(currArc, Math.toRadians(-30));
        assertTrue(insideNegative);

        boolean insidePositive = (boolean) method.invoke(currArc, Math.toRadians(60));
        assertTrue(insidePositive);

        //Выходит за него
        boolean outsideNegative = (boolean) method.invoke(currArc, Math.toRadians(-90));
        assertFalse(outsideNegative);

        boolean outsidePositive = (boolean) method.invoke(currArc, Math.toRadians(180));
        assertFalse(outsidePositive);
    }

    //Проверка нахождения угла на границах дуги, проходящей через 0
    @Test
    public void testCheckAngleAfterZeroOnBoards() throws Exception {
        ColoredArc currArc = new ColoredArc(400,  300, 80, -60, 90, Color.CYAN, Color.BLUE, 2);
        Method method = ColoredArc.class.getDeclaredMethod("checkAngle", double.class);
        method.setAccessible(true);

        //Находится на первой границе
        boolean insideNegative = (boolean) method.invoke(currArc, Math.toRadians(-60));
        assertTrue(insideNegative);

        //Находиться на второй границе
        boolean insidePositive = (boolean) method.invoke(currArc, Math.toRadians(90));
        assertTrue(insidePositive);
    }

    //Проверка нахождения углов в полном круге
    @Test
    public void testCheckAngleInFullCirle() throws Exception {
        ColoredArc currArc1 = new ColoredArc(400,  300, 80, 0, 360, Color.CYAN, Color.BLUE, 2);
        ColoredArc currArc2 = new ColoredArc(400,  300, 80, 0, 0, Color.CYAN, Color.BLUE, 2);
        Method method = ColoredArc.class.getDeclaredMethod("checkAngle", double.class);
        method.setAccessible(true);

        //Находится на первой границе
        boolean insideNegative = (boolean) method.invoke(currArc1, Math.toRadians(76));
        assertTrue(insideNegative);
        insideNegative = (boolean) method.invoke(currArc2, Math.toRadians(76));
        assertTrue(insideNegative);

        //Находиться на второй границе
        boolean insidePositive = (boolean) method.invoke(currArc1, Math.toRadians(-78));
        assertTrue(insidePositive);
        insidePositive = (boolean) method.invoke(currArc2, Math.toRadians(-78));
        assertTrue(insidePositive);
    }

    //Проверка нахождения углов > 360
    @Test
    public void testCheckAngleMore360() throws Exception {
        ColoredArc currArc = new ColoredArc(400,  300, 80, 0, 90, Color.CYAN, Color.BLUE, 2);
        Method method = ColoredArc.class.getDeclaredMethod("checkAngle", double.class);
        method.setAccessible(true);

        //Нахождения в диапазоне
        boolean inside = (boolean) method.invoke(currArc, Math.toRadians(420));
        assertTrue(inside);

        //Выход из диапазона
        boolean outside = (boolean) method.invoke(currArc, Math.toRadians(480));
        assertFalse(outside);
    }

    //Проверка нахождения углов < 0
    @Test
    public void testCheckAngleLessZero() throws Exception {
        ColoredArc currArc = new ColoredArc(400,  300, 80, 0, 90, Color.CYAN, Color.BLUE, 2);
        Method method = ColoredArc.class.getDeclaredMethod("checkAngle", double.class);
        method.setAccessible(true);

        //Нахождения в диапазоне
        boolean inside = (boolean) method.invoke(currArc, Math.toRadians(-320));
        assertTrue(inside);

        //Выход из диапазона
        boolean outside = (boolean) method.invoke(currArc, Math.toRadians(-30));
        assertFalse(outside);
    }

    //Проверка нахождения углов в очень маленьком диапазоне
    @Test
    public void testCheckAngleInSmallRange() throws Exception {
        ColoredArc currArc = new ColoredArc(400,  300, 80, 28, 30, Color.CYAN, Color.BLUE, 2);
        Method method = ColoredArc.class.getDeclaredMethod("checkAngle", double.class);
        method.setAccessible(true);

        //Нахождения в диапазоне
        boolean inside = (boolean) method.invoke(currArc, Math.toRadians(29));
        assertTrue(inside);

        //Выход из диапазона
        boolean outside = (boolean) method.invoke(currArc, Math.toRadians(27));
        assertFalse(outside);
    }
}
