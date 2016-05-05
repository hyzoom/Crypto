package classic;

import java.util.*;
import java.io.*;

public class OneTimePad extends Crypto {
    public static void main(String[] args) {
        OneTimePad otp = new OneTimePad();
        test(otp);
    }


    public OneTimePad() {
        super("onetime");
    }

    public String generateKey(int l) {
        String s = "";
        Random r = new Random();
        for (int i = 0; i < l; i++) {
            s += chars[r.nextInt(25)];
        }
        return s;
    }


    public String setupMessage(String p) {
        p = p.replace(" ", "");
        p.toLowerCase();
        return p;
    }

    @Override
    public String encrypt(String m, int key) {
        return null;
    }

    @Override
    public String decrypt(String m, int key) {
        return null;
    }

    public String encrypt(String m, Object k) {
        String key = (String) k;
        m = setupMessage(m);
        String c = "";
        int pindex = 0, kindex = 0;
        for (int i = 0; i < m.length(); i++) {
            for (int j = 0; j < 26; j++) {
                if (m.charAt(i) == chars[j])
                    pindex = j;
            }

            for (int j = 0; j < 26; j++) {
                if (key.charAt(i) == chars[j])
                    kindex = j;
            }

            c += chars[(pindex + kindex) % 26];
        }

        return c;
    }


    public String decrypt(String m, Object k) {
        String key = (String) k;
        m = setupMessage(m);
        String c = "";
        int pindex = 0, kindex = 0;
        for (int i = 0; i < m.length(); i++) {
            for (int j = 0; j < 26; j++) {
                if (m.charAt(i) == chars[j])
                    pindex = j;
            }

            for (int j = 0; j < 26; j++) {
                if (key.charAt(i) == chars[j])
                    kindex = j;
            }

            if (pindex - kindex >= 0)
                c += chars[(pindex - kindex) % 26];
            else
                c += chars[(26 + pindex - kindex) % 26];
        }

        return c;
    }

    public static void test(Crypto c) {
        String k = c.read("onetime_key.txt");
        String p = c.read("plain.txt");
        System.out.println("One Time Pad");
        String enc = c.encrypt(p, k);

        c.write(c.filename + "/" + c.filename + "_cipher.txt", enc);
        System.out.println("Key = " + k);
        System.out.println("Plain TXT : " + p);
        System.out.println("Cipher TXT : " + enc);
        System.out.println("Decrypt The Cipher TXT : " + c.decrypt(enc, k));
    }


}
