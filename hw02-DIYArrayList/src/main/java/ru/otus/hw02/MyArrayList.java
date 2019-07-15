package ru.otus.hw02;

import java.util.*;
import java.util.function.Consumer;

public class MyArrayList<E> implements List<E>, RandomAccess{

    protected transient int modCount = 0;
    transient Object[] objectArray;
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = new Object[0];

    public MyArrayList() {
        this.objectArray = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    @Override
    public int size() {
        return this.objectArray.length;
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("boolean isEmpty()");
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("boolean contains(Object o)");
    }

    @Override
    public Iterator<E> iterator() {
       // System.out.println("Iterator<E> iterator()");
        return new MyArrayList.MyItr();
        //throw new UnsupportedOperationException("Iterator<E> iterator()");
    }

    @Override
    public Object[] toArray() {
        //System.out.println("Object[] toArray()");

        return Arrays.copyOf(objectArray, objectArray.length);
        //throw new UnsupportedOperationException("Object[] toArray()");
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        throw new UnsupportedOperationException("<T> T[] toArray(T[] ts)");
    }

    @Override
    public boolean add(E e) {
        objectArray  = Arrays.copyOf(objectArray, objectArray.length + 1);
        objectArray[objectArray.length - 1] = e;
        return true;
        //throw new UnsupportedOperationException("boolean add(E e)");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("boolean remove(Object o)");
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        throw new UnsupportedOperationException("boolean containsAll(Collection<?> collection)");
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        throw new UnsupportedOperationException("addAll(Collection<? extends E> collection)");
    }

    @Override
    public boolean addAll(int i, Collection<? extends E> collection) {
        throw new UnsupportedOperationException("addAll(int i, Collection<? extends E> collection)");
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException("removeAll(Collection<?> collection)");
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException("retainAll(Collection<?> collection) ");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("void clear()");
    }

    @Override
    public E get(int index) {
        if (index >= this.objectArray.length) {
            throw new IndexOutOfBoundsException();
        }
        if (MyArrayList.this.modCount != this.modCount) {
            throw new ConcurrentModificationException();
        }
        return (E) MyArrayList.this.objectArray[index];
        //throw new UnsupportedOperationException("E get(int i)");
    }

    @Override
    public E set(int index, E element) {
        E old = get(index);
        objectArray[index] = element;
        return old;
        //throw new UnsupportedOperationException("E set(int i, E e)");
    }

    @Override
    public void add(int i, E e) {
        throw new UnsupportedOperationException("void add(int i, E e)");
    }

    @Override
    public E remove(int i) {
        throw new UnsupportedOperationException("E remove(int i)");
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("int indexOf(Object o)");
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("int lastIndexOf(Object o)");
    }

    @Override
    public ListIterator<E> listIterator() {
        //System.out.println("ListIterator<E> listIterator()");

        //original
        //return new ArrayList.ListItr(0);

        return new MyArrayList.MyListItr(0);
        //throw new UnsupportedOperationException("ListIterator<E> listIterator()");
    }

    @Override
    public ListIterator<E> listIterator(int i) {
        throw new UnsupportedOperationException("ListIterator<E> listIterator(int i)");
    }

    @Override
    public List<E> subList(int i, int i1) {
        throw new UnsupportedOperationException("List<E> subList(int i, int i1)");
    }









    private class MyListItr extends MyArrayList<E>.MyItr implements ListIterator<E> {

        MyListItr(int iterator) {
            super();

           // System.out.println("MyListItr(int iterator)");
            this.cursor = iterator;
        }

        @Override
        public boolean hasPrevious() {
            throw new UnsupportedOperationException("1");
        }

        @Override
        public E previous() {
            throw new UnsupportedOperationException("1");
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException("1");
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException("1");
        }

        @Override
        public void set(E e) {

            //System.out.println("void set(E e) IN MyListItr");

            if (this.lastRet < 0) {
                throw new IllegalStateException();
            } else {
                if (MyArrayList.this.modCount != this.expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                try {
                    MyArrayList.this.set(this.lastRet, e);
                } catch (IndexOutOfBoundsException var3) {
                    throw new ConcurrentModificationException();
                }
            }
            //throw new UnsupportedOperationException("1");
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException("1");
        }
    }

    private class MyItr implements Iterator<E> {

        int cursor;
        int lastRet = -1;
        int expectedModCount;

        MyItr() {

           // System.out.println("MyItr()");

            this.expectedModCount = MyArrayList.this.modCount;
        }

        @Override
        public boolean hasNext() {

           // System.out.println("boolean hasNext()");

            return this.cursor != MyArrayList.this.objectArray.length;
            //throw new UnsupportedOperationException("1");
        }

        @Override
        public E next() {

            //System.out.println("next()");

            if (MyArrayList.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
            int var1 = this.cursor;
            if (var1 >= MyArrayList.this.objectArray.length) {
                throw new NoSuchElementException();
            } else {
                Object[] var2 = MyArrayList.this.objectArray;
                //System.out.println(var1);
                //System.out.println(var2.length);
                if (var1 >= var2.length) {
                    throw new ConcurrentModificationException();
                } else {
                    this.cursor = var1 + 1;
                    return (E)var2[this.lastRet = var1];
                }
            }
            //throw new UnsupportedOperationException("1");
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("1");
        }

        @Override
        public void forEachRemaining(Consumer<? super E> consumer) {
            throw new UnsupportedOperationException("1");
        }
    }
}
