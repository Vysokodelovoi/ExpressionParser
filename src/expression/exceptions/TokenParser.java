package expression.exceptions;

import info.kgeorgiy.json.BaseParser;
import info.kgeorgiy.json.StringSource;

import java.util.Set;

import static expression.exceptions.TokenType.*;

public class TokenParser extends BaseParser {
    private final static Set<String> operators = Set.of(
            "min", "max", "**", "*", "+", "//", "/", "-"
    );
    private final static Set<String> unaries = Set.of(
            "l0", "t0", "-", "abs"
    );
    private final static Set<Character> operatorStarts = Set.of(
            'm', '*', '+', '/', '-'
    );
    private final static Set<Character> unaryStarts = Set.of(
            'l', 't', '-', 'a'
    );
    private static final Trie operatorTrie = new Trie(operators);
    private static final Trie unariesTrie = new Trie(unaries);
    private Token curToken;
    private int balance;
    private boolean wereWhitespace;
    private TokenType lastNotWhitespaceTokenType;

    public TokenParser(String source) {
        super(new StringSource(source));
        wereWhitespace = false;
        balance = 0;
    }

    static class Token {
        private final TokenType type;
        private final String self;

        public Token(TokenType type, String self) {
            this.type = type;
            this.self = self;
        }

        public TokenType getType() {
            return type;
        }

        public String getSelf() {
            return self;
        }

        @Override
        public String toString() {
            return type + " '" + self + "'";
        }
    }

    public TokenType determineToken() {
        if (Character.isWhitespace(ch)) {
            return WHITESPACE;
        } else if (between('x', 'z')) {
            return VARIABLE;
        } else if (between('0', '9')) {
            return CONSTANT;
        } else if (ch == '-') {
            if (lastNotWhitespaceTokenType == RIGHT_PARANTH || lastNotWhitespaceTokenType == CONSTANT
                    || lastNotWhitespaceTokenType == VARIABLE) {
                return OPERATOR;
            } else {
                return UNARY;
            }
        } else if (operatorStarts.contains(ch)) {
            return OPERATOR;
        } else if (unaryStarts.contains(ch)) {
            return UNARY;
        }
        return switch (ch) {
            case '(' -> LEFT_PARANTH;
            case ')' -> RIGHT_PARANTH;
            default -> UNKNOWN;
        };
    }

    public Token nextToken() {
        if (eof()) {
            curToken = new Token(END, "");
            return curToken;
        }
        if (curToken == null) {
            lastNotWhitespaceTokenType = UNKNOWN;
        } else if (curToken.type != WHITESPACE) {
            wereWhitespace = curToken.type == RIGHT_PARANTH;
            lastNotWhitespaceTokenType = curToken.type;
        } else {
            wereWhitespace = true;
        }


        curToken = switch (determineToken()) {
            case LEFT_PARANTH -> new Token(LEFT_PARANTH, String.valueOf(take()));
            case RIGHT_PARANTH -> new Token(RIGHT_PARANTH, String.valueOf(take()));
            case UNARY -> getUnary();
            case OPERATOR -> getOperator();
            case WHITESPACE -> getWhitespace();
            case VARIABLE -> getVariable();
            case CONSTANT -> getConst();

            default -> throw new UnknownTokenException("Can't parse symbol '" + ch + "'");
        };
        if (curToken.type == LEFT_PARANTH) {
            balance++;
        } else if (curToken.type == RIGHT_PARANTH) {
            balance--;
        } else if (curToken.type == UNARY && curToken.self.equals("-")) {
            if (determineToken() == CONSTANT) {
                curToken = new Token(CONSTANT, "-" + getConst().self);
            }
        }
        return curToken;
    }

    public Token nextNotWhitespace() {
        Token token = nextToken();
        if (token.type == WHITESPACE) {
            return nextToken();
        }
        return token;
    }

    private Token getWhitespace() {
        skipWhitespace();
        return new Token(WHITESPACE, " ");
    }

    public int getBalance() {
        return balance;
    }

    public Token getCurToken() {
        return curToken;
    }

    private Token getVariable() {
        return new Token(VARIABLE, String.valueOf(take()));
    }

    private Token getConst() {
        StringBuilder stringBuilder = new StringBuilder();
        while (between('0', '9')) {
            stringBuilder.append(take());
        }
        return new Token(CONSTANT, stringBuilder.toString());
    }

    private Token getFromTrie(Trie trie, TokenType tokenType) {
        StringBuilder stringBuilder = new StringBuilder();
        while (trie.goTo(ch)) {
            stringBuilder.append(ch);
            take();
        }
        trie.resetToRoot();
        if (!trie.getStrings().contains(stringBuilder.toString())) {
            throw new UnknownTokenException(stringBuilder.toString());
        }
        return new Token(tokenType, stringBuilder.toString());
    }

    private Token getUnary() {
        return getFromTrie(unariesTrie, UNARY);
    }

    public Token getOperator() {
        return getFromTrie(operatorTrie, OPERATOR);
    }

    public boolean getWereWhitespace() {
        return wereWhitespace;
    }

    public char getCh() {
        return ch;
    }
}
