package TripAmi.backend.auth.authmember.service;

import net.bytebuddy.utility.RandomString;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TempPasswordGenerator {
    static public final Random random = new Random();

    static public String randString() {
        return RandomString.make();
    }

    static public Long randomPositiveLong() {
        return random.nextLong(0, 1000000);
    }

    static public Character randomSpecialCharacter() {
        String specialCharacters = "~!@#$%^&*_-+=`|\\(){}[]:;\"'<>,.?/";
        return specialCharacters.charAt(random.nextInt(specialCharacters.length()));
    }

    static public String shuffle(String input) {
        char[] charArray = input.toCharArray();
        Random random = new Random();

        for (int i = charArray.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            char temp = charArray[index];
            charArray[index] = charArray[i];
            charArray[i] = temp;
        }

        return new String(charArray);
    }

    static public String generateTempPassword() {
        StringBuffer stringBuilder = new StringBuffer();
        stringBuilder.append(randString())
            .append(randomPositiveLong())
            .append(randomSpecialCharacter());
        return shuffle(stringBuilder.toString());
    }
}
