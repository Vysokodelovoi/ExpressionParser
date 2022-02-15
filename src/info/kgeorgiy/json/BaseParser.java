package info.kgeorgiy.json;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class BaseParser {
    private static final char END = '\0';
    protected CharSource source;
    protected char ch = 0xffff, lastChar = 0xffff;
    protected boolean skippedWhitespace;
    private  int position;
    protected BaseParser(final CharSource source) {
        this.source = source;
        position = 0;
        take();
    }

    protected BaseParser() {
        source = null;
    }
    protected char take() {
        lastChar = ch;
        final char result = ch;
        ch = source.hasNext() ? source.next() : END;
        position++;
        skippedWhitespace = false;
        return result;
    }

    protected boolean test(final char expected) {
        return ch == expected;
    }

    protected boolean take(final char expected) {
        if (test(expected)) {
            take();
            return true;
        }
        return false;
    }

    protected void expect(final char expected) {
        if (!take(expected)) {
            throw error("Expected '" + expected + "', found '" + ch + "'");
        }
    }

    protected void expect(final String value) {
        for (final char c : value.toCharArray()) {
            expect(c);
        }
    }

    public boolean eof() {
        return take(END);
    }

    protected IllegalArgumentException error(final String message) {
        return source.error(message);
    }

    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }

    protected void skipWhitespace() {
        while (Character.isWhitespace(ch)) {
            take();
            skippedWhitespace = true;
        }
    }

    protected int getPosition() {
        return position;
    }
    protected void setInitialPosition() {
        position = 0;
    }
}
