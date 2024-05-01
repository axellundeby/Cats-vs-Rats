package inf112.skeleton.app.view.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.math.Circle;
import inf112.skeleton.app.controller.buttons.menu.MenuButtons;
import inf112.skeleton.app.controller.buttons.upgrade.UpgradeButtons;
import inf112.skeleton.app.model.SkadedyrModel;
import inf112.skeleton.app.model.catmenu.CatMenu;
import inf112.skeleton.app.model.entities.cat.Cat;
import inf112.skeleton.app.model.entities.rat.Rat;
import inf112.skeleton.app.view.GameResourceFactory;

public class PlayState extends State {
    private SkadedyrModel model;
    private Stage stage;
    private Stage upgradeStage;
    private Texture mapTexture;
    private MenuButtons menu;
    private UpgradeButtons upgradeButtons;
    private GameResourceFactory resourceFactory;
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;
    private float alpha = 0f;

    public PlayState(GameStateManager gsm, SkadedyrModel model, GameResourceFactory resourceFactory) {
        super(gsm);
        this.model = model;
        this.resourceFactory = resourceFactory;
        this.stage = resourceFactory.createStage();
        this.upgradeStage = resourceFactory.createStage();
        this.font = resourceFactory.createFont(Color.BLACK);
        this.mapTexture = resourceFactory.getTexture("map/Spill_Plattform.jpg");
        this.shapeRenderer = resourceFactory.createShapeRenderer();
        
        this.menu = new MenuButtons(model, resourceFactory);
        this.upgradeButtons = new UpgradeButtons(model);
        
        addMenuButtonsToStage();
        addUpgradeButtonsToStage();
    }

    private void addMenuButtonsToStage() {
        stage.addActor(menu.exitButton());
        stage.addActor(menu.pauseButton());
        stage.addActor(menu.speedButton());
        stage.addActor(menu.helpButton());
        stage.addActor(menu.restartButton());
    }

    public void addUpgradeButtonsToStage() {
        upgradeStage.addActor(upgradeButtons.upgradeDamageButton());
        upgradeStage.addActor(upgradeButtons.upgradeFireRateButton());
        upgradeStage.addActor(upgradeButtons.upgradeRangeButton());
    }

    public void updateMenuButtons() {
        menu.updateButtonAppearance();
    }

    public void updateUpgradeButtons() {
        upgradeButtons.updateButtonAppearance();
    }

    @Override
    public void render(SpriteBatch batch) {
        resourceFactory.clearScreen(Color.DARK_GRAY);
        
        batch.setColor(1, 1, 1, Math.min(alpha += 0.01, 1f));
        batch.begin();
        batch.draw(mapTexture, 0, 200, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() - 300);
        batch.end();

        resourceFactory.enableBlending();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Cat selectedCat = model.getSelectedCat();
        if (selectedCat != null) {
            Circle range = selectedCat.getRangeCircle();
            shapeRenderer.setColor(0.2f, 0.2f, 0.2f, 0.5f);
            shapeRenderer.circle(range.x, range.y, range.radius);
        }
        shapeRenderer.end();
        resourceFactory.disableBlending();

        drawCatMenu(batch);
        drawCats(batch);
        drawRats(batch);
        drawGameStatus(batch);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        if (selectedCat != null) {
            upgradeStage.act(Gdx.graphics.getDeltaTime());
            upgradeStage.draw();
        } else {
            Gdx.input.setInputProcessor(stage);
        }
    }

    private void drawCatMenu(SpriteBatch batch) {
        batch.begin();
        model.getBuyMenu().draw(batch, model.getMoney());
        batch.end();
    }

    private void drawCats(SpriteBatch batch) {
        batch.begin();
        for (Cat cat : model.getCats()) {
            cat.getSprite().draw(batch);
        }
        batch.end();
    }

    private void drawRats(SpriteBatch batch) {
        batch.begin();
        for (Rat rat : model.getRats()) {
            rat.getSprite().draw(batch);
        }
        batch.end();
    }

    private void drawGameStatus(SpriteBatch batch) {
        batch.begin();
        font.draw(batch, "Dine liv: " + model.getLives(), 1100, 800);
        font.draw(batch, "Dine penger: " + model.getMoney(), 925, 800);
        font.draw(batch, "Din Score: " + model.getPoints(), 800, 800);
        font.draw(batch, "Level: " + model.getLevel(), 700, 800);
        font.draw(batch, model.nextWaveText(), 500, 170);
        batch.end();
    }

    @Override
    public void dispose() {
        stage.dispose();
        upgradeStage.dispose();
        shapeRenderer.dispose();
        font.dispose();
    }
}
