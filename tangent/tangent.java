package tangent;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
    Long x;
    Long y;
    int idx;
    int group;

    static Node firstPoint;

    public Node(Long x, Long y, int idx, int group) {
        this.x = x;
        this.y = y;
        this.idx = idx;
        this.group = group;
    }

    @Override
    public int compareTo(Node o) {
        if (this.x > o.x) {
            return 1;
        } else if (this.x < o.x) {
            return -1;
        } else {
            if (this.y > o.y) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}

class ConvexHull implements Comparable<ConvexHull> {
    Long x;
    Long y;
    int idx;
    int group;
    static ConvexHull firstPoint;

    public ConvexHull(Long x, Long y, int idx, int group) {
        this.x = x;
        this.y = y;
        this.idx = idx;
        this.group = group;
    }

    public double CalcAngle(ConvexHull r, ConvexHull p) {
        return Math.toDegrees(Math.atan2(p.y - r.y, p.x - r.x));
    }

    public static double CalcDistance(ConvexHull r, ConvexHull p) {
        return Math.sqrt(Math.pow(r.x - p.x, 2) + Math.pow(r.y - p.y, 2));
    }

    public static int ccw(ConvexHull r, ConvexHull p, ConvexHull q) {
        Long result = (long) ((p.x - r.x) * (q.y - r.y) - (p.y - r.y) * (q.x - r.x));
        if (result == 0)
            return 0;
        if (result > 0)
            return 1;
        return 2;
    }

    public static ConvexHull prepreItem(Stack<ConvexHull> b) {
        Stack<ConvexHull> temp = new Stack<>();
        temp.addAll(b);

        temp.pop();
        return temp.peek();
    }

    @Override
    public int compareTo(ConvexHull p) {
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
}

public class tangent {

    public static void main(String agrs[]) throws Exception {
        try {

            FileReader fileReader = new FileReader("./tangent/tangent.inp");
            BufferedReader br = new BufferedReader(fileReader);
            FileWriter fileWriter = new FileWriter("./tangent/tangent.out");
            BufferedWriter bw = new BufferedWriter(fileWriter);

            int testcase = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();
            for (int t = 0; t < testcase; t++) {
                int n = Integer.parseInt(br.readLine());

                ArrayList<ConvexHull> convex_hull = new ArrayList<ConvexHull>();

                Node[] A = new Node[n];
                Node[] origin_A = new Node[n];

                for (int i = 0; i < n; i++) {
                    String line = br.readLine();
                    StringTokenizer st = new StringTokenizer(line);

                    Long x = Long.parseLong(st.nextToken());
                    Long y = Long.parseLong(st.nextToken());

                    A[i] = new Node(x, y, i, 0);
                    origin_A[i] = new Node(x, y, i, 0);
                    convex_hull.add(new ConvexHull(x, y, i, 0));
                }

                int k = Integer.parseInt(br.readLine());
                Node[] B = new Node[k];
                Node[] origin_B = new Node[k];

                for (int i = 0; i < k; i++) {
                    String line = br.readLine();
                    StringTokenizer st = new StringTokenizer(line);

                    Long x = Long.parseLong(st.nextToken());
                    Long y = Long.parseLong(st.nextToken());

                    B[i] = new Node(x, y, i, 1);
                    origin_B[i] = new Node(x, y, i, 1);
                    convex_hull.add(new ConvexHull(x, y, i, 1));
                }

                Arrays.sort(A);
                Arrays.sort(B);

                int A_or_B = 0;

                if (A[0].x > B[0].x) {
                    Node.firstPoint = B[0];
                    A_or_B = 1;
                } else if (A[0].x < B[0].x) {
                    Node.firstPoint = A[0];
                    A_or_B = 0;
                } else {
                    if (A[0].y > B[0].y) {
                        Node.firstPoint = B[0];
                        A_or_B = 1;
                    } else {
                        Node.firstPoint = A[0];
                        A_or_B = 0;
                    }
                }

                if (A_or_B == 0) {
                    convex_hull.remove(Node.firstPoint.idx);
                } else {
                    convex_hull.remove(Node.firstPoint.idx + n);
                }

                ConvexHull temp = new ConvexHull(Node.firstPoint.x, Node.firstPoint.y, Node.firstPoint.idx,
                        Node.firstPoint.group);
                ConvexHull.firstPoint = temp;
                Collections.sort(convex_hull);
                convex_hull.add(0, temp);

                Stack<ConvexHull> bucket = new Stack<>();

                bucket.push(convex_hull.get(0));
                bucket.push(convex_hull.get(1));
                bucket.push(convex_hull.get(2));
                for (int i = 3; i <= convex_hull.size(); i++) {
                    int checker = 0;

                    while (true) {
                        if (i == convex_hull.size()) {
                            checker = ConvexHull.ccw(ConvexHull.prepreItem(bucket), bucket.peek(), convex_hull.get(0));
                        } else {
                            checker = ConvexHull.ccw(ConvexHull.prepreItem(bucket), bucket.peek(), convex_hull.get(i));
                        }
                        if ((bucket.size() > 1) && (checker >= 2)) {
                            bucket.pop();
                        } else {
                            break;
                        }
                    }

                    if (i != convex_hull.size()) {
                        bucket.push(convex_hull.get(i));
                    }

                }

                ArrayList<ConvexHull> hull = new ArrayList<>();

                while (bucket.size() != 0) {
                    hull.add(bucket.pop());
                }

                // 볼록 껍질까지 구함
                Collections.reverse(hull);
                int count = 0;
                int ccc = 0;

                ArrayList<ConvexHull> c0_to_c3 = new ArrayList<>();

                int start = 0;
                int last = hull.size();

                ConvexHull prev = hull.get(0);
                int idx = 1;
                while (count != 2) {
                    if (count == 0 && prev.group != hull.get(idx).group) {
                        count += 1;
                        c0_to_c3.add(prev);
                        c0_to_c3.add(hull.get(idx));
                    } else if (count == 1 && prev.group != hull.get(idx).group) {
                        c0_to_c3.add(prev);
                        c0_to_c3.add(hull.get(idx));
                        count += 1;
                    }

                    prev = hull.get(idx);
                    idx += 1;
                    if (idx == last) {
                        idx = 0;
                    }
                }

                start = c0_to_c3.get(0).idx;
                last = c0_to_c3.get(c0_to_c3.size() - 1).idx;
                idx = start;
                ArrayList<ConvexHull> c0_first = new ArrayList<>();
                ArrayList<ConvexHull> c2_first = new ArrayList<>();

                int one_circle = 0;
                while (true) {
                    // A그룹이면
                    if (c0_to_c3.get(0).group == 0) {
                        ConvexHull ss = new ConvexHull(origin_A[idx].x, origin_A[idx].y, origin_A[idx].idx,
                                origin_A[idx].group);

                        c0_first.add(ss);

                        if (one_circle >= 1 && idx == last) {
                            break;
                        }

                        idx = (idx + 1) % origin_A.length;
                        one_circle += 1;
                    } else {

                        ConvexHull ss = new ConvexHull(origin_B[idx].x, origin_B[idx].y, origin_B[idx].idx,
                                origin_B[idx].group);
                        c2_first.add(ss);

                        if (one_circle >= 1 && idx == last) {
                            break;
                        }
                        idx = (idx + 1) % origin_B.length;
                        one_circle += 1;
                    }
                }

                // 만약 점이 3개면 시작하자마자 끝 안에 돌필요가없음
                start = c0_to_c3.get(c0_to_c3.size() - 2).idx;
                last = c0_to_c3.get(1).idx;
                idx = start;

                one_circle = 0;
                while (true) {
                    if (c0_to_c3.get(1).group == 0) {
                        ConvexHull ss = new ConvexHull(origin_A[idx].x, origin_A[idx].y, origin_A[idx].idx,
                                origin_A[idx].group);

                        c0_first.add(ss);

                        if (one_circle >= 1 && idx == last) {
                            break;
                        }

                        idx = (idx + 1) % origin_A.length;
                        one_circle += 1;
                    } else {
                        ConvexHull ss = new ConvexHull(origin_B[idx].x, origin_B[idx].y, origin_B[idx].idx,
                                origin_B[idx].group);
                        c2_first.add(ss);

                        if (one_circle >= 1 && idx == last) {
                            break;
                        }
                        idx = (idx + 1) % origin_B.length;
                        one_circle += 1;
                    }
                }

                c0_first.addAll(c2_first);
                c0_first.add(c0_first.get(0));

                double sum1 = 0L;
                for (int i = 0; i < c0_first.size() - 1; i++) {
                    sum1 += (c0_first.get(i).x * c0_first.get(i + 1).y);
                }
                double sum2 = 0L;
                for (int i = 0; i < c0_first.size() - 1; i++) {
                    sum2 += (c0_first.get(i).y * c0_first.get(i + 1).x);
                }

                String ff = String.format("%.1f", (sum2 - sum1) / 2);

                sb.append(ff + "\n");
            }
            bw.write(sb.toString().trim());
            bw.flush();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}