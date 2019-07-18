package ru.otus.hw02;

import java.util.*;
import java.util.function.Consumer;


public class DIYarrayList<E> implements List<E>{

    private int size;
    protected transient int modCount = 0;
    transient Object[] objectArray;
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = new Object[0];

    public DIYarrayList() {
        this.objectArray = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    @Override
    public int size() {
        return this.size;
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
        return new DIYarrayList.DIYItr();
        //throw new UnsupportedOperationException("Iterator<E> iterator()");
    }

    @Override
    public Object[] toArray() {
        //System.out.println("Object[] toArray()");

        return Arrays.copyOf(objectArray, size);
        //throw new UnsupportedOperationException("Object[] toArray()");
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        throw new UnsupportedOperationException("<T> T[] toArray(T[] ts)");
    }

    @Override
    public boolean add(E e) {

        ++this.modCount;

        if (this.size == this.objectArray.length) {
            //var2 = Arrays.copyOf(this.objectArray, (this.newCapacity((this.size + 1))));
            this.objectArray = Arrays.copyOf(this.objectArray, this.newCapacity((this.size + 1)));
        }
        //System.out.println("objectArray.length= " + this.objectArray.length);
       // System.out.println("this.size= " + this.size);
        this.objectArray[this.size] = e;
        //this.size = this.size+1;

        ++this.size;
        return true;

//        ++this.modCount;
//        objectArray  = Arrays.copyOf(objectArray, objectArray.length + 1);
//        objectArray[objectArray.length - 1] = e;
//        ++this.size;
//        return true;


        //throw new UnsupportedOperationException("boolean add(E e)");
    }

    private int newCapacity(int var1) {
        int var2 = this.objectArray.length;
        int var3 = var2 + (var2 >> 1);
       // System.out.println("var1= " + var1);
       // System.out.println("var2= " + var2);
       // System.out.println("(var2 >> 1) = " + (var2 >> 1));
       // System.out.println("newCapacity var3= " + var3);
        if (var3 - var1 <= 0) {
            if (this.objectArray == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
                //System.out.println("DEFAULTCAPACITY_EMPTY_ELEMENTDATA");
               // System.out.println("Math.max(10, var1)= " + Math.max(10, var1));
                return Math.max(1, var1);
            } else if (var1 < 0) {
                throw new OutOfMemoryError();
            } else {
                //System.out.println("else var1= " + var1);
                return var1;
            }
        } else {
            //System.out.println("var3 - 214748 <= 0 ? var3 : hugeCapacity(var1) = " + (var3 - 214748 <= 0 ? var3 : hugeCapacity(var1)));
            return var3 - 214748 <= 0 ? var3 : hugeCapacity(var1);
        }
    }

    private static int hugeCapacity(int var) {
        if (var < 0) {
            throw new OutOfMemoryError();
        } else {
            return var > 2147483639 ? 2147483647 : 2147483639;
        }
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
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (DIYarrayList.this.modCount != this.modCount) {
            throw new ConcurrentModificationException();
        }
        return (E) DIYarrayList.this.objectArray[index];
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

        return new DIYarrayList.DIYListItr(0);
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


    private class DIYListItr extends DIYarrayList<E>.DIYItr implements ListIterator<E> {

        DIYListItr(int iterator) {
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
                if (DIYarrayList.this.modCount != this.expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                try {
                    DIYarrayList.this.set(this.lastRet, e);
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

    private class DIYItr implements Iterator<E> {

        int cursor;
        int lastRet = -1;
        int expectedModCount;

        DIYItr() {

           // System.out.println("MyItr()");

            this.expectedModCount = DIYarrayList.this.modCount;
        }

        @Override
        public boolean hasNext() {

           // System.out.println("boolean hasNext()");

            return this.cursor != DIYarrayList.this.size;
            //throw new UnsupportedOperationException("1");
        }

        @Override
        public E next() {

            //System.out.println("next()");

            if (DIYarrayList.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
            int var1 = this.cursor;
            if (var1 >= DIYarrayList.this.size) {
                throw new NoSuchElementException();
            } else {
                Object[] var2 = DIYarrayList.this.objectArray;
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
