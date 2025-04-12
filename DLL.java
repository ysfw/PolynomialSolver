import java.util.Scanner;

class DLLNode {
    private Object element;
    private DLLNode next;
    private DLLNode prev;

    public DLLNode(Object Data, DLLNode n, DLLNode p) {
        element = Data;
        next = n;
        prev = p;
    }

    public Object getElement() {
        return element;
    }

    public DLLNode getNext() {
        return next;
    }

    public DLLNode getPrev() {
        return prev;
    }

    public void setElement(Object newElem) {
        element = newElem;
    }

    public void setNext(DLLNode newNext) {
        next = newNext;
    }

    public void setPrev(DLLNode newPrev) {
        prev = newPrev;
    }

}

public class DLL implements ILinkedList {
    private DLLNode head = null;
    private DLLNode tail = head;
    int size = 0;
    public DLLNode getHead(){
        return head;
    }
    public DLLNode getTail(){
        return tail;
    }
    public void add(int index, Object element) {
        DLLNode curr = head;
        if (index == 0) {
            DLLNode NewNode = new DLLNode(element, head, null);
            if (curr != null) {
                curr.setPrev(NewNode);
            }
            head = NewNode;
            size++;
        } else if (index >=0 && size > index && head != null) {
            for (int i = 0; i < index - 1; i++) {
                curr = curr.getNext();
            }
            DLLNode NewNode = new DLLNode(element, curr.getNext(), curr);
            curr.setNext(NewNode);
            DLLNode nextNode = NewNode.getNext();
            if (nextNode != null) {
                nextNode.setPrev(NewNode);
            }
            size++;
        } else if (index > 0 &&index == size) {
            add(element);
        } else {
            throw new Error("You Can't add at index " + index + " While The size of the list is " + size);
        }
    }

    public void add(Object element) {
        if (head == null) {
            DLLNode NewNode = new DLLNode(element, null, null);
            head = NewNode;
            tail = NewNode;
        } else {
            DLLNode NewNode = new DLLNode(element, null, tail);
            tail.setNext(NewNode);
            tail = NewNode;
        }
        size++;

    }

    public Object get(int index) {
        DLLNode curr = head;
        if (index >=0 && size > index && head != null) {
            for (int i = 0; i < index; i++) {
                curr = curr.getNext();
            }
            return curr.getElement();
        } else {
            throw new Error("You Can't get element at index " + index + " While The size of the list is " + size);
        }
    }

    public void set(int index, Object element) {
        DLLNode curr = head;
        if (index >=0 && size > index && head != null) {
            for (int i = 0; i < index; i++) {
                curr = curr.getNext();
            }
            curr.setElement(element);
        } else {
            throw new Error("You Can't set element at index " + index + " While The size of the list is " + size);
        }
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else
            return false;
    }

    public void remove(int index) {
        DLLNode curr = head;
        DLLNode prev = null;
        if (head == null) {
            return;
        }
        if (index == 0) {
            head = head.getNext();
            if (head != null) {
                head.setPrev(null);
            }
            size--;
        } else if (index >=0 && size > index && head != null) {
            for (int i = 0; i < index; i++) {
                prev = curr;
                curr = curr.getNext();
            }
            prev.setNext(curr.getNext());
            curr = curr.getNext();
            if (curr == null) {
                tail = prev;
            } else {
                curr.setPrev(prev);
            }
            size--;
        } else {
            throw new Error("You Can't remove element at index " + index + " While The size of the list is " + size);
        }
    }

    public int size() {
        return size;
    }

    public DLL sublist(int fromIndex, int toIndex) {
        if (toIndex >=0 && fromIndex >=0 && fromIndex < size && toIndex < size && fromIndex <= toIndex) {
            DLL subDll = new DLL();
            DLLNode curr = head;
            for (int i = 0; i < fromIndex; i++) {
                curr = curr.getNext();
            }

            for (int i = fromIndex; i <= toIndex; i++) {
                subDll.add(curr.getElement());
                curr = curr.getNext();
            }
            return subDll;
        } else {
            throw new Error("Can't create sublist");
        }

    }

    public boolean contains(Object o) {
        DLLNode curr1 = head;
        DLLNode curr2 = tail;
        while (curr1 != null && curr1 != null && curr2 != curr1.getPrev()) {
            if (curr1.getElement() == o || curr2.getElement() == o) {
                return true;
            }
            curr1 = curr1.getNext();
            curr2 = curr2.getPrev();
        }

        return false;
    }


    public void printList() {

        System.out.print('[');
        DLLNode curr = head;
        while (curr != null) {
            System.out.print(curr.getElement());
            if (curr.getNext() != null) {
                System.out.print(", ");
            }
            curr = curr.getNext();
        }
        System.out.print(']');
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            DLL inputs = new DLL();
            String sin = sc.nextLine().replaceAll("\\[|\\]", "");
            String[] stringarr = sin.split(", ");
            if (!stringarr[0].equals("")) {
                for (String string : stringarr) {
                    inputs.add(Integer.parseInt(string));
                }
            }
            String operation = sc.nextLine();
            if (operation.equals("add")) {
                inputs.add(sc.nextInt());
                inputs.printList();
            } else if (operation.equals("addToIndex")) {
                inputs.add(sc.nextInt(), sc.nextInt());
                inputs.printList();
            } else if (operation.equals("set")) {
                inputs.set(sc.nextInt(), sc.nextInt());
                inputs.printList();
            } else if (operation.equals("clear")) {
                inputs.clear();
                inputs.printList();
            } else if (operation.equals("remove")) {
                inputs.remove(sc.nextInt());
                inputs.printList();
            } else if (operation.equals("isEmpty")) {
                System.out.println(inputs.isEmpty() ? "True" : "False");
            } else if (operation.equals("contains")) {
                System.out.println(inputs.contains(sc.nextInt()) ? "True" : "False");
            } else if (operation.equals("get")) {
                System.out.println(inputs.get(sc.nextInt()));
            } else if (operation.equals("size")) {
                System.out.println(inputs.size());
            } else if (operation.equals("sublist")) {
                DLL sub = inputs.sublist(sc.nextInt(), sc.nextInt());
                sub.printList();
            } else {
                sc.close();
                throw new Error("Invalid Operation");
            }
            sc.close();
        }
        catch (Error e) {
            System.out.println("Error");
        }  
}
}
