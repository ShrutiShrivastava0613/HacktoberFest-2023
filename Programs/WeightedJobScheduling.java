import java.util.*;

public class Scheduler {
	static class Job{
    int start, finish, profit;
    Job(int start, int finish, int profit) {
        this.start = start;
        this.finish = finish;
        this.profit = profit;
     }
  }
	static int latestNonConflict(Job arr[], int i) {
    for (int j = i - 1; j >= 0; j--) {
        if (arr[j].finish <= arr[i - 1].start)
            return j;
    }
    return -1;
}
	static int findMaxProfitRec(Job arr[], int n) {
    if (n == 1) return arr[n-1].profit;
    int includeProfit = arr[n-1].profit;
    int i = latestNonConflict(arr, n);
    if (i != -1)
    includeProfit += findMaxProfitRec(arr, i+1);
    int excludeProfit = findMaxProfitRec(arr, n-1);
    return Math.max(includeProfit, excludeProfit);
}
	static int findMaxProfit(Job arr[], int n) {
    Arrays.sort(arr,new Comparator<Job>(){
       public int compare(Job j1,Job j2) 
      {
           return j1.finish-j2.finish;
      }
    });
 
    return findMaxProfitRec(arr, n);
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		    int n = 4;
        Job arr[] = new Job[n];
        arr[0] = new Job(3, 10, 20);
        arr[1] = new Job(1, 2, 50);
        arr[2] = new Job(6, 19, 100);
        arr[3] = new Job(2, 100, 200);
        System.out.println("Max profit :"+findMaxProfit(arr,n));
	}

}
