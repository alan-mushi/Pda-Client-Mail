package pdaNetwork.misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * 
 * This method is able to process the file.
 * 
 * @author PDA Server Developement Team
 *
 */
public class FileIn {

    /**
     * Path to the storage emplacement.
     */
    String root;

    /**
     * 
     * Constructor of FileIn class.
     * 
     * @param root Chemin vers la racine de l'espace de stockage.
     */
    public FileIn (String root) {
	this.root = root;
    }

    /**
     * 
     * Create a directory.
     * 
     * @param dir Path to the directory which will be create.
     * 
     * @return It returns true if the creation succeeded, else false.
     */
    public boolean createDir (String dir) {
	File file = new File (root+File.separator+dir);
	File path = file.getParentFile ();
	//test que le chemin existe et que le dossier à créer n'existe pas déjà.
	if (!path.exists () || file.exists ())
	    return false;
	return file.mkdirs ();
    }
	
    /**
     * 
     * Delete an empty directory.
     * 
     * @param dir Path to the directory which will be delete.
     * 
     * @return It returns true if the supression succeeded, else false.
     */
    public boolean deleteDir (String dir) {
	File file = new File (root+File.separator+dir);
	return file.exists () && file.isDirectory () && file.delete ();
    }

    /**
     * 
     * Copy a file.
     * 
     * @param start	Path to the file which will be copied.
     * @param end	Path to the destination of the file.
     * 
     * @return		It returns true if the copy succeeded, else false.
     */
    public boolean copy (String start, String end) {
	// Declaration des flux
	FileInputStream sourceFile = null;
	FileOutputStream destinationFile = null;

	try {
	    File source = new File (root+File.separator+start);
	    File destination = new File (root+File.separator+end);

	    // Création du fichier :
            destination.createNewFile ();
            
            // Ouverture des flux
            sourceFile = new java.io.FileInputStream (source);
            destinationFile = new java.io.FileOutputStream (destination);
            
            // Lecture par segment de 0.5Mo 
            byte buffer[] = new byte[512*1024];
	    for (;;) {
		int nbLecture = sourceFile.read (buffer);
		if (nbLecture < 0)
		    break;
            	destinationFile.write (buffer, 0, nbLecture);
            }
	    destinationFile.flush ();
            // Copie réussie
            return true;
	} catch (java.io.FileNotFoundException f ) {
	    System.err.println ("FileIn:copy FileNotFoundException");
	} catch (java.io.IOException e ) {
	    System.err.println ("FileIn:copy IOException");
	} finally {
            // Quoi qu'il arrive, on ferme les flux
            try {
		sourceFile.close ();
            } catch (Exception e) {
	    }
            try {
		destinationFile.close ();
            }
            catch (Exception e) {
	    }
	}
	return false;
    }

    /**
     * 
     * This method move file.
     * 
     * @param start	The path of the file you want to move.
     * @param end	The path of destination.
     * 
     * @return		It returns true if the move succeeded, else false.
     */
    public boolean move (String start, String end) {
	File source = new File (root+File.separator+start);
	File destination = new File (root+File.separator+end);
	return
	    !destination.exists () &&
	    copy (start, end) &&
	    source.delete ();
    }

    /**
     * 
     * This method rename file.
     * 
     * @param start	The path of the file you want to move.
     * @param end	The path of destination.
     * 
     * @return		It returns true if the move succeeded, else false.
     */
    public boolean rename (String start, String end) {

	File source = new File (root+File.separator+start);
	File destination = new File (root+File.separator+end);
	return
	    !destination.exists () &&
	    source.renameTo (destination);
    }

    /**
     * List file in the specified directory.
     * 
     * @param dir Path of the directory you want to list.
     * 
     * @return	An array list containing the name of all the file in the directory.
     */
    public ArrayList<String> getNameList (String dir) {
	File path = new File (root+File.separator+dir);
	String[] files = path.list ();
	ArrayList<String> ret = new ArrayList<String> ();

	if (files != null)
	    for (String file : files)
		ret.add (file);
	return ret;
    }

    /**
     * 
     * @param dir The directory of research.
     * 
     * @return The file in the directory.
     */
    public ArrayList<File> getFileList (String dir) {
	File path = new File (root+File.separator+dir);
	File[] files = path.listFiles ();
	ArrayList<File> ret = new ArrayList<File> ();

	if (files != null)
	    for (File file : files)
		ret.add (file);
	return ret;
    }
}
