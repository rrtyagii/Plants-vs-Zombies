import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;








public class SunFlower implements Plant {
    private int row;
    private int column;
    private int price=50;
    private int[] position = new int[2];
    private int health = 20_000;
    private int power = 0 ;
    private StackPane stackPane = new StackPane();
    private Group root = new Group();
    private Image plant, star;
    private ImageView plantView, starView;
    private double oSizeY, oSizeX;
    private double sizeX, sizeY;
    private boolean shrink = false, hasStar = false;
    private StackPane stackPanee = new StackPane();
    private Player player;
    private int shoot_count = 0;
    private String name = "";

    public SunFlower(int row, int column, Group root, Player player){
        this.star = new Image("C:\\IIT\\New folder\\Plants vs Zombie game trial\\res\\star.png");
        this.starView = new ImageView(star);
        this.stackPanee.getChildren().add(starView);
        this.player=player;
        this.row = row;
        this.column= column;
        this.plant = new Image("C:\\IIT\\New folder\\Plants vs Zombie game trial\\res\\sunflower.png");
        this.plantView = new ImageView(plant);
        stackPane.getChildren().add(plantView);
        this.root = root;
        root.getChildren().add(stackPane);
        stackPane.setTranslateX((135+(row - 1)*110+55)-40);
        stackPane.setTranslateY((60+(column - 1)*80+40)-40);
        this.oSizeX = 90;
        this.oSizeY = 90;
        this.sizeX = 90;
        this.sizeY = 90;
    }

    public void removeImage(){
        this.stackPane.getChildren().remove(this.plantView);
        this.root.getChildren().remove(this.stackPane);
        this.stackPanee.getChildren().remove(this.starView);
        this.root.getChildren().remove(this.stackPanee);
    }

    public void removeStar(){
        this.stackPanee.getChildren().remove(this.starView);
        this.root.getChildren().remove(this.stackPanee);
    }
    @Override
    public void setPosition(int row, int column) {
         this.row = row;
         this.column = column;
    }

    public int[] getPosition(){
        return position;
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
        return power;
    }

    @Override
    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public void setSize(double width, double height){
        this.plantView.setFitWidth(width);
        this.plantView.setFitHeight(height);
    }

    @Override
    public void setMovement() {
        if (this.sizeX > (1.1 * oSizeX) || this.sizeY > (1.1 * oSizeY)){
            this.shrink = true;
        } else if (this.sizeX < (0.8 * oSizeX) || this.sizeY < (0.8 * oSizeY)) {
            this.shrink = false;
        }

        if (shrink) {
            this.setSize(1 * this.sizeX - 0.1, this.sizeY - 0.1);

            this.sizeX = this.sizeX - 0.1;
            this.sizeY = this.sizeY - 0.1;
        } else {
            this.setSize(1 * this.sizeX + 0.1, this.sizeY + 0.1);
            this.sizeX = this.sizeX + 0.1;
            this.sizeY = this.sizeY + 0.1;
        }

        if (shoot_count == 5000){
            StackPane stackPanee = new StackPane();
            stackPanee.getChildren().add(this.starView);
            stackPanee.setTranslateY( (135+(row-1)*110+55)-40+1);
            stackPanee.setTranslateX( (60+(column-1)*80+40)-40-20);
            root.getChildren().add(stackPanee);
            this.stackPanee = stackPanee;
            shoot_count = 0;
            hasStar = true;
        } else {
            shoot_count ++;
        }
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
        return "sunflower";
    }

    public boolean isHasStar(){
        return hasStar;
    }

    public void setHasStar(boolean bool){
        hasStar = bool;
    }

    public void makeSound(){

    }
}
