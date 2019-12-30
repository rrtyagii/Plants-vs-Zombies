import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

 public class RegularZombie implements Zombie {
     private int row, column;
     private double health=500.0;
     private int power=5;
     private StackPane stackPane=new StackPane();
     private Group root = new Group();
     private Image zombie;
     private ImageView zombieView;
     private double imageX, imageY;
     private double speed = -0.009;
     private double initialSpeed = -0.009;

     public RegularZombie(int row, int column, Group root){
         this.row = row;
         this.column = column;
         this.zombie = new Image("C:\\IIT\\New folder\\Plants vs Zombie game trial\\res\\normalZombie.png");
         this.zombieView = new ImageView(zombie);
         stackPane.getChildren().add(zombieView);
         root.getChildren().add(stackPane);

         //converting row-column to fit in the background
         stackPane.setTranslateY((int)(135 + (row - 1) * 110 +55) - 40 + 1);
         stackPane.setTranslateX((int)(60 + (column - 1) * 80 + 40 + 40) -40);

         this.imageX = 60 + (column - 1) * 80 + 40 + 40;
         this.imageY = 135 + (row - 1) * 110 + 55 - 40;
     }

     public double getImagePositionX(){

         return this.imageX;
     }


    @Override
    public void setImgPos(double x, double y) {
       this.imageX=x;
       this.imageY=y;
    }


    @Override
    public int getRow() {
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
          this.health = health;
    }

    @Override
    public int getPower() {
         return power;
    }

     /**
      * get the initial speed of the zombie.
      * We need this to set the speed of zombie back to the
      * initial speed if it stops and does other weird movements
      * @return the initial speed value of the zombie
      */
    @Override
    public double getInitialSpeed() {
         return initialSpeed;
    }

     /**
      * set the speed of the zombie
      * @param speed
      */
    @Override
    public void setSpeed(double speed) {
           this.speed=speed;
    }

    @Override
    public void setCol(int column) {
          this.column = column;
    }

     /**
      * sets the movement of the zombie
      */
    @Override
    public void movement() {
         double imageX=getImagePositionX()+this.speed;
         setImgPos(imageX, imageY);
         stackPane.setTranslateX(imageX);
         stackPane.setTranslateY(this.imageY);
    }

     @Override
     public void removeImage() {
       this.stackPane.getChildren().remove(this.zombieView);
       this.root.getChildren().remove(this.stackPane);
     }
}
