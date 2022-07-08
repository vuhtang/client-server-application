package gui;

import collection.WorkerColManager;
import collection.entity.Coordinates;
import collection.entity.Worker;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

/**
 * Panel for visualizing workers, which are drawn depending on the current user
 * and the characteristics of the worker.
 */
public class VisualizationPanel extends JPanel {
    private final Map<String, Color> ownersToColors = new HashMap<>();
    private final Color[] colors = new Color[]{Color.BLACK, Color.BLUE, Color.CYAN, Color.darkGray, Color.GREEN,
            Color.MAGENTA, Color.ORANGE};
    private int colorIndex;

    public VisualizationPanel() {
        super();
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);
    }

    /**
     * Draws the given worker. Worker image border color is determined by the current user.
     * If all available colors have already been used, they will start repeating.
     * When workers approach each other, their animation changes.
     *
     * @param worker worker to dram
     * @param mainFrame owner frame
     */
    public void drawWorker(Worker worker, MainFrame mainFrame) {
        WorkerColManager colManager = mainFrame.getColManager();
        Coordinates coordinates = worker.getCoordinates();
        int x = coordinates.getX().intValue();
        int y = (int) coordinates.getY();
        boolean isContact = false;
        for (Worker w : colManager.getWorkers()) {
            if (worker.equals(w)) continue;
            int x1 = w.getCoordinates().getX().intValue();
            int y1 = (int) w.getCoordinates().getY();
            if ((2 * x - 50) <= x1 * 2 && x1 * 2 <= (2 * x + 50) && y1 * 1.2 >= (1.2 * y - 50) && y1 * 1.2 <= (1.2 * y + 50)) {
                isContact = true;
                break;
            }
        }
        ImageIcon imageIcon;
        if (isContact) imageIcon = new ImageIcon("Client/src/main/resources/peepo2.gif");
        else imageIcon = new ImageIcon("Client/src/main/resources/peepo1.gif");
        imageIcon.setImageObserver(mainFrame);
        JLabel label = new JLabel(imageIcon);
        label.setBounds(340 + (x * 2), 140 + (int) (y * 1.2), 50, 50);
        if (!ownersToColors.containsKey(worker.getOwner())) {
            if (colorIndex == 6) colorIndex = 0;
            else colorIndex += 1;
            ownersToColors.put(worker.getOwner(), colors[colorIndex]);
        }
        Border border = BorderFactory.createLineBorder(ownersToColors.get(worker.getOwner()), 3);
        label.setBorder(border);
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                mainFrame.getMessageLabel().setText(worker.toString());
            }
        });
        add(label);
    }
}