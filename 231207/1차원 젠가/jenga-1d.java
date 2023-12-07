import java.io.*;
import java.util.*;

class Cell {
    int id, value;
    boolean out;

    public Cell(int id, int value){
        this.id = id;
        this.value = value;
        this.out = false;
    }
}

public class Main {
    static int N, s, e;
    static Cell[] grid;

    static int x, idx, cnt;
    static Cell c;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        grid = new Cell[N];
        cnt = N;

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            grid[i] = new Cell(i, x);
        }

        for(int i = 0; i < 2; i++){
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken()) - 1;
            e = Integer.parseInt(st.nextToken()) - 1;
            convert();
            cnt -= (e-s+1);
        }

        bw.write(cnt+"\n");
        for(int i = 0; i < N; i++){
            c = grid[i];
            if(c.out) continue;
            bw.write(c.value + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void convert(){
        idx = 0;

        for(int i = 0; i < N; i++){
            c = grid[i];

            if(c.out) continue;

            if(idx >= s && idx <= e){
                c.out = true;
                grid[i] = c;
                if(idx == e) break;
            }
            
            idx++;
        }
    }
}