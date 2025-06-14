/* LinkedIn :: @aruppatra*/

/* Question Link :: https://codeforces.com/problemset/problem/2107/A*/

/* approach ::
    -> if all elements are equals then there are no solution.
    -> if there in the array different numbers are present 
        1. then all the max number put in the same sequeance then gcd of that swquesnces is that number itself. (mark these numbers as 1)
        2. rest of the numbers are in another sequesnce the gcd of that sequence is never equal to another one always less. (mark these numbers as 2)    
*/ 

// Solutions :: 
import java.io.*;
import java.util.*;

public class A2107 {
    static final int MOD = 1_000_000_007;
    static final long INF = Long.MAX_VALUE;
    
    // ---------- Fast Input/Output ----------
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer st;

    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
        return st.nextToken();
    }
    static int nextInt() throws IOException { return Integer.parseInt(next()); }
    static long nextLong() throws IOException { return Long.parseLong(next()); }
    static double nextDouble() throws IOException { return Double.parseDouble(next()); }
    static String nextLine() throws IOException { return br.readLine(); }

    // ---------- Bit Manipulation ----------
    static int setBitCount(long num) { return Long.bitCount(num); }
    static int msbIndex(long num) { return 63 - Long.numberOfLeadingZeros(num); } // returns -1 for 0
    static int lsbIndex(long num) { return Long.numberOfTrailingZeros(num); }     // returns 64 for 0
    static boolean isBitSet(long num, int bit) { return ((num >> bit) & 1) == 1; }
    static long setBit(long num, int bit) { return num | (1L << bit); }
    static long unsetBit(long num, int bit) { return num & ~(1L << bit); }
    static long toggleBit(long num, int bit) { return num ^ (1L << bit); }

    // ---------- Math Functions ----------
    static long gcd(long a, long b) { return b == 0 ? a : gcd(b, a % b); }
    static long lcm(long a, long b) { return (a / gcd(a, b)) * b; }

    static long modAdd(long a, long b) { return ((a % MOD + b % MOD) % MOD + MOD) % MOD; }
    static long modSub(long a, long b) { return ((a % MOD - b % MOD) % MOD + MOD) % MOD; }
    static long modMul(long a, long b) { return ((a % MOD) * (b % MOD)) % MOD; }

    static long modPow(long base, long exp) {
        long result = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) result = modMul(result, base);
            base = modMul(base, base);
            exp >>= 1;
        }
        return result;
    }

    static long modInv(long a) {
        return modPow(a, MOD - 2); // Only if MOD is prime
    }

    static long modDiv(long a, long b) {
        return modMul(a, modInv(b));
    }

    static boolean isPrime(long n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (long i = 5; i * i <= n; i += 6)
            if (n % i == 0 || n % (i + 2) == 0) return false;
        return true;
    }

    // ---------- Combinatorics ----------
    static long nCr(int n, int r) {
        if (r > n) return 0;
        if (r > n - r) r = n - r;
        long res = 1;
        for (int i = 0; i < r; i++) {
            res = modMul(res, n - i);
            res = modDiv(res, i + 1);
        }
        return res;
    }

    // ---------- Utility ----------
    static void printList(List<Long> list) {
        for (long val : list) out.print(val + " ");
        out.println();
    }
    static void printList(long[] list) {
        for (long val : list) out.print(val + " ");
        out.println();
    }
    static void printList(double[] list) {
        for (double val : list) out.print(val + " ");
        out.println();
    }
    static void printList(String[] list) {
        for (String str : list) out.print(str + " ");
        out.println();
    }
    // --------------- Fill Arrays ---------------------
    static void fillArray(long[] arr) throws IOException {
        for (int i = 0; i < arr.length; i++) { arr[i] = nextLong(); }
    }

    static void fillArray(double[] arr) throws IOException {
        for (int i = 0; i < arr.length; i++) { arr[i] = nextDouble(); }
    }
    static void fillArray(String[] arr) throws IOException {
        for (int i = 0; i < arr.length; i++) { arr[i] = next(); }
    }
    // ---------------- Fill Queries Array --------------------
    static void fillQueary(int[][] arr) throws IOException {
        if(arr[0].length == 3){
            for (int i = 0; i < arr.length; i++) { 
                arr[i][0] = nextInt(); 
                arr[i][1] = nextInt(); 
                arr[i][2] = nextInt(); 
            }
        }
        if(arr[0].length == 2){
            for (int i = 0; i < arr.length; i++) { 
                arr[i][0] = nextInt(); 
                arr[i][1] = nextInt();             }
        }
    }

    // ---------- Core Solve Logic ----------
    static void solve() throws IOException {
        // write your code here : 
        int n = nextInt();
        long[] a = new long[n];
        fillArray(a);

        long gcd1 = Arrays.stream(a).max().getAsLong();
        long gcd2 = 0;
        for(long i : a){
            if(i != gcd1) gcd2 = gcd(gcd2, i);
        }
        if(gcd2 == 0) System.out.println("NO");
        else if(gcd1 != gcd2){
            System.out.println("YES");
            for(long i : a){
                if(i == gcd1) System.out.print(1+" ");
                else System.out.print(2+" ");
            }
            System.out.println();
        }
        else System.out.println("NO");
        
    }

    // ---------- Main Driver ----------
    public static void main(String[] args) throws IOException {
        // int testCases = 1; // when there is no test-cases 
        int testCases = nextInt();
        while (testCases-- > 0) {
            solve();
        }
        out.flush();
    }
}
