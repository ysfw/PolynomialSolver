import java.security.SignedObject;
import java.util.Scanner;
import java.util.regex.*;

class SNode {
    private Object data;
    private SNode next;

    public SNode(Object number, SNode nextNode) {
        data = number;
        next = nextNode;
    }

    public SNode() {
        data = 0;
        next = null;
    }

    public void setData(Object newData) {
        data = newData;
    }

    public void setNext(SNode nextNode) {
        next = nextNode;
    }

    public Object getData() {
        return data;
    }

    public SNode getNext() {
        return next;
    }
}

public class SingleLinkedList implements ILinkedList {
    private int size = 0;
    private SNode head = null;
    private SNode tail = null;

    public void add(int index, Object element) {
        if (!(index >= 0 && index <= size)) {
            throw new IndexOutOfBoundsException(index);
        }
        if (index == 0) {
            SNode newNode = new SNode(element, head);
            head = newNode;
            if (size == 0) {
                tail = newNode;
            }
        }
        else if (index == size) {
            SNode newNode = new SNode(element, null);
            if (size == 0) {
                head = newNode;
                tail = newNode;
            }
            else {
                tail.setNext(newNode);
                tail = newNode;
            }
        }
        else {
            int i = 1;
            SNode prev = head;
            SNode iter = head.getNext();
            while (i < index) {
                prev = iter;
                iter = iter.getNext();
                i++;
            }
            // after finishing the loop: iter should point to the node at the index specified
            SNode newNode = new SNode(element, iter);
            prev.setNext(newNode);
        }
        size++;
    }

    public void add(Object element) {
        SNode newNode = new SNode(element, null);
        if (size == 0) {
            head = newNode;
            tail = newNode;
        }
        else {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    public Object get(int index) {
        if (!(index >= 0 && index < size)) {
            throw new IndexOutOfBoundsException(index);
        }
        int i = 0;
        SNode iter = head;
        while (i < index) {
            iter = iter.getNext();
            i++;
        }
        // after finishing the loop: iter should point to the node at the index specified
        return iter.getData();
    }

    public void set(int index, Object element) {
        if (!(index >= 0 && index < size)) {
            throw new IndexOutOfBoundsException(index);
        }
        int i = 0;
        SNode iter = head;
        while (i < index) {
            iter = iter.getNext();
            i++;
        }
        // after finishing the loop: iter should point to the node at the index specified
        iter.setData(element);
    }

    public void clear() {
        size = 0;
        head = tail = null;
    }

    public boolean isEmpty() {
        return (head == null);
    }

    public void remove(int index) {
        if (!(index >= 0 && index < size)) {
            throw new IndexOutOfBoundsException(index);
        }
        if (size == 1) {
            size = 0;
            head = tail = null;
        }
        else {
            if (index == 0) {
                SNode nextHead = head.getNext();
                head.setNext(null);
                head = nextHead;
            }
            else {
                int i = 1;
                SNode prev = head, iter = head.getNext();
                while (i < index) {
                    prev = iter;
                    iter = iter.getNext();
                    i++;
                }
                // after finishing the loop: iter should point to the node at the index specified
                SNode afterIter = iter.getNext();
                iter.setNext(null);
                prev.setNext(afterIter);
            }
            size--;
        }
        
    }

    public int size() {
        return size;
    }

    public SingleLinkedList sublist(int fromIndex, int toIndex) {
        if (!(fromIndex >= 0 && fromIndex < size && toIndex >= fromIndex && toIndex < size)) {
            throw new IndexOutOfBoundsException("fromIndex: " + fromIndex + ", or toIndex: " + toIndex + ", is out of bound");
        }
        int i = 0;
        SNode iter = head;
        SingleLinkedList list = new SingleLinkedList();
        while (i < (toIndex + 1)) {
            if (i >= fromIndex && i <= toIndex) {
                list.add(iter.getData());
            }
            iter = iter.getNext();
            i++;
        }
        return list;
    }

    public boolean contains(Object o) {
        SNode iter = head;
        while (iter != null) {
            if (o == iter.getData()) {
                return true;
            }
            iter = iter.getNext();
        }
        return false;
    }

    public void printList() {
        System.out.print("[");
        SNode iter;
        for (iter = head; iter.getNext() != null; iter = iter.getNext()) {
            System.out.print(iter.getData() + ", ");
        }
        System.out.println(iter.getData() + "]");
    }

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        String list_input = scan.nextLine();
        Pattern lisPattern = Pattern.compile("\\[(.*)\\]");
        Matcher m = lisPattern.matcher(list_input);
        SingleLinkedList list = new SingleLinkedList();
        if (m.matches()) {
            // System.out.println(m.toString());
            String listString = m.group(1);
            // System.out.println(listString);
            for (int i = 0; i < listString.length(); i++) {
                if (Character.isDigi(listString.charAt(int)))
            }
            // System.out.println("Printing list: length = " + list.size());
            // list.printList();
        }
        String operation = scan.nextLine();

        if (operation.equals("add")) {
            int data = scan.nextInt();
            list.add(data);
        }
        else if (operation.equals("addToIndex")) {
            int index = scan.nextInt();
            int data = scan.nextInt();
            list.add(index, data);
            list.printList();
        }
        else if (operation.equals("get")) {
            int index = scan.nextInt();
            Object data = list.get(index);
            System.out.println(data);
        }
        else if (operation.equals("set")) {
            int index = scan.nextInt();
            int data = scan.nextInt();
            list.set(index, data);
            list.printList();
        }
        else if (operation.equals("clear")) {
            list.clear();
        }
        else if (operation.equals("isEmpty")) {
            if (list.isEmpty()) {
                System.out.println("True");
            }
            else {
                System.out.println("False");
            }
        }
        else if (operation.equals("remove")) {
            int index = scan.nextInt();
            if (index < 0 || index >= list.size()) {
                System.out.println("Error");
            }
            else {
                list.remove(index);
                list.printList();
            }
        }
        else if (operation.equals("sublist")) {
            int startIndex = scan.nextInt();
            int endIndex = scan.nextInt();
            SingleLinkedList list2 = list.sublist(startIndex, endIndex);
            list2.printList();
        }
        else if (operation.equals("contains")) {
            int data = scan.nextInt();
            if (list.contains(data)) {
                System.out.println("True");
            }
            else {
                System.out.println("False");
            }
        }

        scan.close();
    }
}
