package com.example.demo2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class Ekran extends Application{


public Button[][] buttons = new Button[3][3];
//
GameButton gameButton = new GameButton();
    public Stage stage;
    //создание игрового окна
        @Override
        public void start(Stage stage)  {
            this.stage = stage;
            Pane root = new Pane();
            GridPane gridPane = new GridPane();
            gridPane.setPrefSize(300, 300);

            // Создание кнопок и добавление кнопок-полей в игровую сетку
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    Button button = gameButton.gameButton();
                    buttons[i][j] = button;
                    gridPane.add(button, j, i);
                }
            }

            root.getChildren().add(gridPane);
            stage.setScene(new Scene(root, 300, 300));
            stage.setTitle("krestuki-noliki");
            stage.show();


        }



        public static void main(String[] args) {
            launch();

        }
    }

