package bozoemjava;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JRadioButton;

public class Bozoemjava {
    static UserInterface userinterface = new UserInterface();

    public static int getOption(JRadioButton[] rbuttons){
        int opcao_escolhida = 0;

        for(int i = 0; i<5; i++){
            if(userinterface.rbuttons_options[i].isSelected()){
                opcao_escolhida = i+2;
            }
        }

        return opcao_escolhida;
    }

    public static void loadSetup(){
        JButton botao = new JButton();
        JRadioButton[] rbuttons = new JRadioButton[5];

        userinterface.loadSetupScreen(botao, rbuttons);

        int opcao_escolhida = 0;
        botao.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                botao.setText(getOption(rbuttons)+" Jogadores");
            }
        });
        
    }
    public static void main(String[] args) {
        JButton button = new JButton();
        JButton button2 = new JButton();

        
        userinterface.loadTitleScreen(button);
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                loadSetup();
                for(ActionListener l : button.getActionListeners()){
                    button.removeActionListener(l);
                }
            }
        });
        
        
        // int dados[] = {1, 2, 3, 4, 5};
        // userinterface.loadGameScreen(dados, button, button2);

        // int tabela[][] = {{1, 1, 1},{1, 1, 1},{1, 1, 1},{-1, -1, 1}};
        // userinterface.loadLoadingScreen(tabela,"aaaaaaaaaa", 5, 1 );
    }
}

                
