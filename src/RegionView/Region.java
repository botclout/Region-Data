package RegionView;

import org.osbot.rs07.Bot;
import org.osbot.rs07.script.MethodProvider;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class Region extends JDialog implements Consumer<MethodProvider> {

    private final Point myPosition = new Point(0, 0);

    private final Clip[][] map = new Clip[104][104];

    private final int[][] currentClip = new int[104][104];

    public Region(Bot bot) {
        super(SwingUtilities.getWindowAncestor(bot.getCanvas()));
        setTitle("Region Data");
        setLayout(new GridLayout(104, 104));
        IntStream.range(0, 104).map(i -> 104 - i - 1).forEach(y -> IntStream.range(0, 104).forEach(x -> {
            map[x][y] = new Clip(x < 1 || x > 98 || y < 1 || y > 98);
            add(map[x][y]);
            currentClip[x][y] = 0;
        }));
        pack();
        setLocationRelativeTo(getOwner());
    }

    @Override
    public void accept(MethodProvider api) {
        setMyPosition(api.myPlayer().getLocalX(), api.myPlayer().getLocalY());
        loadMap(api.getMap().getClippingFlags());
        repaint();
    }

    private void setMyPosition(int x, int y) {
        if (myPosition.x == x && myPosition.y == y || x < 0 || y < 0 || x > 103 || y > 103) return;
        map[myPosition.x][myPosition.y].setMyPosition(false);
        map[x][y].setMyPosition(true);
        myPosition.setLocation(x, y);
    }

    private void loadMap(int[][] map) {
        if (Arrays.deepEquals(currentClip, map)) return;
        IntStream.range(0, 104).forEach(x -> IntStream.range(0, 104).forEach(y -> {
            this.map[x][y].setClip(map[x][y]);
            currentClip[x][y] = map[x][y];
        }));
    }
}
