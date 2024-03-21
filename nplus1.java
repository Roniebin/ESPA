// import java.io.BufferedReader;
// import java.io.BufferedWriter;
// import java.io.FileReader;
// import java.io.FileWriter;
// import java.io.IOException;
// import java.util.HashMap;

// public class nplus1 {

// public static void main(String[] args) throws Exception {

// String line;
// int N, M;

// StringBuilder sb = new StringBuilder();
// try {

// FileReader fileReader = new FileReader("3nplus1.inp");
// BufferedReader bufferedReader = new BufferedReader(fileReader);

// FileWriter fileWriter = new FileWriter("3nplus1.out");

// BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

// while ((line = bufferedReader.readLine()) != null) {

// String[] splittedStrings = line.split(" ");

// N = Integer.parseInt(splittedStrings[0]);
// M = Integer.parseInt(splittedStrings[1]);

// HashMap<Integer, Integer> cycleLength = new HashMap<Integer, Integer>();
// int max = 0;

// if (N < M) {
// for (int i = N; i <= M; i++) {
// int n = i;
// int count = 0;

// while (n != 1) {

// if (cycleLength.get(n) != null) {
// count += cycleLength.get(n);
// count -= 1;
// break;
// }
// if (n % 2 != 0) {
// n = (3 * n) + 1;
// } else {
// n = n / 2;
// }

// count += 1;
// }
// count += 1;
// cycleLength.put(i, count);
// if (max < cycleLength.get(i)) {
// max = cycleLength.get(i);

// }
// }
// } else {
// for (int i = N; i >= M; i--) {
// int n = i;
// int count = 0;

// while (n != 1) {

// if (cycleLength.get(n) != null) {
// count += cycleLength.get(n);
// count -= 1;
// break;
// }
// if (n % 2 != 0) {
// n = (3 * n) + 1;
// } else {
// n = n / 2;
// }

// count += 1;
// }
// count += 1;
// cycleLength.put(i, count);
// if (max < cycleLength.get(i)) {
// max = cycleLength.get(i);

// }
// }
// }

// String p = Integer.toString(max);

// sb.append(N + " " + M + " " + p + "\n");
// }

// String result = sb.toString();
// bufferedWriter.write(result.trim());
// bufferedWriter.flush();

// bufferedReader.close();

// } catch (IOException e) {
// e.printStackTrace();
// }

// }
// }
