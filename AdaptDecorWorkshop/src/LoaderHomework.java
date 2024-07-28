//Все необходимые данные запрашиваются;
//


import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;

public class LoaderHomework {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите директорию (путь): ");
        String directory = scanner.nextLine().trim();

        System.out.print("Введите имя файла с указанием расширения: ");
        String name = scanner.nextLine().trim();

        System.out.print("Введите текст, который вы хотите сохранить: ");
        String text = scanner.nextLine().trim();


        if (directory.isEmpty() || name.isEmpty() || text.isEmpty()) {
            System.out.println("Все поля обязательны! Убедитесь в заполнении каждого.");
            return;
        }

        File newDir = new File(directory);
        if (!newDir.exists()) {
            newDir.mkdirs();
        }


        File file = new File(newDir, name);
        try {
            long lastMod = file.lastModified();
            savingInput(file, text);
            long size = Files.size(file.toPath());
            System.out.println("Файл сохранен под названием " + name);
            System.out.println("Размер файла: " + size + " (в байт)");
            System.out.println("Последняя модификация: " + lastMod);
        } catch (IOException e) {
            System.out.println("Что-то пошло не так: " + e.getMessage());
            return;
        }

        System.out.print("Хотите прочитать файл? 'Да' или 'Нет', простой ответ: ");
        String request = scanner.nextLine().trim();
        if (request.equalsIgnoreCase("Да")) {
            System.out.println("Я б сказал...");
            try {
                System.out.println("Содержимое файла: ");
                readContent(file);
            } catch (IOException e) {
                System.out.println("Что-то пошло не так, сорри: " + e.getMessage());
            }
        } else {
            System.out.println("Не хочешь не надо");
        }
    }

    private static void savingInput(File file, String text) throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(text.getBytes());
        }
    }
    private static void readContent(File file) throws IOException {
    try (FileInputStream fin = new FileInputStream(file)) {
            int i = -1;
            while ((i = fin.read()) != -1) {
                System.out.print((char)i);
                }
            }
    }
}