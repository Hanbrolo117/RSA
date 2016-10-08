package euclidean;

import java.util.Scanner;

/**
 * Created by CptAmerica on 4/1/15.
 */
public class ExtendedEuclideanAlgorithm {

    static long[][] table;
    long[][] extendedGCD_Table;
    long counter = 0;
    int data = 0;
    public ExtendedEuclideanAlgorithm(long number1, long number2){
        table = new long[getGCDIteration(number1, number2)][5];
        extendedGCD_Table = new long[getGCDIteration(number1, number2)+2][5];

    }


    public ExtendedEuclideanAlgorithm(){

    }
    public int getGCDIteration(long number1, long number2){
        long a = Math.abs(number1);
        long b = Math.abs(number2);
        long gcd = 0;
        long q;
        long r = 0;
        int iteration = 1;
        //Second number must be the smaller number the 2
        //a and b are arbitrary as they just represent the bigger (a) and smaller (b) number
        if(a < b){
            long temp = b;
            b = a;
            a = temp;
        }
        for(q = 0; q < a; q++){
            //base case (r will equal 0), this jumps out of the loop
            if((q*b) == a){
                break;
            }
            else if((q*b) > a){
                //Do nothing, let loop decrement.
            }
            //otherwise the third and final possible state is less than num1
            else if((q*b < a && (q+1)*b > a)){
                r = a - (q*b);
                break;
            }
        }

        if((q*b) == a){
            iteration = 1;
        }
        else{

            iteration += getGCDIteration(b, r);
        }
        return iteration;
    }


    public long[] gcdTable(long number1, long number2){

        long a = Math.abs(number1);
        long b = Math.abs(number2);
        long gcd = 0;
        long q;
        long r = 0;
        long[] result = new long[3];
        //Second number must be the smaller number the 2
        //a and b are arbitrary as they just represent the bigger (a) and smaller (b) number
        if(a < b){
            long temp = b;
            b = a;
            a = temp;
        }


        //Set up iteration columns
        for(int i = 0; i < table.length; i++){
            table[i][0] = i;
        }

        for(q = 0; q < a; q++){

            //base case (r will equal 0), this jumps out of the loop
            if((q*b) == a){

                break;
            }
            else if((q*b) > a){
                //Do nothing, let loop decrement.
            }
            //otherwise the third and final possible state is less than num1
            else if((q*b < a && (q+1)*b > a)){

                r = a - (q * b);
                break;

            }

        }

        if((q*b) == a){
           // System.out.println("--------------------------------------------------\n");
            //System.out.println("DEBUG");
            //System.out.println("--------------------------------------------------\n");

            //gcd
            result[0] = b;
            //System.out.println("GCD before return: "+ b);

            //X
            result[2] = table[table.length-1][3];

            //Y
            result[1] = table[table.length-1][4];


            return result;

            //gcd = b;

        }
        else {

                table[data][1] = a;
                //System.out.println("Calculated q for " + data + ": " + table[data][1]);

                //add r data to table row
                table[data][2] = q;
                //System.out.println("Calculated r for " + data + ": " + table[data][2]);

                //Find X (for us it is really Y)
                table[data][3] = b;
                //System.out.println("Calculated x for " + data + ": " + table[data][3]);

                //Find Y (for us it is really X)
                table[data][4] = r;
                //System.out.println("Calculated y for " + data + ": " + table[data][4]);
                //System.out.println("--------------------------------------------------\n");
                data += 1;




            result = gcdTable(b, r);

        }

        //Return {gcd, x, y}
        return result;
    }


    public long[] extendedGCD(long number1, long number2){
        long[] result = new long[3];

        //Sets table

        //gcd-Table Key
        //a (eventually b) [i][1]
        //q [i][2]
        //b (eventually r) [i][3]
        //r [i][4]
        gcdTable(number1, number2);


        //Extended-Table Key
        //i     (0)
        //q-i   (1)
        //r-i   (2)
        //x-i   (3)
        //y-i   (4)

        long a = number1;
        long b = number2;
        if(a < b){
            long temp = b;
            b = a;
            a = temp;
        }
        //Initial setup

        //Sets up row -1 (based off example table in paper)
        extendedGCD_Table[0][2] = a;
        extendedGCD_Table[0][0] = -1;
        extendedGCD_Table[0][3] = 1;
        extendedGCD_Table[0][4] = 0;

        //Sets up row 0 (based off example table in paper)
        extendedGCD_Table[1][2] = b;
        extendedGCD_Table[1][0] = 0;
        extendedGCD_Table[1][3] = 0;
        extendedGCD_Table[1][4] = 1;

        //Set up row 1 (based off example table in paper)
        extendedGCD_Table[2][1] = table[0][2];
        extendedGCD_Table[2][2] = table[0][4];
        //X
        extendedGCD_Table[2][3] = extendedGCD_Table[0][3] - (extendedGCD_Table[2][1]*extendedGCD_Table[1][3]);
        //Y
        extendedGCD_Table[2][4] = extendedGCD_Table[0][4] - (extendedGCD_Table[2][1]*extendedGCD_Table[1][4]);


        int rCheck = 1;
        int track = 0;
        if(table[rCheck-1][4] == 0){
            result[1] = 0;
            result[2] = 1;
        }else {
            while (table[rCheck][4] != 0) {
                //add q
                extendedGCD_Table[rCheck + 2][1] = table[rCheck][2];
                //add r
                extendedGCD_Table[rCheck + 2][2] = table[rCheck][4];

                //find x
                extendedGCD_Table[rCheck + 2][3] = extendedGCD_Table[rCheck][3] - (extendedGCD_Table[rCheck + 2][1] * extendedGCD_Table[rCheck + 1][3]);

                //find y
                extendedGCD_Table[rCheck + 2][4] = extendedGCD_Table[rCheck][4] - (extendedGCD_Table[rCheck + 2][1] * extendedGCD_Table[rCheck + 1][4]);

                track++;
                rCheck++;
            }
            result[1] = extendedGCD_Table[extendedGCD_Table.length-2][3];
            result[2] = extendedGCD_Table[extendedGCD_Table.length-2][4];
        }
        result[0] = gcd(a, b);



        return result;

    }


    public long gcd(long number1, long number2){
        long a = Math.abs(number1);
        long b = Math.abs(number2);
        long gcd = 0;
        long q;
        long r = 0;

        //Second number must be the smaller number the 2
        //a and b are arbitrary as they just represent the bigger (a) and smaller (b) number
        if(a < b){
            long temp = b;
            b = a;
            a = temp;
        }

        //a = q*b + r
        //20 = q * 5 + r
        //20 = 4 * 5 + 0
        //20 = q * 3 + r
        //20 = 6 * 3 + 1
        //3 = q * 1 + r
        // 3 = 3 * 1 + 0

        //gcd(a,b) = gcd(b,r)
        //find quotient that either is equal to or is highest number less than num1 (denoted with 'q').
        //I chose this implementation as I remember someone mentioning in class to start at the high
        // number rather than working from zero, this allows us to eliminate the need to see if the q
        // is the highest possible number that we can use.
        for(q = 0; q < a; q++){

            //base case (r will equal 0), this jumps out of the loop
            if((q*b) == a){
                break;
            }
            else if((q*b) > a){
                //Do nothing, let loop decrement.
            }
            //otherwise the third and final possible state is less than num1
            else if((q*b < a && (q+1)*b > a)){
                r = a - (q*b);
                break;
            }

        }

        //if a = some q * b then r = 0, meaning there is no remainder (base case),
        //I double check conditions after breaking from loop.
        if((q*b) == a){

            //we return b because if we look at how the algorithm works, r:k is the b of r:k+1 = 0
            gcd = b;

            //

        }
        //Otherwise if less than perform recursion of this method. (again double check condition as we broke from loop).
        else{
            //if gcd(a,b) = gcd (number1, number2) and num2 = b, then surely num2 = b therefore:
            //gcd(b,r)
            gcd = gcd(b, r);
        }
        return gcd;
    }

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String input;
        long in1 = 0;
        long in2 = 0;

        System.out.println("Demonstrates a basic version of the Euclidean algorithm.\n" +
                "Enter 2 numbers to find their gcd!\n");

        //Prompt user for the two numbers the do whiles allow for redo if the user inputs something invalid
        //this approach cleans up the user interaction by avoiding fatal errors.
        long sentinel_main = 0;
        do{

            //Get the first number from the user
            int sentinel_Input_1 = 0;
            do {
                System.out.print("Enter first number: ");
                input = scan.nextLine();

                try {
                    in1 = Integer.parseInt(input);
                    sentinel_Input_1 = 0;
                } catch (Exception e) {
                    System.out.println("\n-------------------------");
                    System.out.println("ERROR: Not a number.");
                    System.out.println("-------------------------\n");
                    sentinel_Input_1 = 1;
                }
            } while (sentinel_Input_1 == 1);
            ////////////////////////////////////////



            //Get second number from user
            long sentinel_Input_2 = 0;
            do {
                System.out.print("Enter second number: ");
                input = scan.nextLine();

                try {
                    in2 = Integer.parseInt(input);
                    sentinel_Input_2 = 0;
                } catch (Exception e) {
                    System.out.println("\n-------------------------");
                    System.out.println("ERROR: Not a number.");
                    System.out.println("-------------------------\n");
                    sentinel_Input_2 = 1;
                }
            } while (sentinel_Input_2 == 1);
            ////////////////////////////////////////

            sentinel_main = 0;
        }while(sentinel_main == 1);


        //Display algorithm stuff
        ExtendedEuclideanAlgorithm euclid = new ExtendedEuclideanAlgorithm(in1, in2);
        long[] result = euclid.extendedGCD(in1, in2);

        System.out.println("The gcd of "+in1+" and "+in2+" is: "+result[0]+". The x value is: "+ result[1]+", and the y value is: "+ result[2]);
        System.out.println("Checking...");

        long a = in1;
        long b = in2;

        if(a < b){
            long temp = b;
            b = a;
            a = temp;
        }
        long product1 = a * result[1];
        long product2 = b * result[2];



        if(result[0] == (product1 + product2)){
            System.out.println(result[0]+" = ("+a+" * "+ result[1]+") + ("+b+" * "+result[2]+")");
        }
        else{
            System.out.println(result[0]+" =/ ("+a+" * "+ result[1]+") + ("+b+" * "+result[2]+")");

        }

        System.out.println();
        System.out.println("Euclidean Algorithm Table");
        System.out.println("-------------------------");
        for(int y = 0; y < table.length; y++){
            for(int x = 0; x < table[0].length; x++ ){
                System.out.print(table[y][x]+"\t");
            }
            System.out.println();

        }

        System.out.println();
        System.out.println("Extended Euclidean Algorithm Table");
        System.out.println("-------------------------");
        for(int y = 0; y < euclid.extendedGCD_Table.length; y++){
            for(int x = 0; x < euclid.extendedGCD_Table[0].length; x++ ){
                System.out.print(euclid.extendedGCD_Table[y][x]+"\t");
            }
            System.out.println();
        }

        //Close up shop.
        scan.close();
        System.exit(0);



    }
}
