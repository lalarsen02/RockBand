public class RingBuffer {

    // Instance Variables
    private double[] rb;   // items in the buffer
    private int first;     // index for the next dequeue or peek
    private int last;      // index for the next enqueue
    private int size;      // number of items in the buffer

    // Creates an empty ring buffer with the specified capacity.
    public RingBuffer(int capacity) {
        rb = new double[capacity];
        first = 0;
        last = 0;
        size = 0;
    }

    // Returns the capacity of this ring buffer.
    public int capacity() {
        return rb.length;
    }

    // Returns the number of items currently in this ring buffer.
    public int size() {
        return size;
    }

    // Is this ring buffer empty (size equals zero)?
    public boolean isEmpty() {
        return size == 0;
    }

    // Is this ring buffer full (size equals capacity)?
    public boolean isFull() {
        return size == rb.length;
    }

    // Adds item x to the end of this ring buffer.
    public void enqueue(double x) {
        if (isFull()) {
            throw new RuntimeException("Queue is already full");
        }
        else {
            rb[last] = x;
            if (last == rb.length - 1) {
                last = 0;
            }
            else {
                last += 1;
            }
            size += 1;
        }
    }

    // Deletes and returns the item at the front of this ring buffer.
    public double dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is already empty");
        }
        else {
            if (first == rb.length) {
                first = 1;
            }
            else first += 1;
            size -= 1;
        }
        return rb[first - 1];
    }

    // Returns the item at the front of this ring buffer.
    public double peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is already empty");
        }
        else {
            if (size == 0) {
                return 0;
            }
            if (first == rb.length) {
                return rb[0];
            }
            else return rb[first];
        }
    }

    // Tests this class by directly calling all instance methods.
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        RingBuffer buffer = new RingBuffer(n);
        for (int i = 1; i <= n; i++) {
            buffer.enqueue(i);
        }
        StdOut.println(buffer.capacity());

        double t = buffer.dequeue();
        buffer.enqueue(t);
        StdOut.println("Size after wrap-around is " + buffer.size());

        StdOut.println(buffer.isEmpty());
        StdOut.println(buffer.isFull());

        while (buffer.size() >= 2) {
            double x = buffer.dequeue();
            double y = buffer.dequeue();
            buffer.enqueue(x + y);
        }
        StdOut.println(buffer.peek());
    }
}
