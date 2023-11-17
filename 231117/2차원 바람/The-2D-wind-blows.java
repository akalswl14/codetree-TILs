import java.io.*;
import java.util.*;

class Cell{
    int v1, v2;
    public Cell(int v){
        this.v1 = v;
        this.v2 = v;
    }
}

public class Main {
    static final int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int N, M, Q, r1, c1, r2, c2;
    static int sum, num;
    static Cell[][] grid;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        grid = new Cell[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                grid[i][j] = new Cell(Integer.parseInt(st.nextToken()));
            }
        }

        for(int i = 0; i < Q; i++){
            st = new StringTokenizer(br.readLine());
            r1 = Integer.parseInt(st.nextToken())-1;
            c1 = Integer.parseInt(st.nextToken())-1;
            r2 = Integer.parseInt(st.nextToken())-1;
            c2 = Integer.parseInt(st.nextToken())-1;
            
            solve();
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                bw.write(grid[i][j].v1+" ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    private static void solve(){
        for(int i = r1; i <= r2; i++){
            if(i == r1 || i == r2){
                for(int j = c1; j <= c2; j++){
                    rotate(i, j);
                }
            }else{
                rotate(i, c1);
                rotate(i, c2);
            }
        }

        // average
        for(int i = r1; i <= r2; i++){
            for(int j = c1; j <= c2; j++){
                getAverage(i, j);
            }
        }
    }

    private static void rotate(int i, int j){
        if(i == r1){
            if(j == c2){ // down
                grid[i+1][j].v2 = grid[i][j].v1;
            }else{ // right
                grid[i][j+1].v2 = grid[i][j].v1;
            }
        }else if(i == r2){
            if(j == c1){ // up
                grid[i-1][j].v2 = grid[i][j].v1;
            }else{ // left
                grid[i][j-1].v2 = grid[i][j].v1;
            }
        }else{
            if(j == c1){ // up
                grid[i-1][j].v2 = grid[i][j].v1;
            }else{ // down
                grid[i+1][j].v2 = grid[i][j].v1;
            }
        }
    }

    private static void getAverage(int i, int j){
        sum = grid[i][j].v2;
        num = 1;
        int ni, nj;

        for(int[] dir: dirs){
            ni = i+dir[0];
            nj = j+dir[1];

            if(ni >= 0 && ni < N && nj >= 0 && nj < M){
                sum += grid[ni][nj].v2;
                num++;
            }
        }

        grid[i][j].v1 = sum / num;
    }
}