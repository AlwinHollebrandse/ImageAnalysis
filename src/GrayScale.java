import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;

public class GrayScale {

    /** * convert a BufferedImage to RGB colourspace */
    // from https://blog.idrsolutions.com/2009/10/converting-java-bufferedimage-between-colorspaces/
    public BufferedImage convertToGrayScale(BufferedImage originalImage) { // TODO is this allowed? pretty sure its not
        ProgressBar bar = new ProgressBar("Converting to GrayScale", 1);

        BufferedImage grayScaleImage = null;
        try {
            grayScaleImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
            ColorConvertOp xformOp = new ColorConvertOp(null);
            xformOp.filter(originalImage, grayScaleImage);
            bar.next();
        } catch (Exception e) {
            System.out.println("Exception " + e + " converting image");
        }
        return grayScaleImage;
    }

    public BufferedImage convertToSingleColor (BufferedImage originalImage, String color) {
        ProgressBar bar = new ProgressBar("Converting to GrayScale", originalImage.getWidth() * originalImage.getHeight());
        BufferedImage newImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_RGB);

        for(int x = 0; x < originalImage.getWidth(); x++) {
            for(int y = 0; y < originalImage.getHeight(); y++) {

                Color c = new Color(originalImage.getRGB(x, y));
                if ("gray".equalsIgnoreCase(color)) {
                    int gray = (int)(c.getRed() * 0.299) + (int)(c.getGreen() * 0.587) + (int)(c.getBlue() *0.114);
//                    int gray = c.getRed() + c.getGreen() + c.getBlue();
                    Color newColor = new Color(gray, gray, gray);
                    newImage.setRGB(x, y, newColor.getRGB());
                } else if ("red".equalsIgnoreCase(color)) {
                    Color newColor = new Color(c.getRed(), 0, 0);
                    newImage.setRGB(x, y, newColor.getRGB());
                } else if ("green".equalsIgnoreCase(color)) {
                    Color newColor = new Color(0, c.getGreen(), 0);
                    newImage.setRGB(x, y, newColor.getRGB());
                } else if ("blue".equalsIgnoreCase(color)) {
                    Color newColor = new Color(0, 0, c.getBlue());
                    newImage.setRGB(x, y, newColor.getRGB());
                } else {
                    throw new NullPointerException("something went wrong getting the colors");
                }
                bar.next();
            }
        }
        return newImage;
    }

    // OR this from: https://stackoverflow.com/questions/9131678/convert-a-rgb-image-to-grayscale-image-reducing-the-memory-in-java
//    ImageFilter filter = new GrayFilter(true, 50);
//    ImageProducer producer = new FilteredImageSource(colorImage.getSource(), filter);
//    Image mage = Toolkit.getDefaultToolkit().createImage(producer);
}