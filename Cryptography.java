import java.util.Scanner;
import java.math.*;

public class Cryptography {

    // RSA encryption and decryption with prime number.

    public static void main(String[] args) {

        Scanner Sc = new Scanner(System.in);
        int first_Prime_Num, second_Prime_Num, f, public_Key_1, public_Key_2, greatest_Common_Divisor, k,
                private_Key = 1;
        System.out.println("enter number please");
        first_Prime_Num = Sc.nextInt();

        System.out.println("enter another number pleass");
        second_Prime_Num = Sc.nextInt();

        if (first_Prime_Num < 2 || second_Prime_Num < 2)
            System.out.println("enter another number pleas");
        else {

            for (int i = 2; i <= first_Prime_Num - 1; i++) {
                if (first_Prime_Num % i == 0)
                    first_Prime_Num--;
            }
            System.out.println("first prime number = " + first_Prime_Num);
            for (f = 2; f <= second_Prime_Num - 1; f++) {
                if (second_Prime_Num % f == 0)
                    second_Prime_Num--;
            }
            System.out.println("second prime number = " + second_Prime_Num);

            // First part of public key:

            public_Key_1 = first_Prime_Num * second_Prime_Num;
            System.out.println("firt public key = " + public_Key_1);

            // second part of public key:
            public_Key_2 = (first_Prime_Num - 1) * (second_Prime_Num - 1);
            System.out.println("second public key = " + public_Key_2);

            // greatest common divisor (m,e)=1
            for (greatest_Common_Divisor = 2; greatest_Common_Divisor < public_Key_2; greatest_Common_Divisor++) {
                if (public_Key_2 % greatest_Common_Divisor != 0)
                    break;
            }
            System.out.println("greatest common divisor = " + greatest_Common_Divisor);

            // Private key (d stands for decrypt)
            for (k = 0; k < public_Key_2; k++) {
                private_Key = (public_Key_2 * k + 1) / greatest_Common_Divisor;
                if ((private_Key * greatest_Common_Divisor) % public_Key_2 == 1 % public_Key_2)
                    break;
            }
            System.out.println("private key = " + private_Key);
            Scanner obj = new Scanner(System.in);
            System.out.println("enter the string");
            String read_String = obj.nextLine();
            char array_Of_String[] = read_String.toCharArray();
            long array_of_ASCII_String[] = new long[array_Of_String.length];
            long array_Of_Encryption[] = new long[array_Of_String.length];
            for (int l = 0; l < array_Of_String.length; l++) {
                array_of_ASCII_String[l] = (long) array_Of_String[l];
                array_Of_Encryption[l] = (long) (Math.pow(array_of_ASCII_String[l], greatest_Common_Divisor));
                array_Of_Encryption[l] = array_Of_Encryption[l] % public_Key_1;
                System.out.print((char) (array_Of_Encryption[l] % 256));
            }
            
            BigInteger array_Of_Decryption1[] = new BigInteger[array_Of_String.length];
            BigInteger array_Of_Decryption2[] = new BigInteger[array_Of_String.length];

            System.out.println("");

            for (int v = 0; v < array_Of_String.length; v++) {
                array_Of_Decryption1[v] = BigInteger.valueOf(array_Of_Encryption[v]);
                array_Of_Decryption2[v] = array_Of_Decryption1[v].modPow(BigInteger.valueOf(private_Key),
                        BigInteger.valueOf(public_Key_1));
                System.out.print((char) (array_Of_Decryption2[v].intValue()));

            }
        }

    }
}
