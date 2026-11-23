package app;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class ImageService {
    private static final String DEFAULT_IMAGE_SUBDIRECTORY = "default";

    // TODO: placeholder method, we will want to change how this works soon

    /**
     * Returns paths to images in the persistent data directories given a collection and index
     * @param collection The collection folder to check. "default" returns built-in images,
     *                   this is used by the default override of this method as well
     * @param index The index within the collection of set images
     * @return
     */
    public static BufferedImage getCardImage(String collection, int index) {
        InputStream is = null;
        if(collection.equals(DEFAULT_IMAGE_SUBDIRECTORY)) {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            try {
                is = classloader.getResourceAsStream("images/" + index);
            } catch (RuntimeException e) {
                System.out.println("Set component index was out of range or images directory does not exist");
                throw new RuntimeException(e);
            }
        }
        else {
            // TODO: fill in with custom image directory logic
        }
        if(is == null) {
            System.out.println("WARNING: you attempted to access " + collection + " but custom images " +
                    "are unsupported");
            return null;
        }
        try {
            return ImageIO.read(is);
        } catch (IOException e) {
            System.out.println("Could not read image from " + collection + " index " + index);
            throw new RuntimeException(e);
        }
    }

    public static BufferedImage getCardImage(int index) {
        return getCardImage(DEFAULT_IMAGE_SUBDIRECTORY, index);
    }

}
