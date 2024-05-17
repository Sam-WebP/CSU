
/**
 * Using an adjacency matrix, a graph is represented where nodes are individuals and edges represent the connections between them.
 * Methods are used to set and get information about the graph, like adding edges and setting/retrieving vertex labels.
 */
public class Graph {
    private int[][] adjacencyMatrix;
    private String[] vertexLabels;
    private int size;

    /**
     * New Graph are constructed with a specified number of vertices. 
     * The adjacency matrix and vertex label are initialised based on the number of vertices.
     *
     * @param size The number of vertices in the graph.
     */
    public Graph(int size) {
        this.size = size; // Initialise the size based on the size parameter
        adjacencyMatrix = new int[size][size]; // Initialise the adjacency matrix edges between vertices
        vertexLabels = new String[size]; // Initialise the size of the vertexLabels array
    }
    
    /**
     * Adds an undirected edge between two vertices in the graph. 
     * Both entries in the matrix are Initialised to 1 to indicate a connection.
     *
     * @param i The index of the first vertex.
     * @param j The index of the second vertex.
     * @throws IndexOutOfBoundsException if the indices i or j are not within the valid range of vertex indices.
     */
    public void addEdge(int i, int j) {
        if (i >= 0 && i < size && j >= 0 && j < size) { // Check if the vertices are larger or equal to 0 and smaller than the size
            adjacencyMatrix[i][j] = 1; // Puts the boolean value 1 in the coordinates i, j to confirm a relationship
            adjacencyMatrix[j][i] = 1; // Also puts the boolean value 1 in the flipped coordinates j, i because it is an adjacency matrix which are bidirectional
        }
    }

    /**
     * Assigns a label to a specific vertex in the graph.
     *
     * @param vertex The index of the vertex that will be labelled.
     * @param label The label to assign to the vertex.
     * @throws ArrayIndexOutOfBoundsException if the vertex index is out of bounds.
     */
    public void setLabel(int vertex, String label) {
        vertexLabels[vertex] = label; // Initialises the label to the vertexLabels array at the vertex index
    }

    /**
     * Return the value of the vertex index within the vertexLabels array 
     *
     * @param vertex The index of the vertex whose label will be retrieved.
     * @return The label of the specified vertex.
     * @throws ArrayIndexOutOfBoundsException if the vertex index is out of bounds.
     */
    public String getLabel(int vertex) {
        return vertexLabels[vertex]; 
    }

    /**
     * Returns the adjacency matrix used for the graph. .
     *
     * @return The adjacency matrix of the graph.
     */
    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix; 
    }

    /**
     * Returns the array of labels for all vertices in the graph. 
     *
     * @return An array containing the labels of all the vertices.
     */
    public String[] getVertexLabels() {
        return vertexLabels; 
    }
}
