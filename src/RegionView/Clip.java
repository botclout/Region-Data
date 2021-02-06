package RegionView;

import javax.swing.*;
import java.awt.*;

public class Clip extends JPanel {

    private final boolean outOfBounds;

    private boolean myPosition = false;

    private int clip = 0;

    {
        setPreferredSize(new Dimension(5, 5));
    }

    public Clip(boolean outOfBounds) {
        this.outOfBounds = outOfBounds;
    }

    public void setClip(int clip) {
        this.clip = clip;
    }

    public void setMyPosition(boolean value) {
        myPosition = value;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (myPosition) {
            g.setColor(Color.GREEN);
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        g.setColor(Color.LIGHT_GRAY);

        if (Collision.WALL_NORTH.test(clip) || Collision.WALL_BLOCK_NORTH.test(clip)) {
            g.drawLine(0, 0, getWidth(), 0);
        }

        if (Collision.WALL_EAST.test(clip) || Collision.WALL_BLOCK_EAST.test(clip)) {
            g.drawLine(getWidth(), 0, getWidth(), getHeight());
        }

        if (Collision.WALL_SOUTH.test(clip) || Collision.WALL_BLOCK_SOUTH.test(clip)) {
            g.drawLine(0, getHeight(), getWidth(), getHeight());
        }

        if (Collision.WALL_WEST.test(clip) || Collision.WALL_BLOCK_WEST.test(clip)) {
            g.drawLine(0, 0, 0, getHeight());
        }

        if (Collision.OBJECT_BLOCK.test(clip) || Collision.DECORATION_BLOCK.test(clip) ||
                Collision.MAP_BLOCK.test(clip)) {
            if (outOfBounds) g.setColor(Color.BLUE.darker().darker());
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}
