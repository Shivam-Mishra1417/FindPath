package COMP6651_Project;
/**
 * It is a class to check if with the direction assigned graph is strongly connected.
 * 
 * @author Shivam Mishra
 * @version 1.0
 * @since 14-feb-2022 
 * 
 */
import java.util.ArrayList;

public class Proof1 {
    //Using Kosaraju algorithm to confirm the resulted graph is strongly connected or not
    public static ArrayList < ArrayList < Integer >> finalDiGraph, reverseDiGraph, tempGraph;
    int root = 0;
    boolean checkStronglyConnected(ArrayList < ArrayList < Integer >> finalDiGraph) {

        Orientation1 o1 = new Orientation1();
        boolean round1 = o1.DFS_traversal(root, finalDiGraph, false);
        System.out.println("");
        if (round1) {
            reverseDiGraph = reverseTheGraph(finalDiGraph);
            System.out.println("Below is the edges direction for reversed graph");
            for (int i = 0; i < reverseDiGraph.size(); i++) {
                int edgeCount = reverseDiGraph.get(i).size();
                //System.out.println("vertex : "+i+ "  EdgeCount : "+edgeCount);
                for (int j = 0; j < edgeCount; j++) {
                    System.out.println(i + " -> " + reverseDiGraph.get(i).get(j));
                    //System.out.println(" "+Orientation.finalDiGraph.get(i).get(j));
                }
            }

            System.out.println("Going for reversed graph traversal");
            boolean round2 = o1.DFS_traversal(root, reverseDiGraph, false);
            if (round2)
                return true;
        }
        return false;
    }

    ArrayList < ArrayList < Integer >> reverseTheGraph(ArrayList < ArrayList < Integer >> diGraph) {

        reverseDiGraph = new ArrayList < > (diGraph.size()); //Initializing Directed graph
        for (int i = 0; i < diGraph.size(); i++) {
            reverseDiGraph.add(new ArrayList());
        }
        for (int i = 0; i < diGraph.size(); i++) {
            int edgeCount = diGraph.get(i).size();
            //System.out.println("vertex : "+i+ "  EdgeCount : "+edgeCount);
            for (int j = 0; j < edgeCount; j++) {

                reverseDiGraph.get(diGraph.get(i).get(j)).add(i);
            }
        }

        return reverseDiGraph;
    }
}