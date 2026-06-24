package com.bajaj.bfhl;

import com.bajaj.bfhl.dto.BfhlRequest;
import com.bajaj.bfhl.dto.BfhlResponse;
import com.bajaj.bfhl.service.BfhlServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BfhlServiceImplTest {

    private BfhlServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new BfhlServiceImpl();
        // Inject @Value fields manually for unit testing
        ReflectionTestUtils.setField(service, "fullName", "john doe");
        ReflectionTestUtils.setField(service, "dob", "17091999");
        ReflectionTestUtils.setField(service, "email", "john@xyz.com");
        ReflectionTestUtils.setField(service, "rollNumber", "ABCD123");
    }

    @Test
    @DisplayName("Example A - mixed input with alpha, numbers, special char")
    void testExampleA() {
        BfhlRequest request = new BfhlRequest(Arrays.asList("a", "1", "334", "4", "R", "$"));
        BfhlResponse response = service.processData(request);

        assertTrue(response.isSuccess());
        assertEquals("john_doe_17091999", response.getUserId());
        assertEquals("john@xyz.com", response.getEmail());
        assertEquals("ABCD123", response.getRollNumber());

        assertEquals(List.of("1"), response.getOddNumbers());
        assertEquals(List.of("334", "4"), response.getEvenNumbers());
        assertEquals(List.of("A", "R"), response.getAlphabets());
        assertEquals(List.of("$"), response.getSpecialCharacters());
        assertEquals("339", response.getSum());
        // allAlphaChars = "aR" → reversed = "Ra" → alternating caps = "Ra" (R→R, a→a)
        assertEquals("Ra", response.getConcatString());
    }


    @Test
    @DisplayName("Example B - multiple alphabets, special chars, numbers")
    void testExampleB() {
        BfhlRequest request = new BfhlRequest(
                Arrays.asList("2", "a", "y", "4", "&", "-", "*", "5", "92", "b"));
        BfhlResponse response = service.processData(request);

        assertTrue(response.isSuccess());
        assertEquals(List.of("5"), response.getOddNumbers());
        assertEquals(List.of("2", "4", "92"), response.getEvenNumbers());
        assertEquals(List.of("A", "Y", "B"), response.getAlphabets());
        assertEquals(List.of("&", "-", "*"), response.getSpecialCharacters());
        assertEquals("103", response.getSum());
        // allAlphaChars = "ayb" → reversed = "bya" → alternating caps = "ByA"
        assertEquals("ByA", response.getConcatString());
    }


    @Test
    @DisplayName("Example C - multi-char alphabets only")
    void testExampleC() {
        BfhlRequest request = new BfhlRequest(Arrays.asList("A", "ABCD", "DOE"));
        BfhlResponse response = service.processData(request);

        assertTrue(response.isSuccess());
        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertEquals(List.of("A", "ABCD", "DOE"), response.getAlphabets());
        assertTrue(response.getSpecialCharacters().isEmpty());
        assertEquals("0", response.getSum());

        assertEquals("EoDdCbAa", response.getConcatString());
    }


    @Test
    @DisplayName("Edge case - empty data array")
    void testEmptyData() {
        BfhlRequest request = new BfhlRequest(List.of());
        BfhlResponse response = service.processData(request);

        assertTrue(response.isSuccess());
        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertTrue(response.getAlphabets().isEmpty());
        assertTrue(response.getSpecialCharacters().isEmpty());
        assertEquals("0", response.getSum());
        assertEquals("", response.getConcatString());
    }


    @Test
    @DisplayName("Edge case - only numbers")
    void testOnlyNumbers() {
        BfhlRequest request = new BfhlRequest(Arrays.asList("3", "6", "11", "100"));
        BfhlResponse response = service.processData(request);

        assertEquals(List.of("3", "11"), response.getOddNumbers());
        assertEquals(List.of("6", "100"), response.getEvenNumbers());
        assertTrue(response.getAlphabets().isEmpty());
        assertTrue(response.getSpecialCharacters().isEmpty());
        assertEquals("120", response.getSum());
        assertEquals("", response.getConcatString());
    }


    @Test
    @DisplayName("Edge case - only special characters")
    void testOnlySpecialChars() {
        BfhlRequest request = new BfhlRequest(Arrays.asList("@", "#", "!"));
        BfhlResponse response = service.processData(request);

        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertTrue(response.getAlphabets().isEmpty());
        assertEquals(List.of("@", "#", "!"), response.getSpecialCharacters());
        assertEquals("0", response.getSum());
        assertEquals("", response.getConcatString());
    }


    @Test
    @DisplayName("user_id format - full_name_ddmmyyyy in lowercase")
    void testUserIdFormat() {
        BfhlRequest request = new BfhlRequest(List.of());
        BfhlResponse response = service.processData(request);
        assertEquals("john_doe_17091999", response.getUserId());
    }


    @Test
    @DisplayName("Numbers in response must be strings")
    void testNumbersAsStrings() {
        BfhlRequest request = new BfhlRequest(Arrays.asList("1", "2"));
        BfhlResponse response = service.processData(request);
        assertTrue(response.getOddNumbers().contains("1"));
        assertTrue(response.getEvenNumbers().contains("2"));
        assertInstanceOf(String.class, response.getSum());
    }
}
