package com.mycompany.pfind;

import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ParallelLookup parallelLookup = new ParallelLookup("/home/okhoruzhenko/svnwork/ops/trunk", "readme.txt");
        ArrayList<String> lookupResult = ForkJoinPool.commonPool().invoke(parallelLookup);
        lookupResult.forEach(System.out::println);
    }
}
