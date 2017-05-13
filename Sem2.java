import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Sem2 {

    public static void main(String[] args) {
        //System.out.println(Seminar2.studentId());

        //this is example of points
        double[][] points = {{-10.0, 10.0}, {-5.0, 10.0}, {10.0, 0.0},
                            {-6.0, -8.0}, {30.0, 1.0}, {-20.0, -15.0},
                            {20.0, 20.0}, {25.0, -14.0}, {5.0, -15.0}, {-5.0, -10.0}};



        Seminar2 sem2 = new Seminar2();

       //int[][] test = sem2.solve(points);


        ArrayList<Seminar2.Point> list = sem2.toArrayList(points);
        /*
        sem2.sortedX(list);
        ArrayList<Incremental.Point> neki = sem2.incremental(list);

        for (Incremental.Point p : neki) {
            System.out.print(p.index);
            System.out.println();
        }




        for (int i=0; i<test.length; i++) {
            for (int j=0; j<test[0].length; j++) {
                System.out.println(test[i][j]);
            }
            System.out.println();
        }
        */


        System.out.println(Arrays.deepToString(sem2.solve(points)).replace("], ", "]\n"));





    }
}
