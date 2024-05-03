package inf112.skeleton.app.model.entities.rat;


import inf112.skeleton.app.view.GameResourceFactory;

public class FastRat extends Rat {
    public FastRat(GameResourceFactory resourceFactory) {
        super(70, 
        40, 
        resourceFactory.getTexture("rats/Spill_Rotte2.png"),
        300,
        2,
        resourceFactory.getTexture("rats/Spill_Rotte2_freezed.png"),
        35, 
        resourceFactory.getTexture("coin.png"));
    }
}
