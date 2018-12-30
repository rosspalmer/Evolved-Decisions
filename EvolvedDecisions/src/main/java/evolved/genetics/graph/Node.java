package evolved.genetics.graph;

import evolved.genetics.graph.relationships.NodeRelationship;
import evolved.genetics.graph.relationships.NodeRelationshipType;

import java.util.HashMap;
import java.util.Map;

public class Node {

    private final int nodeId;
    private final NodeType nodeType;

    private Map<NodeRelationshipType, NodeRelationship> nodeRelationshipMap;

    public Node(int nodeId, NodeType nodeType) {
        this.nodeId = nodeId;
        this.nodeType = nodeType;
        nodeRelationshipMap = new HashMap<>();
    }

    public int getNodeId() {
        return nodeId;
    }

    public NodeType getNodeType() {
        return nodeType;
    }
}
