/**
 * This class represents a node in the hierarchical QuadTree implementation.
 * The node value, node depth level, and the four children nodes represent subtrees based
 * on value differences.
 *
 * @author Arshad Zaheer
 * @version 1.0
 * @since 2025-11-21, Core Implementation 1.0
 */

public class Node {

    /**
     * Represents the value associated with a node in the structure.
     */
    int value;

    /**
     * Represents the hierarchical level of the node within the structure.
     */
    int level;

    /**
     * Reference to a child node where the value difference between this node and the child is greater than a predefined arbitrary threshold and is less than the current node's value.
     */
    Node muchSmaller;

    /**
     * References a child node that is associated with values less than the current node's value excluding the muchSmaller subtree.
     */
    Node smaller;

    /**
     * References a child node that is associated with values greater than the current node's value excluding the muchBigger subtree.
     */
    Node bigger;

    /**
     * Reference to a child node where the value difference between this node and the child is greater than a predefined arbitrary threshold and is greater than the current node's value.
     */
    Node muchBigger; // child for > 10 difference and > than node value

    /**
     * Constructs a Node with the specified value and initializes the node's level and child node references.
     *
     * @param value the integer value to be assigned to the node. This represents the value associated with the newly created node.
     */
    Node(int value) {
        this.value = value;
        this.level = 0;
        this.muchSmaller = null; //initialize this child node to null to indicate a leaf node
        this.smaller = null;        //initialize this child node to null to indicate a leaf node
        this.bigger = null;        //initialize this child node to null to indicate a leaf node
        this.muchBigger = null; //initialize this child node to null to indicate a leaf node
    }

    /**
     * Constructs a Node with the specified value and level, initializing all child node references to null.
     *
     * @param value the integer value to be assigned to the node. This represents the value associated with the node.
     * @param level the hierarchical level to be assigned to the node within the structure.
     */
    Node(int value, int level) {
        this.value = value; // int type cannot be null
        this.level = level;
        this.muchSmaller = null; //initialize this child node to null to indicate a leaf node
        this.smaller = null;        //initialize this child node to null to indicate a leaf node
        this.bigger = null;        //initialize this child node to null to indicate a leaf node
        this.muchBigger = null; //initialize this child node to null to indicate a leaf node
    }
}