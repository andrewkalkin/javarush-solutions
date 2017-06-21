package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Collection;
import java.util.List;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Serializable, Cloneable{

    public Entry<String> root = new Entry<String>("0");
    int size = 0;
    Bool lastFlag = new Bool();

    private class Bool {
        boolean flag;

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }
    }


    public static void main(String[] args) {
        List<String> list = new CustomTree();
        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));
        }
//        System.out.println("Expected 3, actual is " + ((CustomTree) list).getParent("8"));
        list.remove("5");
//        System.out.println("Expected null, actual is " + ((CustomTree) list).getParent("11"));

    }

    class result{
        Entry<String> entity;
        int level;

        public result(Entry<String> entity, int level) {
            this.entity = entity;
            this.level = level;
        }
    }

    public int getSize(Entry<String> node){
        if (node.leftChild == null && node.rightChild == null) return 1;
        int sum = 0;
        if (node.leftChild != null) sum += getSize(node.leftChild);
        if (node.rightChild != null) sum += getSize(node.rightChild);

        return sum + 1;

    }

    String getParent(String name){
        Entry<String> node = findEntry(root, name);
        if (node == null) return "null";
        return node.parent.elementName;
    }

    public boolean remove(String s) {
        Entry<String> curEntry = findEntry(root, (String) s);

        if (curEntry != null) {
            int numNodes = getSize(curEntry);
            if (curEntry.parent != null && curEntry.parent == root) {
                if (root.leftChild == curEntry) root.leftChild = null;
                if (root.rightChild == curEntry) root.rightChild = null;
                size -= numNodes;
                curEntry.parent.checkChildren();
                return true;
            }

            if (curEntry != root){
                if (curEntry == curEntry.parent.leftChild) curEntry.parent.leftChild = null;
                if (curEntry == curEntry.parent.rightChild) curEntry.parent.rightChild = null;
                size -= numNodes;
                curEntry.parent.checkChildren();
                return true;
            }
        }

        return false;
    }

    public boolean remove(Object s){
        return remove((String) s);
    }

    public boolean add(String s){

        Entry<String> lastParent;
        Entry<String> parent;
        result lastEntry = getEndLevel(root, 0);

        if (lastEntry.entity == root) parent = root; else {
            lastParent = findLast(root, 0, lastEntry.level - 1).entity;

            if (lastParent.rightChild != null) {
                lastFlag.setFlag(true);
                parent = getParent(root,0, lastEntry.level, lastEntry.entity, lastFlag);
            } else {
                lastFlag.setFlag(false);
                parent = getParent(root,0,lastEntry.level - 1, lastEntry.entity, lastFlag);
            }
        }

        if (parent.leftChild == null) {
            parent.leftChild = new Entry<>(s);
            parent.leftChild.parent = parent;
            parent.leftChild.checkChildren();
            parent.checkChildren();
            size++;
            return true;
        } else
        if (parent.rightChild == null) {
            parent.rightChild = new Entry<>(s);
            parent.rightChild.parent = parent;
            parent.rightChild.checkChildren();
            parent.checkChildren();
            size++;
            return true;
        }

        return false;
    }

    result getEndLevel(Entry<String> node, int level){
        if (node.leftChild == null && node.rightChild == null)  return new result(node, level);

        result res1 = null, res2 = null;
        if (node.leftChild != null) res1 =  getEndLevel(node.leftChild,level+1);
        if (node.rightChild != null) res2 = getEndLevel(node.rightChild,level+1);

        if (res1 != null && res2 != null) return res1.level <= res2.level ? res2 : res1;
        if (res1 != null) return res1;
        if (res2 != null) return res2;

        return null;

    }

    result findLast(Entry<String> node, int level, int endLevel){

        if (level == endLevel) return new result(node, level);

        result res1 = null;
        result res2 = null;

        if (node.leftChild != null) res1 = findLast(node.leftChild, level + 1, endLevel);
        if (node.rightChild != null) res2 = findLast(node.rightChild, level + 1, endLevel);

        if (res2 != null) return res2; else if (res1 != null) return res1;

        return null;
    }

    Entry<String> getParent(Entry<String> node, int level, int EndLevel, Entry<String> lastNode, Bool flag) {
        if (node.leftChild == lastNode || node.rightChild == lastNode) flag.setFlag(true);

        if ((node.leftChild == null || node.rightChild == null) && level == EndLevel && flag.isFlag() ) return node;
        if (node.leftChild == null && node.rightChild == null && level != EndLevel) return null;
        if (node.leftChild != null && node.rightChild != null && level == EndLevel) return null;
        Entry<String> res = null;
        if (node.leftChild != null) res = getParent(node.leftChild, level + 1, EndLevel, lastNode, flag);
        if (res != null) return res; else {
            if (node.rightChild != null) {
                res = getParent(node.rightChild, level + 1, EndLevel, lastNode, flag);
                return res;
            }
        }
        return null;
    }


    Entry<String> findEntry(Entry<String> node, String name){

        Entry<String> res = null;

        if (node.elementName.equals(name)) return node;
        if (node.leftChild != null) res = findEntry(node.leftChild, name);
        if (res == null && node.rightChild != null) res = findEntry(node.rightChild, name);
        return res;
    }

    void printTreeRecursive(Entry<String> root){
        System.out.println(String.format("Node: %s; left: %s; right: %s; parent: %s; availableLeft: %s; availableRight: %s", root.elementName, root.leftChild != null ? root.leftChild.elementName : "null", root.rightChild != null ? root.rightChild.elementName : "null", root.parent != null ? root.parent.elementName : "null", root.availableToAddLeftChildren, root.availableToAddRightChildren));
        if (root.leftChild != null) printTreeRecursive(root.leftChild);
        if (root.rightChild != null) printTreeRecursive(root.rightChild);
    }

    void printTree(Entry<String> root) {
        printTreeRecursive(root);
        System.out.println(String.format("Размер: %s", size()));
    }



    @Override
    public String get(int index) {

        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return this.size;
    }

    public String set(int index, String element){
        throw new UnsupportedOperationException();
    }

    public void add(int index, String element){
        throw new UnsupportedOperationException();
    }

    public String remove(int index){
        throw new UnsupportedOperationException();
    }

    public List<String> subList(int fromIndex, int toIndex){
        throw new UnsupportedOperationException();
    }

    protected void removeRange(int fromIndex, int toIndex){
        throw new UnsupportedOperationException();
    }

    public boolean addAll(int index, Collection<? extends String> c){
        throw new UnsupportedOperationException();
    }

    static class Entry<T> implements Serializable{
        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren = true;
        }

        void checkChildren(){
            if (leftChild != null) availableToAddLeftChildren = false;
            if (rightChild != null) availableToAddRightChildren = false;
        }

        boolean isAvailableToAddChildren(){
            return  availableToAddLeftChildren | availableToAddRightChildren;
        }
    }




}
