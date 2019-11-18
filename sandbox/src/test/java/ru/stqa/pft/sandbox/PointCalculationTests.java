package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointCalculationTests {
    @Test

    public void testDistance() {
        Point p1 = new Point(2, 5);
        Point p2 = new Point(4, 10);
        Assert.assertEquals(p1.distance(p2), 5.385164807134504);

    }
}
