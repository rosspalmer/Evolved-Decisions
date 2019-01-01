package evolved.genetics.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class NodeSearch {

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
}
