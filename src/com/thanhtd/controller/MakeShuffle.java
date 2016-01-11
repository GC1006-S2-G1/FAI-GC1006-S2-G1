/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thanhtd.controller;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Thanh Tran
 */
public class MakeShuffle {

    public static List<Integer> shuffleArray(List<Integer> item) {
        Random rnd = ThreadLocalRandom.current();
        List<Integer> ar = item;
        for (int i = ar.size() - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar.get(index);
            ar.set(index, ar.get(i));
            ar.set(i, a);
        }
        return ar.subList(0, 10);
    }

    public static int getRandom(List<Integer> list) {
        int index = new Random().nextInt(list.size());
        return list.get(index);
    }
}
