package ru.roguelike.view;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import ru.roguelike.models.Position;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConsoleViewImpl implements ConsoleView {
    private List<List<Character>> field = new ArrayList<>();
    private List<String> info = new ArrayList<>();
    private Screen gameScreen;

    public ConsoleViewImpl() {
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        Terminal terminal;
        Screen screen;

        try {
            terminal = defaultTerminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            this.gameScreen = screen;

            screen.startScreen();
        } catch (IOException e) {
            //TODO: handle
            e.printStackTrace();
        }
    }
    @Override
    public void clear() {
        field.clear();
        info.clear();
    }


    @Override
    public void draw(List<List<Drawable>> figures, List<String> info) {
        figures.forEach(figuresRow -> field.add(mapRow(figuresRow)));
        this.info = info;

        drawInner();
    }

    @Override
    public Screen getScreen() {
        return gameScreen;
    }

    private void drawInner() {
        gameScreen.clear();
        for (int i = 0; i < field.size(); i++) {
            List<Character> row = field.get(i);

            for(int j = 0; j < row.size(); j++) {
                gameScreen.setCharacter(j, i, new TextCharacter(row.get(j)));
            }
        }

        try {
            gameScreen.refresh();
        } catch (IOException e) {
            //TODO:
            e.printStackTrace();
        }

        //info.forEach( line -> gameScreen.);
    }

    private List<Character> mapRow(List<Drawable> row) {
        List<Character> result = new ArrayList<>();

        row.forEach(drawable -> result.add(drawable.getDrawingFigure()));

        return result;
    }
}