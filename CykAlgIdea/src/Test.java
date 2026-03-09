import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

enum Status{
    completed("已完成"),
    underway("进行中"),
    unfinished("未完成");

    private String description;

    private Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

public class Test {

    public static void main(String[] args) {

     int[] capacity = {3,5,4,3};
        int itemSize = 2;

        int minCapacity = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < capacity.length; i++) {
            if (capacity[i] >= itemSize) {
                minCapacity = Math.min(minCapacity, capacity[i]);
            }
        }
        for (int i = 0; i < capacity.length; i++) {
            if (capacity[i] == minCapacity) {
                System.out.println(i);
                return;
            }
        }

    }

}
