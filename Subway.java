import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

public class Subway {


    List<String> lineN = List.of("times square", "34th", "28th", "23rd", "union square", "8th");
    List<String> lineL = List.of("8th", "6th", "Union Square", "3rd", "1st");
    List<String> line6 = List.of("grand central", "33rd", "28th", "23rd", "union square", "astor place");

    ArrayList<String> startPath;
    ArrayList<String> endPath;


    public void planTrip(String startLine, String startStation, String endLine, String endStation) {
        startPath = new ArrayList<>();
        endPath = new ArrayList<>();
        int index;
        if (startLine.equalsIgnoreCase("n")) {
            index = lineN.indexOf(startStation) + 1 == lineN.size() ? lineN.indexOf(startStation) - 1 : lineN.indexOf(startStation) + 1;
            saveStartPath(lineN, lineN.get(index));
        } else if (startLine.equalsIgnoreCase("l")) {
            index = lineL.indexOf(startStation) + 1 == lineL.size() ? lineL.indexOf(startStation) - 1 : lineL.indexOf(startStation) + 1;
            saveStartPath(lineL, lineL.get(index));
        } else if (startLine.equalsIgnoreCase("6")) {
            index = line6.indexOf(startStation) + 1 == line6.size() ? line6.indexOf(startStation) - 1 : line6.indexOf(startStation) + 1;
            saveStartPath(line6, line6.get(index));
        }
        if (endLine.equalsIgnoreCase("n")) {
            saveEndPath(lineN, endStation);
        } else if (endLine.equalsIgnoreCase("l")) {
            saveEndPath(lineL, endStation);
        } else if (endLine.equalsIgnoreCase("6")) {
            saveEndPath(line6, endStation);
        }
        Collections.reverse(endPath);
        System.out.printf("You must travel through the following stops on the %s line: %s \nChange at Union Square.\nYour journey continues through the following stops: %s \n%s stops in total.%n", startLine.toUpperCase(), startPath, endPath, startPath.size() + endPath.size());
    }


    public boolean checkLineValue(String line) {
        return line.equalsIgnoreCase("n") || line.equalsIgnoreCase("l") || line.equalsIgnoreCase("6");
    }

    public boolean checkStationValue(String line, String station) {
        if (line.equalsIgnoreCase("n")) {
            return lineN.contains(station);
        } else if (line.equalsIgnoreCase("l")) {
            return lineL.contains(station);
        } else if (line.equalsIgnoreCase("6")) {
            return line6.contains(station);
        }
        return false;
    }


    public void saveStartPath(List line, String station) {
        int indexOfStart = line.indexOf(station);
        if (indexOfStart == line.indexOf("union square")) {
            startPath.add(station);
        } else if (indexOfStart < line.indexOf("union square")) {
            startPath.add(station);
            saveStartPath(line, (String) line.get(indexOfStart + 1));
        } else if (indexOfStart > line.indexOf("union square")) {
            startPath.add(station);
            saveStartPath(line, (String) line.get(indexOfStart - 1));
        }
    }

    public void saveEndPath(List line, String station) {
        int indexOfStart = line.indexOf(station);
        if (indexOfStart == line.indexOf("union square")) {
            return;
        } else if (indexOfStart < line.indexOf("union square")) {
            endPath.add(station);
            saveEndPath(line, (String) line.get(indexOfStart + 1));
        } else if (indexOfStart > line.indexOf("union square")) {
            endPath.add(station);
            saveEndPath(line, (String) line.get(indexOfStart - 1));

        }

    }
}