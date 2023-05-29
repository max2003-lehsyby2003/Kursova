package com.example.demo2;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Board {


    public char[][] board = new char[3][3];
    //обновляэт игрровую доску после каждого хода игрока
    public void updateBoard(Button button, char player) {
        int row = GridPane.getRowIndex(button);
        int col = GridPane.getColumnIndex(button);
        board[row][col] = player;
    }

}
