/**
 * This Class Represent a Box in the 3D Cartesian Space.
 * @author Zvi Badash 214553034
 * */
public class Box3D {
    //Class Attribute
    private final int MINIMAL_LENGTH = 0;
    private final int DEFAULT_LENGTH = 1;
    private Point3D _base;
    private int _length;
    private int _height;
    private int _width;



    // Constructors
    /**
     * Constructs and initializes a box with a default base point and all dimensions to 1
     * */
    public Box3D(){
        this._base   = new Point3D();  // Utilize The Copy Constructor Of Point3D
        this._length = DEFAULT_LENGTH; // 1
        this._width  = DEFAULT_LENGTH; // 1
        this._height = DEFAULT_LENGTH; // 1
    }
    /**
     * Construct a Box at a Point3D Object and Length, Width, Height is Given
     * @param base The Down Left Front Point of the Box
     * @param length The length of the Box
     * @param width  The Width of the Box
     * @param height The Height of the Box
     * */
    public Box3D (Point3D base, int length, int width, int height ){
        this._height = (height > MINIMAL_LENGTH)? height: DEFAULT_LENGTH; //If The Value Is "Good", Assign It
        this._width  = (width  > MINIMAL_LENGTH)? width : DEFAULT_LENGTH; //If The Value Is "Good", Assign It
        this._length = (length > MINIMAL_LENGTH)? length: DEFAULT_LENGTH; //If The Value Is "Good", Assign It
        this._base   = new Point3D(base);
    }
    /**
     * Copies Box to a new Object
     * @param other The Box to Copy
     * */
    public Box3D (Box3D other) {
        this._base   = new Point3D(other._base); //Utilizing The Copy Constructor Of Point 3D To Avoid Aliasing
        this._length = other._length;
        this._width  = other._width;
        this._height = other._height;
    }



    // Methods - Accessors
    /**
     * Gets the Length value of the Box
     * @return The Length of the Box
     * */
    public int getLength(){
            return(this._length);
    }
    /**
     * Gets the Width value of the Box
     * @return The Width of the Box
     * */
    public int getWidth(){
            return(this._width);
    }
    /**
     * Gets the Height value of the Box
     * @return The Height of the Box
     * */
    public int getHeight(){
            return(this._height);
    }
    /**
     * Gets the Point 3D Object of the Down Left Front Point of the Box
     * @return The Length of the Box
     * */
    public Point3D getBase(){
            return new Point3D(this._base); //Utilizing The Copy Constructor Of Point 3D To Avoid Aliasing
    }

    // Methods - Mutators
    /**
     * Sets the Length value of the Box
     * @param num The Length to Set
     * */
    public void setLength (int num){
            this._length = (num > MINIMAL_LENGTH)? num: DEFAULT_LENGTH;  //If The Value Is "Good", Set It
    }
    /**
     * Sets the Width value of the Box
     * @param num The Width to Set
     * */
    public void setWidth (int num){
            this._width  = (num > MINIMAL_LENGTH)? num: DEFAULT_LENGTH; //If The Value Is "Good", Set It
    }
    /**
     * Sets the Height value of the Box
     * @param num The Height to Set
     * */
    public void setHeight (int num){
            this._height = (num > MINIMAL_LENGTH)? num: DEFAULT_LENGTH; //If The Value Is "Good", Set It
    }
    /**
     * Sets the Base Point of the Box
     * @param p The Base Point to Set
     * */
    public void setBase (Point3D p){
            this._base = new Point3D(p); //Utilizing The Copy Constructor Of Point 3D To Avoid Aliasing
    }

    // Methods - Misc.
    /**
     * Return the Point as String Format like this : The base point Is XXX, length = XXX, width = XXX, height = XXX
     * @return The Formatted String
     * */
    public String toString(){
            return("The base point is " + this._base.toString() + ", " + "length = " + this._length + ", " + "width = " +
                                                                     + _width + ", " + "height = " + _height);
    }
    /**
     * Checks if the Box that Called the method is Equal to the Other Box
     * @param other The other Box
     * @return true or false
     * */
    public boolean equals(Box3D other) {
            return this._base.equals(other._base) && this._length == other._length && this._height == other._height && this._width == other._width;
    }
    /**
     * Moves the box in the (x,y,z) coordinate system to (x+dx ,y+dy ,z+dz) without changing the box dimensions
     * @param dx The x Amount to Move
     * @param dy The y Amount to Move
     * @param dz The z Amount to Move
     * @return The Moved Box
     * */
    public Box3D move(double dx, double dy, double dz){
            Box3D movedBox = new Box3D(this); // Copy The Current Box
            movedBox._base.move(dx, dy, dz); // Move It's Base
            return movedBox;
    }
    /**
     * Calculates and returns the upper-right-back point of this box
     * @return The upper-right-back Point
     * */
    public Point3D getUpRightBackPoint(){
            return new Point3D((this._base.getX() - this._length), (this._base.getY() + this._width), (this._base.getZ() + this._height));
    }
    /**
     * Calculates and returns the center point of the box
     * @return The Center Point
     * */
    public Point3D getCenter(){
            return new Point3D(this._base.getX() - (this._length / 2.0), this._base.getY() + (this._width / 2.0), this._base.getZ() + (this._height / 2.0));
    }
    /**
     * Computes the distance between two boxes based on the distance of their center points
     * @param other The Other Box
     * @return The Distance
     * */
    public double distance(Box3D other){
            return this.getCenter().distance(other.getCenter()); // The Distance Between Boxes Is The Distance Between Their Centers
    }
    /**
     * Computes the volume of the box
     * @return The Volume
     * */
    public int getVolume(){
            return this._height * this._width * this._length; // V = Height * Width * Length
    }
    /**
     * Computes the surface area of a box
     * @return The Surface Area
     * */
    public int getSurfaceArea(){
            return 2*this._length*this._width + 2*this._length*this._height + 2*this._width*this._height; // S = 2(Length * Width + Width * Height + Length * Height)
    }
    /**
     * Determines whether this box has a greater volume in compare to other box
     * @param other The Other Box
     * @return True or False
     * */
    public boolean isLargerCapacity (Box3D other){
            return this.getVolume() > other.getVolume();
    }
    /**
     * Determines whether this box can contain the other box
     * @param other The Other Box
     * @return True or False
     * */
    public boolean contains (Box3D other){
            return this._length > other._length && this._width > other._width && this._height > other._height;
    }
    /**
     * Checks if this box is above the other box
     * @return True or False
     * @param other The Other Box
     * */
    public boolean isAbove (Box3D other){
            return this.getBase().isAbove(other.getUpRightBackPoint());
    }

} // End of class Box3D
