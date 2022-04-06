import java.util.*;
import java.io.*;

public class WordlePoem {
    private ArrayList<String> Poem = new ArrayList<String>();

    public static void main(String[] args) {
        
        String[] a = haiku();
        for (int i = 0; i < a.length; ++i) {
            System.out.println(a[i]);
        }
        
    }

    public static String random() throws IOException {
        Scanner s = new Scanner(new File("/Users/harryliu/Desktop/data.txt"));
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNext()) {
            list.add(s.next());
        }
        s.close();
        return list.get((int) (Math.random() * list.size()));
    }

    public static int syllables(String x) {
        String vowels = "aeiouy";
        int y = 0;
        for (int i = 0; i < x.length(); ++i)
            for (int j = 0; j < vowels.length(); ++j)
                if (x.substring(i, i + 1).equals(vowels.substring(j, j + 1)))
                    ++y;
        y -= diphthongs(x);
        return y;
    }

    public static int diphthongs(String x) {
        String[] diphthongs = { "au", "oy", "oi", "ou", "oo", "ea", "ee", "ai", "ay", "ei", "oa", "ua", "ey", "you", "ie"};
        String[] special = {"fire", "able", "doing", "ien", "ugue", "agle", "queer", "ier", "being", "quai"};
        int l = 0;
        int k = 0;
        for(int i = 0; i < diphthongs.length; ++i) {
            while (x.substring(k).indexOf(diphthongs[i]) >= 0) {
                k += x.substring(k).indexOf(diphthongs[i]) + 1;
                ++l;
            }
        }
        for(int i = 2; i < x.length() - 2; ++i)
            if((x.substring(i, i + 1).equals("a") || x.substring(i, i + 1).equals("i") || x.substring(i, i + 1).equals("o") || x.substring(i, i + 1).equals("e") || x.substring(i, i + 1).equals("u")) && x.substring(i + 2, i + 3).equals("e"))
                ++l;
        for(int i = 1; i < x.length() - 3; ++i)
            if((x.substring(i, i + 1).equals("a") || x.substring(i, i + 1).equals("u") || x.substring(i, i + 1).equals("o") || x.substring(i, i + 1).equals("e")) && x.substring(i + 3, i + 4).equals("e"))
                ++l;
        for(int i = 0; i < special.length; ++i)
            if(x.indexOf(special[i]) > 0)
                --l;
        return Math.max(l, 0);
    }

    public static String[] haiku() {
        String[] x = new String[3];
        for (int i = 0; i < 3; ++i) {
            x[i] = "";
            int s = 0;
            int g = 0;
            String y = "";
            if(i % 2 == 0)
                g = 5;
            else
                g = 7;
            while (s != g) {
                try {
                    y = random();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
                if (s + syllables(y) <= g) {
                    s += syllables(y);
                    x[i] += y + " ";
                }
            }
        }
        return x;
    }
}