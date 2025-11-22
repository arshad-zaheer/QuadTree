/**
 * This class represents a specific QuadTree implementation given a set of requirements.
 * Given an integer array as input, the QuadTree is constructed using the following rules:
 *  1) The Root node is created from the first int value from the input array of int values.  In the case of no value passed the Root will point to null value
 *  2) When creating the QuadTree structure duplicate int values from the input are ignored
 *  3) The four children subtrees are labeled: muchSmaller, smaller, bigger, muchBigger
 *  4) muchSmaller subtree contains all nodes where the difference from the parent node value exceeds MUCH_SMALLER_THRESHOLD.
 *  5) Smaller subtree contains all nodes that are less than the parent node, excluding the ones already accounted for under the muchSmaller subtree.
 *  6) muchBigger subtree contains all nodes where the difference from the parent node value exceeds MUCH_BIGGER_THRESHOLD.
 *  7) Bigger subtree contains all nodes that are more than the parent node, excluding the ones already accounted for under the muchBigger subtree.
 *  8) printTree function skips printing child nodes that are null
 *
 * @author Arshad Zaheer
 * @version 1.0
 * @since 2025-11-21, Core Implementation 1.0
 */
import java.util.Arrays;
public class QuadTree {

    static final int MUCH_BIGGER_THRESHOLD = 10; // as requried from the assignment, sets the thresholds for buckets
    static final int MUCH_SMALLER_THRESHOLD = -10; // as required from the assignment, sets the thresholds for the buckets

    static final int ROOT_COUNT = 0;
    static final int MUCH_SMALLER_COUNT = 1;
    static final int SMALLER_COUNT = 2;
    static final int BIGGER_COUNT = 3;
    static final int MUCH_BIGGER_COUNT = 4;

    int[] childCounts = new int[]{0,0,0,0};  // this variable is used in testing to validate expected vs actual test results under JUnit

    Node root; //this represents the start node for the QuadTree

    /**
     * Initializes a new instance of the QuadTree class.
     *
     * This default constructor creates a QuadTree object with the root node set to null.
     * The QuadTree is initially empty, and elements can be added to it by invoking appropriate methods.
     */
    public QuadTree() {
        this.root = null;
    }

    /**
     * Constructs a QuadTree object and initializes it using the given input array.
     * The QuadTree organizes the elements from the input array into hierarchical nodes
     * based on certain conditions, making it efficient for searching and operations.
     *
     * @param input an array of integers to initialize and build the QuadTree structure.
     *              Each element of the array is inserted into the QuadTree according
     *              to its value and specific comparison rules.
     */
    public QuadTree(int[] input) {
        // Build the quadtree
        System.out.println("QuadTree input int array:"+ Arrays.toString(input));
        this.root = buildQuadtree(input);
    }

    /**
     * Builds a quadtree structure from the given input array of integers.
     * The quadtree organizes the elements hierarchically using rules for
     * determining the placement of child nodes based on the node's value and the value difference.
     *
     * @param input an array of integers to be used for constructing the quadtree.
     *              Each element in the array is sequentially inserted into the tree,
     *              creating a hierarchical structure.
     *              If the array is null or empty, the method returns a null quadtree.
     * @return the root node of the constructed quadtree. If the input array is null or empty,
     *         returns null.
     */
    private Node buildQuadtree(int[] input) {
        if (input == null || input.length == 0) {
            return null;
        }

        // Start by creating the root node with the first element of the array
        Node root = new Node(input[0]);

        // Iterate through the array and insert each value into the tree
        for (int i = 1; i < input.length; i++) {
            insertNode(root, input[i]);
        }

        return root;
    }

    /**
     * Inserts a value into the specified node of the quadtree based on the value's
     * comparison result with the node's value. The method determines the appropriate
     * child node for the value and inserts it recursively. It creates new child
     * nodes if necessary.
     *
     * @param node  the current node of the quadtree where the value is to be inserted.
     *              This node serves as the starting point for the insertion logic,
     *              which recursively navigates the tree to find the correct placement.
     * @param value the integer value to be inserted into the quadtree.
     *              The placement of this value is determined by comparing it
     *              with the value of the current node and predefined thresholds.
     */
    //private static void insertNode(Node node, int value) {
    private void insertNode(Node node, int value) {

        //iterate through the four child nodes and insert the value into the appropriate bucket.
        //depth levels are tracked for the child nodes.
        //Note:  duplicate node values are dropped, hence no check for an equal value use case

        if (value - node.value > MUCH_BIGGER_THRESHOLD) {
            if (node.muchBigger == null) {
                node.muchBigger = new Node(value, node.level + 1);
            } else {
                insertNode(node.muchBigger, value);
            }
        } else if (value - node.value < MUCH_SMALLER_THRESHOLD) {
            if (node.muchSmaller == null) {
                node.muchSmaller = new Node(value, node.level + 1);
            } else {
                insertNode(node.muchSmaller, value);
            }
        } else if (value < node.value) {
            if (node.smaller == null) {
                node.smaller = new Node(value, node.level + 1);
            } else {
                insertNode(node.smaller, value);
            }
        } else if (value > node.value) {
            if (node.bigger == null) {
                node.bigger = new Node(value, node.level + 1);
            } else {
                insertNode(node.bigger, value);
            }
        }
    }


    /**
     * Prints the structure and hierarchy of the specified QuadTree.
     * This method serves as the entry point for printing the entire tree by calling
     * a recursive helper method to traverse and display the nodes of the QuadTree.
     *
     * @param qt the QuadTree instance to be printed. If the tree is null or empty,
     *           the method will simply output the initial "Printing QuadTree" message.
     */
    public static void printTree(QuadTree qt) {

        // Print the quadtree structure
        System.out.println("Printing QuadTree");
        printTree(qt.root, "Root:", "");
    }

    /**
     * Recursively prints the structure and hierarchy of the given quadtree starting from the specified node.
     * Each node in the tree is printed with an appropriate header and indentation
     * to represent its position and relationship in the tree.
     *
     * @param node   the current node being printed in the quadtree. This node serves as the entry
     *               point for traversal at each level. If the node is null, the method exits without printing.
     * @param header a descriptive label indicating the relationship or position of the node
     *               being printed (e.g., "Much Smaller", "Smaller", "Bigger", "Much Bigger").
     * @param indent a string used for indentation to visually represent the depth of the node
     *               in the tree hierarchy. It is incremented as the recursion progresses to child nodes.
     */
    public static void printTree(Node node, String header, String indent) {

        //return if the input root node is null.  This is to catch a null input to print the quadtree
        if (node == null) return;

        System.out.println(indent + header + node.value);

        //continue moving down the tree recursively if child nodes are present
        if (node.muchSmaller != null) printTree(node.muchSmaller, "Much Smaller:", indent + "    ");
        if (node.smaller != null) printTree(node.smaller, "Smaller:", indent + "    ");
        if (node.bigger != null) printTree(node.bigger, "Bigger:", indent + "    ");
        if (node.muchBigger != null) printTree(node.muchBigger, "Much Bigger:", indent + "    ");

    }

    /**
     * The entry point method for the application, which demonstrates the creation and
     * structure of a quadtree by building it from an input array and printing the resultant
     * tree hierarchy.
     *
     * @param args command-line arguments passed to the program. These arguments are ignored
     *             in this implementation.
     */
    public static void main(String[] args) {
        int[] input = {100, 11, 9, 100, 13, 4, 200};

        // Build the quadtree
        QuadTree q = new QuadTree(input);

        // Print the quadtree structure
        printTree(q);
    }

    /**
     * Counts the root and child node sizes recursively in a quadtree.
     * This method traverses the tree structure to compute and update counts
     * for the root, "much smaller", "smaller", "bigger", and "much bigger" nodes.
     *
     * @param node   the root node from which the recursive traversal begins. If the node is null,
     *               the method will return without updating counts.
     * @param counts an integer array where the calculated counts are stored. The array must
     *               have a minimum length of 5, where:
     *               - counts[ROOT_COUNT] represents the root node count.
     *               - counts[MUCH_SMALLER_COUNT] represents the "much smaller" child node count.
     *               - counts[SMALLER_COUNT] represents the "smaller" child node count.
     *               - counts[BIGGER_COUNT] represents the "bigger" child node count.
     *               - counts[MUCH_BIGGER_COUNT] represents the "much bigger" child node count.
     */
    // Method to count the root and chile node sizes recursively
    public static void getCounts(Node node, int[] counts) {

        //return if the input root node is null.  This is to catch a null input to print the quadtree
        if (node == null) return;

        // if we reach this point, it means we have guaranteed to have root
        counts[ROOT_COUNT] = 1; //the first position in the array is for the root element and @max remains 1

        if (node.muchSmaller != null) {
            //counts[1]++; //2nd position in the array is for the "tooMuchLess" node size
            counts[MUCH_SMALLER_COUNT]++; //2nd position in the array is for the "tooMuchLess" node size
            getCounts(node.muchSmaller, counts);
        }
        if (node.smaller != null) {
            //counts[2]++; //3rd position in the array is for the "less" node size
            counts[SMALLER_COUNT]++; //3rd position in the array is for the "less" node size
            getCounts(node.smaller, counts);
        }
        if (node.bigger != null) {
            //counts[3]++; //4th position in the array is for the "more" node size
            counts[BIGGER_COUNT]++; //4th position in the array is for the "more" node size
            getCounts(node.bigger, counts);
        }
        if (node.muchBigger != null) {
            //counts[4]++; //5th position in the array is for the tooMuchMore node size
            counts[MUCH_BIGGER_COUNT]++; //5th position in the array is for the tooMuchMore node size
            getCounts(node.muchBigger, counts);
        }

    }
}
