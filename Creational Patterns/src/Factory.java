// Let me show you how we can use the factory pattern
// Factory method is just a member of the point class

class Point
{
    private double x, y;

    private Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    // Dedicate a class for the purpose of create of points
    public static class Factory
    {
        public static Point newCartesianPoint(double x, double y)
        {
            return new Point(x, y);
        }

        public static Point newPolarPoint(double rho, double theta)
        {
            return new Point(rho * Math.cos(theta), rho*Math.sin(theta));
        }
    }
}
// This is not allowed
//class Point
//{
//    private double x, y;
//
//    public Point(double x, double y)
//    {
//        this.x = x;
//        this.y = y;
//    }
//
//    public Point(double rho, double theta)
//    {
//        x = rho * Math.cos(theta);
//        y = rho * Math.sin(theta);
//    }
//}

// We specify the different coordinate systems in our case using ENUM
// This is ugly :))
// User might guest what a and b are.
//enum CoordinateSystem
//{
//    CARTESIAN,
//    POLAR
//}
//class Point
//{
//    private double x, y;
//
//    /**
//     * We might add documents to specify a and b
//     * @param a is x if cartesian or rho if polar
//     * @param b is b if cartesian or theta if polar
//     * @param cs coordinate system.
//     */
//    public Point(double a, double b, CoordinateSystem cs)
//    {
//        switch (cs)
//        {
//            case CARTESIAN:
//                this.x = a;
//                this.y = b;
//                break;
//            case POLAR:
//                this.x = a * Math.cos(b);
//                this.y = a * Math.sin(b);
//                break;
//        }
//
//    }
//}