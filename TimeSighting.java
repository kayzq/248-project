public class TimeSighting extends SIGHTING {

    private double timeSpotted;
    private boolean nocturnal;
    private double sightingDuration;


    public TimeSighting(String sightingid, String speciesName, String location, String dateSpotted, String observerName, boolean criticallyEndangered, double timeSpotted, boolean nocturnal, double sightingDuration) {
        super(sightingid, speciesName, location, dateSpotted, observerName, criticallyEndangered);
        this.timeSpotted = timeSpotted;
        this.nocturnal = nocturnal;
        this.sightingDuration = sightingDuration;
    }

    // Setter methods
    public void setTimeSpotted(double timeSpotted) {
        this.timeSpotted = timeSpotted;
    }

    public void setNocturnal(boolean nocturnal) {
        this.nocturnal = nocturnal;
    }
    public void setSightingDuration(double sightingDuration) {
        this.sightingDuration = sightingDuration;
    }
    public double getTimeSpotted() {
        return timeSpotted;
    }

    public boolean isNocturnal() {
        return nocturnal;
    }

    public double getSightingDuration() {
        return sightingDuration;
    }


   
    public String toString() {
        return super.toString() + String.format(
               " %-12.2f  %-12s  %-7.2f \n",
            timeSpotted,
            nocturnal ,
            sightingDuration
            );
    }

    
}
