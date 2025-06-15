package org.silvanus.develop.appfilterfoto.model;

import java.awt.*;
import java.awt.image.BufferedImage;

public class VignetteFilter implements Filter{
    @Override
    public BufferedImage apply(BufferedImage image){
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage result = new BufferedImage(w, h, image.getType());

        double centerX = w / 2.0;
        double centerY = h / 2.0;
        double maxDistance = Math.sqrt(centerX * centerX + centerY * centerY);

        for(int y = 0; y < h; y++){
            for(int x = 0; x < w; x++){
                double dx = centerX - x;
                double dy = centerY - y;
                double distance = Math.sqrt(dx * dx + dy * dy);
                double ratio = 1.0 - distance / maxDistance;
                ratio = Math.max(0, ratio);

                int rgb = image.getRGB(x, y);
                int a = (rgb >> 24) & 0xFF;
                int r = (int)(((rgb >> 16) & 0xFF) * ratio);
                int g = (int)(((rgb >> 8) & 0xFF) * ratio);
                int b = (int)((rgb & 0xFF) * ratio);

                int newRGB = (a << 24) | (r << 16) | (g << 8) | b;
                result.setRGB(x, y, newRGB);
            }
        }
        return result;
    }
}
