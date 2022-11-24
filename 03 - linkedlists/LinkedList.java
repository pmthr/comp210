package comp210.assn03;

public class LinkedList<T> {
    
    private Node<T> head = null;
    private Node<T> tail = null;
    private int size = 0;

    public boolean isEqual(LinkedList list2) {
        while (this.size() == list2.size()) {
            Node a = this.head, b = list2.head;
            while (a != null && b != null)
            {
                if (a.getValue() != b.getValue() )
                    return false;
                a = a.getNext();
                b = b.getNext();
            }

            return (a == null && b == null);
        }

        return false;
    }

    public boolean isSymmetrical() {
        for (int i = 0; i < (this.size() - 1) / 2; i++) {
            if (this.get(i) != this.get(this.size() - 1 - i)) {
                return false;
            }
        }

        return true;
    }

    public Node<T> reversed(Node<T> head){
        Node<T> prev = null;

        while(head != null) {
            Node<T> next = head.getNext();
            head.setNext(prev);
            prev = head;
            head = next;
        }

        return prev;
    }

    public void multiply(int factor) {
        if (factor == 0) { 
            this.clear();
            return;
        }

        Node<T> current = this.getHead();

        for (int i = 0; i < this.size(); i++) {
            Node<T> temp = current.getNext();
            for (int j = 1; j < factor; j++) {
                Node<T> node = new NodeImpl<T>(current.getValue(), null);
                current.setNext(node);
                current = node;
            }

            current.setNext(temp);
            current = temp;
        }

        this.size *= factor;
    }

    public boolean containsCycle() {
        Node<T> current = this.getHead();
        int i = 1;

        while (current.hasNext()) {
            current = current.getNext();
            i += 1;
            if (i > size) {
                return true;
            }
        }

        return false;
    }

    public void merge(LinkedList list2) {
        Node<T> one = this.getHead();
        Node<T> two = list2.getHead();

        for (int i = 0; i < list2.size(); i++) {
            Node<T> temp = one.getNext();
            one.setNext(two);
            two = two.getNext();
            one.getNext().setNext(temp);
            one = temp;
        }

        this.size += list2.size();
    }
    
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean contains(Object element) {
        Node<T> current = head;

        while(current != null) {
            if(current.getValue().equals(element)) {
                return true;
            }
            current = current.getNext();
        }

        return false;
    }

    public T[] toArray() {
        T[] arr =  (T[]) new Object[size()];
        Node<T> current = head;
        int i = 0;

        if(isEmpty()) {
            return arr;
        }

        while(current != null){
            arr[i] = current.getValue();
            current = current.getNext();
            i++;
        }

        return arr;
    }

    public void add(Object element) {
        Node<T> newNode = new NodeImpl<T>((T) element, null);

        if(isEmpty()) {
            head = newNode;
            tail = newNode;
            size++;
        } else {
            tail.setNext(newNode);
            tail = newNode;
            size++;
        }
    }

    public boolean remove(Object element) {
        Node<T> current = head;

        if(isEmpty()) {
            return false;
        }

        if(current.getValue() == element){
            head = head.getNext();
            size--;
            return true;
        }

        while(current.getNext().getValue() != element) {
            current = current.getNext();
            if(current == null) {
                return false;
            }
        }

        if(current.getNext().getNext() == null) {
            tail = current;
        }

        current.setNext(current.getNext().getNext());
        size--;
        return true;
    }

    public T get(int index) {
        validIndex(index);
        Node<T> current = head;
        int i = 0;

        while (i < index) {
            current = current.getNext();
            i++;
        }

        return current.getValue();
    }

    public T set(int index, Object element) {
        validIndex(index);
        Node<T> current = head;
        T prevValue = null;
        int i = 0;

        if(index == 0) {
            prevValue = head.getValue();
            head.setValue((T) element);
        } else {
            while(current != null) {
                if(i == index) {
                    prevValue = current.getValue();
                    current.setValue((T) element);
                    return prevValue;
                }
                current = current.getNext();
                i++;
            }
        }

        return prevValue;
    }

    public void add(int index, Object element) {
        if(index > size) {
            validIndex(index);
        }

        Node<T> current = head;
        int i = 0;

        if(index == 0) {
            if(isEmpty()) {
                add(element);
                return;
            } else {
                Node<T> newNode = new NodeImpl<T>((T) element, head);
                head = newNode;
                size++;
                return;
            }
        }  
        
        else if(index == size) {
            add(element);
            return;
        }
        
        while(current != null) {
            if(i == (index - 1)) {
                Node<T> temp = current.getNext();
                Node<T> newNode = new NodeImpl<T>((T) element, temp);
                current.setNext(newNode);
                size++;
                return;
            } else {
                current = current.getNext();
                i++;
            }
        }
    }

    public int indexOf(Object element) {
        Node<T> current = head;
        int index = 0;

        while(current != null) {
            if(current.getValue().equals((T) element)) {
                return index;
            }

            index++;
            current = current.getNext();
        }

        return -1;
    }

    public int lastIndexOf(Object element) {
        Node<T> current = head;
        int index = -1;
        int i = 0;

        while(current != null) {
            if(current.getValue().equals ((T) element)) {
                index = i;
            }

            i++;
            current = current.getNext();
        }

        return index;
    }

    public void validIndex(int i) {
        if(i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
    }
    public Node<T> getHead() {
        return head;
    }

    @Override
    public String toString() {
        String list = "";
        Node<T> current = head;

        while(current != null) {
            if(current.getNext() == null)
                list+= current.getValue();
            else
                list += current.getValue() + " -> ";
            current = current.getNext();
        }
        
        return list;
    }
}


