import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.util.Optional;

public class Welcome extends Application {
    static Stage welcomeStage;
    private int difficulty =5;



    public void start(Stage primaryStage) throws Exception{
        WebView webView = new WebView();
        VBox root = addContent(webView);
    }

    private VBox addContent(WebView webView){
        VBox box = new VBox();
        box.prefWidth(500);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(50);
        Text title = new Text("Plants vs. Zombies");
        Button startGameButton = addStartButton(webView);
        Button helpButton = addHelpButton();
        Button difficultyButton = addDifficultyButton();
        title.setFont(Font.font("Verdana", 50));
        title.setId("titleText");
        box.getChildren().addAll(title, startGameButton, difficultyButton, helpButton);
        return box;
    }

    private class StartButton extends Button{
        public StartButton(String textOnButton, WebView webView){
            setText(textOnButton);
            webView.getEngine().load(textOnButton);
        }
    }

    private Button addStartButton(WebView webView) {
      Button startGameButton = new StartButton("Start", webView);
      startGameButton.setOnAction(actionEvent ->{
           GameStage game = new GameStage(difficulty);
                try{
                    game.start(game.getInitiateStage());
                } catch(Exception e){
                    e.printStackTrace();
                    System.err.println("Can't initiate game");
                }
                welcomeStage.close();
            } );
           return startGameButton;
    }

    private Button addHelpButton() {
        Button helpButton = new Button("How to Play");
        helpButton.setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Plant vs. Zombies");
            alert.setHeaderText("How to Play");
            alert.setContentText("In this game you will fight against zombies by planting different plants in your garden.\n" +
                    "Each plant has its own special skill:\n" +
                    "-Peashooter: attacks zombies by continuously shoot out peas once you put it down\n" +
                    "-SunFlower: produce one star at intervals. You can click on the plant to collect the stars. Each star worth 50 value, which you would use to buy more plants.\n" +
                    "-WallNut: put it on the ground to slow down zombie's movement.\n" +
                    "Good luck and don't get eaten!");
            alert.showAndWait();
        });
      return helpButton;
    }

    private Button addDifficultyButton(){
        Button difficultyButtion = new Button("SET DIFFICULTY");
        difficultyButtion.setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("SET DIFFICULTY");
            alert.setHeaderText("SET DIFFICULTY");
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
   public static void main(String[] args){
        launch(args);
   }
}
