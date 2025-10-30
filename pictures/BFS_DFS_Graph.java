import java.util.*;


class Graph {

    private int vertices;

    private LinkedList < Integer > [] adjList;


    // Constructor

    Graph(int v) {

        vertices = v;

        adjList = new LinkedList[v];

        for (int i = 0; i < v; i++) {

            adjList[i] = new LinkedList < > ();

        }

    }


    // Add edge (undirected)

    void addEdge(int src, int dest) {

        adjList[src].add(dest);

        adjList[dest].add(src);

    }


    // BFS Traversal

    void BFS(int start) {

        boolean visited[] = new boolean[vertices];

        Queue < Integer > queue = new LinkedList < > ();


        visited[start] = true;

        queue.add(start);


        System.out.print("BFS Traversal: ");

        while (!queue.isEmpty()) {

            int node = queue.poll();

            System.out.print(node + " ");


            for (int neighbor: adjList[node]) {

                if (!visited[neighbor]) {

                    visited[neighbor] = true;

                    queue.add(neighbor);

                }

            }

        }

        System.out.println();

    }


    // DFS Traversal

    void DFS(int start) {

        boolean visited[] = new boolean[vertices];

        System.out.print("DFS Traversal: ");

        DFSUtil(start, visited);

        System.out.println();

    }


    private void DFSUtil(int node, boolean[] visited) {

        visited[node] = true;

        System.out.print(node + " ");

        for (int neighbor: adjList[node]) {

            if (!visited[neighbor]) {

                DFSUtil(neighbor, visited);

            }

        }

    }


    // Print adjacency list

    void printAdjList() {

        System.out.println("Adjacency List Representation:");

        for (int i = 0; i < vertices; i++) {

            System.out.print(i + " -> ");

            for (int neighbor: adjList[i]) {

                System.out.print(neighbor + " ");

            }

            System.out.println();

        }

    }

}


public class BFS_DFS_Graph {

    public static void main(String[] args) {

        Graph g = new Graph(9); // 9 vertices (0–8)


        // Adding edges from the given graph (ignoring weights)

        g.addEdge(0, 1);

        g.addEdge(0, 7);

        g.addEdge(1, 2);

        g.addEdge(1, 7);

        g.addEdge(2, 3);

        g.addEdge(2, 8);

        g.addEdge(2, 5);

        g.addEdge(3, 4);

        g.addEdge(3, 5);

        g.addEdge(4, 5);

        g.addEdge(5, 6);

        g.addEdge(6, 7);

        g.addEdge(6, 8);

        g.addEdge(7, 8);


        // Print adjacency list

        g.printAdjList();


        // BFS & DFS Traversal from node 0

        g.BFS(0);

        g.DFS(0);

    }

}