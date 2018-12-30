package evolved.genetics.graph;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


public class GraphDatabase {

    private Set<Node> nodes;

    public GraphDatabase() {
        nodes = new HashSet<>();
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public Node getNode(int nodeId) {
        return nodes.stream()
                .filter(node -> node.getNodeId() == nodeId)
                .findAny().orElse(null);
    }

    public Set<Node> getNodesOfType(NodeType nodeType) {
        return nodes.stream()
                .filter(node -> node.getNodeType() == nodeType)
                .collect(Collectors.toSet());
    }



}
