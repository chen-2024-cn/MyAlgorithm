package Backtracking;

import java.util.ArrayList;
import java.util.List;

public class QueenNProblem {

    List<List<String>> arrayList = new ArrayList<>();
    public List<List<String>> QueenN(int N) {
        //初始化棋盘，‘Q‘表示皇后，’.’表示空
        char[][] board = new char[N][N];
        for (int i = 0; i < N; i++) {
            for (int i1 = 0; i1 < N; i1++) {
                board[i][i1] = '.';
            }
        }
        backtrack(board, 0);
        return arrayList;
    }
 /*
 路径：board中小于row的那些行已经成功防止换后
 选择列表：第row行的所有列都是放置皇后的选择
 结束条件：row超过board的最后一行
  */
    public void backtrack(char[][] board, int row) {
        if (row == board.length) {
            arrayList.add(construct(board));
            return;
        }
        int n = board[row].length;
        for (int col = 0; col < n; col++) {
            if (!isValid(board, row, col)) {
                continue;
            }
            //做选择
            board[row][col] = 'Q';
            //进入下一决策树
            backtrack(board, row + 1);
            //撤销选择
            board[row][col] = '.';
        }
    }
        //将数组存到arrayList中
    private List<String> construct(char[][] board) {
        List<String> list = new ArrayList<>();
        for (char[] row : board) {
            list.add(new String(row));
        }
        return list;
    }

    public boolean isValid(char[][] board, int row, int col) {
        //检查列
        for (int i = 0; i <= row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        //检查左上
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        //检车右上
        for (int i = row - 1, j = col + 1; i >= 0 && j < board[row].length; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        QueenNProblem queenNProblem = new QueenNProblem();
        List<List<String>> solutions = queenNProblem.QueenN(5);
        for (List<String> solution : solutions) {
            for (String row : solution) {
                System.out.println(row);
            }
            System.out.println();
        }
    }
}
