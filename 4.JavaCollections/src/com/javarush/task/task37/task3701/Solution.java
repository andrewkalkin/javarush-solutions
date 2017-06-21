package com.javarush.task.task37.task3701;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/* 
Круговой итератор
*/
public class Solution<T> extends ArrayList<T> {
    public static void main(String[] args) {
        Solution<Integer> list = new Solution<>();
        list.add(1);
        list.add(2);
        list.add(3);

        int count = 0;
        for (Integer i : list) {
            //1 2 3 1 2 3 1 2 3 1
            System.out.print(i + " ");
            count++;
            if (count == 10) {
                break;
            }
        }
    }

    public class RoundIterator implements Iterator<T> {
        int cursor;
        int lastRet = 1;
        int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return (Solution.this.size() > 0);
        }

        @Override
        public T next() {
            checkForComodification();
            int i = cursor;
            if (i == size()) cursor = i = 0;
            cursor++;
            lastRet = i;
            return get(i);
        }

        @Override
        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                Solution.super.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex){
                throw new ConcurrentModificationException();
            }
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new RoundIterator();
    }
}
