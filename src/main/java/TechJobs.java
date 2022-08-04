import java.util.*;


public class TechJobs {

    public static Scanner in = new Scanner(System.in);

    public static void main (String[] args) {

        HashMap<String, String> columnChoices = new HashMap<>();
        columnChoices.put("core competency", "Skill");
        columnChoices.put("employer", "Employer");
        columnChoices.put("location", "Location");
        columnChoices.put("position type", "Position Type");
        columnChoices.put("all", "All");


        HashMap<String, String> actionChoices = new HashMap<>();
        actionChoices.put("search", "Search");
        actionChoices.put("list", "List");

        System.out.println("Welcome to LaunchCode's TechJobs App!");

        while (true) {

            String actionChoice = getUserSelection("View jobs by:", actionChoices);

            if (actionChoice.equals("list")) {

                String columnChoice = getUserSelection("List", columnChoices);

                if (columnChoice.equals("all")) {
                    printJobs(JobData.findAll());
                } else {

                    ArrayList<String> results = JobData.findAll(columnChoice);

                    System.out.println("\n*** All " + columnChoices.get(columnChoice) + " Values ***");

                    for (String item : results) {
                        System.out.println(item);
                    }
                }

            } else {
                String searchField = getUserSelection("Search by:", columnChoices);



                System.out.println("\nSearch term: ");
                String searchTerm = in.nextLine().toUpperCase();


                if (searchField.equals("all")) {

                    printJobs(findByValue(searchTerm));
                } else {
                    printJobs(JobData.findByColumnAndValue(searchField, searchTerm));
                }
            }
        }
    }

    private static String getUserSelection(String menuHeader, HashMap<String, String> choices) {

        int choiceIdx;
        boolean validChoice = false;
        String[] choiceKeys = new String[choices.size()];


        int i = 0;
        for (String choiceKey : choices.keySet()) {
            choiceKeys[i] = choiceKey;
            i++;
        }

        do {

            System.out.println("\n" + menuHeader);


            for (int j = 0; j < choiceKeys.length; j++) {
                System.out.println("" + j + " - " + choices.get(choiceKeys[j]));
            }

            choiceIdx = in.nextInt();
            in.nextLine();
            if (choiceIdx < 0 || choiceIdx >= choiceKeys.length) {
                System.out.println("Invalid choice. Try again.");
            } else {
                validChoice = true;
            }

        } while(!validChoice);

        return choiceKeys[choiceIdx];
    }


    private static void printJobs(ArrayList<HashMap<String, String>> jobs) {

        if (jobs.size() > 0 ) {


            for (HashMap<String, String> field : jobs) {

                for(Map.Entry<String, String> data: field.entrySet()){
                    System.out.println(data.getKey() + ": "+data.getValue() );

                }
                System.out.println("*********************");
            }

        } else System.out.println("Sorry there is nothing that matches your search");
    }


    public static ArrayList<HashMap<String, String>> findByValue(String searchTerm){

        ArrayList<HashMap<String, String>> allJobs = JobData.findAll();
        ArrayList<HashMap<String, String>> items = new ArrayList<>();

        for (HashMap<String,String> row: allJobs){

            for(Map.Entry<String, String> column: row.entrySet()){
                if (column.getValue().toUpperCase().contains(searchTerm)){
                    if(Objects.equals(items, row)){
                        continue;
                    }
                    items.add(row);
                }
            }

k

        }

        return items;


    }
}