import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class rangeSum {

    // start : 시작인덱스 , end: 끝 인덱스
    // idx : 구간 합을 수정하고자 하는 노드
    // change: 수정할 값
    public static void changing(Long[] result, int start, int end, int idx, Long change, int Node) {
        // 범위 밖에 있는경우
        if (idx < start || end < idx) {
            return;
        } // 중간값 설정

        if (start == end) {
            result[Node] = change;
            return;
        }

        int mid = (start + end) / 2;

        changing(result, start, mid, idx, change, Node * 2);
        changing(result, mid + 1, end, idx, change, Node * 2 + 1);
        result[Node] = result[Node * 2] + result[Node * 2 + 1];

    }

    // start: 시작인덱스 ,end: 끝 인덱스
    // left, right: 구간 합을 구하고자 하는 범위
    public static Long findSum(Long[] result, int start, int end, int left, int right, int Node) {
        // 범위 밖에 있는 경우
        if (right < start || end < left) {
            return Long.valueOf(0);
        }
        // 범위 안에 있는 경우
        if (left <= start && end <= right) {
            return result[Node];
        }

        int mid = (start + end) / 2;
        // 그렇지 않다면 두 부분으로 나누어 합을 구하기
        return findSum(result, start, mid, left, right, Node * 2)
                + findSum(result, mid + 1, end, left, right, Node * 2 + 1);
    }

    // start: 시작인덱스 , end: 끝 인덱스
    public static Long init(Long[] result, String[] arr, int start, int end, int Node) {
        // 리프노트가 됬을때 그노드에 원래 배열의 값저장
        if (start == end) {
            result[Node] = Long.valueOf(arr[start]);
            return result[Node];
        }
        int middle = (start + end) / 2;
        // 재귀적으로 두 부분으로 나눈 뒤에 그 합을 자기 자신으로
        return result[Node] = init(result, arr, start, middle, Node * 2)
                + init(result, arr, middle + 1, end, Node * 2 + 1);

    }

    public static void main(String agrs[]) throws Exception {

        try {
            FileReader fileReader = new FileReader("./rangeSum/rangeSum.inp");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            FileWriter fileWriter = new FileWriter("./rangeSum/rangeSum.out");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            StringBuilder sb = new StringBuilder();

            int n = Integer.parseInt(bufferedReader.readLine());

            Long[] result = new Long[(n * 4) + 1];

            String[] arr = bufferedReader.readLine().split(" ");

            init(result, arr, 0, n - 1, 1);

            while (true) {
                String[] line = bufferedReader.readLine().split(" ");
                if (line[0].equals("c")) {
                    changing(result, 1, arr.length, Integer.parseInt(line[1]),
                            Long.valueOf(Integer.parseInt(line[2])),
                            1);
                } else if (line[0].equals("s")) {
                    sb.append(findSum(result, 1, arr.length, Integer.parseInt(line[1]),
                            Integer.parseInt(line[2]), 1)
                            + "\n");
                } else {
                    break;
                }
            }

            bufferedWriter.write(sb.toString().trim());
            bufferedWriter.flush();

        } catch (Exception e) {

        }
    }
}
