package com.bajaj.bfhl.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class BfhlResponse {

    @JsonProperty("is_success")
    private boolean isSuccess;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("email")
    private String email;

    @JsonProperty("roll_number")
    private String rollNumber;

    @JsonProperty("odd_numbers")
    private List<String> oddNumbers;

    @JsonProperty("even_numbers")
    private List<String> evenNumbers;

    @JsonProperty("alphabets")
    private List<String> alphabets;

    @JsonProperty("special_characters")
    private List<String> specialCharacters;

    @JsonProperty("sum")
    private String sum;

    @JsonProperty("concat_string")
    private String concatString;

    public BfhlResponse() {}


    private BfhlResponse(Builder builder) {
        this.isSuccess = builder.isSuccess;
        this.userId = builder.userId;
        this.email = builder.email;
        this.rollNumber = builder.rollNumber;
        this.oddNumbers = builder.oddNumbers;
        this.evenNumbers = builder.evenNumbers;
        this.alphabets = builder.alphabets;
        this.specialCharacters = builder.specialCharacters;
        this.sum = builder.sum;
        this.concatString = builder.concatString;
    }


    public boolean isSuccess() { return isSuccess; }
    public String getUserId() { return userId; }
    public String getEmail() { return email; }
    public String getRollNumber() { return rollNumber; }
    public List<String> getOddNumbers() { return oddNumbers; }
    public List<String> getEvenNumbers() { return evenNumbers; }
    public List<String> getAlphabets() { return alphabets; }
    public List<String> getSpecialCharacters() { return specialCharacters; }
    public String getSum() { return sum; }
    public String getConcatString() { return concatString; }


    public void setSuccess(boolean success) { isSuccess = success; }
    public void setUserId(String userId) { this.userId = userId; }
    public void setEmail(String email) { this.email = email; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }
    public void setOddNumbers(List<String> oddNumbers) { this.oddNumbers = oddNumbers; }
    public void setEvenNumbers(List<String> evenNumbers) { this.evenNumbers = evenNumbers; }
    public void setAlphabets(List<String> alphabets) { this.alphabets = alphabets; }
    public void setSpecialCharacters(List<String> specialCharacters) { this.specialCharacters = specialCharacters; }
    public void setSum(String sum) { this.sum = sum; }
    public void setConcatString(String concatString) { this.concatString = concatString; }


    public static class Builder {
        private boolean isSuccess;
        private String userId;
        private String email;
        private String rollNumber;
        private List<String> oddNumbers;
        private List<String> evenNumbers;
        private List<String> alphabets;
        private List<String> specialCharacters;
        private String sum;
        private String concatString;

        public Builder isSuccess(boolean isSuccess) { this.isSuccess = isSuccess; return this; }
        public Builder userId(String userId) { this.userId = userId; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder rollNumber(String rollNumber) { this.rollNumber = rollNumber; return this; }
        public Builder oddNumbers(List<String> oddNumbers) { this.oddNumbers = oddNumbers; return this; }
        public Builder evenNumbers(List<String> evenNumbers) { this.evenNumbers = evenNumbers; return this; }
        public Builder alphabets(List<String> alphabets) { this.alphabets = alphabets; return this; }
        public Builder specialCharacters(List<String> specialCharacters) { this.specialCharacters = specialCharacters; return this; }
        public Builder sum(String sum) { this.sum = sum; return this; }
        public Builder concatString(String concatString) { this.concatString = concatString; return this; }

        public BfhlResponse build() { return new BfhlResponse(this); }
    }
}
