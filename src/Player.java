import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class Player {
    private int sun = 200;
    private ArrayList<Plant> listPlants = new ArrayList<Plant>();
    private ArrayList<Pea> listPeas = new ArrayList<Pea>();
    Group root = new Group();
    Label sunLabel;
    Label sunOriginal;

    public Player(Group root, Label priceLabel){
        this.sunOriginal=priceLabel;
        this.root=root;
    }

    public int getSun(){
        return this.sun;
    }

    public ArrayList<Plant> getPlant(){
        return listPlants;
    }

    public ArrayList<Pea> getPeas(){
        return listPeas;
    }

    public void setSun(int sun){
        this.sun=sun;
        this.root.getChildren().remove(this.sunLabel);
        this.root.getChildren().remove(this.sunOriginal);
        Label sunL= new Label(Integer.toString(getSun()));
        this.sunLabel = sunL;
        sunL.setFont(new Font(20));
        this.root.getChildren().add(sunLabel);
        sunL.setTranslateX(90);
        sunL.setTranslateY(110);
    }

    public void addPlants(Plant plant){
        listPlants.add(plant);
    }

    public void addPea(Pea pea){
        listPeas.add(pea);
    }
}
