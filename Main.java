import java.util.*;

public class Main {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        Subway.SubwayStations(); //load stations

        String sourceLine, sourceStop, destinationLine, destinationStop;

        while (true){
            System.out.print("Enter the Line you're getting on at (N, L, 6): ");
            sourceLine = scanner.nextLine().toUpperCase();
            //check if lines names exist
            if(!Subway.subwayLines.containsKey(sourceLine)){
                System.out.println("One or both lines entered does not exist!");
                continue;
            }

            System.out.println("Available stops on line " + sourceLine + ":");
            System.out.println(String.join(", ", Subway.subwayLines.get(sourceLine)));
            System.out.print("Enter the Stop you're getting on at: ");
            sourceStop = scanner.nextLine().trim();

            System.out.print("Enter the Line you're getting off at (N, L, 6): ");
            destinationLine = scanner.nextLine().toUpperCase();
            if(!Subway.subwayLines.containsKey(destinationLine)){
                System.out.println("One or both lines entered does not exist!");
                continue;
            }

            System.out.println("Available stops on line " + sourceLine + ":");
            System.out.println(String.join(", ", Subway.subwayLines.get(destinationLine)));
            System.out.print("Enter the Stop you're getting off at: ");
            destinationStop = scanner.nextLine().trim();
        break;
        }

        System.out.println();
        Subway.triPlanner(sourceLine, sourceStop, destinationLine, destinationStop);

        scanner.close();
    }
}