import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {

    private JFrame frame;
    private JPanel panel;
    private JTextField nameField;
    private JTextField priceField;
    private JTextField quantityField;
    private JTextField manufacturerField;
    private JTextField materialField;
    private JTextField partsField;
    private Shop shop;

    public Main() {
        shop = new Shop(10);

        frame = new JFrame("Магазин продуктов");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel(new GridLayout(0, 2));
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        JLabel nameLabel = new JLabel("Наименование:");
        nameField = new JTextField(20);
        panel.add(nameLabel);
        panel.add(nameField);

        JLabel priceLabel = new JLabel("Цена, кг:");
        priceField = new JTextField(20);
        panel.add(priceLabel);
        panel.add(priceField);

        JLabel quantityLabel = new JLabel("Количество, кг:");
        quantityField = new JTextField(20);
        panel.add(quantityLabel);
        panel.add(quantityField);

        JLabel manufacturerLabel = new JLabel("Поставщик:");
        manufacturerField = new JTextField(20);
        panel.add(manufacturerLabel);
        panel.add(manufacturerField);

        JButton addCategoryBtn = new JButton("Добавить товар");
        addCategoryBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                double price = Double.parseDouble(priceField.getText());
                int quantity = Integer.parseInt(quantityField.getText());
                String manufacturer = manufacturerField.getText();

                Category category = new Category(name, price, quantity, manufacturer);

                shop.addProduct(category);

                JOptionPane.showMessageDialog(null, "Товар добавлен");
            }
        });
        panel.add(addCategoryBtn);

        JButton showStatsButton = new JButton("Статистика");
        showStatsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showStatistics(shop);
            }
        });
        panel.add(showStatsButton);

        JButton showTableButton = new JButton("Показать таблицу");
        showTableButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createTable(shop);
            }
        });
        panel.add(showTableButton);


        frame.pack();
        frame.setVisible(true);
    }

    public void showStatistics(Shop shop) {
        if (shop.getProducts() == null) {
            System.out.println("Магазин пуст");
            return;
        }
        int totalQuantity = 0;
        double totalCost = 0;
        for (Product product : shop.getProducts()) {
            if (product != null) {
                totalQuantity += product.getQuantity();
                totalCost += product.getPrice() * product.getQuantity();
            }
        }
        System.out.println(totalCost);
        String message = String.format("Общее количество товаров: %d\nОбщая стоимость: %.2f", totalQuantity, totalCost);
        System.out.println(message);
        JOptionPane.showMessageDialog(null, message);
    }

    public void createTable(Shop shop) {
        // Создание таблицы с товарами
        String[] columnNames = {"Наименование", "Цена, кг", "Количество, кг", "Поставщик"};
        Object[][] rowData = new Object[shop.getProducts().length][5];
        for (int i = 0; i < shop.getProducts().length; i++) {
            if (shop.getProducts()[i] != null) {
                rowData[i][0] = shop.getProducts()[i].getName();
                rowData[i][1] = shop.getProducts()[i].getPrice();
                rowData[i][2] = shop.getProducts()[i].getQuantity();
                rowData[i][3] = shop.getProducts()[i].getManufacturer();
            }
        }
        JTable table = new JTable(rowData, columnNames);

        // Создание панели для таблицы с возможностью скроллинга
        JScrollPane scrollPane = new JScrollPane(table);

        // Создание нового окна для отображения таблицы
        JFrame tableFrame = new JFrame("Таблица с товарами");
        tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tableFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        tableFrame.pack();
        tableFrame.setSize(1000, 500);
        tableFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
    }
}
