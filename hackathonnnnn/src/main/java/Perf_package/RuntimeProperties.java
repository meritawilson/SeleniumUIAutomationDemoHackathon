/*
 * Copyright (c) 2007 - 2013 Informatica Corporation.  All rights reserved.
 * INFORMATICA PROPRIETARY/CONFIDENTIAL.  Use is subject to license terms.
 */
package Perf_package;


import java.io.*;
import java.util.Properties;

public class RuntimeProperties {
    public static String IICS_APP_URL = "iics.url";
    public static String SAML_URL = "saml.url";
    public static int TOTAL_ITERATIONS;
    public static int TOTAL_ACTIONS;
    public static String RUN = "Runn";
    public static String RELEASE = "Release";
    public static String DATE = "Dateofrun";

    public static String IDS_URL = "ids.url";
    public static String POD_URL = "pod.url";
    public static String IDS_USER;
    public static String IDS_PWD;
    public static int WAIT_TIMEOUT_IN_SECONDS = 15;

    static Properties prop = new Properties();
    static String path = "C:\\Users\\svali\\Desktop\\Hackathonnnnn\\automation.properties";
    static File f = new File(path);

     static  {

         final FileInputStream configStream;
         try {
             configStream = new FileInputStream(f);
         } catch (FileNotFoundException e) {
             throw new RuntimeException(e);
         }
         try {
            // load a properties file
            prop.load(configStream);


            IICS_APP_URL = prop.getProperty("iics.url");

            IDS_URL = prop.getProperty("ids.url");

            POD_URL = prop.getProperty("pod.url");

            TOTAL_ITERATIONS = Integer.parseInt(prop.getProperty("totaliterations"));

            TOTAL_ACTIONS = Integer.parseInt(prop.getProperty("Totalactions"));

            RUN = prop.getProperty("Runn");

            RELEASE = prop.getProperty("Release");

            DATE = prop.getProperty("Dateofrun");

            IDS_USER = prop.getProperty("loginid");

            IDS_PWD = prop.getProperty("pwd");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}


   

    