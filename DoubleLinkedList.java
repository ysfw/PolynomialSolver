import java.util.Scanner;
import java.util.regex.*;

class DNode {
    private Object data;
    private DNode next;
    private DNode prev;

    public DNode(Object newData, DNode newNext, DNode newPrev) {
        data = newData;
        next = newNext;
        prev = newPrev;
    }

    public DNode() {
        data = next = prev = null;
    }

    public void setData(Object newData) {
        data = newData;
    }

    public void setNext(DNode newNext) {
        next = newNext;
    }

    public void setPrev(DNode newPrev) {
        prev = newPrev;
    }

    public Object getData() {
        return data;
    }

    public DNode getNext() {
        return next;
    }

    public DNode getPrev() {
        return prev;
    }
}

public class DoubleLinkedList implements ILinkedList {
    private DNode head;
    private DNode tail;
    private int size;

    public DoubleLinkedList() {
        head = tail = null;
        size = 0;
    }

    public void add(int index, Object element) {
        if (!(index >= 0 && index <= size)) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }

        if (index == 0) {
            DNode newNode = new DNode(element, head, null);
            if (size == 0) {
                tail = newNode;
            }
            else {
                head.setPrev(newNode);
            }
            head = newNode;
        }
        else if (index == size) {
            DNode newNode = new DNode(element, null, tail);
            if (size == 0) {
                head = newNode;
            }
            else {
                tail.setNext(newNode);
            }
            tail = newNode;
        }
        else {
            int i = 0;
            DNode iter = head;
            while (i < index) {
                iter = iter.getNext();
                i++;
            }
            // after finishing the loop: iter should point to the node at the index specified
            DNode prev = iter.getPrev();
            DNode newNode = new DNode(element, iter, prev);
            prev.setNext(newNode);
            iter.setPrev(newNode);
        }
        size++;
    }

    public void add(Object element) {
        DNode newNode = new DNode(element, null, tail);
        if (size == 0) {
            head = newNode;
        }
        else {
            tail.setNext(newNode);
        }
        tail = newNode;
        size++;
    }

    public Object get(int index) {
        if (!(index >= 0 && index < size)) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }

        int i = 0;
        DNode iter = head;
        while (i < index) {
            iter = iter.getNext();
            i++;
        }
        // after finishing the loop: iter should point to the node at the index specified
        return iter.getData();
    }

    public void set(int index, Object element) {
        if (!(index >= 0 && index < size)) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }

        int i = 0;
        DNode iter = head;
        while (i < index) {
            iter = iter.getNext();
            i++;
        }
        // after finishing the loop: iter should point to the node at the index specified
        iter.setData(element);
    }

    public void clear() {
        head = tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return (head == null);
    }

    public void remove(int index) {
        if (!(index >= 0 && index < size)) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }

        if (size == 1) {
            head = null;
            tail = null;
        }
        else {
            if (index == 0) {
                DNode nextHead = head.getNext();
                nextHead.setPrev(null);
                head.setNext(null);
                head = nextHead;
            }
            else if (index == (size - 1)) {
                DNode prevTail = tail.getPrev();
                prevTail.setNext(null);
                tail.setPrev(null);
                tail = prevTail;
            }
            else {
                int i = 0;
                DNode iter = head;
                while (i < index) {
                    iter = iter.getNext();
                    i++;
                }
                // after finishing the loop: iter should point to the node at the index specified
                DNode prev = iter.getPrev();
                DNode next = iter.getNext();
                iter.setNext(null);
                iter.setPrev(null);
                prev.setNext(next);
                next.setPrev(prev);
            }
        }
        size--;
    }

    public int size() {
        return size;
    }

    public DoubleLinkedList sublist(int fromIndex, int toIndex) {
        if (!(fromIndex >= 0 && fromIndex < size && toIndex >= fromIndex && toIndex < size)) {
            throw new IndexOutOfBoundsException("fromIndex: " + fromIndex + ", or toIndex: " + toIndex + ", is out of bound");
        }

        DoubleLinkedList list = new DoubleLinkedList();
        int i = 0;
        DNode iter = head;
        while (i <= toIndex) {
            if (i >= fromIndex) {
                list.add(iter.getData());
            }
            iter = iter.getNext();
            i++;
        }
        return list;
    }

    public boolean contains(Object o) {
        for (DNode iter = head; iter != null; iter = iter.getNext()) {
            if (iter.getData() == o) {
                return true;
            }
        }
        return false;
    }

    public void printList() {
        System.out.print("[");
        DNode iter = head;
        while (iter != null) {
            System.out.print(iter.getData());
            if (iter.getNext() != null) {
                System.out.print(", ");
            }
            iter = iter.getNext();
        }
        System.out.println("]");
    }

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        String inputString = scan.nextLine();
        Pattern p = Pattern.compile("\\[(.*)\\]");
        Matcher m = p.matcher(inputString);

        DoubleLinkedList list = new DoubleLinkedList();

        if (m.matches()) {
            String listString = m.group(1);
            // System.out.println(listString);

            int i = 0;
            while (i < listString.length()) {
                char character = listString.charAt(i);
                if (Character.isDigit(character)) {
                    String s = "";
                    while (Character.isDigit(character)) {
                        s += character;
                        i++;
                        if (i < listString.length())
                            character = listString.charAt(i);
                        else
                            break;
                    }
                    list.add(Integer.parseInt(s));
                }
                i++;
            }
            // System.out.println("Printing mylist: length = " + list.size());
            // list.printList();
        }

        String operation = scan.nextLine();
        try {
            if (operation.equals("add")) {
                int data = scan.nextInt();
                list.add(data);
                list.printList();
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
                list.printList();
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
                list.remove(index);
                list.printList();
            }
            else if (operation.equals("sublist")) {
                int startIndex = scan.nextInt();
                int endIndex = scan.nextInt();
                DoubleLinkedList list2 = list.sublist(startIndex, endIndex);
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
            else if (operation.equals("size")) {
                System.out.println(list.size());
            }
        }
        catch (Exception btngan) {
            System.out.println("Error");
        }
        scan.close();
    }
}
