import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;

public class PlantDragOverController implements EventHandler<DragEvent> {
    protected Group root;
    protected Player player;

    public PlantDragOverController(){
        this.root=null;
        this.player=null;
    }

    public PlantDragOverController(Group root, Player player) {
       this.player=player;
       this.root= root;
    }


    @Override
    public void handle(DragEvent dragEvent) {
        dragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        dragEvent.consume();
    }
}
