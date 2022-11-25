import java.util.*;

public class Menu {

    Scanner input = new Scanner(System.in);
    private static boolean exit;

    ArrayList<String> menu = new ArrayList<>(); // ArrayList of Menu options
    HashMap<String, ArrayList<Task>> toDoLists; // Key = Name of To-Do List, Value = ArrayList of tasks

    public Menu() {
        exit = false;
        toDoLists = new HashMap<>(); // I used a HashMap as it allows me to easily access all tasks (the value) within any list (the key)

        // Populating To-Do lists.
        toDoLists.put("Personal", new ArrayList<>(Arrays.asList(new Task("Buy John a birthday present", "High", new Date()), new Task("Join the gym", "Medium", new Date()))));
        toDoLists.put("House", new ArrayList<>(Arrays.asList(new Task("Clean the oven", "High", new Date()), new Task("Buy new sofa", "Low", new Date()))));
    }

    // Adding options to the menu ArrayList
    public void setMenu() {
        menu.add("View To-Do lists"); // Includes ability to view individual To-Do lists.
        menu.add("Create a new To-Do List");
        menu.add("Add a task");
        menu.add("Delete a task");
        menu.add("Mark task as complete");
        menu.add("Exit");
    }

    public void displayMenu() {
        System.out.println("--------------");
        System.out.println("Main Menu");
        System.out.println("--------------");
        System.out.println("1: " + menu.get(0));
        System.out.println("2: " + menu.get(1));
        System.out.println("3: " + menu.get(2));
        System.out.println("4: " + menu.get(3));
        System.out.println("5: " + menu.get(4));
        System.out.println("0: " + menu.get(5));
        System.out.println();
        System.out.print("Enter an option: ");
    }

    public void setOption() { // User will choose a menu option and this method will execute it.
        int option = -1;
        while (option < 0 || option > 5) { // If user inputs an invalid integer, nothing will be executed until they input a valid number.
            try {
                option = input.nextInt(); // User's option input.
                input.nextLine(); // Avoid skipping first nextLine().
            } catch (Exception e) { // If user inputs anything other than an integer, an error message will be displayed. They will be able to try again.
                System.out.print("Please enter a valid number: "); // Error message.
                input.nextLine(); // Avoid skipping first nextLine().
            }
        }

        switch (option) {
            case 1: // View To-Do lists.
                boolean flag0 = false; // This boolean variable tracks if the user inputs a String which matches one of the keys (To-Do Lists)
                System.out.println("-------------");
                if (toDoLists.isEmpty()) { // Step 1 -> check if there are any keys (To-Do lists).
                    System.out.println("There are no lists.\n");
                } else {
                    for (String name : toDoLists.keySet()) { // Step 2 -> Print out all keys.
                        System.out.println(name);
                    }
                    System.out.println();
                    System.out.println("Be careful, the lists are case-sensitive.");
                    System.out.print("Which To-Do list would you like to view? "); // Step 3 -> Ask user which individual list they would like to view
                    String selection = input.nextLine().trim();
                    System.out.println();

                    for (String name : toDoLists.keySet()) {
                        if (name.equals(selection)) { // Step 4 -> If user enters a list name which matches a key...
                            ArrayList<Task> viewList = toDoLists.get(name); // Save values associated with that key to an ArrayList<Task>...
                            flag0 = true; // One of the keys matched the user input.

                            for (Task task : viewList) {
                                System.out.println(task); //... and display them in the format of our toString();
                            }
                        }
                    }
                    if (!flag0) { // None of the keys match the user input. This is purposely outside the 'for-each-loop' to avoid multiple error messages.
                        System.out.println("That list does not exist!");
                    }
                }
                break;
            case 2: // Create new To-Do list.
                String nameNewToDoList;
                System.out.println();
                System.out.println("Create a new To-Do list!");
                System.out.println("---------------");
                System.out.print("What would you like to call your new list? ");
                nameNewToDoList = input.nextLine().trim(); // Step 1 -> user input will be the name of the new list

                if (toDoLists.containsKey(nameNewToDoList)) { // Step 2 -> Checks if list name already exists
                    System.out.println();
                    System.out.println("To-Do list already exists!");
                } else {
                    toDoLists.put(nameNewToDoList, new ArrayList<>()); // Step 3-> adds new list name to HashMap as a Key, with an empty ArrayList as Value.
                    System.out.println();
                    System.out.println("New List created!");
                }
                break;

            case 3: // Add a task.
                boolean flag1 = false; // This boolean variable tracks if the user inputs a String which matches one of the keys (To-Do Lists)
                String nameOfToDoList;
                System.out.println();
                System.out.println("Add a new task!");
                System.out.println("---------------");
                for (String name : toDoLists.keySet()) { // Step 1 -> Displays all To-Do lists user can add a task to.
                    System.out.println(name);
                }
                System.out.println("---------------");
                System.out.println("Be careful, the lists are case-sensitive.");
                System.out.print("What is the name of the To-Do list you would like to add to? ");
                nameOfToDoList = input.nextLine().trim(); // Step 2 -> User types name of To-Do list they want to add to.

                for (String name : toDoLists.keySet()) {
                    if (name.equals(nameOfToDoList)) { // Step 3 -> Checks if To-Do list exists.
                        Task newTask = new Task(); // Step 4 -> Creates new Task object the user will add to.
                        System.out.println();
                        System.out.print("What is the name of the new task? ");
                        newTask.setName(input.nextLine());
                        System.out.println();
                        System.out.println("Recommended -> High/Medium/Low"); // User may decide to use a different priority measuring system, but I have suggested High/Medium/Low.
                        System.out.print("What is the priority of the new task? ");
                        newTask.setPriority(input.nextLine());

                        System.out.println("\n");

                        Date date = new Date(); // Creating Date object
                        newTask.setCreated(date);
                        newTask.setComplete(false); // Set to false.

                        ArrayList<Task> todoList = toDoLists.get(nameOfToDoList); // Step 5 -> Create variable of values associated to key selected earlier.
                        todoList.add(newTask); // Step 6 -> Adds new task as a value

                        System.out.println("You added a task!");
                        System.out.println("-----------------");

                        System.out.println("Name: " + newTask.getName().trim()); // Displays the task the user has just added.
                        System.out.println("Priority: " + newTask.getPriority().toUpperCase().trim()); // Converts priority to uppercase so user does not forget/miss the priority of a task, especially high priority tasks.
                        System.out.println("Date Created: " + newTask.getCreated() + "\n");
                        flag1 = true; // One of the keys matched the user input.
                        break;
                    }
                }
                if (!flag1) { // None of the keys match the user input. This is purposely outside the 'for-each-loop' to avoid multiple error messages
                    System.out.println();
                    System.out.println("To-Do list does not exist!");
                }
                break;

            case 4: // Delete a task.
                boolean flag2 = false; // This boolean variable tracks if the user inputs a String which matches one of the keys (To-Do Lists)
                String deleteFrom; // Which To-Do list to delete a task from
                String deleteTask;
                System.out.println();
                System.out.println("Delete a task!");
                System.out.println("---------------");
                for (String name : toDoLists.keySet()) { // Step 1 -> Displays all To-Do lists user can add a task to.
                    System.out.println(name);
                }
                System.out.println("---------------");
                System.out.println("Be careful, the lists are case-sensitive.");
                System.out.print("What is the name of the To-Do list you would like to delete a task from? ");
                deleteFrom = input.nextLine().trim(); // Step 2 -> User types name of To-Do list they want to delete a task from.

                if (toDoLists.containsKey(deleteFrom)) { // Step 3 -> Checks if To-Do list exists.
                    ArrayList<Task> removeFromList = toDoLists.get(deleteFrom); // Step 4 -> Create variable of values associated to key selected earlier.

                    for (Task task : removeFromList) { // Step 5 -> Display tasks from To-Do list
                        System.out.println(task.getName()); // Only the name of the tasks are displayed in case there are a large number of tasks...
                        System.out.println(); // ... it will be easier for the user to view and type in the correct task name to delete.
                    }

                    System.out.println("Be careful, the tasks are case-sensitive.");
                    System.out.print("Which task would you like to delete? ");
                    deleteTask = input.nextLine().trim(); // Step 5 -> User types which task they would like to delete.

                    for (Task task : removeFromList) {
                        if (task.getName().equals(deleteTask)) { // Step 6 -> Checking the task exists
                            removeFromList.remove(task); // Step 7 -> Removes task.
                            System.out.println();
                            System.out.println("You deleted a task!");
                            flag2 = true; // One of the key values matched the user input.
                            break;
                        }
                    }
                    if (!flag2) { // None of the values (tasks) associated with the key specific earlier match the user input. This is purposely outside the 'for-each-loop' to avoid multiple error messages
                        System.out.println();
                        System.out.println("Task does not exist!");
                    }
                } else {
                    System.out.println();
                    System.out.println("To-Do List does not exist."); // Error message if Step 3 fails -> User input does not match a key.
                }
                System.out.println("-----------------");
                break;

            case 5: // Mark task as complete.
                boolean flag3 = false; // This boolean variable tracks if the user inputs a String which matches one of the keys (To-Do Lists)
                String completeFrom; // Which To-Do list to complete a task from
                String completeTask;
                System.out.println();
                System.out.println("Complete a task!");
                System.out.println("---------------");

                for (String name : toDoLists.keySet()) { // Step 1 -> Displays all To-Do lists user can add a task to.
                    System.out.println(name);
                }
                System.out.println("---------------");
                System.out.println("Be careful, the lists are case-sensitive.");
                System.out.print("What is the name of the To-Do list you would like to complete a task from? ");
                completeFrom = input.nextLine().trim(); // Step 2 -> User types name of To-Do list they want to complete a task from.

                if (toDoLists.containsKey(completeFrom)) { // Step 3 -> Checks if To-Do list exists.
                    ArrayList<Task> completeFromList = toDoLists.get(completeFrom); // Step 4 -> Create variable of values associated to key selected earlier.

                    for (Task task : completeFromList) { // Step 5 -> Display tasks from To-Do list
                        System.out.println(task.getName()); // Only the name of the tasks are displayed in case there are a large number of tasks...
                        System.out.println(); // ... it will be easier for the user to view and type in the correct task name to delete.
                    }

                    System.out.println("Be careful, the tasks are case-sensitive.");
                    System.out.print("Which task would you like to complete? ");
                    completeTask = input.nextLine().trim(); // Step 5 -> User types which task they would like to mark as complete.

                    for (Task task : completeFromList) {
                        if (task.getName().equals(completeTask)) { // Step 6 -> Checking the task exists
                            task.setComplete(true); // Step 7 -> Marks task as complete.
                            System.out.println();
                            System.out.println("You marked a task as complete!");
                            flag3 = true; // One of the key values matched the user input.
                            break;
                        }
                    }
                    if (!flag3) { // None of the values (tasks) associated with the key specific earlier match the user input. This is purposely outside the 'for-each-loop' to avoid multiple error messages
                        System.out.println();
                        System.out.println("Task does not exist!");
                    }
                } else {
                    System.out.println();
                    System.out.println("To-Do List does not exist."); // Error message if Step 3 fails -> User input does not match a key.
                }
                System.out.println("-----------------");
                break;

            case 0: // Exit program.
                exit = true; // Amends boolean expression
                System.out.println("Goodbye!");
                break;
        }
    }

    public void startMenu() {
        Scanner input = new Scanner(System.in); //Creates input
        int option = -1; //Choice to continue making menu selections
        Menu menu = new Menu(); // Menu object
        menu.setMenu(); //Setting up the menu options -  View A List, Create A List etc.
        do {
            menu.displayMenu();
            menu.setOption(); // Prompts user to select a menu option
            if (!exit) { // If the user chooses an option other than exit...
                System.out.println(); //... Once they are finished with their selection...
                System.out.println("Return to Menu?"); //... They can return to the main menu...
                System.out.print("1 = Yes/ 2 = No: "); //... Or choose to exit the program.
                option = input.nextInt();
                input.nextLine();
                while (option < 0 || option > 2) {
                    try {
                        option = input.nextInt(); // User's option input.
                        input.nextLine(); // Avoid skipping first nextLine().
                    } catch (Exception e) {
                        System.out.print("Please enter a valid number! ");
                        input.nextLine(); // Avoid skipping first nextLine().
                    }
                }
                if (option == 2) {
                    System.out.println("Goodbye!");
                }
            }
        } while (option != 2 && !exit); // Keep re-displaying the Menu and Options if user inputs 1 (to return to Menu) and doesn't choose the exit option from Menu.
        input.close(); // Closing the scanner.
    }
}
