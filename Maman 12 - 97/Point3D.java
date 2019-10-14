/**
 * This Class Represent a Point in the 3D Cartesian Space.
 * @author Zvi Natan Badash 214553034
 * */
public class Point3D {
    // Class Attributes
    private final double DEFAULT_VAL = 0; // the default point value
    private double _x; // the x instance variable
    private double _y; // the y instance variable
    private double _z; // the z instance variable


    // Constructors
    /**
     * Construct a Blank Point at (0, 0, 0)
     * */
    public Point3D(){
        this._x = DEFAULT_VAL; //Assign The Default Value To X
        this._y = DEFAULT_VAL; //Assign The Default Value To Y
        this._z = DEFAULT_VAL; //Assign The Default Value To Z
    }
    /**
     * Construct a Point at (x, y, z)
     * @param x The x Coordinate
     * @param y The y Coordinate
     * @param z The z Coordinate
     * */
    public Point3D (double x, double y, double z){
        this._x = x; //Assign The Value Passed To X
        this._y = y; //Assign The Value Passed To Y
        this._z = z; //Assign The Value Passed To Z
    }
    /**
     * Copies a point to a new Object
     * @param other The Point to copy
     * */
    public Point3D (Point3D other){
        this._x = other._x; // copy from the other point
        this._y = other._y; // copy from the other point
        this._z = other._z; // copy from the other point
    }


    // Methods - Accessors
    /**
     * Gives the x Coordinate of the Point
     * @return The x Coordinate of the Given Point
     * */
    public double getX(){
       return(this._x);
    }
    /**
     * Gives the x Coordinate of the Point
     * @return The y Coordinate of the Given Point
     * */
    public double getY(){
        return(this._y);
    }
    /**
     * Gives the x Coordinate of the Point
     * @return The z Coordinate of the Given Point
     * */
    public double getZ(){
        return(this._z);
    }

    // Methods - Mutators
    /**
     * Sets the x Value of the Point
     * @param num The x Coordinate to Set
     * */
    public void setX(double num){
        this._x = num;
    }
    /**
     * Sets the x Value of the Point
     * @param num The y Coordinate to Set
     * */
    public void setY(double num){
        this._y = num;
    }
    /**
     * Sets the x Value of the Point
     * @param num The z Coordinate to Set
     * */
    public void setZ(double num){
        this._z = num;
    }

    // Methods - Misc.
    /**
     * Return the Point as String Format like this : (x, y, z)
     * @return The Formatted String
     * */
    public String toString(){
        return"(" + _x + "," + _y + "," + _z + ")"; //format the data and returns it
    }
    /**
     * Checks if the Point that Called the method is Equal to the Other Point
     * @param other The other Point
     * @return true or false
     * */
    public boolean equals(Point3D other) {
        return (this._z == other._z && this._y == other._y && this._x == other._x); //check if the values are equal
    }
    /**
     * Checks if the Point that Called the method is Above the Other Point
     * @param other The other Point
     * @return true or false
     * */
    public boolean isAbove(Point3D other){
        return (this._z > other._z);
    }
    /**
     * Checks if the Point that Called the method is Under the Other Point
     * @param other The other Point
     * @return true or false
     * */
    public boolean isUnder(Point3D other){
        return (other.isAbove(this)); // if point A is above another point B then B is under A
    }
    /**
     * Checks if the Point that Called the method is Left to the Other Point
     * @param other The other Point
     * @return true or false
     * */
    public boolean isLeft(Point3D other){
        return(this._y < other._y);
    }
    /**
     * Checks if the Point that Called the method is Right to the Other Point
     * @param other The other Point
     * @return true or false
     * */
    public boolean isRight(Point3D other){
        return(other.isLeft(this)); // if point A is left to another point B then B is right to A
    }
    /**
     * Checks if the Point that Called the method is Behind the Other Point
     * @param other The other Point
     * @return true or false
     * */
    public boolean isBehind(Point3D other){
        return(this._x < other._x);
    }
    /**
     * Checks if the Point that Called the method is in Front of the Other Point
     * @param other The other Point
     * @return true or false
     * */
    public boolean isInFrontOf(Point3D other){
        return(other.isBehind(this)); // if point A is behind another point B then B is in front of A
    }
    /**
     * Moves the Point that Called the Method by dx, dy, dz
     * @param dx The x Axis Amount to move
     * @param dy The y Axis Amount to move
     * @param dz The z Axis Amount to move
     * */
    public void move(double dx, double dy, double dz){
        this._x += dx;
        this._y += dy;
        this._z += dz;
    }
    /**
     * Gives the Distance between the Point that Called the Method and the Other Point
     * @param other The other Point
     * @return The Distance
     * */
    public double distance(Point3D other){
        return(Math.sqrt(Math.pow(this._x-other._x, 2) + Math.pow(this._y-other._y, 2) + Math.pow(this._z-other._z, 2)));
        // uses the pythagoras formula for distance
    }

}