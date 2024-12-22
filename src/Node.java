public class Node {
    int x, y;
    int gCost = Integer.MAX_VALUE;
    int hCost;
    int fCost;
    Node parent;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}