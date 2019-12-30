import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class BucketZombie implements Zombie {
    private int row;
    private int column;
    private double health = 600.0;
    private int power = 10;
    private StackPane stackPane= new StackPane();
    private Group root = new Group();
    private Image bZombie;
    private ImageView bZombieView;
    private double imageX;
    private double imageY;
    private double speed= -0.008;
    private double initialSpeed = -0.008;

    public BucketZombie(int row, int column, Group root){
        this.row = row;
        this.column = column;
        this.bZombie = new Image("C:\\IIT\\New folder\\Plants vs Zombie game trial\\res\\bucketZombie.png");
        this.bZombieView = new ImageView(bZombie);
        stackPane.getChildren().add(bZombieView);
        root.getChildren().add(stackPane);

        //converting row and colum according to the position at the background
        stackPane.setTranslateY((int)(135 + (row - 1) * 110 +55) - 40 + 1);
        stackPane.setTranslateX((int)(60 + (column - 1) * 80 + 40 + 40) -40);
        this.imageX = 60+(column - 1)*80+40+40;
        this.imageY = 135 + (row - 1) * 110 +55-40;

    }

    @Override
    /**
     * remove the image of the zombie
     */
    public void removeImage(){
        this.stackPane.getChildren().remove(this.bZombieView);
        this.root.getChildren().remove(this.stackPane);
    }

    /**
     * setting the image position of the zombie. We need this since the zombie
     * is not moving based on column, but their position
     * @param x for the x abcissca
     * @param y for the y ordinate
     */
    @Override
    public void setImgPos(double x, double y){
        this.imageX = x;
        this.imageY = y;
    }

    public double getImagePositionX (){
        return this.imageX;
    }

    @Override
    public int getRow(){
        return this.row;
    }

    @Override
    public int getCol() {
        int zombieCol = 0;
        if(getImagePositionX() >= 60 && getImagePositionX()<= 140){
            zombieCol = 1;
        }
        else if(getImagePositionX() >= 141 && getImagePositionX() <= 211){
            zombieCol = 2;
        }
        else if(getImagePositionX() >= 212 && getImagePositionX() <= 300){
            zombieCol = 3;
        }
        else if(getImagePositionX() >= 301 && getImagePositionX() <= 380){
            zombieCol = 4;
        }
        else if(getImagePositionX() >= 381 && getImagePositionX() <= 460){
            zombieCol = 5;
        }
        else if(getImagePositionX() >= 461 && getImagePositionX() <= 540){
            zombieCol = 6;
        }
        else if(getImagePositionX() >= 541 && getImagePositionX() <= 620){
            zombieCol = 7;
        }
        else if(getImagePositionX() >= 621 && getImagePositionX() <= 700){
            zombieCol = 8;
        }
        else if(getImagePositionX() >= 701 && getImagePositionX() <= 780){
            zombieCol = 9;
        }
        return zombieCol;
    }

    @Override
    public double getHealth() {
        return health;
    }

    @Override
    public void setHealth(double health) {
        this.health=health;
    }

    @Override
    public int getPower() {
        return power;
    }

    @Override
    public double getInitialSpeed() {
        return initialSpeed;
    }

    @Override
    public void setSpeed(double speed) {
      this.speed=speed;
    }

    @Override
    public void setCol(int column){
        this.column=column;
    }

    @Override
    public void movement() {
    double imageX=getImagePositionX() + this.speed;
    setImgPos(imageX, imageY);
    stackPane.setTranslateY(this.imageY);
    stackPane.setTranslateX(imageX);
    }

}
