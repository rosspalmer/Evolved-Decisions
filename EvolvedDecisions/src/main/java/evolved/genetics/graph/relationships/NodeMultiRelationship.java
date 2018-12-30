package evolved.genetics.graph.relationships;

import evolved.genetics.graph.Node;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NodeMultiRelationship extends NodeRelationship {

    private Map<NodeMultiRelationshipLabel, Set<Node>> relatedNodeMap;

    public NodeMultiRelationship(int relationshipId, NodeRelationshipType nodeRelationshipType) {
        super(relationshipId, nodeRelationshipType);
        relatedNodeMap = new HashMap<>();
    }

    public void addRelatedNodes(NodeMultiRelationshipLabel nodeMultiRelationshipLabel, Node node) {
        Set<Node> nodes = new HashSet<>();
        nodes.add(node);
        addRelatedNodes(nodeMultiRelationshipLabel, nodes);
    }

    public void addRelatedNodes(NodeMultiRelationshipLabel nodeMultiRelationshipLabel, Set<Node> nodes) {
        Set<Node> allFromNodes = new HashSet<>();
        if (relatedNodeMap.containsKey(nodeMultiRelationshipLabel))
            allFromNodes = relatedNodeMap.get(nodeMultiRelationshipLabel);
        allFromNodes.addAll(nodes);
        this.relatedNodeMap.put(nodeMultiRelationshipLabel, allFromNodes);
    }

    public Map<NodeMultiRelationshipLabel, Set<Node>> getRelatedNodeMap() {
        return relatedNodeMap;
    }

    public Set<Node> getRelatedNodes(NodeMultiRelationshipLabel nodeMultiRelationshipLabel) {
        return relatedNodeMap.get(nodeMultiRelationshipLabel);
    }

    public void removeRelatedNodes(NodeMultiRelationshipLabel nodeMultiRelationshipLabel, Node node) {
        Set<Node> nodes = new HashSet<>();
        nodes.add(node);
        removeRelatedNodes(nodeMultiRelationshipLabel, nodes);
    }

    public void removeRelatedNodes(NodeMultiRelationshipLabel nodeMultiRelationshipLabel, Set<Node> nodes) {
        if (relatedNodeMap.containsKey(nodeMultiRelationshipLabel))
            relatedNodeMap.get(nodeMultiRelationshipLabel).removeAll(nodes);
    }
}
