class mobb{
 
    public static void main(String[] args) {
 
        Node rootNode = null;
        

        rootNode = addNode(rootNode, 1, true);
        rootNode = addNode(rootNode, 2, true);
        rootNode = addNode(rootNode, 3, true);
        rootNode = addNode(rootNode, 4, true);
        rootNode = addNode(rootNode, 5, true);
        
 
        printTreePreOrder(rootNode);
        System.out.println();
 
        String str = sTree(rootNode);
        System.out.println(str);
 
        Node start = deserializeBinaryTree(str);
        System.out.println(start);
        printTreePreOrder(start);
    }
 
    //Serialize
    private static String sTree(Node rootNode) {
        if (rootNode == null) {
            return "null,";
        }
 
        StringBuilder sb = new StringBuilder();
        sb.append(rootNode.getData());
        sb.append(",");
 
        sb.append(sTree(rootNode.getLeft()));
        sb.append(sTree(rootNode.getRight()));
        return sb.toString();
    }
 
    //Deserialize Binary Tree
    public static Node deserializeBinaryTree(String data) {
        String[] temp = data.split(",");
        //return deserialize(temp, new int[] {0});
        return deserializeUsingStaticCounter(temp);
    }
 
    private static Node deserialize(String[] data, int[] index) {
        if (index[0] > data.length || data[index[0]].equals("null")) {
            index[0]++;
            return null;
        }
 
        //After reading the data, increment index value as indication to read next
        //array value in further iteration
        Node node = new Node(Integer.parseInt(data[index[0]++]));
        node.setLeft(deserialize(data, index));
        node.setRight(deserialize(data, index));
 
        return node;
    }
 
    static int index;
 
    private static Node deserializeUsingStaticCounter(String[] data) {
        if (index > data.length || data[index].equals("null")) {
            index++;
            return null;
        }
 
        Node node = new Node(Integer.parseInt(data[index++]));
        node.setLeft(deserializeUsingStaticCounter(data));
        node.setRight(deserializeUsingStaticCounter(data));
 
        return node;
    }
 
    private static Node addNode(Node rootNode, int i, boolean isRootNode) {
        if (rootNode == null) {
            return new Node(i);
        } else {
            if (i > rootNode.getData()) {
                if (isRootNode) {
                    Node nodeToAdd = addNode(rootNode.getRight(), i, isRootNode);
                    rootNode.setRight(nodeToAdd);
                } else {
                    Node nodeToAdd = addNode(rootNode.getLeft(), i, isRootNode);
                    rootNode.setLeft(nodeToAdd);
                }
 
            } else {
                if (isRootNode) {
                    Node nodeToAdd = addNode(rootNode.getLeft(), i, isRootNode);
                    rootNode.setLeft(nodeToAdd);
                } else {
                    Node nodeToAdd = addNode(rootNode.getRight(), i, isRootNode);
                    rootNode.setRight(nodeToAdd);
                }
            }
        }
        return rootNode;
    }
 
    private static void printTreePreOrder(Node rootNode) {
        if (rootNode == null)
            return;
        System.out.print(rootNode.getData() + " ");
        printTreePreOrder(rootNode.getLeft());
        printTreePreOrder(rootNode.getRight());
    }
}
 
class Node {
 
    private Node left;
    private Node right;
    private int data;
 
    public Node(int data) {
        this.data = data;
    }
 
    public Node getLeft() {
        return left;
    }
 
    public void setLeft(Node left) {
        this.left = left;
    }
 
    public Node getRight() {
        return right;
    }
 
    public void setRight(Node right) {
        this.right = right;
    }
 
    public int getData() {
        return data;
    }
 
    public void setData(int data) {
        this.data = data;
    }
}