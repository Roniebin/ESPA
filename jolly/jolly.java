package jolly;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class jolly {
    public static boolean isJolly(int[] diff, int n) {

        Arrays.sort(diff);

        boolean checker = true;
        for (int i = 1; i < diff.length; i++) {
            if (i != diff[i]) {
                checker = false;
            }
        }
        return checker;
    }

    public static void main(String agrs[]) throws Exception {
        try {
            FileReader fileReader = new FileReader("./jolly/jolly.inp");
            BufferedReader br = new BufferedReader(fileReader);
            FileWriter fileWriter = new FileWriter("./jolly/jolly.out");
            BufferedWriter bw = new BufferedWriter(fileWriter);

            StringBuilder sb = new StringBuilder();

            String line = "";
            while ((line = br.readLine()) != null) {

                StringTokenizer st = new StringTokenizer(line);
                int n = Integer.parseInt(st.nextToken());

                int[] diff = new int[n];
                int[] arr = new int[n + 1];

                for (int i = 1; i < n + 1; i++) {
                    arr[i] = Integer.parseInt(st.nextToken());
                }

                for (int i = 1; i < n; i++) {
                    diff[i] = Math.abs(arr[i] - arr[i + 1]);
                }

                if (isJolly(diff, n)) {
                    sb.append("Jolly" + "\n");
                } else {
                    sb.append("Not Jolly" + "\n");
                }
            }

            bw.write(sb.toString().trim());
            bw.flush();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}