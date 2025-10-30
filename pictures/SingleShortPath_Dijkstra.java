// Program: Single Source Shortest Path using Dijkstra's Algorithm


import java.util.*;


public class SingleShortPath_Dijkstra {

    static final int INF = Integer.MAX_VALUE;


    // Function to find the vertex with minimum distance value

    int minDistance(int dist[], boolean visited[], int vertices) {

        int min = INF, min_index = -1;


        for (int v = 0; v < vertices; v++) {

            if (!visited[v] && dist[v] <= min) {

                min = dist[v];

                min_index = v;

            }

        }

        return min_index;

    }


    // Function to implement Dijkstra's Algorithm

    void dijkstra(int graph[][], int src) {

        int vertices = graph.length;

        int dist[] = new int[vertices];

        boolean visited[] = new boolean[vertices];


        // Initialize distances

        for (int i = 0; i < vertices; i++) {

            dist[i] = INF;

            visited[i] = false;

        }


        dist[src] = 0;


        // Find shortest path for all vertices

        for (int count = 0; count < vertices - 1; count++) {

            int u = minDistance(dist, visited, vertices);

            visited[u] = true;


            for (int v = 0; v < vertices; v++) {

                if (!visited[v] && graph[u][v] != 0 &&

                    dist[u] != INF &&

                    dist[u] + graph[u][v] < dist[v]) {

                    dist[v] = dist[u] + graph[u][v];

                }

            }

        }


        printSolution(dist);

    }


    // Function to print the constructed distance array

    void printSolution(int dist[]) {

        System.out.println("Vertex \t Distance from Source");

        for (int i = 0; i < dist.length; i++) {

            System.out.println(i + " \t\t " + dist[i]);

        }

    }


    // Main method

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        SingleShortPath_Dijkstra da = new SingleShortPath_Dijkstra();


        System.out.print("Enter number of vertices: ");

        int vertices = sc.nextInt();

        int graph[][] = new int[vertices][vertices];


        System.out.println("Enter adjacency matrix: ");

        for (int i = 0; i < vertices; i++) {

            for (int j = 0; j < vertices; j++) {

                graph[i][j] = sc.nextInt();

            }

        }


        System.out.print("Enter source vertex: ");

        int src = sc.nextInt();


        da.dijkstra(graph, src);

        sc.close();

    }

}