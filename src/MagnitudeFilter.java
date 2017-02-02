/**
 * Created by user on 02.02.2017.
 */
public class MagnitudeFilter implements Filter {
    private double minMagnitude;
    private double maxMagnitude;

    public MagnitudeFilter(double minMagnitude, double maxMagnitude) {
        this.minMagnitude = minMagnitude;
        this.maxMagnitude = maxMagnitude;
    }
    //
    @Override
    public boolean satisfies(QuakeEntry qe) {
        return qe.getMagnitude()>=this.minMagnitude&&qe.getMagnitude()<=maxMagnitude;
    }

    @Override
    public String getName() {
        return "Magnitude ";
    }
}
