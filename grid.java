import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.StringTokenizer;

public class grid {
    public static void main(String agrs[]) throws Exception {

        try {
            FileReader fileReader = new FileReader("grid.inp");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            FileWriter fileWriter = new FileWriter("grid.out");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            int mod = 1000000007;

            int testcase = Integer.parseInt(bufferedReader.readLine());

            StringBuilder sb = new StringBuilder();

            for (int t = 0; t < testcase; t++) {
                int X = 0;
                int Y = 0;
                int a = 0;
                int b = 0;
                int k = 0;

                String line = bufferedReader.readLine();
                StringTokenizer st = new StringTokenizer(line, " ");
                X = Integer.parseInt(st.nextToken());
                Y = Integer.parseInt(st.nextToken());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                k = Integer.parseInt(st.nextToken());

                int x_axis_done = 0;
                int y_axis_done = 0;

                int[][] dots = new int[X + 1][Y + 1];

                line = bufferedReader.readLine();
                st = new StringTokenizer(line, " ");
                while (st.hasMoreTokens()) {
                    dots[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
                }

                line = bufferedReader.readLine();
                st = new StringTokenizer(line, " ");
                while (st.hasMoreTokens()) {
                    dots[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 2;
                }

                int[][][] point = new int[X + 1][Y + 1][k + 1];

                point[0][0][0] = 1;

                for (int y = 0; y < Y + 1; y++) {
                    for (int x = 0; x < X + 1; x++) {
                        if (x == 0 && y == 0) {
                            continue;
                        }

                        if (dots[x][y] == 2) {
                            if (x == 0) {

                                y_axis_done = 1;
                            } else if (y == 0) {

                                x_axis_done = 1;
                            }
                        } else if (dots[x][y] == 1) {
                            if (y == 0) {

                                if (x_axis_done == 0) {

                                    for (int z = k - 1; z >= 0; z--) {

                                        if (z == k - 1) {
                                            point[x][y][z + 1] = (point[x - 1][y][z] + point[x - 1][y][z + 1]) % mod;
                                        } else {
                                            point[x][y][z + 1] = point[x - 1][y][z];
                                        }
                                    }
                                    if (k == 0) {
                                        point[x][y][0] = (point[x][y][0] + point[x - 1][y][0]) % mod;
                                    } else {
                                        point[x][y][0] = 0;
                                    }
                                }

                            } else if (x == 0) {

                                if (y_axis_done == 0) {

                                    for (int z = k - 1; z >= 0; z--) {

                                        if (z == k - 1) {
                                            point[x][y][z + 1] = (point[x][y - 1][z] + point[x][y - 1][z + 1]) % mod;
                                        } else {
                                            point[x][y][z + 1] = point[x][y - 1][z];
                                        }
                                    }
                                    if (k == 0) {
                                        point[x][y][0] = (point[x][y][0] + point[x][y - 1][0]) % mod;
                                    } else {
                                        point[x][y][0] = 0;
                                    }
                                }
                            } else {
                                for (int z = k - 1; z >= 0; z--) {

                                    if (z == k - 1) {
                                        point[x][y][z + 1] = ((point[x - 1][y][z] + point[x][y - 1][z]) % mod
                                                + (point[x - 1][y][z + 1] + point[x][y - 1][z + 1]) % mod) % mod;
                                    } else {
                                        point[x][y][z + 1] = (point[x - 1][y][z] + point[x][y - 1][z]) % mod;
                                    }
                                }
                                if (k == 0) {
                                    point[x][y][0] = (point[x - 1][y][0] + point[x][y - 1][0]) % mod;
                                } else {
                                    point[x][y][0] = 0;
                                }

                            }
                        } else {

                            if (y == 0) {
                                if (x_axis_done == 0) {
                                    for (int z = 0; z <= k; z++) {
                                        point[x][y][z] = point[x - 1][y][z];
                                    }
                                }
                            } else if (x == 0) {
                                if (y_axis_done == 0) {
                                    for (int z = 0; z <= k; z++) {
                                        point[x][y][z] = point[x][y - 1][z];
                                    }
                                }
                            } else {
                                for (int z = 0; z <= k; z++) {
                                    point[x][y][z] = (point[x - 1][y][z] + point[x][y - 1][z]) % mod;
                                }
                            }
                        }
                        if (x == X && y == Y) {
                            sb.append(point[X][Y][k] + "\n");
                        }
                    }
                }
            }
            String result = sb.toString();
            bufferedWriter.write(result.trim());
            bufferedWriter.flush();
        } catch (

        Exception e) {
            e.printStackTrace();
        }
    }
}
