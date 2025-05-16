import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String startLine, startStation, endLine, endStation;
        Subway subway = new Subway();
        boolean finish = true;

        do {
            System.out.println(
                    """
                            Line N: Times Square, 34th, 28th, 23rd, Union Square, 8th
                            Line L: 8th, 6th, Union Square, 3rd, 1st
                            Line 6: Grand Central, 33rd, 28th, 23rd, Union Square, Astor Place"""
            );
            System.out.println("Which line are you going from?");
            startLine = scan.nextLine();
            if (subway.checkLineValue(startLine)) {
                System.out.println("Which station are you departing from?");
                startStation = scan.nextLine();
                if (subway.checkStationValue(startLine, startStation.toLowerCase())) {
                    System.out.println("Which line are you going to?");
                    endLine = scan.nextLine();
                    if (subway.checkLineValue(endLine)) {
                        System.out.println("Which station are you going to?");
                        endStation = scan.nextLine();
                        if (subway.checkStationValue(endLine, endStation.toLowerCase())) {
                            subway.planTrip(startLine, startStation.toLowerCase(), endLine, endStation.toLowerCase());
                        } else {
                            System.out.println("Select the correct station \nor enter '?' to exit!\n");
                        }
                    } else {
                        System.out.println("Select the correct line \nor enter '?' to exit!\n");
                    }
                } else {
                    System.out.println("Select the correct station \nor enter '?' to exit!\n");
                }
            } else {
                if (startLine.equalsIgnoreCase("?")) {
                    finish = false;
                } else {
                    System.out.println("Select the correct line \nor enter '?' to exit!\n");
                }
            }
        } while (finish);
    }

}
