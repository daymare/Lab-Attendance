import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * 
 * notes: design this like a linux console command.
 * 
 * methods:
 * -help
 * -getStudent
 * -getStudentList
 * -getClass
 * 
 */


public class Attendance {
	private String filepath;
	
	private Boolean exit = false;
	private Scanner console = new Scanner(System.in);
	private Scanner file;
	
	private String userInput;
	
	private ArrayList<Compudent> computers;
	private ArrayList<Compudent> students;
	
	// Constructor
	public Attendance(String fp) {
		
		filepath = fp;
		
		try {
			file = new Scanner(new File(filepath));
			mainLoop();
		} catch (FileNotFoundException e) {
			System.out.println("COULD NOT FIND FILE");
			e.printStackTrace();
		}
	}
	
	/*
	 * mainLoop
	 * 
	 * main loop of the program
	 * asks for commands and calls functions or gives output based on those commands.
	 * 
	 */
	public void mainLoop() {
		while(!exit) {
			userInput = askUser(console);
			
			switch(userInput.split(" ")[0].toLowerCase()) {
			case "getStudent":
				getStudentInfo(userInput.split(" "));
				break;
			
			case "getAttendance":
				getAttendance(userInput.split(" "));
				break;
				
			case "processData":
				processData();
				break;
			
			case "help":
				displayHelp();
				break;
				
			case "exit": // exit the program
				exit = true;
				break;
				
			case "hi":
				System.out.println("Hello!");
				break;
				
			case "test":
				testBlock();
				break;
				
			default:
				System.out.println("not a command!");
				break;
			}
		}
	}
	
	public String askUser(Scanner in) {
		System.out.println();
		System.out.print("Command me:");
		return in.nextLine();
	}
	
	/*
	 * processData
	 * 
	 * loads the raw data from user.txt
	 * and fills in the two compudent arraylists (student and computer)
	 * 
	 */
	private void processData() {
		loadComputerData();
		loadStudentData();
	}
	
	/*
	 * loadComputerData
	 * 
	 * loads computer data from the raw text document
	 * 
	 */
	private void loadComputerData() {
		ArrayList<String> currentBlock;
		ArrayList<String[]> formattedBlock;
		Compudent toAdd;
		
		currentBlock = getNextBlock(file);
		
		while(currentBlock.get(0).toString() != "Directory") {
			formattedBlock = formatBlock(currentBlock);
			toAdd = createComputerFromBlock(formattedBlock);
			computers.add(toAdd);
			
			currentBlock = getNextBlock(file);
		}
	}
	
	/*
	 * Tests that blocks are printed correctly
	 * by sending the output of a block to the console.
	 */
	private void testBlock() {
		ArrayList<String[]> formattedBlock;
		ArrayList<String> rawBlock;
		
		rawBlock = getNextBlock(file);
		
		formattedBlock = formatBlock(rawBlock);
		
		for(String[] a : formattedBlock) {
			for(String n : a) {
				System.out.print(n + " ");
			}
			System.out.println();
		}
		
	}
	
	/*
	 * 
	 * createComputerFromBlock
	 * 
	 * creates a new Compudent object
	 * and loads the data from off the given block
	 * into the new Compudent
	 * 
	 */
	private Compudent createComputerFromBlock(ArrayList<String[]> formattedBlock) {
		Compudent out;
		
		// create compudent. With name.
		
		// add all students
		
		return null;
	}

	/*
	 * loadStudentData
	 * 
	 * loads student data from the COMPUTER ARRAYLIST
	 * will call loadComputerData if it has not already been called
	 */
	private void loadStudentData() {
		if(computers == null) {
			loadComputerData();
		}
		
	}
	
	/*
	 * loadComputers
	 * 
	 * input: 
	 * 	Scanner(file) of input. 
	 * 
	 * output:
	 * 	ArrayList<Compudent> of computers in the file.
	 */
	
	/*
	 * getNextBlockf
	 * 
	 * returns a string array of all lines
	 * in a computer directory block
	 * 
	 */
	public ArrayList<String> getNextBlock(Scanner file) {
		ArrayList<String> out = new ArrayList<String>();
		
		// find the start of computer block
		while(file.hasNextLine()) {
			String line = file.nextLine();
			String[] text = line.split(" ");
			try {
				if(line.split(" ")[1].equalsIgnoreCase("Directory")) {
					out.add(line);
					out.add(file.nextLine());
					break;
				}
			}catch(ArrayIndexOutOfBoundsException e) {
				
			}
		}
		
		// End of file. Return null.
		if(file.hasNextLine() == false) {
			return null;
		}
		
		// move past the one line space in the start of a block
		file.nextLine();
		file.nextLine();
		
		// load data
		while(file.hasNextLine()) {
			String line = file.nextLine();
			if(line.equals("")) { // check for end of block
				break;
			}
			out.add(line);
		}
		return out;
	}
	
	/*
	 * formatBlock
	 * 
	 * inputs a block of data from a particular computer.
	 * blocks can be created by getNextBlock
	 * 
	 * outputs in the form
	 * 
	 * "th134labXX"
	 * <Date>, <Time: HRS:Mins> (converted to military time), <User>
	 * <Date>, <Time: HRS:Mins> (converted to military time), <User>
	 * <Date>, <Time: HRS:Mins> (converted to military time), <User>
	 * .........
	 * 
	 */
	public ArrayList<String[]> formatBlock(ArrayList<String> block) {
		ArrayList<String[]> out = new ArrayList<String[]>();
		
		out.add(block.get(0).split(" "));
		out.add(block.get(1).split(" "));
		
		for(int i = 2; i<block.size()-4; i++) {
			String[] line = block.get(i).split(" ");
			String[] add = new String[3];
			
			add[0] = line[0];
			add[1] = line[2]+" "+line[3];
			add[2] = line[17];
			
			out.add(add);
		}
		return out;
	}
	
	public void displayHelp() {
		System.out.println();
		System.out.println("No help available at this time...");
	}
	

	/*
	 * getStudentInfo
	 * 
	 * options:
	 * - > saves the output of this command with the filename <student>attendance.txt
	 * 
	 * outputs data about a student in the format:
	 * <student>
	 * <computer> <logon time>
	 * 
	 */
	public void getStudentInfo(String[] args) {
		String output = "";
		String username = args[1];
		
		output += args[1];
		output += "\n";
		
		// TODO get input file.
		
	}
	
	/*
	 * getAttendance
	 * 
	 * Get the attendance of an entire class.
	 * 
	 */
	public void getAttendance(String[] args) {
		
	}
	
}
