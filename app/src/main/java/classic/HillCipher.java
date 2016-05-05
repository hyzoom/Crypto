package classic;

import java.util.*;
import java.io.*;

public class HillCipher extends Crypto {

    int p[][];
    Crypto crypto;
    HillCipher hc;

    public HillCipher() {
        super("hillcipher");
    }


    public int[][] getP(String m) {
        int rows = 0;
        if (m.length() % 3 == 0)
            rows = (m.length() / 3);
        else {
            rows = (m.length() + (3 - m.length() % 3)) / 3;
            for (int i = 0; i < m.length() % 3; i++)
                m += "x";
        }

        int[][] p = new int[rows][3];

        int counter = 0;
        for (int i = 0; i < p.length; i++)
            for (int j = 0; j < p[i].length; j++) {
                p[i][j] = (int) (m.charAt(counter)) - 97;
                counter++;
            }

        return p;
    }


    public String multiply(int k[][], int p[]) {
        String c = "";
        int sum = 0;
        for (int i = 0; i < k.length; i++) {
            for (int j = 0; j < p.length; j++) {
                sum += p[j] * k[j][i];
            }
            c += chars[sum % 26] + "";
            sum = 0;
        }
        return c;
    }

    private String setupMessage(String m) {
        m = m.toLowerCase();
        m = m.trim();
        String s = "";
        int l = m.length() - 1;
        for (int i = 0; i < l; i++) {
            if (m.charAt(i) != ' ')
                s += m.charAt(i) + "";
        }
        s += m.charAt(m.length() - 1) + "";
        return s;
    }


    public String encrypt(String m, Object k1) {
        m = setupMessage(m);
        String c = "";
        int k[][] = (int[][]) k1;
        p = getP(m);
        for (int i = 0; i < p.length; i++)
            c += multiply(k, p[i]);
        return c;
    }

    public String decrypt(String m, Object k1) {
        String c = "";
        int k[][] = (int[][]) k1;
        p = getP(m);
        for (int i = 0; i < p.length; i++)
            c += multiply(k, p[i]);
        return c;
    }

    public void test() {
        HillCipher hc = new HillCipher();
        crypto = hc;
        System.out.println("HILLCipher ");
        int k[][] = new int[3][3];
        int k1[][] = new int[3][3];
        String file = crypto.read("hillcipher_key.txt");
        String p = crypto.read("plain.txt");
        System.out.println("Plain text is " + p);
        StringTokenizer t = new StringTokenizer(file, ",");
        System.out.println("key is ");
        for (int i = 0; i < k.length; i++) {
            for (int j = 0; j < k[i].length; j++) {
                k[i][j] = Integer.parseInt(t.nextToken());
                System.out.print(k[i][j] + "\t");

            }
            System.out.println();
        }
        String c = crypto.encrypt(p, k);
        crypto.write("hillcipher_cipher.txt", c);
        System.out.println("Cipher :- " + c);
        file = crypto.read("hillcipher_key_inverse.txt");
        t = new StringTokenizer(file, ",");
        System.out.println("key reverse is ");
        for (int i = 0; i < k.length; i++) {
            for (int j = 0; j < k[i].length; j++) {
                k1[i][j] = Integer.parseInt(t.nextToken());
                System.out.print(k1[i][j] + "\t");
            }
            System.out.println();
        }
        String c1 = crypto.read("hillcipher_cipher.txt");
        c1 = crypto.decrypt(c, k1);
        System.out.println("Decrypt The Cipher : " + c1);
    }

    public static void main(String[] args) {
        HillCipher hc = new HillCipher();
        hc.test();
    }

    @Override
    public String encrypt(String m, int key) {
        return null;
    }

    @Override
    public String decrypt(String m, int key) {
        return null;
    }
}
