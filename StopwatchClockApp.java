import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StopwatchClockApp {
    private static boolean running = false;
    private static int elapsedSeconds = 0;
    private static Timer stopwatchTimer;
    private static Timer clockTimer;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Stopwatch and Clock");
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 1));

        // --- Clock ---
        JLabel clockLabel = new JLabel("", SwingConstants.CENTER);
        clockLabel.setFont(new Font("Verdana", Font.BOLD, 24));

        clockTimer = new Timer(1000, e -> {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            clockLabel.setText("Current Time: " + sdf.format(new Date()));
        });
        clockTimer.start();

        // --- Stopwatch ---
        JLabel stopwatchLabel = new JLabel("Stopwatch: 00:00:00", SwingConstants.CENTER);
        stopwatchLabel.setFont(new Font("Verdana", Font.BOLD, 24));

        stopwatchTimer = new Timer(1000, e -> {
            elapsedSeconds++;
            int hrs = elapsedSeconds / 3600;
            int mins = (elapsedSeconds % 3600) / 60;
            int secs = elapsedSeconds % 60;
            stopwatchLabel.setText(String.format("Stopwatch: %02d:%02d:%02d", hrs, mins, secs));
        });

        // --- Buttons ---
        JPanel buttonPanel = new JPanel();
        JButton startBtn = new JButton("Start");
        JButton stopBtn = new JButton("Stop");
        JButton resetBtn = new JButton("Reset");

        startBtn.addActionListener(e -> {
            if (!running) {
                stopwatchTimer.start();
                running = true;
            }
        });

        stopBtn.addActionListener(e -> {
            stopwatchTimer.stop();
            running = false;
        });

        resetBtn.addActionListener(e -> {
            stopwatchTimer.stop();
            running = false;
            elapsedSeconds = 0;
            stopwatchLabel.setText("Stopwatch: 00:00:00");
        });

        buttonPanel.add(startBtn);
        buttonPanel.add(stopBtn);
        buttonPanel.add(resetBtn);

        frame.add(clockLabel);
        frame.add(stopwatchLabel);
        frame.add(buttonPanel);

        frame.setVisible(true);
    }
}
