// import java.util.Scanner;

// public class main {
// public static void main(String[] args) {
// Scanner scanner = new Scanner(System.in);

// int studentCnt = scanner.nextInt();
// while (studentCnt != 0) {
// double result1 = 0.0;
// double result2 = 0.0;

// // calc average
// double[] money = new double[studentCnt];
// double average = 0.0;
// for (int i = 0; i < studentCnt; i++) {
// money[i] = scanner.nextDouble();
// average += money[i];
// }
// average /= studentCnt;

// for (int i = 0; i < studentCnt; i++) {
// double sub = ((int) ((money[i] - average) * 100)) / 100.0;
// System.out.println(sub);
// if (sub < 0) {
// result1 -= sub;
// } else {
// result2 += sub;
// }
// }

// System.out.println(result1);
// System.out.println(result2);
// System.out.printf("$%.2f\n", Math.max(result1, result2));
// studentCnt = scanner.nextInt();
// }

// scanner.close();
// }
// }