class Athlete {
    int athleteId;
    String name;

    Athlete(int athleteId, String name) {
        this.athleteId = athleteId;
        this.name = name;
    }
}

class AVLNode {
    Athlete athlete;
    AVLNode left, right;
    int height;

    AVLNode(Athlete athlete) {
        this.athlete = athlete;
        height = 1;
    }
}

public class CO1_AthleteAVL {

    AVLNode root;

    int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    int getBalance(AVLNode node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    AVLNode insert(AVLNode node, Athlete athlete) {

        if (node == null)
            return new AVLNode(athlete);

        if (athlete.athleteId < node.athlete.athleteId)
            node.left = insert(node.left, athlete);
        else
            node.right = insert(node.right, athlete);

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && athlete.athleteId < node.left.athlete.athleteId)
            return rightRotate(node);

        if (balance < -1 && athlete.athleteId > node.right.athlete.athleteId)
            return leftRotate(node);

        if (balance > 1 && athlete.athleteId > node.left.athlete.athleteId) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && athlete.athleteId < node.right.athlete.athleteId) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    void inorder(AVLNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.println(node.athlete.athleteId +
                    " - " + node.athlete.name);
            inorder(node.right);
        }
    }

    public static void main(String[] args) {

        CO1_AthleteAVL tree = new CO1_AthleteAVL();

        tree.root = tree.insert(tree.root,
                new Athlete(103, "Virat"));

        tree.root = tree.insert(tree.root,
                new Athlete(101, "Dhoni"));

        tree.root = tree.insert(tree.root,
                new Athlete(105, "Rohit"));

        tree.root = tree.insert(tree.root,
                new Athlete(102, "Hardik"));

        System.out.println("Athlete Rankings:");
        tree.inorder(tree.root);
    }
}