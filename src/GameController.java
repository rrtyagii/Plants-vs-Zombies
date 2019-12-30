import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.*;

public class GameController implements EventHandler<KeyEvent> {
    private final double FRAMES_PER_SECOND = 60.0;
    private int difficulty;
    private Player player;
    private Enemy enemy;
    private boolean playerWin = false;
    private boolean zombieWin = false;
    private Stage stage;
    private Timer timer;


    public GameController(int difficulty, Player player, Enemy enemy, Stage initialStage){
        this.difficulty= difficulty;
        this.player = player;
        this.enemy = enemy;
        this.stage = initialStage;
    }

    public void initialize(){
        this.startTimer();
    }

    private void startTimer(){
        this.timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable(){
                    public void run(){
                        updateAnimation();
                    }
                });
            }
        };
        long frameTimeInMilSec = 1L ;
        this.timer.schedule(timerTask, 0, frameTimeInMilSec);
    }

    public Player getPlayer(){
        return player;
    }

    private void updateAnimation(){
        ArrayList<Plant> listOfPlants = checkPlants();
        ArrayList<Zombie> listOfZombies = checkZombies();
        ArrayList<Pea> listOfPeas = checkPeas();
        runFight(listOfPlants, listOfZombies, listOfPeas);
        for(Pea pea: listOfPeas){
            pea.setMovement();
        }

        for(Plant plant: listOfPlants){
            plant.setMovement();
        }

        for(Zombie zombie: enemy.getZombies()){
            zombie.movement();
        }
    }

    private ArrayList<Plant> checkPlants(){
        ArrayList<Plant> listOfPlants = player.getPlant();
        return listOfPlants;
    }

    private ArrayList<Zombie> checkZombies(){
        ArrayList<Zombie> listOfZombies = enemy.getZombies();
        return listOfZombies;
    }
    private ArrayList<Pea> checkPeas(){
        ArrayList<Pea> listOfPeas = player.getPeas();
        return listOfPeas;
    }

    private void runFight(ArrayList<Plant> plants, ArrayList<Zombie> zombies, ArrayList<Pea> peas){
        boolean plantDie = false;
        ArrayList<Zombie> blockZombie = new ArrayList<Zombie>();
        for(Iterator<Zombie> iterator = zombies.iterator(); iterator.hasNext(); ){
            Zombie zombie = iterator.next();
            if(zombie.getImagePositionX()<60){
                this.zombieWin = true;
            }
            for(Iterator<Plant> plantIterator = plants.iterator(); plantIterator.hasNext();){
                Plant plant = plantIterator.next();
                int plantRow = plant.getRow();
                int plantColumn = plant.getColumn();
                int zombieRow = zombie.getRow();
                int zombieColumn = zombie.getCol();

                if(plant.getName().equals("peashooter")){
                    for(Iterator<Pea> peaIterator = peas.iterator(); peaIterator.hasNext(); ){
                        Pea pea = peaIterator.next();
                        int peaRow= pea.getRow();
                        int peaX =(int)Math.round(pea.getImagePositionX());


                        if(plantRow == peaRow && peaRow == zombieRow && peaX == zombie.getImagePositionX()) {
                            pea.removeImage();
                            int zombieHealth = (int)zombie.getHealth();
                            int plantPower = plant.getPower();
                            zombie.setHealth(zombieHealth - plantPower);
                            peaIterator.remove();
                            if(zombie.getHealth() <= 0){
                                zombie.removeImage();
                                peaIterator.remove();
                            }
                        }
                        if(pea.getImagePositionX() > 1000){
                            peaIterator.remove();
                        }
                    }
                }
                if(plantRow == zombieRow && plantColumn == zombieColumn){
                    zombie.setSpeed(0);
                    blockZombie.add(zombie);
                    int plantHealth = plant.getHealth();
                    int zombiePower = zombie.getPower();
                    plant.setHealth((plantHealth - zombiePower));
                    if(plant.getHealth() < 50){
                        if(plant.getHealth() <= 0){
                            plantDie = true;
                        }
                        zombie.setSpeed(zombie.getInitialSpeed());
                    }
                }
                if(plantDie){
                    plant.removeImage();
                    iterator.remove();
                }
                plantDie = false;

            }
        }

        if(enemy.getZombies().isEmpty()){
            this.playerWin = true;
        }
        checkIfEnd();
    }

    public void checkIfEnd(){
        if(this.playerWin){
            String result = "You win the Game! ";
            EndGame game = new EndGame(result);
            try{
                this.timer.cancel();
                game.start(game.getEndStage());
                this.stage.close();
            } catch (Exception e){

            }
        }
        if(this.zombieWin){
            System.out.println("here");
            String result = "You were eatern";
            EndGame game= new EndGame(result);
            try{
                this.timer.cancel();
                game.start(game.getEndStage());
                this.stage.close();
            }
            catch (Exception e){

            }
        }

    }

    @Override
    public void handle(KeyEvent keyEvent){

    }

}
