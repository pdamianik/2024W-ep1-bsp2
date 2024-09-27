package at.ac.tuwien.sem2024w.prop.ep1;

public class Main {
    public static void main(String[] args) {
        short result;
        String test = "Blaukraut";

        result = (short)getIntegerRoot(25);

        System.out.println(getIntegerRoot(144));
        assert getIntegerRoot(144) == 12;
        System.out.println(getIntegerRoot(13));
        assert getIntegerRoot(13) == -1;
        System.out.println(getIntegerRoot(1));
        assert getIntegerRoot(1) == 1;
        System.out.println(getThird("toss", "a", "coin"));
        assert getThird("toss", "a", "coin").equals("alle unterschiedlich");
        System.out.println(getThird("Blaukraut", "bleibt", test));
        assert getThird("Blaukraut", "bleibt", test).equals("bleibt");
        System.out.println(getThird("badger", "badger", "badger"));
        assert getThird("badger", "badger", "badger").equals("alle gleich");
        System.out.println(replaceA("TU Wien"));
        assert replaceA("TU Wien").equals("TU Wien");
        System.out.println(replaceA("Hubba bubba!"));
        assert replaceA("Hubba bubba!").equals("Hubb1 bubb2!");
        System.out.println(replaceA("aaaa"));
        assert replaceA("aaaa").equals("1234");
        printBars(2);
        printBars(19);
        printBars(20);
        printBars(21);
    }

    public static int getIntegerRoot(int k) {
        if (k == 1) {
            // happy case, its just 1^2 = 1
            return 1;
        }

        // a^2 > a * 2 for all a > 1 & a is a positive integer
        for (int i = 2; i <= k/2; i++) {
            if (i * i == k) {
                return i;
            }
        }
        return -1;
    }

    public static String getThird(String a, String b, String c) {
        int lengthA = a.length(), lengthB = b.length(), lengthC = c.length();
        int length; // The length to iterate through to compare (at least two strings have to have this length)
        boolean aAndB = lengthA == lengthB, bAndC = lengthB == lengthC, aAndC = lengthA == lengthC;

        if (aAndB || aAndC) {
            length = lengthA; // a has an equal length to either b or c
        } else if (bAndC) {
            length = lengthB; // b and c are equal in length
        } else {
            return "alle unterschiedlich"; // all strings have a different length -> not equal
        }

        for (int i = 0; i < length; i++) {
            // compare strings with same length/strings that have been equal so far
            if (aAndB) {
                aAndB = a.charAt(i) == b.charAt(i);
            }
            if (bAndC) {
                bAndC = b.charAt(i) == c.charAt(i);
            }
            if (aAndC) {
                aAndC = a.charAt(i) == c.charAt(i);
            }
        }

        if (aAndB && bAndC) {
            return "alle gleich";
        } else if (aAndB) {
            return c;
        } else if (bAndC) {
            return a;
        } else if (aAndC) {
            return b;
        } else {
            return "alle unterschiedlich";
        }
    }

    public static String simpleReplaceA(String s) {
        int counter = 0; // counter for a occurrences
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a') { // search for a
                counter += 1;
                // replace a with prefix + counter + suffix
                s = s.substring(0, i) + counter + s.substring(i + 1);
            }
        }
        return s;
    }

    public static String replaceA(String s) {
        int counter = 0; // counter for a occurrences
        // cant use either the String constructor or the Math class :(
        // lg is basically the char length of counter
        int nextHighestPower10 = 10, lg = 1;
        int lengthS = s.length();
        for (int i = 0; i < lengthS; i++) {
            if (s.charAt(i) == 'a') { // search for a
                counter += 1;

                if (i + lg > s.length()) {
                    // can't get the substring beyond the string length
                    s = s.substring(0, i) + counter;
                } else {
                    s = s.substring(0, i) + counter + s.substring(i + 1);
                }

                // increment the counter length
                if (counter >= nextHighestPower10) {
                    nextHighestPower10 *= 10;
                    lg++;
                }

                // increment index and string length to skip added characters
                i += lg - 1;
                lengthS += lg - 1;
            }
        }
        return s;
    }

    public static void printBars(int i) {
        // print all numbers not divisible by 3
        for (int j = 1; j <= i; j += 3) {
            System.out.print(j + "-");
            if (j + 1 <= i) {
                System.out.print((j + 1) + "+");
            }
        }

        if (1 <= i) {
            System.out.println();
        }

        boolean plus = true;
        // print all numbers divisible by 3
        for (int j = 3; j <= i; j += 3) {
            System.out.print(j);
            if (plus) {
                System.out.print("+");
            } else {
                System.out.print("-");
            }
            plus = !plus;
        }

        if (3 <= i) {
            System.out.println();
        }
    }
}