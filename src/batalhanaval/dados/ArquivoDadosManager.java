package batalhanaval.dados;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ArquivoDadosManager {
	private static final String pathString = "src/ressources/";
	private static Path dadosPath;
	private static Scanner reader;
	private static FileWriter writer;
	
	public static String lerArquivo(String arquivoNome) throws IOException {
		String s = "";
		
		dadosPath = Paths.get(pathString+arquivoNome);
		reader = new Scanner(dadosPath.toFile());
		while(reader.hasNextLine()) {
			s = s.concat(reader.nextLine()+"\n");
		}
				
		return s;
	}
	
	public static void escreverArquivo(String arquivoNome, String conteudo, boolean sobrescrever) throws IOException {
		dadosPath = Paths.get(pathString+arquivoNome);
		writer = new FileWriter(dadosPath.toFile(), !sobrescrever);
		
		writer.write(conteudo);
		writer.close();
	}
}
