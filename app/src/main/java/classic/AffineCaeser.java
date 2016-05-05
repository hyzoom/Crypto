package classic;

import java.util.*;
import java.io.*;
public class AffineCaeser extends Crypto {
    int a=0;

    public AffineCaeser(){
        super("affine");
    }

    @Override
    public String encrypt(String m, int key) {
        return null;
    }

    @Override
    public String decrypt(String m, int key) {
        return null;
    }

    public String encrypt(String message,Object k){
        int key=Integer.parseInt((String)k);
        message=message.toLowerCase();
        message.trim();
        String m="";

        for(int i=0; i<message.length(); i++){
            for(int j=0 ;j<chars.length ; j++)
                if(message.charAt(i)==chars[j])
                    m+=chars[(a*j+key)%26]+"";
        }
        return m;
    }



    public String decrypt(String message,Object k){
        int key=Integer.parseInt((String)k);
        int ain=getA();
        String m="";

        for(int i=0; i<message.length(); i++){
            for(int j=0 ;j<chars.length ; j++)
                if(message.charAt(i)==chars[j])
                {
                    if(j-key>=0)
                    {
                        m += chars[ain * (j - key) % 26] + "";

                    }
                    else{
                        m+=chars[26-(ain*(j-key)*-1)%26];

                    }
                }
        }
        return m;
    }


    public int getA(){
        int m=a;
        int c=1;
        while((m%26!=1)){
            c++;
            m=a*c;
        }
        return c;
    }
    public static void test(Crypto c){
        String k=c.read("affine_key.txt");
        String p=c.read("plain.txt");
        //System.out.println(c.filename.toUpperCase()+" :-");
        String enc=c.encrypt(p, k);

        c.write(c.filename+"/"+c.filename+"_cipher.txt", enc);
        System.out.println("Key = "+k);
        System.out.println("Plain TXT : "+p);
        System.out.println("Cipher TXT : "+enc);
        System.out.println("Decrypt The Cipher TXT : "+c.decrypt(enc, k));


    }

    public static void main(String[]args){
        AffineCaeser ac = new AffineCaeser();
        test(ac);
    }

}

abstract class Crypto {

    String filename;
    char chars[];
    PrintWriter out;
    Scanner in;


    public Crypto(String s) {
        filename = s;
        chars = new char[26];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (i + 97);
        }
    }

    public abstract String encrypt(String m, int key);

    public abstract String decrypt(String m, int key);

    public abstract String encrypt(String m, Object key);

    public abstract String decrypt(String m, Object key);

    public String read(String s) {
        String m = "";
        try {
            in = new Scanner(new File(s));
            while (in.hasNextLine()) {
                m = m + in.nextLine();
            }
            in.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return m;
    }

    public void write(String m, String s) {
        try {
            out = new PrintWriter(m);
            out.write(s);
            out.close();
        } catch (IOException e) {
            System.out.println(" ");
        }
    }
}







