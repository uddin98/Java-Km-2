package org.example;

import lombok.Data;
import lombok.SneakyThrows;

import java.util.*;

@Data
public class TripletDeque<T> implements Deque<T>, Containerable {
    private MyContainer<T> firstContainer;
    private MyContainer<T> lastContainer;
    private int size;
    private int volume=1000;
    private int capacity;
    private int Elements;
    private MyContainer<T> sequence;

    public TripletDeque(){
        MyContainer<T> InitialContainer = new MyContainer<>(null, null);
        this.firstContainer = InitialContainer;
        this.lastContainer = InitialContainer;
        this.capacity=5;
        this.size = 0;
        this.Elements = 0;
        this.sequence = new MyContainer<>(null, null);
    }
    public TripletDeque(int capacity){
        capacity=5;
    }

    public void Elements() {
        MyContainer<T> present = firstContainer;
        while (present != null) {
            System.out.println(present);
            present = present.next;
        }
    }

    @Override
    public void addFirst(T object) {
        if (object == null) {
            throw new NullPointerException();
        } else if (size >= volume) {
            throw new IllegalStateException();
        } else {
            int j = 0;
            for (int i = capacity-1; i >= 0; i--) {
                if (firstContainer.data[i] == null) {
                    firstContainer.data[i] = object;
                    size++;
                    Elements = size;
                    break;
                } else {
                    j++;
                }
            }
            if (j >= 5) {
                MyContainer<T> newContainer = new MyContainer<>(firstContainer, null);
                firstContainer.prev = newContainer;
                firstContainer = newContainer;
                size++;
                Elements = size;
                newContainer.data[4] = object;
            }
        }
    }

    @Override
    public void addLast(T object) {
        if (object == null) {
            throw new NullPointerException();
        } else if (size >= volume) {
            throw new IllegalStateException();
        } else {
            int j = 0;
            for (int i = 0; i < capacity; i++) {
                if (lastContainer.data[i] == null) {
                    lastContainer.data[i] = object;
                    size++;
                    Elements = size;
                    break;
                } else {
                    j++;
                }
            }
            if (j >= capacity) {
                MyContainer<T> newContainer = new MyContainer<>(null, lastContainer);
                lastContainer.next = newContainer;
                lastContainer = newContainer;
                size++;
                Elements = size;
                newContainer.data[0] = object;
            }
        }
    }

    @Override
    public boolean offerFirst(T object) {
        int flag = size;
        try {
            addFirst(object);
        } catch (IllegalStateException e){
            return false;
        }
        return flag != size;
    }

    @Override
    public boolean offerLast(T object) {
        int flag = size;
        try {
            addLast(object);
        } catch (IllegalStateException e){
            return false;
        }
        return flag != size;
    }

    @Override
    public T removeFirst() {
        if (firstContainer != null) {
            int j = 0;
            for (int i = 0; i < capacity; i++) {
                if (firstContainer.data[i] != null) {
                    T element = (T) firstContainer.data[i];
                    firstContainer.data[i] = null;
                    size--;
                    Elements = size;
                    return element;
                } else {
                    j++;
                }
            }
            MyContainer<T> newContainer = firstContainer.next;
            newContainer.prev = null;
            firstContainer = newContainer;
            return removeFirst();
        }
        throw new NoSuchElementException();
    }
    @SneakyThrows
    @Override
    public T removeLast() {
        if(size() == 0){
            try {
                throw new NoSuchFieldException();
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        } else {
            return pollLast();
        }
    }

    @Override
    public T pollFirst() {
        T returnedValue;
        for (int i = 0; i < this.firstContainer.data.length; i++) {
            if(firstContainer.data[i] != null){
                returnedValue = (T)firstContainer.data[i];
                this.firstContainer.data[i] = null;
                if(i == capacity - 1 && firstContainer.next!=null){
                    firstContainer = firstContainer.next;
                }
                return returnedValue;
            }
        }
        return null;
    }

    @Override
    public T pollLast() {
        T returnedValue;
        for (int i = this.lastContainer.data.length-1; i >= 0; i--) {
            if(lastContainer.data[i] != null){
                returnedValue = (T)lastContainer.data[i];
                this.lastContainer.data[i] = null;
                if(i == 0 && lastContainer.prev!=null){
                    lastContainer = lastContainer.prev;
                }
                return returnedValue;
            }
        }
        return null;
    }

    @Override
    public T getFirst() {
        if (firstContainer != null) {
            for (int i = 0; i < capacity; i++) {
                if (firstContainer.data[i] != null) {
                    return (T) firstContainer.data[i];
                }
            }
            MyContainer<T> newContainer = firstContainer.next;
            newContainer.prev = null;
            firstContainer = newContainer;
            return getFirst();
        }
        throw new NoSuchElementException();
    }

    @Override
    public T getLast() {
        if (lastContainer != null) {
            for (int i = 4; i >= 0; i--) {
                if (lastContainer.data[i] != null) {
                    return (T) lastContainer.data[i];
                }
            }
            MyContainer<T> newContainer = lastContainer.prev;
            newContainer.next = null;
            lastContainer = newContainer;
            return getLast();
        }
        throw new NoSuchElementException();
    }

    @Override
    public T peekFirst() {
        try {
            return getFirst();
        } catch (NoSuchElementException e){
            return null;
        }
    }

    @Override
    public T peekLast() {
        try {
            return getLast();
        } catch (NoSuchElementException object){
            return null;
        }
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean add(T object) {
        return false;
    }

    @Override
    public boolean offer(T object) {
        return false;
    }

    @Override
    public T remove() {
        return null;
    }

    @Override
    public T poll() {
        return null;
    }

    @Override
    public T element() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public void clear() {
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public void push(T object) {

    }

    @Override
    public T pop() {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new SkillIterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public Iterator<T> descendingIterator() {
        return null;
    }

    @Override
    public Object[] getContainerByIndex(int cIndex) {
        MyContainer<T> ref = firstContainer;
        int counter = 0;
        while (cIndex!=counter){
            ref = ref.next;
            counter++;
        }
        return ref.data;
    }

    public class SkillIterator implements Iterator<T> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            if (Elements == 1) {
                Elements = size;
                return false;
            }
            if (Elements > 0) {
                return true;
            }
            return false;
        }

        @Override
        public T next() {
            if (Elements > 0) {
                for (int i = currentIndex; i < capacity; i++) {
                    if (sequence.data[i] != null) {
                        currentIndex = i + 1;
                        Elements--;
                        return (T) sequence.data[i];
                    }
                }
                sequence = sequence.next;
                currentIndex = 0;
                return next();
            }
            throw new NoSuchElementException();
        }
    }

    public class MyContainer<T> {
        private MyContainer<T> next;
        private MyContainer<T> prev;
        private T object;
        private Object[] data;

        public MyContainer(MyContainer<T> next, MyContainer<T> prev) {
            this.next = next;
            this.prev = prev;
            this.data = new Object[5];
        }

        @Override
        public String toString() {
            return  Arrays.toString(data);
        }
    }
}