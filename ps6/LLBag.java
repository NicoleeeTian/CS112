
/*
 * LLBag.java
 *
 * Computer Science 112, Boston University
 * 
 * modified by: Wenxin Tian
 */

/*
 * A class that implements our simple Bag interface using an link list.
 */
public class LLBag implements Bag{

    // Inner class for a node. We use an inner class so that the LLBag
    // methods can access the instance variables of the nodes.
    private class Node {
        private Object item;
        private Node next;

        private Node(Object i, Node n) {
            item = i;
            next = n;
        }
    }

    // fields of the LLBag object
    private Node head; //dummy node
    private int length; // num of objects in the list
    private Node last; //the reference of last node 

    /*
     * Constructs a LLBag object for a list that is initially empty.
     */
    public LLBag(){
        head = new Node(null, null);
        last = head;
        length = 0;
    }


     /*
     * toString - converts this LLBag into a string that can be printed.
     * Overrides the version of this method inherited from the Object class.
     */
    public String toString(){
        String x ="{";
        Node trav = head.next;
        while(trav.next != null){
            x += trav.item;
            x+= ", ";
            trav = trav.next;
        }
        x += trav.item + "}";
        return x;
    }

    /* 
     * add - adds the specified item to this LLBag. Returns true.
     * Throws an IllegalArgumentException if the item is null.
     */
    public boolean add(Object item){
        if (item == null){
            throw new IllegalArgumentException();
        }
        Node n = new Node(item,null);
        last.next = n;
        last = n;
        length++;
        return true;
    }
    
    /** 
     * removes one occurrence of the specified item (if any) from the
     * Bag.  Returns true on success and false if the specified item
     * (i.e., an object equal to item) is not in the Bag.
     */
    public boolean remove(Object item){
        if (item == null){
            throw new IllegalArgumentException();
        }
        Node trav = head.next;
        Node trail = head;
        while(trav != null){
            if(trav.item.equals(item)){
                trail.next = trail.next.next;
                length--;
                if(trav.equals(last)){
                    last = trail;
                }
                return true;
            }
            trail = trav;
            trav = trav.next; 
        }
        return false;
    }
    
    /**
     * returns true if the specified item is in the Bag, and false
     * otherwise.
     */
    public boolean contains(Object item){
        if (item == null){
            throw new IllegalArgumentException();
        }
        Node n = head.next;
        while(n != null){
            if(n.item.equals(item)){
                return true;
            }
            n = n.next;
        }
        return false;
    }
    
    /**
     * returns the number of items in the Bag.
     */
    public int numItems(){
        return length;
    }
    
    /**
     * grab - returns a reference to a randomly chosen in the Bag.
     */
    public Object grab(){
        if (length == 0) {
            throw new IllegalStateException("the bag is empty");
        }
        int count = 0;
        int whichOne = (int)(Math.random() * length);
        Node trav = head.next;
        while(trav!= null){
            if(count == whichOne){
                break;
            }else{
                count++;
                trav=trav.next;
            }
        }
        return trav;
    }
    
    /**
     * toArray - return an array containing the current contents of the bag
     */
    public Object[] toArray(){
        Object[] copy = new Object[length];
        Node trav = head.next;
        int i =0;
        while(trav != null){
            copy[i] = trav.item;
            trav = trav.next;
            i++;
        }
        return copy;
    }
}
