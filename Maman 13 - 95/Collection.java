/**
 * This Class Represents a Collection (Array) Of Box3D Objects
 *
 * @author Zvi Badash 214553034
 */
@SuppressWarnings({"unused", "UnusedReturnValue", "StringConcatenationInLoop", "ManualArrayCopy"})

public class Collection {

    private final int MAX_NUM_BOXES = 100;
    //Class Attributes & Constants
    private Box3D[] _boxes;
    private int _noOfBoxes;


    //Constructor

    /**
     * Construct a 100 - Sized Collection of Uninitialized Box3D Objects
     */
    public Collection() {
        this._boxes = new Box3D[MAX_NUM_BOXES]; // Initialize The Biggest Possible Array
        this._noOfBoxes = 0; // Set noOfBoxes to 0 (no Actual Boxes Yet...)
    }


    //Methods - private
    public void sortByVolume() {
        //***
        // This Bubble Sort Is To Ensure That After Every Call To #addBox The Collection Is Sorted By Volume
        // This Is Like Inserting The Box In The Right Index In The First Place
        //***

        // For The Swap
        Box3D temp;
        // Bubble Sort
        for (int i = 0; i < this._noOfBoxes; i++)
            for (int j = 0; j < this._noOfBoxes; j++)
                if (this._boxes[j].isLargerCapacity(this._boxes[j + 1])) { // If Need To Swap
                    // Swapping Values
                    temp = this._boxes[j + 1];
                    this._boxes[j + 1] = this._boxes[j];
                    this._boxes[j] = temp;
                }
    }

    private boolean inBound(int x, int y) {
        return (x < MAX_NUM_BOXES && y < MAX_NUM_BOXES && x >= 0 && y >= 0); // Check If i, j Are Inside The Collection
    }

    private Box3D[] createSubArray(int first, int second) {
        Box3D[] subArray;
        int smallIndex;
        int bigIndex;

        if (!inBound(first, second)) // If The Values Cannot Be Indexes In The Collection...
            return null;

        // Determine What is The Bigger Index, And What is The Smaller
        if (first > second) {
            bigIndex = first;
            smallIndex = second;
        } else if (first < second) {
            bigIndex = second;
            smallIndex = first;
        } else {
            smallIndex = first;
            bigIndex = first;
        }
        // Initialize The Array
        subArray = new Box3D[bigIndex - smallIndex + 1];
        // Populate The Array
        for (int k = 0; k < subArray.length; k++) {
            subArray[k] = this._boxes[k + smallIndex];
        }
        return subArray;
    }

    private int biggestLengthInArray(Box3D[] arr) {
        int biggest = 0;
        for (int i = 1; i < arr.length; i++) { // Run On Every Box In The Array
            if (arr[i].getLength() > biggest) // If The Current Box's Length is Bigger Then The Current Biggest Length
                biggest = arr[i].getLength(); // The New Biggest Length is The Current Length
        }
        return biggest;
    }

    private int biggestWidthInArray(Box3D[] arr) {
        int biggest = 0;
        for (int i = 1; i < arr.length; i++) { // Run On Every Box In The Array
            if (arr[i].getWidth() > biggest) // If The Current Box's Width is Bigger Then The Current Biggest Width
                biggest = arr[i].getWidth(); // The New Biggest Width is The Current Width
        }
        return biggest;
    }

    private int biggestHeightInArray(Box3D[] arr) {
        int biggest = 0;
        for (int i = 1; i < arr.length; i++) { // Run On Every Box In The Array
            if (arr[i].getHeight() > biggest) // If The Current Box's Width is Bigger Then The Current Biggest Width
                biggest = arr[i].getHeight(); // The New Biggest Height is The Current Height
        }
        return biggest;
    }


    //Methods - public

    /**
     * Adds a Box With a Base Point, Length, Width and Height To Its Place in The Collection (Location By Volume) And return true on Success
     *
     * @param base   The Base Point As a Point3D Object
     * @param length The Length
     * @param width  The Width
     * @param height The Height
     * @return True or False (Succeeded or Failed)
     * @see #sortByVolume()
     */
    public Boolean addBox(Point3D base, int length, int width, int height) {
        if (this._noOfBoxes == MAX_NUM_BOXES) // If Not Enough Space
            return false;

        this._boxes[this._noOfBoxes] = new Box3D(base, length, width, height); // Put The New Box In The First Available Index, Utilize Box3D Constructor
        this.sortByVolume(); // Make Sure The Boxes Are Still Sorted
        this._noOfBoxes++; // Increment _noOfBoxes (Added A Box)
        return true; // If Gotten Here, The Box Was Added
    }

    /**
     * Returns The Box With The Most Upper Base Point in The Collection
     *
     * @return The Box With The Most Upper Base Point
     */
    public Box3D mostUpperBaseCorner() {
        if (this._noOfBoxes == 0) // If The Collection Is Empty
            return null;

        Box3D upper = this._boxes[0]; // Assume The Most Upper Box Is The First One
        for (int i = 1; i < this._noOfBoxes; i++) { // Run On Every Box In The Collection
            if (this._boxes[i].getBase().isAbove(upper.getBase())) // If Current Box is Above The Current Most Up Box...
                upper = this._boxes[i]; // New Upper Box is The Box at The Index i
        }
        return new Box3D(upper);    // Utilizing The Copy Constructor To Avoid Aliasing
    }

    /**
     * Calculate And Return The Total Surface Area of The Boxes In The Collection
     *
     * @return The Sum of The Surface Area of The Boxes In The Collection
     */
    public double totalSurfaceArea() {
        double surfaceArea = 0; // The Total Surface Area
        for (int i = 0; i < this._noOfBoxes; i++) { // Run On Every Box In The Collection
            surfaceArea += this._boxes[i].getSurfaceArea(); // Adds The Current Box Surface Area to The Total Surface Area
        }
        return surfaceArea;
    }

    /**
     * Find And Return The Longest Distance Between any Two Boxes's Center Points in The Collection
     *
     * @return The Longest Distance Between any Two Boxes's Center Points
     */
    public double longestDistance() {
        double biggestDistance = 0; // The Current Biggest Distance
        if (this._noOfBoxes < 2)
            return biggestDistance;
        // Run on Every Pair Of Boxes
        for (int i = 0; i < this._noOfBoxes; i++)
            for (int k = i; k < this._noOfBoxes; k++)
                if (this._boxes[i].distance(this._boxes[k]) > biggestDistance) // If The Current Distance Is Bigger Then The Biggest Distance
                    biggestDistance = this._boxes[i].distance(this._boxes[k]);

        return biggestDistance;
    }

    /**
     * Gets The Number Of Boxes In The Array That Can Contain A Given Box
     *
     * @param box The Given Box
     * @return The Number Of Box Containing It
     */
    public int howManyContains(Box3D box) {
        int counter = 0; // The Number Of Boxes That Can Contain The Parameter box
        for (int i = 0; i < _noOfBoxes; i++) // For All Of The Boxes In The Collection
            if (_boxes[i].contains(box)) // If the Current Box Can Contain The Parameter box
                counter++; // Increment Counter
        return counter;
    }

    /**
     * Gets The Volume Of The Smallest Box That Can Contain All Of The Boxes In The Collection Between The Indexes i and j (Inclusive)
     *
     * @param i First Index
     * @param j Second Index
     * @return Volume Of The Smallest Box That Can Contain All Of The Boxes In The Collection Between The Indexes i and j (Inclusive)
     * @see #createSubArray(int, int)
     * @see #biggestHeightInArray(Box3D[])
     * @see #biggestWidthInArray(Box3D[])
     * @see #biggestLengthInArray(Box3D[])
     */
    public int volumeOfSmallestBox(int i, int j) {
        Box3D[] subArray = createSubArray(i, j); // Create The Sub Array
        if (subArray == null) // If The Sub Array Could Not Of Being Created
            return 0; // Return The Default Value
        return ((biggestLengthInArray(subArray) + 1) * (biggestWidthInArray(subArray) + 1) * (biggestHeightInArray(subArray) + 1)); // It's The Smallest Volume Possible That Will Still contain Every Box In The Sub Array
    }

    /**
     * Gets An Array With All Of The Boxes In The Collection In It
     *
     * @return Array With All Of The Boxes In The Collection In It
     */
    public Box3D[] getBoxes() {
        Box3D[] boxes = new Box3D[_noOfBoxes]; // The Array To Return
        for (int i = 0; i < _noOfBoxes; i++) // For Every Box In The Collection
            boxes[i] = new Box3D(this._boxes[i]); // Put The Elements Of _boxes In boxes
        return boxes;
    }

    /**
     * Gets The Number Of Boxes In The Collection
     *
     * @return Number Of Boxes In The Collection
     */
    public int getNumOfBoxes() {
        return _noOfBoxes; // The Attribute That Stand For The Number Of Boxes In The Collection
    }

    /**
     * Returns The Collection In a String Format
     *
     * @return The Collection In a String Format
     */
    public String toString() {
        String arrayAsString = "";
        String EMPTY_ARRAY_MESSAGE = "The Array is Empty !";
        if (_noOfBoxes == 0) // If The Array Is Empty...
            return EMPTY_ARRAY_MESSAGE;

        for (int i = 0; i < _noOfBoxes; i++) // Run On Every Box In The The Collection
            arrayAsString += "Box no. " + (i + 1) + ": " + _boxes[i].toString() + "\n"; // Append The Needed Strings To The arrayAsString Variable
        return arrayAsString;
    }

}
