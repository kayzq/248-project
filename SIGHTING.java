//NORMAL CONSTRUCTOR


public class SIGHTING {

    private String sightingid;
    private String speciesName;
    private String location;
    private String dateSpotted;
    private String observerName;
    private boolean criticallyEndangered;


public SIGHTING(String sightingid, String speciesName, String location, String dateSpotted, String observerName, boolean criticallyEndangered) {
    this.sightingid = sightingid;
    this.speciesName = speciesName;
    this.location = location;
    this.dateSpotted = dateSpotted;
    this.observerName = observerName;
    this.criticallyEndangered = criticallyEndangered;
}

//SETTER
public void setSightingid(String sightingid) {
    this.sightingid = sightingid;
}
public void setSpeciesName(String speciesName) {
    this.speciesName = speciesName;
}
public void setLocation(String location) {
    this.location = location;
}
public void setDateSpotted(String dateSpotted) {
    this.dateSpotted = dateSpotted;
}
public void setObserverName(String observerName) {
    this.observerName = observerName;
}
public void setCriticallyEndangered(boolean criticallyEndangered) {
    this.criticallyEndangered = criticallyEndangered;
}




public String getSightingid(){
    return sightingid;
}

public String getSpeciesName(){
    return speciesName;
}

public String getLocation(){
    return location;
}

public String getDateSpotted(){
    return dateSpotted;
}

public String getObserverName(){
    return observerName;
}

public boolean getisCriticallyEndangered(){
    return criticallyEndangered;
}
    
public String toString(){
    return "Sighting ID: " + sightingid + "\n" +
           "Species Name: " + speciesName + "\n" +
           "Location: " + location + "\n" +
           "Date Spotted: " + dateSpotted + "\n" +
           "Observer Name: " + observerName + "\n" +
           "Critically Endangered: " + getisCriticallyEndangered() + "\n";
}


}