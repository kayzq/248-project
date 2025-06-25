public class CRITICALLYENDANGEREDSIGHTING extends SIGHTING {
    private String monitoringFrequency;
    private boolean rescued;


public CRITICALLYENDANGEREDSIGHTING(String sightingid, String speciesName, String location, String dateSpotted, String observerName, boolean criticallyEndangered, String monitoringFrequency, boolean rescued) {
    super(sightingid, speciesName, location, dateSpotted, observerName, criticallyEndangered);
    this.monitoringFrequency = monitoringFrequency;
    this.rescued = rescued;
}

// Setter methods
public void setMonitoringFrequency(String monitoringFrequency) {
    this.monitoringFrequency = monitoringFrequency;
}

public void setRescued(boolean rescued) {
    this.rescued = rescued;
}

public String getMonitoringFrequency() {
    return monitoringFrequency;
}
public boolean isRescued() {
    return rescued;
}
    
public String toString() {
    return super.toString() + 
           "Monitoring Frequency: " + monitoringFrequency + "\n" +
           "Rescued: " + (rescued ? "Yes" : "No") + "\n";
}
}
