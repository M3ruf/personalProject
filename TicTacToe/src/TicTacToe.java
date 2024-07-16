import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 700; // Extra 50 for text textPanel

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel label = new JLabel();
    JPanel textPanel = new JPanel(); // A panel for the text
    JPanel boardPanel = new JPanel(); // A panel for the Board. New Items are divided into the panel
    JPanel features = new JPanel();


    JButton[][] board = new JButton[3][3];
    String player1 = "X";
    String player2 = "O";
    String turn = player1;

    JButton reset = new JButton();
    JButton restart = new JButton();

    boolean gameOver = false;
    JLabel xWins = new JLabel();
    JLabel oWins = new JLabel();
    int moves=0;
    int xCounter = 0;
    int oCounter = 0;

    TicTacToe(){

        // Game board setup
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Labeling the game
        label.setBackground(Color.DARK_GRAY);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setText("Welcome to Tic-Tac-Toe");
        label.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(label);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.DARK_GRAY);
        frame.add(boardPanel, BorderLayout.CENTER);

        //Game Code
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.WHITE);
                tile.setForeground(Color.BLACK);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);

                // Actions
                tile.addActionListener(new ActionListener() {
                    @Override // overrides action listener interface
                    public void actionPerformed(ActionEvent e) {
                        if(gameOver){
                            return;
                        }
                        JButton tile = (JButton) e.getSource();
                        if(tile.getText() == ""){
                            tile.setText(turn);
                            moves++;
                            CheckWinner();
                            // Alternate to next turn
                            if (!gameOver && turn.equals(player1)) {
                                    turn = player2;
                                }
                            else if(!gameOver && turn.equals(player2)) {
                                    turn = player1;
                                }
                        }
                    }
                });

            }
        }

        features.setLayout(new GridLayout(1,4));

        // Scoreboard
        restart.setText("Restart");
        Font scoreboard = new Font("Arial", Font.BOLD, 30);
        xWins.setText("X Wins:" + xCounter);
        xWins.setFont(scoreboard);
        xWins.setBackground(Color.BLACK);
        oWins.setText("O Wins:" + oCounter);
        oWins.setFont(scoreboard);
        oWins.setBackground(Color.BLACK);

        //Reset
        reset.setText("Reset Scoreboard");
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton reset = (JButton) e.getSource();
                restart();
                xCounter=0;
                oCounter=0;
                xWins.setText("X Wins:" + xCounter);
                oWins.setText("O Wins:" + oCounter);
                turn=player1;

            }
        });

        //Add features
        features.add(restart,0);
        features.add(xWins,1);
        features.add(oWins,2);
        features.add(reset,3);

        //Add the features Panel to the main frame
        frame.add(features, BorderLayout.SOUTH);

        // Restart Button
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton restart = (JButton) e.getSource();
                restart();
            }
        });
    }
    void CheckWinner(){
        // Horizontal check
        for (int row = 0; row < 3; row++) {
            if(board[row][0].getText()==""){
                continue;
            }
            else{
                if(board[row][0].getText() == board[row][1].getText() &&
                        board[row][1].getText()==board[row][2].getText()){
                    label.setText("We have a winner: " + board[row][0].getText());
                    board[row][0].setForeground(Color.BLUE);
                    board[row][1].setForeground(Color.BLUE);
                    board[row][2].setForeground(Color.BLUE);
                    if(board[row][0].getText().equals(player1)){
                        xCounter++;
                        xWins.setText("X Wins:" + xCounter);
                        turn=player1;
                    }
                    else{
                        oCounter++;
                        oWins.setText("O Wins:" + oCounter);
                        turn=player2;
                    }
                    gameOver=true;
                    return;
                }
            }
        }

        // Vertical Check
        for (int col = 0; col < 3; col++) {
            if(board[0][col].getText()==""){
                continue;
            }
            else{
                if(board[0][col].getText() == board[1][col].getText() &&
                        board[1][col].getText()==board[2][col].getText()){
                    label.setText("We have a winner: " + board[0][col].getText());
                    boardSpot(col+1).setForeground(Color.BLUE);
                    boardSpot(col+4).setForeground(Color.BLUE);
                    boardSpot(col+7).setForeground(Color.BLUE);
                    if(board[0][col].getText().equals(player1)){
                        xCounter++;
                        xWins.setText("X Wins:" + xCounter);
                        turn=player1;
                    }
                    else{
                        oCounter++;
                        oWins.setText("O Wins:" + oCounter);
                        turn=player2;
                    }
                    gameOver=true;
                    return;
                }
            }
        }

        // Diagnol Check
        if(boardSpot(1).getText()!="" && board[0][0].getText()==board[1][1].getText() &&
                board[1][1].getText()==board[2][2].getText()) {
            label.setText("We have a winner: " + boardSpot(1).getText());
            boardSpot(1).setForeground(Color.BLUE);
            boardSpot(5).setForeground(Color.BLUE);
            boardSpot(9).setForeground(Color.BLUE);
            if(boardSpot(1).getText().equals(player1)){
                xCounter++;
                xWins.setText("X Wins:" + xCounter);
                turn=player1;
            }
            else{
                oCounter++;
                oWins.setText("O Wins:" + oCounter);
                turn=player2;
            }
            gameOver = true;
            return;
        }
        // Reverse Diagnol
        if(boardSpot(3).getText()!="" && boardSpot(3).getText()==board[1][1].getText() &&
            board[1][1].getText()==board[2][0].getText()){
            label.setText("We have a winner: " + boardSpot(3).getText());
            boardSpot(3).setForeground(Color.BLUE);
            boardSpot(5).setForeground(Color.BLUE);
            boardSpot(7).setForeground(Color.BLUE);
            if(boardSpot(3).getText().equals(player1)){
                xCounter++;
                xWins.setText("X Wins:" + xCounter);
                turn=player1;
            }
            else{
                oCounter++;
                oWins.setText("O Wins:" + oCounter);
                turn=player2;
            }
            gameOver = true;
            return;
        }
        if(moves==9){
            label.setText("DRAW!");
            gameOver = true;
            for (int i = 1; i <= 9; i++) {
                boardSpot(i).setForeground(Color.ORANGE);
            }

            return;
        }

    }
    JButton boardSpot(int spot) {
//        if(spot<=0 || spot>9){
//            throw new Exception("Invalid spot");
//        }
//        else{
            if(spot == 1){
                return board[0][0];
            }
            if(spot == 2){
                return board[0][1];
            }
            if(spot == 3){
                return board[0][2];
            }
            if(spot ==4){
                return board[1][0];
            }
            if(spot == 5){
                return board[1][1];
            }
            if(spot ==6){
                return board[1][2];
            }
            if(spot ==7){
                return board[2][0];
            }
            if(spot == 8){
                return board[2][1];
            }
            if(spot == 9){
                return board[2][2];
            }
//        }
        return board[0][0];
    }

    void setDeafult(){
        label.setBackground(Color.DARK_GRAY);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setText("Welcome to Tic-Tac-Toe");
        label.setOpaque(true);
    }
    void restart(){
        for (int i = 1; i <= 9; i++) {
            boardSpot(i).setText("");
            boardSpot(i).setForeground(Color.BLACK);
            setDeafult();
        }
        moves = 0;
        gameOver = false;
        return;
    }
}
