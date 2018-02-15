/*
 * Copyright (c) 2016. Ethan Hann
 */

/**
 * Helper class contains methods used in the main TotalArea class.
 */

public class Helper{

    private static final int MAXSCALE = 20; //the maximum number of asterisks wide a shape can be (used for scaling)

    /**
     * Simulates a clear screen command.
     * Prints 150 empty lines and output begins at bottom of screen
     */
    public static void cls()
    {
        System.out.flush();
        for (int i = 0; i <= 150; i++)
        {
            System.out.println("");
        }
    }

    /**
     * Displays a nicely formatted banner in the form of:
     *  **********
     *  * Banner *
     *  **********
     * @param str : the String to put inside the banner
     */
    public static void banner(String str)
    {
        int l = str.length() + 4;
        for (int i = 0; i <= l - 1; i++) {
            System.out.print("*");
        }

        System.out.printf("\n* %s *\n", str);

        for (int i = 0; i <= l - 1; i++) {
            System.out.print("*");
        }
        System.out.print("\n");
    }

    /**
     * Displays a banner with all information at the end of the program
     *
     * @param str : the first string
     * @param str2 : the second string
     * @param str3 : the third string
     * @param str4 : the fourth string
     * @param area : the total Area
     * @param rCounter : the number of rectangles
     * @param cCounter : the number of circles
     * @param tCounter : the number of triangles
     */
    public static void banner(String str, String str2, String str3, String str4, double area, int rCounter, int cCounter, int tCounter)
    {
        String fArea = Double.toString(area);
        String strRCounter = Integer.toString(rCounter);
        String strCCounter = Integer.toString(cCounter);
        String strTCounter = Integer.toString(tCounter);

        int strLength = str.length() + 4;
        int str2Length = str2.length() + 4;
        int str3Length = str3.length() + 4;
        int str4Length = str4.length() + 4;

        int areaLength = fArea.length() + 3;
        int strRLength = strRCounter.length() + 3;
        int strCLength = strCCounter.length() + 3;
        int strTLength = strTCounter.length() + 3;

        int maximumNumLength;

        if (areaLength > strRLength && areaLength > strCLength && areaLength > strTLength)
        {
            maximumNumLength = areaLength;
        }
        else if (strRLength > strCLength && strRLength > strTLength)
        {
            maximumNumLength = strRLength;
        }
        else if (strCLength > strTLength)
        {
            maximumNumLength = strCLength;
        }
        else
        {
            maximumNumLength = strTLength;
        }

        int maximumStrLength;

        if (strLength > str2Length && strLength > str3Length && strLength > str4Length)
        {
            maximumStrLength = strLength;
        }
        else if (str2Length > str3Length && str2Length > str4Length)
        {
            maximumStrLength = str2Length;
        }
        else if (str3Length > str4Length)
        {
            maximumStrLength = str3Length;
        }
        else
        {
            maximumStrLength = str4Length;
        }

        for (int i = 0; i <= (maximumStrLength + maximumNumLength) - 1; i++) {
            System.out.print("*");
        }

        System.out.printf("\n* %s = %s *\n", str, fArea);

        System.out.printf("* %s %d\n", str2, rCounter);

        System.out.printf("* %s %d\n", str3, cCounter);

        System.out.printf("* %s %d\n", str4, tCounter);

        for (int i = 0; i <= (maximumStrLength + maximumNumLength) - 1; i++) {
            System.out.print("*");
        }
        System.out.print("\n");
    }

    /**
     * Displays a banner with a string and an area.
     *
     * @param str : the string to put into the banner
     * @param area : the area to display
     */
    public static void banner(String str, double area)
    {
        String fArea = Double.toString(area);

        int strLength = str.length() + 4;

        int areaLength = fArea.length() + 3;

        for (int i = 0; i <= (strLength + areaLength) - 1; i++) {
            System.out.print("*");
        }

        System.out.printf("\n* %s = %.2f *\n", str, area);

        for (int i = 0; i <= (strLength + areaLength) - 1; i++) {
            System.out.print("*");
        }
        System.out.print("\n");
    }

    /**
     * Shows the menu of options in which the user can select a shape to
     * calculate the area of.
     */
    public static void menuOptions()
    {
        System.out.println("<<SELECT AN OPTION BY ITS CORRESPONDING NUMBER>>\n");
        System.out.println("1.\tArea of a Square / Rectangle");
        System.out.println("2.\tArea of a Circle");
        System.out.println("3.\tArea of a Triangle");
        System.out.println("4.\tQuit");
        System.out.println("");
    }

    /**
     * Shows the rectangle using asterisks (*) that the user defined
     * Creates a scale object to scale the shape if it doesn't fit on the screen
     * @param length : the length of the rectangle
     * @param width : the width of the rectangle
     */
    public static void shapeRect(double length, double width)
    {
        length = Math.abs(length); //ensure only positive numbers are entered
        width = Math.abs(width);

        boolean isScaled; // is the rectangle scaled?
        Scale rectScale = rScaleFactor(length, width);
        isScaled = rectScale.getScaleFactor() != 1; //if the scale factor is not 1, the shape has been scaled
        if (isScaled)
        {
            System.out.println("<<Rectangle Has Been Scaled To Fit On Screen (Area Is Not Changed; Only Visual Representation)>>");
            System.out.printf("<<Scale Factor: %d>>\n", rectScale.getScaleFactor());
        }
        else
        {
            System.out.println("<<Rectangle As Defined By User>>");
        }

        //if-else statement to determine the orientation of the rectangle
        if (rectScale.getLength() > rectScale.getHeight())
        {
            for (int i = 1; i <= rectScale.getHeight(); i++) {
                for (int j = 1; j <= rectScale.getLength(); j++) {
                    System.out.print("* ");
                }
                System.out.println("");
            }
        }
        else
        {
            for (int i = 1; i <= rectScale.getLength(); i++) {
                for (int j = 1; j <= rectScale.getHeight(); j++) {
                    System.out.print("* ");
                }
                System.out.println("");
            }
        }
    }

    /**
     * Shows the circle using asterisks (*) that the user defined
     * The first while loop creates the top of the circle.
     * The second while loop creates the bottom of the circle.
     * Creates a scale object to scale the shape if it doesn't fit on the screen
     * @param radius : the radius of the circle
     */
    public static void shapeCirc(double radius)
    {
        radius = Math.abs(radius); //ensure only positive numbers are entered

        boolean isScaled; // is the circle scaled?
        Scale circleScale = cScaleFactor(radius);
        isScaled = circleScale.getScaleFactor() != 1; //if the scale factor is not 1, the shape has been scaled
        if (isScaled)
        {
            System.out.println("<<Circle Has Been Scaled To Fit on Screen (Area Is Not Changed; Only Visual Representation)>>");
            System.out.printf("<<Scale Factor: %d\n>>", circleScale.getScaleFactor());
        }
        else
        {
            System.out.println("<<Circle As Defined By User>>");
        }

        int radiusInt = circleScale.getRadius();
        int diameter = radiusInt * 2; // the diameter of the circle to draw
        int numberInRow = 2; //the number of asterisks in each row
        int numberOfSpaces = diameter + 2; // the amount of spaces preceding before asterisks is printed
        String asterisks = ""; //the asterisks string for each row of the circle
        String spaces = ""; //the string of spaces; generated from numberOfSpaces

        //FIRST HALF OF CIRCLE
        /*
         *  begin at the first row; if radius = 3:
         *
         *      * *     <-- numberInRow; where the first half the circle starts
         *    * * * *
         *  * * * * * *
         *  * * * * * *
         *    * * * *
         *      * *
         */
        while (numberInRow <= diameter) //while the middle of the circle hasn't been reached
        {
            for (int i = 0; i < numberOfSpaces; i++)
            {
                spaces += " ";
            }
            for (int i = 0; i < numberInRow; i++)
            {
                asterisks = asterisks + "* ";
            }
            numberInRow += 2;
            numberOfSpaces -= 2;

            System.out.printf(spaces + "%s", asterisks);
            System.out.println("");
            asterisks = "";
            spaces = "";
        }
        numberInRow = numberInRow - 2;

        //SECOND HALF OF CIRCLE
        /*
         *  begin at the row beneath the middle; if radius = 3:
         *
         *      * *
         *    * * * *
         *  * * * * * *
         *  * * * * * *
         *    * * * *   <-- numberInRow; where the second half the circle starts
         *      * *
         */
        while (numberInRow >= 2)
        {
            for (int i = 0; i < numberOfSpaces + 2; i++)
            {
                spaces += " ";
            }
            for (int i = 0; i < numberInRow; i++)
            {
                asterisks = asterisks + "* ";
            }
            numberInRow -= 2;
            numberOfSpaces += 2;

            System.out.printf(spaces + "%s", asterisks);
            System.out.println("");
            asterisks = "";
            spaces = "";
        }
    }

    /**
     * Shows the triangle using asterisks (*) that the user defined
     * Only uses the base of the triangle to make a perfect right triangle
     * Creates a scale object to scale the shape if it doesn't fit on the screen
     * @param base : the base of the triangle
     */
    public static void shapeTri(double base)
    {
        base = Math.abs(base); //can't have a negative base
        boolean isScaled;// is the triangle scaled
        Scale triScale = tScaleFactor(base); //since I only care about the base for the visual,
        // I use the scaleFactor() method with only one parameter

        isScaled = triScale.getScaleFactor() != 1; //if the scale factor is not 1, the shape has been scaled
        if(isScaled)
        {
            System.out.println("<<Triangle Has Been Scaled To Fit On Screen (Area Is Not Changed; Only Visual Representation)>>");
            System.out.printf("<<Scale Factor: %d>>\n", triScale.getScaleFactor());
        }
        else
        {
            System.out.println("<<Triangle As Defined By User>>");
        }

        int numberInRow = 2;
        System.out.println("*"); // print the tip of the triangle
        while (numberInRow <= triScale.getRadius())
        {
            for (int i = 1; i <= numberInRow; i++)
            {
                System.out.print("* ");
            }
            System.out.println("");
            numberInRow++;
        }
    }

    /**
     * Scales the shape (in this case, a rectangle) so that it fits on the screen
     * Using a scale object so that three values can be returned together: the scale factor and the new length and width
     * @param length : the length of the rectangle or base of the triangle
     * @param width : the width of the rectangle or height of the triangle
     * @return A Scale object containing the scaleFactor and the new length and width
     */
    private static Scale rScaleFactor(double length, double width)
    {
        // if statements to test if the length or width is less than 1.0; i.e. 0.5
        // if they are, they are set to 1 so that at least something gets printed to the screen
        if (length < 1.0)
        {
            length = 1.0;
        }
        if (width < 1.0)
        {
            width = 1.0;
        }
        int lengthInt = (int)length; //convert double to int (can't have a fraction of an asterisk)
        int widthInt = (int)width;
        int scaleFactor; //the scale factor at which to scale the shape

        if (lengthInt <= MAXSCALE && widthInt <= MAXSCALE) //if the shape is already valid, nothing is changed
        {
            scaleFactor = 1;
            return new Scale(scaleFactor, lengthInt, widthInt);
        }
        else //if it is not valid, the shape is scaled
        {
            int counter = 0;
            if (lengthInt > MAXSCALE)
            {
                while (lengthInt > MAXSCALE)
                {
                    lengthInt /= 2; //keep dividing length by 2 until it is valid
                    counter++; //increment counter
                }
            }
            if (widthInt > MAXSCALE && counter != 0) //if the length was scaled
            {
                while (widthInt > MAXSCALE)
                {
                    widthInt /= 2; //keep dividing width by 2 until it is valid
                }
            }
            else if (widthInt > MAXSCALE && counter == 0) //if the length was not scaled
            {
                while (widthInt > MAXSCALE)
                {
                    widthInt /= 2; //keep dividing width by 2 until it is valid
                    counter++; //increment counter
                }
            }
            scaleFactor = 2 * counter; // 2 * how many times the length was divided
            return new Scale(scaleFactor, lengthInt, widthInt); //return a Scale object that holds the
            // values for the scale factor, new length, and new width
        }
    }

    /**
     * Scales the shape (in this case, a circle) so that it fits on the screen
     * Using a scale object so that two values can be returned together: the scale factor and the new radius
     * @param radius : the radius of the circle
     * @return A Scale object containing the scaleFactor and the new radius
     */
    private static Scale cScaleFactor(double radius)
    {
        // if statement to test if the radius is less than 1.0; i.e. 0.5
        // if it is, it is set to 1 so that at least something gets printed to the screen
        if (radius < 1.0)
        {
            radius = 1.0;
        }
        int radiusInt = (int)radius; //convert double to int (can't have a fraction of an asterisk)
        int diameter = radiusInt * 2;
        int scaleFactor; //the scale factor at which to scale the shape
        if (diameter <= MAXSCALE) //if shape is already valid, nothing is changed
        {
            scaleFactor = 1;
            return new Scale(scaleFactor, radiusInt);
        }
        else //if it is not valid, the shape is scaled
        {
            int counter = 0;
            while (diameter > MAXSCALE) //while the diameter is greater than the MAXSCALE
            {
                diameter = diameter / 2; //keep dividing diameter by 2 until it is valid
                counter++; //increment counter
            }
            scaleFactor = 2 * counter; // 2 * how many times the length and width were divided
            radiusInt = diameter / 2; //the new radius
            return new Scale(scaleFactor, radiusInt); //return a Scale object that holds the
            // values for the scale factor and new radius
        }
    }

    /**
     * Scales the shape (in this case, a triangle) so that it fits on the screen
     * Using a scale object so that two values can be returned together: the scale factor and the new base
     * @param base : the radius of the circle
     * @return A Scale object containing the scaleFactor and the new base
     */
    private static Scale tScaleFactor(double base)
    {
        // if statement to test if the base is less than 1.0; i.e. 0.5
        // if it is, it is set to 1 so that at least something gets printed to the screen
        if (base < 1.0)
        {
            base = 1.0;
        }
        int baseInt = (int)base; //convert double to int (can't have a fraction of an asterisk)
        int scaleFactor; //the scale factor at which to scale the shape

        if (base <= MAXSCALE) //if shape is already valid, nothing is changed
        {
            scaleFactor = 1;
            return new Scale(scaleFactor, baseInt);
        }
        else //if it is not valid, the shape is scaled
        {
            int counter = 0;
            while (baseInt > MAXSCALE) //while the base is greater than the MAXSCALE
            {
                baseInt /= 2; //keep dividing baseInt by 2 until it is valid
                counter++; //increment counter
            }
            scaleFactor = 2 * counter; // 2 * how many times the base was divided
            return new Scale(scaleFactor, baseInt); //return a Scale object that holds the
            // values for the scale factor and new base
        }
    }
}