import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class WallNut implements Plant {
    private int row, column;
    private int price = 50;
    private int[] position = new int[2];
    private int health = 100_000;
    private int power = 0;
    private StackPane stackPane = new StackPane();
    private Group root = new Group();
    private Image plant;
    private ImageView plantView;
    private double oSizeX ;
    private double oSizeY;
    private double sizeX;
    private double sizeY;
    private boolean shrink = false;
    private boolean isDie = false;

    public WallNut(int row, int column, Group root){
        this.row = row;
        this.column = column;
        this.plant = new Image("C:\\IIT\\New folder\\Plants vs Zombie game trial\\res\\wallnut.png");
        this.plantView = new ImageView(plant);
        this.root = root;
        root.getChildren().add(stackPane);
        stackPane.setTranslateX((60 + (column - 1)*80 + 40) - 40);
        stackPane.setTranslateY((135 + (row - 1)*110 + 55) - 40);
        this.oSizeX = 90;
        this.oSizeY = 90;
        this.sizeY = 90;
        this.sizeX = 90;
    }

    public int getRow(){
        return this.row;
    }

    public int getColumn(){
        return this.column;
    }

    public void removeImage(){
        this.stackPane.getChildren().remove(this.plantView);
        this.root.getChildren().remove(this.stackPane);
    }

    public void setPosition(int row, int column){
        this.row = row;
        this.column = column;
    }

    public void setSize(double width, double height){
        this.plantView.setFitHeight(height);
        this.plantView.setFitWidth(width);
    }

    public int[] getPosition(){
        return this.position;
    }

    public int getHealth(){
        return health;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public int getPower(){
        return power;
    }

    public void setPower(int power){
        this.power = power;
    }

    public int getPrice(){
        return price;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public void setMovement(){
        if(this.sizeX > (1.1 * oSizeX) || this.sizeY > (1.1 * oSizeY)){
            this.shrink = true;
        }
        else if(this.sizeX < (0.8 * oSizeX) || this.sizeY < (0.8 * oSizeY)){
            this.shrink = false;
        }

        if(shrink){
            this.setSize(1 * this.sizeX - 0.1, this.sizeY);

            this.sizeX = this.sizeX - 0.1;
        }
        else{
            this.setSize(1 * this.sizeX + 0.1, this.sizeY);

            this.sizeX = this.sizeX + 0.1;
        }
    }

    public void makeSound(){

    }

    public void removeStar(){

    }

    public String getName(){
        return "wallnut";
    }
}
