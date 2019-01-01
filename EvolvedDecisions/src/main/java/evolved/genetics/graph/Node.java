package evolved.genetics.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Node {

    private final int nodeId;

    private Map<NodeRelationship, Set<Node>> relatedNodesMap;

    public Node(int nodeId) {
        this.nodeId = nodeId;
        relatedNodesMap = new HashMap<>();
    }

    public void addRelatedNodes(NodeRelationship relationship, Node node) {
        Set<Node> relatedNodes = new HashSet<>();
        if (relatedNodesMap.containsKey(relationship))
            relatedNodes = relatedNodesMap.get(relationship);
        relatedNodes.add(node);
        relatedNodesMap.put(relationship, relatedNodes);
    }

    public int getNodeId() {
        return nodeId;
    }

    public Set<Node> getRelatedNodes(NodeRelationship relationship) {
        if (relatedNodesMap.containsKey(relationship)) {
            return relatedNodesMap.get(relationship);
        } else {
            return new HashSet<>();
        }
    }

    public void setRelatedNodes(NodeRelationship relationship, Set<Node> relatedNodes) {
        relatedNodesMap.put(relationship, relatedNodes);
    }

}
