import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class PeaShooter implements Plant {
    private int price = 100;
    private int row;
    private int  column;
    private int health = 20_000;
    private int power = 100;
    private StackPane stackPane = new StackPane();
    private Group root = new Group();
    private Image plant;
    private ImageView plantView;
    private double oSizeX;
    private double oSizeY;
    private double sizeX;
    private double sizeY;
    private boolean shrink = false;
    private Player player;
    private int shoot_count = 900;

    public PeaShooter(int row, int column, Group root, Player player){
        this.player = player;
        this.row = row;
        this.column = column;
        this.plant = new Image("C:\\IIT\\New folder\\Plants vs Zombie game trial\\res\\peashooter.png");
        this.plantView = new ImageView(plant);
        stackPane.getChildren().add(plantView);
        this.root = root;
        root.getChildren().add(stackPane);
        stackPane.setTranslateY((135+(row-1)*110+55)-40);
        stackPane.setTranslateX((60+(column-1)*80+40)-40);
        this.oSizeX=90;
        this.oSizeY=90;
        this.sizeX=90;
        this.sizeY=90;
    }
    @Override
    public void setPosition(int row, int column) {
      this.row = row;
      this.column = column ;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int getPower() {
        return this.power;
    }

    @Override
    public void setPower(int power) {
       this.power = power;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public void setSize(double width, double height){
        this.plantView.setFitHeight(height);
        this.plantView.setFitWidth(width);
    }

    @Override
    public void setMovement() {
       if(this.sizeX > (1.1 * oSizeX) || this.sizeY > (1.1 * oSizeY)){
           this.shrink = true;
       }
       else if(this.sizeX < (0.8 * oSizeX) || this.sizeY < (0.8 * oSizeY)){
           this.shrink = false;
       }


       if(shoot_count == 1000){
           Pea pea = new Pea(this.row, this.column, this.root, this.player);
           this.player.addPea(pea);
           pea.makeSound();
           shoot_count=0;
       }
       else{
           shoot_count ++;
       }

       if(shrink){
           this.setSize(1 * this.sizeX, this.sizeY - 0.1);

           this.sizeY = this.sizeY - 0.1;
       }
       else{
           this.setSize(1 * this.sizeX, this.sizeY + 0.1);
           this.sizeY = this.sizeY + 0.1;
       }
    }

    @Override
    public void removeImage() {
     this.stackPane.getChildren().remove(this.plantView);
     this.root.getChildren().remove(this.stackPane);
    }

    @Override
    public int getRow() {
        return this.row;
    }

    @Override
    public int getColumn() {
        return this.column;
    }

    @Override
    public String getName() {
        return "peashooter";
    }
}
