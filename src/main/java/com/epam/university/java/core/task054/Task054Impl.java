package com.epam.university.java.core.task054;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Task054Impl implements Task054 {
    /**
     * Convert an image to black-and-white.
     * Tip: red, green and blue values of pixel have to have the same value.
     *
     * @param inputFilePath  - image to apply filter.
     * @param outputFilePath - image after filter applying.
     */
    @Override
    public BufferedImage grayscaleFilter(String inputFilePath, String outputFilePath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(inputFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage outputImage = new BufferedImage(width, height, 1);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color = new Color(image.getRGB(x, y));

                int r = (int)(color.getRed() * 0.299);
                int g = (int)(color.getGreen() * 0.587);
                int b = (int)(color.getBlue() * 0.114);

                Color newColor = new Color(r + g + b, r + g + b,r + g + b);
                outputImage.setRGB(x, y, newColor.getRGB());
            }
        }

        try {
            File file = new File(outputFilePath);
            ImageIO.write(outputImage, "jpg", file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputImage;
    }

    /**
     * Implement Sepia filter that make an image old-like looking.
     * Tip: to implement Sepia filter you need to set pixels value by this formulas:
     * Red = 0.393 * originalRed + 0.769 * originalGreen + 0.189 * originalBlue.
     * Green = 0.349 * originalRed + 0.686 * originalGreen + 0.168 * originalBlue.
     * Blue = 0.272 * originalRed + 0.534 * originalGreen + 0.131 * originalBlue.
     *
     * @param inputFilePath  - image to apply filter.
     * @param outputFilePath - image after filter applying.
     */
    @Override
    public BufferedImage sepiaFilter(String inputFilePath, String outputFilePath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(inputFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage outputImage = new BufferedImage(width, height, 1);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color = new Color(image.getRGB(x, y));

                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();

                int r1 = ((int) (0.393 * r + 0.769 * g + 0.189 * b));
                int g1 = ((int) (0.349 * r + 0.686 * g + 0.168 * b));
                int b1 = ((int) (0.272 * r + 0.534 * g + 0.131 * b));

                r1 = Math.min(r1, 255);
                g1 = Math.min(g1, 255);
                b1 = Math.min(b1, 255);

                Color newColor = new Color(r1, g1,b1);
                outputImage.setRGB(x, y, newColor.getRGB());
            }
        }

        try {
            File f = new File(outputFilePath);
            ImageIO.write(outputImage, "jpg", f);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputImage;
    }

    /**
     * Reflect an image.
     *
     * @param inputFilePath  - image to apply filter.
     * @param outputFilePath - image after filter applying.
     */
    @Override
    public BufferedImage reflectFilter(String inputFilePath, String outputFilePath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(inputFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage outputImage = new BufferedImage(width, height, 1);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixel = image.getRGB(x, y);
                outputImage.setRGB(width - x - 1, y, pixel);
            }
        }

        try {
            File f = new File(outputFilePath);
            ImageIO.write(outputImage, "jpg", f);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputImage;
    }

    /**
     * Return original image from path.
     *
     * @param inputFilePath - image to return.
     * @return - original image.
     */
    @Override
    public BufferedImage originalImage(String inputFilePath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(inputFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    /**
     * Return value of red in pixel.
     *
     * @param pixel - integer that represents pixel from ColorModel.class.
     * @return - integer that represents value of red in pixel.
     */
    @Override
    public int getRed(int pixel) {
        return (pixel >> 16) & 0xff;
    }

    /**
     * Return value of green in pixel.
     *
     * @param pixel - integer that represents pixel from ColorModel.class.
     * @return - integer that represents value of green in pixel.
     */
    @Override
    public int getGreen(int pixel) {
        return (pixel >> 8) & 0xff;
    }

    /**
     * Return value of blue in pixel.
     *
     * @param pixel - integer that represents pixel from ColorModel.class.
     * @return - integer that represents value of blue in pixel.
     */
    @Override
    public int getBlue(int pixel) {
        return pixel & 0xff;
    }
}
