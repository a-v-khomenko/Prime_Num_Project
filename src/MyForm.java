import javax.swing.*;

public class MyForm extends JFrame {
    private JButton button_msg;
    private JPanel panelMain;
    private JTextField textField1;
    private JTextField textField2;
    private JTextArea textArea1;

    /* Вивеси на екран прості числа на проміжку
     * return добуток цих чисел */
    public long GenerateNum(int lower, int higher) {
        boolean[] prime = new boolean[higher+1];
        // ініціалзація масиву одиницями
        for(int i = 0; i <= higher; i++) {
            prime[i] = true;
        }
        for(int p = 2; p * p <= higher; p++) {
            if(prime[p]) {
                for(int i = p * p; i <= higher; i += p) {
                    prime[i] = false; // викреслення індексів
                }
            }
        }
        long num = 1;
        textArea1.append("  Прості числа на даному проміжку: ");
        for(int p = lower; p <= higher; p++) {
            if(prime[p]) {
                textArea1.append(p + " ");
                num *= p;
            }
        }
        textArea1.append("\n  Згенероване початкове число: " + num);
        return num;
    }
    /* Перевірка простоти числа
    *  return boolean */
    public static boolean IsPrime(long number) {
        // Перевірка на від'ємність та 0, 1, 2
        if (number <= 1) return false;
        if (number == 2) return true;
        // Перевірка чи число кратне 2
        if (number % 2 == 0) return false;
        // Перевірка дільників до квадратного кореня з числа
        for (long i = 3; i * i <= number; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    // Метод, що перевіряє, чи є число квадратом іншого числа
    public static boolean IsSquare(long num) {
        long squareRoot = (long) Math.sqrt(num);
        return squareRoot * squareRoot == num;
    }

    // Метод для факторизації числа методом Ферма
    public static long[] FermaFactorization(long n) {
        long[] factors = new long[2];
        long a = (long) Math.ceil(Math.sqrt(n));
        long b2 = a * a - n;

        while (!IsSquare(b2)) {
            a++;
            b2 = a * a - n;
        }

        factors[0] = a - (long) Math.sqrt(b2);
        factors[1] = a + (long) Math.sqrt(b2);
        return factors;
    }

    // Метод для запуску факторизації числа та його множників рекурсивно
    public void factorizeRecursively(long n) {

        long[] factors = FermaFactorization(n);

        for (long factor : factors) {
            if (!IsPrime(factor)) {
                factorizeRecursively(factor);
            } else {
                textArea1.append(factor + " ");
            }
        }
    }

    public MyForm() {
        textArea1.setEditable(false); // Забороняємо редагування тексту всередині текстового поля
        textArea1.setLineWrap(true); // Встановлення переносу тексту
        textArea1.setWrapStyleWord(true); // Встановлення переносу за словами

        button_msg.addActionListener(e -> {
            try {
                textArea1.setText(""); // Очищення тексту в полі JTextArea з назвою textArea1
                // Отримуємо рядок з текстового поля та перетворюємо його на число
                int number1 = Integer.parseInt(textField1.getText());
                int number2 = Integer.parseInt(textField2.getText());
                // Додаємо результат в JTextArea
                long genNum = GenerateNum(number1, number2);
                //-------------------------------------------------------------------------
                textArea1.append("\n\n  Блок перевірки чисел на простоту:");
                short seed;
                boolean[] primeArr = new boolean[6]; // Масив для позицій простих чисел
                for(int i = 1; i <= 5; i++){
                    if(i==1) seed = 983;
                    else if(i==2) seed = 991;
                    else if(i==3) seed = 997;
                    else if(i==4) seed = 1009;
                    else seed = 1013;
                    if(IsPrime(genNum + seed)) {
                        primeArr[i] = true;
                        textArea1.append("\n  Число " + (genNum + seed) + " є простим");
                    }
                    else textArea1.append("\n  Число " + (genNum + seed) + " не є простим");
                }
                //-------------------------------------------------------------------------
                textArea1.append("\n\n  Блок факторизації чисел методом Ферма:");
                for(int i = 1; i <= 5; i++) {
                    if (i == 1) seed = 983;
                    else if (i == 2) seed = 991;
                    else if (i == 3) seed = 997;
                    else if (i == 4) seed = 1009;
                    else seed = 1013;
                    if (primeArr[i])
                        textArea1.append("\n  Множники числа " + (genNum + seed) + " відсутні, бо число просте");
                    else {
                        textArea1.append("\n  Множники числа " + (genNum + seed) + ": {");
                        factorizeRecursively(genNum + seed);
                        textArea1.append("}");
                    }
                }

            } catch (NumberFormatException ew) {
                // Обробка винятку у випадку, якщо введене значення не може бути перетворено на число
                textArea1.append("  Помилка: Введіть коректне ціле число.");
            }
        });
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Main");
        frame.setContentPane(new MyForm().panelMain);
        frame.setBounds(720, 260, 600, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
