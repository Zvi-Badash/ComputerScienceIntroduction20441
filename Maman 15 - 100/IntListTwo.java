import java.util.Scanner; // For readToList

/**
 * This Class Represent A Doubly Linked List
 *
 * @author Zvi Badash 214553034
 */
public class IntListTwo {

    // Class Attributes
    private IntNodeTwo _head, _tail;

    // Constructors
    /**
     * This Constructor Will Create An Empty List
     */
    public IntListTwo() {
        _head = null;
        _tail = null;
    }

    /**
     * This Constructor Will Create A List With Given Head And Tail
     *
     * @param h The Head
     * @param t The Tail
     */
    public IntListTwo(IntNodeTwo h, IntNodeTwo t) {
        this._head = h;
        this._tail = t;
    }

    // Methods - Helpers
    private boolean isEmpty() {
        return this._head == null;
    }

    private boolean isOdd(int num) {
        return num % 2 != 0;
    }

    // Methods - Public

    /**
     * This Method Will Add A Number To the List At The Right Location (Sorted By Value)
     * <i>
     *     <br>
     * Time Complexity: O(n),
     * Space Auxiliary: O(1)
     * </i>
     *
     * @param num The Number To Add
     */
    public void addNumber(int num) {
        IntNodeTwo nodeToAdd = new IntNodeTwo(num);

        if (!isEmpty()) { // If The List Is Not Empty
            if (nodeToAdd.getNum() < this._head.getNum()) { // If The Node Should Be Located Before The Head
                nodeToAdd.setNext(this._head);
                this._head.setPrev(nodeToAdd);
                this._head = nodeToAdd;
                return;
            }

            IntNodeTwo beforeNode; // The Node That Come Before The Location nTo Add The Node In
            for (beforeNode = this._head;
                 beforeNode.getNext() != null && beforeNode.getNext().getNum() < nodeToAdd.getNum();
                 beforeNode = beforeNode.getNext())
                ; // An Empty For Loop To Make beforeNode Point At The Correct Location

            /*
             * The Following Block Is To Insert nodeToAdd After beforeNode
             * */
            nodeToAdd.setNext(beforeNode.getNext());
            nodeToAdd.setPrev(beforeNode);
            beforeNode.setNext(nodeToAdd);

            /*
             * To Handle The Tail
             * */
            if (nodeToAdd.getNext() == null)
                this._tail = nodeToAdd;
            else
                nodeToAdd.getNext().setPrev(nodeToAdd);

        } else {
            // If The List Is Empty Then Both _head And _tail Shall Point At nodeToAdd
            this._head = nodeToAdd;
            this._tail = nodeToAdd;
        }
    }

    /**
     * This Method Will Remove The First Occurrence Of a Number From The List
     * <i>
     *     <br>
     * Time Complexity: O(n),
     * Space Auxiliary: O(1)
     * </i>
     *
     * @param num The Number To Remove
     */
    public void removeNumber(int num) {
        if (!isEmpty()) {
            if (this._head.getNum() == num) // If num Is At The Head
                this._head = this._head.getNext(); // Remove Head

            else {
                IntNodeTwo beforeNode = this._head;
                while (beforeNode.getNext() != null)
                    if (beforeNode.getNext().getNum() == num) { // If The Next Node Has num
                        beforeNode.setNext(beforeNode.getNext().getNext()); // Remove It
                        return; // End The Method

                    } else
                        beforeNode = beforeNode.getNext(); // Keep Going If Needed
            }
        } // If The List Is Empty, There Is Nothing To Remove...
    }

    /**
     * This Method Will Read And Add Numbers To The List Until The User Enter -9999
     * <i>
     *     <br>
     * Time Complexity: O(n * k), k = Number Of Integers To Add,
     * Space Auxiliary: O(1)
     * </i>
     */
    public void readToList() {
        Scanner s = new Scanner(System.in); // The Scanner Object To Read From
        for (;;) {
            int in = s.nextInt(); // Read A Number
            if (in == -9999) // If Need To Break The Loop
                return; // Exit The Method
            else
                this.addNumber(in); // If Not -9999, Add The Number
        }
    }

    /**
     * This Method Will Return The Length Of The List
     * <i>
     *     <br>
     * Time Complexity: O(n),
     * Space Auxiliary: O(1)
     * </i>
     *
     * @return The Length
     */
    public int length() {
        int len = 0;
        for (IntNodeTwo node = this._head; node != null; node = node.getNext()) // If The Current Node Is Not null
            len++; // Increment len

        return len;
    }

    /**
     * This Method Will Return The List Inside Curly Brackets Separated With Commas
     * <i>
     *     <br>
     * Time Complexity: O(n),
     * Space Auxiliary: O(1)
     * </i>
     *
     * @return The List As String
     */
    public String toString() {
        String result = "{";
        IntNodeTwo node;

        if (!isEmpty())
            for (node = this._head; node != null; node = node.getNext())
                result += node.getNum() + ", "; // Add The Number + ", "
        else
            return "{}"; // Empty List Representation

        return result.substring(0, result.length() - 2) + "}"; // To Remove The Last Redundant Two Characters And Add }
    }

    /**
     * This Method Will Return The Sum Of The List Elements
     * <i>
     *     <br>
     * Time Complexity: O(n),
     * Space Auxiliary: O(1)
     * </i>
     *
     * @return The Sum
     */
    public int sum() {
        int totalSum = 0;

        if (!isEmpty())
            for (IntNodeTwo current = this._head; current != null; current = current.getNext())
                totalSum += current.getNum(); // Add The Current Node To The Sum

        return totalSum; // If Empty, totalSum = 0

    }

    /**
     * This Method Will Return The Length Of The Longest Sub List With An Even Sum In The List
     * <i>
     *     <br>
     * Time Complexity: O(n),
     * Space Auxiliary: O(1)
     * </i>
     *
     * @return The Length Of The Longest Sub List With An Even Sum In The List
     */
    public int maxLength() {
        // *** First Case: Only One Element ***
        if (this.length() == 1 && isOdd(this._head.getNum()))
            return 0;

            // *** Second Case: The Sum Of The List Is Even ***
        else if (!isOdd(this.sum())) // If The sum Is Even, Then Return length (The Sum Of The List Is Even)
            return this.length();

            // *** Third Case: The Sum Of The List Is Odd And The First / Last Element Is Odd ***
            // If Gotten Here totalSum Is Odd
        else if (isOdd(this._head.getNum()) || isOdd(this._tail.getNum()))
            return this.length() - 1;

            // *** Fourth Case: The Sum Of The List Is Odd And Both The First And The Last Elements Are Even ***
        else {
            int leftEvenCounter = 0; // Will Count How Many Even Numbers Are In The Array Until Reached An Odd One (From The Left)
            for (IntNodeTwo q = this._head; q != null && !isOdd(q.getNum()); q = q.getNext())
                leftEvenCounter++;

            int rightEvenCounter = 0; // Will Count How Many Even Numbers Are In The Array Until Reached An Odd One (From The Right)
            for (IntNodeTwo p = this._tail; p != null && !isOdd(p.getNum()); p = p.getNext())
                rightEvenCounter++;

            return Math.max(this.length() - leftEvenCounter - 1, this.length() - rightEvenCounter - 1);
        }
    }

    /**
     * This Method Will Return If There Is A Sub List In The List With A Given Average
     * <i>
     *     <br>
     * Time Complexity: O(n),
     * Space Auxiliary: O(1)
     * </i>
     *
     * @param num The Given Average
     * @return If There Is A Sub List In The List With A Given Average
     */
    public boolean isAverage(double num) {
        if (!isEmpty()) { // If The List Is Empty Then There Are No Sub Lists
            final double tolerance = 0.0000001; // To Compare The Averages
            double currentAvg;
            double currentSum = this.sum(), nodesInAvg = this.length(); // The Current Sum And The Current Number Of Nodes In The average
            IntNodeTwo left = this._head, right = this._tail; // The Two Nodes To Traverse The List

            while (nodesInAvg >= 1) { // While The Number Of Nodes In The Current Average Is Bigger, Or Equal To 1...
                currentAvg = currentSum / nodesInAvg; // Calculate The Current Sub List Average

                if (currentAvg > num) { // If The Current Average Is Bigger Then The Given Average
                    // Update The Sum, Length And Right Pointer
                    currentSum -= right.getNum();
                    nodesInAvg--;
                    right = right.getPrev();
                } else if (currentAvg < num) { // If The Current Average Is Bigger Then The Given Average
                    // Update The Sum, Length And Left Pointer
                    currentSum -= left.getNum();
                    nodesInAvg--;
                    left = left.getNext();
                } else if (currentAvg - num < tolerance) // If The Current Average Is The Given Average Return True
                    return true;
            }
        }
        return false; // The List Is Either Empty Or Does Not Contain A "Good" Sub List
    }
}
