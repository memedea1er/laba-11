import java.io.*;
import javax.swing.*;

public class Zadanie_2 extends JFrame {

    private JTextField lastNameField;
    private JTextField firstNameField;
    private JTextField middleNameField;
    private JTextField birthDateField;
    private JTextField groupField;

    public Zadanie_2() {
        setTitle("Форма ввода данных");
        setSize(320, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lastNameLabel = new JLabel("Фамилия:");
        lastNameField = new JTextField(20);
        JLabel firstNameLabel = new JLabel("Имя:");
        firstNameField = new JTextField(20);
        JLabel middleNameLabel = new JLabel("Отчество:");
        middleNameField = new JTextField(20);
        JLabel birthDateLabel = new JLabel("Дата рождения:");
        birthDateField = new JTextField(20);
        JLabel groupLabel = new JLabel("Учебная группа:");
        groupField = new JTextField(20);

        JButton saveButton = new JButton("Записать");
        JButton loadButton = new JButton("Загрузить");

        saveButton.addActionListener(e -> saveDataToFile());

        loadButton.addActionListener(e -> loadDataFromFile());

        JPanel panel = new JPanel();
        panel.add(lastNameLabel);
        panel.add(lastNameField);
        panel.add(firstNameLabel);
        panel.add(firstNameField);
        panel.add(middleNameLabel);
        panel.add(middleNameField);
        panel.add(birthDateLabel);
        panel.add(birthDateField);
        panel.add(groupLabel);
        panel.add(groupField);
        panel.add(saveButton);
        panel.add(loadButton);

        getContentPane().add(panel);

        setVisible(true);
    }

    private void saveDataToFile() {
        String lastName = lastNameField.getText();
        String firstName = firstNameField.getText();
        String middleName = middleNameField.getText();
        String birthDate = birthDateField.getText();
        String group = groupField.getText();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("2.txt"))) {
            writer.write(lastName + "\n");
            writer.write(firstName + "\n");
            writer.write(middleName + "\n");
            writer.write(birthDate + "\n");
            writer.write(group + "\n");
            writer.flush();
            JOptionPane.showMessageDialog(this, "Данные успешно сохранены в файл.", "Успех", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Ошибка при сохранении в файл: " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadDataFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("2.txt"))) {
            String lastName = reader.readLine();
            String firstName = reader.readLine();
            String middleName = reader.readLine();
            String birthDate = reader.readLine();
            String group = reader.readLine();

            lastNameField.setText(lastName);
            firstNameField.setText(firstName);
            middleNameField.setText(middleName);
            birthDateField.setText(birthDate);
            groupField.setText(group);

            JOptionPane.showMessageDialog(this, "Данные успешно загружены из файла.", "Успех", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Ошибка при загрузке из файла: " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Zadanie_2::new);
    }
}