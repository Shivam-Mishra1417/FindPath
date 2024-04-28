package COMP6651_Project;
/**
 * It is a class to define the orientation.
 * 
 * @author Shivam Mishra
 * @version 1.0
 * @since 14-feb-2022 
 * 
 */
import java.util.*;
public class Orientation1 {

    public static ArrayList < ArrayList < Integer >> finalDiGraph, reverseDiGraph, tempGraph;
    public static int[] orderOfTraversal, parent;
    public static int orderOfTraversalIndex = 0, total_nodes, root_node = 0;

    public void traverse_and_update(int node_visited, boolean[] visited_list, ArrayList < ArrayList < Integer >> finalGraph, boolean first_dfs) {
        // Mark the current node as visited and print it
        visited_list[node_visited] = true;
        //System.out.print(node_visited + " - ");

        // Iterate for all the vertices adjacent to this vertex
        for (int i = 0; i < finalGraph.get(node_visited).size(); i++) {
            int n = finalGraph.get(node_visited).get(i);
            if (!visited_list[n]) {
                if (first_dfs) {
                    //System.out.println("Adding edge "+node_visited+" to "+n);
                    finalDiGraph.get(node_visited).add(n);
                    orderOfTraversal[orderOfTraversalIndex] = node_visited;
                    orderOfTraversalIndex++;
                }
                parent[n] = node_visited;
                traverse_and_update(n, visited_list, finalGraph, first_dfs);

                //get node shows "from" and added node is "to" 
                // if node_visited = 0 and n = 1 then direction is 0 -> 1
            } else {
                if ((parent[node_visited] != n) && (!checkDirectionAssigned(node_visited, n)) && first_dfs) {
                    //System.out.println("xx  Adding edge "+node_visited+" to "+n);
                    finalDiGraph.get(node_visited).add(n);

                }
            }
        }
    }

    public boolean DFS_traversal(int currrent_node, ArrayList < ArrayList < Integer >> finalGraph, boolean first_dfs) {
        total_nodes = finalGraph.size();
        boolean visited_list[] = new boolean[total_nodes];
        Arrays.fill(visited_list, false);
        if (first_dfs) {
            //Initializing Directed graph
            finalDiGraph = new ArrayList < > (total_nodes);
            for (int i = 0; i < total_nodes; i++) {
                finalDiGraph.add(new ArrayList());
            }
            orderOfTraversal = new int[total_nodes];
            parent = new int[total_nodes];

        }
        traverse_and_update(currrent_node, visited_list, finalGraph, first_dfs);


        for (int i = 0; i < visited_list.length; i++)
            if (visited_list[i] == false)
                return false;
        return true;
    }
    //End of DFS

    void assignDirection(ArrayList < ArrayList < Integer >> graph) {
        boolean directionAssigned, first_dfs = true;
        int from_node, to_node;

        //Running a DFS and assigning direction Parent to child
        DFS_traversal(root_node, graph, first_dfs);

        // Now going to assign direction to remaining edges	

    }

    boolean checkDirectionAssigned(int node1, int node2) {
        //boolean result = false;

        for (int i = 0; i < finalDiGraph.get(node1).size(); i++)
            if (finalDiGraph.get(node1).get(i) == node2)
                //result =  true;
                return true;


        for (int i = 0; i < finalDiGraph.get(node2).size(); i++)
            if (finalDiGraph.get(node2).get(i) == node1)
                //result =  true;
                return true;
        return false;
    }
}