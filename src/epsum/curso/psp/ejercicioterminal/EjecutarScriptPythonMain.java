package epsum.curso.psp.ejercicioterminal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EjecutarScriptPythonMain {
    public static void main(String[] args) {
        String ruta = "C:\\Users\\ALUMNO\\Documents\\HolaMundo.py";

        ProcessBuilder processBuilder = new ProcessBuilder();

        processBuilder.command("python", ruta);
        
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
