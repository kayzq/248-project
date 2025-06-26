public class LinkedList {
    private Node firstNode;
    private Node currentNode;
    private Node lastNode;

    public LinkedList(){
        // Default constructor
        firstNode = null;
        currentNode = null;
        lastNode = null;
    }
    public void insertAtBack(Object elem){
        // Insert an element at the back of the linked list
        Node newNode = new Node(elem);
        if (firstNode == null) {
            firstNode = newNode;
            lastNode = newNode;
        } else {
            lastNode.setNext(newNode);
            lastNode = newNode;
        }
       
    }

    public void insertAtFront(Object elem){
        // Insert an element at the front of the linked list
        Node newNode = new Node(elem, firstNode);
        if (firstNode == null) {
            lastNode = newNode;
        }
        firstNode = newNode;
    }

   public Object getFirst(){
    // Initialize iteration and return the first element
    currentNode = firstNode;
    if (currentNode != null) {
        return currentNode.getData();
    }
    return null;
    }

    public Object getLast(){
          // Return the last element of the linked list
          if (lastNode != null) {
                return lastNode.getData();
          }
          return null;
     }

    public Object getNext(){
         // Get the next element of the linked list
        if (currentNode != null) {
            currentNode = currentNode.getNext();
            if (currentNode != null) {
                return currentNode.getData();
            }
        }
        return null;
      
    }
    public void clear(){
        // Clear the linked list
        firstNode = null;
        currentNode = null;
        lastNode = null;
    }

    public Object removeFromFront(){
        // Remove the first element of the linked list
        if (firstNode != null) {
            Object data = firstNode.getData();
            firstNode = firstNode.getNext();
            if (firstNode == null) {
                lastNode = null;
            }
            return data;
        }
        return null;
    }
    public Object removeFromBack(){
        // Remove the last element of the linked list
        if (firstNode == null) {
            return null; // List is empty
        }
        if (firstNode == lastNode) {
            Object data = firstNode.getData();
            clear(); // Clear the list
            return data;
        }
        Node temp = firstNode;
        while (temp.getNext() != lastNode) {
            temp = temp.getNext();
        }
        Object data = lastNode.getData();
        lastNode = temp;
        lastNode.setNext(null);
        return data;
    }
    public Object removeAfter(Object elem){
        // Remove the element after the specified element
        Node temp = firstNode;
        while (temp != null && temp.getNext() != null) {
            if (temp.getData().equals(elem)) {
                Node toRemove = temp.getNext();
                temp.setNext(toRemove.getNext());
                if (toRemove == lastNode) {
                    lastNode = temp; // Update lastNode if we removed the last element
                }
                return toRemove.getData();
            }
            temp = temp.getNext();
        }
        return null; // Element not found
    }

    public String toString() {
        // Convert the linked list to a string representation
        StringBuilder sb = new StringBuilder();
        Node temp = firstNode;
        while (temp != null) {
            sb.append(temp.getData().toString()).append(" -> ");
            temp = temp.getNext();
        }
        sb.append("null");
        return sb.toString();
    }
}
