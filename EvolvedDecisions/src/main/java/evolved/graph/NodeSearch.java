package evolved.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class NodeSearch {

    public static Node getNode(Set<Node> nodes, int nodeId) {
        return nodes.stream().filter(node -> node.getNodeId() == nodeId).findAny().orElse(null);
    }

    public static Set<Node> getNodes(Set<Node> nodes, NodeTag nodeTag) {
        return nodes.stream().filter(node -> node.hasNodeTag(nodeTag)).collect(Collectors.toSet());
    }

    public static List<Node> getNodePath(Node startNode, Set<Node> includedNodes) {

        List<Node> nodePath = new ArrayList<>();
        nodePath.add(startNode);
        Node currentNode = startNode;

        boolean pathIsComplete = false;

        while (!pathIsComplete) {

            Set<Node> nextNodes = currentNode.getRelatedNodes(NodeRelationship.PATH_TO);
            Node nextNode = null;
            for (Node node : nextNodes) {
                if (includedNodes.contains(node)) {
                    nextNode = node;
                    break;
                }
            }

            if (nextNode != null) {
                nodePath.add(nextNode);
                currentNode = nextNode;
            } else {
                pathIsComplete = true;
            }
        }
        return nodePath;
    }

    public static Set<Set<Node>> getNodeBlocks(List<Node> orderedNodes, NodeTag blockStartType, NodeTag blockEndType) {

        Set<Set<Node>> nodeBlocks = new HashSet<>();
        boolean startFound = false;
        int startIndex = 0;

        for (int i=0; i < orderedNodes.size(); i++) {

            Node node = orderedNodes.get(i);

            if (!startFound && node.hasNodeTag(blockStartType)) {
                startIndex = i;
                startFound = true;

            } else if (startFound && node.hasNodeTag(blockEndType)) {
                nodeBlocks.add(new HashSet<>(orderedNodes.subList(startIndex, i + 1)));
                startFound = false;
            }
        }
        return nodeBlocks;
    }
}
