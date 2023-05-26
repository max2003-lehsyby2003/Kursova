package com.example.demo2;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TicTacToeGame  {
    private boolean isPlayerXTurn = true;
    private char[][] board = new char[3][3];
    private Button[][] buttons = new Button[3][3];
    private Stage primaryStage;
    //Board board = new Board();

//    public static void main(String[] args) {
//        launch(args);
//    }


//    public void createButtons() {
////        this.primaryStage = primaryStage;
////        Pane root = new Pane();
//        GridPane gridPane = new GridPane();
//        gridPane.setPrefSize(300, 300);
//
//        // Создание кнопок и добавление их в сетку
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                Button button = createButton();
//                buttons[i][j] = button;
//                gridPane.add(button, j, i);
//            }
//        }
//
////        root.getChildren().add(gridPane);
////        primaryStage.setScene(new Scene(root, 300, 300));
////        primaryStage.setTitle("Tic-Tac-Toe");
////        primaryStage.show();
//    }
Board boards = new Board();
    private Button createButton() {
        Button button = new Button();
        button.setPrefSize(100, 100);
        button.setOnAction(event -> {
            if (((Button) event.getSource()).getText().isEmpty()) {
                if (isPlayerXTurn) {
                    ((Button) event.getSource()).setText("X");
                    ((Button) event.getSource()).setDisable(true);
                    isPlayerXTurn = false;
                    boards.updateBoard(((Button) event.getSource()), 'X');
                    if (checkGameOver('X')) {
                        showGameOverDialog("X");
                    }
                } else {
                    ((Button) event.getSource()).setText("O");
                    ((Button) event.getSource()).setDisable(true);
                    isPlayerXTurn = true;
                    boards.updateBoard(((Button) event.getSource()), 'O');
                    if (checkGameOver('O')) {
                        showGameOverDialog("O");
                    }
                }
            }
        });

        return button;
    }

//    private void updateBoard(Button button, char player) {
//        int row = GridPane.getRowIndex(button);
//        int col = GridPane.getColumnIndex(button);
//        board[row][col] = player;
//    }

    private boolean checkGameOver(char player) {
        // Проверка по горизонтали и вертикали
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }

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

    private void showGameOverDialog(String winner) {
        Stage dialog = new Stage();
        dialog.initOwner(primaryStage);
        dialog.setTitle("Game Over");

        Rectangle bg = new Rectangle(200, 100, Color.WHITE);
        bg.setStroke(Color.BLACK);
        bg.setStrokeWidth(2.0);

        Line line1 = new Line(0, 0, 200, 100);
        line1.setStroke(Color.RED);
        line1.setStrokeWidth(2.0);

        Line line2 = new Line(0, 100, 200, 0);
        line2.setStroke(Color.RED);
        line2.setStrokeWidth(2.0);

        Label winnerLabel = new Label(winner + " победил!");
        Button playAgainButton = new Button("Заново");
        Button exitButton = new Button("Выйти");

        playAgainButton.setOnAction(event -> {
            dialog.close();
            resetGame();
        });

        exitButton.setOnAction(event -> {
            primaryStage.close();
        });

        HBox buttonsBox = new HBox(10, playAgainButton, exitButton);
        buttonsBox.setAlignment(Pos.CENTER);

        VBox dialogRoot = new VBox(10, winnerLabel, buttonsBox);
        dialogRoot.setAlignment(Pos.CENTER);
        dialogRoot.setPrefSize(200, 100);

        dialogRoot.getChildren().addAll(bg, line1, line2);
        dialog.setScene(new Scene(dialogRoot));

        dialog.showAndWait();
    }

    private void resetGame() {
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