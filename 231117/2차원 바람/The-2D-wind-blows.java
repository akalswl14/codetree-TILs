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
            for(int j = c1; j <= c2; j++){
                if(i == r1 || i == r2){
                    rotate(i, j);
                } else{
                    if(j == c1 || j == c2){
                        rotate(i, j);
                    }else{
                        grid[i][j].v2 = grid[i][j].v1;
                    }
                }
            }
        }

        // for(int i = 0; i < N; i++){
        //     for(int j = 0; j < M; j++){
        //         System.out.print(grid[i][j].v2+" ");
        //     }
        //     System.out.println();
        // }
        // System.out.println();

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
                sum += inSquare(ni, nj) ? grid[ni][nj].v2 : grid[ni][nj].v1;
                num++;
            }
        }

        grid[i][j].v1 = sum / num;
    }

    private static boolean inSquare(int i, int j){
        return i>=r1 && i<=r2 && j>=c1 && j<=c2;
    }
}
// After #1 [2, 2, 4, 6]
// 4 5 2 5 6 6 0 
// 2 3 2 2 3 2 0 
// 5 3 3 4 3 3 0 
// 4 4 5 5 6. 4 0 

// After rotate
// 2 4 5 2 5 6 0 
// 5 3 2 2 6 2 0 
// 4 3 3 4 3 3 0 
// 4 5 5 6 3. 4 0

// After #2 [1, 1, 4, 5]
// 3 3 3 3 4 6 0 
// 3 3 3 3 3 2 0 
// 4 3 3 3 3 3 0 
// 4 4 4 4 4 4 0