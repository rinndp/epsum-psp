package epsum.curso.psp.ejercicioterminal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AbrirNotepadMain {
    public static void main(String[] args) {
        String ruta = "C:\\Users\\ALUMNO\\Documents\\script.ps1";

        ProcessBuilder processBuilder = new ProcessBuilder("powershell.exe");

        try {
            Process process = processBuilder.start();
            System.out.println("El archivo se ha abierto");

            int estadoSalida = process.waitFor();
            System.out.println("El proceso terminó con el código: "+estadoSalida);
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
