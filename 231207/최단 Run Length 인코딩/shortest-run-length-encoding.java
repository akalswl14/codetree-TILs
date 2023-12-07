import java.io.*;
import java.util.*;

public class Main {
    static int N, min, sum, cnt, types; // sum : 총 개수, cnt : 현재 알파벳의 개수
    static char x, nx;
    static String A;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = st.nextToken();
        N = A.length();

        // 원본
        sum = 0; cnt = 0;
        init(0);
        for(int i = 0; i < N; i++){
            check(i);
        }
        sum += (parseCnt() + 1);
        min = sum;

        for(int i = 1; i < N; i++){
            sum = 0; cnt = 0;
            init(N - i);
            for(int j = N - i; j < N; j++){
                check(j);
            }
            for(int j = 0; j < N - i; j++){
                check(j);
            }
            sum += (parseCnt() + 1);
            min = Math.min(min, sum);
        }

        bw.write(min + "\n");
        bw.flush();
        bw.close();
    }

    private static void init(int idx){
        if(A.charAt(idx) == '0'){
            x = '1';
        }else{
            x = '0';
        }
    }

    private static void check(int j){
        nx = A.charAt(j);
        if(x != nx){
            if(cnt > 0){
                sum += (parseCnt() + 1); // 알파벳 개수 + 알파벳
            }
            cnt = 1;
            x = nx;
        }else{
            cnt++;
        }
    }

    private static int parseCnt(){ // 알파벳의 개수가 10개면 2로 바꿔준다.
        return cnt / 10 + 1;
    }
}