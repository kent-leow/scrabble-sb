package com.example.scrabblesb.scores.constants;

import java.util.HashMap;
import java.util.Map;

public class ScoreRules {
    public static final Map<Character, Integer> SCORE_RULES = new HashMap<>();

    static {
        SCORE_RULES.put('A', 1);
        SCORE_RULES.put('E', 1);
        SCORE_RULES.put('I', 1);
        SCORE_RULES.put('O', 1);
        SCORE_RULES.put('U', 1);
        SCORE_RULES.put('L', 1);
        SCORE_RULES.put('N', 1);
        SCORE_RULES.put('S', 1);
        SCORE_RULES.put('T', 1);
        SCORE_RULES.put('R', 1);
        SCORE_RULES.put('D', 2);
        SCORE_RULES.put('G', 2);
        SCORE_RULES.put('B', 3);
        SCORE_RULES.put('C', 3);
        SCORE_RULES.put('M', 3);
        SCORE_RULES.put('P', 3);
        SCORE_RULES.put('F', 4);
        SCORE_RULES.put('H', 4);
        SCORE_RULES.put('V', 4);
        SCORE_RULES.put('W', 4);
        SCORE_RULES.put('Y', 4);
        SCORE_RULES.put('K', 6);
        SCORE_RULES.put('J', 8);
        SCORE_RULES.put('X', 8);
        SCORE_RULES.put('Q', 10);
        SCORE_RULES.put('Z', 10);
    }
}
