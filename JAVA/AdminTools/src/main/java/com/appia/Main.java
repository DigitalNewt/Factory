package com.appia;

/**
 * User: Brent Baker
 * Date: 9/12/13
 * Time: 5:04 PM
 */
public class Main {


    public static void main(String[] args) {
        UploadRules uploadRules = new UploadRules();
        WritePoem writePoem = new WritePoem();
        try {
            uploadRules.LoadPoemRules();
            System.out.print(writePoem.generatePoem());

        } catch (Exception e) {
            System.out.print(e);
        }
    }
}
