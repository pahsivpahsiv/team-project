package app;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import com.github.weisj.jsvg.SVGDocument;
import com.github.weisj.jsvg.parser.SVGLoader;
import com.github.weisj.jsvg.view.ViewBox;

import java.net.URL;
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
    public static SVGDocument getCardImage(String collection, int index) {
        URL url = null;
        System.out.println(collection);
        if(collection.equals(DEFAULT_IMAGE_SUBDIRECTORY)) {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            try {
                url = classloader.getResource("images/" + index + ".svg");
            } catch (NullPointerException e) {
                System.out.println("Set component index was out of range or images directory does not exist");
                throw new RuntimeException(e);
            } catch (Exception e) {
                System.out.println("Unknown exception caught");
                throw new RuntimeException(e);
            }
        }
        else {
            FileHelper.getUploadDir();
            // TODO: fill in with custom image directory logic
        }
        if(url == null) {
            System.out.println("WARNING: you attempted to access " + collection + " but custom images " +
                    "are unsupported");
            return null;
        }
        try {
            SVGLoader loader = new SVGLoader();
            return loader.load(Objects.requireNonNull(url, "SVG file not found"));
        } catch (Exception e) { // TODO: fix this too
            System.out.println("Could not read image from " + collection + " index " + index);
            throw new RuntimeException(e);
        }
    }

    public static SVGDocument getCardImage(int index) {
        return getCardImage(DEFAULT_IMAGE_SUBDIRECTORY, index);
    }

}
