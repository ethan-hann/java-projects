import java.util.Scanner;

public class CarTripV2
{
    /*General banner to format titles nicely
     ex. with the string, "Banner", the following would be outputted:

     **********
     * Banner *
     **********

    */
    private static void banner(String str)
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

    //prints a formatted banner
    private static void bannerf(String str, double miles)
    {
        String fMiles = Double.toString(miles);
        int l = str.length() + 4;
        int d = fMiles.length() + 4;
        for (int i = 0; i <= (l + d) - 1; i++) {
            System.out.print("*");
        }

        System.out.printf("\n* %s = %.2f *\n", str, miles);

        for (int i = 0; i <= (l + d) - 1; i++) {
            System.out.print("*");
        }
        System.out.print("\n");
    }


    public static void main(String[] args) {
        int gTank = 0; //Gallons of gas in users tank
        int mMiles = 0; //Maximum miles the user can drive
        double distance; //Distance for each leg
        double speed; //Speed for each leg
        double totalDistance; //Running total of the distance
        double time; //Time taken for each leg (in hours)
        double totalTime; //Running total of the time passed (in hours)
        int sentinel = 1; //sentinel for main trip loop
        String moreTrips; //stores users answer to More Trips?
        int legs; //keeps track of number of legs in trip
        double gallonsLeftAtEnd = 0;
        Scanner intInput = new Scanner(System.in); //used for integer input
        Scanner strInput = new Scanner(System.in); //used for string input
        Scanner douInput = new Scanner(System.in); //used for double input

        banner("Car Trip Helper v.2 - Ethan Hann"); //shows nice title banner
        while (sentinel != 0) //while the user is going on more trips
        {
            System.out.print("\nAre you going on a trip? (y = yes : n = no) :> ");
            moreTrips = strInput.nextLine();

            if (moreTrips.equals("y") || moreTrips.equals("Y")) //if the user is going on another trip
            {
                System.out.print("\nHow many legs are in your trip? :> ");
                legs = intInput.nextInt(); //get the number of legs in the trip
                totalTime = 0.0; //reset the total time
                totalDistance = 0.0; //reset the total distance
                System.out.print("How many gallons of gas do you have in your gas tank? (Integer value) :> ");
                gTank = intInput.nextInt();
                mMiles = gTank * 20; //maximum number of miles user can travel on one tank of gas
                bannerf("Maximum Travel Miles", mMiles); //formatted banner to show user the maximum # of miles
                // they can travel

                for (int i = 0; i < legs; i++)
                {
                    System.out.printf("\nDistance of Leg %d (Integer or Double) (miles)", i + 1);
                    System.out.print(":> ");
                    distance = douInput.nextDouble();

                    if (distance + totalDistance > mMiles) //if the user enters in a distance that would make the
                    // total distance more than the maximum
                    // # of miles
                    {
                        distance = mMiles - totalDistance; //ensure the user can go as far as possible
                        System.out.printf("You do not have enough gas to go the distance you specified. " +
                                "\nYou can only travel %.2f miles.\n", distance);

                        System.out.printf("\nSpeed of Leg %d (Integer or Double) (MPH)", i + 1);
                        System.out.print(":> ");
                        speed = intInput.nextDouble();

                        totalDistance += distance; //running total of the distance travelled
                        time = (distance / speed) * 60.00; //calculate and convert time to minutes (currently in hours)
                        totalTime += time; //running total of the time spent travelling

                        break; //exit loop because the user can not travel farther
                    }
                    else
                    {
                        System.out.printf("\nSpeed of Leg %d (Integer or Double) (MPH)", i + 1);
                        System.out.print(":> ");
                        speed = intInput.nextDouble();

                        totalDistance += distance;
                        time = (distance / speed) * 60.00;
                        totalTime += time;

                        System.out.println("");
                        bannerf("Miles Left to Travel", ((mMiles - totalDistance) / 1.00)); //display the amount of miles left that
                        // the user can travel
                        // dividing by 1.00 to force the answer
                        // to be rounded to 2 decimal places
                    }
                    if (!(totalDistance < mMiles)) //as long as the distance gone so far is less than the maximum
                    // distance, the user can continue travelling
                    {
                        System.out.println("Not have enough gas in tank to go any farther.");
                        break;
                    }
                } //end of for loop
                //System.out.print("You");
                System.out.printf("\n\t-> Traveled %.2f miles in %.2f minutes (%.2f hours).\n", totalDistance,
                        totalTime, totalTime / 60); //Display how far and long the user has travelled

                if (totalDistance == mMiles) //if the total distance is equal to the max miles the user can travel, then they have
                // run out of gas at the end of their trip and will need more gas
                {
                    System.out.println("\t-> Ran out of gas at the end of the trip.");
                    System.out.println("\t-> WILL need gas for the return trip. Sorry :(");
                }
                else if (totalDistance * 2 < mMiles) //if the total distance * 2 is less than the maximum distance
                // the user can travel, they will not need more gas.
                {
                    System.out.println("\t-> Will NOT need gas for the return trip. Yay :)");
                    gallonsLeftAtEnd = ((mMiles - totalDistance) / 1.00) / 20.00;
                    System.out.printf("\t-> Have approximately %.2f gallons left in the gas tank.\n", gallonsLeftAtEnd);
                }
                else //if the total distance * 2 is greater than the maximum distance
                // the user can travel, they will need more gas.
                {
                    System.out.println("\t-> WILL need gas for the return trip. Sorry :(");
                    gallonsLeftAtEnd = ((mMiles - totalDistance) / 1.00) / 20.00;
                    System.out.printf("\t-> Have approximately %.2f gallons left in the gas tank.", gallonsLeftAtEnd);
                }
                System.out.println(""); //print a new line at the end of the trip information
            }
            else if (moreTrips.equals("n") || moreTrips.equals("N"))
            {
                System.out.println("Goodbye!");
                sentinel = 0; //exit while loop
            }
            else
            {
                System.out.println("\nYou must enter either 'y' or 'n' or 'Y' or 'N'. Please try again.");
                sentinel = 1; //continue while loop
            }
        } //end of while loop
    }
}