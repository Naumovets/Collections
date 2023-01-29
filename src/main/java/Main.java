import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<Integer> nums = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o2 > o1){
                    return 1;
                }else if(o2 < o1){
                    return -1;
                }return 0;
            }
        });
        for(int i = 0; i < 100; i++){
            nums.add((int)(Math.random() * 10));
        }
        for(int i : nums){
            System.out.println(i);
        }
    }
}