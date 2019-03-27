package com.techupstudio.Utils;


import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static com.techupstudio.Utils.MyFuncs.*;

public class Collections {

    public static class EmptyObjectException extends Exception{
        EmptyObjectException(String object){ println( "\n"+object+" is Empty.");}
    }

    //##### Containers (LIST,MAP) #####
    //##### Containers (LIST,MAP) #####
    //##### Containers (LIST,MAP) #####
    //##### Containers (LIST,MAP) #####
    //##### Containers (LIST,MAP) #####

    public static class MasterList<T> {

        private List<T> DATA = new ArrayList<>();

        public MasterList(){}

        MasterList(T[] obj){ DATA = changeToList(obj); }

        MasterList(List<T> obj){ DATA = obj; }

        MasterList(MasterList<T> obj){ DATA = changeToList(obj); }

        private List<T> changeToList(T[] obj) {
            List<T> n = new ArrayList<>();
            for (T i : obj) {
                n.add(i);
            }
            return n;
        }

        private List<T> changeToList(MasterList<T> obj){
            List<T> n = new ArrayList<>();
            for (int i : range(obj.size())){
                n.add((T) obj.pop(i));
            }
            return n;
        }

        public MasterList join(List<T> obj){ DATA.addAll(obj); return clone(); }

        public MasterList join(MasterList<T> obj){
            for (T i : obj.toArray()){ DATA.add(i); } return clone(); }

        public MasterList append(T... obj){ for (T i : obj){ DATA.add(i); } return clone(); }

        public MasterList appendleft(T obj){ DATA.add(0,obj); return clone(); }

        public T pop(int index){ return (T) DATA.get(index); }

        public T popfirst(){ return (T) DATA.get(0); }

        public T poplast(){ return (T) DATA.get(size()-1); }

        public void assign(List<T> obj){ DATA = obj; }

        public void assign(MasterList<T> obj){ DATA = changeToList(obj); }

        public void assign(T[] obj){ DATA = changeToList(obj); }

        public MasterList insert(int index, T obj){ DATA.add(index, obj); return clone(); }

        public MasterList update(int index, T obj){ DATA.set(index, obj); return clone(); }

        public MasterList updateAll(T old_obj, T new_obj){
            while (DATA.contains(old_obj)){
                update(lastIndexOf(old_obj), new_obj);
            }
            return clone();
        }

        public MasterList remove (int index){ DATA.remove(index); return clone(); }

        public MasterList removeAll (T obj){
            while (DATA.contains(obj)){
                remove(lastIndexOf(obj));
            }
            return clone();
        }

        public void clear (){ DATA.clear(); }

        public int count(T obj){
            List<T> REMDATA = new ArrayList<>();
            REMDATA.addAll(DATA);int count = 0;
            while (REMDATA.contains(obj)){
                count++;
                REMDATA.remove(REMDATA.lastIndexOf(obj));
            }
            return count;
        }

        public MasterList<T> reverse(){
            MasterList<T> n = new MasterList<>();
            for (int i : range(size())){
                n.append(pop(size()-(i+1)));
            }
            return n;
        }

        public MasterList<T> sample(int size){ return sample(0,size); }

        public MasterList<T> sample(int begin_index, int end_index){
            MasterList<T> n = new MasterList<>();
            for (int i : range(begin_index,end_index)){
                n.append(pop(i));
            }
            return n;
        }

        public MasterList<T> randsample(int size){
            return new MasterList(MyFuncs.randsample(DATA.toArray(), size)); }

        public int firstIndexOf(T obj){ return DATA.indexOf(obj); }

        public int lastIndexOf(T obj){ return DATA.lastIndexOf(obj); }

        public String toString(){ return DATA.toString(); }

        public int hashCode(){ return DATA.hashCode(); }

        public MasterList<T> clone(){ return new MasterList(DATA);  }

        public void forEach (Consumer<? super T> action){ DATA.forEach(action); }

        public Iterator<T> iterator() { return DATA.iterator(); }
        public ListIterator<T> listiterator() { return DATA.listIterator(); }
        public Iterator<T> listiterator(int index) { return DATA.listIterator(index); }

        public boolean equal (Object obj){
            if (obj.getClass().getSimpleName().contains("List")){
                if (obj.toString().equals(toString())){
                    return true;
                }
            }
            return false;
        }

        public boolean equal (MasterList<T> obj){ return DATA.toArray().equals(obj.toArray()); }

        public int size (){ return DATA.size(); }

        public boolean contains (T... obj){
            if (obj.length > 1){
                for (T i : obj){
                    if (!contains(i)){
                        return false;
                    }
                }
                return true;
            }
            return DATA.contains(obj[0]);
        }

        public boolean contains (MasterList<T> obj){
            for (T i : obj.toArray()){
                if (!contains(i)){
                    return false;
                }
            }
            return true;
        }

        public boolean contains (Collection<?> c){ return DATA.containsAll(c); }

        public boolean isEmpty (){ return DATA.isEmpty(); }

        public T[] toArray (){ return (T[]) DATA.toArray(); }

        public T[] toArray (T[] array){ return DATA.toArray(array); }

        public List<T> toList (){ return DATA; }

        public T[] iter (){ return toArray(); }

        public MasterList<T> toSet (){ return new MasterList(asSet()); }

        public List asSet(){
            List<T> n = new ArrayList<>();
            DATA.forEach((o) -> {
                if (!n.contains(o)) {
                    n.add(o);
                }
            });
            return n;
        }

        public MasterList<T> sort() { return changeToMyList(new SortedNumArray(toDouble(DATA.toArray())).toDouble()); }

        private MasterList<T> changeToMyList(Double[] toDouble) {
            MasterList<T> n = new MasterList<>();for (Double i : toDouble){ n.append((T) i); }return n;
        }

        public boolean issupersetOf (List<T> obj){ return DATA.containsAll(obj); }

        public boolean issubsetOf (List<T> obj){ return obj.containsAll(DATA); }

        public boolean issupersetOf (MasterList<T> obj){
            List<T> n = new ArrayList<>();
            for (T i : obj.toArray()){
                n.add(i);
            }
            return DATA.containsAll(n);
        }

        public boolean issubsetOf (MasterList<T> obj){
            List<T> n = new ArrayList<>();
            for (T i : obj.toArray()){
                n.add(i);
            }
            return n.containsAll(DATA);
        }

    }

    public static class Dict<K,V>{

        private Map<K,V> DATA = new HashMap();

        Dict(){}
        Dict(Map<K,V> obj){ DATA = obj;}

        public void assign(Map<K,V> obj){ DATA = obj; }
        public void join(Map<K,V> obj){ obj.forEach((k,v)-> DATA.put(k,v)); }
        public void add(K key, V value){ DATA.put(key,value); }
        public V get(K key){ return DATA.get(key); }
        public void ifabsentSet(K key, V value){ DATA.putIfAbsent(key,value); }
        public V getOrDefault(K key, V defaultValue){ return DATA.getOrDefault(key, defaultValue); }
        public void remove(K key){ DATA.remove(key); }
        public void remove(K key, V value){ DATA.remove(key,value); }
        public void replace(K key, V value){ DATA.remove(key); }
        public void replace(K key, V value, V new_value){ DATA.remove(key,value); }
        public boolean hasKey(K key){ return DATA.containsKey(key); }
        public boolean hasValue(K key, V value){ return DATA.containsValue(value); }
        public Set keys(){ return DATA.keySet(); }
        public Collection<V> values(){ return DATA.values(); }

        public int count(K key) {
            int count = 0;
            for (K i : DATA.keySet()){
                if (key == i){
                    count++;
                }
            }
            return count;
        }
        public int count(K key, V value) {
            int count = 0;
            for (K i : DATA.keySet()){
                if (key == i && value == DATA.get(key)){
                    count++;
                }
            }
            return count;
        }

        public String[][] iter(){
            String[][] n = new String[size()][];
            int index = 0;
            for (K i : DATA.keySet()){
                n[index] = new String[]{i.toString(), get(i).toString()};
                index++;
            }
            return n;
        }


        public int size()  { return DATA.size(); }
        public void clear() { DATA.clear(); }
        public void forEach(BiConsumer<? super K, ? super V> action) { DATA.forEach(action); }
        public Dict clone() { return new Dict(DATA); }
        public String toString() { return DATA.toString(); }
        public int hashCode() { return DATA.hashCode(); }
        public boolean isEmpty() { return DATA.isEmpty(); }



    }

    public static class StatsArray extends MasterList {

        StatsArray() {}

        StatsArray(double... numbers){ assign(numbers); }

        StatsArray(List<Double> numbers){ assign(numbers);}

        StatsArray(MasterList<Double> numbers){ assign(numbers); }

        public StatsArray(Double[] numbers) { assign(numbers);}

        public void assign(double... numbers) { super.clear();for (Double i : numbers){ append(i); } }

        private Double[] getSorted(){ return new SortedNumArray(toDouble(super.toArray())).toDouble(); }
        public Object[] getSortedArray(){ return getSorted(); }
        public Object[] getSet(){ return super.asSet().toArray(); }
        public Object[] getSortedSet(){ return new StatsArray(getSorted()).asSet().toArray(); }

        public Object getLCM (){ return MyFuncs.getLCM(toDouble(super.asSet().toArray())); }
        public Object getHCF (){ return MyFuncs.getHCF(toDouble(super.asSet().toArray())); }
        public Object[] getShuffle (){ return shuffle(toDouble(toArray())); }
        public Object[] getSample (int end_index){ return super.sample(end_index).toArray();}
        public Object[] getSample (int start_index, int end_index){ return super.sample(start_index, end_index).toArray();}
        public Object[] getRandomSample (int size){ return super.randsample(size).toArray(); }
        public Object[][] getRandomSample (int size, int groups){ return MyFuncs.randsample(toDouble(toArray()), size, groups); }


        public double getMean(){ return  (double) getSum()/super.size(); }

        public double getMax(){ return getSorted()[size()-1]; }

        public double getMin(){ return getSorted()[0]; }

        public double getSum(){
            double total = 0.0;
            for (int i : range(super.size())) { total += (double) super.pop(i); }
            return total;
        }

        public double getMedian(){
            double mMedian = 0.0;
            if (size() > 1) {
                if (size() % 2 == 0) { mMedian = (getSorted()[(size()/2)-1] + getSorted()[(size()/2)])/2; }
                else { mMedian = getSorted()[((size()-1) / 2)]; }
            }
            else{ if (size() == 1){ mMedian =  (double) super.pop(0); } }
            return mMedian;
        }

        public String getMode(){
            int maxCount = 0;
            boolean duplicateMode = false;
            Object mMode = 0.0;
            if (size() > 1) {
                for (int i : range(super.toSet().size())) {
                    int counter = count(super.toSet().pop(i));
                    if (counter == maxCount){
                        duplicateMode = true;
                    }
                    if (counter > maxCount) {
                        duplicateMode = false;
                        maxCount = counter;
                        mMode = pop(i);
                    }
                }
                if (duplicateMode){
                    return null;
                }
                return mMode.toString();
            }
            else{
                if (size() == 1){
                    mMode = pop(0);
                }
            }
            return  mMode.toString();
        }

        public String getModeCount(){
            if (getMode() != null) { return MyFuncs.toStrings(count(MyFuncs.toDouble(getMode()))); } return null;
        }


        public double getStandardDiv(){
            double stanDiv = 0;
            for (double num : toDouble(toArray())){
                stanDiv += Math.pow(num - (double) getMean(),2);
            }
            return Math.sqrt(stanDiv/size());// or Math.sqrt(getVariance());
        }

        public double getVariance(){
            double mVariance = 0;
            for (double i : toDouble(toArray())) {
                mVariance += (i - (double) getMean()) * (i - (double) getMean());
            }
            mVariance /= size();
            return mVariance;// or getStandardDiv/size();
        }

        public double getCoVariance(Double array[]){
            double sum = 0;
            if (size() == array.length) {
                StatsArray coArray = new StatsArray(array);
                for (int i : range(size())) {
                    sum += ((double) pop(i) - getMean()) * ((double) coArray.pop(i) - coArray.getMean());
                }
            }
            return sum / (size() - 1);
        }

    }

    //##### END CONTAINERS #####
    //##### END CONTAINERS #####
    //##### END CONTAINERS #####
    //##### END CONTAINERS #####
    //##### END CONTAINERS #####


    public static class BinaryTree {

        private class Node{

            public Integer value;
            public Integer value_count = 1;
            public Node left;
            public Node right;

            Node(int value){ this.value = value; }

            public String toString(){ return format("Node(value:<>, count:<>)",  value, value_count); }

            public Node clone(){
                Node n = new Node(value);
                n.left = left;
                n.right = right;
                return n;
            }

        }

        private Node head;
        private List<Node> nodeList = new ArrayList<>();

        BinaryTree() {
        }

        BinaryTree(Integer start) { head = new Node(start); }

        BinaryTree(Node start) { head = start; }

        public void add(int value){
            if (head == null){ head = new Node(value);return; }
            Node current_node = head;
            while (current_node != null) {
                if (current_node.value == value) {
                    current_node.value_count += 1;
                    break;
                }
                else if (value < current_node.value){
                    if (current_node.left == null) {
                        current_node.left = new Node(value);
                        break;
                    }
                    else {
                        current_node = current_node.left;
                    }
                }
                else if (value > current_node.value){
                    if (current_node.right == null) {
                        current_node.right = new Node(value);
                        break;
                    }
                    else {
                        current_node = current_node.right;
                    }
                }
            }
        }

        public String toString(){
            return format("BinaryTree(Head : <>)", head.toString());
        }

        public Node[] toArray(){
            Node[] n = new Node[nodeList.size()];
            nodeList.clear();
            buildArray(head);
            n = nodeList.toArray(n);
            return n;
        }

        public List<Node> toList(){
            nodeList.clear();
            buildArray(head);
            return nodeList;
        }

        private void buildArray(Node current_node){
            if (current_node == null){
                return;
            }
            buildArray(current_node.left);
            nodeList.add(current_node);
            buildArray(current_node.right);
        }

        private void printOrdered(Node current_node){
            if (current_node == null){
                return;
            }
            printOrdered(current_node.left);
            print(current_node);print();
            printOrdered(current_node.right);
        }

        public void printSorted(){
            printOrdered(head);
        }

    }

    public static class Stack<T>{

        protected MasterList<T> list = new MasterList();

        public void push(T value){ list.insert(0,value); }

        public T pop(){
            T top = list.popfirst();
            list.remove(0);
            return top;
        }

        public T peekTop(){ return list.popfirst(); }

        public int size(){ return list.size(); }

        public boolean isEmpty(){return list.isEmpty();}

        public String toString(){ return list.toString();}

    }

    public static class StackList<T> extends Stack<T>{

        public StackList(){}

        public T peekElementAt(int index){ return super.list.pop(index); }

        public T pop(int index){ T temp = super.list.pop(index); super.list.remove(index); return temp;  }

        public void replace(int index, T object){ super.list.update(index, object); }

        public void remove(int index){ super.list.remove(index); }

        public void remove(T object){ super.list.remove(super.list.firstIndexOf(object)); }

        public void insert(int index, T object){ super.list.insert(index, object); }

        public boolean contains(T object){ return super.list.contains(object); }

        public int findFirstIndexOf(T object){ return super.list.firstIndexOf(object); }

        public int findLastIndexOf(T object){ return super.list.lastIndexOf(object); }

    }

    public static class LinkedList<T>{

        private int SIZE = 0;

        private class Node{

            public T value;
            public Node previous;
            public Node next;

            Node(T value){ this.value = value; }

            public String toString(){ return format("Node(value:<>)", value); }

            public Node clone(){
                Node n = new Node(value);
                n.previous = previous;
                n.next = next;
                return n;
            }

        }

        private Node head = null;

        LinkedList(){}

        public void append(T value){
            if (isEmpty()){
                head = new Node(value); SIZE++;
            }
            else{
                getTempAt(size()-1).next = new Node(value); SIZE++;
            }
        }

        public void insert(int index, T value){
            if (validateIndex(index)){

                if (index == 0){
                   Node temp = new Node(value);temp.next = head;
                   head = temp; SIZE++;
                   return;
                }

                Node new_node = new Node(value);
                new_node.next = getTempAt(index-1).next;
                getTempAt(index-1).next = new_node;

                SIZE++;
            }
        }

        public void replace(int index, T value){
            if (validateIndex(index)){ getTempAt(index).value = value; }
        }

        public void replaceFirst(T object, T value){
            int found = findFirstIndexOf(object);
            if (found != -1){ replace(found, value); }
        }

        public void replaceLast(T object, T value){
            int found = findLastIndexOf(object);
            if (found != -1){ replace(found, value); }
        }

        public void replaceAll(T object, T value){

            if (findLastIndexOf(object) != -1 && get(findFirstIndexOf(object)) == value) { return; }

            while (findFirstIndexOf(object) != -1){
                replace(findFirstIndexOf(object), value);
            }
        }

        public void remove(int index){
            if (validateIndex(index)){
                if (index == 0){
                    head = head.next;SIZE--;
                    return;
                }
                getTempAt(index-1).next = getTempAt(index-1).next.next;
                SIZE--;
            }
        }

        public void removeObject(T object){
           int found = findFirstIndexOf(object);
           if (found == -1){ return; }
           else{ remove(found); }
        }

        public void removeAllOf(T object){
           while (findFirstIndexOf(object) != -1){ removeObject(object); }
        }

        public T get(int index){
            if (validateIndex(index)){
                return getTempAt(index).value;
            }
            return null;
        }

        public int findFirstIndexOf(T object){
            if (!isEmpty()){
                for (int i : range(size())) {
                    if (get(i) == object){ return i; }
                }
            }
            return -1;
        }

        public int findLastIndexOf(T object){
            if (!isEmpty()){
                for (int i : range(size())) {
                    if (get((SIZE-1)-i) == object){ return (SIZE-1)-i; }
                }
            }
            return -1;
        }

        public int count(T object){
            int counter = 0;
            for (int i : range(size())){
                if (get(i) == object){ counter++; }
            }
            return counter;
        }

        public int size(){  return SIZE; }

        public void clear(){  head = null; SIZE = 0; }

        public boolean isEmpty(){ return size() == 0; }

        public String toString(){
            String list = "LinkedList[";
            for (int i : range(size())){
                list += get(i)+ ((i == size()-1) ? "":", ");
            }
            return list + "]";
        }

        private boolean validateIndex(int index){
            if (index < 0 || index >= SIZE){
                throw new IndexOutOfBoundsException();
            }
            else{ return true; }
        }

        private Node getTempAt(int index){
            Node temp = head;

            for (int i : range(index)){
                temp = temp.next;
            }

            return temp;
        }

    }

    public static class Queue<T>{

        private LinkedList<T> DATA = new LinkedList<>();

        Queue(){}

        public void enqueue(T object){ DATA.append(object); }

        public T dequeue(){ T temp = peekFront(); DATA.remove(0); return temp; }

        public T peekFront(){
            if (isEmpty()){
                try {
                    throw new EmptyObjectException("Queue");
                } catch (EmptyObjectException e) {
                    e.printStackTrace();
                }
            }
            return DATA.get(0);
        }

        public boolean isEmpty(){ return DATA.isEmpty();}

        public int size(){ return DATA.size();}

        public String toString(){ return DATA.toString().replace("LinkedList", "Queue");}

    }

    public static class Deque<T>{

        private LinkedList<T> DATA = new LinkedList<>();

        Deque(){}

        public void enqueueEnd(T object){ DATA.append(object); }

        public void enqueueStart(T object){ DATA.insert(0,object); }

        public T dequeueEnd(){ T temp = peekBack(); DATA.remove(size()-1); return temp; }

        public T dequeueStart(){ T temp = peekFront(); DATA.remove(0); return temp; }

        public T peekFront(){
            if (isEmpty()){
                try {
                    throw new EmptyObjectException("Deque");
                } catch (EmptyObjectException e) {
                    e.printStackTrace();
                }
            }
            return DATA.get(0);
        }

        public T peekBack(){
            if (isEmpty()){
                try {
                    throw new EmptyObjectException("Deque");
                } catch (EmptyObjectException e) {
                    e.printStackTrace();
                }
            }
            return DATA.get(size()-1);
        }

        public boolean isEmpty(){ return DATA.isEmpty();}

        public int size(){ return DATA.size();}

        public String toString(){ return DATA.toString().replace("LinkedList", "Deque");}

    }

    public static class StackForPostFitExpression extends Stack {

        public String pop(){
            String top = super.list.popfirst().toString();
            super.list.remove(0);

            if (top == "+" || top == "-" || top == "*" || top == "/" || top == "//" || top == "%" || top == "^"){
                return pop() + top + pop();
            }
            else{
                return top;
            }
        }
    }

}
