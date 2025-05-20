
package train;

public class Train {

  
    public static void main(String[] args) {

        // اخزن محطات الخطوط في اراي

        String[] lineN = {"Times Square", "34th", "28th", "23rd", "Union Square", "8th"};

        String[] lineL = {"8th", "6th", "Union Square", "3rd", "1st"};

        String[] line6 = {"Grand Central", "33rd", "28th", "23rd", "Union Square", "Astor Place"};

        
         // from the example in read me file
        String startLine = "N";
        String startStop = "Times Square";
        String endLine = "6";
        String endStop = "33rd";

        // سويت فنكشن عشان اعرف انا بستخدم اي لاين من محطة البدايه والنهاية
        String[] startArray = getLineArray(startLine, lineN, lineL, line6);
        String[] endArray = getLineArray(endLine, lineN, lineL, line6);

        // فنكشن عشان يرجع لي قيمة المحطة بالاندكس 
        int startIndex = findIndex(startArray, startStop);
        int unionStart = findIndex(startArray, "Union Square");
        int endIndex = findIndex(endArray, endStop);
        int unionEnd = findIndex(endArray, "Union Square");
        int totalStops = 0;

        // طباعة الجزء الأول من الرحلة خط البدايه
        System.out.print("You must travel through the following stops on the " + startLine + " line: ");

        totalStops += printStops(startArray, startIndex, unionStart);
        System.out.println();

        // إذا الخط مختلف
        if (!startLine.equals(endLine)) {
            System.out.println("Change at Union Square.");

            System.out.print("Your journey continues through the following stops on the " + endLine + " line");
            totalStops += printStops(endArray, unionEnd, endIndex);

            System.out.println();

        }

        System.out.println(totalStops + " stops in total.");

    }

    // ترجع لي اسم الخط

    public static String[] getLineArray(String name, String[] N, String[] L, String[] six) {

        if (name.equals("N")) return N;
        if (name.equals("L")) return L;

        return six;

    }

    // اسوي الاندكس الي بخزن فيه المحطه البدايه

    public static int findIndex(String[] arr, String stop) {

        for (int i = 0; i < arr.length; i++) {

            if (arr[i].equalsIgnoreCase(stop)) return i;

        }

        return -1;

    }

    //اطبع عدد المحطات الي بداها اليوزر والين ما وقف

    public static int printStops(String[] arr, int start, int end) {

        int count = 0;

        if (start < end) {

            for (int i = start + 1; i <= end; i++) {

                System.out.print(arr[i]);

                count++;
            }

        } else {

            for (int i = start - 1; i >= end; i--) {

                System.out.print(arr[i]);

                count++;

            }

        }

        return count;

    }

}
    
