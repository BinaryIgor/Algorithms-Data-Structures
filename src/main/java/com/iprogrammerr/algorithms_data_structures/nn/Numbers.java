package com.iprogrammerr.algorithms_data_structures.nn;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.iprogrammerr.algorithms_data_structures.initialization.Initialization;
import com.iprogrammerr.algorithms_data_structures.initialization.StickyInitialization;

public final class Numbers implements Characters {

	private final Initialization<double[][]> characters;

	public Numbers() {
		this.characters = new StickyInitialization<>(() -> {
			try {
				double[] image = image("0.jpg");
				double[][] characters = new double[10][image.length];
				characters[0] = image;
				for (int i = 1; i < characters.length; i++) {
					characters[i] = image(i + ".jpg");
				}
				return characters;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	@Override
	public double[][] value() {
		return this.characters.value();
	}

	private double[] image(String name) throws Exception {
		try (InputStream is = new BufferedInputStream(Numbers.class.getResourceAsStream("/" + name))) {
			BufferedImage image = ImageIO.read(is);
			int height = image.getHeight();
			int width = image.getWidth();
			double[] pixels = new double[height * width];
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					pixels[(y * width) + x] = image.getRGB(x, y) == Color.WHITE.getRGB() ? 0 : 1;
				}
			}
			return pixels;
		}
	}

}
