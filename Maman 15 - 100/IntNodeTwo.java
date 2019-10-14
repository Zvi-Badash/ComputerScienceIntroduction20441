public class IntNodeTwo {
    // Class Attributes
    private int _num;
    private IntNodeTwo _next, _prev;

    // Constructors
    public IntNodeTwo(int n) {
        _num = n;
        _next = null;
        _prev = null;
    }

    public IntNodeTwo(int num, IntNodeTwo n, IntNodeTwo p) {
        _num = num;
        _next = n;
        _prev = p;
    }

    // Methods
    public int getNum() {
        return this._num;
    }

    public void setNum(int n) {
        this._num = n;
    }

    public IntNodeTwo getNext() {
        return this._next;
    }

    public void setNext(IntNodeTwo node) {
        this._next = node;
    }

    public IntNodeTwo getPrev() {
        return this._prev;
    }

    public void setPrev(IntNodeTwo node) {
        this._prev = node;
    }

}
