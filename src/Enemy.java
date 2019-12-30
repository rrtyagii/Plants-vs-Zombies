import javafx.scene.Group;

import java.util.ArrayList;

import java.util.Random;

public class Enemy {
    private int difficulty;
    private ArrayList<Zombie> zombies = new ArrayList<Zombie>();
    Group root = new Group();
    Random random = new Random();

    public Enemy(Group root, int difficulty){
        this.root = root;
        this.difficulty = difficulty;
        zombies = generateZombies(difficulty);
    }

    public ArrayList<Zombie> generateZombies(int difficulty){
        int norm = (int)Math.round(difficulty * 0.6);
        System.out.println(norm);
        int strong = difficulty - norm;
        for(int i=0; i<difficulty ; i++){
            for(int j=0; j<norm; j++){
                RegularZombie regularZombie = new RegularZombie
                        (random.nextInt(5) +1,
                                random.nextInt(9)+1, root);
                zombies.add(regularZombie);
            }
            for(int k=0; k<strong; k++){
                BucketZombie bucketZombie = new BucketZombie(
                        random.nextInt(5) +1,
                        random.nextInt(9)+1, root);
                zombies.add(bucketZombie);
            }
        }
       return zombies;
    }

    public ArrayList<Zombie> getZombies(){
        return zombies;
    }
}
