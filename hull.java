import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

class Point implements Comparable<Point> {
    int x = 0;
    int y = 0;
    static Point firstPoint;

    Point(int x, int y) {
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

    public double CalcAngle(Point r, Point p) {
        return Math.toDegrees(Math.atan2(p.y - r.y, p.x - r.x));
    }

    public double CalcDistance(Point r, Point p) {
        return Math.sqrt(Math.pow(r.x - p.x, 2) + Math.pow(r.y - p.y, 2));
    }

    public static int ccw(Point r, Point p, Point q) {
        int result = (p.x - r.x) * (q.y - r.y) - (p.y - r.y) * (q.x - r.x);
        if (result == 0)
            return 0;
        if (result > 0)
            return 1;
        return 2;
    }

    public static Point prepreItem(Stack<Point> b) {
        Stack<Point> temp = new Stack<>();
        temp.addAll(b);

        temp.pop();
        return temp.peek();
    }

}

public class hull {

    public static void main(String agrs[]) throws Exception {

        try {
            FileReader fileReader = new FileReader("hull.inp");
            BufferedReader br = new BufferedReader(fileReader);
            FileWriter fileWriter = new FileWriter("hull.out");
            BufferedWriter bw = new BufferedWriter(fileWriter);

            StringBuilder sb = new StringBuilder();

            int n = Integer.parseInt(br.readLine());

            Point[] result = new Point[n];

            int min_y = 1000000;
            int min_x = 1000000;
            int min_idx = -1;

            Point center_point = new Point(0, 0);

            for (int i = 0; i < n; i++) {

                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                Point point = new Point(x, y);

                if (min_x > x) {
                    center_point = point;
                    min_x = x;
                    min_idx = i;
                    min_y = y;
                } else if (min_x == x) {
                    if (min_y > y) {
                        center_point = point;
                        min_y = y;
                        min_idx = i;
                    }
                }
                result[i] = point;
            }

            Point temp = result[0];
            result[0] = center_point;
            result[min_idx] = temp;

            Point.firstPoint = result[0];

            Arrays.sort(result, 1, n);

            Stack<Point> bucket = new Stack<>();

            bucket.push(result[0]);
            bucket.push(result[1]);
            bucket.push(result[2]);

            for (int i = 3; i <= n; i++) {
                int a = 0;
                while (true) {
                    if (i == n) {
                        a = Point.ccw(Point.prepreItem(bucket), bucket.peek(), result[0]);
                    } else {
                        a = Point.ccw(Point.prepreItem(bucket), bucket.peek(), result[i]);
                    }
                    if ((bucket.size() > 1) && (a >= 2)) {
                        bucket.pop();
                    } else {
                        break;
                    }
                }

                if (i != n) {
                    if (a == 0) {
                        bucket.pop();
                    }
                    bucket.push(result[i]);
                }
                if (a == 0) {
                    bucket.pop();
                }
            }

            int c = bucket.size();
            while (bucket.size() != 0) {
                Point r = bucket.pop();
                sb.insert(0, r.x + " " + r.y + "\n");
            }
            sb.insert(0, c + "\n");

            bw.write(sb.toString().trim());
            bw.flush();

        } catch (

        Exception e) {

        }
    }
}
