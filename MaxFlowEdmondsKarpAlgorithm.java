import java.util.*;

public class MaxFlowEdmondsKarpAlgorithm {

    public int getMaxFlow(Graph graph) {
        int flow = 0;
        // shortest path is empty list if there is no path
        ArrayList<Node> shortestPath = graph.get_shortest_path_BFS(graph.graph[1], graph.graph[graph.n_cities]);
        while (shortestPath.size() > 0) {
            int currentMaxFlow = calculate_max_flow_on_path(shortestPath);
            flow += currentMaxFlow;
            graph.calculateResidualGraph(shortestPath, currentMaxFlow);
            shortestPath = graph.get_shortest_path_BFS(graph.graph[1], graph.graph[graph.n_cities]);
        }
        return flow;
        }

    public int calculate_max_flow_on_path(ArrayList<Node> path) {
        int maxCapacity = 10001;
        Node current = path.get(0);
        for (int i = 1; i < path.size(); i++) {
            Node next = path.get(i);
            HashMap<Integer, Integer> currentNeighbors = current.getNeighborNodes();
            // problem: there could be several roads going from a to b with different capacity, wich to pick?
            int currentCapacity = currentNeighbors.get(next.getValue());
            if (currentCapacity < maxCapacity) {
                maxCapacity = currentCapacity;
            }
            current = next;
        }
        return maxCapacity;
    }

    public static void main(String[] args) {
        MaxFlowEdmondsKarpAlgorithm getMaxFlow = new MaxFlowEdmondsKarpAlgorithm();
        Graph graph = new Graph();
        graph.saveGraph();
        int maxFlow = getMaxFlow.getMaxFlow(graph);
        System.out.println(maxFlow);
    }

 }




class Graph {

    public Node[] graph;
    public int n_cities;

    public Node[] saveGraph() {
        Scanner scanner = new Scanner(System.in);
        n_cities = scanner.nextInt();
        int n_roads = scanner.nextInt();
        graph = new Node[n_cities + 1];
        for (int i = 1; i <= n_cities; i++) {
            graph[i] = new Node(i);
        }
        for (int i = 0; i < n_roads; i++) {
            int start_vertex = scanner.nextInt();
            int end_vertex = scanner.nextInt();
            int capacity = scanner.nextInt();
            Node startNode = graph[start_vertex];
            HashMap<Integer, Integer> neighbors = startNode.getNeighborNodes();
            if (neighbors.containsKey(end_vertex)) {
                int currentCapacity = neighbors.get(end_vertex);
                graph[start_vertex].addNeighborNode(end_vertex, capacity + currentCapacity);
            }
            else {
                graph[start_vertex].addNeighborNode(end_vertex, capacity);
            }
        }
        return graph;
    }

    public void printGraph() {
        for (int i_city = 1; i_city <= n_cities; i_city++) {
            Node currentNode = graph[i_city];
            HashMap<Integer, Integer> roads_from_city = currentNode.getNeighborNodes();
            System.out.print(i_city + ": ");
            for (Map.Entry<Integer, Integer> entry : roads_from_city.entrySet()) {
                System.out.print("(" + entry.getKey() + " " + entry.getValue() + ")" + " ");
            }
            System.out.println();
        }
    }

    public ArrayList<Node> get_shortest_path_BFS(Node start_node, Node end_node) {
        Node[] previousNodes = solveShortestPath(start_node, end_node);
        return reconstructPath(start_node, end_node, previousNodes);
    }

    public Node[] solveShortestPath(Node start_node, Node end_node) {
        Node[] previousNodes = new Node[n_cities + 1];
        if (start_node == end_node) {
            return previousNodes;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(start_node);
        Boolean[] visited = new Boolean[n_cities + 1];
        Arrays.fill(visited, Boolean.FALSE);
        visited[start_node.getValue()] = true;
        while (!queue.isEmpty()) {
            Node current_node = queue.remove();
            HashMap<Integer, Integer> neighborNodes = current_node.getNeighborNodes();
            for (Map.Entry<Integer, Integer> entry : neighborNodes.entrySet()) {
                Node next = graph[entry.getKey()];
                if (!visited[next.getValue()]) {
                    queue.add(next);
                    visited[next.getValue()] = true;
                    previousNodes[next.getValue()] = current_node;
                    if (next == end_node) {
                        return previousNodes;
                    }
                }
            }
        }
        return previousNodes;
    }

    public ArrayList<Node> reconstructPath(Node startNode, Node endNode, Node[] previousNodes) {
        ArrayList<Node> path = new ArrayList<>();
        if (previousNodes[endNode.getValue()] == null) {
            return path;
        }
        Node current = endNode;
        while (!(current == null)) {
            path.add(current);
            current = previousNodes[current.getValue()];
        }
        Collections.reverse(path);
        return path;
    }

    public void calculateResidualGraph(ArrayList<Node> path, int flow) {
        Node current = graph[1];
        for (int i = 1; i < path.size(); i++) {
            Node next = path.get(i);
            subtractCapacity(current, next, flow);
            addCapacity(next, current, flow);
            current = next;
        }
    }

    public void addCapacity(Node start, Node end, int flow) {
        HashMap<Integer, Integer> startNeighbors = start.getNeighborNodes();
        int capacity = 0;
        if (startNeighbors.containsKey(end.getValue())) {
            capacity = startNeighbors.get(end.getValue());
        }
        if (capacity - flow <= 0 && startNeighbors.containsKey(start.getValue())) {
            startNeighbors.remove(end.getValue());
        }
        startNeighbors.put(end.getValue(), capacity + flow);
        start.setNeighborNodes(startNeighbors);
    }

    public void subtractCapacity(Node start, Node end, int flow) {
        HashMap<Integer, Integer> startNeighbors = start.getNeighborNodes();
        int capacity = 0;
        if (startNeighbors.containsKey(end.getValue())) {
            capacity = startNeighbors.get(end.getValue());
        }
        if (capacity - flow <= 0 && startNeighbors.containsKey(end.getValue())) {
            startNeighbors.remove(end.getValue());
        }
        else {
            startNeighbors.put(end.getValue(), capacity - flow);
        }
        start.setNeighborNodes(startNeighbors);
    }

}



class Node {

    private final int value;
    private HashMap<Integer, Integer> neighborNodes = new HashMap<>();

    public Node(int newValue) {
        value = newValue;
    }

    public int getValue() {
        return value;
    }

    public HashMap<Integer, Integer> getNeighborNodes() {
        return neighborNodes;
    }

    public void addNeighborNode(int neighborValue, int capacity) {
        neighborNodes.put(neighborValue, capacity);
    }

    public void setNeighborNodes(HashMap<Integer, Integer> neighbors) {
        neighborNodes = neighbors;
    }
}

