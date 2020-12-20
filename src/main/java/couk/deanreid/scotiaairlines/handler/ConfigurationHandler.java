/*
 Scotia Airlines - HND Computer Science
 Program Version: 2.9
 Code Version: 1.4
 @Author: Dean D. Reid
 */
package couk.deanreid.scotiaairlines.handler;

import couk.deanreid.scotiaairlines.utils.Reference;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public final class ConfigurationHandler {
    public static void main (String[] args) {
        Properties prop = new Properties ();
        FileOutputStream fos = null;
        try {
            // create new file in or open existing file from the project's root folder
            fos = new FileOutputStream (Reference.CONFIG_FILE_NAME);
            // set properties
            prop.put ("DEBUG_MODE", "true");
            prop.put ("DARK_MODE", "true");
            prop.put ("DB_URL", "########");
            prop.put ("DB_TABLE", "##########");
            prop.put ("DB_USER", "###########");
            prop.put ("DB_PASSWORD", "#########");
            // store properties to the opened file
            prop.store (fos, "Comments");
        } catch (IOException io) {
            io.printStackTrace ();
        } finally {
            try {
                if (fos
                        != null) {
                    fos.close ();
                }
            } catch (IOException ex) {
                ex.printStackTrace ();
            }
        }
    }

}
