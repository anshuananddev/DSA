package leetcode;

import util.CommonUtil;
import util.InputHelperUtil;
import util.Solution;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import static util.CommonUtil.RESET;
import static util.CommonUtil.YELLOW_TEXT;

public class S1942 implements InputHelperUtil , Solution {
    public int smallestChair(int[][] times, int targetFriend) {
        // arrival , departure , index
        int[][] timesWithIndex = new int[times.length][times[0].length + 1] ;
        for(int i = 0 ; i < times.length ; i++) {
            timesWithIndex[i] = new int[]{times[i][0] , times[i][1] , i} ;
        }

        //sort on arrival
        Arrays.sort(timesWithIndex , Comparator.comparing((time) -> time[0]));

        // min PQ on departure
        //departure , seatNumber
        PriorityQueue<int[]> occupiedSeats = new PriorityQueue<>(Comparator.comparing((array) -> array[0])) ;

        //seats which are empty having seatNumber < i
        PriorityQueue<Integer> emptySeats = new PriorityQueue<>() ;
        int maxSeatNumber = 0 ;
        for (int[] withIndex : timesWithIndex) {
            int arrivalTime = withIndex[0];
            int seatNumber = maxSeatNumber + 1;
            if (occupiedSeats.isEmpty()) {
                seatNumber = 0;
            } else {
                while (!occupiedSeats.isEmpty() && occupiedSeats.peek()[0] <= arrivalTime) {
                    emptySeats.add(occupiedSeats.poll()[1]);
                }
            }
            if (!emptySeats.isEmpty()) {
                seatNumber = emptySeats.poll();
            }
            occupiedSeats.add(new int[]{withIndex[1], seatNumber});
            maxSeatNumber = Math.max(maxSeatNumber, seatNumber);
            if (withIndex[2] == targetFriend) return seatNumber;
        }
        return 0 ;
    }


    @Override
    public void takeInput()  {
        int optionSelected = 1 ;
        //optionSelected = initialPrompt() ;
        System.out.println(YELLOW_TEXT + "TAKING INPUT FROM ALREADY PRESENT INPUT FILE" + RESET);
        if(optionSelected == 1) {
            String filePath = System.getProperty("java.class.path") +"\\leetcode\\resources\\" + getClass().getSimpleName() + ".txt";
            try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
                int t = Integer.parseInt(br.readLine()) ;
                while(t > 0) {
                    int numberOfFriends = Integer.parseInt(br.readLine()) ;
                    int[][] times = new int[numberOfFriends][] ;
                    int i = 0 ;
                    while(i < numberOfFriends){
                        String[] splits = br.readLine().split(" ") ;
                        times[i++] = new int[]{Integer.parseInt(splits[0]) , Integer.parseInt(splits[1])} ;
                    }
                    int target = Integer.parseInt(br.readLine()) ;
                    System.out.println(CommonUtil.YELLOW_TEXT + CommonUtil.BLACK_BG + "----TEST CASE----" + RESET);
                    System.out.println("times : " + Arrays.deepToString(times));
                    System.out.println("targetFriend : " + target);

                    System.out.println("Ans : " + CommonUtil.GREEN_TEXT + smallestChair(times , target) + RESET);
                    t-- ;
                }
                System.out.println(CommonUtil.YELLOW_TEXT + CommonUtil.BLACK_BG + "---------------" + RESET);
            }catch(IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        takeInput();
    }
}


