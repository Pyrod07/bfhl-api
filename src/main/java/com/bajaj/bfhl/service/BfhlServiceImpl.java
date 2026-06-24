package com.bajaj.bfhl.service;

import com.bajaj.bfhl.dto.BfhlRequest;
import com.bajaj.bfhl.dto.BfhlResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    @Value("${user.full_name}")
    private String fullName;

    @Value("${user.dob}")
    private String dob;

    @Value("${user.email}")
    private String email;

    @Value("${user.roll_number}")
    private String rollNumber;

    @Override
    public BfhlResponse processData(BfhlRequest request) {
        List<String> data = request.getData();

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        long sum = 0;
        StringBuilder allAlphaChars = new StringBuilder();

        for (String item : data) {
            if (isNumber(item)) {
                long number = Long.parseLong(item);
                sum += number;
                if (number % 2 == 0) {
                    evenNumbers.add(item);
                } else {
                    oddNumbers.add(item);
                }
            } else if (isAlphaOnly(item)) {

                alphabets.add(item.toUpperCase());

                for (char c : item.toCharArray()) {
                    allAlphaChars.append(c);
                }
            } else if (item.length() == 1) {

                specialCharacters.add(item);
            } else {

                specialCharacters.add(item);
            }
        }

        String concatString = buildConcatString(allAlphaChars.toString());
        String userId = buildUserId();

        return new BfhlResponse.Builder()
                .isSuccess(true)
                .userId(userId)
                .email(email)
                .rollNumber(rollNumber)
                .oddNumbers(oddNumbers)
                .evenNumbers(evenNumbers)
                .alphabets(alphabets)
                .specialCharacters(specialCharacters)
                .sum(String.valueOf(sum))
                .concatString(concatString)
                .build();
    }
    private boolean isNumber(String s) {
        if (s == null || s.isEmpty()) return false;
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    private boolean isAlphaOnly(String s) {
        if (s == null || s.isEmpty()) return false;
        for (char c : s.toCharArray()) {
            if (!Character.isLetter(c)) return false;
        }
        return true;
    }

    private String buildConcatString(String allAlphaChars) {
        if (allAlphaChars.isEmpty()) return "";


        String reversed = new StringBuilder(allAlphaChars).reverse().toString();


        StringBuilder result = new StringBuilder();
        for (int i = 0; i < reversed.length(); i++) {
            char c = reversed.charAt(i);
            if (i % 2 == 0) {
                result.append(Character.toUpperCase(c));
            } else {
                result.append(Character.toLowerCase(c));
            }
        }
        return result.toString();
    }


    private String buildUserId() {
        // fullName from config e.g. "Mridul Sharma" → "mridul_sharma"
        String namePart = fullName.trim().toLowerCase().replace(" ", "_");
        return namePart + "_" + dob;
    }
}
