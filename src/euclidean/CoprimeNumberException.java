package euclidean;

/**
 * Created by CptAmerica on 4/4/15.
 */
public class CoprimeNumberException extends RuntimeException{



    public CoprimeNumberException(long n, long ϕ_N){
        System.out.println("no such number exits for e such that e is a coprime of "+n+" and "+ ϕ_N);
    }


}
