package org.silvanus.develop.appfilterfoto.model;

import java.awt.image.BufferedImage;

public class BrightnessFilter implements Filter{
    private final int brightness;

    public BrightnessFilter(int brightness){
        this.brightness = brightness;
    }

    @Override
    public BufferedImage apply(BufferedImage image){
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        for(int y = 0; y < image.getHeight(); y++){
            for(int x = 0; x < image.getWidth(); x++){
                int rgb = image.getRGB(x, y);
                int a = (rgb >> 24) & 0xFF;
                int r = clamp(((rgb >> 16) & 0xFF) + brightness);
                int g = clamp(((rgb >> 8) & 0xFF) + brightness);
                int b = clamp((rgb & 0xFF) + brightness);
                int newRGB = (a << 24) | (r << 16) | (g << 8) | b;
                result.setRGB(x,y, newRGB);
            }
        }
        return result;
    }

    private int clamp(int val){
        return Math.max(0, Math.min(255, val));
    }
}
