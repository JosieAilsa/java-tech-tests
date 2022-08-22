package com.company.utils;

import com.company.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ObjectUtilsTest {

    Book testBook;

    @BeforeEach
    void setUp() {
        testBook = new Book(1,"Harry Potter", "J.K Rowling", "fiction", "adventure", "Penguin");
    }

    @AfterEach
    void tearDown() {
        testBook = null;
    }

    @Test
    public void beanProperties_HarryPotterBook_ReurnsAllPropeties(){
        Map<String,String> bookHashMap = ObjectUtils.beanProperties(testBook);
        assertEquals(bookHashMap.get("title"), "Harry Potter");
        assertEquals(bookHashMap.get("author"), "J.K Rowling");
        assertEquals(bookHashMap.get("genre"), "fiction");
    }
    //Negative tests
    @Test
    public void beanProperties_Null_ThrowsNFE(){
        Assertions.assertThrows(NullPointerException.class, () -> {
            ObjectUtils.beanProperties(null);
        });
    }
}