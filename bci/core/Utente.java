package bci.core;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.zip.DeflaterOutputStream;

public class Utente implements Serializable {

    public Utente () {
        
    }

    public Object readObject(String inputFilename) throws IOException, ClassNotFoundException {
        ObjectInputStream objIn = null;
        try {
            objIn = new ObjectInputStream(new FileInputStream(inputFilename));
            Object anObject = objIn.readObject();
            return anObject;
        } finally {
            if (objIn != null)
            objIn.close();
        }
    }

    public void saveObject(String filename, Object obj) throws IOException {
        ObjectOutputStream obOut = null;
        try {
            FileOutputStream fpout = new FileOutputStream(filename);
            DeflaterOutputStream dOut = new DeflaterOutputStream(fpout);
            obOut = new ObjectOutputStream(dOut);
            obOut.writeObject(obj);
        } finally {
            if (obOut != null)
            obOut.close();
        }
    }
}
