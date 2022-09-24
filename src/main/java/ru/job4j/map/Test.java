package ru.job4j.map;

public class Test {

    public static String binary(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 31; i++) {
            sb.append(num % 2 == 0 ? 0 : 1);
            sb.append((i + 1) % 8 == 0 ? " " : "");
            num /= 2;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
//        int one = 1549595841;
//        System.out.println(binary(one));
//        int two = one >>> 16;
//        System.out.println(binary(two));
//        int three = one ^ two;
//        System.out.println(binary(three));
//        int four = three & 15;
//        System.out.println(four);

//        int n = 256;
//        int sum = 0;
//        while (n != 0) {
//            sum += (n % 10);
//            n /= 10;
//        }
//        System.out.println(sum + " ");

//    Integer[] in = new Integer[]{1, 2, 3};
//        System.out.println(in);
////        Integer[] out = new Integer[6];
////        System.arraycopy(in, 0, out, 0, 3);
//        Integer[] out = Arrays.copyOf(in, 6);
//        for (Integer el : out) {
//            System.out.println(el);
//        }


    }
}
