package org.silvanus.develop.appfilterfoto.model;

import java.awt.image.BufferedImage;

public class SepiaFilter implements Filter{
    @Override
    public BufferedImage apply(BufferedImage image){
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        for(int y = 0; y < image.getHeight(); y++){
            for(int x = 0; x < image.getWidth(); x++){
                int rgb = image.getRGB(x,y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                int tr = Math.min((int)(0.393*r + 0.769*g + 0.189*b), 255);
                int tg = Math.min((int)(0.349*r + 0.686*g + 0.168*b), 255);
                int tb = Math.min((int)(0.272*r + 0.534*g + 0.131*b), 255);

                int newRGB = (tr << 16) | (tg << 8) | tb;
                result.setRGB(x,y, (rgb & 0xFF000000) | newRGB);

            }
        }
        return result;
    }
}
