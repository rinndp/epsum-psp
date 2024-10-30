package epsum.curso.psp.ejercicioterminal;

import java.io.File;
import java.io.IOException;

public class GuardaInfoArchivoMain {
    public static void main(String[] args) {

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("cmd.exe", "/c", "tasklist");

        File file = new File("C:\\Users\\ALUMNO\\Documents\\tasklist.txt");

        try {
            if (!file.exists())
                file.createNewFile();

            processBuilder.redirectOutput(file);

            Process process = processBuilder.start();

            int estadoSalida = process.waitFor();
            System.out.println("El proceso terminó con el código: "+estadoSalida);
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
