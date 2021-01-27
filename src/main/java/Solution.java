import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public static int game(int[] guess, int[] answer) {
        int k = 0;
        for (int i = 0; i < guess.length; i++) {
            if (guess[i] == answer[i]) {
                k++;
            }
        }
        return k;
    }


    public static void main(String[] args) {
        int[] guess = {1, 4, 3};
        int[] answer = {1, 2, 3};
        long startTime = System.nanoTime();
        System.out.println(game(guess, answer));
        long endTime = System.nanoTime();
        System.out.println(endTime - startTime);

    }
    }