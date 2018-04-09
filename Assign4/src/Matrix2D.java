
class Matrix2D { // Integer type 2D Matrix

    private int[][] matArr; // 2D array

    // public constructor
    Matrix2D() {

        setMatArr(new int[1][1]);
    }

    Matrix2D(int r, int c) {
        setMatArr(new int[r][c]);
    }

    // setter-getters..
    public int[][] getMatArr() {
        return matArr;
    }

    public void setMatArr(int[][] matArr) {
        this.matArr = matArr;
    }

    /**
     * Insert value particular position
     * 
     * @param x
     *            row index
     * @param y
     *            column index
     * @param v
     *            value
     */
    void addValue(int x, int y, int v) {
        ensureCapacity(x, y);
        matArr[x][y] = v;
    }

    // Ensures that elements can fit within the 2D Matrix
    public void ensureCapacity(int x, int y) {
        if (x >= matArr.length) {
            int[][] tmp = matArr;
            matArr = new int[x + 1][];
            System.arraycopy(tmp, 0, matArr, 0, tmp.length);
            for (int i = x; i < x + 1; i++) {
                matArr[i] = new int[y];
            }
        }

        if (y >= matArr[x].length) {
            int[] tmp = matArr[x];
            matArr[x] = new int[y + 1];
            System.arraycopy(tmp, 0, matArr[x], 0, tmp.length);
        }

    }

    // get value from particular position
    int getValue(int x, int y) {
        validateIndex(x, y);
        return matArr[x][y];
    }
    
    // gets a particular row as Integer array from 2D matrix
    int[] getRow(int x) {
        validateIndex(x, 0);
        return matArr[x];
    }

    int getNumberOfRows() {
        return matArr.length;
    }

    // prints the 2D Matrix
    void print() {
        for (int x = 0; x < matArr.length; x++) {
            for (int y = 0; y < matArr[x].length; y++)
                System.out.print(matArr[x][y] + " ");
            System.out.println();
        }
    }

    // throw an IllegalArgumentException unless {@code 0 <= (x,y) < |V|}
    private void validateIndex(int x, int y) {
        int V = matArr.length;
        if (x < 0 || x >= V)
            throw new IllegalArgumentException("Index " + x
                    + " is not between 0 and " + (V - 1));
        if (y < 0 || y >= V)
            throw new IllegalArgumentException("Index " + y
                    + " is not between 0 and " + (V - 1));
    }
}