package org.silvanus.develop.appfilterfoto.model;

import java.awt.image.BufferedImage;

public interface Filter {
    BufferedImage apply(BufferedImage image);
}
