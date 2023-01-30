import java.util.Iterator;

public class CarLinkedList implements CarList,CarQueue {

    private Node first;
    private Node last;
    private int size = 0;

    @Override
    public boolean add(Car car, int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        } else if (index == size) {
            return add(car);
        } else {
            Node nodeNext = getNode(index);
            Node nodePrevious = nodeNext.previous;
            Node newNode = new Node(nodePrevious, car, nodeNext);
            if (nodePrevious != null) {
                nodePrevious.next = newNode;
            } else {
                first = newNode;
            }
            nodeNext.previous = newNode;
            size++;
            return true;
        }
    }

    @Override
    public boolean add(Car car) {
        if (size == 0) {
            first = new Node(null, car, null);
            last = first;
        } else {
            Node secondLast = last;
            last = new Node(secondLast, car, null);
            secondLast.next = last;
        }
        size++;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(Car car) {
        Node current = first;
        for (int i = 0; i < size; i++) {
            if (current.value.equals(car)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public boolean remove(Car car) {
        Node node = first;
        for (int i = 0; i < size; i++) {
            if (node.value.equals(car)) {
                return removeAt(i);
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public boolean removeAt(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Node node = getNode(index);
        if (node.previous != null) {
            node.previous.next = node.next;
        } else {
            first = node.next;
        }
        if (node.next != null) {
            node.next.previous = node.previous;
        } else {
            last = node.previous;
        }

        size--;
        return true;
    }

    @Override
    public void clear() {
        last = null;
        first = null;
        size = 0;
    }


    @Override
    public Car get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return getNode(index).value;
    }

    @Override
    public Iterator<Car> iterator() {
        return new Iterator<Car>() {
            Node node = first;
            final int index = 0;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public Car next() {
                Car car = node.value;
                node = node.next;
                return car;

            }
        };
    }

    @Override
    public Car peek() {
        return size > 0 ? get(0) : null;
//        if(size > 0){
//            return get(0);
//        }
//        return null;
    }

    @Override
    public Car poll() {
        Car car = get(0);
        removeAt(0);
        return car;
    }

    private static class Node {
        private Node previous;
        private final Car value;
        private Node next;

        public Node(Node previous, Car value, Node next) {
            this.previous = previous;
            this.value = value;
            this.next = next;
        }
    }

    private Node getNode(int index) {
        Node node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

}
