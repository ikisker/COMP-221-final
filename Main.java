import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;

class Main{
    // public static final String IV_FILE_1 = "D:\\Code\\221\\COMP-221-final\\iv.txt";

    public static final String ENCRYPT_FILE = "D:\\Code\\221\\COMP-221-final\\encrypted.txt";
    public static final String DECRYPT_FILE = "D:\\Code\\221\\COMP-221-final\\decrypted.txt";
    

    public static void main(String[] args) throws NoSuchAlgorithmException, FileNotFoundException, IOException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        SecureRandom sRandom = SecureRandom.getInstanceStrong();
        byte[] iv = new byte[128/8];
        sRandom.nextBytes(iv);


        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        KeyGenerator kGen = KeyGenerator.getInstance("AES");
        SecretKey sKey = kGen.generateKey();




        encode("hello i am coding hello i am coding hello i am coding hello i am coding hello i am coding hello i am coding", ivSpec, sKey);
        decode(ivSpec, sKey);
    }

    public static void encode(String plainText, IvParameterSpec ivSpec, SecretKey sKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IOException, IllegalBlockSizeException, BadPaddingException{
        Cipher ci = Cipher.getInstance("AES/CBC/PKCS5Padding");
        ci.init(Cipher.ENCRYPT_MODE, sKey, ivSpec);
        

        try (FileOutputStream out = new FileOutputStream(ENCRYPT_FILE)) {
            byte[] input = plainText.getBytes("UTF-8");
            byte[] encoded = ci.doFinal(input);
            out.write(encoded);
        }

    }

    public static void decode(IvParameterSpec ivSpec, SecretKey sKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException{
        Cipher ci = Cipher.getInstance("AES/CBC/PKCS5Padding");
        ci.init(Cipher.DECRYPT_MODE, sKey, ivSpec);
        byte[] encoded = Files.readAllBytes(Paths.get(ENCRYPT_FILE));
        String plainText = new String(ci.doFinal(encoded), "UTF-8");

        try (FileOutputStream out = new FileOutputStream(DECRYPT_FILE)) {

            out.write(plainText.getBytes());
        }
    }


    // public static void writeByteArrToFile(byte[] data) throws FileNotFoundException, IOException{
    //     try (FileOutputStream out = new FileOutputStream(ENCRYPT_FILE)) {
    //         out.write(data);
    //     }
    // }
}