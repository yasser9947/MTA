# MTA 


## Objectives:

- Apply your knowledge of Java to solve a real world problem.
- Get really good at array manipulation.

### Activity

- Create a program that models a simple subway system.

- The program takes the line and stop that a user is getting on at and the line
  and stop that user is getting off at and prints the journey and the total number of stops for the trip in the console:

```java
planTrip("N", "Times Square", "6", "33rd"); // This is only a suggested function name and signature.

// shows output similar to this:
// "You must travel through the following stops on the N line: 34th, 28th, 23rd, Union Square."
// "Change at Union Square."
// "Your journey continues through the following stops: 23rd, 28th, 33rd."
// "7 stops in total."
```

- There are 3 subway lines:
  - The N line has the following stops: Times Square, 34th, 28th, 23rd, Union Square, and 8th
  - The L line has the following stops: 8th, 6th, Union Square, 3rd, and 1st
  - The 6 line has the following stops: Grand Central, 33rd, 28th, 23rd, Union Square, and Astor Place.
  - All 3 subway lines intersect at Union Square, but there are no other intersection points. (For example, this means the 28th stop on the N line is different than the 28th street stop on the 6 line, so you'll have to differentiate this when you name your stops in the arrays.)
- Tell the user the number of stops AND the stops IN ORDER that they will pass through or change at.


### solution:

```
import java.util.Map;

public class Metro {

    // Define the subway lines and their stops
    public static final Map<String, List<String>> MLines = new HashMap<>();

    static {
        MLines.put("N", Arrays.asList("Times Square", "34th", "28th N", "23rd N", "Union Square", "8th"));
        MLines.put("L", Arrays.asList("8th", "6th", "Union Square", "3rd", "1st"));
        MLines.put("6", Arrays.asList("Grand Central", "33rd", "28th 6", "23rd 6", "Union Square", "Astor Place"));
    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
        public static void main(String[] args) {

            Metro M=new Metro();
            Scanner scanner = new Scanner(System.in);


            System.out.println("Welcome to Riyadh metro");
            System.out.print("Enter your starting line N, L, 6: ");
            String startLine = scanner.nextLine().toUpperCase();
            System.out.print("Enter your starting stop: ");
            String startStop = scanner.nextLine();
            System.out.print("Enter your destination line N, L, 6: ");
            String endLine = scanner.nextLine().toUpperCase();
            System.out.print("Enter your destination stop: ");
            String endStop = scanner.nextLine();


            if (!M.MLines.containsKey(startLine) || !M.MLines.containsKey(endLine)) {
                System.out.println("Invalid line input. Please enter N, L, or 6.");
                return;
            }
            if (!M.MLines.get(startLine).contains(startStop) || !M.MLines.get(endLine).contains(endStop)) {
                System.out.println("Invalid stop input. Please enter a valid stop on the selected line.");
                return;
            }


            List<String> journey = new ArrayList<>();
            int totalStops = 0;

            if (startLine.equals(endLine)) {

                List<String> stops = M.MLines.get(startLine);
                int startIndex = stops.indexOf(startStop);
                int endIndex = stops.indexOf(endStop);
                int step = (startIndex < endIndex) ? 1 : -1;

                for (int i = startIndex; i != endIndex + step; i += step) {
                    journey.add(stops.get(i));
                }
                totalStops = Math.abs(endIndex - startIndex);
            } else {

                String transferStation = "Union Square";
                if (!M.MLines.get(startLine).contains(transferStation) || !M.MLines.get(endLine).contains(transferStation)) {
                    System.out.println("No valid transfer station found. Please check the input and try again.");
                    return;
                }

                // Journey from start to transfer station
                List<String> startLineStops = M.MLines.get(startLine);
                int startIndex = startLineStops.indexOf(startStop);
                int transferIndexStart = startLineStops.indexOf(transferStation);
                int stepStart = (startIndex < transferIndexStart) ? 1 : -1;

                for (int i = startIndex; i != transferIndexStart + stepStart; i += stepStart) {
                    journey.add(startLineStops.get(i));
                }
                totalStops += Math.abs(transferIndexStart - startIndex);


                List<String> endLineStops = M.MLines.get(endLine);
                int transferIndexEnd = endLineStops.indexOf(transferStation);
                int endIndex = endLineStops.indexOf(endStop);
                int stepEnd = (transferIndexEnd < endIndex) ? 1 : -1;

                for (int i = transferIndexEnd; i != endIndex + stepEnd; i += stepEnd) {
                    journey.add(endLineStops.get(i));
                }
                totalStops += Math.abs(endIndex - transferIndexEnd);
            }


            System.out.println("\nYour journey from " + startStop + " to " + endStop + " is:");
            System.out.println(String.join(" -> ", journey));
            System.out.println("Total number of stops: " + totalStops);
        }
    }

```
