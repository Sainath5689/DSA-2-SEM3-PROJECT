import java.util.*;

public class CO3_TournamentGraph {

    private int vertices;
    private LinkedList<Integer>[] adjList;

    CO3_TournamentGraph(int vertices) {

        this.vertices = vertices;
        adjList = new LinkedList[vertices];

        for (int i = 0; i < vertices; i++)
            adjList[i] = new LinkedList<>();
    }

    void addEdge(int source, int destination) {
        adjList[source].add(destination);
        adjList[destination].add(source);
    }

    void BFS(int start) {

        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {

            int node = queue.poll();
            System.out.print(node + " ");

            for (int neighbor : adjList[node]) {

                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }

    void DFS(int node, boolean[] visited) {

        visited[node] = true;

        System.out.print(node + " ");

        for (int neighbor : adjList[node]) {

            if (!visited[neighbor])
                DFS(neighbor, visited);
        }
    }

    public static void main(String[] args) {

        CO3_TournamentGraph graph =
                new CO3_TournamentGraph(6);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(4, 5);

        System.out.println("Tournament BFS:");
        graph.BFS(0);

        System.out.println("\nTournament DFS:");

        boolean[] visited = new boolean[6];
        graph.DFS(0, visited);
    }
}