package blocmarbre;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Gwenegan on 28/11/2014.
 */
public class AmeliorationLocale {

    private Vorace vorace;

    private static int MAX_VOISINAGE_IT = 5;

    public AmeliorationLocale(Vorace v) {
        this.vorace = v;
    }

    public Solution ameliorer() {
        Solution si = this.vorace.getSolution();
        Set<Solution> Vs = voisinage(si);

        // remplir set

        for (Solution s : Vs) {
            if (s.getPerte() < si.getPerte()) {
                si = s;
            } else {
                continue;
            }
        }

        return si;
    }

    private HashSet<Solution> voisinage(Solution s0){
        HashSet<Solution> set = new HashSet<Solution>();

        int k = 0;
        Solution s, si;
        while(k <= 5) {
//
//            if (s.equals(si)) {
//                k++;
//            }
            break;
        }

        return set;
    }
}
