package lockedme;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class App {

	final static String FOLDER = "C:/NOTES";
	
	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		showMeWelcomeScreen();
		showMainMenu();
	}
	
	private static void showMeWelcomeScreen() {
		System.out.println("-----------------------------------");
		System.out.println("~~~~~~~~WELCOME!!~~~~~~~~~~");
		System.out.println("Application: LockedMe.com");
		System.out.println("Developer: Maheen Momin");
		System.out.println("-----------------------------------");
	}
	
	private static void showMainMenu() {
		System.out.println("\n\n-----------Main Menu------------");
		System.out.println("1.) Show Me Files In Ascending Order");
		System.out.println("2.) Perform File Operations");
		System.out.println("3.) Close Lockedme.com Application");
		collectMainMenuOption();
	}
	
	private static void collectMainMenuOption() {
		System.out.println("\nPlease choose option 1, 2, or 3.");
		int option = scanner.nextInt();
		scanner.nextLine();
		switch (option) {
			case 1:
				showFilesInAscendingOrder();
				break;
			case 2:
				showFileOperations();
				break;
			case 3:
				System.out.println("Thanks for using LockedMe.com.");
				System.out.println("Application Closing");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid input. Please choose option 1, 2, or 3.");
		}
		showMainMenu();
	}
	
	private static void showFilesInAscendingOrder() {
		System.out.println("Showing Files");
		System.out.println("Loading...\n");
		
		File[] files = new File (FOLDER).listFiles();
		
		Set<String> sorted = new TreeSet<>();
		
		for (File file: files) {
			sorted.add(file.getName());
		}
		sorted.forEach(System.out::println);
		System.out.println("-----------------------");
	}
	
	private static void showFileOperations() {
		System.out.println("----------------------------");
		System.out.println("1.) Add a File");
		System.out.println("2.) Delete a File");
		System.out.println("3.) Search for a File");
		System.out.println("4.) Back to Main Menu");
		System.out.println("----------------------------");
		collectFileOperations();
	}
	
	private static void collectFileOperations() {
		System.out.println("Please choose 1, 2, 3, or 4.");
		String option = scanner.nextLine();
		switch (option) {
		case "1":
			addAFile();
			System.out.println("Successful");
			break;
		case "2":
			deleteAFile();
			break;
		case "3":
			searchAFile();
			break;
		case "4":
			backToMain();
			break;
		default:
			System.out.println("Invalid input. Please choose option 1, 2, 3, or 4.");
		}
//		showFileOperations();
	}
	
	private static void addAFile() throws InvalidPathException {
		System.out.println("Please provide a file path:");
		String filePath = scanner.nextLine();
		Path path = Paths.get(filePath);
		
		if (!Files.exists(path)) {
			System.out.println("File does not exist");
			return;
		}
		String newFilePath = FOLDER + "/" + path.getFileName();
		int inc = 0;
		while (Files.exists(Paths.get(newFilePath))) {
			inc++;
			newFilePath = FOLDER + "/" + inc + "_" + path.getFileName(); 		}
		try {
			Files.copy(path, Paths.get(newFilePath));
		} catch(IOException e) {
			System.out.println("Unable to copy file to " + newFilePath);
		}
	}
	
	private static void deleteAFile() throws InvalidPathException {
		System.out.println("Please provide a file to delete.");
		String filePath = scanner.nextLine();
			Path path = Paths.get(filePath);
			
			if (!Files.exists(path)) {
				System.out.println("File does not exist");
				return;
			}
			try {
				Files.delete(path);
			} catch (IOException e) {
				System.out.println("Unable to delete file in " + filePath);
			}
	}
	private static void searchAFile() {
		System.out.println("Please provide file to search");
		String filePath = scanner.nextLine();
		
			if(!Files.exists(Paths.get(filePath))) {
				System.out.println("File not found");
				return;
			} else {
				System.out.println("File found Path: " + filePath);
			}
		
	}
	
	private static void backToMain() {
		System.out.println("Back to Main Menu");
		showMainMenu();
	}
}

