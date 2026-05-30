public class CO2_FenwickTree {

    private int[] BIT;
    private int n;

    public CO2_FenwickTree(int size) {
        n = size;
        BIT = new int[n + 1];
    }

    void update(int index, int value) {
        while (index <= n) {
            BIT[index] += value;
            index += index & (-index);
        }
    }

    int query(int index) {
        int sum = 0;

        while (index > 0) {
            sum += BIT[index];
            index -= index & (-index);
        }

        return sum;
    }

    int rangeQuery(int left, int right) {
        return query(right) - query(left - 1);
    }

    public static void main(String[] args) {

        int[] scores = {50, 40, 70, 30, 60};

        CO2_FenwickTree tree =
                new CO2_FenwickTree(scores.length);

        for (int i = 0; i < scores.length; i++)
            tree.update(i + 1, scores[i]);

        System.out.println(
                "Total Score (Athlete 1 to 5): "
                        + tree.query(5));

        System.out.println(
                "Score Range (2 to 4): "
                        + tree.rangeQuery(2, 4));
    }
}