package hr.fer.zemris.ooup.lab2.zad6;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class DatabaseTester {

	private static Map<Param, String> mapDB;
			
	private static String[] tables=new String[]{"ForceUsers"};
	private static String[] columns=new String[]{"Name","LightsaberColor","Affiliation"};
	private static String[] keys=new String[]{"1","2","3"};
	
	
	static{
		mapDB=new HashMap<>();
		//
		mapDB.put(new Param(tables[0],columns[0],keys[0]), "Luke Skywalker");
		mapDB.put(new Param(tables[0],columns[0],keys[1]), "Obi Wan Kenobi");
		mapDB.put(new Param(tables[0],columns[0],keys[2]), "Darth Vader");
		
		mapDB.put(new Param(tables[0],columns[1],keys[0]), "Green");
		mapDB.put(new Param(tables[0],columns[1],keys[1]), "Blue");
		mapDB.put(new Param(tables[0],columns[1],keys[2]), "Red");
		
		mapDB.put(new Param(tables[0],columns[2],keys[0]), "Rebel Alliance");
		mapDB.put(new Param(tables[0],columns[2],keys[1]), "Rebel Alliance");
		mapDB.put(new Param(tables[0],columns[2],keys[2]), "The Empire");
		
	}
	
	private static MyDBase db;
	
	public static void main(String[] args) {
		try {
			db=new ExcDBDecorator(new LogDBDecorator( new ConcreteDatabase(mapDB), "log.txt"));
		} catch (IOException e) {
			System.out.println("Unable to open file.");
			return;
		}
		queryLoop();
		db.close();
	}

	private static void queryLoop() {
		Scanner input=new Scanner(System.in);
		System.out.println("DB query console. Type \"q\" to quit, \"h\" for help.");
		while(true){
			System.out.print(">");
			String query=input.nextLine();
			if(query.equals("q"))
				break;
			else if(query.equals("h"))
				System.out.println("Type:\n\t"+
									"\"t\" to list all tables.\n\t"+
									"\"c\" to list all columns.\n\t"+
									"\"k\" to list all keys.\n\t"
									);
			else 
				if(query.equals("t"))
					System.out.println(arrayToString(tables));
			else 
				if(query.equals("c"))
					System.out.println(arrayToString(columns));
			else 
				if(query.equals("k"))
				System.out.println(arrayToString(keys));
			else{
				makeQuery(query);
			}
				
			
		}
		input.close();
	}

	private static void makeQuery(String query) {
		String[] arguments=query.trim().split(" ");
		if(arguments.length!=3){
			System.out.println("Invalid query given. Query must contain \"tabel column key\" values seperated by a single space.");
			return;
		}
		try{
		db.query(new Param(arguments[0], arguments[1], arguments[2]));
		}catch(DatabaseErrorException e){
			System.out.println("There was an error.\n"+e.getMessage());
		}
	}

	private static String arrayToString(String[] tables2) {
		StringBuilder sb=new StringBuilder();
		for(String s:tables2)
			sb.append("\t"+s+"\n");
		return sb.toString();
	}
}
