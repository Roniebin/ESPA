package skyline;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Point implements Comparable<Point> {
    Long x = 0L;
    Long y = 0L;
    boolean start = true;

    public Point(Long x, Long y, boolean start) {
        this.x = x;
        this.y = y;
        this.start = start;
    }

    public int compareTo(Point o) {

        if (this.x != o.x) {
            return (int) (this.x - o.x);
        } else if (this.start && !o.start) {
            return -1;
        } else if (!this.start && o.start) {
            return 1;
        } else if (this.start && o.start) {
            return (int) (o.y - this.y);
        } else {
            return (int) (this.y - o.y);
        }
    }
}

public class contour {
    public static void main(String agrs[]) throws Exception {
        try {
            FileReader fileReader = new FileReader("./skyline/contour.inp");
            BufferedReader br = new BufferedReader(fileReader);
            FileWriter fileWriter = new FileWriter("./skyline/contour.out");
            BufferedWriter bw = new BufferedWriter(fileWriter);

            StringBuilder sb = new StringBuilder();
            int testcase = 1;
            while (true) {
                ArrayList<Point> Points = new ArrayList<>();

                boolean done = false;
                while (true) {
                    String line = br.readLine();
                    if (line == null) {
                        done = true;
                        break;
                    }
                    StringTokenizer st = new StringTokenizer(line);

                    Long x_start = Long.valueOf(st.nextToken());
                    Long y = Long.valueOf(st.nextToken());
                    Long x_end = Long.valueOf(st.nextToken());

                    if (x_start == 0 && y == 0 && x_end == 0) {
                        line = br.readLine();
                        break;
                    }
                    Points.add(new Point(x_start, y, true));
                    Points.add(new Point(x_end, y, false));
                }
                if (done == true) {
                    break;
                }
                Collections.sort(Points);
                PriorityQueue<Long> pq = new PriorityQueue<>(2, (a, b) -> (int) (b - a));

                pq.offer(0L);

                ArrayList<Long[]> result = new ArrayList<>();

                for (int i = 0; i < Points.size(); i++) {
                    Long max = pq.peek();

                    if (Points.get(i).start) {
                        pq.offer(Points.get(i).y);

                    } else {
                        pq.remove(Points.get(i).y);
                    }
                    if (max != pq.peek()) {
                        result.add(new Long[] { Points.get(i).x, pq.peek() });
                    }
                }

                Long a = 0L;
                sb.append("Test Case #" + testcase + " : ");
                for (int i = 1; i < result.size(); i++) {
                    a += (result.get(i)[0] - result.get(i - 1)[0]) * result.get(i - 1)[1];
                    if (result.get(i)[1] == 0) {
                        sb.append(a + " ");
                        a = 0L;
                    }
                }
                sb.append("\n");
                testcase += 1;
            }
            bw.write(sb.toString().trim());
            bw.flush();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}