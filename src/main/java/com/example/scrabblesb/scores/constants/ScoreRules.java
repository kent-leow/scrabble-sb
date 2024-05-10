package com.example.scrabblesb.scores.constants;

import java.util.HashMap;
import java.util.Map;

public class ScoreRules {
    public static final Map<Character, Integer> scoringRules = new HashMap<>();

    static {
        scoringRules.put('A', 1);
        scoringRules.put('E', 1);
        scoringRules.put('I', 1);
        scoringRules.put('O', 1);
        scoringRules.put('U', 1);
        scoringRules.put('L', 1);
        scoringRules.put('N', 1);
        scoringRules.put('S', 1);
        scoringRules.put('T', 1);
        scoringRules.put('R', 1);
        scoringRules.put('D', 2);
        scoringRules.put('G', 2);
        scoringRules.put('B', 3);
        scoringRules.put('C', 3);
        scoringRules.put('M', 3);
        scoringRules.put('P', 3);
        scoringRules.put('F', 4);
        scoringRules.put('H', 4);
        scoringRules.put('V', 4);
        scoringRules.put('W', 4);
        scoringRules.put('Y', 4);
        scoringRules.put('K', 6);
        scoringRules.put('J', 8);
        scoringRules.put('X', 8);
        scoringRules.put('Q', 10);
        scoringRules.put('Z', 10);
    }
}
