package main;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static main.Main.*;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    public void getCommandTest() {
        commands = Arrays.asList("add");
        String expected = "add";
        String actual = getCommand("add");
        assertEquals(expected, actual);
    }

    @Test
    public void getCommandTestLeadingSpaces() {
        commands = Arrays.asList("add");
        String expected = "add";
        String actual = getCommand("   add");
        assertEquals(expected, actual);
    }

    @Test
    public void getCommandTestUpperCase() {
        commands = Arrays.asList("add");
        String expected = "add";
        String actual = getCommand("ADD");
        assertEquals(expected, actual);
    }

    @Test
    public void getCommandTestWrong() {
        commands = Arrays.asList("add");
        String expected = "Error";
        String actual = getCommand("a;ssf;sfh");
        assertEquals(expected, actual);
    }

    @Test
    public void getCommandTestEmptyLine() {
        commands = Arrays.asList("add");
        String expected = "Error";
        String actual = getCommand("");
        assertEquals(expected, actual);
    }

    @Test
    public void getCommandTestNotInCommandList() {
        commands = Arrays.asList("add");
        String expected = "Error";
        String actual = getCommand("list");
        assertEquals(expected, actual);
    }

    @Test
    public void getCommandTestIncludedInCommandListWithUser() {
        commands = Arrays.asList("add", "list");
        user = "Anybody";

        String expected = "list";
        String actual = getCommand("list");
        assertEquals(expected, actual);
    }

//    @Test
//    public void getCommandTestIncludedInCommandListWithoutUser() {
//        commands = Arrays.asList("add", "list");
//
//        String expected = "list";
//        String actual = getCommand("list");
//        assertEquals(expected, actual);
//    }

    @Test
    public void getUserTest() {

        String expected = "abba";
        String actual = getUserForTests("abba");
        assertEquals(expected, actual);
    }

    @Test
    public void getUserTestTwoWords() {

        String expected = "";
        String actual = getUserForTests("ab ba");
        assertEquals(expected, actual);
    }

    @Test
    public void getUserTestDigitIsFirst() {

        String expected = "";
        String actual = getUserForTests("3ab");
        assertEquals(expected, actual);
    }

    @Test
    public void getUserTestEmptyLine() {

        String expected = "";
        String actual = getUserForTests("");
        assertEquals(expected, actual);
    }

    @Test
    public void getUserTestEmptyFirstToken() {

        String expected = "";
        String actual = getUserForTests("+ ?line");
        assertEquals(expected, actual);
    }

    @Test
    public void setUserActiveTest() {
        String name = "John";
        setUserActive(name,false);
        String expected = userObject.getUser();
        String actual = name;
        assertEquals(expected, actual);
    }

    @Test
    public void getTokensTest() {

        List<String> expected = Arrays.asList("one", "two", "three");
        List<String> actual = getTokens("one two three", false, true);
        assertLinesMatch(expected, actual);
    }

    @Test
    public void getTokensTestUpperCase() {

        List<String> expected = Arrays.asList("one", "two", "three");
        List<String> actual = getTokens("ONE two three", true, true);
        assertLinesMatch(expected, actual);
    }

    @Test
    public void getTokensTestSpaces() {

        List<String> expected = Arrays.asList("one", "two", "three");
        List<String> actual = getTokens("  ONE two three", true, true);
        assertLinesMatch(expected, actual);
    }

    @Test
    public void getTokensTestSigns() {

        List<String> expected = Arrays.asList("one", "two", "three");
        List<String> actual = getTokens("  ONE two! +three", true, true);
        assertLinesMatch(expected, actual);
    }

    @Test
    public void getTokensTestUnderscore() {

        List<String> expected = Arrays.asList("one_one", "two", "three");
        List<String> actual = getTokens("  ONE_one-two! +three", true, true);
        assertLinesMatch(expected, actual);
    }

    @Test
    public void getTokensTestRussian() {

        List<String> expected = Arrays.asList("three");
        List<String> actual = getTokens(" раз+два+three", true, true);
        assertLinesMatch(expected, actual);
    }

    @Test
    public void getTokensTestWrongEntry() {

        List<String> expected = new ArrayList<>();
        List<String> actual = getTokens(" раз+два+три", true, true);
        assertLinesMatch(expected, actual);
    }

  }
