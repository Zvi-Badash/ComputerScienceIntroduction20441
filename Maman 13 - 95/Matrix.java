/**
 * This Class Represent A Matrix Of Values Between 0 To 255, Including.
 *
 * @author Zvi Badash 214553034
 */
@SuppressWarnings({"unused", "UnusedReturnValue", "StringConcatenationInLoop", "ManualArrayCopy"})
public class Matrix {

    final private int MAX_MAT_VAL = 255;
    final private int MIN_MAT_VAL = 0;
    // Class Attributes
    private int[][] _matrix;


    // Constructors

    /**
     * Constructs And Initialize A Matrix With The Values From The 2 Dimensional Array Passed To It
     *
     * @param array The 2DArray
     */
    public Matrix(int[][] array) {
        this._matrix = new int[array.length][array[0].length]; // Create The Matrix
        // Populate The Matrix (Copy The 2D Array Given To The _matrix Attribute)
        for (int i = 0; i < array.length; i++)
            for (int j = 0; j < array[0].length; j++)
                this._matrix[i][j] = array[i][j];
    }

    /**
     * Constructs And Initialize A Matrix With The Given Sizes, Filled With 0's
     *
     * @param size1 The Number Of Rows
     * @param size2 The Number Of Columns
     */
    public Matrix(int size1, int size2) {
        this._matrix = new int[size1][size2]; // Create The Matrix
        // Populate The Matrix With 0's
        for (int i = 0; i < size1; i++)
            for (int j = 0; j < size2; j++)
                this._matrix[i][j] = 0;
    }


    // Methods - private
    private Matrix padArray() {
        /*
         * The Reason For The Padding Is To Avoid "ArrayIndexOutOfBoundsException" That Might Come Up
         * If We Treat Every Cell As If He Had 8 "Neighbors" When He Dose'nt, This Method Make Sure Each And Every Cell Have
         * Exactly 8 "Neighbors".
         * */
        final int PADDING_VALUE = -1; // The Value To Pad The Matrix With
        Matrix paddedMatrix = new Matrix(this._matrix.length + 2, this._matrix[0].length + 2); // Create The Padded Matrix
        // Populate The Array With -1's.
        for (int i = 0; i < paddedMatrix._matrix.length; i++)
            for (int j = 0; j < paddedMatrix._matrix[0].length; j++)
                paddedMatrix._matrix[i][j] = PADDING_VALUE;
        // Populate The Array With The Current Matrix's Array (Overwrite Some Of The -1's With The this._matrix Array)
        for (int i = 0; i < this._matrix.length; i++)
            for (int j = 0; j < this._matrix[0].length; j++)
                paddedMatrix._matrix[i + 1][j + 1] = this._matrix[i][j];

        return paddedMatrix;
    }

    private int averageCell(int row, int col) {
        /*
         * This Method Calculate The Average Of Every Cell And His 8 "Neighbors".
         * */
        int sum = this._matrix[row][col]; // Include The Current Cell In The Average
        int average;
        int counter = 1; // Include The Current Cell In The Counting
        /*
         * If The Current Value Of The Cell Is Greater Then OR Equal Then 0, Include It In The Sum
         * If Its Not, Add 0 To The Sum (If The Value is => 0 Then It Is Part Of The Original Matrix, Not Part Of The Padding)
         * */
        sum += (this._matrix[row + 1][col] >= MIN_MAT_VAL) ? this._matrix[row + 1][col] : 0;
        sum += (this._matrix[row - 1][col] >= MIN_MAT_VAL) ? this._matrix[row - 1][col] : 0;
        sum += (this._matrix[row][col + 1] >= MIN_MAT_VAL) ? this._matrix[row][col + 1] : 0;
        sum += (this._matrix[row][col - 1] >= MIN_MAT_VAL) ? this._matrix[row][col - 1] : 0;
        sum += (this._matrix[row + 1][col + 1] >= MIN_MAT_VAL) ? this._matrix[row + 1][col + 1] : 0;
        sum += (this._matrix[row - 1][col - 1] >= MIN_MAT_VAL) ? this._matrix[row - 1][col - 1] : 0;
        sum += (this._matrix[row + 1][col - 1] >= MIN_MAT_VAL) ? this._matrix[row + 1][col - 1] : 0;
        sum += (this._matrix[row - 1][col + 1] >= MIN_MAT_VAL) ? this._matrix[row - 1][col + 1] : 0;
        /*
         * If The Current Value Of The Cell Is Greater Then OR Equal Then 0, Add 1 To The Counting
         * If Its Not, Add 0 To The Counting (If The Value is => 0 Then It Is Part Of The Original Matrix, Not Part Of The Padding)
         * */
        counter += (this._matrix[row + 1][col] >= MIN_MAT_VAL) ? 1 : 0;
        counter += (this._matrix[row][col + 1] >= MIN_MAT_VAL) ? 1 : 0;
        counter += (this._matrix[row - 1][col] >= MIN_MAT_VAL) ? 1 : 0;
        counter += (this._matrix[row][col - 1] >= MIN_MAT_VAL) ? 1 : 0;
        counter += (this._matrix[row + 1][col + 1] >= MIN_MAT_VAL) ? 1 : 0;
        counter += (this._matrix[row - 1][col - 1] >= MIN_MAT_VAL) ? 1 : 0;
        counter += (this._matrix[row + 1][col - 1] >= MIN_MAT_VAL) ? 1 : 0;
        counter += (this._matrix[row - 1][col + 1] >= MIN_MAT_VAL) ? 1 : 0;

        average = sum / counter; // Calculating The Average, In Int Type (INT / INT = INT)
        return average;
    }


    // Methods - public

    /**
     * Returns The Matrix As A String Format
     *
     * @return The Matrix As A String Format
     */
    public String toString() {
        String matrixAsString = "";
        for (int i = 0; i < this._matrix.length; i++) {
            for (int j = 0; j < this._matrix[0].length; j++) {
                matrixAsString += this._matrix[i][j];
                // This Is To Make Sure That a (\t) Wan't Appear At The Of Each Line
                if (j != _matrix[0].length - 1) // If NOT End Of Line
                    matrixAsString += "\t"; // Append TAB
            }
            matrixAsString += "\n"; // Next Line
        }
        return matrixAsString;
    }

    /**
     * Returns A New Matrix But Every Value In The Matrix Is The Complement To 255.
     *
     * @return The "Negative" Matrix
     */
    public Matrix makeNegative() {
        Matrix negMatrix = new Matrix(this._matrix); // Create The Negative Matrix
        final int ROWS_OF_NEGATIVE = negMatrix._matrix.length;
        final int COLS_OF_NEGATIVE = negMatrix._matrix[0].length;

        for (int i = 0; i < ROWS_OF_NEGATIVE; i++)
            for (int j = 0; j < COLS_OF_NEGATIVE; j++)
                negMatrix._matrix[i][j] = MAX_MAT_VAL - negMatrix._matrix[i][j]; // Run On Every In negMatrix Cell And Exchange It With 255 - VALUE, (VALUE = The Current Cell)

        return negMatrix;
    }

    /**
     * Returns A New Matrix But After Applying A Image Mean Filter On It
     *
     * @return The Filtered Matrix
     * @see <a href="https://www.digimizer.com/manual/m-image-filtermean.php"><strong>More About Average Flitering</strong></a>
     * @see #padArray()
     * @see #averageCell(int, int)
     */
    public Matrix imageFilterAverage() {
        Matrix paddedMatrix = this.padArray(); // Create The Padded Matrix
        Matrix filteredMatrix = new Matrix(this._matrix.length, this._matrix[0].length); // Create The Filtered Matrix
        final int ROWS_OF_FILTERED = filteredMatrix._matrix.length;
        final int COLS_OF_FILTERED = filteredMatrix._matrix[0].length;

        for (int i = 0; i < ROWS_OF_FILTERED; i++)
            for (int j = 0; j < COLS_OF_FILTERED; j++)
                filteredMatrix._matrix[i][j] = paddedMatrix.averageCell(i + 1, j + 1); // Run On Every Cell Starting From (1, 1) Because Of The Padding. And Putting Its Average In The Filtered Matrix

        return filteredMatrix;
    }

    /**
     * Returns A New Matrix, Rotated Clockwise
     *
     * @return The Rotated Matrix
     */
    public Matrix rotateClockwise() {
        Matrix rotatedMatrix = new Matrix(this._matrix[0].length, this._matrix.length); // The Rotated Matrix
        final int ROWS_OF_ORIGINAL = this._matrix.length;
        final int ROWS_OF_ROTATED = rotatedMatrix._matrix.length;
        final int COLS_OF_ROTATED = rotatedMatrix._matrix[0].length;
        for (int i = 0; i < ROWS_OF_ROTATED; i++)
            for (int j = 0; j < COLS_OF_ROTATED; j++)
                rotatedMatrix._matrix[i][j] = this._matrix[ROWS_OF_ORIGINAL - 1 - j][i]; // Run On Every Cell And Transfer it To The New "Rotated" Location
        return rotatedMatrix;
    }

    /**
     * Returns A New Matrix, Rotated Counter Clockwise
     *
     * @return The Rotated Matrix
     * @see #rotateClockwise() Is Used
     */
    public Matrix rotateCounterClockwise() {
        Matrix rotatedMatrix = new Matrix(this._matrix[0].length, this._matrix.length); // The Rotated Matrix
        final int COLS_OF_ORIGINAL = this._matrix[0].length;
        final int ROWS_OF_ROTATED = rotatedMatrix._matrix.length;
        final int COLS_OF_ROTATED = rotatedMatrix._matrix[0].length;
        for (int i = 0; i < ROWS_OF_ROTATED; i++)
            for (int j = 0; j < COLS_OF_ROTATED; j++)
                rotatedMatrix._matrix[i][j] = this._matrix[j][COLS_OF_ORIGINAL - 1 - i]; // Run On Every Cell And Transfer it To The New "Rotated" Location
        return rotatedMatrix;
    }

}

