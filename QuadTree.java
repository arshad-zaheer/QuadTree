/**
 * This class represents IDE-generated JUnit test cases for the QuadTree v1.0 implementation.
 *
 * @author Arshad Zaheer
 * @version 1.0
 * @since 2025-11-21, Core Implementation 1.0
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.logging.Logger;
import java.util.logging.Level;

class QuadTreeTest {
    private static final Logger logger = Logger.getLogger(QuadTreeTest.class.getName());

    /**
     * Test the printTree() method for a QuadTree with a single element.
     */
    @Test
    void testPrintTreeWithSingleElement() {
        logger.log(Level.INFO, "Testing printTree with single element");
        int[] input = {10};
        QuadTree quadTree = new QuadTree(input);

        // Ensure the root node is not null and holds the correct value
        assertNotNull(quadTree.root);
        assertEquals(10, quadTree.root.value);

        // Execute printTree to verify it does not throw any exceptions
        QuadTree.printTree(quadTree);
        
    }

    /**
     * Test the printTree() method for a QuadTree with multiple elements.
     */
    @Test
    void testPrintTreeWithMultipleElements() {
        logger.log(Level.INFO, "Testing printTree with multiple elements");
        int[] input = {100, 50, 150, 30, 60, 110, 200};
        QuadTree quadTree = new QuadTree(input);

        QuadTree.printTree(quadTree);
        // Verify root node and main children
        assertNotNull(quadTree.root);
        assertEquals(100, quadTree.root.value);
        assertNull(quadTree.root.smaller);
        assertNotNull(quadTree.root.bigger);

        // Verify structure by checking child values
        assertEquals(50, quadTree.root.muchSmaller.value);
        assertEquals(150, quadTree.root.muchBigger.value);

        // Execute printTree to validate structure
        QuadTree.printTree(quadTree);
    }

    /**
     * Test the printTree() method for an empty QuadTree.
     */
    @Test
    void testPrintTreeWithEmptyTree() {
        logger.log(Level.INFO, "Testing printTree with empty tree");
        QuadTree quadTree = new QuadTree();

        // Ensure the root is null
        assertNull(quadTree.root);

        // Execute printTree to ensure no exception is thrown
        QuadTree.printTree(quadTree);
    }

    /**
     * Test the printTree() method for a tree with nodes distributed across all thresholds.
     */
    @Test
    void testPrintTreeWithDistributedNodes() {
        logger.log(Level.INFO, "Testing printTree with distributed nodes");
        int[] input = {100, 50, 150, 90, 45, 5, 250, 1};
        QuadTree quadTree = new QuadTree(input);
        QuadTree.printTree(quadTree);

        // Validate root value
        assertEquals(100, quadTree.root.value);

        // Validate distribution of nodes in muchSmaller, smaller, bigger, and muchBigger
        assertNotNull(quadTree.root.muchSmaller);
        assertNotNull(quadTree.root.smaller);
        assertNotNull(quadTree.root.muchBigger);

        // Validate specific sub-nodes
        assertEquals(50, quadTree.root.muchSmaller.value);
        assertEquals(90, quadTree.root.smaller.value);
        assertEquals(150, quadTree.root.muchBigger.value);

        // Execute printTree to ensure correct results
        QuadTree.printTree(quadTree);
    }

    /**
     * Test the printTree() method with input containing duplicate values.
     */
    @Test
    void testPrintTreeWithDuplicates() {
        logger.log(Level.INFO, "Testing printTree with duplicate values");
        int[] input = {100, 50, 100, 150, 50, 250};
        QuadTree quadTree = new QuadTree(input);
        QuadTree.printTree(quadTree);

        // Verify root and main children
        assertNotNull(quadTree.root);
        assertEquals(100, quadTree.root.value);

        // Ensure duplicates are not added
        assertEquals(50, quadTree.root.muchSmaller.value);
        assertEquals(250, quadTree.root.muchBigger.muchBigger.value);

        // Execute printTree to validate behavior
        QuadTree.printTree(quadTree);
    }
}
