
public class ENDANGEREDSIGHTING extends SIGHTING {
    private String threatLevel;
    private int populationEstimate;


public ENDANGEREDSIGHTING(String SightingID, String SpeciesName, String Location, String DateSpotted, String ObserverName, boolean CriticallyEndangered, String threatLevel, int populationEstimate) {
    super(SightingID, SpeciesName, Location, DateSpotted, ObserverName, CriticallyEndangered);
    this.threatLevel = threatLevel;
    this.populationEstimate = populationEstimate;
}

//setter
public void setthreatlevel(String threatlevel){
    this.threatLevel = threatLevel;
}
public void setpopulationEstimate(int populationEstimate){
    this.populationEstimate = populationEstimate;
}


public String getThreatLevel() {
    return threatLevel;
}
public int getPopulationEstimate() {
    return populationEstimate;
}

public String toString() {
    return super.toString() + String.format(" %-19s  %-7d \n",
            threatLevel  ,
          populationEstimate );
}
}
