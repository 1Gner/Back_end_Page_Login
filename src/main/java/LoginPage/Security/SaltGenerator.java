/*package LoginPage.Security;

import java.security.SecureRandom;
import org.springframework.security.crypto.codec.Hex;

import java.util.Arrays;
import java.util.Base64;

public class SaltGenerator {

    private String salt;
    private SecureRandom random;


    public SaltGenerator() {
        random = new SecureRandom();
        generateSalt();
    }


    private void generateSalt() {
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        this.salt = new String(Hex.encode(saltBytes));
    }


    public String getSalt() {
        return salt;
    }
}
*/