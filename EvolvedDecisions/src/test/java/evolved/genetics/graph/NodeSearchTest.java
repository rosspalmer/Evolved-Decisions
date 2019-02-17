package evolved.genetics.graph;

import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class NodeSearchTest {

    @Test
    public void getNodesOfType() {

        Node nodeA = new Node(1, NodeType.CHARACTER);
        Node nodeB = new Node(2, NodeType.PATH_START);
        Node nodeC = new Node(3, NodeType.PATH_END);
        Node nodeD = new Node(4, NodeType.CHARACTER);

        Set<Node> nodes = new HashSet<>();
        nodes.add(nodeA);
        nodes.add(nodeB);
        nodes.add(nodeC);
        nodes.add(nodeD);

        Set<Node> characterNodes = NodeSearch.getNodesOfType(nodes, NodeType.CHARACTER);
        assertEquals(2, characterNodes.size());
        assertTrue(characterNodes.contains(nodeA));
        assertFalse(characterNodes.contains(nodeB));
        assertFalse(characterNodes.contains(nodeC));
        assertTrue(characterNodes.contains(nodeD));

        Set<Node> startNodes = NodeSearch.getNodesOfType(nodes, NodeType.PATH_START);
        assertEquals(1, startNodes.size());
        assertFalse(startNodes.contains(nodeA));
        assertTrue(startNodes.contains(nodeB));
        assertFalse(startNodes.contains(nodeC));
        assertFalse(startNodes.contains(nodeD));

        Set<Node> endNodes = NodeSearch.getNodesOfType(nodes, NodeType.PATH_END);
        assertEquals(1, endNodes.size());
        assertFalse(endNodes.contains(nodeA));
        assertFalse(endNodes.contains(nodeB));
        assertTrue(endNodes.contains(nodeC));
        assertFalse(endNodes.contains(nodeD));

    }

    @Test
    public void getCharacterPath() {

        Node node0 = new Node(0, null);
        Node nodeA = new Node(1, null);
        Node nodeB = new Node(2, null);
        Node nodeC = new Node(3, null);
        Node nodeD = new Node(4, null);
        Node nodeE = new Node(5, null);

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

    @Test
    public void getNodeBlocks() {

        Node node0 = new Node(0, NodeType.CHARACTER);
        Node nodeA = new Node(1, NodeType.PATH_START);
        Node nodeB = new Node(2, NodeType.CHARACTER);
        Node nodeC = new Node(3, NodeType.PATH_START);
        Node nodeD = new Node(4, NodeType.PATH_END);
        Node nodeE = new Node(5, NodeType.PATH_START);
        Node nodeF = new Node(6, NodeType.CHARACTER);
        Node nodeG = new Node(7, NodeType.PATH_END);

        node0.addRelatedNodes(NodeRelationship.PATH_TO, nodeA);
        nodeA.addRelatedNodes(NodeRelationship.PATH_TO, nodeB);
        nodeB.addRelatedNodes(NodeRelationship.PATH_TO, nodeC);
        nodeC.addRelatedNodes(NodeRelationship.PATH_TO, nodeD);
        nodeD.addRelatedNodes(NodeRelationship.PATH_TO, nodeE);
        nodeE.addRelatedNodes(NodeRelationship.PATH_TO, nodeF);
        nodeF.addRelatedNodes(NodeRelationship.PATH_TO, nodeG);

        Set<Node> fullPath = new HashSet<>();
        fullPath.add(node0);
        fullPath.add(nodeA);
        fullPath.add(nodeB);
        fullPath.add(nodeC);
        fullPath.add(nodeD);
        fullPath.add(nodeE);
        fullPath.add(nodeF);
        fullPath.add(nodeG);

        List<Node> nodesOrdered = NodeSearch.getNodePath(node0, fullPath);
        assertEquals(8, nodesOrdered.size());

        Set<Set<Node>> nodeBlocks = NodeSearch.getNodeBlocks(nodesOrdered, NodeType.PATH_START, NodeType.PATH_END);

        assertEquals(2, nodeBlocks.size());

        for (Set<Node> block : nodeBlocks) {
            if (block.size() == 4) {
                assertTrue(block.contains(nodeA));
                assertTrue(block.contains(nodeB));
                assertTrue(block.contains(nodeC));
                assertTrue(block.contains(nodeD));
            } else if (block.size() == 3) {
                assertTrue(block.contains(nodeE));
                assertTrue(block.contains(nodeF));
                assertTrue(block.contains(nodeG));
            } else {
                throw new RuntimeException("Incorrect size of returned block");
            }
        }

    }

}