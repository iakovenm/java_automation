public class PointCalculation {
    public static void main(String[] args){
        Point p1 = new Point(2,4);
        Point p2 = new Point(4,5);

        System.out.println("Distance between Point1 with coordinates " + p1.x + " and " + p1.y + " and Point2 with coordinates " + p2.x + " and " + p2.y + " is "+ p1.distance(p2));
    }
    }

