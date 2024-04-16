package crt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.StringTokenizer;

public class crt {
    // 역원 구해주는함수
    public static long Inverse(long a, long b) {
        long c = b;
        long y = 0, x = 1;

        if (b == 1)
            return 0;

        while (a > 1) {
            long q = a / b;
            long t = b;

            b = a % b;
            a = t;
            t = y;

            y = x - q * y;
            x = t;
        }

        if (x < 0)
            x += c;
        return x;
    }

    public static Long gcd(Long a, Long b) {

        while (b != 0) {
            Long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static void main(String agrs[]) throws Exception {
        try {
            FileReader fileReader = new FileReader("crt.inp");
            BufferedReader br = new BufferedReader(fileReader);
            FileWriter fileWriter = new FileWriter("crt.out");
            BufferedWriter bw = new BufferedWriter(fileWriter);

            int testcase = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < testcase; i++) {
                int t = Integer.parseInt(br.readLine());

                String line = br.readLine();
                StringTokenizer st = new StringTokenizer(line);

                Long r0 = Long.valueOf(st.nextToken());
                Long a0 = Long.valueOf(st.nextToken());

                int c = 0;
                for (int j = 1; j < t; j++) {
                    line = br.readLine();
                    st = new StringTokenizer(line);
                    Long r = Long.valueOf(st.nextToken());
                    Long a = Long.valueOf(st.nextToken());

                    if (r != 0) {
                        r = r - r0;

                        if (r < 0) {

                            r = ((r % a) + a) % a;

                        }
                        Long o_a0 = a0;
                        Long o_r_0 = r0;

                        Long y = gcd(a0, a);

                        a0 = a0 / y;
                        if (r % y == 0) {
                            r = r / y;
                        } else {
                            c = 1;
                        }
                        a = a / y;

                        Long inv = Inverse(a0, a);

                        r = r * inv;
                        if (r > a) {
                            r = r % a;
                        }

                        r0 = o_a0 * r + o_r_0;
                        a0 = a * o_a0;
                    } else {

                        Long o_r = r0;
                        Long rr = 0L;
                        Long o_a_0 = a0;

                        Long m = gcd(a0, a);

                        a0 = a0 / m;
                        a = a / m;

                        a0 = o_a_0 * a + rr;
                        r0 = o_r;
                    }

                }
                if (c == 1) {
                    sb.append(-1 + "\n");
                    c = 0;
                } else {
                    sb.append(r0 + "\n");
                }

            }

            bw.write(sb.toString().trim());
            bw.flush();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}