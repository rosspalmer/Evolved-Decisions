package evolved.genetics.graph.relationships;

import evolved.genetics.graph.Node;

import java.util.HashSet;
import java.util.Set;

public class NodeSingleRelationship extends NodeRelationship {

    private Set<Node> relatedNodes;

    public NodeSingleRelationship(int relationshipId, NodeRelationshipType nodeRelationshipType) {
        super(relationshipId, nodeRelationshipType);
        relatedNodes = new HashSet<>();
    }

    public void addToNodes(Node fromNode) {
        Set<Node> toNodes = new HashSet<>();
        toNodes.add(fromNode);
        addToNodes(toNodes);
    }

    public void addToNodes(Set<Node> fromNodes) {
        this.relatedNodes.addAll(fromNodes);
    }

    public Set<Node> getRelatedNodes() {
        return relatedNodes;
    }

    public void removeToNodes(Node toNode) {
        Set<Node> toNodes = new HashSet<>();
        toNodes.add(toNode);
        removeToNodes(toNodes);
    }

    public void removeToNodes(Set<Node> toNodes) {
        this.relatedNodes.removeAll(toNodes);
    }
}
