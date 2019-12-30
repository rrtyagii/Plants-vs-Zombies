
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;

public class Pea {
    private double velocityX = 0.2;
    private double row;
    private double column;
    private Image plant;
    private ImageView plantView;
    private Player player;
    private AudioClip audioClip;
    private Group root = new Group();
    private StackPane stackPane = new StackPane();
    private double imageY;
    private double imageX;

    public Pea(int row, int column, Group root, Player player){
        this.player = player;
        this.row = row;
        this.column = column;
        this.plant = new Image("C:\\IIT\\New folder\\Plants vs Zombie game trial\\res\\pea.png");
        this.plantView = new ImageView(plant);
        stackPane.getChildren().add(plantView);
        this.root = root;
        root.getChildren().add(stackPane);

        stackPane.setTranslateY((int)(135+(row-1)*110+55)-40);
        stackPane.setTranslateX((int)(60+(column-1)*80+40)+40);

        this.audioClip = new AudioClip(getClass().getResource("C:\\IIT\\New folder\\Plants vs Zombie game trial\\res\\shoot.wav").toString());
        this.imageX = 60+ (column-1)*80+40+40;
        this.imageY= 135 + (row-1)*110+55-40;
    }

    public void setPosition(double row, double column){
        this.row = row;
        this.column = column;
    }

    public void removeImage(){
        this.stackPane.getChildren().remove(this.plantView);
        this.root.getChildren().remove(this.stackPane);
    }

    public int getRow(){
        return (int)this.row;
    }

    public int getColumn(){
        return (int)this.column;
    }

    public void setMovement(){
        double imageX = getImagePositionX()+velocityX;
        setImagePosition(imageX, imageY);
        stackPane.setTranslateY(this.imageY);
        stackPane.setTranslateX(this.imageX);
    }

    public void setImagePosition(double x, double y){
        this.imageY= y;
        this.imageX = x;
    }

    public double getImagePositionX(){
        return this.imageX;
    }

    public void makeSound(){
        this.audioClip.play();
    }
}
