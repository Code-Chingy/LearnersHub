package com.techupstudio.Utils;

import java.io.*;
import java.net.URI;
import java.util.*;

import static com.techupstudio.Utils.MyFuncs.format;
import static com.techupstudio.Utils.MyFuncs.println;
import static com.techupstudio.Utils.MyFuncs.range;

public class DirectoryManager {

    private DirectoryManager(){}

    public static class FileManager {

        FileManager(){}

        protected void createDirectory(File parent, String childName){
            File temp = new File(parent, childName);
            if (!temp.exists()){ temp.mkdirs(); }
        }

        private File getDirectory(File parent, String childName){
            File folder = new File(parent, childName);
            return folder;
        }

        public void createFile(File parent, String fileName){
            File temp = new File(parent, fileName);
            if (!temp.exists()){
                try {
                    temp.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void listDir(File file) {
            String[] paths;
            try{
                paths = file.list();
                for(String path : paths)
                {
                    System.out.println(path);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        public void makeDir(String path){ new File(path).mkdirs(); }

        public void write(File file, String line){

            FileWriter WRITER = null;

            if (file.canWrite()){
                try {
                    WRITER = new FileWriter(file);
                    WRITER.write(line);
                    WRITER.flush();
                    WRITER.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void writeAppend(File file, String lines){

            FileWriter WRITER = null;

            if (file.canWrite()){
                try {
                    WRITER = new FileWriter(file);
                    WRITER.write(read(file)+"\n"+lines);
                    WRITER.flush();
                    WRITER.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public String read(File file) {
            String retStr = "";

            FileReader READER = null;

            if (file.canRead()){
                try {
                    READER = new FileReader(file);
                    while (true){
                        int c = READER.read();
                        if (c != -1){
                            retStr += (char)c;
                        }
                        else{ break; }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            try {
                READER.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return retStr;
        }


        public Map<Integer, String> readEnumerate(File file) {

            Map<Integer, String> lines = new HashMap<>();

            String retStr = "";

            FileReader READER = null;

            if (file.canRead()){
                try {
                    READER = new FileReader(file);
                    int i = 1;
                    while (true){
                        int c = READER.read();
                        if (c != -1){
                            if (c == 10){
                                if (!retStr.strip().isEmpty()){
                                    lines.put(i, retStr);
                                    retStr = "";i++;
                                }
                            }
                            else{
                                retStr += (char)c;
                            }
                        }
                        else{
                            if (!retStr.strip().isEmpty()){
                                lines.put(i, retStr);
                            }
                            break;
                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    READER.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return lines;
        }


        public void writeUTF(File file, String lines) {
            if (file.canWrite()) {
                DataOutputStream dataOut = null;
                try {
                    dataOut = new DataOutputStream(new FileOutputStream(file.getAbsolutePath()));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                try {
                    dataOut.writeUTF(lines);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public String readUTF(File file) {
            String retStr = "";

            if (file.canRead()) {
                DataInputStream dataIn = null;
                try {
                    dataIn = new DataInputStream(new FileInputStream(file.getAbsolutePath()));
                    while (dataIn.available() > 0) {
                        String c = dataIn.readUTF();
                        retStr += c;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return retStr;
        }

        public void copy(File file, File newLocation, String newName){

        }

        public void cut(File file, File newLocation){

        }

        public String getEnviron(String name){ return System.getenv(name); }

        public void listEnviron(){ System.getenv().forEach((k,v) -> println("KEY : "+k, "\tVALUE : "+v)); }

    }

    public static class FileExplorer {

        private File HOME;
        private File CURRENTFILE;
        private Collections.Stack<File> FORWARDSTACK;
        private Collections.Stack<File> BACKWARDSTACK;
        private FileManager fm = new FileManager();

        FileExplorer(File directory){ CURRENTFILE = directory; initializeFields(); }

        FileExplorer(String directoryPath){ CURRENTFILE = new File(directoryPath); initializeFields(); }

        FileExplorer(FileExplorer fileExplorer) { CURRENTFILE = fileExplorer.getCurrentFile(); initializeFields(); }

        private void initializeFields(){
            FORWARDSTACK = new Collections.Stack<>();
            BACKWARDSTACK = new Collections.Stack<>();
            HOME = CURRENTFILE;
        }

        public void setHome(File file){ HOME = file; }
        public void setHome(String filePath){ HOME = new File(filePath); }
        public void setHome(FileExplorer file){ HOME = file.getCurrentFile(); }

        public FileExplorer newFolder(String childName){
            fm.createDirectory(CURRENTFILE, childName);
            return this;
        }

        public FileExplorer newFile(String fileName){
            fm.createFile(CURRENTFILE, fileName);
            return this;
        }

        public FileExplorer renameFile(String oldName, String newName) {
            if (getFile(oldName).exists()){
                getFile(oldName).renameTo(new File(CURRENTFILE, newName));
            }
            return this;
        }

        public FileExplorer deleteFile (String childName){
            if (getFile(childName).exists()){
                getFile(childName).delete();
            }
            return this;
        }

//            public void renameSelf(String newName){ CURRENTFILE.renameTo(new File(CURRENTFILE.getParentFile(), newName)); }
//
//            public void deleteSelf(){ CURRENTFILE.delete(); }

        public boolean isFile(){ return CURRENTFILE.isFile(); }

        public boolean isFolder(){ return CURRENTFILE.isFile(); }

        public URI getCurrentFileURI(){ return CURRENTFILE.toURI(); }

        public ReadWritableFile getCurrentFileAsReadWritableFile(){
            if (isFile()){
                try {
                    return new ReadWritableFile(getCurrentFile());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        public ReadWritableFile openReadWritableFile(String fileName){
            if (getFile(fileName).isFile()){
                try {
                    return new ReadWritableFile(getFile(fileName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        public String getCurrentPath(){ return CURRENTFILE.getAbsolutePath(); }

        public String makeFilePath(String newPathName){ return getCurrentPath()+"/"+newPathName; }

        public String[] getFileNames(){ return CURRENTFILE.list(); }

        public File[] getFileItems() { return CURRENTFILE.listFiles(); }


        public FileExplorer createOrOpenFolderIfExist(String fileName){
            if (getFile(fileName).exists()){
                return openFolder(fileName);
            }
            else{ getFile(fileName).mkdirs(); }
            return openFolder(fileName);
        }

        public ReadWritableFile createOrOpenFileIfExist(String fileName){
            if (getFile(fileName).exists()){
                return openReadWritableFile(fileName);
            }
            else{
                try {
                    getFile(fileName).createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return openReadWritableFile(fileName);
        }


        public FileExplorer openFolder(String folderName){
            if (getFile(folderName).exists()) {
                if (getFile(folderName).isDirectory()) {
                    return new FileExplorer(getFile(folderName));
                }
                try {
                    throw new FileNotDirectoryException();
                } catch (FileNotDirectoryException e) {
                    e.printStackTrace();
                }
            }
            else{
                try {
                    throw new FileNotFoundException();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        public FileExplorer exploreFile(String fileName){
            if (getFile(fileName).exists()){
                BACKWARDSTACK.push(getCurrentFile());
                CURRENTFILE = getFile(fileName);
            }
            else{
                try {
                    throw new FileNotFoundException();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            return this;
        }

        public FileExplorer goBack(){
            if (BACKWARDSTACK.isEmpty()){ return null; }
            if (BACKWARDSTACK.peekTop().exists()) {
                FORWARDSTACK.push(getCurrentFile());
                CURRENTFILE = BACKWARDSTACK.pop();
            }
            else {
                try {
                    throw new FileNotFoundException();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            return this;
        }

        public FileExplorer goForward(){
            if (FORWARDSTACK.isEmpty()){ return null; }
            if (FORWARDSTACK.peekTop().exists()) {
                BACKWARDSTACK.push(getCurrentFile());
                CURRENTFILE = FORWARDSTACK.pop();
            }
            else {
                try {
                    throw new FileNotFoundException();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            return this;
        }

        public FileExplorer goHome(){
            if (HOME.exists()) {
                if (CURRENTFILE != HOME) {
                    BACKWARDSTACK.push(getCurrentFile());
                    BACKWARDSTACK.push(getCurrentFile());
                    CURRENTFILE = HOME;
                }
            }
            else {
                try {
                    throw new FileNotFoundException();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            return this;
        }

        public File[] getFileIfNameContains(String pattern){
            List<File> matchesList = new ArrayList<>();
            for (File file : getFileItems()){
                String name = file.getName();
                if (name.toLowerCase().contains(pattern.toLowerCase())){
                    matchesList.add(file);
                }
            }
            File[] matchesArray = new File[matchesList.size()];
            matchesList.toArray(matchesArray);
            return matchesArray;
        }

        public FileExplorer getBackwardFile(){ return new FileExplorer(BACKWARDSTACK.peekTop()); }

        public FileExplorer getForwardFile(){ return new FileExplorer(FORWARDSTACK.peekTop()); }

        public File getCurrentFile(){ return CURRENTFILE; }

        public File getFile(String fileName){return new File(CURRENTFILE, fileName); }

        public boolean isCurrentFileOnDisk(){ return getCurrentFile().exists(); }

        public boolean isFileOnDisk(String fileName){ return getFile(fileName).exists(); }

        public FileExplorer ls(){ if (CURRENTFILE.isDirectory()) { fm.listDir(CURRENTFILE); } return this; }

        public FileExplorer clone(){ return new FileExplorer(this); }

        private class FileNotDirectoryException extends Exception { FileNotDirectoryException(){} }
    }

    public static class ReadWritableFile{
        private File FILE;
        private Scanner FILESCANNER;
        private FileWriter WRITER;
        //private FileReader READER;

        ReadWritableFile(File file) throws IOException { initializeFile(file); }

        ReadWritableFile(String filepath) throws IOException { initializeFile(new File(filepath)); }

        private void initializeFile(File file) throws IOException {
            if (file.canWrite() || file.canRead()){
                FILE = file;
                FILESCANNER = new Scanner(FILE);
                WRITER = new FileWriter(FILE);
            }
            else{
                try {
                    throw new FileNotReadWritableException();
                } catch (FileNotReadWritableException e) {
                    e.printStackTrace();
                }
            }
        }

        public ReadWritableFile write(Object... lines){
            if (lines.length > 0) {
                try {
                    String writableLines = "";
                    for (Object line : lines) {
                        writableLines += line.toString() + "\n";
                    }
                    WRITER = new FileWriter(getFile());
                    WRITER.write(writableLines + "\n");
                    WRITER.flush();
                    WRITER.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return this;
        }

        private void write(Collection<Object> lines) { write(lines.toArray()); }

        public ReadWritableFile writeAppend(Object... lines){
            String writableLines = "";
            if (lines.length > 0) {
                for (int i : range(lines.length)) {
                    writableLines += lines[i].toString() + ((i == lines.length-1) ?  "" : "\n");
                }
                write(read()+writableLines);
            }
            return this;
        }

        private ReadWritableFile writeAppend(Collection<Object> lines) { writeAppend(lines.toArray()); return this; }

        public ReadWritableFile writeReplaceLine(int lineIndex, Object line){
            if (lineIndex < numberOfLines()){
                Map<Integer, Object> DATA =  readEnumerate();
                DATA.put(lineIndex, line.toString());
                write(DATA.values());
            }
            return this;
        }


        public String read(){
            String content = "";

            try {
                FILESCANNER = new Scanner(getFile());
                while (FILESCANNER.hasNextLine()){ content += FILESCANNER.nextLine() + ((FILESCANNER.hasNextLine()) ? "\n" : ""); }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            return content;
        }

        public Map<Integer, Object> readEnumerate(){
            Map<Integer, Object> LINES_LIST =  new HashMap<>();
            int i = 0;
            try {
                FILESCANNER = new Scanner(getFile());
                while (FILESCANNER.hasNextLine()){
                    String line = FILESCANNER.nextLine();
                    LINES_LIST.put(i, line);i++;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if (LINES_LIST.size() > 0 && LINES_LIST.get(LINES_LIST.size()-1).toString().trim().isEmpty()){
                LINES_LIST.remove(LINES_LIST.size()-1);
            }
            return LINES_LIST;
        }

        public String readLineAt(int index){
            if (index  > 0 && index < numberOfLines()) { return readEnumerate().get(index).toString(); }
            else throw new IndexOutOfBoundsException();
        }

        public class IterableFile{

            private Scanner SCANNER;

            IterableFile(File file){
                try {
                    SCANNER = new Scanner(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

            public String readNextLine(){ return SCANNER.nextLine(); }

            public String readNextWord(){ return SCANNER.next(); }

            public boolean hasNextLine(){ return FILESCANNER.hasNextLine(); }

            public boolean hasNextWord(){ return FILESCANNER.hasNext(); }

            public boolean isReadable(){ return FILE.canRead(); }

            public Scanner getScannerbleFile(){
                try {
                    return new Scanner(getFile());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                return null;
            }

        }

        public boolean isReadable(){ return FILE.canRead(); }

        public boolean isWritable(){ return FILE.canWrite(); }

        public boolean isReadWritable(){ return FILE.canWrite() && FILE.canWrite(); }

        public IterableFile getIterableFile(){ return new IterableFile(getFile()); }

        public File getFile(){ return FILE; }

        public String getFileName(){ return FILE.getName(); }

        public String getAbsoluteFilePath(){ return getFile().toPath().toString(); }

        public int numberOfLines(){ return readEnumerate().size(); }

        public ReadWritableFile clearFile(){
            try {
                WRITER = new FileWriter(getFile());
                WRITER.write("");
                WRITER.flush();
                WRITER.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return this; }

        public String toString(){ return format("ReadWritableFile('<>')", getAbsoluteFilePath()); }

        private class FileNotReadWritableException extends Exception { }

//        ReadWritableFile(){
//            try {
//                WRITER.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

    }

}
