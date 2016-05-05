package classic;

import java.util.*;
import java.io.*;
public class Vigenere extends Crypto{

    public Vigenere(){
        super("vigenere");
    }

    public String setupKey(String k,String p){
        String s="";
        for(int i=0;i<p.length();i++)
            s+=k.charAt(i%k.length());
        return s;
    }

    public String setupMessage(String p){
        p=p.replace(" ", "");
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
       String key=(String)k;
       key=setupKey(key, m);
       m=setupMessage(m);
       String c="";
       int pindex=0,kindex=0;
       for(int i=0 ; i<m.length();i++){
           for(int j=0;j<26; j++){
               if(m.charAt(i)==chars[j])
                   pindex=j;
           }

           for(int j=0;j<26; j++){
               if(key.charAt(i)==chars[j])
                   kindex=j;
           }

           c+=chars[(pindex+kindex)%26];
       }

       return c;
    }


    public String decrypt(String m, Object k) {
       String key=(String)k;
       key=setupKey(key, m);
       m=setupMessage(m);
       String c="";
       int pindex=0,kindex=0;
       for(int i=0 ; i<m.length();i++){
           for(int j=0;j<26; j++){
               if(m.charAt(i)==chars[j])
                   pindex=j;
           }

           for(int j=0;j<26; j++){
               if(key.charAt(i)==chars[j])
                   kindex=j;
           }

           if(pindex-kindex>=0)
               c+=chars[(pindex-kindex)%26];
           else
               c+=chars[(26+pindex-kindex)%26];
       }

       return c;
    }
     public static void test(Crypto c){
	        String k=c.read("vigenere_key.txt");
	        String p=c.read("plain.txt");
	        System.out.println("Vigenere");
	        String enc=c.encrypt(p, k);
	        c.write("vigenere_cipher.txt", enc);
	        System.out.println("Key = "+k);
	        System.out.println("Plain TXT : "+p);
	        System.out.println("Cipher TXT : "+enc);
	        System.out.println("Decrypt The Cipher TXT : "+c.decrypt(enc, k));
    }
    public static void main (String[]args){
		Vigenere vg = new Vigenere();
		test(vg);
	}
}

