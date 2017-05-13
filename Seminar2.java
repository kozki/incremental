/**
 * Created by jernejkozelj on 12/05/2017.
 */
import java.lang.reflect.Array;
import java.util.*;


public class Seminar2 {
    public Seminar2() {}


    //this function makes everything better
    //SERIOUSLY!!!
    public int[][] solve(double[][] points){
        ArrayList<Point> listNew = makeConnections(toArrayList(points));

        //make it faster
        Point[] fstr = listNew.toArray(new Point[listNew.size()]);

        int size = fstr.length;
        int secondSize = size / 2;
        secondSize = size % 2 == 0 ? secondSize : secondSize + 1;

        int[][] resultSolve = new int[secondSize][2];


        int count = 0;
        for (int i=0; i<listNew.size(); i++) {
            if (i%2==1) {
                resultSolve[count][0] = fstr[i-1].index;
                resultSolve[count][1] = fstr[i].index;
                count++;
            }
        }

        return resultSolve;
    }


    //this funtion makes connections between points in convex hull
    public ArrayList<Point> makeConnections(ArrayList<Point> list) {
        ArrayList<Point> newList = new ArrayList<>(sortedX(list));
        ArrayList<Point> result = new ArrayList<>();


        while (!newList.isEmpty() && newList.size()>=2) {
            ArrayList<Point> scan = incremental(newList);
            //make it faster
            Point[] fstr = scan.toArray(new Point[scan.size()]);

            for (int i=1; i<fstr.length; i++) {
                Point cur = fstr[i];
                Point prev = fstr[i-1];
                if ((cur.pos && !prev.pos) || (!cur.pos && prev.pos)) {
                    result.add(prev);
                    newList.remove(prev);
                    result.add(cur);
                    newList.remove(cur);
                }
            }
        }
        return result;
    }


    //incremental algorithm for making upper and lower convex hull
    //here I used only upper hull, no need for the lower one
    public ArrayList<Point> incremental(ArrayList<Point> points) {

        ArrayList<Point> clone = new ArrayList<>(points);
        //make it faster
        Point[] fstr = clone.toArray(new Point[clone.size()]);

        ArrayList<Point> result = new ArrayList<>();

        Point first = fstr[0];
        Point second = fstr[1];

        result.add(first);
        result.add(second);

        for (int i=2; i<fstr.length; i++) {
            result.add(fstr[i]);
            while (result.size()>=3 && (cw(result.get(result.size()-1),
                                            result.get(result.size()-2),
                                            result.get(result.size()-3)) == 1)) {
                result.remove(result.size()-2);
            }
        }

        return result;
    }


    //this function sorts points according to the X value
    public ArrayList<Point> sortedX(ArrayList<Point> list) {
        list.sort(new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                if (p1.x > p2.x) {
                    return 1;
                }else {
                    return -1;
                }
            }
        });

        return list;
    }

    //this function returns 1 if clockwise and 2 if counter clockwise
    //'cause I want to return 1 and 2, that's why - TRUE/FALSE is passe
    public int cw(Point a, Point b, Point c) {
        double val = (b.y - a.y) * (c.x - b.x) - (b.x - a.x) * (c.y - b.y);
        if (val == 0.0) return 0;
        return (val > 0.0) ? 1 : 2;
    }


    //this function stores Points to arraylist
    public ArrayList<Point> toArrayList(double[][] points) {
        ArrayList<Point> pointsNew = new ArrayList<>();
        boolean positive = true;

        for (int i=0; i<points.length; i++) {
            if(points[i][0] < 0.0) {
                positive = false;
            }
            Point temp = new Point(points[i][0], points[i][1], positive, i);
            pointsNew.add(temp);
        }
        return pointsNew;
    }


    //very important thingy
    public static String studentId(){
        return "63110196";
    }


    //class represents Point in plain with X and Y position
    //and also if X is positive or negative, also index
    class Point {
        public double x = 0.0;
        public double y = 0.0;
        public boolean pos = true;
        public int index;

        Point(double x1, double y1, boolean pos1, int index){
            x = x1;
            y = y1;
            if (x1 < 0.0) {
                pos = false;
            }else {
                pos = true;
            }
            this.index = index;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Point) {
                Point p = (Point) obj;
                if ((p.x == x) && (p.y == y) && (p.pos == pos)) {
                    return true;
                }
            }
            return false;
        }
    }
}



