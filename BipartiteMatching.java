import java.util.*;

public class BipartiteMatching {

    private final Graph Graph = new Graph();
    private Node[] graph;

    public int getMaxFlow() {
        int flow = 0;
        ArrayList<Node> shortestPath = Graph.getShortestPathBFS(graph[0], graph[graph.length - 1]);
        while (shortestPath.size() > 0) {
            // maxFlow on each path is 1
            flow += 1;
            Graph.calculateResidualGraph(shortestPath);
            shortestPath = Graph.getShortestPathBFS(graph[0], graph[graph.length - 1]);
        }
        return flow;
    }

    private int[] getMatches() {
        int nFlights = Graph.getNFlights();
        int[] matches = new int[nFlights];
        Arrays.fill(matches, -1);
        for (int i = Graph.getNFlights() + 1; i < graph.length - 1; i++) {
            Node crew = graph[i];
            ArrayList<Node> neighbors = crew.getNeighbors();
            Node flight = neighbors.get(0);
            if (flight.getValue() <= nFlights) {
                matches[flight.getValue() - 1] = crew.getValue() - nFlights;
            }
        }
        return matches;
    }

    public static void main(String[] args) {
        BipartiteMatching BiMatching = new BipartiteMatching();
        BiMatching.graph = BiMatching.Graph.constructGraph();
        BiMatching.getMaxFlow();
        int[] matches = BiMatching.getMatches();
        for (int i = 0; i < matches.length - 1; i++) {
            System.out.print(matches[i] + " ");
        }
        System.out.print(matches[matches.length - 1]);
    }
}



class Graph {

    private Node[] graph;
    private int nNodes;
    private int nFlights;

    public int getNFlights() {
        return nFlights;
    }

    public Node[] constructGraph() {
        Scanner scanner = new Scanner(System.in);
        nFlights = scanner.nextInt();
        int nCrews = scanner.nextInt();
        nNodes = nCrews + nFlights + 2;
        graph = new Node[nNodes];
        Node source = new Node(0);
        Node sink = new Node(nNodes - 1);
        graph[0] = source;
        graph[graph.length - 1] = sink;
        for (int i = 1; i <= nNodes - 2; i++) {
            Node current = new Node(i);
            if (i <= nFlights) {
                source.addNeighbor(current);
            }
            else {
                current.addNeighbor(sink);
            }
            graph[i] = current;
        }
        for (int flight = 1; flight <= nFlights; flight++) {
            Node flightNode = graph[flight];
            for (int crew = 1; crew <= nCrews; crew++) {
                int available = scanner.nextInt();
                if (available == 1) {
                    Node crewNode = graph[crew + nFlights];
                    flightNode.addNeighbor(crewNode);
                }
            }
        }
        return graph;
    }

    public void printGraph() {
        for (Node node : graph) {
            System.out.print(node.getValue() + ": ");
            for (Node neighbor : node.getNeighbors()) {
                System.out.print(neighbor.getValue() + ", ");
            }
            System.out.println();
        }
    }

    public ArrayList<Node> getShortestPathBFS(Node startNode, Node endNode) {
        Node[] previousNodes = getPreviousNodes(startNode, endNode);
        return reconstructPath(startNode, endNode, previousNodes);
    }

    public Node[] getPreviousNodes(Node startNode, Node endNode) {
        Node[] previousNodes = new Node[nNodes];
        if (startNode == endNode) {
            return previousNodes;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(startNode);
        Boolean[] visited = new Boolean[nNodes];
        Arrays.fill(visited, Boolean.FALSE);
        visited[startNode.getValue()] = true;
        while (!queue.isEmpty()) {
            Node current = queue.remove();
            ArrayList<Node> neighbors = current.getNeighbors();
            for (Node next : neighbors) {
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

    public void calculateResidualGraph(ArrayList<Node> path) {
        Node current = graph[0];
        for (int i = 1; i < path.size(); i++) {
            Node next = path.get(i);
            ArrayList<Node> neighbors = current.getNeighbors();
            for (Node neighbor : neighbors) {
                if (neighbor == next) {
                    neighbors.remove(neighbor);
                    current.setNeighbors(neighbors);
                    next.addNeighbor(current);
                    break;
                }
            }
            current = next;
        }
    }
}



class Node {

    private final int value;
    private ArrayList<Node> neighbors = new ArrayList<>();

    public Node(int newValue) {
        value = newValue;
    }

    public int getValue() {
        return value;
    }

    public ArrayList<Node> getNeighbors() {
        return neighbors;
    }

    public void addNeighbor(Node node) {
        neighbors.add(node);
    }

    public void setNeighbors(ArrayList<Node> neighbors) {
        this.neighbors = neighbors;
    }
}
