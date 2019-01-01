package evolved.genetics.graph;

import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class NodeSearchTest {

    @Test
    public void getCharacterPath() {

        Node node0 = new Node(0);
        Node nodeA = new Node(1);
        Node nodeB = new Node(2);
        Node nodeC = new Node(3);
        Node nodeD = new Node(4);
        Node nodeE = new Node(5);

        node0.addRelatedNodes(NodeRelationship.PATH_TO, nodeA);
        nodeA.addRelatedNodes(NodeRelationship.PATH_TO, nodeB);
        nodeA.addRelatedNodes(NodeRelationship.PATH_TO, nodeC);
        nodeB.addRelatedNodes(NodeRelationship.PATH_TO, nodeD);
        nodeC.addRelatedNodes(NodeRelationship.PATH_TO, nodeE);

        Set<Node> fullABDPath = new HashSet<>();
        fullABDPath.add(nodeD);
        fullABDPath.add(nodeA);
        fullABDPath.add(nodeB);

        List<Node> resultsABDPath = NodeSearch.getNodePath(nodeA, fullABDPath);
        assertEquals(3, resultsABDPath.size());
        assertEquals(nodeA, resultsABDPath.get(0));
        assertEquals(nodeB, resultsABDPath.get(1));
        assertEquals(nodeD, resultsABDPath.get(2));

        Set<Node> partialACPath = new HashSet<>();
        partialACPath.add(nodeC);
        partialACPath.add(nodeA);

        List<Node> resultsACPath = NodeSearch.getNodePath(nodeA, partialACPath);
        assertEquals(2, resultsACPath.size());
        assertEquals(nodeA, resultsACPath.get(0));
        assertEquals(nodeC, resultsACPath.get(1));


    }
}