import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

class Point implements Comparable<Point> {
    Long x = 0L;
    Long y = 0L;
    static Point firstPoint;

    Point(Long x, Long y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point p) {
        double angle1 = CalcAngle(firstPoint, this);
        double angle2 = CalcAngle(firstPoint, p);
        double distance1 = CalcDistance(firstPoint, this);
        double distance2 = CalcDistance(firstPoint, p);

        if (angle1 < angle2)
            return -1;
        else if (angle1 > angle2)
            return 1;
        else {
            return Double.compare(distance2, distance1);
        }
    }

    public static double CalcAngle(Point r, Point p) {
        return Math.toDegrees(Math.atan2(p.y - r.y, p.x - r.x));
    }

    public static double CalcDistance(Point r, Point p) {
        return Math.sqrt(Math.pow(r.x - p.x, 2) + Math.pow(r.y - p.y, 2));
    }

    public static int isParallelogram(Point[] result) {
        double d1 = 0;
        double d2 = 0;
        double d3 = 0;
        double d4 = 0;
        d1 = CalcDistance(result[0], result[1]);
        d2 = CalcDistance(result[2], result[3]);

        if (d1 != d2) {
            return 0;
        } else {
            d3 = CalcDistance(result[1], result[2]);
            d4 = CalcDistance(result[0], result[3]);

            if (d3 == d4) {
                return 1;
            } else {
                return 0;
            }
        }
    }

}

public class parallelogram {
    public static void main(String agrs[]) throws Exception {

        try {
            FileReader fileReader = new FileReader("./parallelogram/parallelogram.inp");
            BufferedReader br = new BufferedReader(fileReader);
            FileWriter fileWriter = new FileWriter("./parallelogram/parallelogram.out");
            BufferedWriter bw = new BufferedWriter(fileWriter);

            StringBuilder sb = new StringBuilder();

            Point[] result = new Point[4];

            while (true) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                int done = 0;

                int min_idx = 0;
                Long min_y_value = 10000000000L;
                Long min_x_value = 10000000000L;

                for (int i = 0; i < 4; i++) {

                    Long x = Long.parseLong(st.nextToken());
                    Long y = Long.parseLong(st.nextToken());

                    if (x == 0 && y == 0) {
                        done += 1;
                    }

                    if (min_y_value > y) {
                        min_y_value = y;
                        min_idx = i;
                        min_x_value = x;

                    } else if (min_y_value == y) {
                        if (min_x_value > x) {
                            min_x_value = x;
                            min_idx = i;
                        }
                    }

                    result[i] = new Point(x, y);
                }

                if (done == 4) {
                    break;
                }

                Point temp = result[0];
                result[0] = result[min_idx];
                result[min_idx] = temp;

                Point.firstPoint = result[0];

                Arrays.sort(result, 1, 4);

                int checker = Point.isParallelogram(result);
                sb.append(checker == 1 ? "1\n" : "0\n");
            }

            bw.write(sb.toString().trim());
            bw.flush();

        } catch (

        Exception e) {
            System.err.println(e);
        }
    }
}
