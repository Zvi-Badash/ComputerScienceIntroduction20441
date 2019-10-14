/**
 * @author Zvi Natan Badash 214553034
 * */
public class Ex14 {
    // Constants
    private static final int MIN_CHARS_SOLUTIONS = 9; // 9 Is For a + a + a, When a Is a 1-Digit Number
    private static final int MAX_CHARS_SOLUTIONS = 12; //  12 Is For bb + bb + bb, When bb is a 2-Digit Number
    private static int solutionsCount = 0;

    // Methods - Public
    /**
     * This Method Will Get An Array With Values Representing Heights Of A Vessel And Return The Amount Of Water It Can Hold<br>
     *     <i>
     * Time Complexity = O(n)<br>
     * Space Auxiliary = O(1)<br>
     *     </i>
     * <br>
     * @param heights The Heights Array
     * @return The Water Amount It Can Hold
     */
    public static int waterVolume(int[] heights) {
        int totalWaterAmount = 0; // To Hold The Water Amount

        int low = 0, high = heights.length - 1; // The Two Values To Traverse The Array  
        int leftHighest = 0, rightHighest = 0; // The Current Left & Right Max Values For An Element  

        while (low <= high) {
            leftHighest = Math.max(leftHighest, heights[low]); // left Highest Is The Max Between leftHighest And heights[low]  
            rightHighest = Math.max(rightHighest, heights[high]); // right Highest Is The Max Between rightHighest And heights[high]  

            if (rightHighest < leftHighest) { // If The Right Highest Value Is Smaller Then The Greatest Value To The Left  
                totalWaterAmount += rightHighest - heights[high]; // Add To The Total Amount The Current Max Value Right To The Element - Its Value
                high--; // Move high To One Step Back
            } else { // If The Left Highest Value Is Bigger Then The Greatest Value To The Right
                totalWaterAmount += leftHighest - heights[low]; // Add To The Total Amount The Current Max Value Left To The Element - Its Value
                low++; // Move high To One Step Further
            }
        }
        return totalWaterAmount;
    }

    /**
     * This Method Gets A Number num And Then Return The Number Of Solutions <br>
     * For: x1 + x2 + x3 = num <br>
     * The Method Will Also Print All Of The Solutions <br>
     *
     * @param num The Number To Solve For
     * @return The Number Of The Solutions For: x1 + x2 + x3 = num
     */
    public static int solutions(int num) { // This Is A Wrapper Method
        if (num >= 3 && num <= 30) {
            solutionsCount = 0;
            int[] history = new int[num];
            return solutions(num, history, 0);
        }
        return 0;
    }

    /**
     * This Method Will Return The Length Of The Longest Sub Array With An Even Sum In The Array<br>

     * <br>
     * <b> Complexity Questions: </b><br>
     * <b>A</b> - Given An Array Of Integers The Method Will Return  The Length Of The Longest Sub Array With An Even Sum In The Array<br>
     * <br>
     * <b>B</b> - Complexity Of Original What:<br>
     *      <i>
     * Time: O(n^3)<br>
     * Space: O(1)<br>
     *     </i>
     * <br>
     * <b>D</b> - Complexity Of Enhanced What:<br> <i>
     * Time: O(n), Because The Method Only Iterate Over The Array 3 Times, For The Sum And For Counting Even Number From Both Ends<br>
     * Because Nothing Else Is In The Loops, The Complexity Is O(3n + k) = O(n) (Where k Is A Negligible Constant)<br>
     * <br>
     * Space: O(1), The Method Only Define A Constant Number Of Variables <br>
     *     </i>
     *
     * @param a The Array
     * @return The Length Of The Longest Sub Array With An Even Sum In The Array
     */
    public static int what(int[] a) {
        int totalSum = 0;

        for (int i = 0; i < a.length; i++) { // Loop On The Entire Array
            totalSum += a[i]; // Add a[i] To totalSum
        }

        // *** First Case: Only One Element ***
        if (a.length == 1 && isOdd(a[0])) // If There Is Only One Odd Value ...
            return 0; // Return 0 (The Sum Of The Array Is Odd)

            // *** Second Case: The Sum Of The Array Is Even ***
        else if (!isOdd(totalSum)) // If The totalSum Is Even, Then Return length (The Sum Of The Array Is Even)
            return a.length;

            // *** Third Case: The Sum Of The Array Is Odd And The First / Last Element Is Odd ***
            // If Gotten Here totalSum Is Odd
        else if (isOdd(a[0]) || isOdd(a[a.length - 1])) // If The First Or The Last Values Are Odd And The Sum Of The Array Is Odd, Then Return length - 1 (Everything But The Last / First)
            return a.length - 1;

            // *** Fourth Case: The Sum Of The Array Is Odd And Both The First And The Last Elements Are Even ***
        else {
           int leftEvenCounter = 0; // Will Count How Many Even Numbers Are In The Array Until Reached An Odd One (From The Left)
           for (int i = 0; !isOdd(a[i]); i++) // Run Until Hitting An Odd Number
               leftEvenCounter++;

           int rightEvenCounter = 0; // Will Count How Many Even Numbers Are In The Array Until Reached An Odd One (From The Right)
           for (int i = a.length - 1; !isOdd(a[i]); i--) // Run Until Hitting An Odd Number
               rightEvenCounter++;

           return Math.max(a.length - leftEvenCounter - 1, a.length - rightEvenCounter - 1);

        }
    }

    /**
     * This Method Will Return If There Is A Path In The Given Matrix With A Given Sum<br>
     * If There Is One, The Method Will Put 1's In The Corresponding Places In The Path Array<br>
     * @param mat The Matrix To Find A Path In
     * @param sum The Sum To Get To In The Path
     * @param path The Path Matrix To Mark 1's In */
    public static boolean findSum(int[][] mat, int sum, int[][] path){
        return findSum(mat, path, sum, 0, 0);
    }


    // Methods - Private
    /*
     * This Method Will Recursively Print A Given History Array From from To to
     * */
    private static String recursiveToString(int[] arr, int from, int to, String line) {
        if (from < to) {
            line += arr[from] + " + ";
            return recursiveToString(arr, from + 1, to, line);
        }
        return line.substring(0, line.length() - 3); // This Is To Delete The Last 3 Characters (" + ")
    }

    /*
     * This Is The Actual solutions Method, Not The Wrapper
     * */
    private static int solutions(int num, int[] history, int index){
        /*
         * This Is The Base Case, If num = 0 Then Print The "Good" 3 - Lengthed Solutions And Add 1 To solutionsCount
         * */
        if (num == 0) { // If num = 0 Then It Means That history Contain A Solution
            String sol = recursiveToString(history, 0, index, "");
            if (sol.length() >= MIN_CHARS_SOLUTIONS && sol.length() <= MAX_CHARS_SOLUTIONS) { // If The History Array Contain 9 - 12 Characters (9 Characters Is For n + n + n, When n is A Number
                // 12 Is For aa + aa + aa, When aa is A 2 Digit Number )
                System.out.println(sol); // Print The Solution
                solutionsCount++;
            }

        }
        if (num > 0) {
            history[index] = 1; // Try To Put 1 In history
            solutions(num - 1, history, index + 1); // Follow That "Path" Until Success Or Failure

            history[index] = 2; // Try To Put 2 In history
            solutions(num - 2, history, index + 1); // Follow That "Path" Until Success Or Failure

            history[index] = 3; // Try To Put 3 In history
            solutions(num - 3, history, index + 1); // Follow That "Path" Until Success Or Failure

            history[index] = 4; // Try To Put 4 In history
            solutions(num - 4, history, index + 1); // Follow That "Path" Until Success Or Failure

            history[index] = 5; // Try To Put 5 In history
            solutions(num - 5, history, index + 1); // Follow That "Path" Until Success Or Failure

            history[index] = 6; // Try To Put 6 In history
            solutions(num - 6, history, index + 1); // Follow That "Path" Until Success Or Failure

            history[index] = 7; // Try To Put 7 In history
            solutions(num - 7, history, index + 1); // Follow That "Path" Until Success Or Failure

            history[index] = 8; // Try To Put 8 In history
            solutions(num - 8, history, index + 1); // Follow That "Path" Until Success Or Failure

            history[index] = 9; // Try To Put 9 In history
            solutions(num - 9, history, index + 1); // Follow That "Path" Until Success Or Failure

            history[index] = 10; // Try To Put 10 In history
            solutions(num - 10, history, index + 1); // Follow That "Path" Until Success Or Failure

        }
        // Return The Number Of Solutions
        return solutionsCount;
    }

    private static boolean isOdd(int num) {
        return num % 2 != 0;
    } // Time Complexity: O(1)
    /*
    * This Method Will return If A Good Path Exist From A Given Point
    * */
    private static boolean findSumHelper(int[][] mat, int sum, int[][] path , int row, int col){
        if(sum < 0 || !isValid(mat, path, col, row)) // If The Current Path Exceeded The Needed Sum
            return false;

        path[row][col] = 1; // Mark The Cell As Part Of The Path (Until Proven Otherwise)

        if(sum == 0 || sum - mat[row][col] == 0) // If The Sum Of The Path Is sum
            return true;

        if(isValid(mat, path, col, row + 1))
            if(findSumHelper(mat, sum - mat[row][col], path, row + 1, col))
                return true; // If We Can Complete The Path By Going Down

        if(isValid(mat, path, col + 1, row))
            if(findSumHelper(mat, sum - mat[row][col], path, row, col + 1))
                return true; // If We Can Complete The Path By Going Right

        if(isValid(mat, path, col, row - 1))
            if(findSumHelper(mat, sum - mat[row][col], path, row - 1, col))
                return true; // If We Can Complete The Path By Going Up

        if(isValid(mat, path, col - 1, row))
            if(findSumHelper(mat, sum - mat[row][col], path, row, col - 1))
                return true; // If We Can Complete The Path By Going Left

        path[row][col] = 0; // If Gotten Here The Cell Is Not Pat Of The Path
        return false;

    }

    private static boolean isValid(int[][] mat, int[][] path, int col, int row) {
        return (row >= 0 && col >= 0 && row < mat.length && col < mat.length && path[row][col] == 0); // Return If The Indexes Are Inside The Array
    }
    /*
    * This Method Will Run findSumHelper On Every Cell Of The Given Matrix (To Find Paths That Can Begin At Every Cell)
    * */
    private static boolean findSum(int[][] mat, int[][] path, int sum, int row, int col) {
        if (row == mat.length)
            if (col == mat[0].length)
                return false;
            else
                return findSum(mat, path, sum, 0, ++col);
        else
        if (findSumHelper(mat, sum, path, row, col))
            return true;

        return findSum(mat, path, sum, ++row, col);
    }

}