/*
 * Copyright (c) 2016. Ethan Hann
 */

import java.util.Scanner;

/**
 * Class where the main method is.
 */
public class TotalArea {

    /**
     * Main Method
     * @param args : a String array of command line arguments4
     */
    public static void main(String[] args){
        CalcArea universalArea = new CalcArea(); //creating an object to use the methods in the CalcArea class
        Scanner input = new Scanner(System.in);

        int sentinel = 1;
        int rectCounter = 0; //keep track of number of rectangles
        int triCounter = 0; //keep track of number of triangles
        int circCounter = 0; //keep track of number of circles
        double length;
        double width;
        double height;
        double base;
        double radius;

        Helper.cls(); // simulate a clear screen
        Helper.banner("Area Calculator v1.0 - Ethan Hann");
        System.out.println("<<ALL INPUTS CAN BE DOUBLES OR INTEGERS>>");

        while (sentinel != 0)
        {
            Helper.menuOptions(); //display menu of choices
            System.out.print(":> ");

            switch (input.nextInt()) //user selects a menu option
            {
                case 1: //calculate the area of a rectangle/square
                {
                    rectCounter++; //keep track of number of rectangles

                    System.out.print("Length :> ");
                    length = input.nextDouble();

                    System.out.print("Width :> ");
                    width = input.nextDouble();
                    System.out.println("");

                    Helper.shapeRect(length, width); // shows defined or scaled rectangle

                    System.out.println("");

                    //calls the CalcArea object's method, rectArea(); returns a double that is the area
                    //Places this area in a formatted banner, hence the call to banner()
                    Helper.banner("Area of the Rectangle", universalArea.rectArea(length, width));

                    //Displays the sum of the areas
                    System.out.printf("Sum of Areas: %.2f\n", universalArea.getSum());
                    System.out.println("");
                    break;
                }
                case 2: //calculate the area of a circle
                {
                    circCounter++; //keep track of number of circles

                    System.out.print("Radius :> ");
                    radius = input.nextDouble();
                    System.out.println("");

                    Helper.shapeCirc(radius); // shows defined or scaled circle

                    System.out.println("");

                    //calls the CalcArea object's method, circArea(); returns a double that is the area
                    //Places this area in a formatted banner, hence the call to banner()
                    Helper.banner("Area of the Circle", universalArea.circArea(radius));
                    System.out.printf("Sum of Areas: %.2f\n", universalArea.getSum());
                    System.out.println("");
                    break;
                }
                case 3: //calculate the area of a triangle
                {
                    triCounter++; //keep track of number of triangles

                    System.out.print("Base :> ");
                    base = input.nextDouble();

                    System.out.print("Height :> ");
                    height = input.nextDouble();
                    System.out.println("");

                    Helper.shapeTri(base); // shows defined or scaled triangle

                    System.out.println("");

                    //calls the CalcArea object's method, triArea(); returns a double that is the area
                    //Places this area in a formatted banner, hence the call to banner()
                    Helper.banner("Area of the Triangle", universalArea.triArea(base, height));
                    System.out.printf("Sum of Areas: %.2f\n", universalArea.getSum());
                    System.out.println("");
                    break;
                }
                case 4: //quit the program
                {
                    Helper.cls();
                    //Shows total of all areas in a formatted banner
                    Helper.banner("Total Sum of All Areas", "# of Rectangles: ", "# of Circles: ", "# of Triangles: ",
                            universalArea.getSum(), rectCounter, circCounter, triCounter);
                    sentinel = 0; //exits the while loop
                    break; //exits the switch statement
                }
                default:
                {
                    Helper.cls();
                    System.out.println("You must select one of the menu options."); //user selected an option unavailable
                }
            } //end of switch
        } //end of while
    } //end of main
} //end of class
