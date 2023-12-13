package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;

public class SerpinskyTriangle extends JFrame {
    private GeneralPath path = new GeneralPath();

    /**
     * Конструктор класса. Инициализирует окно и строит треугольник Серпинского.
     */
    public SerpinskyTriangle() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        path = new GeneralPath();
        buildSerpinsky(path, new Point(-200, 60), new Point(0, -150), new Point(200, 60), 7);
    }

    /**
     * Метод для рисования графики на окне.
     * @param g Графический контекст.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.translate(getWidth() / 2, getHeight() / 2);
        g2d.setColor(Color.BLACK);
        g2d.draw(path);
    }

    /**
     * Метод для построения треугольника Серпинского.
     * @param path Объект GeneralPath для построения пути.
     * @param p1   Первая точка треугольника.
     * @param p2   Вторая точка треугольника.
     * @param p3   Третья точка треугольника.
     * @param iterations Количество итераций для построения.
     */
    private void buildSerpinsky(GeneralPath path, Point p1, Point p2, Point p3, int iterations) {
        // Устанавливаем начальную точку пути на p1
        path.moveTo(p1.getX(), p1.getY());
        // Добавляем линию от текущей точки до p2
        path.lineTo(p2.getX(), p2.getY());
        // Добавляем линию от текущей точки до p3
        path.lineTo(p3.getX(), p3.getY());
        // Замыкаем путь, соединяя последнюю точку с первой
        path.closePath();

        iterations--;

        if (iterations > 0) {
            // Вычисляем координаты середины отрезка между p1 и p2 (p12)
            Point p12 = new Point((int) (p1.getX() + (p2.getX() - p1.getX()) / 2),
                    (int) (p1.getY() + (p2.getY() - p1.getY()) / 2));
            // Вычисляем координаты середины отрезка между p2 и p3 (p23)
            Point p23 = new Point((int) (p2.getX() + (p3.getX() - p2.getX()) / 2),
                    (int) (p2.getY() + (p3.getY() - p2.getY()) / 2));
            // Вычисляем координаты середины отрезка между p1 и p3 (p13)
            Point p13 = new Point((int) (p1.getX() + (p3.getX() - p1.getX()) / 2),
                    (int) (p1.getY() + (p3.getY() - p1.getY()) / 2));
            // Рекурсивно вызываем метод для каждого из полученных треугольников
            buildSerpinsky(path, p1, p12, p13, iterations);
            buildSerpinsky(path, p12, p2, p23, iterations);
            buildSerpinsky(path, p13, p23, p3, iterations);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SerpinskyTriangle::new);
    }
}