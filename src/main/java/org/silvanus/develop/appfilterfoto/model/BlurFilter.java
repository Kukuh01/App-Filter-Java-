package org.silvanus.develop.appfilterfoto.model;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BlurFilter implements Filter{
    @Override
    public BufferedImage apply(BufferedImage image){
        float[] kernel = {
                1f / 9, 1f / 9, 1f / 9,
                1f / 9, 1f / 9, 1f / 9,
                1f / 9, 1f / 9, 1f / 9
        };
        return ImageUtils.convolve(image, kernel, 3);
    }
}
