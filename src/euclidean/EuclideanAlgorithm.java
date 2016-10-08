package euclidean;

import java.lang.reflect.Array;
import java.util.Scanner;

/**
 * Created by CptAmerica on 3/25/15.
 */
public class EuclideanAlgorithm {

    static int[][] table;
    int data = 2;
    int iteration;
    int counter = 0;
    int[] b2r2;
    public EuclideanAlgorithm(int number1, int number2){
        table = new int[getGCDIteration(number1, number2)+2][5];
        iteration = getGCDIteration(number1, number2);
        b2r2 = getB2R2(number1, number2);

        int num1 = 0;

        int num2 = 0;
        if(number1 < number2){
            int temp = number2;
            num2 = number1;
            num1 = temp;
        }

        //index 0 = -1
        //index 1 = 0
        //index 2 = 1
        //.
        //.
        //.
        //etc.
        //SET UP FIRST ROW IN TABLE
        //iteration
        table[0][0] = -1;
        //skip quotient which is table[0][1]
        //r
        table[0][2] = num1;
        //x
        table[0][3] = 1;
        //y
        table[0][4] = 0;


        //SET UP SECOND ROW IN TABLE
        //iteration
        table[1][0] = 0;
        //skip quotient which is table[1][1]
        //r
        table[1][2] = num2;
        //x
        table[1][3] = 0;
        //y
        table[1][4] = 1;
    }

    //The gcd(a,b) recursive method
    public int gcd(int number1, int number2){
        int a = Math.abs(number1);
        int b = Math.abs(number2);
        int gcd = 0;
        int q;
        int r = 0;

        //Second number must be the smaller number the 2
        //a and b are arbitrary as they just represent the bigger (a) and smaller (b) number
        if(a < b){
            int temp = b;
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
        for(q = a; q > 0; q--){

            //base case (r will equal 0), this jumps out of the loop
            if((q*b) == a){
                break;
            }
            else if((q*b) > a){
                //Do nothing, let loop decrement.
            }
            //otherwise the third and final possible state is less than num1
            else if((q*b < a)){
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


    public int[] getB2R2(int number1, int number2){
        int a = Math.abs(number1);
        int b = Math.abs(number2);
        int q;
        int r = 0;
        int[] b2r2 = new int[2];
        //Second number must be the smaller number the 2
        //a and b are arbitrary as they just represent the bigger (a) and smaller (b) number
        if(a < b){
            int temp = b;
            b = a;
            a = temp;
        }
        for(q = a; q > 0; q--){
            //base case (r will equal 0), this jumps out of the loop
            if((q*b) == a){
                break;
            }
            else if((q*b) > a){
                //Do nothing, let loop decrement.
            }
            //otherwise the third and final possible state is less than num1
            else if((q*b < a)){
                r = a - (q*b);
                break;
            }
        }

        if((q*b) == a){
            iteration = 1;
        }
        else{
            if(counter != 2) {
                counter += 1;
            }
            else if(counter == 2){
                b2r2[0] = b;
                b2r2[1] = r;
                return b2r2;
            }
            b2r2 = getB2R2(b, r);
        }
        return b2r2;
    }

    public int getGCDIteration(int number1, int number2){
        int a = Math.abs(number1);
        int b = Math.abs(number2);
        int gcd = 0;
        int q;
        int r = 0;
        int iteration = 1;
        //Second number must be the smaller number the 2
        //a and b are arbitrary as they just represent the bigger (a) and smaller (b) number
        if(a < b){
            int temp = b;
            b = a;
            a = temp;
        }
        for(q = a; q > 0; q--){
            //base case (r will equal 0), this jumps out of the loop
            if((q*b) == a){
                break;
            }
            else if((q*b) > a){
                //Do nothing, let loop decrement.
            }
            //otherwise the third and final possible state is less than num1
            else if((q*b < a)){
                r = a - (q*b);
                break;
            }
        }

        if((q*b) == a){
            iteration = 1;
        }
        else{

            iteration += gcd(b, r);
        }
        return iteration;
    }

    public int[] adjustedTable(int number1, int number2){
        /*
        int num1 = number1;
        int num2 = number2;
        int b, r;

        if(number1 < number2){
            int temp = num2;
            num2 = num1;
            num1 = temp;
        }

        int[] result;

        for(int i = 0; i <= 1; i++){

            if(num2 == 0){

            }
            else if( num1 %  num2 != 0){

            num1 = num2;
            num2 = num1 % num2;
            }
            else{
             //do nothing?
            }
            System.out.println(num1);
            System.out.println(num2);
        }
        b = num1;
        r = num2;
        System.out.println(b);
        System.out.println(r);*/
        int[] result;
        int b = b2r2[0];
        int r = b2r2[1];
        result = gcdTable(b, r);
        return result;
    }

    public int[] gcdTable(int number1, int number2){

        int a = Math.abs(number1);
        int b = Math.abs(number2);
        int gcd = 0;
        int q;
        int r = 0;
        int[] result = new int[3];
        //Second number must be the smaller number the 2
        //a and b are arbitrary as they just represent the bigger (a) and smaller (b) number
        if(a < b){
            int temp = b;
            b = a;
            a = temp;
        }


        //Set up iteration columns
        for(int i = 2; i < table.length; i++){
            table[i][0] = i - 1;
        }

        for(q = a; q > 0; q--){

            //base case (r will equal 0), this jumps out of the loop
            if((q*b) == a){

                break;
            }
            else if((q*b) > a){
                //Do nothing, let loop decrement.
            }
            //otherwise the third and final possible state is less than num1
            else if((q*b < a)){

                    r = a - (q * b);
                    break;

            }

        }

        if((q*b) == a){
            System.out.println("--------------------------------------------------\n");
            System.out.println("DEBUG");
            System.out.println("--------------------------------------------------\n");
/*
            //gcd
            result[0] = b;
            System.out.println("GCD before return: "+ b);

            //X
            result[2] = table[table.length-2][3];
            System.out.println("X value before return: "+table[table.length-(iteration-2)][3]);

            //Y
            result[1] = table[table.length-2][4];
            System.out.println("Y value before return: "+table[table.length-(iteration-2)][4]);
            System.out.println("--------------------------------------------------\n");*/
            for(int y = 0; y < table.length; y++){
                System.out.println();
                for(int x = 0; x < table[0].length; x++ ){
                    System.out.print(table[y][x]+"\t");
                }
            }
            System.out.println();
            return result;

            //gcd = b;

        }
        else {

                       table[data][1] = q;
                       System.out.println("Calculated q for " + data + ": " + table[data][1]);

                       //add r data to table row
                       table[data][2] = r;
                       System.out.println("Calculated r for " + data + ": " + table[data][2]);

                       //Find X (for us it is really Y)
                       table[data][3] = table[data - 2][3] - (table[data][1] * table[data - 1][3]);
                       System.out.println("Calculated x for " + data + ": " + table[data][3]);

                       //Find Y (for us it is really X)
                       table[data][4] = table[data - 2][4] - (table[data][1] * table[data - 1][4]);
                       System.out.println("Calculated y for " + data + ": " + table[data][4]);
                       System.out.println("--------------------------------------------------\n");
            if(data <= iteration+2) {
                data += 1;
            }
                result = gcdTable(b, r);

        }

        //Return {gcd, x, y}
        return result;
    }



    //Test the code
    public static void main(String[] args) {
        //Temp var's
        Scanner scan = new Scanner(System.in);
        String input;
        int in1 = 0;
        int in2 = 0;

        System.out.println("Demonstrates a basic version of the Euclidean algorithm.\n" +
                "Enter 2 numbers to find their gcd!\n");

        //Prompt user for the two numbers the do whiles allow for redo if the user inputs something invalid
        //this approach cleans up the user interaction by avoiding fatal errors.
        int sentinel_main = 0;
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
            int sentinel_Input_2 = 0;
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

        EuclideanAlgorithm euclid = new EuclideanAlgorithm(in1, in2);
        //Display the Greatest common denominator by calling the recursive method: gcd(num1, num2).
        int theGCD = euclid.gcd(in1, in2);
        //int theEffiecientGCD = euclid.getGCDIteration(in1, in2);

        System.out.println("The greatest common denominator of "+in1+" and "+in2+" is: "+theGCD);
        //System.out.println("The number of iterations to find gcd of "+in1+" and "+in2+" is: "+theEffiecientGCD);
/*
        int[] result = new int[3];
        for(int i = 0; i < 3; i++ ){
         result[i] = euclid.gcdTable(in1, in2)[i];
        }
*/
        /*
        int g = euclid.gcd(in1, in2);

        //X
        //result[2] = euclid.table[euclid.table.length-1][4];
        //System.out.println("X value before return: "+euclid.table[euclid.table.length-1][4]);

        //Y
        //result[1] = euclid.table[euclid.table.length-1][3];


        System.out.println("The gcd of "+in1+" and "+in2+" is: "+result[0]+". The x value is: "+ result[1]+", and the y value is: "+ result[2]);
        System.out.println("Checking...");

        int product1 = in1 * result[1];
        int product2 = in2 * result[2];


        if(result[0] == (product1 + product2)){
            System.out.println(result[0]+" = ("+in1+" * "+ result[1]+") + ("+in2+" * "+result[2]+")");
        }
        else{
            System.out.println(result[0]+" =/ ("+in1+" * "+ result[1]+") + ("+in2+" * "+result[2]+")");

        }

        for(int y = 0; y < table.length; y++){
            System.out.println();
            for(int x = 0; x < table[0].length; x++ ){
                System.out.print(table[y][x]+"\t");
            }
        }
        */
        //Close up shop.
        scan.close();
        System.exit(0);
    }
}
//Note:
//Two integers are relatively prime if 1 is their gcd