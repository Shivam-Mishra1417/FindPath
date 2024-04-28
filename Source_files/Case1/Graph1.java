package COMP6651_Project;
/**
 * It is a main class where graph is read from a file.
 * 
 * @author Shivam Mishra
 * @version 1.0
 * @since 14-feb-2022
 */
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Graph1 {

    public static ArrayList < ArrayList < Integer >> finalGraph;
    static int from_index = 0, to_index = 1, root = 0, numberOfNodes = 0;
    public static void main(String[] args) throws Exception {
        Scanner inputFile = new Scanner(new File("D:\\Shivam\\COMP6651_Algo\\Algo_project\\Test_cases\\rose_medical_building\\Case1\\Failure\\C1_NF_undirected_graph_input.txt"));
        // Read the number of nodes
        String s = inputFile.nextLine();
        numberOfNodes = Integer.parseInt(s);
        System.out.println("The number of nodes are " + numberOfNodes);

        //Initialize the graph
        finalGraph = new ArrayList < > (numberOfNodes);
        for (int i = 0; i < numberOfNodes; i++) {
            finalGraph.add(new ArrayList());
        }
        System.out.println("Case 1 - Graph is: ");
        while (inputFile.hasNext()) {
            s = inputFile.nextLine();
            String[] tuple = s.split("[\\|]");
            String[][] overall = new String[tuple.length][];
            for (int i = 0; i < tuple.length; i++) {
                overall[i] = tuple[i].split("[,]");
            }
            //System.out.println("overall - "+overall.length);
            for (int z = 0; z < overall.length; z++) {
                finalGraph.get(Integer.parseInt(overall[z][from_index])).add(Integer.parseInt(overall[z][to_index]));
            }
        }

        //Printing the accepted graph in adjacency list
        for (int i = 0; i < numberOfNodes; i++) {
            int edgeCount = finalGraph.get(i).size();
            for (int j = 0; j < edgeCount; j++) {
                Integer startVertex = i;
                Integer endVertex = finalGraph.get(i).get(j);
                System.out.printf("%d is connected to %d%n", startVertex, endVertex);
            }
        }

        System.out.println("");
        //Going to check the feasibility
        Feasibility1 f1 = new Feasibility1();
        boolean feasible = f1.checkFeasibility(finalGraph);

        if (feasible) {
            System.out.println("Graph is feasible for orientation");
            //Going to define the Orientation

            Orientation1 o1 = new Orientation1();
            o1.assignDirection(finalGraph);

            System.out.println("");
            //System.out.println("Below is the edges direction");
            for (int i = 0; i < numberOfNodes; i++) {
                int edgeCount = Orientation1.finalDiGraph.get(i).size();
                for (int j = 0; j < edgeCount; j++) {
                    System.out.println(i + " -> " + Orientation1.finalDiGraph.get(i).get(j));
                }
            }
            // Proof of result to check if its strongly connected or not
            Proof1 p1 = new Proof1();
            boolean success = p1.checkStronglyConnected(Orientation1.finalDiGraph);

            System.out.println("");
            if (success)
                System.out.println("Orientation - Successful");
            else
                System.out.println("Orientation - Unsuccessful");
        } else
            System.out.println("Failed to assign direction to edges");
    }


}