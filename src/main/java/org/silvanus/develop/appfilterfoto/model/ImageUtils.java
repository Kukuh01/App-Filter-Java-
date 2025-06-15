package org.silvanus.develop.appfilterfoto.model;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;

public class ImageUtils {

    public static BufferedImage loadImage(File file) throws IOException{
        return ImageIO.read(file);
    }

    public static void saveImage(BufferedImage image, String format, File file) throws IOException {
        ImageIO.write(image, format, file);
    }

    public static Image toFXImage(BufferedImage image){
        return SwingFXUtils.toFXImage(image, null);
    }

    public static BufferedImage convolve(BufferedImage src, float[] kernelData, int kernelWidth) {
        Kernel kernel = new Kernel(kernelWidth, kernelWidth, kernelData);
        ConvolveOp op = new ConvolveOp(kernel);
        return op.filter(src, null);
    }

}
