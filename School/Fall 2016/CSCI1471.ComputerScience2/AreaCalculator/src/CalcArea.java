/*
 * Copyright (c) 2016. Ethan Hann
 */

public class CalcArea {

    //sum is used to store the sum of all the areas in one user session
    private double sum;

    //area is used to store the area calculated
    private double area;

    //Constructor, setting sum and area = 0.0;
    public CalcArea()
    {
        sum = 0.0;
        area = 0.0;
    }
    public double rectArea(double length, double width)
    {
        area = Math.round(Math.abs(length * width) * 100.0) / 100.0;
        sum += area;
        return area; // Area of a rectangle/square: l*w
    }

    public double circArea(double radius)
    {
        area = Math.round(Math.abs(Math.PI * Math.pow(radius, 2)) * 100.0) / 100.0;
        sum += area;
        return area; // Area of a circle: pi*r^2
    }

    public double triArea(double base, double height)
    {
        area = Math.round(Math.abs((base * height) / 2.00) * 100.0) / 100.0;
        sum += area;
        return area; // Area of a triangle: (b*h)/2
    }

    public double getSum()
    {
        return sum;
    }
}
