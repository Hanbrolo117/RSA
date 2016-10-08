package RSA;

import java.util.Scanner;

/**
 * Created by CptAmerica on 4/4/15.
 */
public class RSADriver {

    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        String input;
        long in1 = 0;
        long in2 = 0;

        System.out.println("Demonstrates RSA encryption \n" +
                "Enter 2 big prime numbers to encrypt and decrypt hello world!\n");

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

        long a = in1;
        long b = in2;

        if(a < b){
            long temp = b;
            b = a;
            a = temp;
        }

        System.out.println("Enter a string to encrypt:");
        input = scan.nextLine();


        RSA_Encryption rsa = new RSA_Encryption(a, b);
        long start_enc = System.currentTimeMillis();
        String encrpytM = rsa.encrpyt(input);
        long end_enc = System.currentTimeMillis();
        long time_enc = (end_enc-start_enc)/1000;
        System.out.println(encrpytM);
        System.out.println("Encryption time: "+time_enc+" seconds");
        long[] pKeys = rsa.getPublicKeys();
        System.out.println("Public key1 = "+ pKeys[0]);
        System.out.println("Public key2 = "+ pKeys[1]);



        System.out.println("Enter key1: "+pKeys[0]);

        System.out.println("Enter key2: "+ pKeys[1]);

        System.out.println("-------------------------------------------");
        System.out.println("Decrypted message: ");
        long start = System.currentTimeMillis();
        System.out.println(rsa.decrypt(encrpytM, pKeys[0], pKeys[1]));
        long end = System.currentTimeMillis();
        long time = (end-start)/1000;
        System.out.println("Decryption time: "+time+" seconds");

        //example test
        //104729
        //104723
        //enter a message
    }
}