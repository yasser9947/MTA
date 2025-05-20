import java.util.*;

public class Main {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        Subway.SubwayStations(); //load stations

        System.out.print("Enter the Line you're getting on at (N, L, 6): ");
        String sourceLine = scanner.nextLine().toUpperCase();

        System.out.print("Enter the Stop you're getting on at: ");
        String sourceStop = scanner.nextLine().trim();

        System.out.print("Enter the Line you're getting off at (N, L, 6): ");
        String destinationLine = scanner.nextLine().toUpperCase();

        System.out.print("Enter the Stop you're getting off at: ");
        String destinationStop = scanner.nextLine().trim();
        System.out.println();

        Subway.triPlanner(sourceLine, sourceStop, destinationLine, destinationStop);

        scanner.close();
    }
}