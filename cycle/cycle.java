import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class cycle {
    public static void main(String agrs[]) throws Exception {

        try {
            FileReader fileReader = new FileReader("./cycle.cycle.inp");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            FileWriter fileWriter = new FileWriter("./cycle/cycle.out");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            StringBuilder sb = new StringBuilder();

            int testcase = Integer.parseInt(bufferedReader.readLine());

            for (int i = 0; i < testcase; i++) {
                int n = Integer.parseInt(bufferedReader.readLine());

                StringTokenizer st = new StringTokenizer(bufferedReader.readLine());

                int[] arr = new int[n + 1];

                for (int j = 1; j < n + 1; j++) {
                    arr[j] = Integer.parseInt(st.nextToken());
                }

                int cycle_num = 0;
                int start = 0;
                int idx = 0;

                for (int z = 1; z < n + 1; z++) {
                    start = z;
                    idx = start;

                    if (arr[idx] != -1) {
                        while (true) {
                            if (start == arr[idx]) {
                                arr[idx] = -1;
                                break;
                            }
                            int temp = idx;
                            idx = arr[idx];
                            arr[temp] = -1;
                        }
                        cycle_num += 1;
                    }
                }
                sb.append(cycle_num + "\n");
            }

            bufferedWriter.write(sb.toString().trim());
            bufferedWriter.flush();

        } catch (Exception e) {

        }
    }
}
