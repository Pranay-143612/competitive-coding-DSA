
class CircularArrayQueue<T> {
    private T[] data;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    @SuppressWarnings("unchecked")
    public CircularArrayQueue(int capacity) {
        this.capacity = capacity;
        data = (T[]) new Object[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int size() {
        return size;
    }

    public void enqueue(T item) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }
        rear = (rear + 1) % capacity;
        data[rear] = item;
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        T item = data[front];
        data[front] = null; // Optional: clear reference
        front = (front + 1) % capacity;
        size--;
        return item;
    }
    public T front() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return data[front];
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return data[front];
    }


    
}

public class CircularQueueExample {
    public static void main(String[] args) {
        CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(5);
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        System.out.println("Front element: " + queue.peek());
        System.out.println("Dequeued: " + queue.dequeue());
        System.out.println("Front element after dequeue: " + queue.peek());
        queue.enqueue(40);
        queue.enqueue(50);
        queue.enqueue(60);
        
        System.out.println("Queue size: " + queue.size());
        while (!queue.isEmpty()) {
            System.out.println("Dequeued: " + queue.dequeue());
        }
        System.out.println("Queue size: " + queue.size());
    }
}
