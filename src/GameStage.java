import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class GameStage extends Application {
    private Group root;
    private int difficulty;
    static Stage initiateStage = new Stage();

    public GameStage(int difficulty){
        this.difficulty = difficulty;
    }

    public void start(Stage primaryStage) throws IOException{
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Platform.exit();
                System.exit(0);
            }
        });
        primaryStage.setTitle("Plants VS Zombies");
        this.root = new Group();
        Scene theScene = new Scene(root, 900, 800);

        Image background = new Image("C:\\IIT\\New folder\\Plants vs Zombie game trial\\res\\wallp.png");
        ImageView backgroundView = new ImageView();
        backgroundView.setImage(background);
        backgroundView.setTranslateY(100);
        root.getChildren().add(backgroundView);

        StackPane stackPane = new StackPane();
        Image peaShooter = new Image("C:\\IIT\\New folder\\Plants vs Zombie game trial\\res\\peashooter.png");
        ImageView peaShooterView = new ImageView(peaShooter);
        stackPane.getChildren().add(peaShooterView);
        stackPane.setMaxSize(90,160);
        stackPane.setTranslateX(-170);

        StackPane stakPane = new StackPane();
        Image sunflower = new Image("C:\\IIT\\New folder\\Plants vs Zombie game trial\\res\\sunflower.png");
        ImageView sunflowerView = new ImageView(sunflower);
        stakPane.getChildren().add(sunflowerView);
        stakPane.setMaxSize(90,160);
        stakPane.setTranslateX(-75);

        StackPane stackP = new StackPane();
        Image wallnut = new Image("C:\\IIT\\New folder\\Plants vs Zombie game trial\\res\\wallnut.png");
        ImageView wallnutView = new ImageView(wallnut);
        stackP.getChildren().add(wallnutView);
        stackP.setMaxSize(90,160);
        stackP.setTranslateX(20);

        Label money = new Label("100");
        money.setTranslateY(50);
        money.setFont(Font.font("Cambria" , 20));
        money.setTextFill(Color.WHITE);
        stackPane.getChildren().add(money);

        Label fifty = new Label("50");
        fifty.setTranslateY(50);
        fifty.setFont(Font.font("Cambria", 20));
        fifty.setTextFill(Color.WHITE);
        stakPane.getChildren().add(fifty);

        Label fiftyAnother = new Label("50");
        fiftyAnother.setTranslateY(50);
        fiftyAnother.setTextFill(Color.WHITE);
        fifty.setFont(Font.font("Cambria", 20));
        stackP.getChildren().add(fiftyAnother);

        Label colorHundrend = new Label("200");
        colorHundrend.setFont(Font.font("Cambria", 20));
        colorHundrend.setTranslateY(50);
        colorHundrend.setTextFill(Color.WHITE);

        Label sun1 = new Label(Integer.toString(200));
        sun1.setFont(new Font(20));
        this.root.getChildren().add(sun1);
        sun1.setTranslateY(110);
        sun1.setTranslateX(90);

        Image down = new Image("C:\\IIT\\New folder\\Plants vs Zombie game trial\\res\\bar.png");
        ImageView barView = new ImageView();
        barView.setImage(down);
        StackPane bar = new StackPane() ;
        BorderPane sun = new BorderPane();
        root.getChildren().add(bar);
        bar.getChildren().add(sun);
        bar.getChildren().add(barView);
        bar.getChildren().add(stackPane);
        bar.getChildren().add(stakPane);
        bar.getChildren().add(stackP);

        stackPane.setId("peashooter");
        stakPane.setId("sunflower");
        stackP.setId("wallnut");
        primaryStage.setScene(theScene);
        primaryStage.setResizable(false);
        primaryStage.show();

        Enemy enemy = new Enemy( root, difficulty);
        Player player = new Player(root, sun1);

        stackPane.setOnDragDetected(new PlantDragController(stackPane, peaShooter));
        stakPane.setOnDragDetected(new PlantDragController(stakPane,sunflower));
        stackP.setOnDragDetected(new PlantDragController(stackP, wallnut));
        theScene.setOnDragOver(new PlantDragOverController());
        theScene.setOnDragDropped(new PlantDragOverController(root,player));
        theScene.setOnMouseClicked(new SunController(player, root, sun1));
        initiateStage= primaryStage;
        GameController controller = new GameController(1, player, enemy, initiateStage);
        controller.initialize();
    }

    public Stage getInitiateStage(){
        return initiateStage;
    }

    public static void main(String[] args){
        launch(args);
    }
}
