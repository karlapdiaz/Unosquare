import java.util.Arrays;

/**
 * Class to find the minimum number that it is not on the array
 */
public class MyClass {

    public static void main(String [] args){
        int [] array={1, 3, 6, 4, 1, 2};
        System.out.println(solution(array));

        int [] array2={1, 2, 3};
        System.out.println(solution(array2));

        int [] array3={-1, -3};
        System.out.println(solution(array3));

    }

    /**
     * Method that search for the minimum number
     * @param A array of number
     * @return minumun number
     */
   public static int solution(int[] A){
       Arrays.sort(A);
       int min=A[0];
       for(int i=0; i<A.length; i++ ){
           if(A[i]==min && A[i]>0)
               min++;
           else if(A[i]<0)
               min=1;
       }
        return min;
   }
}
