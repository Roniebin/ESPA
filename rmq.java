import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class rmq {

    static int miss = -1;

    public static void changing(int[] result, int[] arr, int start, int end, int idx, int change, int node) {
        if (start == end) {
            result[node] = idx;
            return;
        }

        int mid = (start + end) / 2;

        if (start <= idx && idx <= mid) {
            changing(result, arr, start, mid, idx, change, node * 2);
        } else {
            changing(result, arr, mid + 1, end, idx, change, node * 2 + 1);
        }

        int L = result[node * 2];
        int R = result[node * 2 + 1];

        if (arr[L] <= arr[R]) {
            result[node] = L;
        } else {
            result[node] = R;
        }
    }

    public static int findMinimum(int[] result, int[] arr, int start, int end, int left, int right, int node) {
        if (end < left || start > right) {
            return miss;
        }

        if (left <= start && end <= right) {
            return result[node];
        }

        int middle = (start + end) / 2;

        int L = findMinimum(result, arr, start, middle, left, right, node * 2);
        int R = findMinimum(result, arr, middle + 1, end, left, right, node * 2 + 1);

        if (L == miss) {
            return R;
        } else if (R == miss) {
            return L;
        } else {
            if (arr[L] <= arr[R]) {
                return L;
            } else {
                return R;
            }
        }
    }

    public static int init(int[] result, int[] arr, int start, int end, int node) {

        if (start == end) {
            result[node] = start;
            return result[node];
        }
        int middle = (start + end) / 2;

        int L = arr[init(result, arr, start, middle, node * 2)];
        int R = arr[init(result, arr, middle + 1, end, node * 2 + 1)];

        if (L <= R) {
            return result[node] = result[node * 2];
        } else {
            return result[node] = result[node * 2 + 1];
        }

    }

    public static void main(String agrs[]) throws Exception {

        try {
            FileReader fileReader = new FileReader("rmq.inp");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            FileWriter fileWriter = new FileWriter("rmq.out");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            StringBuilder sb = new StringBuilder();

            int n = Integer.parseInt(bufferedReader.readLine());

            int[] result = new int[(n * 4) + 1];
            int[] arr = new int[n];
            long sum = 0;

            StringTokenizer st = new StringTokenizer("");
            for (int i = 0; i < n; i++) {
                if (!st.hasMoreTokens()) {
                    st = new StringTokenizer(bufferedReader.readLine());
                }
                arr[i] = Integer.parseInt(st.nextToken());
            }

            bufferedReader.readLine();
            init(result, arr, 0, n - 1, 1);

            while (true) {
                String[] line = bufferedReader.readLine().split(" ");
                if (line[0].equals("c")) {
                    int idx = Integer.parseInt(line[1]);
                    int change = Integer.parseInt(line[2]);
                    arr[idx] = change;

                    changing(result, arr, 0, arr.length - 1, idx, change, 1);
                } else if (line[0].equals("q")) {
                    int min_value = 0;

                    min_value = findMinimum(result, arr, 0, arr.length - 1, Integer.parseInt(line[1]),
                            Integer.parseInt(line[2]),
                            1);

                    sum += min_value;
                } else if (line[0].equals("s")) {
                    break;
                }
            }

            sb.append(sum % 100000);
            bufferedWriter.write(sb.toString().trim());
            bufferedWriter.flush();

        } catch (Exception e) {

        }
    }
}
