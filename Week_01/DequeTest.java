import java.util.*;
import java.util.stream.Collectors;

public class DequeTest
{
    public static void main(String args[]) {
      deque();
    }

    private static void deque(){
        Deque<String> deque = new LinkedList<String>();

        deque.push("d");
        deque.addFirst("c");
        deque.addLast("e");
        deque.addFirst("b");
        deque.addLast("f");
        deque.push("a");
        System.out.println(deque);

        String str1 = deque.peekLast();
        String str2 = deque.peekFirst();
        System.out.println(str1+" "+str2);
        System.out.println(deque);

        int i = 0;
        while(i++ < 8){
            System.out.println(deque.pollFirst());
        }
        System.out.println(deque);
    }
}
