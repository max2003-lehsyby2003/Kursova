package com.example.demo2;

import javafx.scene.control.Button;

public class GameButton {



    LogikGame checkGameOver = new LogikGame();
    boolean isPlayerXTurn = checkGameOver.getIsPlayerXTurn();
    // Собирает и компонирует все функции игровой кнопки
    public Button gameButton() {
        Button button = new Button();
        button.setPrefSize(100, 100);
        button.setOnAction(event -> {
            if (((Button) event.getSource()).getText().isEmpty()) {
                if (isPlayerXTurn) {
                    ((Button) event.getSource()).setText("X");
                    ((Button) event.getSource()).setDisable(true);
                    isPlayerXTurn = false;
                    checkGameOver.updateBoard(((Button) event.getSource()), 'X');
                    if (checkGameOver.checkGameOver('X')) {
                        checkGameOver.showGameOverDialog("X");
                    }
                } else {
                    ((Button) event.getSource()).setText("O");
                    ((Button) event.getSource()).setDisable(true);
                    isPlayerXTurn = true;
                    checkGameOver.updateBoard(((Button) event.getSource()), 'O');
                    if (checkGameOver.checkGameOver('O')) {
                        checkGameOver.showGameOverDialog("O");
                    }
                }
            }
        });
return button;
}
}
