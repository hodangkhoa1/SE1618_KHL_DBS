package com.khl.gentledentalcare.utils;

import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 *
 * @author ASUS
 */
public class FunctionRandom {

    private static final String[] defaultAvatarArray = {
        "#FF9933", "#FF9900", "#CC99FF", "#CC99CC", "#CC9999", "#CC9966", "#CC9933", "#CC9900", "#9999FF", "#9999CC",
        "#999999", "#999966", "#6699FF", "#6699CC", "#339933", "#0099FF", "#0099CC", "#009999", "#009966", "#009900",
        "#FF66FF", "#FF6699", "#FF6666", "#FF6633", "#9966FF", "#6666FF", "#6666CC", "#3366FF", "#3366CC", "#FF33FF",
        "#FF3366", "#FF3300", "#9933FF", "#993300", "#6633FF", "#3333FF", "#FF3333", "#FF0066", "#660066", "#330000"
    };

    public static String colorAvatar() {
        Random random = new Random();
        int index = random.nextInt(defaultAvatarArray.length);
        return defaultAvatarArray[index];
    }

    public static String randomID(int length) {
        byte[] array = new byte[256];
        Random random = new Random();
        random.nextBytes(array);
        String randomString = new String(array, StandardCharsets.UTF_8);
        StringBuilder stringBuffer = new StringBuilder();

        for (int i = 0; i < randomString.length(); i++) {
            char character = randomString.charAt(i);

            if (((character >= 'a' && character <= 'z') || (character >= 'A' && character <= 'Z') || (character >= '0' && character <= '9')) && (length > 0)) {
                stringBuffer.append(character);
                length--;
            }
        }

        return stringBuffer.toString();
    }

    public static String randomVerifyCode(int length) {
        byte[] array = new byte[256];
        Random random = new Random();
        random.nextBytes(array);
        String randomString = new String(array, StandardCharsets.UTF_8);
        StringBuilder stringBuffer = new StringBuilder();

        for (int i = 0; i < randomString.length(); i++) {
            char character = randomString.charAt(i);

            if ((character >= '0' && character <= '9') && (length > 0)) {
                stringBuffer.append(character);
                length--;
            }
        }

        return stringBuffer.toString();
    }
}
