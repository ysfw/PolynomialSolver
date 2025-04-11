package PolynomialSolver;

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
    DLLNode head = null;
    DLLNode tail = head;
    int size = 0;

    public void add(int index, Object element) {
        DLLNode curr = head;
        if (size >= index && head != null) {
            for (int i = 0; i < index-1; i++) {
                curr = curr.getNext();
            }
            DLLNode NewNode = new DLLNode(element,curr.getNext(),curr);
            curr.setNext(NewNode);
            DLLNode nextNode = NewNode.getNext();
            if (nextNode != null) {
                nextNode.setPrev(NewNode);
            }
            size++;
        }
        else if(head==null && size == 0 && index == size) {
            DLLNode NewNode = new DLLNode(element,null,null);
            head = NewNode;
            tail = NewNode;
            size++;
        }
        else{
            throw new Error("You Can't add at index " + index + " While The size of the list is " + size);
        }
    }

    public void add(Object element) {
        if (head == null) {
            DLLNode NewNode = new DLLNode(element,null,null);
            head = NewNode;
            tail = NewNode;
        }
        else{
            DLLNode NewNode = new DLLNode(element,null,tail);
            tail.setNext(NewNode);
            tail = NewNode;
        }
        size++;

    }

    public Object get(int index) {
        DLLNode curr = head;
        if (size > index && head != null) {
            for (int i = 0; i < index; i++) {
                curr = curr.getNext();
            }
            return curr.getElement();
        }
        else{
            throw new Error("You Can't get element at index " + index + " While The size of the list is " + size);
        }
    }

    public void set(int index, Object element) {
        DLLNode curr = head;
        if (size > index && head != null) {
            for (int i = 0; i < index; i++) {
                curr = curr.getNext();
            }
            curr.setElement(element);
        }
        else{
            throw new Error("You Can't get element at index " + index + " While The size of the list is " + size);
        }
    }

    public void clear() {
        head = null;
        tail = head;
        size = 0;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        else return false;
    }

    public void remove(int index) {
        DLLNode curr = head;
        DLLNode prev = null;
        if (index ==0) {
            head = head.getNext();
            size--;
        }
        else if (size > index && head != null) {
            for (int i = 0; i < index; i++) {
                prev = curr;
                curr = curr.getNext();
            }
            prev.setNext(curr.getNext());
            curr = curr.getNext();
            curr.setPrev(prev);
            size--;
        }
        else{
            throw new Error("You Can't remove element at index " + index + " While The size of the list is " + size);
        }     
    }

    public int size() {
        return size;
    }

    public DLL sublist(int fromIndex, int toIndex) {
        DLL subDll = new DLL();
        DLLNode curr = head;
        for (int i = 0; i < fromIndex; i++) {
            curr = curr.getNext();
        }

        for (int i = 0; i < toIndex; i++) {
            subDll.add(curr.getElement());
            curr = curr.getNext();
        }
        return subDll;
        
    }

    public boolean contains(Object o) {
        DLLNode curr = head;
        while (curr != null) {
            curr = head.getNext();
            if (curr.getElement() == o) {
                return true;
            }
        }

            return false;
    }

}
