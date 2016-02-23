import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;

public class Jogar {
	File replayFile = null;

	public Jogar() throws IOException{
		replayFile = geraLog();
		TabuleiroLogico tabuleiroLogico = new TabuleiroLogico();
		TabuleiroVisual tabuleiroBranco = new TabuleiroVisual(replayFile,tabuleiroLogico,false);
		TabuleiroVisual tabuleiroPreto = new TabuleiroVisual(replayFile,tabuleiroLogico,true);
		tabuleiroBranco.setTabuleiroOposto(tabuleiroPreto);
		tabuleiroPreto.setTabuleiroOposto(tabuleiroBranco);
	}

	public File geraLog() throws IOException{
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Brazil/East"));		
		String sFileName = "replay/"+String.format("%02d",calendar.get(Calendar.HOUR_OF_DAY))+
				 					 String.format("%02d",calendar.get(Calendar.MINUTE))+
				 					 String.format("%02d",calendar.get(Calendar.SECOND))+
				 					 String.format("%02d",calendar.get(Calendar.DAY_OF_MONTH))+
				 					 String.format("%02d",calendar.get(Calendar.MONTH))+
				 					 String.format("%04d",calendar.get(Calendar.YEAR))+".txt";
		
		File replayFile = new File(sFileName); 
		replayFile.createNewFile();
		
		return replayFile;
	}

	public File getReplayFile() {
		return replayFile;
	}

	public void setReplayFile(File replayFile) {
		this.replayFile = replayFile;
	}
}