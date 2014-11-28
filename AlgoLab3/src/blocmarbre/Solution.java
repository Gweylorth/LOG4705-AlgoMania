package blocmarbre;

import java.util.ArrayList;

/**
 * Created by Gwenegan on 28/11/2014.
 */
public class Solution extends ArrayList<Bloc> {

    public int getPerte(){
        if (this.isEmpty()) {
            return Integer.MAX_VALUE;
        }

        int perte = 0;
        for (Bloc bloc : this) {
            perte += bloc.getPerte();
        }
        return perte;
    }

    public boolean changerSolution() {
        return false;
    }
}
