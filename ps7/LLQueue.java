/*
 * LLQueue.java
 *
 * Computer Science 112, Boston University
 */

/*
 * A generic class that implements our Queue interface using a linked list.
 */
public class LLQueue<T> implements Queue<T> {
    // Inner class for a node.  We use an inner class so that the LLQueue
    // methods can access the fields of the nodes.
    private class Node {
        private T item;
        private Node next;
        
        private Node(T i, Node n) {
            item = i;
            next = n;
        }
    }
    
    // the fields of the LLQueue object
    private Node front;    // the node containing the item at the front
    private Node rear;     // the node containing the item at the rear
    
    /*
     * Constructs an LLQueue object for a queue that is initially
     * empty.
     */
    public LLQueue() {
        front = null;
        rear = null;
    }
    
    /* 
     * isEmpty - returns true if the queue is empty, and false otherwise
     */
    public boolean isEmpty() {
        return (front == null);
    }
    
    /*
     * isFull - always returns false, because the linked list can
     * grow indefinitely and thus the queue is never full.
     */
    public boolean isFull() {
        return false;
    }

    /* 
     * insert - adds the specified item at the rear of the queue.
     * Always returns true, because the linked list is never full.
     */
    public boolean insert(T item) {
        Node newNode = new Node(item, null);
        
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        
        return true;
    }
    
    /* 
     * remove - removes the item at the front of the queue and returns a
     * reference to the removed object.  Returns null if the queue is
     * empty.
     */
    public T remove() {
        if (isEmpty()) {
            return null;
        }
        
        T removed = front.item;
        if (front == rear) {       // removing the only item
            front = null;
            rear = null;
        } else {
            front = front.next;
        }
        
        return removed;
    }
    
    /* 
     * peek - returns a reference to the item at the front of the queue
     * without removing it. Returns null if the queue is empty.
     */
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return front.item;
    }
    
    /*
     * toString - converts the stack into a String of the form 
     * {front, one-after-front, two-after-front, ...}
     */
    public String toString() {
        String str = "{";
        
        Node trav = front;
        while (trav != null) {
            str = str + trav.item;
            if (trav.next != null)
                str = str + ", ";
            trav = trav.next;
        }
        
        str = str + "}";
        return str;
    }
    public static void doubleAllQueue(Queue<Object> stack, Object item){
        LLQueue<Object> newQueue = new LLQueue<Object>();
        while(!stack.isEmpty()){
            Object x=stack.remove();
            newQueue.insert(x);
            if(x.equals(item)){
                newQueue.insert(x);
            }
        }
        while(!newQueue.isEmpty()){
            stack.insert(newQueue.remove());
        }
    }
    private static boolean searchItem(Stack<Object> s, Object item){
        if(s.isEmpty()){
            return false;
        }else{
            boolean result = false;
            LLQueue<Object> q = new LLQueue<Object>();
            while(!s.isEmpty()){
                if(item.equals(s.peek())){
                    result=true;
                }
                q.insert(s.pop());
            }
            while(!s.isFull()){
                Object x = q.remove();
                s.push(x);
            }
            while(!q.isFull()){
                Object x = s.pop();
                q.insert(x);
            }
            while(!s.isFull()){
                Object x = q.remove();
                s.push(x);
            }
            return result;
        }
    }
}
