package RSA;

import euclidean.CoprimeNumberException;
import euclidean.EuclideanAlgorithm;
import euclidean.ExtendedEuclideanAlgorithm;
import sun.tools.tree.ThisExpression;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by CptAmerica on 4/4/15.
 */
public class RSA_Encryption {

    private long p;
    private long q;
    private long n;
    private long ϕ_N;
    private long e;
    private long d;
    private String encrpyted_message;
    private String decrypted_message;

    public RSA_Encryption(long p, long q){
        this.p = p;
        this.q = q;
        this.n = p*q;
        this.encrpyted_message = "";
        this.decrypted_message = "";
        System.out.println("n = "+ this.n);
        phi();
        findE();

    }

    public String encrpyt(String plainText){


        for(int i = 0; i < plainText.length();i++){

            //letter -i of string to encrypt
            char chP = plainText.charAt(i);
            System.out.print(chP + " ");
            long P_i = String.valueOf(chP).codePointAt(0);
            long temp = P_i;
            long C_i;

            for(long power = 0; power < (e-1); power++){
                P_i *= temp;
            }
            System.out.println("P_i = "+temp+" raised to e = "+ this.e+ " is: "+P_i);
            C_i = P_i % this.n;
            encrpyted_message +=(C_i +" ");
        }

        System.out.println();
        return this.encrpyted_message;
    }



    public String decrypt(String encryption, long pKey1, long pKey2){
        //E should always be bigger than ϕ(N) ?
        //pKey1 = E
        //pKey2 = ϕ(N)
        ExtendedEuclideanAlgorithm euclid = new ExtendedEuclideanAlgorithm(pKey1, pKey2);

        long[] extendedGCD = euclid.extendedGCD(pKey1, pKey2);
        long D = extendedGCD[2];
        long E = pKey1;
        System.out.println("Just for reference: ");
        System.out.println("gcd = "+ extendedGCD[0]);
        System.out.println("x = "+ extendedGCD[1]);

        System.out.println("D value: "+D);
        System.out.println("E value: "+E);

        String[] code = encryption.split(" ");
        long[] C_i = new long[code.length];
        for(int i = 0; i <  code.length; i++){
            C_i[i] = Long.valueOf(code[i]);
        }
        //Prove parsing of code is correct
        for(int i = 0; i < C_i.length; i++){
            System.out.println("Code  "+ i +": "+ C_i[i]);
        }

        //Find D_actual if D is negative
        long D_actual = -1;
        long D_temp;
        if(D < 0){

            for(long k = 0; D_actual < 0; k++ ){
                D_temp = D + k*this.ϕ_N;
                if(D_temp > 0){
                    D_actual = D_temp;

                }
            }
            D = D_actual;
            System.out.println("D value: "+D);

        }



        //decode
        BigInteger bigI_D = new BigInteger(String.valueOf(D));
        BigInteger bigI_Mod = new BigInteger(String.valueOf(this.n));

        for(int i = 0; i < code.length; i++){
            BigInteger bigI_C = new BigInteger(String.valueOf(code[i]));

            BigInteger temp = new BigInteger(String.valueOf(code[i]));
            long P_i = Long.valueOf(code[i]);

            //Long data type power C_i
             String p =  String.valueOf(bigI_C.modPow(bigI_D, bigI_Mod ));
             P_i = Long.valueOf(p);
            //long plainT = P_i % pKey1;
            System.out.println("C_i = "+temp+" raised to D = "+ D + " = "+ P_i +"(mod "+this.n+") = "+ P_i);
            String decode = Character.toString((char)P_i);
            System.out.println("P_i = "+decode);
            decrypted_message += Character.toString((char)P_i);

        }
        System.out.println("Decrypted message: ");

        return decrypted_message;

    }


    private void phi(){

        this.ϕ_N = (p-1)*(q-1);
        System.out.println("ϕ_N = "+ this.ϕ_N);

    }

    private void findE(){
        ExtendedEuclideanAlgorithm euclid = new ExtendedEuclideanAlgorithm();
        long temp;
        for(long i = 2; i < ϕ_N; i++){

            //If i is coprime (gcd = 1) with n check if ϕ(N) and e are coprime

            //if(euclid.gcd(i, n) == 1){
                //is i and ϕ(N) coprime? if so break from loop.
                if(euclid.gcd(i, ϕ_N) == 1) {
                    this.e = i;
                    System.out.println("e = "+ this.e);

                    break;
                }

                //}

        }
    }

    private void findEE(){
        ExtendedEuclideanAlgorithm euclid = new ExtendedEuclideanAlgorithm();
        long temp;

            //If i is coprime (gcd = 1) with n check if ϕ(N) and e are coprime

            //if(euclid.gcd(i, n) == 1){
            //is i and ϕ(N) coprime? if so break from loop.
            if(euclid.gcd(this.ϕ_N-1, this.ϕ_N) == 1) {
               this.e = this.ϕ_N-1;
                System.out.println("e = "+ this.e);



        }
        else{
                throw new CoprimeNumberException(this.ϕ_N-1, this.ϕ_N);
            }

    }

    public long[] getPublicKeys(){
        long[] pKeys = new long[2];
        pKeys[0] = this.e;
        pKeys[1] = this.ϕ_N;
        return pKeys;
    }
}
