import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        planTrip("N", "Times Square", "6", "33rd");
    }
    /*
    * Checks if the current stop and destination stop is forward or backward
    * */
    public static boolean isForward(ArrayList<String> line, String currentIndex, String targetIndex){
        if(line.indexOf(targetIndex) > line.indexOf(currentIndex)){
            return true;
        }
        return false;
    }


    public static void planTrip(String startingLine, String startingStop, String destinationLine, String destinationStop){
        /* Array for record the stops and the change */
        ArrayList<String> stops = new ArrayList<>();
        HashMap<String, ArrayList<String>> lines = lines();

        if (startingLine.equals(destinationLine)){
            System.out.println("No need to change line, here's the trip plan");
            System.out.println("You must travel through the following stops on the "
                    + startingLine + " line: " +
                    moveALine(lines.get(startingLine), startingStop, destinationStop));
            return;
        }

        List<String> firstLine =  moveALine(lines.get(startingLine), startingStop);
        List<String> secondLine = moveALine(lines.get(destinationLine), "Union Square", destinationStop);
        System.out.println("You must travel through the following stops on the "
                + startingLine + " line: "
                + firstLine.stream().collect(Collectors.joining(", ")));
        System.out.println("Change at Union Square.");
        System.out.println("Your journey continues through the following stops: " +
                secondLine.stream().collect(Collectors.joining(", ")));
        System.out.println(firstLine.size() + secondLine.size() + " tops in total.");
    }

    // This move a line until intersectionPoint
    // If the line is backward the array got reverse
    public static List<String> moveALine(ArrayList<String> line, String startStop) {

        String intersectionPoint = "Union Square";

        if(!isForward(line, startStop, intersectionPoint)){
           Collections.reverse(line);
        }

        int endIndex = line.indexOf(intersectionPoint);
        int startIndex = line.indexOf(startStop);

        return line.subList(startIndex + 1, endIndex + 1);

    }

    public static List<String> moveALine(ArrayList<String> line, String startStop, String endStop) {


        if(!isForward(line, startStop, endStop)){
            Collections.reverse(line);
        }

        int endIndex = line.indexOf(endStop);
        int startIndex = line.indexOf(startStop);
        int length = (endIndex - startIndex + 1);

        return line.subList(startIndex + 1, endIndex + 1);

    }



    public static HashMap<String, ArrayList<String>> lines(){
        /* Make array for the lines  */
        ArrayList<String> lineN = new ArrayList<>(List.of("Times Square",
                "34th",
                "28th",
                "23rd",
                "Union Square",
                "8th"));

        ArrayList<String> lineL = new ArrayList<>(List.of("8th",
                "6th",
                "Union Square",
                "3rd",
                "1st"));

        ArrayList<String> line6 = new ArrayList<>(List.of("Grand Central",
                "33rd",
                "28th",
                "23rd",
                "Union Square",
                "Astor Place"));


        /* Make hashmap for saving Line Name with its stops */
        HashMap<String, ArrayList<String>> lines  = new HashMap<>();

        lines.put("N", lineN);
        lines.put("L", lineL);
        lines.put("6", line6);

        return lines;
    }
}
