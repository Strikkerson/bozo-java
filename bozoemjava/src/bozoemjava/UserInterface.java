package bozoemjava;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

public class UserInterface {
    JFrame frame = new JFrame();
    CardLayout cl = new CardLayout();
    JPanel clPanel = new JPanel();
    JPanel startscreenPanel = new JPanel();
    JPanel setupscreenPanel = new JPanel();
    JPanel gamescreenPanel = new JPanel();
    JPanel loadingscreenPanel = new JPanel();
    public JRadioButton[] rbuttons_options = new JRadioButton[5];
    static JRadioButton[] rbuttonsPointOptions = new JRadioButton[10];

    UserInterface(){
        frame.setSize(800,600);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("BOZÓ EM JAVA");

        clPanel.setLayout(cl);
        clPanel.add(startscreenPanel, "start");
        clPanel.add(setupscreenPanel, "setup");
        clPanel.add(gamescreenPanel, "round");
        clPanel.add(loadingscreenPanel, "loading");


        frame.add(clPanel);

        //loadTitleScreen();

    }
    
    
    public void loadTitleScreen(JButton start_button){
        startscreenPanel.setLayout(null);


        JPanel title_panel = new JPanel();
        //title_panel.setBackground(Color.BLUE);
        title_panel.setBounds(0, 100, 800, 150);
        title_panel.setLayout(new FlowLayout());

        JPanel startbutton_panel = new JPanel();
        startbutton_panel.setBackground(Color.RED);
        startbutton_panel.setBounds(250, 300, 300, 80);
        startbutton_panel.setLayout(new BorderLayout());

        JLabel titlename_label = new JLabel();
        titlename_label.setText("TESTE");
        titlename_label.setFont(new Font("Arial", 0, 120));

        //JButton start_button = new JButton();
        start_button.setText("INICIAR");
        start_button.setFont(new Font("Arial", Font.BOLD, 25));

        /*start_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                loadSetupScreen();
            }
        });*/

        title_panel.add(titlename_label);

        startbutton_panel.add(start_button);

        startscreenPanel.add(startbutton_panel);
        startscreenPanel.add(title_panel);
        cl.show(clPanel,"start");
        frame.setVisible(true);
    }
    
    public void loadSetupScreen(JButton start_button, JRadioButton[] rbutton_options){
        //codigo vem antes do cl.show
        setupscreenPanel.setLayout(null);
        
        JLabel setupmessage_label = new JLabel();
        setupmessage_label.setText("<html>Selecione a quantidade<br/>   de jogadores</html>");
        setupmessage_label.setFont(new Font("Arial", 0, 60));

        JPanel setupmessage_panel = new JPanel();
        setupmessage_panel.setBounds(0, 100, 800, 200);
        setupmessage_panel.setLayout(new FlowLayout());
        setupmessage_panel.add(setupmessage_label);

        JPanel startbutton_panel = new JPanel();
        startbutton_panel.setBackground(Color.RED);
        startbutton_panel.setBounds(250, 400, 300, 80);
        startbutton_panel.setLayout(new BorderLayout());
    
        //JButton start_button;
        start_button.setText("INICIAR");
        startbutton_panel.add(start_button);

        JPanel rbuttons_panel = new JPanel();
        rbuttons_panel.setBounds(0, 300, 800, 300);
        rbuttons_panel.setLayout(new FlowLayout());


        ButtonGroup rbutton_group = new ButtonGroup();
        String[] written_numbers = {"Dois", "Três", "Quatro", "Cinco", "Seis"};

        for(int i=0; i<5; i++){
            rbuttons_options[i] = new JRadioButton();
            rbuttons_options[i].setText(written_numbers[i]+" jogadores");

            rbutton_group.add(rbuttons_options[i]);
            rbuttons_panel.add(rbuttons_options[i]);
        }
        rbuttons_options[0].setSelected(true);
        

        

        setupscreenPanel.add(setupmessage_panel);
        setupscreenPanel.add(rbuttons_panel);
        setupscreenPanel.add(startbutton_panel);



       cl.show(clPanel, "setup");
       setupscreenPanel.setVisible(true);
       frame.setVisible(true);
    }

    public void loadGameScreen(int dados[], JButton confirmButton, JButton rerollButton){
        gamescreenPanel.setLayout(null);
        
        JLabel dadosLabel[] = new JLabel[5];
        for(int i=0; i<5; i++){
            dadosLabel[i] = new JLabel();
            dadosLabel[i].setText(""+dados[i]);
            dadosLabel[i].setFont(new Font("Arial", Font.BOLD, 100));
        }

        JPanel dicePanel = new JPanel();
        dicePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));
        dicePanel.setBounds(0, 100, 800, 100);
        for(int i=0; i<5; i++){
            dicePanel.add(dadosLabel[i]);
        }

        JPanel rbuttons_panel = new JPanel();
        rbuttons_panel.setBounds(0, 300, 800, 300);
        rbuttons_panel.setLayout(new FlowLayout());

        ButtonGroup rbutton_group = new ButtonGroup();
        String[] written_options = {"Ás", "Duque", "Terno", "Quadra", "Quina", "Sena", "Fu", "Seguida", "Quadrada", "General"};

        for(int i=0; i<10; i++){
            rbuttonsPointOptions[i] = new JRadioButton();
            rbuttonsPointOptions[i].setText("teste de "+written_options[i]);

            rbutton_group.add(rbuttons_options[i]);
            rbuttons_panel.add(rbuttons_options[i]);
        }


        JPanel buttons_panel = new JPanel();
        buttons_panel.setBounds(350, 400, 100, 80);
        buttons_panel.setLayout(new FlowLayout());
    
        //JButton confirmButton;
        confirmButton.setText("CONFIRMAR");
        buttons_panel.add(confirmButton);

        //JButton rerollButton;
        rerollButton.setText("REROLAR");
        buttons_panel.add(rerollButton);

        
        gamescreenPanel.add(buttons_panel);
        gamescreenPanel.add(rbuttons_panel);
        gamescreenPanel.add(dicePanel);

        cl.show(clPanel, "round");        
    }

    public void loadLoadingScreen(int[][] tabela, String nome, int pontos, int num_rodada){
        loadingscreenPanel.setLayout(null);

        JPanel playertablePanel = new JPanel();
        playertablePanel.setLayout(new GridLayout(4, 3));
        playertablePanel.setBounds(100,100,300,300);

        JLabel tableSpaces[][] = new JLabel[4][3];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j<3; j++){
                tableSpaces[i][j] = new JLabel();
                if(tabela[i][j] != -1){
                    tableSpaces[i][j].setText(""+tabela[i][j]);
                }else{
                    tableSpaces[i][j].setText("");
                }
                tableSpaces[i][j].setFont(new Font("Arial", Font.BOLD, 50));

                playertablePanel.add(tableSpaces[i][j]);

            }
        }

        JPanel playerinfoPanel = new JPanel();
        playerinfoPanel.setLayout(new GridLayout(2, 1));

        JLabel playerNameLabel = new JLabel(nome);
        playerNameLabel.setSize(200, 200);
        playerNameLabel.setFont(new Font("Arial", Font.BOLD, 30));

        JLabel playerPointsLabel = new JLabel(pontos+" pontos");
        playerNameLabel.setSize(200, 200);
        playerPointsLabel.setFont(new Font("Arial", Font.BOLD, 30));
        
        playerinfoPanel.add(playerNameLabel);
        playerinfoPanel.add(playerPointsLabel);
        playerinfoPanel.setBounds(500, 200, 200, 100);

        loadingscreenPanel.add(playertablePanel);
        loadingscreenPanel.add(playerinfoPanel);

        cl.show(clPanel, "loading");
        frame.setVisible(true);
    }

    public static void main(String[] args){
        new UserInterface();
    }

}


