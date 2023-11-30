package com.example.project510fx.Util;

import com.example.project510fx.Entities.*;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ResourceManager {

    static final String ENTRIES = "DatabaseEntries.xlsx";
    static final String COMMANDS = "CreateCommands.txt";

    static final String DROPS = "DropTables.txt";

    static final String MANUAL = "Manual.txt";

    private final File worksheet;
   private List<Feedback> feedbackList = null;
   private List<Media> mediaList = null;
   private List<Member> memberList = null;
   private List<Penalty> penaltyList = null;
   private List<Transaction> transactions = null;
   private List<Librarian> librarians = null;
   private List<String> manual = null;
    private static ResourceManager manager = null;

    private ResourceManager() throws IOException, IllegalStateException {
        worksheet = copyResource(ENTRIES);

    }

    public static ResourceManager getInstance() throws IOException, IllegalStateException {
        if (manager == null) {
            manager = new ResourceManager();
        }
        return manager;
    }

    /*
    This function copies a file from the resources folder to the working directory that the jar file is running in.
    For this to work, the resource must be in the resources folder in the project.
    Examples of files in the resources folder: DatabaseEntries.xlsx, CreateCommands.txt
     */
    private File copyResource(String resourceName) throws IllegalStateException, IOException {
       URL resource = ResourceManager.class.getClassLoader().getResource(resourceName);

       String destination = ResourceManager.class.getProtectionDomain().getCodeSource().getLocation().getPath().substring(1);
       String reversed = new StringBuilder(destination).reverse().toString();

       char replace;
       if (reversed.contains("\\")) {
        replace = '\\';
       }
       else replace = '/';

       destination = new StringBuilder(reversed.substring(reversed.indexOf(replace))).reverse().toString();
       destination += resourceName;


       if (resource == null) {
           throw new IllegalStateException("Could not find resource "+resourceName);
       }

           URLConnection connection = resource.openConnection();
           connection.setUseCaches(false);

           InputStream stream = connection.getInputStream();

           Path path = Path.of(destination);

           File file = new File(path.toString());
               try {
                   java.nio.file.Files.copy(stream, path);
               } catch (FileAlreadyExistsException ignored) {

               }

           return file;
    }



    /*
    This function returns a list of strings for each table creation command, reading them
    from the CreateCommands.txt file.
     */
    public List<String> getTableCreateCommands() {
        List<String> cmdList = new ArrayList<>();
        BufferedReader reader = null;
        try {
            File commands = copyResource(COMMANDS);
            reader = new BufferedReader(new FileReader(commands));

            String line = reader.readLine();
            StringBuilder command = new StringBuilder("");

            while (line != null) {
                line = line.strip();
                if (line.length() > 0) {  //if the line is not just a \n
                    line = line + " ";
                    command.append(line);
                }
                else {
                    if (command.length() > 0) {
                        cmdList.add(command.toString());
                        command = new StringBuilder();
                    }
                }

                line = reader.readLine();
            }

            cmdList.add(command.toString());

        }
        catch (Exception e) {
            throw new IllegalStateException("Could not read creation commands from file: "+e.getMessage());
        }
        finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            }
            catch (Exception ignored) {
            }

        }
        return cmdList;
    }


    /*
    This function reads all of the drop table commands from the DropTables.txt file
     */
    public List<String> getDropTableCommands() {
        List<String> commandList = new ArrayList<>();
        BufferedReader reader = null;

        try {
            File file = copyResource(DROPS);
             reader = new BufferedReader(new FileReader(file));

            String line = reader.readLine();

            while (line != null) {
            commandList.add(line);
            line = reader.readLine();
            }

        }
        catch (Exception e) {
            throw new IllegalStateException("Could not get drop table commands: "+e.getMessage());
        }
        finally {
            try {
                if (reader != null)
                    reader.close();
            }
            catch (Exception ignored) {

            }
        }
        return commandList;
    }



    /*
Returns a list of string arrays which contains the attributes from the excel table.
I.e if the table is  A B C D
                     1 2 3 4
                     5 6 7 8

 Then the string arrays in the list will be [1][2][3][4], [5][6][7][8] ... etc.
 Name: Name of the excel page. (E.g Media, Transaction, etc)
 Worksheet: The excel file. Use copyResource() to get this.
 numAttributes: the length of the string array.

 Throws: IllegalStateException if the worksheet fails to open.
 */
    private List<String[]> getWorksheetEntries(String name, File worksheet, int numAttributes) throws IllegalStateException {

        List<String[]> list = new ArrayList<>();
        try (
                FileInputStream stream = new FileInputStream(worksheet);
                XSSFWorkbook workbook = new XSSFWorkbook(stream)
        ) {

            XSSFSheet sheet = workbook.getSheet(name);
            Iterator<Row> rows = sheet.iterator();

            boolean skip = true;
            while (rows.hasNext()) {

                if (skip) {
                    rows.next();
                    skip = false;
                    continue;
                }

                Row row = rows.next();

                Iterator<Cell> cellIterator = row.cellIterator();

                if (!cellIterator.hasNext()) {
                    continue;
                }

                String[] attributes = new String[numAttributes];
                int index = 0;

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    CellType type = cell.getCellType();

                    if (index >= attributes.length)
                        break;

                    if (type == CellType._NONE || type == CellType.BLANK || type == CellType.ERROR) {
                        break;
                    }

                    if (type == CellType.FORMULA) {
                        throw new IllegalArgumentException("Formula data error:" +
                                " Remove the formula for cell at row, column: ["+cell.getRowIndex()+", "+cell.getColumnIndex()+"]" +
                                " in worksheet "+sheet.getSheetName()+" and replace it with a constant value!");
                    }

                    if (type == CellType.STRING) {
                        String value = cell.getStringCellValue();
                        if (value == null || value.length() == 0)
                            break;

                        attributes[index] = value;
                        index++;
                        continue;
                    }

                    if (type == CellType.NUMERIC) {
                        if (DateUtil.isCellDateFormatted(cell)) {
                            attributes[index] = new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue());
                        } else attributes[index] = "" + cell.getNumericCellValue();
                        index++;
                    }
                }
                list.add(attributes);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Could not read from worksheet " + worksheet.getName() + " due to: " + e.getMessage());
        }

        return list;
    }



    /*
    Reads the feedback data from the excel sheet.
    use copyResource() to get the File object to pass into this function.
     */
    public List<Feedback> getFeedbackEntries() {

        if (feedbackList != null) {
            return feedbackList;
        }

        feedbackList = new ArrayList<>();
        try {
            //int int int int string
            //feedbackid, memid, libid, stars, comment
            List<String[]> values = getWorksheetEntries("Feedback", worksheet, 5);
            for (String[] array: values) {
                int feedbackId, memId, libId, stars;
                String comment;

                feedbackId = (int)Double.parseDouble(array[0]);
                memId = (int)Double.parseDouble(array[1]);
                libId = (int)Double.parseDouble(array[2]);
                stars = (int)Double.parseDouble(array[3]);
                comment = array[4];

                Feedback feedback = new Feedback(feedbackId,memId,libId,comment,stars);
                feedbackList.add(feedback);
            }
        }
        catch (Exception e) {
            feedbackList = null;
            throw new IllegalStateException("Error parsing feedback: "+e.getMessage());
        }

        return feedbackList;
    }



    /*
    This function gets all of the media objects from the excel file
     */
    public List<Media> getMediaEntries() {

        if (mediaList != null) {
            return mediaList;
        }
         mediaList = new ArrayList<>();
        try {
            //int str str str str int
            //id  auth title date type stock
            List<String[]> values = getWorksheetEntries("Media", worksheet, 6);
            for (String[] array: values) {
                int id, stock;
                String auth, title, publish, type;

                id = (int)Double.parseDouble(array[0]);
                auth = array[1];
                title = array[2];
                publish = array[3];
                type = array[4];
                stock = (int)Double.parseDouble(array[5]);

                mediaList.add(new Media(auth,title,publish,id, stock, type));
            }
        }
        catch (Exception e) {
            mediaList = null;
            throw new IllegalStateException("Error parsing media: "+e.getMessage());
        }

        return mediaList;
    }


    /*
    This function gets all of the member objects from the excel file
     */
    public List<Member> getMemberEntries() {

        if (memberList != null) {
            return memberList;
        }
       memberList = new ArrayList<>();

        String[] debug = null;
        try {
            //int str str str str double
            //id username pass email name owe
            List<String[]> values = getWorksheetEntries("Members", worksheet, 6);
            for (String[] array: values) {

                debug = array;
                int id;
                double owe;
                String user, pass, email, name;

                id =  (int)Double.parseDouble(array[0]);
                user = array[1];
                pass = array[2];
                email = array[3];
                name = array[4];
                owe = Double.parseDouble(array[5]);

                memberList.add(new Member(id, owe, user, pass, email, name));
            }
        }
        catch (Exception e) {
            memberList = null;
            throw new IllegalStateException("Error parsing members: "+e.getMessage());
        }

        return memberList;
    }


    /*
This function gets all of the penalty objects from the excel file
 */
    public List<Penalty> getPenaltyEntries() {

        if (penaltyList != null) {
            return penaltyList;
        }

      penaltyList = new ArrayList<>();
        try {
            //int str str str str double
            //id username pass email name owe
            List<String[]> values = getWorksheetEntries("Penalties", worksheet, 3);
            for (String[] array: values) {
                int id, memId, histId;

                id = (int)Double.parseDouble(array[0]);
                memId = (int)Double.parseDouble(array[1]);
                histId = (int)Double.parseDouble(array[2]);


                penaltyList.add(new Penalty(id, memId, histId));
            }
        }
        catch (Exception e) {
            penaltyList = null;
            throw new IllegalStateException("Error parsing penalties: "+e.getMessage());
        }

        return penaltyList;
    }


    /*
    This function gets all of the transaction objects from the excel file
     */
    public List<Transaction> getTransactionEntries() {

        if (transactions != null) {
            return transactions;
        }

        transactions = new ArrayList<>();

        try {

            //int str str int int int
            //histid pckup expire status memid mediaid
            List<String[]> values = getWorksheetEntries("Transaction", worksheet, 6);
            for (String[] array: values) {

                int histId, status, memId, mediaId;
                String pickupDate, expireDate;

                histId = (int)Double.parseDouble(array[0]);
                pickupDate = array[1];
                expireDate = array[2];
                status = (int)Double.parseDouble(array[3]);
                memId = (int)Double.parseDouble(array[4]);
                mediaId = (int)Double.parseDouble(array[5]);

                transactions.add(new Transaction(histId,mediaId,status,memId,pickupDate,expireDate));
            }
        }
        catch (Exception e) {
            transactions = null;
            throw new IllegalStateException("Error parsing transactions: "+e.getMessage());
        }

        return transactions;
    }


    /*
    This function gets all of librarian objects from the excel file
     */
    public List<Librarian> getLibrarianEntries() {
        //int int str
        //libid key name

        if (librarians != null) {
            return librarians;
        }

       librarians = new ArrayList<>();
        try {
            List<String[]> values = getWorksheetEntries("Librarian", worksheet, 6);
            for (String[] array: values) {
                int libId, key;
                String name;

                libId = (int)Double.parseDouble(array[0]);
                key = (int)Double.parseDouble(array[1]);
                name = array[2];

                librarians.add(new Librarian(libId,key, name));
            }
        }
        catch (Exception e) {
            librarians  = null;
            throw new IllegalStateException("Error parsing librarians: "+e.getMessage());
        }

        return librarians;

    }

    /*
    This function gets all of the string values from the manual.txt file
     */
    public List<String> getManual() {

        if (manual != null) {
            return manual;
        }

        manual = new ArrayList<>();
        BufferedReader reader = null;

        try {
            File file = copyResource(MANUAL);
            reader = new BufferedReader(new FileReader(file));

            String line = reader.readLine();

            while (line != null) {
                manual.add(line);
                line = reader.readLine();
            }
        }
        catch (Exception e) {
            manual = null;
            throw new IllegalStateException("Could not get manual entries: "+e.getMessage());
        }
        finally {
            try {
                if (reader != null)
                    reader.close();
            }
            catch (Exception ignored) {

            }
        }
        return manual;
    }


    /*

    public void printArr(String[] a) {
        if (a == null) {
            System.out.println("null");
            return;
        }

        System.out.print("[");
        for (String s: a)
            System.out.print(s+",");
        System.out.print("]");
    }

     */

}
