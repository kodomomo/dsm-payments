package com.github.kodomo.dsmpayments.domain.user.entity;

import com.github.kodomo.dsmpayments.domain.user.exception.HashException;
import com.github.kodomo.dsmpayments.infra.db.DMSUserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Optional;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DMSUser {
    private String id;

    private String password;

    private String name;

    private Integer number;

    public static Optional<DMSUser> map(DMSUserEntity dmsUserEntity) {
        if (dmsUserEntity == null) return Optional.empty();
        return Optional.of(DMSUser.builder()
                .id(dmsUserEntity.getId())
                .password(dmsUserEntity.getPassword())
                .name(dmsUserEntity.getName())
                .number(dmsUserEntity.getNumber())
                .build());
    }

    public boolean checkPassword(String password) {
        try {
            String[] hashedPassword = this.password.split("\\$");
            String[] method = hashedPassword[0].split(":");

            String iteration = method[2];
            String salt = hashedPassword[1];
            String originHash = hashedPassword[2];

            return originHash.equals(generatePasswordHash(password, salt, Integer.parseInt(iteration)));

        } catch (Exception exception) {
            throw new HashException();
        }
    }

    private static String generatePasswordHash(String password, String salta, int iterations)
            throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        char[] chars = password.toCharArray();
        byte[] salt = salta.getBytes(StandardCharsets.UTF_8);

        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64*4);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return toHex(hash);
    }

    private static String toHex(byte[] array) throws NoSuchAlgorithmException
    {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
        {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }
}
