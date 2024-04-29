package inf112.skeleton.app.model.entities.rat;


import inf112.skeleton.app.view.GameResourceFactory;
public class BasicRat extends Rat {

    public BasicRat(GameResourceFactory resourceFactory) {
    super(
        70, 
        20, 
        resourceFactory.getTexture("rats/Spill_Rotte1.png"),
        200, 
        1, 
        resourceFactory.getTexture("rats/Spill_Rotte1_freezed.png"),
        40, 
        resourceFactory.getTexture("coin.png")
    );
    }   
}

