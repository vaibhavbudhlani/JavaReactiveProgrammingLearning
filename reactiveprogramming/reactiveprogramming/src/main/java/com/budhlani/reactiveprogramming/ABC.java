package com.budhlani.reactiveprogramming;

import java.util.Scanner;

public class ABC {
    {
        System.out.println("I am Awesome");
    }
    public static void main(String[] args) {


        Scanner scan = new Scanner(System.in);
        long n = scan.nextLong();
        long[] ls = new long[(int) n];
        long minOdd = 999998;
        long tsum = 0;
        for(long i =0;i<n;i++){

            ls[(int) i] = scan.nextLong();
            tsum = tsum + ls[(int) i];
            if(ls[(int) i] %2 !=0 ){
                minOdd = Math.min(minOdd , ls[(int) i]);
            }
        }

        if(tsum %2 != 0 ){
            System.out.print(tsum);
        }
        else if(minOdd != 999998){
            System.out.print(tsum-minOdd);
        }
        else{
            System.out.print("-1");
        }
    }

    public static int findMaxPetalsToFinishOnY(int[] petals) {
        int totalPetals = 0;
        int maxOddPetals = 0;

        for (int petal : petals) {
            totalPetals += petal;

            if (petal % 2 != 0) {
                // If the flower has odd petals, update the maximum odd petals
                maxOddPetals = Math.max(maxOddPetals, petal);
            }
        }

        if (totalPetals % 2 == 1) {
            // If the sum of all petals is odd, it's not possible to finish on "Y"
            return -1;
        }

        // If there is at least one flower with odd petals, include it in the sum
        if (maxOddPetals > 0) {
            totalPetals -= maxOddPetals;
        }

        return totalPetals;
    }
}