package lab5out;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

public class DatabaseFile {
	
	private Hashtable data;
	
	// constructor reads the file
	DatabaseFile(){
		data = new Hashtable();
		readFile();
	}
	
	public Hashtable getData() {
		return data;
	}
	
	// read file function
	public void readFile() {
		// File path is passed as parameter
        File file = new File("lab5out/usersData.txt");
        
        // create a scanner that will read the data
        Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		// read and save data to the "database" (in this case it is a dictionary)
		String[] line = new String[3];
        while (sc.hasNextLine()) {
          line = sc.nextLine().split(",");
          
          // add the data to the dictionary (line[1] = username AND line[2] = password)
          data.put(line[1], line[2]);
        }
	    
        System.out.println(data);
	      
	}
	
	
	// write file function

}
