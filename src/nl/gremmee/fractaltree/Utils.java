package nl.gremmee.fractaltree;

import java.awt.geom.Point2D;
import java.util.Random;

public class Utils {
    private static Random random = new Random();

    public static Point2D.Double addPoints2D(Point2D.Double aA, Point2D.Double aB) {
        return new Point2D.Double(aA.x + aB.x, aA.y + aB.y);
    }

    public static Point2D.Double divPoints2D(Point2D.Double aA, float aFactor) {
        return new Point2D.Double(aA.x / aFactor, aA.y / aFactor);
    }

    public static float getRandomFloat(float aMax) {
        return getRandomFloat(0, aMax);
    }

    public static float getRandomFloat(float aMin, float aMax) {
        return (random.nextFloat() * (aMax - aMin)) + aMin;
    }

    public static final float map(float aMappedValue, float aInboundMin, float aInboundMax, float aOutboundMin,
            float aOutboundMax) {
        return aOutboundMin
                + ((aOutboundMax - aOutboundMin) * ((aMappedValue - aInboundMin) / (aInboundMax - aInboundMin)));
    }

    public static Point2D.Double multiplyPoints2D(Point2D.Double aA, float aFactor) {
        return new Point2D.Double(aA.x * aFactor, aA.y * aFactor);
    }

    public static Point2D.Double normalize(Point2D.Double aA) {
        Point2D.Double v2 = new Point2D.Double();

        double length = Math.sqrt((aA.x * aA.x) + (aA.y * aA.y));
        if (length != 0) {
            v2.x = aA.x / length;
            v2.y = aA.y / length;
        }

        return v2;
    }

    public static Point2D.Double subPoints2D(Point2D.Double aA, Point2D.Double aB) {
        return new Point2D.Double(aA.x - aB.x, aA.y - aB.y);
    }
}
