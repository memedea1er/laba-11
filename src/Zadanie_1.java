import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Zadanie_1 extends JFrame implements ActionListener {

    private JTextField textField1;
    private JTextField textField2;

    public Zadanie_1() {
        setTitle("Запись в файл");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label1 = new JLabel("Введите текст (до 25 символов) для записи в файл:");
        textField1 = new JTextField(25);
        JLabel label2 = new JLabel("Введите путь к файлу:");
        textField2 = new JTextField(25);

        JButton button = new JButton("Записать");

        button.addActionListener(this);

        JPanel panel = new JPanel();
        panel.add(label1);
        panel.add(textField1);
        panel.add(label2);
        panel.add(textField2);
        panel.add(button);

        // Добавление панели на окно
        getContentPane().add(panel);

        setVisible(true);
    }

    private void writeToFile(String text, String path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            if (text.length() <= 25) {
                writer.write(text);
                writer.flush();
                JOptionPane.showMessageDialog(this, "Данные успешно сохранены в файл.", "Успех", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Ошибка: Длина текста должна быть не более 25 символов.", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Ошибка при сохранении в файл: " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Zadanie_1::new);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = textField1.getText();
        String path = textField2.getText();
        writeToFile(text, path);
    }
}