package org.example;

import lombok.Data;
import lombok.SneakyThrows;

import java.util.*;

@Data
public class TripletDeque<T> implements Deque<T>, Containerable {
    private final int volume;
    private final int capacity;
    private MyContainer<T> firstContainer = null;
    private MyContainer<T> lastContainer = null;

     class MyContainer<T>{
        private final Object[] data;
        private MyContainer<T> next;
        private MyContainer<T> prev;
        public MyContainer() {
            this.data = new Object[capacity];
            this.next = null;
            this.prev = null;
        }

    }
    public TripletDeque() {
        this.volume = 1000;
        this.capacity = 5;
        this.firstContainer = new MyContainer<T>();
        this.lastContainer = firstContainer;
    }
    public TripletDeque(int volume) {
        this.volume = volume;
        this.capacity = 5;
        this.firstContainer = new MyContainer<>();
        this.lastContainer = firstContainer;
    }
    public TripletDeque(int volume, int capacity) {
        this.volume = volume;
        this.capacity = capacity;
        this.firstContainer = new MyContainer<T>();
        this.lastContainer = firstContainer;

    }

    @Override
    public void addFirst(T object) {
        if(object == null){
            throw new NullPointerException();
        }
        if(size() <= volume){
            if(this.firstContainer.data[0] == null){
                for (int i = 0; i < this.capacity; i++) {
                    if(i == this.capacity-1){
                        this.firstContainer.data[i] = object;
                        break;
                    }
                    if(this.firstContainer.data[i+1] != null){
                        this.firstContainer.data[i] = object;
                        break;
                    }
                }
            }else {
                MyContainer<T> newFirst = new MyContainer<>();
                newFirst.next = this.firstContainer;
                this.firstContainer.prev = newFirst;
                this.firstContainer = newFirst;
                this.firstContainer.data[this.capacity - 1] = object;

            }
        }else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void addLast(T object) {
        if (object == null) {
            throw new NullPointerException();
        }
        if (size() <= volume) {
            if (this.lastContainer.data[capacity - 1] == null) {

                for (int i = capacity - 1; i >= 0; i--) {
                    if (i == 0) {
                        this.lastContainer.data[i] = object;
                        break;
                    }
                    if (this.lastContainer.data[i - 1] != null) {
                        this.lastContainer.data[i] = object;
                        break;
                    }

                }
            } else {
                MyContainer<T> newLast = new MyContainer<>();
                newLast.prev = this.lastContainer;
                this.lastContainer.next = newLast;
                this.lastContainer = newLast;
                this.lastContainer.data[0] = object;

            }
        } else {
            throw new IllegalStateException();

        }
    }

    @Override
    public boolean offerFirst(T object) {
        if(size()<=volume){
            addFirst(object);
            return true;
        }
        return false;
    }

    @Override
    public boolean offerLast(T object) {
        if(size()<=volume){
            addLast(object);
            return true;
        }
        return false;
    }

    @SneakyThrows
    @Override
    public T removeFirst() {
        if(size() == 0){
            throw new NoSuchFieldException();
        } else {
            return pollFirst();
        }
    }

    @SneakyThrows
    @Override
    public T removeLast() {
        if(size() == 0){
            throw new NoSuchFieldException();
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

    @SneakyThrows
    @Override
    public T getFirst() {
            if (size() == 0) {
                throw new NoSuchFieldException();
            } else {
                return peekFirst();
            }
        }
    @SneakyThrows
    @Override
    public T getLast() {
        if(size() == 0){
            throw new NoSuchFieldException();
        } else {
            return peekLast();
        }
    }
    @Override
    public T peekFirst() {
        for (int i = 0; i < this.firstContainer.data.length; i++) {
            if(firstContainer.data[i] != null){
                return (T) firstContainer.data[i];
            }
        }
        return null;
    }

    @Override
    public T peekLast() {
        for (int i = this.lastContainer.data.length-1; i >= 0; i--) {
            if(lastContainer.data[i] != null){
                return  (T)lastContainer.data[i];
            }
        }
        return null;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        Iterator<T> iterator = this.iterator();
        int i = 0;
        while(firstContainer.data[i]==null && size()!=0){
            i++;
        }
        MyContainer<T> currentCont= firstContainer;
        while(iterator.hasNext()){
            if(iterator.next().equals((T)o)){
                while(iterator.hasNext()){
                    currentCont.data[i] = iterator.next();
                    if (i != capacity - 1) {
                        i++;
                    } else {
                        i = 0;
                        currentCont = currentCont.next;
                    }
                }
                if(i ==0){
                    currentCont.prev.next=null;
                    lastContainer = currentCont.prev;
                }else{
                    currentCont.data[i] = null;
                }

                return true;
            }
            if(iterator.hasNext()) {
                if (i != capacity - 1) {
                    i++;
                } else {
                    i = 0;
                    currentCont = currentCont.next;
                }
            }
        }
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        Iterator<T> iterator = this.descendingIterator();
        int i = capacity-1;
        while(lastContainer.data[i]==null && size()!=0){
            i--;
        }
        MyContainer<T> currentCont = lastContainer;
        while(iterator.hasNext()){
            if(iterator.next().equals((T) o)){
                while(iterator.hasNext()){
                    currentCont.data[i] = iterator.next();
                    if (i != 0) {
                        i--;
                    } else {
                        i = capacity-1;
                        currentCont = currentCont.prev;
                    }
                }
                if(i == capacity - 1){
                    currentCont.next.prev=null;
                    firstContainer = currentCont.next;
                }else{
                    currentCont.data[i] = null;
                }
                return true;
            }
            if (i != 0) {
                i--;
            } else {
                i = capacity - 1;
                currentCont = currentCont.prev;
            }

        }
        return false;
    }

    @Override
    public boolean add(T object) {
        addLast(object);
        return true;
    }

    @Override
    public boolean offer(T object) {
        return offerLast(object);
    }

    @Override
    public T remove() {
        return removeFirst();
    }

    @Override
    public T poll() {
        return pollFirst();
    }

    @Override
    public T element() {
        return getFirst();
    }

    @Override
    public T peek() {
        return peekFirst();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if(c.size() == 0){
            return false;
        }
        for (T t : c) {
            add(t);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        while(size()!=0){
            remove();
        }
    }

    @Override
    public void push(T object) {
        addFirst(object);
    }

    @Override
    public T pop() {
        return removeFirst();
    }

    @Override
    public boolean remove(Object o) {
        return removeFirstOccurrence(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        for (T t : this) {
            if (t.equals((T) o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        int size = 0;
        MyContainer<T> current = this.firstContainer;
        size+= containerSize(current);
        while (current.next != null){
            current = current.next;
            size += containerSize(current);
        }
        return size;
    }
    private int containerSize(MyContainer<T> valueDeq){
        int size = 0;
        for (Object o : valueDeq.data) {
            if(o != null){
                size++;
            }
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new SkillIterator();
    }
    private class SkillIterator implements Iterator<T>{
        int cursor;
        MyContainer<T> presentContainer = firstContainer;
        MyContainer<T> returnContainer;
        int dequeCursor;
        int lastRet = -1;
        @Override
        public boolean hasNext() {
            return dequeCursor < size() && size()!=0;
        }

        @SneakyThrows
        @Override
        public T next() {
            if(hasNext()){
                if(cursor == 0 && presentContainer == firstContainer){
                    while(presentContainer.data[cursor]==null){
                        cursor++;
                    }
                }
                lastRet = cursor;
                returnContainer = presentContainer;
                if(cursor == capacity-1){
                    presentContainer = presentContainer.next;
                    cursor =0;
                    dequeCursor++;
                } else{
                    cursor++;
                    dequeCursor++;
                }
                return (T) returnContainer.data[lastRet];
            }
            else{
                throw new NoSuchElementException();
            }

        }
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size()];
        int i = 0;
        for( T t : this){
            array[i]  = t;
            i++;
        }
        return array;
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
        int counter=0;
        MyContainer<T> present=firstContainer;
        while ((counter<cIndex)){
            if(present.next==null){
                return null;
            }
            present=present.next;
            counter++;
        }
        return present.data;
    }
}
