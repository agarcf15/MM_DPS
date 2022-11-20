package maristmessage.modelo.dao;

//import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
//import java.io.OutputStream;
/**
 * 
 */
public class CopiaBDDao implements ICopiaBD {
	public void realizarCopia() {
		try {
		      Process p = Runtime
		            .getRuntime()
		            .exec("mysqldump.exe -h db4free.net -P 3306 -u maristmessage -ph7Hdb3Jkr maristmessage_bb");
      
		      InputStream is = p.getInputStream();
		      FileOutputStream fos = new FileOutputStream("copia_bd.sql");
		      byte[] buffer = new byte[1000];

		      int leido = is.read(buffer);
		      while (leido > 0) {
		         fos.write(buffer, 0, leido);
		         leido = is.read(buffer);
		      }

		      fos.close();

		   } catch (Exception e) {
		      e.printStackTrace();
		   }
	}
}
