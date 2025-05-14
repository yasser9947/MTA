
private int stopsNum = 0;
private ArrayList<String> stops;
private final List<String> lineN = List.of("times square", "34th", "28th", "23rd", "union square", "8th");
private final List<String> lineL = List.of("8th", "6th", "union square", "3rd", "1st");
private final List<String> line6 = List.of("grand central", "33rd", "28th", "23rd", "union square", "astor place");
private List<String> depList;
private List<String> desList;
private String depLine;
private String desLine;
private String depStop;
private final List<String> allowedLine = List.of("N", "L", "6");
public void main(){
    String desStop;
    String ans;
    stops = new ArrayList<>();
    Scanner scan = new Scanner(System.in);
    do {
        System.out.println("Please select the line you are going from:");
        System.out.println("Line N, Stops(Times Square, 34th, 28th, 23rd, Union Square, and 8th)");
        System.out.println("Line L, Stops(8th, 6th, Union Square, 3rd, and 1st)");
        System.out.println("Line 6, Stops(Grand Central, 33rd, 28th, 23rd, Union Square, and Astor Place)");
        depLine = scan.nextLine().toUpperCase();
        while (!allowedLine.contains(depLine)) {
            System.out.println("Please only enter a line from the list: (N, L, or 6)");
            depLine = scan.nextLine().toUpperCase();
        }
        whichLine(depLine, true);

        System.out.println("Please select the stop you are going from:");
        System.out.println("Line N, Stops(Times Square, 34th, 28th, 23rd, Union Square, and 8th)");
        System.out.println("Line L, Stops(8th, 6th, Union Square, 3rd, and 1st)");
        System.out.println("Line 6, Stops(Grand Central, 33rd, 28th, 23rd, Union Square, and Astor Place)");
        depStop = scan.nextLine().toLowerCase();
        while(!depList.contains(depStop)) {
            System.out.println("Please only enter a stop from the (" + depLine + ") list");
            System.out.println(depList);
            depStop = scan.nextLine().toLowerCase();
        }

        System.out.println("Please select the line you are going to:");
        System.out.println("Line N, Stops(Times Square, 34th, 28th, 23rd, Union Square, and 8th)");
        System.out.println("Line L, Stops(8th, 6th, Union Square, 3rd, and 1st)");
        System.out.println("Line 6, Stops(Grand Central, 33rd, 28th, 23rd, Union Square, and Astor Place)");
        desLine = scan.nextLine().toUpperCase();
        while (!allowedLine.contains(desLine)) {
            System.out.println("Please only enter a line from the list: (N, L, or 6)");
            desLine = scan.nextLine().toUpperCase();
        }
        whichLine(desLine, false);
        System.out.println("Please select the stop you are going to:");
        System.out.println("Line N, Stops(Times Square, 34th, 28th, 23rd, Union Square, and 8th)");
        System.out.println("Line L, Stops(8th, 6th, Union Square, 3rd, and 1st)");
        System.out.println("Line 6, Stops(Grand Central, 33rd, 28th, 23rd, Union Square, and Astor Place)");
        desStop = scan.nextLine().toLowerCase();
        while(!desList.contains(desStop)) {
            System.out.println("Please only enter a stop from the (" + desLine + ") list");
            System.out.println(desList);
            desStop = scan.nextLine().toLowerCase();
        }
        planTrip(depList, depStop, desList, desStop);
        printPlan();
        System.out.println("Would you like to plan another trip (y/n)?");
        ans = scan.nextLine().toLowerCase();
    } while (!ans.equals("n"));
}

private void printPlan() {
    String result;
    if(depStop.equals("union square")){
        System.out.print("You must travel through the following stops on the (" + desLine + ") line: ");
    }else{
        System.out.print("You must travel through the following stops on the (" + depLine + ") line: ");
    }
    if(desLine.equals(depLine)){
        result = String.join(", ", stops);
        System.out.println(result);
    }else{
        result = stops.stream().takeWhile(stop -> !stop.equals("union square")).collect(Collectors.joining(", "));
        if(!result.isEmpty()) System.out.print(result + ", ");
        if(stops.contains("union square")){
            System.out.println("Union Square.");
            System.out.println("Change to line (" + desLine + ") at Union Square.");
            System.out.print("Your journey continues through the following stops: ");
            result = stops.stream().dropWhile(stop -> !stop.equals("union square")).skip(1).collect(Collectors.joining(", "));
            System.out.println(result + ".");
        }
    }
    if(stopsNum == 1){
        System.out.println(stopsNum + " stop in total.");
    }else{
        System.out.println(stopsNum + " stops in total.");
    }
    stops.clear();
    stopsNum = 0;
}

/**
 * Helper method to check what line the user has chosen as their departure or destination line.
 * @param s - A string noting the destination line (N,L, or 6)
 * @param isDep - A boolean confirming if this is line is the destination or departure line.
 */
private void whichLine(String s, boolean isDep) {
    if(isDep){
        if (s.equals("N")){
            depList = lineN;
        } else if (s.equals("L")) {
            depList = lineL;
        }else{
            depList = line6;
        }
    }
    else{
        if (s.equals("N")){
            desList = lineN;
        } else if (s.equals("L")) {
            desList = lineL;
        }else{
            desList = line6;
        }
    }
}

private void planTrip(List<String> depLine, String depStop, List<String> desLine, String desStop) {
    int depIndex = depLine.indexOf(depStop);
    int desIndex = desLine.indexOf(desStop);
    if(depList.equals(desList)){
        if(depIndex < desIndex){
            leftToRightIterate(depIndex + 1, desIndex, true);
        }
        else if(depIndex > desIndex){
            rightToLeftIterate(depIndex - 1, desIndex, true);
        }
    }else{
        int depUS = depLine.indexOf("union square");
        int desUS = desLine.indexOf("union square");
        if(depIndex <= depUS){
            leftToRightIterate(depIndex + 1, depUS, true);
            if(desIndex >= desUS ){
                leftToRightIterate(desUS + 1, desIndex, false);
            } else {
                rightToLeftIterate(desUS - 1, desIndex, false);
            }
        } else {
            rightToLeftIterate(depIndex - 1, depUS, true);
            if(desIndex >= desUS){
                leftToRightIterate(desUS + 1, desIndex, false);
            } else {
                rightToLeftIterate(desUS - 1, desIndex, false);
            }
        }
    }
}


private void leftToRightIterate(int start, int end, boolean isDep){
    if(isDep){
        for (int i = start; i <= end; i++){
            stops.add(depList.get(i));
            stopsNum +=1;
        }
    }else{
        for (int i = start; i <= end; i++){
            stops.add(desList.get(i));
            stopsNum +=1;
        }
    }
}

private void rightToLeftIterate(int start, int end, boolean isDep){
    if(isDep){
        for (int i = start; i >= end; i--){
            stops.add(depList.get(i));
            stopsNum +=1;
        }
    }else{
        for (int i = start; i >= end; i--){
            stops.add(desList.get(i));
            stopsNum +=1;
        }
    }
}