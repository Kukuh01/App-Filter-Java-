package org.silvanus.develop.appfilterfoto.model;

import java.awt.image.BufferedImage;

public class GrayscaleFilter implements Filter{
    @Override
    public BufferedImage apply(BufferedImage image) {
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        for (int y = 0; y < image.getHeight(); y++){
            for (int x = 0; x < image.getWidth(); x++){
                int rgb = image.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;
                int gray = (r + g + b)/3;
                int newRGB = (gray << 16) | (gray << 8) | gray;
                result.setRGB(x, y, (rgb & 0xFF000000) | newRGB);
            }
        }
        return result;
    }
}
