package LoginPage.Security;


import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.encrypt.AesBytesEncryptor;


public class Aes {

    private AesBytesEncryptor aesEncryptor;


    //private SaltGenerator salt;




    public Aes() {

        //salt = new SaltGenerator();

        this.aesEncryptor = new AesBytesEncryptor("JoaoVitorKey123456","123456");
    }

    public String encrypt(String text) {
        byte[] encryptedBytes = aesEncryptor.encrypt(text.getBytes());
        return new String(Hex.encode(encryptedBytes));
    }


    public String decrypt(String encryptedText) {
        byte[] encryptedBytes = Hex.decode(encryptedText);
        byte[] decryptedBytes = aesEncryptor.decrypt(encryptedBytes);
        return new String(decryptedBytes);
    }
}
