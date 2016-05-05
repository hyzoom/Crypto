package classic;

import java.util.*;
import java.io.*;

public class CaeserCipher extends Crypto {
    public CaeserCipher() {
        super("caesercipher");
    }

    @Override
    public String encrypt(String m, int key) {
        return null;
    }

    @Override
    public String decrypt(String m, int key) {
        return null;
    }

    public String encrypt(String message, Object k) {
        int key = Integer.parseInt((String) k);
        message = message.toLowerCase();
        message.trim();
        String m = "";

        for (int i = 0; i < message.length(); i++) {
            for (int j = 0; j < chars.length; j++)
                if (message.charAt(i) == chars[j])
                    m += chars[(j + key) % 26] + "";
        }
        return m;
    }

    public String decrypt(String message, Object k) {
        int key = Integer.parseInt((String) k);
        String m = "";
        for (int i = 0; i < message.length(); i++) {
            for (int j = 0; j < chars.length; j++)
                if (message.charAt(i) == chars[j]) {
                    if (j - key >= 0)
                        m += chars[(j - key)] + "";
                    else
                        m += chars[26 + (j - key)];
                }
        }
        return m;
    }

    public static void test(Crypto c) {
        String k = c.read("caesercipher_key.txt");
        String p = c.read("plain.txt");
        // System.out.println(c.filename.toUpperCase()+" :-");
        String enc = c.encrypt(p, k);

        c.write("caesercipher_cipher.txt", enc);
        System.out.println("Key = " + k);
        System.out.println("Plain TXT : " + p);
        System.out.println("Cipher TXT : " + enc);
        System.out.println("Decrypt The Cipher TXT : " + c.decrypt(enc, k));
    }

    public static void main(String[] args) {
        CaeserCipher cc = new CaeserCipher();
        test(cc);
    }

}

