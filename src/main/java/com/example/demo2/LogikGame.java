package com.example.demo2;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LogikGame {

    public char[][] board = new char[3][3];
    public boolean isPlayerXTurn = true;
    public Button[][] buttons = new Button[3][3];

    public Stage stage;
    public Button[][] getButtons(){
        return  buttons;
    }
    public boolean getIsPlayerXTurn(){
        return isPlayerXTurn;
    }
    //Обновление игры после действия игрока
    public void updateBoard(Button button, char player) {
        int row = GridPane.getRowIndex(button);
        int col = GridPane.getColumnIndex(button);
        board[row][col] = player;
    }


    //Условия чтобы выйграть чтобы выйграть
    public boolean checkGameOver(char player) {
        // Проверка по горизонтали и вертикали
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }
        System.out.println(board[0][0]+" "+ board[0][1]+ " "+ board[0][2]+" "+ board[1][0]+" "+board[1][1]+" "+board[1][2]+
                board[2][0]+" "+board[2][1]+" "+board[2][2]);
        // Проверка по диагоналям
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player)) {
            return true;
        }

        // Проверка на ничью
        boolean isBoardFull = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '\0') {
                    isBoardFull = false;
                    break;
                }
            }
        }
        if (isBoardFull) {
            showGameOverDialog("Ничья");
            return true;
        }

        return false;
    }
    //Диалоговое окно после игры с запросом у пользователя начать заново или выйти
    public void showGameOverDialog(String winner) {
        Stage dialog = new Stage();
        dialog.initOwner(stage);
        dialog.setTitle("Game Over");

        Label winnerLabel = new Label(winner + " победил!");
        Button playAgainButton = new Button("Заново");
        Button exitButton = new Button("Выйти");

        playAgainButton.setOnAction(event -> {
            dialog.close();
            resetGame();
        });

        exitButton.setOnAction(event -> {
            stage.close();
        });

        HBox buttonsBox = new HBox(10, playAgainButton, exitButton);
        buttonsBox.setAlignment(Pos.CENTER);

        VBox dialogRoot = new VBox(10, winnerLabel, buttonsBox);
        dialogRoot.setAlignment(Pos.CENTER);
        dialogRoot.setPrefSize(200, 100);


        dialog.setScene(new Scene(dialogRoot));

        dialog.showAndWait();
    }


    //рестарт игры
    public void resetGame() {
        isPlayerXTurn = true;
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setDisable(false);
            }
        }
    }
}
