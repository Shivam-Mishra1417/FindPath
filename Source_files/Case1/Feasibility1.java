package COMP6651_Project;
/**
 * It is a class to check if all the edges in graph can be converted to one-way.
 * 
 * @author Shivam Mishra / Pragya Tomar
 * @version 1.0
 * @since 14-feb-2022 
 * 
 */
import java.util.ArrayList;

public class Feasibility1 {

    static int time = 0, bridgecount = 0, numberOfNodes = 0;
    ArrayList < ArrayList < Integer >> bridges;
    ArrayList < Integer > twoedge_list;
    boolean disconnected_graph = false, two_edge = false;

    void bridgeUtil(int u, int numberOfNodes, boolean visited[], int disc[],
        int low[], int parent[], ArrayList < ArrayList < Integer >> graph) {

        // Mark the current node as visited
        if (graph.get(u).size() > 0) {
            if (graph.get(u).size() < 2) {
                two_edge = true;
                twoedge_list.add(u);
            }
            visited[u] = true;
        } else
            disconnected_graph = true;
        // Initialize discovery time and low value
        disc[u] = low[u] = ++time;

        // Go through all vertices adjacent to this	
        for (int i = 0; i < graph.get(u).size(); i++) {
            int v = graph.get(u).get(i); // v is current adjacent of u
            // If v is not visited yet, then make it a child
            // of u in DFS tree and recur for it.
            if (!visited[v]) {
                parent[v] = u;
                bridgeUtil(v, numberOfNodes, visited, disc, low, parent, graph);
                // Check if the subtree rooted with v has a
                // connection to one of the ancestors of u
                low[u] = Math.min(low[u], low[v]);

                // If the lowest vertex reachable from subtree
                // under v is below u in DFS tree, then u-v is
                // a bridge
                if (low[v] > disc[u]) {
                    bridges.get(u).add(v);
                    //System.out.println("There is a bridge between "+u+" and "+v);
                    bridgecount++;
                }
            }

            // Update low value of u for parent function calls.
            else if (v != parent[u])
                low[u] = Math.min(low[u], disc[v]);
        }
    }

    boolean checkFeasibility(ArrayList < ArrayList < Integer >> graph) {
        numberOfNodes = graph.size();
        boolean visited[] = new boolean[numberOfNodes];
        int disc[] = new int[numberOfNodes];
        int low[] = new int[numberOfNodes];
        int parent[] = new int[numberOfNodes];
        twoedge_list = new ArrayList < Integer > (numberOfNodes);

        // Initialize parent and visited, and 2edge array
        for (int i = 0; i < numberOfNodes; i++) {
            parent[i] = -1;
            visited[i] = false;
        }
        // Initialize bridge 
        bridges = new ArrayList < > (numberOfNodes);
        for (int i = 0; i < numberOfNodes; i++) {
            bridges.add(new ArrayList());
        }
        //Checking for bridges
        for (int i = 0; i < numberOfNodes; i++)
            if (visited[i] == false)
                bridgeUtil(i, numberOfNodes, visited, disc, low, parent, graph);

        if (disconnected_graph) {
            System.out.print("Below node(s) are not connected to graph%n" +
                "so converting into One-way will not be feasible");
            for (int i = 0; i < numberOfNodes; i++)
                if (visited[i] == false) {
                    System.out.println(i);
                }
            return false;
        }

        if (two_edge) {
            System.out.println("Below nodes are connected with single edge " +
                "converting into One-way will not be feasible - ");
            System.out.println(twoedge_list);
        }

        if (bridgecount != 0) {
            System.out.println("Graph has below bridges so converting into One-way will not be feasible");
            for (int i = 0; i < bridges.size(); i++) {
                for (int j = 0; j < bridges.get(i).size(); j++)
                    System.out.println(i + " - " + bridges.get(i).get(j));
            }
        }

        if (disconnected_graph || two_edge || (bridgecount != 0))
            return false;

        return true;
    }

}