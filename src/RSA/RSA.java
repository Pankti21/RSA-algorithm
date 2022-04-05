package RSA;

import java.util.Scanner;
import java.math.*;

public class RSA {
    static int p;
    static int q;

    public static void main(String[] args){
        System.out.println("RSA");
        System.out.print("Enter the prime numbers, p and q:");
        Scanner sc = new Scanner(System.in);
        p = sc.nextInt();
        q = sc.nextInt();

        //validate p and q
        primeValidation(p,q);

        int n = p * q;
        int n2 = (p-1) * (q-1);
        System.out.println("\nCalculating RSA values...");

        //calculate keys
        int e = calculateE(n,n2);
        int d = calculateD(n,n2,e);

        System.out.println("\nPublic RSA key is ("+e+","+n+").");
        System.out.println("Private RSA key is ("+d+","+n+").");

        int c = encryption(e,n);
        int m = decryption(d, n, c);

        System.out.println("\nEncrypting m…\n" + "The ciphertext c is "+c);
        System.out.println("\nDecrypting c ….\n" + "The plaintext m is "+m);

    }

    private static int decryption(int d, int n, int c) {
        int m = func(c,d,n);
        return m;
    }

    private static int func(int text, int key, int n) {
        long result;
        if(key%2==0){
            int num = key/2;
            result = 1;
            for(int i=0;i<num;i++){
                result = (long) (result * (Math.pow(text,2)%n))%n;
            }
            result = result % n;
        }
        else {
            int num = (key-1)/2;
            result = 1;
            for(int i=0;i<num;i++){
                result = (long) (result * (Math.pow(text,2)%n))%n;
            }
            result = result * (text%n)%n;
            result = result % n;
        }
        return (int) result;
    }

    private static int encryption(int e, int n) {
        System.out.print("\nEnter the plaintext message m (an integer):");
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int c = (int) Math.pow(m,e)%n;
        return c;
    }

    private static int calculateD(int n, int m, int e) {
        int temp = m+1;
        int i = 1;
        while(temp%e!=0){
            temp = (m*i)+1;
            i++;
        }
        return (temp/e);
    }

    private static int calculateE(int n, int m) {
        int e = 0;
        boolean eMatch = false;
        for(int i=2;i<n;i++){
            e = i;
            if(m%e!=0){
                eMatch = true;
                break;
            }
        }
        return e;
    }

    private static void primeValidation(int p, int q) {
        Scanner sc = new Scanner(System.in);
        //validate if p is prime
        boolean isPrime = validatePrime(p);
        while (!isPrime){
            System.out.println(p+ " is not a prime number. Try again! \nEnter p:");
            p = sc.nextInt();
            isPrime = validatePrime(p);
        }

        //validate if q is prime
        isPrime = validatePrime(q);
        while (!isPrime){
            System.out.println(q+ " is not a prime number. Try again! \nEnter q:");
            q = sc.nextInt();
            isPrime = validatePrime(q);
        }
    }

    private static boolean validatePrime(int p) {
        boolean isPrime = true;
        for (int i=2;i<p/2;i++){
            if(p%i==0){
                isPrime=false;
            }
        }
        return isPrime;
    }
}
