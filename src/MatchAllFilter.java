import java.util.ArrayList;

/**
 * Created by user on 02.02.2017.
 */
public class MatchAllFilter implements Filter {
    private ArrayList<Filter> listF;

    public MatchAllFilter() {
        this.listF = new ArrayList<>();
    }
    //
    public void addFilter(Filter f){
        this.listF.add(f);
    }
    //
    @Override
    public boolean satisfies(QuakeEntry qe) {
        for (Filter lf : this.listF) {
            if (!lf.satisfies(qe))
                return false;
        }
        return true;
    }

    @Override
    public String getName() {
        String result="";
        for (Filter f :this.listF) {
            result+=f.getName()+" ";
        }
        return result;
    }
}
