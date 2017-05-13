/**
 * Created by jernejkozelj on 26/04/2017.
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.geom.Line2D;
        import java.io.BufferedReader;
        import java.io.FileReader;
        import java.util.Iterator;
        import java.util.LinkedList;

public class CheckSolution {
    public CheckSolution() {
    }

    public static void main(String[] args) {
        double[][] input = (double[][])null;
        int[][] solution = (int[][])null;
        String input_file = null;
        String solution_file = null;

        try {
            input_file = args[0];
            solution_file = args[1];
        } catch (Exception var39) {
            System.out.println("No input or solution file.");
            System.exit(1);
        }

        BufferedReader sl;
        Throwable ac;
        LinkedList rc;
        String ln;
        String[] result;
        Iterator var10;
        int var50;
        try {
            sl = new BufferedReader(new FileReader(input_file));
            ac = null;

            try {
                rc = new LinkedList();

                while((ln = sl.readLine()) != null) {
                    result = ln.split(" ");
                    rc.push(new double[]{Double.parseDouble(result[0]), Double.parseDouble(result[1])});
                }

                var50 = rc.size();
                input = new double[var50][2];
                --var50;

                double[] p;
                for(var10 = rc.iterator(); var10.hasNext(); input[var50--] = p) {
                    p = (double[])var10.next();
                }
            } catch (Throwable var43) {
                ac = var43;
                throw var43;
            } finally {
                if(sl != null) {
                    if(ac != null) {
                        try {
                            sl.close();
                        } catch (Throwable var38) {
                            ac.addSuppressed(var38);
                        }
                    } else {
                        sl.close();
                    }
                }

            }
        } catch (Exception var45) {
            System.out.println("Wrong form of the input file.");
            System.exit(1);
        }

        try {
            sl = new BufferedReader(new FileReader(solution_file));
            ac = null;

            try {
                rc = new LinkedList();

                while((ln = sl.readLine()) != null) {
                    result = ln.split(" ");
                    rc.push(new int[]{Integer.parseInt(result[0]), Integer.parseInt(result[1])});
                }

                var50 = rc.size();
                solution = new int[var50][2];
                --var50;

                int[] var51;
                for(var10 = rc.iterator(); var10.hasNext(); solution[var50--] = var51) {
                    var51 = (int[])var10.next();
                }
            } catch (Throwable var40) {
                ac = var40;
                throw var40;
            } finally {
                if(sl != null) {
                    if(ac != null) {
                        try {
                            sl.close();
                        } catch (Throwable var37) {
                            ac.addSuppressed(var37);
                        }
                    } else {
                        sl.close();
                    }
                }

            }
        } catch (Exception var42) {
            System.out.println("Wrong form of the solution file.");
            System.exit(1);
        }

        boolean var46 = solution.length == input.length / 2;
        if(!var46) {
            System.out.println(var46);
            System.exit(0);
        }

        boolean var48 = all_points_connected_once(input, solution);
        boolean var47 = all_rightfully_connected(input, solution);
        boolean var49 = lines_not_crossing(input, solution);
        boolean var52 = var46 && var48 && var47 && var49;
        System.out.println(var52);
        System.exit(0);
    }

    public static boolean same_sign(double[] p1, double[] p2) {
        return p1[0] > 0.0D && p2[0] > 0.0D || p1[0] < 0.0D && p2[0] < 0.0D;
    }

    private static boolean all_rightfully_connected(double[][] points, int[][] lines) {
        int[][] var2 = lines;
        int var3 = lines.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            int[] line = var2[var4];
            if(same_sign(points[line[0]], points[line[1]])) {
                return false;
            }
        }

        return true;
    }

    private static boolean all_points_connected_once(double[][] points, int[][] lines) {
        int[] counter = new int[points.length];
        int[][] var3 = lines;
        int var4 = lines.length;

        int var5;
        for(var5 = 0; var5 < var4; ++var5) {
            int[] c = var3[var5];
            ++counter[c[0]];
            ++counter[c[1]];
        }

        int[] var7 = counter;
        var4 = counter.length;

        for(var5 = 0; var5 < var4; ++var5) {
            int var8 = var7[var5];
            if(var8 != 1) {
                return false;
            }
        }

        return true;
    }

    private static Line2D[] create_line_objects(int[][] s, double[][] p) {
        Line2D[] lines = new Line2D[s.length];

        for(int i = 0; i < s.length; ++i) {
            lines[i] = new java.awt.geom.Line2D.Double(p[s[i][0]][0], p[s[i][0]][1], p[s[i][1]][0], p[s[i][1]][1]);
        }

        return lines;
    }

    private static boolean lines_not_crossing(double[][] points, int[][] lines) {
        Line2D[] los = create_line_objects(lines, points);

        for(int i = 0; i < los.length; ++i) {
            for(int j = i + 1; j < los.length; ++j) {
                if(los[i].intersectsLine(los[j])) {
                    return false;
                }
            }
        }

        return true;
    }
}
