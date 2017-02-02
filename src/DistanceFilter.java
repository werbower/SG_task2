/**
 * Created by user on 02.02.2017.
 */
public class DistanceFilter implements Filter {
    private Location location;
    private double maxDistance;

    public DistanceFilter(Location location, double maxDistance) {
        this.location = location;
        this.maxDistance = maxDistance;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        return qe.getLocation().distanceTo(location)<maxDistance;
    }

    @Override
    public String getName() {
        return "Distance ";
    }
}
