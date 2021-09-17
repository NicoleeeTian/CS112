/*
 * ChainedHashTable.java
 *
 * Computer Science 112, Boston University
 * 
 * Modifications and additions by:
 *     name: Wenxin Tian
 *     email: twx@bu.edu
 */

import java.util.*;     // to allow for the use of Arrays.toString() in testing

/*
 * A class that implements a hash table using separate chaining.
 */
public class ChainedHashTable implements HashTable {
    /* 
     * Private inner class for a node in a linked list
     * for a given position of the hash table
     */
    private class Node {
        private Object key;
        private LLQueue<Object> values;
        private Node next;
        
        private Node(Object key, Object value) {
            this.key = key;
            values = new LLQueue<Object>();
            values.insert(value);
            next = null;
        }
    }
    
    private Node[] table;      // the hash table itself
    private int numKeys;       // the total number of keys in the table
        
    /* hash function */
    public int h1(Object key) {
        int h1 = key.hashCode() % table.length;
        if (h1 < 0) {
            h1 += table.length;
        }
        return h1;
    }
    
    /*** Add your constructor here ***/
    public ChainedHashTable(int size){
        if (size <=0){
            throw new IllegalArgumentException();
        }else{
            table = new Node[size];
        }
        numKeys = 0;  
    }
    
    /*
     * insert - insert the specified (key, value) pair in the hash table.
     * Returns true if the pair can be added and false if there is overflow.
     */
    public boolean insert(Object key, Object value) {
        /** Replace the following line with your implementation. **/
        if(key ==null){
            throw new IllegalArgumentException("key must be non-null");
        }
        int h = h1(key);
        Node newNode = new Node(key,value);
        Node trav = table[h];
        if(trav==null){
            table[h] = newNode;
        }else{
            while(trav!=null){
                if(trav.key.equals(key)){
                    trav.values.insert(value);
                    return true;
                }
                trav = trav.next;
            }
            newNode.next = table[h];
            table[h]=newNode;
        }
        numKeys++;
        return true;
    }
    
    /*
     * search - search for the specified key and return the
     * associated collection of values, or null if the key 
     * is not in the table
     */
    public Queue<Object> search(Object key) {
        /** Replace the following line with your implementation. **/
        if(key == null){
            throw new IllegalArgumentException("key must be non-null");
        }
        int h = h1(key);
        Node n = table[h];
        while (n!=null){
            if(n.key.equals(key)){
                return n.values;
            }
            n= n.next;
        }
        return null;
    }
    
    /* 
     * remove - remove from the table the entry for the specified key
     * and return the associated collection of values, or null if the key 
     * is not in the table
     */
    public Queue<Object> remove(Object key) {
        /** Replace the following line with your implementation. **/
        if (key == null){
            throw new IllegalArgumentException("key must be non-null");
        }else{
            int num = h1(key);
            if(num ==-1 || table[num]==null){
                return null;
            }
            Node trav = table[num];
            Node trail =  null;
            Queue<Object> result = new LLQueue<Object>();
            while(trav!=null){
                if(trav.key.equals(key)){
                    result = trav.values;
                    if(trail!=null){
                        trail.next = trav.next;
                    }else{
                        table[num]=trav.next;
                    }
                    trav = null;
                    numKeys--;
                    break;
                }
                trail = trav;
                trav=trav.next;
            }
            return result;
        }
    }
    
    /*** Add the other required methods here ***/
    /*
    getNumKeys() method returns int value that represents numKeys
    */
    public int getNumKeys(){
        return numKeys;
    }
    /*
    load() method measures the degree of fullness
    it returns a double value that represents the load factor of the table.
    */
    public double load(){
        int k = this.getNumKeys();
        int l = table.length;
        return (double)k/l;
    }

    /*
    getAllKeys() method returns a list that contain all of the keys in the hash table.
    */
    public Object[] getAllKeys(){
        Object[] arr = new Object[getNumKeys()];
        int index = 0;
        for(int i=0;i<table.length;i++){
            Node trav = table[i];
            while(trav!=null){
                arr[index]=trav.key;
                index++;
                trav=trav.next;
            }
        }
        return arr;
    }
    /*
    resize() method takes an integer representing the new size, which allows the table
    to have the new size.
    */
    public void resize(int newSize){
        if(newSize<table.length){
            throw new IllegalArgumentException();
        }
        if(newSize==table.length){
            return;
        }
        int len = table.length;
        ChainedHashTable x = new ChainedHashTable(newSize);
        for(int i=0;i<len;i++){
            Node trav = table[i];
            while(trav!=null){
                LLQueue<Object> y = trav.values;
                while(!y.isEmpty()){
                    x.insert(trav.key,y.remove());
                }
                trav=trav.next;
            }
        }
        this.table =x.table;
        this.numKeys=x.getNumKeys();
    }
    
    /*
     * toString - returns a string representation of this ChainedHashTable
     * object. *** You should NOT change this method. ***
     */
    public String toString() {
        String s = "[";
        
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                s += "null";
            } else {
                String keys = "{";
                Node trav = table[i];
                while (trav != null) {
                    keys += trav.key;
                    if (trav.next != null) {
                        keys += "; ";
                    }
                    trav = trav.next;
                }
                keys += "}";
                s += keys;
            }
        
            if (i < table.length - 1) {
                s += ", ";
            }
        }       
        
        s += "]";
        return s;
    }
    public static void main(String[] args) {
        /** Add your unit tests here **/
        System.out.println("(1) Testing on \"insert method\"");
        try {
            ChainedHashTable table = new ChainedHashTable(6);
            table.insert("flower",10);
            table.insert("computer",7);
            table.insert("science",3);
            String results = table.toString();
            String expected = "[null, null, null, {flower}, {science}, {computer}]";
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results.equals(expected));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();    // include a blank line between tests
        
        System.out.println("(2) Testing on \"insert method\"");
        try {
            ChainedHashTable table = new ChainedHashTable(2);
            table.insert("computer",10);
            table.insert("computer",7);
            String results = table.toString();
            String expected = "[null, {computer}]";
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results.equals(expected));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();    // include a blank line between tests

        System.out.println("(3) Testing on \"insert method\"");
        try {
            ChainedHashTable table = new ChainedHashTable(2);
            table.insert("flower",10);
            table.insert("computer",10);
            table.insert("science",10);
            String results = table.toString();
            String expected = "[{science}, {computer; flower}]";
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results.equals(expected));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }

        System.out.println();    // include a blank line between tests

        System.out.println();    // include a blank line between methods
        System.out.println();    // include a blank line between methods

        System.out.println("(1) Testing on \"search method\"");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("test", 10);
            table.insert("test", 11);
            Queue<Object> q =  table.search("test");
            String result = q.toString();
            String expected = "{10, 11}";
            System.out.println("actual results:");
            System.out.println(result);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(result.equals(expected));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();    // include a blank line between tests

        System.out.println("(2) Testing on \"search method\"");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("test", 10);
            Queue<Object> q =  table.search("test");
            String result = q.toString();
            String expected = "{10}";
            System.out.println("actual results:");
            System.out.println(result);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(result.equals(expected));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();    // include a blank line between tests

        System.out.println();    // include a blank line between methods
        System.out.println();    // include a blank line between methods
        

        System.out.println("(3) Testing on \"search method\"");
        try {
            ChainedHashTable table = new ChainedHashTable(2);
            table.insert("apple", 10);
            table.insert("juice",14);
            Queue<Object> q =  table.search("apple");
            String result = q.toString();
            String expected = "{10}";
            System.out.println("actual results:");
            System.out.println(result);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(result.equals(expected));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();    // include a blank line between tests

        System.out.println();    // include a blank line between methods
        System.out.println();    // include a blank line between methods
        
        System.out.println("(1) Testing on \"remove method\"");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("test", 10);
            Queue<Object> q =  table.remove("test");
            String result = q.toString();
            String expected = "{10}";
            System.out.println("actual results:");
            System.out.println(result);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(result.equals(expected));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();    // include a blank line between tests

        System.out.println("(2) Testing on \"remove method\"");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("test", 5);
            table.insert("test", 10);
            table.insert("test", 15);
            System.out.println("table"+table);
            Queue<Object> q =  table.remove("test");
            String result = q.toString();
            String expected = "{5, 10, 15}";
            System.out.println("actual results:");
            System.out.println(result);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(result.equals(expected));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();    // include a blank line between tests

        System.out.println("(3) Testing on \"remove method\"");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("apple", 5);
            table.insert("howdy", 10);
            table.insert("add", 15);
            System.out.println("table"+table);
            Queue<Object> q =  table.remove("howdy");
            String result = q.toString();
            String expected = "{10}";
            System.out.println("actual results:");
            System.out.println(result);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(result.equals(expected));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();    // include a blank line between tests

        System.out.println();    // include a blank line between methods
        System.out.println();    // include a blank line between methods

        System.out.println("(1) Testing on \"insert method\"");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            String result = table.toString();
            String expected = "[{apple; howdy}, null, null, {goodbye}, null]";
            System.out.println("actual results:");
            System.out.println(result);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(result.equals(expected));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();    // include a blank line between tests

        System.out.println("(2) Testing on \"insert method\"");
        try {
            ChainedHashTable table2 = new ChainedHashTable(10);
            table2.insert("Boston", 1);
            table2.insert("Big", 2);
            table2.insert("Bright", 3);
            String result2 = table2.toString();
            String expected2 = "[null, null, null, null, {Big}, null, null, null, {Bright}, {Boston}]";
            System.out.println("actual results:");
            System.out.println(result2);
            System.out.println("expected results:");
            System.out.println(expected2);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(result2.equals(expected2));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();    // include a blank line between tests

        System.out.println("(3) Testing on \"insert method\"");
        try {
            ChainedHashTable table3 = new ChainedHashTable(1);
            table3.insert("Apple", 1);
            table3.insert("Banana", 2);
            table3.insert("Cece", 3);
            String result3 = table3.toString();
            String expected3 = "[{Cece; Banana; Apple}]";
            System.out.println("actual results:");
            System.out.println(result3);
            System.out.println("expected results:");
            System.out.println(expected3);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(result3.equals(expected3));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();    // include a blank line between tests

        System.out.println();    // include a blank line between methods
        System.out.println();    // include a blank line between methods

        System.out.println("(1) Testing on \"getNumKeys method\"");
        try {
            ChainedHashTable table4 = new ChainedHashTable(1);
            table4.insert("apple", 1);
            table4.insert("apple", 1);
            table4.insert("apple", 1);
            int result4 = table4.getNumKeys();
            int expected4 = 1;
            System.out.println("actual results:");
            System.out.println(result4);
            System.out.println("expected results:");
            System.out.println(expected4);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(result4 == expected4);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }

        System.out.println("(2) Testing on \"getNumKeys method\"");
        try {
            ChainedHashTable table5 = new ChainedHashTable(3);
            table5.insert("apple", 10);
            table5.insert("zoe", 15);
            table5.insert("apple", 20);
            int result5 = table5.getNumKeys();
            int expected5 = 2;
            System.out.println("actual results:");
            System.out.println(result5);
            System.out.println("expected results:");
            System.out.println(expected5);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(result5 == expected5);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }

        System.out.println("(3) Testing on \"getNumKeys method\"");
        try {
            ChainedHashTable table4 = new ChainedHashTable(10);
            table4.insert("CS112", 1);
            table4.insert("CS132", 2);
            table4.insert("CS131", 3);
            int result4 = table4.getNumKeys();
            int expected4 = 3;
            System.out.println("actual results:");
            System.out.println(result4);
            System.out.println("expected results:");
            System.out.println(expected4);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(result4 == expected4);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }

        System.out.println();    // include a blank line between methods
        System.out.println();    // include a blank line between methods

        System.out.println("(1) Testing on \"load method\"");
        try {
            ChainedHashTable table = new ChainedHashTable(10);
            table.insert("lucy", 15);
            table.insert("mary", 10);
            table.insert("oscar", 5);
            double result = table.load();
            double expected = 0.3;
            System.out.println("actual results:");
            System.out.println(result);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(result == expected);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();    // include a blank line between tests

        System.out.println("(2) Testing on \"load method\"");
        try {
            ChainedHashTable table = new ChainedHashTable(1);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            double result = table.load();
            double expected = 3.0;
            System.out.println("actual results:");
            System.out.println(result);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(result == (expected));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();    // include a blank line between tests

        System.out.println("(3) Testing on \"load method\"");
        try {
            ChainedHashTable table = new ChainedHashTable(100);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            double result = table.load();
            double expected = 0.03;
            System.out.println("actual results:");
            System.out.println(result);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(result == (expected));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();    // include a blank line between tests


        System.out.println();    // include a blank line between methods
        System.out.println();    // include a blank line between methods

        System.out.println("(1) Testing on \"getAllKeys method\"");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            String result = Arrays.toString(table.getAllKeys());
            String expected = "[apple, howdy, goodbye]";
            System.out.println("actual results:");
            System.out.println(result);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(result.equals(expected));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();    // include a blank line between tests

        System.out.println("(2) Testing on \"getAllKeys method\"");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("Boston", 15);
            table.insert("Bright", 10);
            table.insert("Big", 5);
            System.out.println(table);
            String result = Arrays.toString(table.getAllKeys());
            String expected = "[Bright, Big, Boston]";
            System.out.println("actual results:");
            System.out.println(result);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(result.equals(expected));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();    // include a blank line between tests

        System.out.println("(3) Testing on \"getAllKeys method\"");
        try {
            ChainedHashTable table = new ChainedHashTable(10000);
            table.insert("Apple", 15);
            table.insert("Cece", 10);
            table.insert("Banana", 5);
            String result = Arrays.toString(table.getAllKeys());
            String expected = "[Cece, Apple, Banana]";
            System.out.println("actual results:");
            System.out.println(result);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(result.equals(expected));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();    // include a blank line between tests

        System.out.println();    // include a blank line between methods
        System.out.println();    // include a blank line between methods

        System.out.println("(1) Testing on \"resize method\"");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            table.resize(7);
            String result = table.toString();
            String expected = "[null, {apple}, null, null, null, {howdy}, {goodbye}]";
            System.out.println("actual results:");
            System.out.println(result);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(result.equals(expected));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }

        System.out.println();    // include a blank line between tests

        System.out.println("(2) Testing on \"resize method\"");
        try {
            ChainedHashTable table = new ChainedHashTable(1);
            table.insert("abb", 1);
            table.insert("acc", 1);
            table.insert("add", 1);
            table.resize(3);
            String result = table.toString();
            String expected = "[{add}, {acc}, {abb}]";
            System.out.println("actual results:");
            System.out.println(result);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(result.equals(expected));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }

        System.out.println();    // include a blank line between tests

        System.out.println("(3) Testing on \"resize method\"");
        try {
            ChainedHashTable table = new ChainedHashTable(3);
            table.insert("howdy", 15);
            table.insert("howdy", 10);
            table.insert("howdy", 5);
            table.resize(4);
            String result = table.toString();
            String expected = "[null, {howdy}, null, null]";
            System.out.println("actual results:");
            System.out.println(result);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(result.equals(expected));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
    }
}
