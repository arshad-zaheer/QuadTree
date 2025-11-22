# QuadTree
Sample implementation of a specialized **QuadTree** given the following requirements:

Design and implement a reusable class for Quaternary Trees.  Implement a function which takes an array of integers, puts it in a quaternary tree, then prints the quaternary tree using the following rules:

**Quaternary Tree** <br>
Each element of the tree contains up to 4 child elements<br>
1st element - "Much Smaller" elements. Elements < ThisElement -10.<br>
2nd element - "Smaller" elements. Elements < ThisElement, excluding the Much Smaller elements.<br>
3rd element - "Bigger" elements. Elements > ThisElement, excluding the Much Bigger elements.<br>
4th element - "Much Bigger" elements. Elements > ThisElement + 10.<br>

**Print Out Rules** <br>
Print the tree in the following sequence:<br>

<pre><code>This Element<br>
	Much Smaller subtree<br>
	Smaller subtree<br>
    Bigger subtree<br>
    Much Bigger subtree<br></code></pre>

**Assumptions Inferred:** <br>
1. The root node of the tree is labelled as the "Root" when printing the quadtree
2. Duplicate nodes are not allowed, based on the rules defined for the child nodes it appears this to be the only valid outcome
3. Child nodes that are null are not printed when walking the tree to print.

**Example execution:** <br>

QuadTree input int array:[100, 11, 9, 100, 13, 4, 200] <br>
Printing QuadTree<br>
<pre><code>Root:100
    Much Smaller:11
        Smaller:9
            Smaller:4
        Bigger:13
    Much Bigger:200</code></pre>

