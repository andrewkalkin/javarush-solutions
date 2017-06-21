package com.javarush.task.task31.task3109;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

/* 
Читаем конфиги
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Properties properties = solution.getProperties("src/com/javarush/task/task31/task3109/properties.xml");
        properties.list(System.out);

        properties = solution.getProperties("src/com/javarush/task/task31/task3109/properties.txt");
        properties.list(System.out);

        properties = solution.getProperties("src/com/javarush/task/task31/task3109/notExists");
        properties.list(System.out);
    }

    public Properties getProperties(String fileName) {

        Properties result = new Properties();

        try (FileInputStream fIn = new FileInputStream(fileName)) {
            result.loadFromXML(fIn);
        } catch (InvalidPropertiesFormatException e ) {
            try (FileInputStream fIn = new FileInputStream(fileName)) {
                result.load(fIn);
            } catch (InvalidPropertiesFormatException ipe) {

            } catch (FileNotFoundException ffe) {

            } catch (IOException ioe) {

            } finally {
                return result;
            }

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        } finally {
            return result;
        }

    }
}
