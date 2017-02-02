import java.util.*;
//import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        }
        return answer;
    }
    //
    public void testMatchAllFilter(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        //
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0,2));
        maf.addFilter(new DepthFilter(-100_000,-10_000));
        maf.addFilter(new PhraseFilter("any","a"));
        System.out.println("Filters used are: "+maf.getName());
        ArrayList<QuakeEntry> answer = filter(list,maf);
        for (QuakeEntry qe :answer) {
            System.out.println(qe);
        }
    }

    public void testMatchAllFilter2(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        //
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0,3));
        maf.addFilter(new DistanceFilter(new Location(36.1314,-95.9372),10_000_000));

        maf.addFilter(new PhraseFilter("any","Ca"));
        System.out.println("Filters used are: "+maf.getName());
        ArrayList<QuakeEntry> answer = filter(list,maf);
        for (QuakeEntry qe :answer) {
            System.out.println(qe);
        }
    }
    //
    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

//        Filter f = new MinMagFilter(4.0);
//        ArrayList<QuakeEntry> m7  = filter(list, f);
//        for (QuakeEntry qe: m7) {
//            System.out.println(qe);
//        }

        System.out.println("===========Filter by magnitude&depth");
        Filter f = new MagnitudeFilter(4.0,5.0);
        ArrayList<QuakeEntry> answer = filter(list,f);
        //
        f = new DepthFilter(-35_000.0,-12_000.0);
        answer = filter(answer,f);
        for (QuakeEntry qe :answer) {
            System.out.println(qe);
        }
        //
        System.out.println("===========Filter by distance&phrase");
        f = new DistanceFilter(new Location(35.42,139.43),10_000_000);
        answer = filter(list,f);
        f = new PhraseFilter("end","Japan");
        answer = filter(answer,f);
        for (QuakeEntry qe :answer) {
            System.out.println(qe);
        }

    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

    public static void main(String[] args) {
        EarthQuakeClient2 earthQuakeClient2 = new EarthQuakeClient2();
        //
        System.out.println("Filtering by magnitude and by depth");
        earthQuakeClient2.quakesWithFilter();
        //
        System.out.println("Filtering by some filters");
        earthQuakeClient2.testMatchAllFilter();
        //
        System.out.println("Filtering by some filters 2");
        earthQuakeClient2.testMatchAllFilter2();


    }

}
