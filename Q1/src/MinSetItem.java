import java.util.Iterator;

public class MinSetItem <E extends Comparable<E>>{

    public E minInSet(Set<E> givenSet){

        Iterator<E> it = givenSet.iterator();
        E minItem = it.next();
        while (it.hasNext()){
            E temp = it.next();
            if (minItem.compareTo(temp) > 0) { //if temp is smaller than current min item
                minItem = temp;
            }

        }
        return minItem; //return the min item we found
    }
}
