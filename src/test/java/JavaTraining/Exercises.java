package JavaTraining;

import org.testng.annotations.Test;

public class Exercises {


    @Test
    public void addNum() {

        int sum = 0;
        int a = 5;
        int b = 7;
        for (int i = a; i <= b; i++) {
            if (i % 2 == 0)
                sum +=i;
        }
        System.out.println(sum);
    }
}
