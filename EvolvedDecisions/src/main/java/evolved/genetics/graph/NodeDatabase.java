package evolved.genetics.graph;

import java.util.HashSet;
import java.util.Set;

public class NodeDatabase {

    private Set<Node> nodes;

    public NodeDatabase() {
        nodes = new HashSet<>();
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public Node getNode(int nodeId) {
        return NodeSearch.getNode(nodes, nodeId);
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public Set<Node> getNodes(NodeTag nodeTag) {
        return NodeSearch.getNodes(nodes, nodeTag);
    }

}
