package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Getter
@ToString
public class SummonScroll {
    private final List<String> elements;
    private final double chanceAwaken3Stars;
    private final double chance4Stars;
    private final double chanceAwaken4Stars;
    private final double chance5Stars;

    @AllArgsConstructor
    @Getter
    public enum Type {
        MYSTIC_SCROLL_3_5_STAR(new SummonScroll(Arrays.asList("Water", "Wind", "Fire"), 0.15, 0.08, 0.05, 0.005)),
        MYSTIC_SCROLL_3_5_STAR_WATER(new SummonScroll(Collections.singletonList("Water"), 0.15, 0.08, 0.05, 0.005)),
        MYSTIC_SCROLL_3_5_STAR_WIND(new SummonScroll(Collections.singletonList("Wind"), 0.15, 0.08, 0.05, 0.005)),
        MYSTIC_SCROLL_3_5_STAR_FIRE(new SummonScroll(Collections.singletonList("Fire"), 0.15, 0.08, 0.05, 0.005)),
        LEGENDARY_SCROLL_4_5_STAR(new SummonScroll(Arrays.asList("Water", "Wind", "Fire"), 0, 0.935, 0.15, 0.065)),
        LEGENDARY_SCROLL_4_5_STAR_WATER(new SummonScroll(Collections.singletonList("Water"), 0, 0.935, 0.15, 0.065)),
        LEGENDARY_SCROLL_4_5_STAR_WIND(new SummonScroll(Collections.singletonList("Wind"), 0, 0.935, 0.15, 0.065)),
        LEGENDARY_SCROLL_4_5_STAR_FIRE(new SummonScroll(Collections.singletonList("Fire"), 0, 0.935, 0.15, 0.065)),
        TRANSCEND_SCROLL_5_STAR(new SummonScroll(Arrays.asList("Water", "Wind", "Fire"), 0, 0, 0, 1)),
        LIGHT_DARK_SCROLL_3_5_STAR(new SummonScroll(Arrays.asList("Light", "Dark"), 0.15, 0.06, 0.05, 0.0035));

        private final SummonScroll scroll;
    }
}