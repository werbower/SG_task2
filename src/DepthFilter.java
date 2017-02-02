/**
 * Created by user on 02.02.2017.
 */
public class DepthFilter implements Filter {
    private double minDepth;
    private double maxDepth;

    public DepthFilter(double minDepth, double maxDepth) {
        this.minDepth = minDepth;
        this.maxDepth = maxDepth;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        return qe.getDepth()>=this.minDepth&&qe.getDepth()<=this.maxDepth;
    }

    @Override
    public String getName() {
        return "Depth ";
    }
}
