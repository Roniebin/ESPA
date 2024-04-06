package rectangles;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.io.BufferedWriter;
import java.io.BufferedReader;

public class rectangles {

    public static int isgcd(int w, int h) {
        if (h == 0) {
            return w;
        } else {
            return isgcd(h, w % h);
        }

    }

    public static void main(String agrs[]) throws Exception {
        try {
            FileReader fileReader = new FileReader("./rectangles/rectangles.inp");
            BufferedReader br = new BufferedReader(fileReader);
            FileWriter fileWriter = new FileWriter("./rectangles/rectangles.out");
            BufferedWriter bw = new BufferedWriter(fileWriter);

            int n = Integer.parseInt(br.readLine());

            ArrayList<Integer> R = new ArrayList<Integer>();

            int[] L = new int[n];
            int max_L = 0;
            for (int i = 0; i < n; i++) {
                L[i] = Integer.parseInt(br.readLine());
                if (max_L < L[i]) {
                    max_L = L[i];
                }
            }

            int stop = 0;
            for (int w = 2; w < max_L; w++) {

                if (stop == 1) {
                    break;
                }
                for (int h = 1; h < w; h++) {
                    if (stop == 1) {
                        break;
                    }
                    if (w % 2 == 0) {
                        if (h % 2 != 0) {
                            if (isgcd(w, h) == 1) {
                                int a = w * w - h * h;
                                int b = 2 * w * h;
                                int result = 2 * a + 2 * b;
                                if (result < max_L) {
                                    R.add(result);
                                }
                                if (result >= max_L) {
                                    stop = 1;
                                }
                            }
                        }
                    } else {
                        if (h % 2 == 0) {
                            if (isgcd(w, h) == 1) {
                                int a = w * w - h * h;
                                int b = 2 * w * h;
                                int result = 2 * a + 2 * b;
                                if (result < max_L) {
                                    R.add(result);
                                }
                                if (result >= max_L) {
                                    stop = 1;
                                }
                            }
                        }
                    }
                }
            }

            Collections.sort(R);

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < L.length; i++) {
                int sum = 0;
                int count = 0;
                for (int j = 0; j < R.size(); j++) {
                    if (sum < L[i]) {
                        sum += R.get(j);
                        if (sum > L[i]) {
                            break;
                        }
                        count += 1;
                    } else {
                        break;
                    }

                }
                sb.append(count + "\n");
            }

            bw.write(sb.toString().trim());
            bw.flush();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}