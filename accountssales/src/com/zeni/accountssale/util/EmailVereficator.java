package com.zeni.accountssale.util;

import static com.zeni.accountssale.util.EmailSender.sendEmail;

import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class EmailVereficator {
    
    private static final int TIMEOUT_SECONDS = 60;
    
    public static boolean authenticateUser(String userEmail) {
        
        String verificationCode = generateVerificationCode();
        
        sendEmail(userEmail, "Ваш код підтвердження", verificationCode);
        
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Час вийшов. Спробуйте ще раз.");
                //Зробити повернення до головного меню
            }
        }, TIMEOUT_SECONDS * 1000);
        
        // Очікування введення коду користувачем
        System.out.print("Введіть код підтвердження: ");
        Scanner scanner = new Scanner(System.in);
        String userEnteredCode = scanner.nextLine().trim();
        
        timer.cancel();
        if (userEnteredCode != null && userEnteredCode.equals(verificationCode)) {
            System.out.println("Код вірний. Успішна автентифікація!");
            return true;
        } else {
            System.out.println("Код не вірний або термін його дії минув.");
            return false;
        }
    }
    
    private static String generateVerificationCode() {
        // Генерація рандомного коду (у цьому прикладі 6-значний код)
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }
    
}
