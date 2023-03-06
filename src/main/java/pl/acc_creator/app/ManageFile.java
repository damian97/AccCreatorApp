package pl.acc_creator.app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class ManageFile {

    String fileName = Engine.fileName + ".txt";
    String filePatch = Engine.documentsPath + "\\" + fileName;
    File txtFile = new File(filePatch);
    FileWriter fw;
    BufferedWriter bw;

    public ManageFile() {
        if (!txtFile.exists()) {
            try {
                txtFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void addAccountData() {
        LocalDate today = LocalDate.now();
        try {
            fw = new FileWriter(txtFile, true);
            bw = new BufferedWriter(fw);
            bw.write(System.lineSeparator());
            bw.write(System.lineSeparator());
            bw.write(System.lineSeparator());
            bw.write("\t\t" + "Nick postaci" + "\t\t" + "<" + today + ">" + System.lineSeparator());
            bw.write(Engine.email + "@interia.pl" + System.lineSeparator());
            bw.write(Engine.password + System.lineSeparator());
            bw.write(Engine.rKeyString + System.lineSeparator());
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
