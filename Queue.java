public class Queue {
    private LinkedList queueList;
    private int size;

    public Queue() {
        queueList = new LinkedList();
        size = 0;
    }

    public void enqueue(Object elem) {
        queueList.insertAtBack(elem);
        size++;
    }

    public Object dequeue() {
        if (size > 0) {
            size--;
            return queueList.removeFromFront();
        }
        return null;
    }

    public Object getFront() {
        return queueList.getFirst();
    }
    public Object getLast() {
        return queueList.getLast();
    }
    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }
    
}