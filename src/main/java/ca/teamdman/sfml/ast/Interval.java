package ca.teamdman.sfml.ast;

public record Interval(int ticks) implements ASTNode {
    public static Interval fromTicks(int ticks) {
        return new Interval(ticks);
    }

    public static Interval fromSeconds(int seconds) {
        return new Interval(seconds * 20);
    }

    public int getTicks() {
        return ticks;
    }

    public int getSeconds() {
        return ticks / 20;
    }
}