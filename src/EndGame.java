import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.util.Optional;

public class EndGame extends Application {
    private String result="";
    public static Stage endStage = new Stage();
    private int difficulty = 5 ;

    public EndGame(String stir){
        this.result = stir;
    }

    public void start(Stage primaryStage) throws Exception{
        WebView webView = new WebView();
        VBox root = addContent(webView);
        Scene scene = new Scene(root, 900, 800);
        scene.getStylesheets().add(Welcome.class.getResource("static/welcome.css").toExternalForm());
        primaryStage.setTitle("End");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        endStage = primaryStage;
    }

    private VBox addContent (WebView webView){
        VBox box= new VBox();
        box.prefWidth(500);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(50);
        Text title = new Text(this.result);
        Button startGame = addStart(webView);
        Button helpButton = addHelp();
        Button difficultyButton = addDifficulty();
        title.setFont(Font.font("Verdana", 50));
        title.setId("fancytext");
        box.getChildren().addAll(title, startGame, difficultyButton, helpButton);
        return box;
    }

    private class StartButton extends Button{
        public StartButton(String textOnButton, WebView webView){
            setText(textOnButton);
            webView.getEngine().load(textOnButton);
        }
    }
    private Button addStart(WebView webView){
        Button startGame = new EndGame.StartButton("START A NEW GAME AND TRY TO WIN", webView);
        startGame.setOnAction(actionEvent -> {
            GameStage game= new GameStage(difficulty);
            try{
                game.start(game.getInitiateStage());
            }catch(Exception e){
                e.printStackTrace();
                System.err.println("Can not initiate game");
            }
            endStage.close();
        });
        return startGame;
    }

    private Button addHelp(){
        Button helpButton = new Button("HOW TO PLAY");
        helpButton.setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Plants vs. Zombies");
            alert.setHeaderText("How to Play");
            alert.setContentText("In this game you will fight against zombies by plant different plants on your plant.\n" +
                    "Each plant has their own special skill:\n" +
                    "-Peashooter: attacks zombies by continuously shoot out peas once you put it down\n" +
                    "-Sunflower: produce one star at intervals. You can click on the plant to collect the stars. Each star worth 50 value, which you would use to buy more plants.\n" +
                    "-Wallnut: put it on the ground to slow down zombie's movement.\n" +
                    "Good luck and have fun!");

            alert.showAndWait();
        });
        return helpButton;
    }

    private Button addDifficulty(){
        Button difficultyButtion = new Button("SET DIFFICULTY");
        difficultyButtion.setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            DialogPane dialogPane = alert.getDialogPane();
            alert.setTitle("SET DIFFICULTY");

            ButtonType buttonType1= new ButtonType("Easy");
            ButtonType buttonType2= new ButtonType("Medium");
            ButtonType buttonType3= new ButtonType("Hard");
            ButtonType buttonType4= new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonType1, buttonType2, buttonType3, buttonType4);

            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == buttonType1){
                difficulty = 3;
            }
            else if(result.get() == buttonType2){
                difficulty = 5;
            }
            else if(result.get() == buttonType3){
                difficulty = 7;
            }
            else{

            }
        });
        return difficultyButtion;
    }

    public Stage getEndStage(){
        return endStage;
    }

    public static void EndGame(String[] args){
        launch(args);
    }

}


