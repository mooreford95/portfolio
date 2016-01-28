import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.*;

/**
 * The main program, reads in a graph from an input file. SocialNetwork will then
 * parse through commands given by the user from standard input and will pass them 
 * to the CommandHandler class.
 * SocialNetwork also handles all exceptions. 
 * 
 * @author Thomas Ortiz
 * @author Michael Mackrell
 * @author Jacob Stone
 * @author Curtis Moore
 */
public class SocialNetwork {

    /**
     * Initiates the program and retrieves all input from the file and user. 
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Scanner scanFile = null;
        
        try {
             scanFile = getInput( args[0] ); // File input
        } catch (ArrayIndexOutOfBoundsException aioob){
            System.out.println("Error, expected: java SocialNetwork input_file.txt");
            System.exit(1);
        }
        
        Scanner scanInput = new Scanner(System.in);
        HashMap<String, Vertex> hashGraph = new HashMap<String, Vertex>();
        Graph graph = new Graph();
        createGraph(graph, hashGraph, scanFile);
        CommandHandler handler = new CommandHandler(graph, hashGraph);
        
        while(scanInput.hasNextLine()){
            try{
                nextCommand(handler, scanInput.nextLine() );
            } catch (Warning e){
                System.out.println(e);
            }
        }
        
        scanFile.close();
        scanInput.close();
    }

    /**
     * Method to retrieve the scanner for the file.
     * 
     * @param filename to retrieve input from
     * @return a scanner for the input file
     */
    public static Scanner getInput(String filename){
        Scanner in = null;
        try{
            in = new Scanner(new File(filename));
        } catch(FileNotFoundException fnfe){
            fnfe.getMessage();
        }
        return in;
    }

    /**
     * Creates a graph based off of the input file.
     * 
     * @param graphList object that holds our vertices
     * @param hashGraph String object mapping for efficient access
     * @param scanFile Scanner that parses through the file
     * @return a graph representation of the file
     */
    public static Graph createGraph(Graph graph, HashMap<String, Vertex> hashGraph, Scanner scanFile){
        GenericList<Vertex> vertexList = graph.getVertexArray();
        
        String name = null;
        Vertex person = null;
        // Part where we create the nodes
        while ( scanFile.hasNextLine() ){
            name = scanFile.nextLine();
            
            if(name.equals("$")){
                break;
            }
            person = new Vertex(name);
            hashGraph.put(name, person);
            vertexList.add(person);
        }
        
        // Part where we create the edges/adjacency list
        Scanner scanNameRelations = null;
        System.out.println("$");
        
        while( scanFile.hasNextLine() ){
            scanNameRelations = new Scanner(scanFile.nextLine());
            String name1 = null;
            String name2 = null;
            
            try{
                name1 = scanNameRelations.next();
                name2 = scanNameRelations.next();
            } catch(NoSuchElementException e){
                scanNameRelations.close();
                throw new Warning("command name1 name2");
            }
            
            Vertex person1 = hashGraph.get(name1);
            Vertex person2 = hashGraph.get(name2);
            
            person1.getAdjVertices().add(person2);
            person2.getAdjVertices().add(person1);
        }
        
        if(scanNameRelations != null){
            scanNameRelations.close();
        }        
        return graph;
    }
    
    /**
     * Determines the next command to handle
     * 
     * @param handler the CommandHandler object for the program
     * @param input the input from the user
     * @return true if the command was successful, false otherwise
     */
    public static void nextCommand(CommandHandler handler, String line){
        Scanner scanLine = new Scanner(line);
        String command = null;
        
        try {
            command = scanLine.next().toLowerCase();
        } catch (NoSuchElementException e){
            scanLine.close();
            throw new Warning("Invalid Input");
        }
        
        if( command.equals("quit") ){
            System.exit(0);
        } else if( command.equals("isfriend") ){
            handleIsFriend(handler, scanLine);
        } else if( command.equals("mutual") ){
            handleMutual(handler, scanLine);
        } else if( command.equals("relation") ){
            handleRelation(handler, scanLine);
        } else if( command.equals("notconnected") ){
            handleNotConnected(handler);
        } else if( command.equals("popular") ){
            handlePopular(handler);
        } else {
            scanLine.close();
            throw new Warning("Invalid Command");
        }
    
        if ( scanLine.hasNext() )
              throw new Warning("Extra stuff on command line, starting with " + scanLine.next());
        
        scanLine.close();
        System.out.println("$"); // Prints a $ after every command is done
    }
    
    /**
     * Handles the command isfriend from the user by passing it to the commandhandler class.
     * 
     * @param handler that we pass the command to
     * @param scanLine line to scan through for names 
     */
    public static void handleIsFriend(CommandHandler handler, Scanner scanLine){
        try{
            String name1 = scanLine.next();
            String name2 = scanLine.next();
            handler.isFriend(name1, name2);
        } catch(NoSuchElementException e){
            throw new Warning("command name1 name2");
        }
    }
    
    /**
     * Handles the command mutual from the user by passing it to the commandhandler class.
     * 
     * @param handler that we pass the command to
     * @param scanLine line to scan through for names 
     */
    public static void handleMutual(CommandHandler handler, Scanner scanLine){
        try{
            String name1 = scanLine.next();
            String name2 = scanLine.next();
            handler.mutual(name1, name2);
        } catch(NoSuchElementException e){
            throw new Warning("command name1 name2");
        }
    }
    
    /**
     * Handles the command relation from the user by passing it to the commandhandler class.
     * 
     * @param handler that we pass the command to
     * @param scanLine line to scan through for names 
     */
    public static void handleRelation(CommandHandler handler, Scanner scanLine){
        try{
            String name1 = scanLine.next();
            String name2 = scanLine.next();
            handler.relation(name1, name2);
        } catch(NoSuchElementException e){
            throw new Warning("command name1 name2");
        }
    }
    
    /**
     * Handles the command notconnected from the user by passing it to the commandhandler class.
     * 
     * @param handler that we pass the command to
     * @param scanLine line to scan through for names 
     */
    public static void handleNotConnected(CommandHandler handler){
        handler.notConnected();
    }
    
    /**
     * Handles the command popular from the user by passing it to the commandhandler class.
     * 
     * @param handler that we pass the command to
     * @param scanLine line to scan through for names 
     */
    public static void handlePopular(CommandHandler handler){
        handler.popular();
    }
}
