import java.util.Scanner;

public class lab0202 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] a = new int[n][n];
        int[][] b = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = sc.nextInt();
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                b[i][j] = sc.nextInt();
            }
        }

        int[][] res = multiply(a, b);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(res[i][j]);
                if (!(i == n - 1 && j == n - 1)) {
                    System.out.print(" ");
                }
            }
        }
    }

    private static int[][] multiply(int[][] a, int[][] b) {
        int n = a.length;
        if (n <= 16) {
            return traditionalMultiply(a, b);
        }

        int size = 1;
        while (size < n) {
            size <<= 1;
        }

        if (size != n) {
            a = padMatrix(a, size);
            b = padMatrix(b, size);
        }

        int[][] result = strassenMultiply(a, b, size);

        if (size != n) {
            result = extractMatrix(result, n);
        }

        return result;
    }

    private static int[][] traditionalMultiply(int[][] a, int[][] b) {
        int n = a.length;
        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    res[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return res;
    }

    private static int[][] strassenMultiply(int[][] a, int[][] b, int n) {
        if (n == 1) {
            return new int[][]{{a[0][0] * b[0][0]}};
        }

        int newSize = n / 2;
        int[][] res = new int[n][n];

        int[][] a11 = new int[newSize][newSize];
        int[][] a12 = new int[newSize][newSize];
        int[][] a21 = new int[newSize][newSize];
        int[][] a22 = new int[newSize][newSize];

        int[][] b11 = new int[newSize][newSize];
        int[][] b12 = new int[newSize][newSize];
        int[][] b21 = new int[newSize][newSize];
        int[][] b22 = new int[newSize][newSize];

        splitMatrix(a, a11, a12, a21, a22, newSize);
        splitMatrix(b, b11, b12, b21, b22, newSize);

        int[][] m1 = strassenMultiply(addMatrix(a11, a22, newSize), addMatrix(b11, b22, newSize), newSize);
        int[][] m2 = strassenMultiply(addMatrix(a21, a22, newSize), b11, newSize);
        int[][] m3 = strassenMultiply(a11, subtractMatrix(b12, b22, newSize), newSize);
        int[][] m4 = strassenMultiply(a22, subtractMatrix(b21, b11, newSize), newSize);
        int[][] m5 = strassenMultiply(addMatrix(a11, a12, newSize), b22, newSize);
        int[][] m6 = strassenMultiply(subtractMatrix(a21, a11, newSize), addMatrix(b11, b12, newSize), newSize);
        int[][] m7 = strassenMultiply(subtractMatrix(a12, a22, newSize), addMatrix(b21, b22, newSize), newSize);

        int[][] res11 = addMatrix(subtractMatrix(addMatrix(m1, m4, newSize), m5, newSize), m7, newSize);
        int[][] res12 = addMatrix(m3, m5, newSize);
        int[][] res21 = addMatrix(m2, m4, newSize);
        int[][] res22 = addMatrix(subtractMatrix(addMatrix(m1, m3, newSize), m2, newSize), m6, newSize);
        joinMatrix(res, res11, res12, res21, res22, newSize);
        return res;
    }

    private static void splitMatrix(int[][] original, int[][] a11, int[][] a12, int[][] a21, int[][] a22, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                a11[i][j] = original[i][j];
                a12[i][j] = original[i][j + size];
                a21[i][j] = original[i + size][j];
                a22[i][j] = original[i + size][j + size];
            }
        }
    }

    private static void joinMatrix(int[][] res, int[][] a11, int[][] a12, int[][] a21, int[][] a22, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                res[i][j] = a11[i][j];
                res[i][j + size] = a12[i][j];
                res[i + size][j] = a21[i][j];
                res[i + size][j + size] = a22[i][j];
            }
        }
    }

    private static int[][] addMatrix(int[][] a, int[][] b, int size) {
        int[][] res = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                res[i][j] = a[i][j] + b[i][j];
            }
        }
        return res;
    }

    private static int[][] subtractMatrix(int[][] a, int[][] b, int size) {
        int[][] res = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                res[i][j] = a[i][j] - b[i][j];
            }
        }
        return res;
    }

    private static int[][] padMatrix(int[][] matrix, int size) {
        int n = matrix.length;
        int[][] res = new int[size][size];
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix[i], 0, res[i], 0, n);
        }
        return res;
    }

    private static int[][] extractMatrix(int[][] matrix, int n) {
        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix[i], 0, res[i], 0, n);
        }
        return res;
    }
}
