import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

public class LinkedListMain {

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

    static LinkedList getSightingInfo() {
        LinkedList list = new LinkedList();
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
                list.insertAtBack(sighting);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        try {
            Scanner input = new Scanner(System.in);
            LinkedList sightingList = getSightingInfo();
            LinkedList criticallyEndangeredList = getCriticallyEndangeredInfo();
            criticallyEndangeredList = filterCriticallyEndangeredFromSightings(sightingList);
            LinkedList endangeredSpeciesSightingList = filterEndangeredFromSightings(sightingList);
            LinkedList timeSightingList = autoFillTimeSighting(sightingList);

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
                                criticallyEndangeredList.insertAtBack(csighting);
                                break;
                            case 2:
                                System.out.println("\n\nCritically Endangered Species Management - Search Species");
                                System.out.println("------------------------------------------------");
                                input.nextLine();
                                System.out.print("Enter species id to search: ");
                                String speciesID = input.nextLine();
                                searchCE(criticallyEndangeredList, speciesID);
                                break;
                            case 3:
                                System.out.println("\n\nCritically Endangered Species Management - Delete Species");
                                System.out.println("------------------------------------------------");
                                input.nextLine();
                                System.out.print("Enter sighting id to delete: ");
                                String SightingID = input.nextLine();
                                CRITICALLYENDANGEREDSIGHTING ce;
                                LinkedList tempCE = new LinkedList();
                                if (criticallyEndangeredList == null)
                                    System.out.println("No data found");
                                else {
                                    Object data = criticallyEndangeredList.getFirst();
                                    while (data != null) {
                                        ce = (CRITICALLYENDANGEREDSIGHTING) data;
                                        if (ce.getSightingid().equals(SightingID)) {
                                            System.out.println(
                                                    "Sighting with ID " + SightingID + " deleted successfully.");
                                        } else {
                                            tempCE.insertAtBack(data);
                                        }
                                        data = criticallyEndangeredList.getNext();
                                    }
                                }
                                if (tempCE == null)
                                    System.out.println("No data found");
                                else {
                                    Object data = tempCE.getFirst();
                                    while (data != null) {
                                        ce = (CRITICALLYENDANGEREDSIGHTING) data;
                                        criticallyEndangeredList.insertAtBack(ce);
                                        data = tempCE.getNext();
                                    }
                                }
                                deleteFileCE(criticallyEndangeredList);
                                break;
                            case 4:
                                System.out.println("\n\nCritically Endangered Species Management - List Species");
                                System.out.println("------------------------------------------------");
                                System.out.println(
                                        "Sighting ID | Species Name          | Location             | Date Spotted|Observer Name|Critically Endangered|Monitoring Frequency|Rescued");
                                System.out.print(PrintCE(criticallyEndangeredList));
                                break;
                            case 5:
                                sortByName(criticallyEndangeredList);
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
                                System.out.println("Average duration Time of sighting: "+ String.format("%.2f", calcavg(timeSightingList)) + " hours");
                                break;
                            case 2:
                                System.out.println("\n\nCalculate Total Time Sighting");
                                System.out.println("Total duration Time of sighting: " + String.format("%.2f", calctotal(timeSightingList)) + " hours");
                                break;
                            case 3:
                               System.out.println("\n\nTime of Sighting Management - List Sightings");
                               System.out.println("------------------------------------------------");
                               System.out.println(
                                       "Sighting ID | Species Name          | Location             | Date Spotted|Observer Name|Critically Endangered|Time Spotted|Nocturnal|Sighting Duration");
                               System.out.print(PrintTime(timeSightingList));
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
                                endangeredSpeciesSightingList.insertAtBack(esighting);
                                System.out.println("Data added successfully!");
                                break;
                            case 2:
                                System.out.println("\n\nEndangered Species Sighting Management - Search species");
                                System.out.println("------------------------------------------------");
                                input.nextLine();
                                System.out.print("Enter species id to search: ");
                                String searchID = input.nextLine();
                                searchEndangeredSighting(endangeredSpeciesSightingList, searchID);
                                break;
                            case 3:
                                System.out.println("\n\nEndangered Species Sighting Management - Delete species");
                                System.out.println("------------------------------------------------");
                                input.nextLine();
                                System.out.print("Enter sighting id to delete: ");
                                String sightingID = input.nextLine();
                                ENDANGEREDSIGHTING es;
                                LinkedList tempES = new LinkedList();
                                if (endangeredSpeciesSightingList == null)
                                    System.out.println("No data found");
                                else {
                                    Object data = endangeredSpeciesSightingList.getFirst();
                                    while (data != null) {
                                        es = (ENDANGEREDSIGHTING) data;
                                        if (es.getSightingid().equals(sightingID)) {
                                            System.out.println(
                                                    "Sighting with ID " + sightingID + " deleted successfully.");
                                        } else {
                                            tempES.insertAtBack(data);
                                        }
                                        data = endangeredSpeciesSightingList.getNext();
                                    }
                                }
                                endangeredSpeciesSightingList = tempES;
                                if (tempES == null)
                                    System.out.println("No data found");
                                else {
                                    Object data = tempES.getFirst();
                                    while (data != null) {
                                        es = (ENDANGEREDSIGHTING) data;
                                        endangeredSpeciesSightingList.insertAtBack(es);
                                        data = tempES.getNext();
                                    }
                                }
                                deleteFileES(endangeredSpeciesSightingList);
                                break;
                            case 4:
                                System.out.println("\n\nEndangered Species Sighting Management - List Sightings");
                                System.out.println("------------------------------------------------");
                                System.out.println(
                                        "Sighting ID | Species Name          | Location             | Date Spotted|Observer Name|Critically Endangered|Threat Level|Population Estimate");
                                System.out.print(PrintEndangered(endangeredSpeciesSightingList));
                                break;
                            case 5:
                               sortByNameENDANGERED(endangeredSpeciesSightingList);
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

    static void searchCE(LinkedList searchList, String searchID) {
        String out = "";
        CRITICALLYENDANGEREDSIGHTING ce;
        boolean found = false;
        if (searchList == null)
            out += "No data found";
        else {
            Object data = searchList.getFirst();
            while (data != null) {
                ce = (CRITICALLYENDANGEREDSIGHTING) data;
                if (ce.getSightingid().equals(searchID)) {
                    out += ce.toString();
                    found = true;
                }
                data = searchList.getNext();
            }
        }
        if (!found) {
            System.out.println("No data found for Sighting ID: " + searchID);
        } else {
            System.out.println(out);
        }
    }

    static LinkedList getCriticallyEndangeredInfo() {
        LinkedList list = new LinkedList();
        try (BufferedReader br = new BufferedReader(new FileReader("critically_endangered.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 8) {
                    CRITICALLYENDANGEREDSIGHTING ce = new CRITICALLYENDANGEREDSIGHTING(
                            parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim(),
                            parts[4].trim(), Boolean.parseBoolean(parts[5].trim()),
                            parts[6].trim(), Boolean.parseBoolean(parts[7].trim()));
                    list.insertAtBack(ce);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    static LinkedList autoFillTimeSighting(LinkedList sightingList) {
        LinkedList timeList = new LinkedList();
        Object data = sightingList.getFirst();
        Random rand = new Random();

        while (data != null) {
            SIGHTING s = (SIGHTING) data;

            int hour = rand.nextInt(24);
            int minute = rand.nextInt(60);
            double timeSpotted = hour + (minute / 100.0);
            boolean nocturnal = rand.nextBoolean();
            double duration = Math.round((0.5 + rand.nextDouble() * 4.5) * 10.0) / 10.0;

            TimeSighting ts = new TimeSighting(
                s.getSightingid(), s.getSpeciesName(), s.getLocation(),
                s.getDateSpotted(), s.getObserverName(), s.isCriticallyEndangered(),
                timeSpotted, nocturnal, duration
            );

            writeNewTime(ts);
            timeList.insertAtBack(ts);
            data = sightingList.getNext();
        }

        return timeList;
    }

    static void deleteFileCE(LinkedList CE) {
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
        
        if (CE == null) {
            System.out.println("List is empty, nothing to write.");
        } else {
            Object data = CE.getFirst();
            while (data != null) {
                ce = (CRITICALLYENDANGEREDSIGHTING) data;
                try (PrintWriter writeCE = new PrintWriter(
                        new BufferedWriter(new FileWriter("critically_endangered.txt", true)))) {
                    writeCE.println(ce.getSightingid() + "," + ce.getSpeciesName() + "," + ce.getLocation() + ","
                            + ce.getDateSpotted() + "," + ce.getObserverName() + "," + ce.isCriticallyEndangered() + ","
                            + ce.getMonitoringFrequency() + "," + ce.isRescued());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                data = CE.getNext();
            }
        }
    }

    static void deleteFileES(LinkedList ES) {
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
        
        if (ES == null) {
            System.out.println("List is empty, nothing to write.");
        } else {
            Object data = ES.getFirst();
            while (data != null) {
                es = (ENDANGEREDSIGHTING) data;
                try (PrintWriter writeES = new PrintWriter(
                        new BufferedWriter(new FileWriter("endangered_sighting.txt", true)))) {
                    writeES.println(es.getSightingid() + "," + es.getSpeciesName() + "," + es.getLocation() + ","
                            + es.getDateSpotted() + "," + es.getObserverName() + "," + es.isCriticallyEndangered());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                data = ES.getNext();
            }
        }
    }

    static LinkedList filterCriticallyEndangeredFromSightings(LinkedList sightingList) {
        LinkedList criticallyEndangeredList = new LinkedList();
        if (sightingList == null) {
            return criticallyEndangeredList;
        }
        
        Object data = sightingList.getFirst();
        while (data != null) {
            SIGHTING sighting = (SIGHTING) data;
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
                criticallyEndangeredList.insertAtBack(ce);
            }
            data = sightingList.getNext();
        }
        
        return criticallyEndangeredList;
    }

    public static String PrintCE(LinkedList List) {
        String out = "";
        CRITICALLYENDANGEREDSIGHTING ce;
        if (List == null)
            out += "No data found";
        else {
            Object data = List.getFirst();
            while (data != null) {
                ce = (CRITICALLYENDANGEREDSIGHTING) data;
                out += ce.toString();
                data = List.getNext();
            }
        }
        return out;
    }

    public static String PrintTime(LinkedList list) {
        String out = "";
        TimeSighting ts;
        if (list == null) {
            out += "No data found";
        } else {
            Object data = list.getFirst();
            while (data != null) {
                ts = (TimeSighting) data;
                out += ts.toString();
                data = list.getNext();
            }
        }
        return out;
    }

    static void sortByName(LinkedList list) {
        if (list == null || list.getFirst() == null) {
            System.out.println("No data to sort.");
            return;
        }

        ArrayList<CRITICALLYENDANGEREDSIGHTING> tempList = new ArrayList<>();
        Object current = list.getFirst();

        while (current != null) {
            tempList.add((CRITICALLYENDANGEREDSIGHTING) current);
            current = list.getNext();
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

        // Clear original list and re-insert sorted items
        while (list.getFirst() != null) {
            list.removeFromFront();
        }

        for (CRITICALLYENDANGEREDSIGHTING ce : tempList) {
            list.insertAtBack(ce);
        }

        System.out.println("Species sorted alphabetically by name.");
    }

    static double calcavg(LinkedList list) {
        if (list == null || list.getFirst() == null) {
            return 0.0;
        }

        double totalDuration = 0.0;
        int count = 0;
        Object data = list.getFirst();

        while (data != null) {
            TimeSighting ts = (TimeSighting) data;
            totalDuration += ts.getSightingDuration();
            count++;
            data = list.getNext();
        }

        return totalDuration / count;
    }

    static double calctotal(LinkedList list) {
        if (list == null || list.getFirst() == null) {
            return 0.0;
        }

        double totalDuration = 0.0;
        Object data = list.getFirst();

        while (data != null) {
            TimeSighting ts = (TimeSighting) data;
            totalDuration += ts.getSightingDuration();
            data = list.getNext();
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

    static void searchEndangeredSighting(LinkedList list, String searchID) {
        String out = "";
        ENDANGEREDSIGHTING es;
        boolean found = false;
        if (list == null)
            out += "No data found";
        else {
            Object data = list.getFirst();
            while (data != null) {
                es = (ENDANGEREDSIGHTING) data;
                if (es.getSightingid().equals(searchID)) {
                    out += es.toString();
                    found = true;
                }
                data = list.getNext();
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
    
    static LinkedList getEndangeredSightingInfo() {
        LinkedList list = new LinkedList();
        try (BufferedReader br = new BufferedReader(new FileReader("endangered_sighting.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    ENDANGEREDSIGHTING es = new ENDANGEREDSIGHTING(
                        parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim(),
                        parts[4].trim(), Boolean.parseBoolean(parts[5].trim()), "", 0
                    );
                    list.insertAtBack(es);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    static LinkedList filterEndangeredFromSightings(LinkedList sightingList) {
        LinkedList endangeredList = new LinkedList();
        if (sightingList == null) {
            return endangeredList;
        }
        
        Object data = sightingList.getFirst();
        while (data != null) {
            SIGHTING sighting = (SIGHTING) data;
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
                endangeredList.insertAtBack(es);
            }
            data = sightingList.getNext();
        }
        
        return endangeredList;
    }

    public static String PrintEndangered(LinkedList list) {
        String out = "";
        ENDANGEREDSIGHTING es;
        if (list == null)
            out += "No data found";
        else {
            Object data = list.getFirst();
            while (data != null) {
                es = (ENDANGEREDSIGHTING) data;
                out += es.toString();
                data = list.getNext();
            }
        }
        return out;
    }

    static void sortByNameENDANGERED(LinkedList list) {
        if (list == null || list.getFirst() == null) {
            System.out.println("No data to sort.");
            return;
        }

        ArrayList<ENDANGEREDSIGHTING> tempList = new ArrayList<>();
        Object current = list.getFirst();

        while (current != null) {
            tempList.add((ENDANGEREDSIGHTING) current);
            current = list.getNext();
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

        // Clear original list and re-insert sorted items
        while (list.getFirst() != null) {
            list.removeFromFront();
        }

        for (ENDANGEREDSIGHTING es : tempList) {
            list.insertAtBack(es);
        }

        System.out.println("Species sorted alphabetically by name.");
    }
}