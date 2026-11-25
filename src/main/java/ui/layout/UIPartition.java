package ui.layout;

import ui.SVGPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * A way of partitioning UI that allows one to define flexible zones by ID.
 */
public class UIPartition {
    public final Map<Integer, JPanel> map;
    private final int[][] partitionMap;

    public enum ImageType {
        Default,
        SVG
    }

    /**
     * A partitioner that takes an int[][] and produces a map of JPanels which can be consumed elsewhere.
     * @param partitionMap The IDs corresponding to each spatial position.
     * PRECONDITION: partitionMap does not contain more partitions than its highest key, TODO fix this
     */
    public UIPartition(JPanel parent, int[][] partitionMap, ImageType imageType) {
        this.partitionMap = partitionMap;
        this.map = buildMap(parent, Arrays
                .stream(partitionMap)
                .flatMapToInt(Arrays::stream)
                .max()
                .orElse(Integer.MIN_VALUE), imageType);
    }


    /**
    * Builds a map of the input ids to a grid of JPanels under a GridBagLayout
    * @param parent The parent to partition with a GridBagLayout
    * @param max The maximum number of entries that could populate this map; saves memory on HashMap init
    * @param imageType The image file type that is expected to be used.
    * @return A map of the partitioned panels.
    */
    private Map<Integer, JPanel> buildMap(JPanel parent, int max, ImageType imageType){
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        parent.setLayout(layout);

        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.BOTH;
        Map<Integer, JPanel> toReturn = new HashMap<>(max);
        for (int y = 0; y < partitionMap.length; y++) {
            for (int x = 0; x < partitionMap[y].length; x++) {
                if(toReturn.containsKey(partitionMap[y][x])) continue;

                int width = 1;
                while (x + width < partitionMap[y].length && partitionMap[y][x + width] == partitionMap[y][x]) {
                    width++;
                }

                int height = 1;
                while (y + height < partitionMap.length && partitionMap[y + height][x] == partitionMap[y][x]) {
                    height++;
                }

                constraints.gridx = x;
                constraints.gridy = y;
                constraints.gridwidth = width;
                constraints.gridheight = height;

                System.out.print("(" + constraints.gridx + ", " + constraints.gridy + " - " + constraints.gridwidth + ", " + constraints.gridheight + ") ");

                JPanel panel = null;
                if(imageType == ImageType.Default)
                    panel = new JPanel(null);
                if(imageType == ImageType.SVG)
                    panel = new SVGPanel(null);
                parent.add(panel, constraints);
                toReturn.put(partitionMap[y][x], panel);
            }
            System.out.println();
        }
        return toReturn;
    }
}
