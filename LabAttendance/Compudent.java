import java.util.ArrayList;
import java.sql.*;

/*
 * public class compudent
 * 
 * an instance of compudent will be used to represent
 * either a computer or student. Since both entities need
 * to have the same information represented.
 * 
 */

public class Compudent {
	
	private String name;
	
	private ArrayList<Compudent> compudentInfo;
	private ArrayList<Timestamp> compudentTimes;
	
	Compudent(String _name) {
		name = _name;
	}
	
	void addCompudent(Compudent cs, Timestamp t) {
		compudentInfo.add(cs);
		compudentTimes.add(t);
	}
	
	public String getName() {
		return name;
	}
}
