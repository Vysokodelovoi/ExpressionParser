package expression.parser.BaseParser;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class BaseParser {
    private static final char END = '\0';
    protected StringSource source;
    protected char ch = 0xffff, lastChar = 0xffff;
    protected boolean skippedWhitespace;
    private  int position;
    protected void init(final StringSource source) {
        this.source = source;
        position = 0;
        take();
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

    protected boolean test(final String expected) {
        int count = 0;
        while(count < expected.length() && take(expected.charAt(count))) {
            count++;
        }
        if (count == expected.length()) {
            return true;
        }
        source.back(count+1);
        take();
        return false;
    }

    protected boolean take(final char expected) {
        if (test(expected)) {
            take();
            return true;
        }
        return false;
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

    public int getPosition() {
        return position;
    }
}
