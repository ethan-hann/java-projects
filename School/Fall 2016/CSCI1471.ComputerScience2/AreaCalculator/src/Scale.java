/*
 * Copyright (c) 2016. Ethan Hann
 */

/**
 * Class used to hold a shape's scaling attributes
 * i.e. Scale Factor, length, width, etc...
 */
public class Scale {

    private int scaleFactor = 0;
    private int radius = 0;
    private int length = 0;
    private int height = 0;

    public Scale(int sF, int radius) //circle and triangle
    {
        this.scaleFactor = sF;
        this.radius = radius; //also the same as 'base'
    }

    public Scale(int sF, int l, int h) //rectangle
    {
        this.scaleFactor = sF;
        this.length = l;
        this.height = h; //also the same as 'width'
    }

    public int getScaleFactor()
    {
        return scaleFactor;
    }

    public int getRadius()
    {
        return radius;
    }

    public int getLength()
    {
        return length;
    }

    public int getHeight()
    {
        return height;
    }
}
