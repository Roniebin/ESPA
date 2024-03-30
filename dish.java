// import java.io.BufferedReader;
// import java.io.BufferedWriter;
// import java.io.FileReader;
// import java.io.FileWriter;

// public class dish {
// public static void main(String agrs[]) throws Exception {

// try {
// FileReader fileReader = new FileReader("dish.inp");
// BufferedReader bufferedReader = new BufferedReader(fileReader);
// FileWriter fileWriter = new FileWriter("dish.out");
// BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

// StringBuilder sd = new StringBuilder();

// int n = Integer.parseInt(bufferedReader.readLine());

// for (int i = 0; i < n; i++) {
// int result = 0;
// int N = Integer.parseInt(bufferedReader.readLine());
// String[] line = bufferedReader.readLine().split("");

// for (int j = 0; j < N; j++) {
// if (j == 0) {
// result += 10;
// } else {
// if (!line[j - 1].equals(line[j])) {
// result += 10;
// } else {
// result += 5;
// }
// }
// }
// sd.append(result + "\n");
// }
// bufferedWriter.write(sd.toString().trim());
// bufferedWriter.flush();

// } catch (Exception e) {

// }
// }
// }
