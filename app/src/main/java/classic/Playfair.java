package classic;

import java.util.*;
import java.io.*;

public class Playfair extends Crypto {

    char p[][];

    public Playfair() {
        super("playfair");
    }

    private boolean isWritten(char c, String m) {
        for (int i = 0; i < m.length(); i++)
            if (c == m.charAt(i))
                return false;
        return true;
    }

    public char[][] getP(String m) {
        ArrayList arr = new ArrayList();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'j')
                continue;
            arr.add(chars[i]);
        }


        p = new char[5][5];
        int col = 0, row = -1;
        for (int i = 0; i < m.length(); i++) {
            if (col % 5 == 0) {
                col = 0;
                row++;
            }
            p[row][col] = m.charAt(i);
            for (int k = 0; k < arr.size(); k++) {
                if (p[row][col] == ((Character) (arr.get(k))))
                    arr.remove(k);
            }
            col++;
        }

        int l = 25 - m.length();
        for (int i = 0; i < l; i++) {
            if (col % 5 == 0) {
                col = 0;
                if (row < 4)
                    row++;
            }


            p[row][col] = (Character) arr.get(i);
            col++;
        }

        return p;
    }

    private String setupKey(String m) {
        m = m.toLowerCase();
        m = m.trim();

        return m;
    }

    public String setupMessage(String m) {
        m = m.toLowerCase();
        m = m.trim();
        String s = "";
        int l = m.length();
        for (int i = 0; i < l; i += 1) {
            if (m.charAt(i) != ' ') {
                if (m.charAt(i) == 'j')
                    s += 'i' + "";
                else
                    s += m.charAt(i);
            }
        }

        if (s.length() % 2 == 0)
            l = s.length();
        else
            l = s.length() - 1;
        for (int i = 0; i < l; i += 2) {

            if (s.charAt(i) == s.charAt(i + 1)) {
                s = s.substring(0, i) + "x" + s.substring(i, s.length());
                l = s.length() - 1;
            }

        }

        if (s.length() % 2 != 0)
            s += "x";


        return s;
    }

    public String encrypt1(String m, Object k1) {
        m = setupMessage(m);
        k1 = setupKey((String) k1);
        String c = "";
        p = getP((String) k1);
        int row1 = 0, col1 = 0, row2 = 0, col2 = 0;
        for (int i = 0; i < m.length(); i += 2) {
            for (int r = 0; r < 5; r++)
                for (int l = 0; l < 5; l++) {
                    if (p[r][l] == m.charAt(i)) {
                        row1 = r;
                        col1 = l;
                        break;
                    }
                }

            for (int r = 0; r < 5; r++)
                for (int l = 0; l < 5; l++) {
                    if (p[r][l] == m.charAt(i + 1)) {
                        row2 = r;
                        col2 = l;
                        break;
                    }
                }
            if (col1 == col2) {
                row1 = (row1 + 1) % 5;
                row2 = (row2 + 1) % 5;
            } else if (row1 == row2) {
                col1 = (col1 + 1) % 5;
                col2 = (col2 + 1) % 5;
            } else {
                int col = col1;
                col1 = col2;
                col2 = col;

            }
            c += p[row1][col1] + "" + p[row2][col2] + "";
        }
        return c;
    }

    public String decrypt(String m, Object k1) {
        k1 = setupKey((String) k1);
        String c = "";
        p = getP((String) k1);
        int row1 = 0, col1 = 0, row2 = 0, col2 = 0;
        for (int i = 0; i < m.length(); i += 2) {
            for (int r = 0; r < 5; r++)
                for (int l = 0; l < 5; l++) {
                    if (p[r][l] == m.charAt(i)) {
                        row1 = r;
                        col1 = l;
                        break;
                    }
                }

            for (int r = 0; r < 5; r++)
                for (int l = 0; l < 5; l++) {
                    if (p[r][l] == m.charAt(i + 1)) {
                        row2 = r;
                        col2 = l;
                        break;
                    }
                }
            if (col1 == col2) {
                row1 = (row1 - 1) % 5;
                row2 = (row2 - 1) % 5;
                if (row1 < 0)
                    row1 = 4;
                if (row2 < 0)
                    row2 = 4;

            } else if (row1 == row2) {
                col1 = (col1 - 1) % 5;
                col2 = (col2 - 1) % 5;

                if (col1 < 0)
                    col1 = 4;
                if (col2 < 0)
                    col2 = 4;

            } else {
                int col = col1;
                col1 = col2;
                col2 = col;

            }
            c += p[row1][col1] + "" + p[row2][col2] + "";
        }
        return c;
    }

    @Override
    public String encrypt(String m, int key) {
        return null;
    }

    public String decrypt(String m, int k1) {
        String ss = setupKey(k1 + "");
        k1 = Integer.parseInt(ss);
        String c = "";
        p = getP(k1 + "");
        int row1 = 0, col1 = 0, row2 = 0, col2 = 0;
        for (int i = 0; i < m.length(); i += 2) {
            for (int r = 0; r < 5; r++)
                for (int l = 0; l < 5; l++) {
                    if (p[r][l] == m.charAt(i)) {
                        row1 = r;
                        col1 = l;
                        break;
                    }
                }

            for (int r = 0; r < 5; r++)
                for (int l = 0; l < 5; l++) {
                    if (p[r][l] == m.charAt(i + 1)) {
                        row2 = r;
                        col2 = l;
                        break;
                    }
                }
            if (col1 == col2) {
                row1 = (row1 - 1) % 5;
                row2 = (row2 - 1) % 5;
                if (row1 < 0)
                    row1 = 4;
                if (row2 < 0)
                    row2 = 4;

            } else if (row1 == row2) {
                col1 = (col1 - 1) % 5;
                col2 = (col2 - 1) % 5;

                if (col1 < 0)
                    col1 = 4;
                if (col2 < 0)
                    col2 = 4;

            } else {
                int col = col1;
                col1 = col2;
                col2 = col;

            }
            c += p[row1][col1] + "" + p[row2][col2] + "";
        }
        return c;
    }

    @Override
    public String encrypt(String m, Object key) {
        return null;
    }

//    public static void test(Crypto c) {
//        String k = c.read("playfair_key.txt");
//        String p = c.read("plain.txt");
//        System.out.println("Play fair ");
//        String enc = c.encrypt(p, k);
//
//        c.write("playfair_cipher.txt", enc);
//        System.out.println("Key = " + k);
//        System.out.println("Plain TXT : " + p);
//        System.out.println("Cipher TXT : " + enc);
//        System.out.println("Decrypt The Cipher TXT : " + c.decrypt(enc, k));
//
//    }


}
