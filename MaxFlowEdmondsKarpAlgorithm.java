import java.util.*;

public class MaxFlowEdmondsKarpAlgorithm {

    private final Graph Graph = new Graph();

    public int getMaxFlow(Node[] graph) {
        int flow = 0;
        ArrayList<Node> shortestPath = Graph.getShortestPathBFS(graph[1], graph[graph.length - 1]);
        while (shortestPath.size() > 0) {
            int currentMaxFlow = getMaxFlowOnPath(shortestPath);
            flow += currentMaxFlow;
            Graph.calculateResidualGraph(shortestPath, currentMaxFlow);
            shortestPath = Graph.getShortestPathBFS(graph[1], graph[graph.length - 1]);
        }
        return flow;
    }

    public int getMaxFlowOnPath(ArrayList<Node> path) {
        int maxCapacity = 10000;
        Node current = path.get(0);
        for (int i = 1; i < path.size(); i++) {
            Node next = path.get(i);
            HashMap<Integer, Integer> currentNeighbors = current.getNeighbors();
            int currentCapacity = currentNeighbors.get(next.getValue());
            if (currentCapacity <= maxCapacity) {
                maxCapacity = currentCapacity;
            }
            current = next;
        }
        return maxCapacity;
    }

    public static void main(String[] args) {
        MaxFlowEdmondsKarpAlgorithm MaxFlow = new MaxFlowEdmondsKarpAlgorithm();
        Node[] graph = MaxFlow.Graph.saveGraph();
        int maxFlow = MaxFlow.getMaxFlow(graph);
        System.out.println(maxFlow);
    }

 }




class Graph {

    private Node[] graph;
    private int nNodes;

    public Node[] saveGraph() {
        Scanner scanner = new Scanner(System.in);
        nNodes = scanner.nextInt();
        int nEdges = scanner.nextInt();
        graph = new Node[nNodes + 1];
        for (int i = 1; i <= nNodes; i++) {
            graph[i] = new Node(i);
        }
        for (int i = 0; i < nEdges; i++) {
            int startVertexValue = scanner.nextInt();
            int endVertexValue = scanner.nextInt();
            int capacity = scanner.nextInt();
            Node startNode = graph[startVertexValue];
            HashMap<Integer, Integer> neighbors = startNode.getNeighbors();
            if (neighbors.containsKey(endVertexValue)) {
                int previousCapacity = neighbors.get(endVertexValue);
                capacity += previousCapacity;
            }
            graph[startVertexValue].addNeighbor(endVertexValue, capacity);
        }
        return graph;
    }

    public void printGraph() {
        for (int i = 1; i <= nNodes; i++) {
            Node current = graph[i];
            HashMap<Integer, Integer> outgoingEdges = current.getNeighbors();
            System.out.print(current.getValue() + ": ");
            for (Map.Entry<Integer, Integer> entry : outgoingEdges.entrySet()) {
                System.out.print("(" + entry.getKey() + " " + entry.getValue() + ")" + " ");
            }
            System.out.println();
        }
    }

    public ArrayList<Node> getShortestPathBFS(Node startNode, Node endNode) {
        Node[] previousNodes = getPreviousNodes(startNode, endNode);
        return reconstructPath(startNode, endNode, previousNodes);
    }

    public Node[] getPreviousNodes(Node startNode, Node endNode) {
        Node[] previousNodes = new Node[nNodes + 1];
        if (startNode == endNode) {
            return previousNodes;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(startNode);
        Boolean[] visited = new Boolean[nNodes + 1];
        Arrays.fill(visited, Boolean.FALSE);
        visited[startNode.getValue()] = true;
        while (!queue.isEmpty()) {
            Node current = queue.remove();
            HashMap<Integer, Integer> neighbors = current.getNeighbors();
            for (Map.Entry<Integer, Integer> entry : neighbors.entrySet()) {
                Node next = graph[entry.getKey()];
                if (!visited[next.getValue()]) {
                    queue.add(next);
                    visited[next.getValue()] = true;
                    previousNodes[next.getValue()] = current;
                    if (next == endNode) {
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

    public Node[] calculateResidualGraph(ArrayList<Node> path, int flow) {
        Node current = graph[1];
        for (int i = 1; i < path.size(); i++) {
            Node next = path.get(i);
            updateCapacity(current, next, flow, "subtract");
            updateCapacity(next, current, flow, "add");
            current = next;
        }
        return graph;
    }

    public void updateCapacity(Node start, Node end, int flow, String operation) {
        HashMap<Integer, Integer> startNeighbors = start.getNeighbors();
        int capacity = 0;
        if (startNeighbors.containsKey(end.getValue())) {
            capacity = startNeighbors.get(end.getValue());
        }
        if (operation.equals("add")) {
            startNeighbors.put(end.getValue(), capacity + flow);
        }
        else {
            if (capacity - flow <= 0 && startNeighbors.containsKey(end.getValue())) {
                startNeighbors.remove(end.getValue());
            }
            else {
                startNeighbors.put(end.getValue(), capacity - flow);
            }
        }
        start.setNeighbors(startNeighbors);
    }

}



class Node {

    private final int value;
    private HashMap<Integer, Integer> neighbors = new HashMap<>();

    public Node(int newValue) {
        value = newValue;
    }

    public int getValue() {
        return value;
    }

    public HashMap<Integer, Integer> getNeighbors() {
        return neighbors;
    }

    public void addNeighbor(int neighborValue, int capacity) {
        neighbors.put(neighborValue, capacity);
    }

    public void setNeighbors(HashMap<Integer, Integer> neighbors) {
        this.neighbors = neighbors;
    }
}

