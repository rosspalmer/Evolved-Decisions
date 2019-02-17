package evolved.genetics.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Node {

    private final int nodeId;
    private Set<NodeTag> nodeTags;

    private Map<NodeRelationship, Set<Node>> relatedNodesMap;

    public Node(int nodeId) {
        this.nodeId = nodeId;
        this.nodeTags = new HashSet<>();
        relatedNodesMap = new HashMap<>();
    }

    public void addNodeTag(NodeTag nodeTag) {
        nodeTags.add(nodeTag);
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

    public Set<NodeTag> getNodeTags() {
        return nodeTags;
    }

    public Set<Node> getRelatedNodes(NodeRelationship relationship) {
        if (relatedNodesMap.containsKey(relationship)) {
            return relatedNodesMap.get(relationship);
        } else {
            return new HashSet<>();
        }
    }

    public boolean hasNodeTag(NodeTag nodeTag) {
        return nodeTags.contains(nodeTag);
    }

    public void setNodeTags(Set<NodeTag> nodeTags) {
        this.nodeTags = nodeTags;
    }

    public void setRelatedNodes(NodeRelationship relationship, Set<Node> relatedNodes) {
        relatedNodesMap.put(relationship, relatedNodes);
    }

}
