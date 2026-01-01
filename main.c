#include <stdio.h>
#include <string.h>

#define MAX 100

/* =========================
   STRUCT DEFINITIONS
   ========================= */

typedef struct {
    char sightingId[20];
    char speciesName[50];
    char location[50];
    char dateSpotted[15];
    char observerName[50];
    int criticallyEndangered;   // 1 = yes, 0 = no
} Sighting;

typedef struct {
    Sighting base;
    char monitoringFrequency[20];
    int rescued;
} CESighting;

typedef struct {
    Sighting base;
    char threatLevel[20];
    int populationEstimate;
} ESighting;

typedef struct {
    Sighting base;
    double timeSpotted;
    int nocturnal;
    double sightingDuration;
} TimeSighting;

/* =========================
   FUNCTION PROTOTYPES
   ========================= */

void menu();

void addCE(CESighting list[], int *count);
void addES(ESighting list[], int *count);
void addTime(TimeSighting list[], int *count);

void listCE(CESighting list[], int count);
int searchCE(CESighting list[], int count, char id[]);

/* =========================
   MAIN FUNCTION
   ========================= */

int main() {

    CESighting ceList[MAX];
    ESighting esList[MAX];
    TimeSighting timeList[MAX];

    int ceCount = 0, esCount = 0, timeCount = 0;
    int choice;
    char searchId[20];

    do {
        menu();
        printf("Enter choice: ");
        scanf("%d", &choice);

        switch(choice) {

            case 1:
                addCE(ceList, &ceCount);
                break;

            case 2:
                listCE(ceList, ceCount);
                break;

            case 3:
                printf("Enter Sighting ID to search: ");
                scanf("%s", searchId);

                if (searchCE(ceList, ceCount, searchId) != -1)
                    printf("Record found.\n");
                else
                    printf("Record not found.\n");
                break;

            case 0:
                printf("Exiting program...\n");
                break;

            default:
                printf("Invalid choice.\n");
        }

    } while(choice != 0);

    return 0;
}

/* =========================
   MENU FUNCTION
   ========================= */

void menu() {
    printf("\n===== WILDLIFE SIGHTING SYSTEM =====\n");
    printf("1. Add Critically Endangered Sighting\n");
    printf("2. List Critically Endangered Sightings\n");
    printf("3. Search Critically Endangered Sighting\n");
    printf("0. Exit\n");
}

/* =========================
   ADD FUNCTIONS
   ========================= */

void addCE(CESighting list[], int *count)
/* list  → IN
   count → IN / OUT */
{
    printf("Sighting ID: ");
    scanf("%s", list[*count].base.sightingId);

    printf("Species Name: ");
    scanf(" %[^\n]", list[*count].base.speciesName);

    printf("Location: ");
    scanf(" %[^\n]", list[*count].base.location);

    printf("Date Spotted: ");
    scanf("%s", list[*count].base.dateSpotted);

    printf("Observer Name: ");
    scanf(" %[^\n]", list[*count].base.observerName);

    list[*count].base.criticallyEndangered = 1;

    printf("Monitoring Frequency: ");
    scanf("%s", list[*count].monitoringFrequency);

    printf("Rescued (1=yes, 0=no): ");
    scanf("%d", &list[*count].rescued);

    (*count)++;

    printf("Critically Endangered Sighting added.\n");
}

void addES(ESighting list[], int *count)
/* list → IN
   count → IN / OUT */
{
    /* Same logic as addCE (not shown to reduce length) */
}

void addTime(TimeSighting list[], int *count)
/* list → IN
   count → IN / OUT */
{
    /* Same logic as addCE (not shown to reduce length) */
}

/* =========================
   LIST FUNCTION
   ========================= */

void listCE(CESighting list[], int count)
/* list  → IN
   count → IN */
{
    int i;

    if (count == 0) {
        printf("No records available.\n");
        return;
    }

    for (i = 0; i < count; i++) {
        printf("\n--- Record %d ---\n", i + 1);
        printf("ID: %s\n", list[i].base.sightingId);
        printf("Species: %s\n", list[i].base.speciesName);
        printf("Location: %s\n", list[i].base.location);
        printf("Observer: %s\n", list[i].base.observerName);
        printf("Monitoring: %s\n", list[i].monitoringFrequency);
        printf("Rescued: %d\n", list[i].rescued);
    }
}

/* =========================
   SEARCH FUNCTION
   ========================= */

int searchCE(CESighting list[], int count, char id[])
/* list  → IN
   count → IN
   id    → IN */
{
    int i;

    for (i = 0; i < count; i++) {
        if (strcmp(list[i].base.sightingId, id) == 0)
            return i;
    }

    return -1;
}
