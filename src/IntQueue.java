public class IntQueue implements Traversable<Integer> {

    private int[] elements;
    private int front;
    private int rear;
    private boolean empty;

    private int cursor;
    private boolean start; // true at the start of a traversal

    public IntQueue(int capacity) {
        elements = new int[capacity];
        rear = -1;
        front = -1;
    }

    public void enqueue(int element) {
        if(isFull())
            throw new QueueOverflowException();
//        rear++;
//        if(rear == elements.length)
//            rear = 0;
        rear = (rear + 1) % elements.length;
        elements[rear] = element;
        empty = false;
    }

    private boolean isFull() {
        return rear == front && !empty; //rear != -1;
    }

    private boolean isEmpty() {
        return empty; // rear == front && rear == -1;
    }

    public int peek() {
        if(isEmpty())
            throw new QueueUnderflowException();
        return elements[(front + 1) % elements.length];
    }

    public int dequeue() {
        if(isEmpty())
            throw new QueueUnderflowException();
//        front++;
//        if(front == elements.length)
//            front = 0;
        front = (front + 1) % elements.length;
        if(front == rear)
            empty = true;
            //front = rear = -1;
        return elements[front];
    }


    @Override
    public void reset() {
        cursor = front;
        start = !empty;
    }

    @Override
    public boolean hasNext() {
        return cursor != rear && !start;
    }

    @Override
    public Integer next() {
        cursor = (cursor + 1) % elements.length;
        start = false;
        return elements[cursor];
    }
}
