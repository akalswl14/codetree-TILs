import java.io.*;
import java.util.*;

public class Main {
    final static int[][] dirs = {{-1, 1}, {-1, -1}, {1, -1}, {1, 1}};
    static int N, r, c, m1, m2, m3, m4, rotateDir; // dir이 1이면 시계 방향.
    static int[][] grid;
    static int[] m = new int[4];

    static int x, y, v, nx, ny, nv; // now

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        grid = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        for(int i = 0; i < 4; i++){
            m[i] = Integer.parseInt(st.nextToken());
        }
        rotateDir = Integer.parseInt(st.nextToken());

        x = r-1; y = c-1; v = grid[x][y];
        if(rotateDir == 1) {
            clockwise();
        }else{
            couterClockwise();
        }
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                bw.write(grid[i][j]+" ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    private static void couterClockwise(){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < m[i]; j++){
                rotate(i);
            }
        }
    }

    private static void clockwise(){
        for(int i = 3; i >= 0; i--){
            for(int j = 0; j < m[i]; j++){
                rotate(i);
            }
        }
    }

    private static void repeat(int dir, int m){
        for(int i = 0; i < m; i++){
            rotate(dir);
        }
    }

    private static void rotate(int dir){
        nx = x + dirs[dir][0];
        ny = y + dirs[dir][1];
        nv = grid[nx][ny];
        grid[nx][ny] = v;
        
        x = nx; y = ny; v = nv;
    }
}