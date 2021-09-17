/*
 * LLList.java
 *
 * Computer Science 112, Boston University
 * 
 * modified by: Wenxin Tian
 */

import java.util.*;

/*
 * A class that implements our simple List interface using a linked list.
 * The linked list includes a dummy head node that allows us to avoid
 * special cases for insertion and deletion at the front of the list.
 */
public class LLList implements List {
    // Inner class for a node. We use an inner class so that the LLList
    // methods can access the instance variables of the nodes.
    private class Node {
        private Object item;
        private Node next;

        private Node(Object i, Node n) {
            item = i;
            next = n;
        }
    }

    // fields of the LLList object
    private Node head; // dummy head node
    private int length; // # of items in the list
    private Node last; // reference to the last node; see Problem 5

    /*
     * Constructs a LLList object for a list that is initially empty.
     */
    public LLList() {
        head = new Node(null, null);
        length = 0;
        last = head; // when list is empty, last points to dummy head node
    }

    /*
     * Constructs an LLList object containing the items in the specified array
     */
    public LLList(Object[] initItems) {
        if (initItems == null) {
            throw new IllegalArgumentException();
        }

        head = new Node(null, null);

        Node prevNode = head;
        Node nextNode = null;
        for (int i = 0; i < initItems.length; i++) {
            nextNode = new Node(initItems[i], null);
            prevNode.next = nextNode;
            prevNode = nextNode;
            if (i == (initItems.length-1)){
                last = nextNode;
            }
        }
        length = initItems.length;
    }

    /*
     * getLastItem - returns the item in node referred to by the field last, or null
     * if that field is null.
     * 
     * YOU SHOULD *NOT* MODIFY THIS METHOD.
     */
    public Object getLastItem() {
        if (last == null) {
            return null;
        } else {
            return last.item;
        }
    }

    /*
     * length - returns the number of items in the list
     */
    public int length() {
        return length;
    }

    /*
     * isFull - always returns false, because the linked list can grow indefinitely
     * and thus the list is never full.
     */
    public boolean isFull() {
        return false;
    }

    /*
     * getNode - private helper method that returns a reference to the ith node in
     * the linked list. It assumes that the value of the parameter is valid.
     * 
     * If i == -1, it returns a reference to the dummy head node.
     */
    private Node getNode(int i) {
        Node trav = head;
        int travIndex = -1;
        if(i == length-1){
            return last;
        }else{
            while (travIndex < i) {
                travIndex++;
                trav = trav.next;
            }
        }
        return trav;
    }

    /*
     * getItem - returns the item at position i in the list
     */
    public Object getItem(int i) {
        if (i < 0 || i >= length) {
            throw new IndexOutOfBoundsException();
        }

        Node n = getNode(i);
        return n.item;
    }

    /*
     * addItem - adds the specified item at position i in the list, shifting the
     * items that are currently in positions i, i+1, i+2, etc. to the right by one.
     * Always returns true, because the list is never full.
     *
     * We don't need a special case for insertion at the front of the list (i == 0),
     * because getNode(0 - 1) will return the dummy head node, and the rest of
     * insertion can proceed as usual.
     */
    public boolean addItem(Object item, int i) {
        if (item == null || i < 0 || i > length) {
            throw new IllegalArgumentException();
        }

        Node newNode = new Node(item, null);
        if(i < length){
            Node prevNode = getNode(i - 1);
            newNode.next = prevNode.next;
            prevNode.next = newNode;
        }else{
            last.next = newNode;
            last = newNode;
        }

        length++;
        return true;
    }

    /*
     * removeItem - removes the item at position i in the list, shifting the items
     * that are currently in positions i+1, i+2, etc. to the left by one. Returns a
     * reference to the removed object.
     *
     * Here again, we don't need a special case for i == 0 (see the note
     * accompanying addItem above).
     */
    public Object removeItem(int i) {
        if (i < 0 || i >= length) {
            throw new IndexOutOfBoundsException();
        }

        Node prevNode = getNode(i - 1);
        Object removed = prevNode.next.item;
        if(i < length-1){
            prevNode.next = prevNode.next.next;
        }else{
            prevNode.next = null;
            last = prevNode;
        }

        length--;
        return removed;
    }

    public void rotate(int k){
        if (k < 0 || k >= length) {
            throw new IndexOutOfBoundsException();
        }else{
            int l = length-k;
            Node newLast = getNode(l-1);
            Node newFirst = newLast.next;

            newLast.next = null;
            Node first = head.next;
            last.next=first;
            head.next = newFirst;

            last = newLast;
        }
    }

    /*
     * toString - converts the list into a String of the form {item0, item1, ...}
     */
    public String toString() {
        String str = "{";

        Node trav = head.next; // skip over the dummy head node
        while (trav != null) {
            str = str + trav.item;
            if (trav.next != null) {
                str = str + ", ";
            }
            trav = trav.next;
        }

        str = str + "}";
        return str;
    }

    /*
     * iterator - returns an iterator for this list
     */
    public ListIterator iterator() {
        return new LLListIterator();
    }

    /*
     * private inner class for an iterator over an LLList
     */
    private class LLListIterator implements ListIterator {
        private Node nextNode; // the next node to visit

        public LLListIterator() {
            nextNode = head.next; // skip over the dummy head node
        }

        /*
         * hasNext - does the iterator have additional items to visit?
         */
        public boolean hasNext() {
            return (nextNode != null);
        }

        /*
         * next - returns a reference to the next Object in the iteration
         */
        public Object next() {
            if (nextNode == null) {
                throw new NoSuchElementException();
            }

            Object item = nextNode.item;
            nextNode = nextNode.next;
            return item;
        }
    }
    public static void main(String[] args){
        String[] letters1 = {"a", "b", "c"};
        LLList list1 = new LLList(letters1);
        System.out.println(list1);
        System.out.println(list1.getLastItem());

        String[] letters2 = {"a", "b", "c", "d", "e"};
        LLList list2 = new LLList(letters2);

        // Add two items to the end of the list.
        list2.addItem("f", 5);
        list2.addItem("g", 6);
        System.out.println(list2.getLastItem());

        // Remove three items from the end of the list.
        list2.removeItem(6);
        list2.removeItem(5);
        list2.removeItem(4);
        System.out.println(list2.getLastItem());

        String[] letters3 = {"a", "b", "c", "d", "e", "f"};
        LLList list3 = new LLList(letters3);
        System.out.println(list3);
        list3.rotate(4);
        System.out.println(list3);
    }
}
