package inf112.skeleton.app.model.entities.rat;

import inf112.skeleton.app.view.GameResourceFactory;

public class StrongRat extends Rat{

    public StrongRat(GameResourceFactory resourceFactory) {
        super(500, 
        20,
        resourceFactory.getTexture("rats/Spill_Rotte3.png"),
         400,
         3,
        resourceFactory.getTexture("rats/Spill_Rotte3_freezed.png"),
         60, 
        resourceFactory.getTexture("coin.png"));
    }
}
