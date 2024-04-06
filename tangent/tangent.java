package tangent;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
    int x;
    int y;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    static Node firstPoint;

    @Override
    public int compareTo(Node p) {
        if (this.y > p.y) {
            return 1;
        } else {
            return -1;
        }
    }

    public double CalcAngle(Node r, Node p) {
        return Math.toDegrees(Math.atan2(p.y - r.y, p.x - r.x));
    }

    public double CalcDistance(Node r, Node p) {
        return Math.sqrt(Math.pow(r.x - p.x, 2) + Math.pow(r.y - p.y, 2));
    }
}

public class tangent {

    public static void main(String agrs[]) throws Exception {
        try {
            FileReader fileReader = new FileReader("./tangent/tangent.inp");
            BufferedReader br = new BufferedReader(fileReader);
            FileWriter fileWriter = new FileWriter("./tangent/tangent.out");
            BufferedWriter bw = new BufferedWriter(fileWriter);

            int n = Integer.parseInt(br.readLine());

            for (int i = 0; i < n; i++) {
                int m = Integer.parseInt(br.readLine());
                Node[] A = new Node[m];

                int A_max_y = -999999999;
                int A_max_y_idx = 0;
                int A_min_y = 999999999;
                int A_min_y_idx = 0;
                int A_max_x = -999999999;
                int A_max_x_idx = 0;
                int A_min_x = 999999999;
                int A_min_x_idx = 0;

                int B_max_y = -999999999;
                int B_max_y_idx = 0;
                int B_min_y = 999999999;
                int B_min_y_idx = 0;
                int B_max_x = -999999999;
                int B_max_X_idx = 0;
                int B_min_x = 999999999;
                int B_min_x_idx = 0;

                for (int j = 0; j < m; j++) {
                    StringTokenizer st = new StringTokenizer(br.readLine());
                    A[j] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

                    if (A_max_y < A[j].y) {
                        A_max_y = A[j].y;
                        A_max_y_idx = j;
                    }

                    if (A_min_y > A[j].y) {
                        A_min_y = A[j].y;
                        A_min_y_idx = j;
                    }

                    if (A_max_x < A[j].x) {
                        A_max_x = A[j].x;
                        A_max_x_idx = j;
                    }

                    if (A_min_x > A[j].x) {
                        A_min_x = A[j].x;
                        A_min_x_idx = j;
                    }
                }

                int k = Integer.parseInt(br.readLine());
                Node[] B = new Node[k];
                for (int j = 0; j < k; j++) {
                    StringTokenizer st = new StringTokenizer(br.readLine());
                    B[j] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

                    if (B_max_y < B[j].y) {
                        B_max_y = B[j].y;
                        B_max_y_idx = j;
                    }

                    if (B_min_y > B[j].y) {
                        B_min_y = B[j].y;
                        B_min_y_idx = j;
                    }

                    if (B_max_x < B[j].x) {
                        B_max_x = B[j].x;
                        A_max_x_idx = j;
                    }

                    if (B_min_x > B[j].x) {
                        B_min_x = B[j].x;
                        B_min_x_idx = j;
                    }
                }

                ArrayList<Node> result_A = new ArrayList<>();
                ArrayList<Node> result_B = new ArrayList<>();
                if (A_max_x < B_max_x) {
                    int cc = 0;
                    for (int j = 0; j < m; j++) {
                        if (j == m - 1) {
                            if (A[j].y > A[j - 1].y) {
                                result_A.add(A[j]);
                                break;
                            }
                        }
                        if (A[j].y < A[j + 1].y) {
                            if (cc != 1) {
                                result_A.add(A[j]);
                            }
                        }
                        if (j == A_max_y_idx || j == A_min_y_idx) {
                            result_A.add(A[j]);
                            cc = 1;
                        }
                    }
                    cc = 0;
                    for (int j = 0; j < k; j++) {
                        if (j == k - 1) {
                            if (B[j].y < B[j - 1].y) {
                                result_B.add(B[j]);
                                break;
                            }
                        }
                        if (B[j].y > B[j + 1].y) {
                            if (cc != 1) {
                                result_B.add(B[j]);
                            }
                        }
                        if (j == B_max_y_idx || j == B_min_y_idx) {
                            result_B.add(B[j]);
                            cc = 1;
                        }
                    }
                } else {
                    int cc = 0;
                    for (int j = 0; j < m; j++) {
                        if (j == m - 1) {
                            if (A[j].y < A[j - 1].y) {
                                result_A.add(A[j]);
                                break;
                            }
                        }
                        if (A[j].y > A[j + 1].y) {
                            if (cc != 1) {
                                result_A.add(A[j]);
                            }
                        }
                        if (j == A_max_y_idx || j == A_min_y_idx) {
                            result_A.add(A[j]);
                            cc = 1;
                        }
                    }
                    cc = 0;
                    for (int j = 0; j < k; j++) {
                        if (j == k - 1) {
                            if (B[j].y > B[j - 1].y) {
                                result_B.add(B[j]);
                                break;
                            }
                        }
                        if (B[j].y < B[j + 1].y) {
                            if (cc != 1) {
                                result_B.add(B[j]);
                            }
                        }
                        if (j == B_max_y_idx || j == B_min_y_idx) {
                            result_B.add(B[j]);
                            cc = 1;
                        }
                    }
                }

                Collections.sort(result_A);
                Collections.reverse(result_A);
                Collections.sort(result_B);

                result_A.addAll(result_B);
                result_A.add(result_A.get(0));

                int first = 0;
                for (int j = 0; j < result_A.size() - 1; j++) {
                    first += (result_A.get(j).x * result_A.get(j + 1).y);
                }
                int second = 0;
                for (int j = 0; j < result_A.size() - 1; j++) {
                    second += (result_A.get(j).y * result_A.get(j + 1).x);
                }

                System.out.println(Math.abs((second - first) / 2));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}