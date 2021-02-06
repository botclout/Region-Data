import RegionView.Region;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

import javax.swing.*;

@ScriptManifest(version = 0.0, info = "", author = "Camaro", name = "Region Data", logo = "")
public class Main extends Script {

    private Region regionView;

    @Override
    public void onStart() {
        if (SwingUtilities.isEventDispatchThread()) {
            regionView = new Region(getBot());
            regionView.setVisible(true);
        } else {
            SwingUtilities.invokeLater(this::onStart);
        }
    }

    @Override
    public int onLoop() {
        SwingUtilities.invokeLater(() -> regionView.accept(getBot().getMethods()));
        return 500;
    }

    @Override
    public void onExit() {
        if (SwingUtilities.isEventDispatchThread()) {
            if (regionView != null) regionView.setVisible(false);
        } else {
            SwingUtilities.invokeLater(this::onExit);
        }
    }
}
