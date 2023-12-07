import java.io.*;
import java.util.*;

class Cell{
    int value;
    boolean out;

    public Cell(int value){
        this.value = value;
    }
}

public class Main {
    final static int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};

    static int N, r, c;
    static Cell[][] grid;
    static int[][] result;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        grid = new Cell[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                grid[i][j] = new Cell(Integer.parseInt(st.nextToken()));
            }
        }

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken()) - 1;
        c = Integer.parseInt(st.nextToken()) - 1;
        grid[r][c].out = true;
        if(grid[r][c].value > 1){
            explode();
        }

        result = new int[N][N];
        copyGrid();
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                bw.write(result[i][j] + " ");
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();

    }

    private static void explode(){
        int x, y;

        for(int i = 0; i < 4; i++){
            x = r; y = c;
            for(int j = 1; j < grid[r][c].value; j++){
                x += dirs[i][0];
                y += dirs[i][1];
                if(possible(x, y)) {
                    grid[x][y].out = true;
                }else{
                    break;
                }
            }
        }
    }

    private static int findNotExplode(int x, int y){
        for(int i = x-1; i >= 0; i--){
            if(!grid[i][y].out) return i;
        }
        return -1;
    }

    private static boolean possible(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    private static void copyGrid(){
        int x, y;

        for(int j = 0; j < N; j++){
            x = N - 1; y = j;
            for(int i = N - 1; i >= 0; i--){
                if(x < 0){
                    break;
                }

                if(grid[x][y].out){
                    x = findNotExplode(x, y);
                    if(x < 0) {
                        break;
                    }else{
                        result[i][j] = grid[x--][y].value;
                    }
                }else{
                    result[i][j] = grid[x--][y].value;
                }
            }
        }
    }
}