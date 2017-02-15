package com.mycompany.pfind;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;

/**
 * Created by okhoruzhenko on 2/14/17.
 */
public class ParallelLookup extends RecursiveTask<ArrayList<String>> {
    private File[] listOfFiles;
    private String lookUpPattern;
    public ParallelLookup(String path, String name) {
        listOfFiles = new File(path).listFiles();
        lookUpPattern = name;
    }
    public ArrayList<String> compute() {
        System.out.println(Thread.currentThread().getName());
        ArrayList<String> lookupResult = new ArrayList<>();
        ArrayList<ParallelLookup> directories = new ArrayList<>();
        for (int i=0; i< listOfFiles.length; i++ ) {
            if (listOfFiles[i].isFile() && listOfFiles[i].getName().matches(lookUpPattern)) {
                lookupResult.add(listOfFiles[i].getAbsolutePath());
            }
            if (listOfFiles[i].isDirectory()) {
                ParallelLookup newLookup = new ParallelLookup(listOfFiles[i].getPath(), lookUpPattern);
                directories.add(newLookup);
                newLookup.fork();
            }
        }
        directories.forEach( dir -> lookupResult.addAll(dir.join()));
        return lookupResult;
    }
}
