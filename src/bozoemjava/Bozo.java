package bozoemjava;


import java.awt.event.*
import java.util.*;

import javax.swing.JButton;

class Jogador{
    public String nome;
    public int tabela[][] = {{-1, -1, -1},{-1, -1, -1},{-1, -1, -1},{-1, -1, -1}};

    public Jogador(String nome){
        this.nome =  nome;
    }
}

public class Bozo{
    UserInterface userinterface = new UserInterface();
    int quantidade_jogadores;

    Bozo(){
        iniciarJogo();
    }

    public void limparActionListener(JButton button){
        for(ActionListener l : button.getActionListeners()){
            button.removeActionListener(l);
        }
    }

    public void iniciarJogo(){
        JButton button = new JButton();
        userinterface.loadTitleScreen(button);

        button.addActionListener(new ActionEvent(){
            public void actionPerformed(ActionEvent e){
                
            }
        });

        
    }

    public void jogo(){
        Scanner leia = new Scanner(System.in);
        quantidade_jogadores = leia.nextInt();

        Jogador jogadores[];
        int MAX_RODADAS = 10;

        jogadores = inicializarJogadores(quantidade_jogadores);
        jogadores = jogarRodadas(jogadores, MAX_RODADAS, quantidade_jogadores);

        int vencedor = verificarVencedor(jogadores, quantidade_jogadores);
        System.out.println("O vencedor é "+ jogadores[vencedor].nome+"!!!");
    }
    
    public static String[] pegarNomes(int quantidade_jogadores){
        Scanner leia = new Scanner(System.in);
        String nomes[] = new String[quantidade_jogadores];

        for(int i=0;i<quantidade_jogadores;i++){
            System.out.print("\nDigite o nome do jogador "+(i+1)+":");
            nomes[i] = leia.nextLine();
        }

        return nomes;
    }

    public static Jogador[] inicializarJogadores(int quantidade_jogadores){
        Jogador jogadores[] = new Jogador[quantidade_jogadores];
        String nomes[] = pegarNomes(quantidade_jogadores);

        for(int num_jogador = 0; num_jogador < quantidade_jogadores ; num_jogador++){
            Jogador jogador = new Jogador(nomes[num_jogador]);
            jogadores[num_jogador] = jogador;
        }

        return jogadores;
    }

    public static int[][] jogarTurno(Jogador jogadores[], int num_jogador){
        System.out.println("Turno de "+jogadores[num_jogador].nome+"\n\n\n");
        int dados[] = new int[6];
        int rolagens = 0;
        int[][] tabela = jogadores[num_jogador].tabela;
        int opcao = 0;

        while(rolagens < 3){
            if(rolagens == 0){
                dados = rolarDados(false, dados);
                rolagens++;

                for(int i = 0; i<6; i++){
                    System.out.print(dados[i]+" ");
                }
                System.out.println();
                
                opcao = mostrarOpcoes(dados, tabela);
                if(opcao != 0){
                    tabela = marcarTabela(opcao, dados, tabela);
                    return tabela;
                }

            }else{
                dados = rolarDados(true, dados);
                rolagens++;

                System.out.println(Arrays.toString(dados));
                
                opcao = mostrarOpcoes(dados, tabela);
                if(rolagens == 3){
                    while(opcao == 0){
                        System.out.println("Opção inválida!");
                        opcao = mostrarOpcoes(dados, tabela);
                    }
                    break;
                }

                if(opcao != 0){
                    tabela = marcarTabela(opcao, dados, tabela);
                    return tabela;
                }
            }
        }

        tabela = marcarTabela(opcao, dados, tabela);
        return tabela;

    }
    

    public static Jogador[] jogarRodadas(Jogador jogadores[], int MAX_RODADAS, int quantidade_jogadores){
        for(int num_rodada = 1; num_rodada<=MAX_RODADAS; num_rodada++){
            System.out.println("RODADA "+num_rodada+"\n\n");

            for(int num_jogador = 0; num_jogador < quantidade_jogadores; num_jogador++){
                jogadores[num_jogador].tabela = jogarTurno(jogadores, num_jogador);

                if(jogadores[num_jogador].tabela[3][0] == 1){
                    return jogadores;
                }
            }
        }

        return jogadores;
    }

    public static int verificarVencedor(Jogador[] jogadores, int quantidade_jogadores){
        int maior_pontuacao = 0;
        int num_jogador_maior = 0;

        for(int i=0; i < quantidade_jogadores; i++){
            if(jogadores[i].tabela[3][0] == 1){
                return i;
            }

            int pontuacao_jogador = 0;
            for(int j = 0; j < 4; j++){
                for(int k=0; k < 3; k++){
                    pontuacao_jogador = pontuacao_jogador + jogadores[i].tabela[k][j];
                }
            }

            if(pontuacao_jogador >= maior_pontuacao){
                maior_pontuacao = pontuacao_jogador;
                num_jogador_maior = i;
            }
        }

        return num_jogador_maior;
    }

    
    public static int mostrarOpcoes(int[] dados, int[][] tabela){
        Scanner leia = new Scanner(System.in);
        String opcoes_tabela[] = {"As", "Duque", "Terno", "Quadra", "Quina", "Sena", "Fu", "Seguida", "Quadrada", "General"};

        System.out.println("TABELA DO JOGADOR\n");
        mostrarTabela(tabela);

        System.out.println("\nEscolha uma das opções:");
        System.out.println("0 - Rerolar");
        
        for(int i = 0; i < opcoes_tabela.length; i++){
            int pontos_opcao = calcularPontos_Opcao(dados, (i+1), tabela);
            if(pontos_opcao == 0){
                System.out.println((i+1)+" - Torar " + opcoes_tabela[i] + " (marcar 0)");

            }else if(pontos_opcao > 0){
                System.out.println((i+1)+" - "+pontos_opcao+" de "+opcoes_tabela[i]);
            }
        }

        System.out.print("Digite o número da opção correspondente: ");
        int opcao = leia.nextInt();

        return opcao;
    }


    public static int[] rolarDados(boolean rerolar, int[] dados){
        Scanner leia = new Scanner(System.in);
        Random rand = new Random();

        if(rerolar){
            System.out.print("Escreva o número dos dados que você quer rerolar, separados por vírgula (Ex: '2,4,5'): ");
            String userinput = leia.nextLine();
            String[] dados_rerolar = userinput.split(",");

            if(dados_rerolar.length == 5){
                dados[5] = 1;
            }else{
                dados[5] = 0;
            }

            for(String i : dados_rerolar){
                int roll = rand.nextInt(6);
                roll = roll+1;
                dados[(Integer.parseInt(i))-1] = roll;
            }
        }else{
            for(int i=0; i<5; i++){
                int roll = rand.nextInt(6);
                roll = roll+1;
                dados[i] = roll;
            }
            dados[5] = 1;
        }

        dados = ordenarDados(dados);

        return dados;
    }

    public static int[] ordenarDados(int[] dados){
        boolean houveTroca = true;
        int aux = 0;

        while(houveTroca){
            houveTroca = false;
            for(int i=0; i < (dados.length - 2); i++){
                if(dados[i] > dados[i+1]){
                    aux = dados[i];
                    dados[i] = dados[i+1];
                    dados[i+1] = aux;
                    houveTroca = true;
                }
            }
        }
        
        return dados;
    }
    
    public static int calcularPontos_Opcao(int[] dados, int opcao, int[][] tabela){
        int pontos_opcao = 0;

        switch(opcao){
            case 1:
                if(tabela[0][0] != -1){
                    return -1;
                }

                for(int dado : dados){
                    if(dado == 1){
                        pontos_opcao = pontos_opcao+1;
                    }
                }
                break;

            case 2:
                if(tabela[1][0] != -1){
                    return -1;
                }

                for(int dado : dados){
                    if(dado == 2){
                        pontos_opcao = pontos_opcao+2;
                    }
                }
                break;

            case 3:
                if(tabela[2][0] != -1){
                    return -1;
                }

                for(int dado : dados){
                    if(dado == 3){
                        pontos_opcao = pontos_opcao+3;
                    }
                }
                break;

            case 4:
                if(tabela[0][1] != -1){
                    return -1;
                }

                for(int dado : dados){
                    if(dado == 4){
                        pontos_opcao = pontos_opcao+4;
                    }
                }
                break;

            case 5:
                if(tabela[1][1] != -1){
                    return -1;
                }

                for(int dado : dados){
                    if(dado == 5){
                        pontos_opcao = pontos_opcao+5;
                    }
                }
                break;

            case 6:
                if(tabela[2][1] != -1){
                    return -1;
                }

                for(int dado : dados){
                    if(dado == 6){
                        pontos_opcao = pontos_opcao+6;
                    }
                }
                break;

            case 7:
                if(tabela[0][2] != -1){
                    return -1;
                }

                if((dados[0] == dados[1] && dados[3] == dados[4]) && (dados[0] == dados[2] || dados[4] == dados[2])){
                    pontos_opcao = 20;
                }
                break;

            case 8:
                if(tabela[1][2] != -1){
                    return -1;
                }

                boolean emOrdem = true;
                int aux = dados[0];

                for(int i=1; i<5; i++){
                    if(!((aux+1) == dados[i])){
                        emOrdem = false;
                    }
                    aux = dados[i];
                }

                if(emOrdem){
                    pontos_opcao = 30;
                }
                break;

            case 9:
                if(tabela[2][2] != -1){
                    return -1;
                }

                if((dados[1] == dados[2] && dados[2] == dados[3]) && (dados[0] == dados[1] || dados[4] == dados[1]) && (dados[0] != dados[4])){
                    pontos_opcao = 40;
                }
                break;
            
            case 10:
                if(tabela[3][2] != -1){
                    return -1;
                }
                
                if(dados[0] == dados[1] && dados[1] == dados[2] && dados[2] == dados[3] && dados[3] == dados[4]){
                    pontos_opcao = 50;
                }
                break;
        }

        return pontos_opcao;
    }

    public static int[][] marcarTabela(int opcao, int[] dados, int[][] tabela){
        int pontos_opcao;

        switch(opcao){
            case 1:
                pontos_opcao = calcularPontos_Opcao(dados, opcao, tabela);
                tabela[0][0] = pontos_opcao;
                break;
                
            case 2:
                pontos_opcao = calcularPontos_Opcao(dados, opcao, tabela);
                tabela[1][0] = pontos_opcao;
                break;
                
            case 3:
                pontos_opcao = calcularPontos_Opcao(dados, opcao, tabela);
                tabela[2][0] = pontos_opcao;
                break;
                
            case 4:
                pontos_opcao = calcularPontos_Opcao(dados, opcao, tabela);
                tabela[0][1] = pontos_opcao;
                break;
                
            case 5:
                pontos_opcao = calcularPontos_Opcao(dados, opcao, tabela);
                tabela[1][1] = pontos_opcao;
                break;
                
            case 6:
                pontos_opcao = calcularPontos_Opcao(dados, opcao, tabela);
                tabela[2][1] = pontos_opcao;
                break;

            case 7:
                pontos_opcao = calcularPontos_Opcao(dados, opcao, tabela);
                if(dados[5] == 1){
                    pontos_opcao = pontos_opcao + 5;
                }
                tabela[0][2] = pontos_opcao;
                break;

            case 8:
                pontos_opcao = calcularPontos_Opcao(dados, opcao, tabela);
                if(dados[5] == 1){
                    pontos_opcao = pontos_opcao + 5;
                }
                tabela[1][2] = pontos_opcao;
                break;

            case 9:
                pontos_opcao = calcularPontos_Opcao(dados, opcao, tabela);
                if(dados[5] == 1){
                    pontos_opcao = pontos_opcao + 5;
                }
                tabela[2][2] = pontos_opcao;
                break;

            case 10:
                pontos_opcao = calcularPontos_Opcao(dados, opcao, tabela);
                if(dados[5] == 1){
                    tabela[3][0] = 1;
                    break;
                }
                tabela[3][2] = pontos_opcao;
                break;
        }

        return tabela;
    }

    public static void mostrarTabela(int[][] tabela){
        for(int i = 0; i < 4; i++){
            System.out.println();
            for(int j=0; j < 3; j++){
                if(tabela[i][j] == -1){
                    System.out.print("- ");
                }else{
                    System.out.print(tabela[i][j]+" ");
                }
            }
        }
    }


    public static void main(String[] args){
        Bozo bozo = new Bozo();
        bozo.jogo();
    }

}
