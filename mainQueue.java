import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class mainQueue {

    static void mainmenu() {
        System.out.println("\n\nWelcome to Endangered Species Management System");
        System.out.println("------------------------------------------------");
        System.out.println("1. Critically Endangered Species Management System");
        System.out.println("2. See Time of Sighting");
        System.out.println("3. Endangered Species Sighting Management System ");
        System.out.println("4. Exit");
        System.out.println("------------------------------------------------");
        System.out.print("Please select an option: ");
    }

    static int CriticallyEndangeredSpeciesManagementSystem(Scanner scan) {
        System.out.println("\n\nCritically Endangered Species Management System");
        System.out.println("------------------------------------------------");
        System.out.println("1. Add Critically Endangered Species");
        System.out.println("2. Search Critically Endangered Species");
        System.out.println("3. Delete Critically Endangered Species");
        System.out.println("4. List Critically Endangered Species");
        System.out.println("5. Sort Critically Endangered Species by Alphabetical Order");
        System.out.println("6. Back to Main Menu");
        System.out.println("------------------------------------------------");
        System.out.print("Please select an option: ");
        int response = scan.nextInt();
        return response;
    }

    static int TimeSightingManagementSystem(Scanner scan) {
        System.out.println("\n\nTime of Sighting Management System");
        System.out.println("------------------------------------------------");
        System.out.println("1. Calculate Average Time of Sighting");
        System.out.println("2. Calculate Total Time of Sighting");
        System.out.println("3. List Time of Sightings");
        System.out.println("4. Back to Main Menu");
        System.out.println("------------------------------------------------");
        System.out.print("Please select an option: ");
        int response = scan.nextInt();
        return response;
    }

    static int EndangeredSpeciesSightingManagementSystem(Scanner scan) {
        System.out.println("\n\nEndangered Species Sighting Management System");
        System.out.println("------------------------------------------------");
        System.out.println("1. Add Endangered Species Sighting");
        System.out.println("2. Search Endangered Species Sightings");
        System.out.println("3. Delete Endangered Species Sighting");
        System.out.println("4. List Endangered Species Sighting");
        System.out.println("5. Sort Endangered Species Sightings by Alphabetical Order");
        System.out.println("6. Back to Main Menu");
        System.out.println("------------------------------------------------");
        System.out.print("Please select an option: ");
        int response = scan.nextInt();
        return response;
    }

    static Queue getSightingInfo() {
        Queue queue = new Queue();
        BufferedReader in;
        String read = null;
        StringTokenizer st;
        try {
            in = new BufferedReader(new FileReader("sighting.txt"));
            while ((read = in.readLine()) != null) {
                st = new StringTokenizer(read, ",");
                String sightingId = st.nextToken().trim();
                String speciesName = st.nextToken().trim();
                String location = st.nextToken().trim();
                String dateSpotted = st.nextToken().trim();
                String observerName = st.nextToken().trim();
                boolean criticallyEndangered = Boolean.parseBoolean(st.nextToken().trim());

                SIGHTING sighting = new SIGHTING(sightingId, speciesName, location, dateSpotted, observerName,
                        criticallyEndangered);
                queue.enqueue(sighting);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return queue;
    }

    public static void main(String[] args) {
        try {
            Scanner input = new Scanner(System.in);
            Queue sightingQueue = getSightingInfo();
            Queue criticallyEndangeredQueue = getCriticallyEndangeredInfo();
            criticallyEndangeredQueue = filterCriticallyEndangeredFromSightings(sightingQueue);
            Queue endangeredSpeciesSightingQueue = filterEndangeredFromSightings(sightingQueue);
            Queue TimeSightingQueue = getTimeInfo();

            for (;;) {
                mainmenu();
                int option = input.nextInt();
                switch (option) {
                    case 1:
                        int submenu = CriticallyEndangeredSpeciesManagementSystem(input);
                        switch (submenu) {
                            case 1:
                                CRITICALLYENDANGEREDSIGHTING csighting = newCE(input);
                                writeNewCE(csighting);
                                System.out.println("Data added successfully!");
                                criticallyEndangeredQueue.enqueue(csighting);
                                break;
                            case 2:
                                System.out.println("\n\nCritically Endangered Species Management - Search Species");
                                System.out.println("------------------------------------------------");
                                input.nextLine();
                                System.out.print("Enter species id to search: ");
                                String speciesID = input.nextLine();
                                searchCE(criticallyEndangeredQueue, speciesID);
                                break;
                            case 3:
                                System.out.println("\n\nCritically Endangered Species Management - Delete Species");
                                System.out.println("------------------------------------------------");
                                input.nextLine();
                                System.out.print("Enter sighting id to delete: ");
                                String SightingID = input.nextLine();
                                CRITICALLYENDANGEREDSIGHTING ce;
                                Queue tempCE = new Queue();
                                if (criticallyEndangeredQueue.isEmpty())
                                    System.out.println("No data found");
                                else {
                                    while (!criticallyEndangeredQueue.isEmpty()) {
                                        ce = (CRITICALLYENDANGEREDSIGHTING) criticallyEndangeredQueue.dequeue();
                                        if (ce.getSightingid().equals(SightingID)) {
                                            System.out.println(
                                                    "Sighting with ID " + SightingID + " deleted successfully.");
                                        } else {
                                            tempCE.enqueue(ce);
                                        }
                                    }
                                }
                                if (tempCE.isEmpty())
                                    System.out.println("No data found");
                                else {
                                    while (!tempCE.isEmpty()) {
                                        ce = (CRITICALLYENDANGEREDSIGHTING) tempCE.dequeue();
                                        criticallyEndangeredQueue.enqueue(ce);
                                    }
                                }
                                deleteFileCE(criticallyEndangeredQueue);
                                break;
                            case 4:
                                System.out.println("\n\nCritically Endangered Species Management - List Species");
                                System.out.println("------------------------------------------------");
                                System.out.println(
                                        "Sighting ID | Species Name          | Location             | Date Spotted|Observer Name|Critically Endangered|Monitoring Frequency|Rescued");
                                System.out.print(PrintCE(criticallyEndangeredQueue));
                                break;
                            case 5:
                                sortByName(criticallyEndangeredQueue);
                                break;
                            case 6:
                                input.nextLine(); 
                                System.out.println("\nReturning to main menu...");
                                break;
                            default:
                                System.out.println("Invalid option. Please try again.");
                        }
                        break;
                    case 2:
                        int submenu2 = TimeSightingManagementSystem(input);
                        switch (submenu2) {
                            case 1:
                                System.out.println("\n\nTime of Sighting Management - Calculate Average Time of Sighting");
                                System.out.println("Average duration Time of sighting: "+ String.format("%.2f", calcavg(TimeSightingQueue)) + " hours");
                                break;
                            case 2:
                                System.out.println("\n\nCalculate Total Time Sighting");
                                System.out.println("Total duration Time of sighting: " + String.format("%.2f", calctotal(TimeSightingQueue)) + " hours");
                                break;
                            case 3:
                               System.out.println("\n\nTime of Sighting Management - List Sightings");
                               System.out.println("------------------------------------------------");
                               System.out.println(
                                       "Sighting ID | Species Name          | Location             | Date Spotted|Observer Name|Critically Endangered|Time Spotted|Nocturnal|Sighting Duration");
                               System.out.print(PrintTime(TimeSightingQueue));
                                break;
                            case 4:
                                input.nextLine(); 
                                System.out.println("\nReturning to main menu...");
                                break;
                            default:
                                System.out.println("Invalid option. Please try again.");
                        }
                        break;
                    case 3:
                        int submenu3 = EndangeredSpeciesSightingManagementSystem(input);
                        switch (submenu3) {
                            case 1:
                                ENDANGEREDSIGHTING esighting = newEndangeredSighting(input);
                                writeNewEndangeredSighting(esighting);
                                endangeredSpeciesSightingQueue.enqueue(esighting);
                                System.out.println("Data added successfully!");
                                break;
                            case 2:
                                System.out.println("\n\nEndangered Species Sighting Management - Search species");
                                System.out.println("------------------------------------------------");
                                input.nextLine();
                                System.out.print("Enter species id to search: ");
                                String searchID = input.nextLine();
                                searchEndangeredSighting(endangeredSpeciesSightingQueue, searchID);
                                break;
                            case 3:
                                System.out.println("\n\nEndangered Species Sighting Management - Delete species");
                                System.out.println("------------------------------------------------");
                                input.nextLine();
                                System.out.print("Enter sighting id to delete: ");
                                String sightingID = input.nextLine();
                                ENDANGEREDSIGHTING es;
                                Queue tempES = new Queue();
                                if (endangeredSpeciesSightingQueue.isEmpty())
                                    System.out.println("No data found");
                                else {
                                    while (!endangeredSpeciesSightingQueue.isEmpty()) {
                                        es = (ENDANGEREDSIGHTING) endangeredSpeciesSightingQueue.dequeue();
                                        if (es.getSightingid().equals(sightingID)) {
                                            System.out.println(
                                                    "Sighting with ID " + sightingID + " deleted successfully.");
                                        } else {
                                            tempES.enqueue(es);
                                        }
                                    }
                                }
                                endangeredSpeciesSightingQueue = tempES;
                                if (tempES.isEmpty())
                                    System.out.println("No data found");
                                else {
                                    while (!tempES.isEmpty()) {
                                        es = (ENDANGEREDSIGHTING) tempES.dequeue();
                                        endangeredSpeciesSightingQueue.enqueue(es);
                                    }
                                }
                                deleteFileES(endangeredSpeciesSightingQueue);
                                break;
                            case 4:
                                System.out.println("\n\nEndangered Species Sighting Management - List Sightings");
                                System.out.println("------------------------------------------------");
                                System.out.println(
                                        "Sighting ID | Species Name          | Location             | Date Spotted|Observer Name|Critically Endangered|Threat Level|Population Estimate");
                                System.out.print(PrintEndangered(endangeredSpeciesSightingQueue));
                                break;
                            case 5:
                               sortByNameENDANGERED(endangeredSpeciesSightingQueue);
                                break;
                            case 6:
                                input.nextLine();
                                System.out.println("\nReturning to main menu...");
                                break;
                            default:
                                System.out.println("Invalid option. Please try again.");
                        }
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static CRITICALLYENDANGEREDSIGHTING newCE(Scanner in) {
        Scanner input = new Scanner(System.in);

        System.out.println("Critically Endangered Management- Add New Species");
        System.out.println("------------------------------------------------");
        System.out.print("Enter sighting ID:  ");
        String sightingId = input.nextLine();
        System.out.print("Enter species name: ");
        String speciesName = input.nextLine();
        System.out.print("Enter location: ");
        String location = input.nextLine();
        System.out.print("Enter date spotted (dd/mm/yyyy): ");
        String dateSpotted = input.nextLine();
        System.out.print("Enter observer name: ");
        String observerName = input.nextLine();
        System.out.print("Is the species critically endangered? (true/false): ");
        boolean criticallyEndangered = input.nextBoolean();
        input.nextLine();  
        System.out.print("Enter monitoring frequency: ");
        String monitoringFrequency = input.nextLine();
        System.out.print("Was the species rescued? (true/false): ");
        boolean rescued = input.nextBoolean();

        CRITICALLYENDANGEREDSIGHTING data = new CRITICALLYENDANGEREDSIGHTING(sightingId, speciesName, location,
                dateSpotted, observerName,criticallyEndangered, monitoringFrequency, rescued);

        return data;
    }

    static void writeNewCE(CRITICALLYENDANGEREDSIGHTING data) {
        try (PrintWriter writeCE = new PrintWriter(new BufferedWriter(new FileWriter("critically_endangered.txt", true)))) {
            writeCE.println(data.getSightingid() + "," + data.getSpeciesName() + "," + data.getLocation() + ","
                    + data.getDateSpotted() + "," + data.getObserverName() + "," + true + ","
                    + data.getMonitoringFrequency() + "," + data.isRescued());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static boolean isFirstTimeWrite = true;

    static void writeNewTime(TimeSighting data) {
        try (PrintWriter writer = new PrintWriter(
                new BufferedWriter(new FileWriter("time_sighting.txt", !isFirstTimeWrite)))) {
            
            writer.println(
                data.getSightingid() + "," +
                data.getSpeciesName() + "," +
                data.getLocation() + "," +
                data.getDateSpotted() + "," +
                data.getObserverName() + "," +
                data.isCriticallyEndangered() + "," +
                data.getTimeSpotted() + "," +
                data.isNocturnal() + "," +
                data.getSightingDuration()
            );

            isFirstTimeWrite = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void searchCE(Queue searchQueue, String searchID) {
        String out = "";
        CRITICALLYENDANGEREDSIGHTING ce;
        boolean found = false;
        Queue tempQueue = new Queue();
        
        if (searchQueue.isEmpty())
            out += "No data found";
        else {
            while (!searchQueue.isEmpty()) {
                ce = (CRITICALLYENDANGEREDSIGHTING) searchQueue.dequeue();
                if (ce.getSightingid().equals(searchID)) {
                    out += ce.toString();
                    found = true;
                }
                tempQueue.enqueue(ce);
            }
            
            // Restore the original queue
            while (!tempQueue.isEmpty()) {
                searchQueue.enqueue(tempQueue.dequeue());
            }
        }
        
        if (!found) {
            System.out.println("No data found for Sighting ID: " + searchID);
        } else {
            System.out.println(out);
        }
    }

    static Queue getCriticallyEndangeredInfo() {
        Queue queue = new Queue();
        try (BufferedReader br = new BufferedReader(new FileReader("critically_endangered.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 8) {
                    CRITICALLYENDANGEREDSIGHTING ce = new CRITICALLYENDANGEREDSIGHTING(
                            parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim(),
                            parts[4].trim(), Boolean.parseBoolean(parts[5].trim()),
                            parts[6].trim(), Boolean.parseBoolean(parts[7].trim()));
                    queue.enqueue(ce);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return queue;
    }

    static Queue getTimeInfo() {
    Queue queue = new Queue();
    try (BufferedReader br = new BufferedReader(new FileReader("time_sighting.txt"))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 9) {
                String sightingId = parts[0].trim();
                String speciesName = parts[1].trim();
                String location = parts[2].trim();
                String dateSpotted = parts[3].trim();
                String observerName = parts[4].trim();
                boolean criticallyEndangered = Boolean.parseBoolean(parts[5].trim());
                double timeSpotted = Double.parseDouble(parts[6].trim());
                boolean nocturnal = Boolean.parseBoolean(parts[7].trim());
                double sightingDuration = Double.parseDouble(parts[8].trim());

                TimeSighting ts = new TimeSighting(
                    sightingId, speciesName, location, dateSpotted, observerName,
                    criticallyEndangered, timeSpotted, nocturnal, sightingDuration
                );
                queue.enqueue(ts);
            }
        }
        } catch (IOException e) {
        e.printStackTrace();
        }
    return queue;
    }

    static void deleteFileCE(Queue CE) {
        CRITICALLYENDANGEREDSIGHTING ce;
        try {
            Files.deleteIfExists(Paths.get("critically_endangered.txt"));
        } catch (Exception e) {
        }
        File file = new File("critically_endangered.txt");

        try {
            boolean value = file.createNewFile();
            if (value) {
                System.out.println("New file created successfully.");
            } else {
                System.out.println("File already exists.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if (CE.isEmpty()) {
            System.out.println("Queue is empty, nothing to write.");
        } else {
            Queue tempQueue = new Queue();
            while (!CE.isEmpty()) {
                ce = (CRITICALLYENDANGEREDSIGHTING) CE.dequeue();
                tempQueue.enqueue(ce);
                try (PrintWriter writeCE = new PrintWriter(
                        new BufferedWriter(new FileWriter("critically_endangered.txt", true)))) {
                    writeCE.println(ce.getSightingid() + "," + ce.getSpeciesName() + "," + ce.getLocation() + ","
                            + ce.getDateSpotted() + "," + ce.getObserverName() + "," + ce.isCriticallyEndangered() + ","
                            + ce.getMonitoringFrequency() + "," + ce.isRescued());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
            // Restore original queue
            while (!tempQueue.isEmpty()) {
                CE.enqueue(tempQueue.dequeue());
            }
        }
    }

    static void deleteFileES(Queue ES) {
        ENDANGEREDSIGHTING es;
        try {
            Files.deleteIfExists(Paths.get("endangered_sighting.txt"));
        } catch (Exception e) {
        }
        File file = new File("endangered_sighting.txt");

        try {
            boolean value = file.createNewFile();
            if (value) {
                System.out.println("New file created successfully.");
            } else {
                System.out.println("File already exists.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if (ES.isEmpty()) {
            System.out.println("Queue is empty, nothing to write.");
        } else {
            Queue tempQueue = new Queue();
            while (!ES.isEmpty()) {
                es = (ENDANGEREDSIGHTING) ES.dequeue();
                tempQueue.enqueue(es);
                try (PrintWriter writeES = new PrintWriter(
                        new BufferedWriter(new FileWriter("endangered_sighting.txt", true)))) {
                    writeES.println(es.getSightingid() + "," + es.getSpeciesName() + "," + es.getLocation() + ","
                            + es.getDateSpotted() + "," + es.getObserverName() + "," + es.isCriticallyEndangered());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
            // Restore original queue
            while (!tempQueue.isEmpty()) {
                ES.enqueue(tempQueue.dequeue());
            }
        }
    }

    static Queue filterCriticallyEndangeredFromSightings(Queue sightingQueue) {
        Queue criticallyEndangeredQueue = new Queue();
        Queue tempQueue = new Queue();
        
        while (!sightingQueue.isEmpty()) {
            SIGHTING sighting = (SIGHTING) sightingQueue.dequeue();
            tempQueue.enqueue(sighting);
            
            if (sighting.isCriticallyEndangered()) {
                CRITICALLYENDANGEREDSIGHTING ce = new CRITICALLYENDANGEREDSIGHTING(
                        sighting.getSightingid(),
                        sighting.getSpeciesName(),
                        sighting.getLocation(),
                        sighting.getDateSpotted(),
                        sighting.getObserverName(),
                        sighting.isCriticallyEndangered(),
                        "Monthly",
                        false);
                criticallyEndangeredQueue.enqueue(ce);
            }
        }
        
        // Restore original queue
        while (!tempQueue.isEmpty()) {
            sightingQueue.enqueue(tempQueue.dequeue());
        }
        
        return criticallyEndangeredQueue;
    }

    public static String PrintCE(Queue queue) {
        String out = "";
        CRITICALLYENDANGEREDSIGHTING ce;
        Queue tempQueue = new Queue();
        
        if (queue.isEmpty())
            out += "No data found";
        else {
            while (!queue.isEmpty()) {
                ce = (CRITICALLYENDANGEREDSIGHTING) queue.dequeue();
                out += ce.toString();
                tempQueue.enqueue(ce);
            }
            
            // Restore original queue
            while (!tempQueue.isEmpty()) {
                queue.enqueue(tempQueue.dequeue());
            }
        }
        return out;
    }

    public static String PrintTime(Queue queue) {
        String out = "";
        TimeSighting ts;
        Queue tempQueue = new Queue();

        if (queue.isEmpty()) {
            out += "No data found";
        } else {
            while (!queue.isEmpty()) {
                ts = (TimeSighting) queue.dequeue();
                out += ts.toString();
                tempQueue.enqueue(ts);
            }

            // Restore original queue
            while (!tempQueue.isEmpty()) {
                queue.enqueue(tempQueue.dequeue());
            }
        }

        return out;
    }

    static void sortByName(Queue queue) {
        if (queue.isEmpty()) {
            System.out.println("No data to sort.");
            return;
        }

        // Convert queue to array for easier sorting
        ArrayList<CRITICALLYENDANGEREDSIGHTING> tempList = new ArrayList<>();
        while (!queue.isEmpty()) {
            tempList.add((CRITICALLYENDANGEREDSIGHTING) queue.dequeue());
        }

        // Bubble sort based on species name
        for (int i = 0; i < tempList.size() - 1; i++) {
            for (int j = 0; j < tempList.size() - i - 1; j++) {
                String name1 = tempList.get(j).getSpeciesName();
                String name2 = tempList.get(j + 1).getSpeciesName();

                if (name1.compareToIgnoreCase(name2) > 0) {
                    // Swap
                    CRITICALLYENDANGEREDSIGHTING temp = tempList.get(j);
                    tempList.set(j, tempList.get(j + 1));
                    tempList.set(j + 1, temp);
                }
            }
        }

        // Rebuild the queue with sorted items
        for (CRITICALLYENDANGEREDSIGHTING ce : tempList) {
            queue.enqueue(ce);
        }

        System.out.println("Species sorted alphabetically by name.");
    }

    static double calcavg(Queue queue) {
        if (queue.isEmpty()) {
            return 0.0;
        }

        double totalDuration = 0.0;
        int count = 0;
        Queue tempQueue = new Queue();

        while (!queue.isEmpty()) {
            TimeSighting ts = (TimeSighting) queue.dequeue();
            totalDuration += ts.getSightingDuration();
            count++;
            tempQueue.enqueue(ts);
        }

        // Restore original queue
        while (!tempQueue.isEmpty()) {
            queue.enqueue(tempQueue.dequeue());
        }

        return totalDuration / count;
    }

    static double calctotal(Queue queue) {
        if (queue.isEmpty()) {
            return 0.0;
        }

        double totalDuration = 0.0;
        Queue tempQueue = new Queue();

        while (!queue.isEmpty()) {
            TimeSighting ts = (TimeSighting) queue.dequeue();
            totalDuration += ts.getSightingDuration();
            tempQueue.enqueue(ts);
        }

        // Restore original queue
        while (!tempQueue.isEmpty()) {
            queue.enqueue(tempQueue.dequeue());
        }

        return totalDuration;
    }

    static void writeNewEndangeredSighting(ENDANGEREDSIGHTING data) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("endangered_sighting.txt", true)))) {
            writer.println(
                data.getSightingid() + "," +
                data.getSpeciesName() + "," +
                data.getLocation() + "," +
                data.getDateSpotted() + "," +
                data.getObserverName() + "," +
                data.isCriticallyEndangered()
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void searchEndangeredSighting(Queue queue, String searchID) {
        String out = "";
        ENDANGEREDSIGHTING es;
        boolean found = false;
        Queue tempQueue = new Queue();
        
        if (queue.isEmpty())
            out += "No data found";
        else {
            while (!queue.isEmpty()) {
                es = (ENDANGEREDSIGHTING) queue.dequeue();
                tempQueue.enqueue(es);
                if (es.getSightingid().equals(searchID)) {
                    out += es.toString();
                    found = true;
                }
            }
            
            // Restore original queue
            while (!tempQueue.isEmpty()) {
                queue.enqueue(tempQueue.dequeue());
            }
        }
        
        if (!found) {
            System.out.println("No data found for Sighting ID: " + searchID);
        } else {
            System.out.println(out);
        }
    }

    static ENDANGEREDSIGHTING newEndangeredSighting(Scanner in) {
        Scanner input = new Scanner(System.in);

        System.out.println("Endangered Species Sighting Management - Add New Sighting");
        System.out.println("------------------------------------------------");
        System.out.print("Enter sighting ID:  ");
        String sightingId = input.nextLine();
        System.out.print("Enter species name: ");
        String speciesName = input.nextLine();
        System.out.print("Enter location: ");
        String location = input.nextLine();
        System.out.print("Enter date spotted (dd/mm/yyyy): ");
        String dateSpotted = input.nextLine();
        System.out.print("Enter observer name: ");
        String observerName = input.nextLine();
        System.out.print("Is the species critically endangered? (true/false): ");
        boolean criticallyEndangered = input.nextBoolean();
        System.out.println("Enter threat level: ");
        String threatLevel = input.next();
        System.out.println("Enter population estimate: ");
        int populationEstimate = input.nextInt();
    

        ENDANGEREDSIGHTING data = new ENDANGEREDSIGHTING(sightingId, speciesName, location, dateSpotted, observerName, criticallyEndangered, threatLevel, populationEstimate);

        return data;
    }
    
    static Queue getEndangeredSightingInfo() {
        Queue queue = new Queue();
        try (BufferedReader br = new BufferedReader(new FileReader("endangered_sighting.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    ENDANGEREDSIGHTING es = new ENDANGEREDSIGHTING(
                        parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim(),
                        parts[4].trim(), Boolean.parseBoolean(parts[5].trim()), "", 0
                    );
                    queue.enqueue(es);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return queue;
    }
    
    static Queue filterEndangeredFromSightings(Queue sightingQueue) {
        Queue endangeredQueue = new Queue();
        Queue tempQueue = new Queue();
        
        while (!sightingQueue.isEmpty()) {
            SIGHTING sighting = (SIGHTING) sightingQueue.dequeue();
            tempQueue.enqueue(sighting);
            
            if (!sighting.isCriticallyEndangered()) {
                ENDANGEREDSIGHTING es = new ENDANGEREDSIGHTING(
                    sighting.getSightingid(),
                    sighting.getSpeciesName(),
                    sighting.getLocation(),
                    sighting.getDateSpotted(),
                    sighting.getObserverName(),
                    sighting.isCriticallyEndangered(),
                    "Unknown",
                    0
                );
                endangeredQueue.enqueue(es);
            }
        }
        
        // Restore original queue
        while (!tempQueue.isEmpty()) {
            sightingQueue.enqueue(tempQueue.dequeue());
        }
        
        return endangeredQueue;
    }

    public static String PrintEndangered(Queue queue) {
        String out = "";
        ENDANGEREDSIGHTING es;
        Queue tempQueue = new Queue();
        
        if (queue.isEmpty())
            out += "No data found";
        else {
            while (!queue.isEmpty()) {
                es = (ENDANGEREDSIGHTING) queue.dequeue();
                out += es.toString();
                tempQueue.enqueue(es);
            }
            
            // Restore original queue
            while (!tempQueue.isEmpty()) {
                queue.enqueue(tempQueue.dequeue());
            }
        }
        return out;
    }

    static void sortByNameENDANGERED(Queue queue) {
        if (queue.isEmpty()) {
            System.out.println("No data to sort.");
            return;
        }

        ArrayList<ENDANGEREDSIGHTING> tempList = new ArrayList<>();
        while (!queue.isEmpty()) {
            tempList.add((ENDANGEREDSIGHTING) queue.dequeue());
        }

        // Bubble sort based on species name
        for (int i = 0; i < tempList.size() - 1; i++) {
            for (int j = 0; j < tempList.size() - i - 1; j++) {
                String name1 = tempList.get(j).getSpeciesName();
                String name2 = tempList.get(j + 1).getSpeciesName();

                if (name1.compareToIgnoreCase(name2) > 0) {
                    // Swap
                    ENDANGEREDSIGHTING temp = tempList.get(j);
                    tempList.set(j, tempList.get(j + 1));
                    tempList.set(j + 1, temp);
                }
            }
        }

        // Rebuild the queue with sorted items
        for (ENDANGEREDSIGHTING es : tempList) {
            queue.enqueue(es);
        }

        System.out.println("Species sorted alphabetically by name.");
    }
}