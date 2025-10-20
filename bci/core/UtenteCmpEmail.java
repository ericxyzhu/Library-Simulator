package bci.core;

import java.util.*;

public class UtenteCmpEmail implements Comparator<Utente> {
    public int compare (Utente u1, Utente u2) {
        int ret = u1.getEmail().compareTo(u2.getEmail());
        if (ret == 0) return (int)(u1.getId() - u2.getId());
        return ret;
    }
    
}