
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MaxFlowEdmondsKarpAlgorithm {

/*    public int getMaxFlow(ArrayList[] graph) {
        int flow = 0;
        while (true) {
            ArrayList[] residual_graph = get_residual_graph(graph);
            ArrayList<Integer> shortestPath = get_shortest_path_BDS(residual_graph);
            if (shortestPath.exists()) {
                flow = flow + get_minimum_capacity(shortestPath);
            }
            else {
                break;
            }
        }
        return flow;
    }

    public ArrayList[] get_residual_graph(ArrayList[] current_graph) {
        return current_graph;
    }

    public ArrayList<Integer> get_shortest_path_BDS(ArrayList[] residual_graph) {
        ArrayList<Integer> shortestPath = new ArrayList<>();
        return shortestPath;
    }

    public int get_minimum_capacity(ArrayList<Integer> shortestPath) {
        int minimum_capacity = 1;
        return minimum_capacity;
    }*/

    public ArrayList[] saveGraph() {
        Scanner scanner = new Scanner(System.in);
        int n_cities = scanner.nextInt();
        int n_roads = scanner.nextInt();
        ArrayList[] graph = new ArrayList[n_cities+1];
        for (int i = 0; i <= n_cities; i++) {
            graph[i] = new ArrayList<int[]>();
        }
        for (int i = 0; i < n_roads; i++) {
            int start_vertex = scanner.nextInt();
            int end_vertex = scanner.nextInt();
            int capacity = scanner.nextInt();
            int[] road = {start_vertex, end_vertex, capacity};
            graph[start_vertex].add(road);
        }
        return graph;
    }

    public void printGraph(ArrayList[] graph) {
        for (int i_city = 1; i_city < graph.length; i_city++) {
            ArrayList<int[]> roads_in_city = graph[i_city];
            System.out.print(i_city + ": ");
            for (int[] road : roads_in_city) {
                System.out.print(Arrays.toString(road) + ", ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        MaxFlowEdmondsKarpAlgorithm getMaxFlow = new MaxFlowEdmondsKarpAlgorithm();
        ArrayList[] graph = getMaxFlow.saveGraph();
        getMaxFlow.printGraph(graph);
/*        int maxflow = getMaxFlow.getMaxFlow(graph);
        System.out.println(maxflow);*/
    }
 }
