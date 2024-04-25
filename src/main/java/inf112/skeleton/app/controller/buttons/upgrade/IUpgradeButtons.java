package inf112.skeleton.app.controller.buttons.upgrade;

import com.badlogic.gdx.scenes.scene2d.ui.Button;

public interface IUpgradeButtons {



    /**
     * Creates and returns a button for upgrading the damage of the selected cat.
     * @return the created button.
     */
    Button upgradeDamageButton();

    /**
     * Creates and returns a button for upgrading the fire rate of the selected cat.
     * @return the created button.
     */
    Button upgradeFireRateButton();

    /**
     * Creates and returns a button for upgrading the range of the selected cat.
     * @return the created button.
     */
    Button upgradeRangeButton();

    /**
     * Updates the appearance of the buttons.
     */
    void updateButtonAppearance();
    


} 

