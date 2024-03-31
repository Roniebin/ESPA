// import java.io.BufferedReader;
// import java.io.BufferedWriter;
// import java.io.FileReader;
// import java.io.FileWriter;
// import java.io.IOException;
// import java.util.ArrayList;

// public class trip {
// public static void main(String agrs[]) throws Exception {

// String line;
// int N, M;

// try {

// FileReader fileReader = new FileReader("./trip/trip.inp");
// BufferedReader bufferedReader = new BufferedReader(fileReader);

// FileWriter fileWriter = new FileWriter("./trip/trip.out");

// BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

// StringBuilder sb = new StringBuilder();
// while ((line = bufferedReader.readLine()) != "0") {

// int n = Integer.parseInt(line);

// if (n == 0) {
// break;
// }
// double[] people = new double[n];
// double avg = 0;
// double total = 0;
// for (int i = 0; i < n; i++) {
// people[i] = Double.parseDouble(bufferedReader.readLine());
// total += people[i];

// }
// total = Math.round(total * 100) / 100.0;

// avg = total / n;

// double A_expense = 0;
// double B_expense = 0;
// for (int i = 0; i < n; i++) {

// if (people[i] >= avg) {
// double temp = (int) ((people[i] - avg) * 100) / 100.0;
// A_expense += temp;
// } else {
// double temp = (int) ((avg - people[i]) * 100) / 100.0;
// B_expense += temp;
// }
// }

// double change = 0;

// change = Math.max(A_expense, B_expense);

// double temp1 = change * 100;
// long temp2 = Math.round(temp1);
// double v16 = temp2 / 100.0;

// sb.append("$" + String.format("%.2f", v16) + "\n");
// }
// String result = sb.toString();
// bufferedWriter.write(result.trim());
// bufferedWriter.flush();

// } catch (IOException e) {
// e.printStackTrace();
// }
// }
// }
