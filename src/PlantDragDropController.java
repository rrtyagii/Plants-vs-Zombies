import javafx.scene.Group;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.StackPane;

public class PlantDragDropController {
    protected Group root;
    protected Player player;

    public PlantDragDropController(Group root, Player player){
        this.root = root;
        this.player = player;
    }


    public void handle(DragEvent event){
        int row =1;
        int column =(int) event.getX() - 40;
        String type  = event.getDragboard().getString();
        StackPane stackPane = new StackPane();

        if(event.getX()>=60 && event.getX()<=780){
            if (event.getY() >= 135 && event.getY() <= 685) {
                if (event.getY() >= 135 && event.getY() <= 245){
                    row=1;
                } else if (event.getY() < 355) {
                    row=2;
                } else if (event.getY() < 465) {
                    row=3;
                } else if ( event.getY() < 575){
                    row=4;
                } else if (event.getY() < 685) {
                    row=5;
                }

                if (event.getX() >= 60 && event.getX() <= 140){
                    column = 1;
                } else if (event.getX()<=211) {
                    column = 2;
                } else if (event.getX()<=300) {
                    column = 3;
                }else if (event.getX()<=380){
                    column = 4;
                } else if (event.getX()<=460) {
                    column = 5;
                } else if (event.getX()<=540) {
                    column = 6;
                } else if (event.getX()<=620) {
                    column = 7;
                } else if (event.getX()<=700) {
                    column = 8;
                }else if (event.getX()<=780) {
                    column = 9;
                }

                boolean add = true;

                for(Plant plant: player.getPlant()){
                    if(plant.getRow() == row && plant.getColumn() == column){
                        add = false;
                    }
                }

                if (add) {
                    switch(type){
                        case "peashooter":
                            if(this.player.getSun()>= 100){
                                PeaShooter peaShooter = new PeaShooter(row, column, root, player);
                                player.addPlants(peaShooter);
                                player.setSun(player.getSun() - peaShooter.getPrice());
                            }
                            break;
                        case "wallnut":
                            if(this.player.getSun()>=50){
                                WallNut wallNut = new WallNut(row, column, root);
                                player.addPlants(wallNut);
                                player.setSun(player.getSun() - wallNut.getPrice());
                            }
                            break;
                        case "sunflower":
                            if(this.player.getSun()>=50){
                                SunFlower sunFlower = new SunFlower(row, column, root, player);
                                player.addPlants(sunFlower);
                                player.setSun(player.getSun() - sunFlower.getPrice());
                            }
                            break;
                    }

                }
                }
        }
    }
}
