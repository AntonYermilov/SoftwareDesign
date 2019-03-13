package ru.roguelike.logic;

import com.googlecode.lanterna.screen.Screen;
import ru.roguelike.models.objects.base.AbstractGameObject;
import ru.roguelike.view.Drawable;

import java.io.IOException;
import java.util.List;

/**
 * Represents a model (current state) of the game.
 */
public interface GameModel {
    boolean finished();

    /**
     * @return a figures to draw on board.
     */
    List<List<Drawable>> makeDrawable();

    /**
     * @return a general info about the game
     */
    List<String> getInfo();

    /**
     * @return a current log of the game
     */
    List<String> getLog();

    /**
     * @return returns a field
     */
    List<List<AbstractGameObject>> getField();

    /**
     * Takes an input from user and makes a corresponding action.
     *
     * @param screen is a screen from which a screen will be taken
     * @throws IOException if it occurs
     */
    void makeAction(Screen screen) throws IOException;

    boolean isShowHelpScreen();
    boolean isLoadMapFromFile();
    void setErrorWhileLoadingMap(boolean errorWhileLoadingMap);
    void setLoadMapFromFile(boolean loadMapFromFile);
}
