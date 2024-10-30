package epsum.curso.psp.ejercicioterminal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EjecutarScriptPowershellMain {
    public static void main(String[] args) {
        String ruta = "C:\\Users\\ALUMNO\\Documents\\script.ps1";

        ProcessBuilder processBuilder = new ProcessBuilder("powershell.exe", "-ExecutionPolicy", "Bypass", "-File", ruta);

        try {
            Process process = processBuilder.start();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
                System.out.println(linea);
            }
            int estadoSalida = process.waitFor();
            System.out.println("El proceso terminó con el código: "+estadoSalida);
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
