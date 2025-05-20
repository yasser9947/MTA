import java.util.*;

public class Subway{
    static Map<String, String[]> subwayLines = new HashMap<>();

    //storing the stations
    static void SubwayStations(){
        subwayLines.put("N", new String[]{"Times Square", "34th", "28th", "23rd", "Union Square", "8th"});
        subwayLines.put("L", new String[]{"8th", "6th", "Union Square", "3rd", "1st"});
        subwayLines.put("6", new String[]{"Grand Central", "33rd", "28th", "23rd", "Union Square", "Astor Place"});
    }

    static void triPlanner(String sourceLine, String sourceStop, String destinationLine, String destinationStop){
        ArrayList<String> route = new ArrayList<>();

        String[] sourceStation = subwayLines.get(sourceLine);
        String[] destinationStation = subwayLines.get(destinationLine);

        int startIndex = getIndex(sourceStation, sourceStop);
        int endIndex = getIndex(destinationStation, destinationStop);

        //the trip is on the same line
        if(sourceLine.equals(destinationLine)){
            if(startIndex < endIndex){
                for (int i = startIndex + 1; i <= endIndex; i++){
                    route.add(sourceStation[i]);
                }
            }
            else{
                for (int i = startIndex - 1; i >= endIndex; i--){
                    route.add(sourceStation[i]);
                }
            }
            System.out.println("You must travel through the following stops on the " + sourceLine + " line: " + String.join(", ", route) + ".");
            System.out.println(route.size() + " stops in total.");
        }
        //the trip transfers at union square
        else{
            int unionIndexSource = getIndex(sourceStation, "Union Square");
            int unionIndexDestination = getIndex(destinationStation, "Union Square");

            if(startIndex < unionIndexSource){
                for (int i = startIndex + 1; i<=unionIndexSource; i++){
                    route.add(sourceStation[i]);
                }
            }else{
                for (int i = startIndex - 1; i>=unionIndexSource; i--){
                    route.add(sourceStation[i]);
                }
            }
            System.out.println("You must travel through the following stops on the " + sourceLine + " line: " + String.join(", ", route) + ".");
            System.out.println(route.size() + " stops in total.");

            //clear the route to reuse it for the second part of the trip
            route.clear();

            if(unionIndexDestination < endIndex){
                for (int i = unionIndexDestination + 1; i<=endIndex; i++){
                    route.add(destinationStation[i]);
                }
            }else{
                for (int i = unionIndexDestination - 1; i>=endIndex; i--){
                    route.add(destinationStation[i]);
                }
            }
            System.out.println("Your trip continues through the following stops on the " + destinationLine + " line: " + String.join(", ", route) + ".");
            System.out.println((route.size() + Math.abs(startIndex - unionIndexSource)) + " stops in total.");//calculate the total stops
        }
    }

    //returns the index of the specified station, ignoring case and trimming extra spaces
    static int getIndex(String[] array, String value){
        for (int i=0; i < array.length; i++){
            if(array[i].equalsIgnoreCase(value.trim())){
                return i;
            }
        }
        return -1; //not found
    }
}