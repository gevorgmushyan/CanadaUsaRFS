package policy;

import java.io.*;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PolicyHolderSerializer {
    private static String fileDirName = System.getProperty("user.dir") + File.separator + "requirements" + File.separator + "testUsers";

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public synchronized void writeToFile(ArrayList<PolicyHolder> users, String fileName, int region) throws IOException {
        File dir = new File(fileDirName);
        dir.mkdirs();
        File file = new File(dir, fileName + region);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(users);
        oos.flush();
        oos.close();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public synchronized void addToFile(List<PolicyHolder> users, String fileName, int region) throws IOException, ClassNotFoundException {
        File dir = new File(fileDirName);
        dir.mkdirs();
        List<PolicyHolder> oldUsers = null;
        try {
            oldUsers = readFromFile(fileName, region);
        } catch (Exception e) {
            e.printStackTrace();
        }
        FileOutputStream file = new FileOutputStream(dir + File.separator + fileName + region, false);
        ObjectOutputStream oos = new ObjectOutputStream(file);
        if (oldUsers == null)
            oldUsers = new ArrayList<>();
        oldUsers.addAll(users);
        oos.writeObject(oldUsers);
        oos.flush();
        oos.close();
        file.close();
    }

    @SuppressWarnings("unchecked")
    private ArrayList<PolicyHolder> readFromFile(File f) throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream(f);
        ObjectInputStream oi = new ObjectInputStream(fi);
        Object obj = null;
        try {
            obj = oi.readObject();
        } catch (IndexOutOfBoundsException e) {
        }
        finally {
            oi.close();
            fi.close();
        }
        return (ArrayList<PolicyHolder>) obj;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<PolicyHolder> readFromFile(String fileName, int region) throws IOException, ClassNotFoundException {
        File f = new File(fileDirName, fileName + region);
        return readFromFile(f);
    }

    @SuppressWarnings("unchecked")
    public synchronized boolean deleteFromFile(String fileName, int region, int itemNum) throws IOException, ClassNotFoundException {
        File f = new File(fileDirName, fileName + region);
        ArrayList<PolicyHolder> users = readFromFile(f);
        if (itemNum < 0 || itemNum >= users.size())
            return false;
        users.remove(itemNum);
        writeToFile(users, fileName, region);
        return true;
    }

    public synchronized static void deleteUsersFile(String fileName, int region) {
        try {
            Files.deleteIfExists(Paths.get(fileDirName + File.separator + fileName + region));
        } catch (NoSuchFileException e) {
            System.out.println("No such file/directory exists");
        } catch (DirectoryNotEmptyException e) {
            System.out.println("Directory is not empty.");
        } catch (IOException e) {
            System.out.println("Invalid permissions.");
        }
        System.out.println("Deletion successful.");
    }
}
