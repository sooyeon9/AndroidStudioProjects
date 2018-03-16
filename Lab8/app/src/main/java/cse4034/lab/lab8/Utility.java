package cse4034.lab.lab8;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Utility {

    /*
     * Convert a String to an int-array
     */
    public static int[] getIntArray(String str) {

        ArrayList<Integer> temp = new ArrayList<Integer>();

        StringTokenizer tokenizer = new StringTokenizer(str);

        while (tokenizer.hasMoreTokens()) {
            temp.add(Integer.valueOf(tokenizer.nextToken()));
        }

        int[] result = new int[temp.size()];
        for (int i = 0; i < temp.size(); i++)
            result[i] = temp.get(i);

        return result;
    }

    /*
     * Format int-array to a String for printout
     */
    public static String toString(int[] array) {

        StringBuffer temp = new StringBuffer();

        for (int i : array)
            temp.append(String.valueOf(i) + " ");

        return temp.toString();
    }
}
