package evolved.genetics.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Node {

    private final int nodeId;
    private final NodeType nodeType;

    private Map<NodeRelationshipType, Set<Node>> nodeRelationships;

    public Node(int nodeId, NodeType nodeType) {
        this.nodeId = nodeId;
        this.nodeType = nodeType;
        nodeRelationships = new HashMap<>();
    }

    public int getNodeId() {
        return nodeId;
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    public Set<Node> getRelatedNodes(NodeRelationshipType relationshipType) {
        if (nodeRelationships.containsKey(relationshipType)) {
            return nodeRelationships.get(relationshipType);
        } else {
            return new HashSet<>();
        }
    }

    public void setRelatedNodes(NodeRelationshipType relationshipType, Set<Node> relatedNodes) {
        nodeRelationships.put(relationshipType, relatedNodes);
    }

}
