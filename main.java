import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;
public class main {
     public static void clrscr() throws Exception           
	{                                                   //method to return screen back to main menu
		Scanner sc = new Scanner(System.in);

		System.out.print("\n\n\n<< Press ENTER to continue >> ");
		sc.nextLine();
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	}
    static void mainmenu(){
        System.out.println("\n\nWelcome to Endangered Species Management System");
        System.out.println("------------------------------------------------");
        System.out.println("1. Critically Endangered Species Management System");
        System.out.println("2. Endangered Species Sighting Management System");
        System.out.println("3. See Time of Sighting ");
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
        System.out.println("5. Back to Main Menu");
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
        System.out.println("5. Back to Main Menu");
        System.out.println("------------------------------------------------");
        System.out.print("Please select an option: ");
        int response = scan.nextInt();
        return response;
    }
    static int SeeTimeOfSighting(Scanner scan) {
        System.out.println("\n\nSee Time of Sighting");
        System.out.println("------------------------------------------------");
        System.out.println("1. Add Time of Sighting");
        System.out.println("2. View Time of Sightings");
        System.out.println("3. Update Time of Sighting");
        System.out.println("4. Delete Time of Sighting");
        System.out.println("5. Back to Main Menu");
        System.out.println("------------------------------------------------");
        System.out.print("Please select an option: ");
        int response = scan.nextInt();
        return response;
    }

   static LinkedList getSightingInfo(){
    LinkedList list = new LinkedList();
    BufferedReader in;
    String read = null;
    StringTokenizer st;
        try {
            in = new BufferedReader(new FileReader("sighting.txt"));
            while((read = in.readLine()) != null) {
                st = new StringTokenizer(read, ",");
                String sightingId = st.nextToken().trim();
                String speciesName = st.nextToken().trim();
                String location = st.nextToken().trim();
                String dateSpotted = st.nextToken().trim();
                String observerName = st.nextToken().trim();
                boolean criticallyEndangered = Boolean.parseBoolean(st.nextToken().trim());

                SIGHTING sighting = new SIGHTING(sightingId, speciesName, location, dateSpotted, observerName, criticallyEndangered);
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
        Scanner scan = new Scanner(System.in);
        LinkedList sightingList = getSightingInfo();
        LinkedList criticallyEndangeredList = new LinkedList();
        LinkedList endangeredSpeciesSightingList = new LinkedList();
        LinkedList timeSightingList = new LinkedList();

        for(;;){
            mainmenu();
            int option = scan.nextInt();
            switch(option) {
                case 1:
                   int submenu =  CriticallyEndangeredSpeciesManagementSystem(scan);
                   switch(submenu) {
                       case 1:
                          CRITICALLYENDANGEREDSIGHTING csighting = newcList(input);
                          break;
                       case 2:
                           // Handle searching critically endangered species
                           break;
                       case 3:
                           // Handle deleting a critically endangered species
                           break;
                       case 4:
                           // Handle listing critically endangered species
                           break;
                       case 5:
                           // Back to main menu
                           break;
                       default:
                           System.out.println("Invalid option. Please try again.");
                   }
                    break;
                case 2:
                    int submenu2 = EndangeredSpeciesSightingManagementSystem(scan);
                    switch(submenu2) {
                        case 1:
                            // Handle adding an endangered species sighting
                            break;
                        case 2:
                            // Handle viewing endangered species sightings
                            break;
                        case 3:
                            // Handle updating an endangered species sighting
                            break;
                        case 4:
                            // Handle deleting an endangered species sighting
                            break;
                        case 5:
                            // Back to main menu
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                    break;
                case 3:
                    int submenu3 = SeeTimeOfSighting(scan);
                    switch(submenu3) {
                        case 1:
                            // Handle adding a time of sighting
                            break;
                        case 2:
                            // Handle viewing time of sightings
                            break;
                        case 3:
                            // Handle updating a time of sighting
                            break;
                        case 4:
                            // Handle deleting a time of sighting
                            break;
                        case 5:
                            // Back to main menu
                            break;
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
    static CRITICALLYENDANGEREDSIGHTING newcList(Scanner in){
        return data;
    } 
        
}
