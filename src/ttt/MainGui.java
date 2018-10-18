package ttt;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author dettrax
 */
public class MainGui extends javax.swing.JFrame {

    /**
     * Creates new form MainGui
     */
    public MainGui() {
        initComponents();
    }
    
    // Action code

class Point {

    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override                              //for runtime polymorphism
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}

class Board {

    List<Point> availablePoints;
    Scanner scan = new Scanner(System.in);
    int[][] board1 = new int[3][3];

    public Board() {
    }

    public boolean isGameOver() {
        //Game is over is someone has won, or board1 is full (draw)
        return (hasXWon() || hasOWon() || getAvailableStates().isEmpty());
    }

    public boolean hasXWon() {
        if ((board1[0][0] == board1[1][1] && board1[0][0] == board1[2][2] && board1[0][0] == 1) || (board1[0][2] == board1[1][1] && board1[0][2] == board1[2][0] && board1[0][2] == 1)) {
            //System.out.println("X Diagonal Win");
            return true;
        }
        for (int i = 0; i < 3; ++i) {
            if (((board1[i][0] == board1[i][1] && board1[i][0] == board1[i][2] && board1[i][0] == 1)
                    || (board1[0][i] == board1[1][i] && board1[0][i] == board1[2][i] && board1[0][i] == 1))) {
                // System.out.println("X Row or Column win");
                return true;
            }
        }
        return false;
    }

    public boolean hasOWon() {
        if ((board1[0][0] == board1[1][1] && board1[0][0] == board1[2][2] && board1[0][0] == 2) || (board1[0][2] == board1[1][1] && board1[0][2] == board1[2][0] && board1[0][2] == 2)) {
            // System.out.println("O Diagonal Win");
            return true;
        }
        for (int i = 0; i < 3; ++i) {
            if ((board1[i][0] == board1[i][1] && board1[i][0] == board1[i][2] && board1[i][0] == 2)
                    || (board1[0][i] == board1[1][i] && board1[0][i] == board1[2][i] && board1[0][i] == 2)) {
                //  System.out.println("O Row or Column win");
                return true;
            }
        }

        return false;
    }

    public List<Point> getAvailableStates() {
        availablePoints = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (board1[i][j] == 0) {
                    availablePoints.add(new Point(i, j));
                }
            }
        }
        return availablePoints;
    }

    public void placeAMove(Point point, int player) {
        board1[point.x][point.y] = player;   //player = 1 for X, 2 for O
    }

    Point computersMove;

    public int minimax(int depth, int turn) {
        if (hasXWon()) return +10;
        if (hasOWon()) return -10;

        List<Point> pointsAvailable = getAvailableStates();
        if (pointsAvailable.isEmpty()) return 0;
        
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        for (int i = 0; i < pointsAvailable.size(); ++i) {
            Point point = pointsAvailable.get(i);
            if (turn == 1) {
                placeAMove(point, 1);
                int currentScore = minimax(depth + 1, 2);
                max = Math.max(currentScore, max);

                if(currentScore >= 0){ if(depth == 0) computersMove = point;}
                if(currentScore == 1){board1[point.x][point.y] = 0; break;}
                if(i == pointsAvailable.size()-1 && max < 0){if(depth == 0)computersMove = point;}
            } else if (turn == 2) {
                placeAMove(point, 2);
                int currentScore = minimax(depth + 1, 1);
                min = Math.min(currentScore, min);
                if(min == -1){board1[point.x][point.y] = 0; break;}
            }
            board1[point.x][point.y] = 0; //Reset this point
        }
        return turn == 1?max:min;
    }
}


// board1 object
Board b = new Board();
class aiturn
{
    public void turn(){
        b.minimax(0, 1);
            int l = b.computersMove.x;
            int l2 = b.computersMove.y;
            
            b.placeAMove(b.computersMove, 1);
            if(l == 0 && l2 == 0)
            { btn00.setEnabled(false);
              btn00.setText("X");
            }
            else if(l == 0 && l2 == 1)
            {btn01.setEnabled(false);
             btn01.setText("X");
            }
            else if(l == 0 && l2 == 2)
            {
                btn02.setEnabled(false);
                 btn02.setText("X");
            }
            else if(l == 1 && l2 == 0)
            {
                btn10.setEnabled(false);
                 btn10.setText("X");
            }
            else if(l == 1 && l2 == 1)
            {
                btn11.setEnabled(false);
                 btn11.setText("X");
            }
            else if(l == 1 && l2 == 2)
            {
                btn12.setEnabled(false);
                 btn12.setText("X");
            }
            else if(l == 2 && l2 == 0)
            {
                btn20.setEnabled(false);
                 btn20.setText("X");
            }
            else if(l == 2 && l2 == 1)
            {
                btn21.setEnabled(false);
                 btn21.setText("X");
            }
            else if(l == 2 && l2 == 2)
            {
                btn22.setEnabled(false);
                 btn22.setText("X");
            }
            board[l][l2] = 'X';
            
            if(b.hasXWon()){
              lockboard();
              P2Score++;
             lblPlayer2Score.setText("AI score "+ P2Score);
             turn++;        
            
    }
    }

}

aiturn ai = new aiturn();
     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        pnlBoard = new javax.swing.JPanel();
        btn00 = new javax.swing.JButton();
        btn01 = new javax.swing.JButton();
        btn02 = new javax.swing.JButton();
        btn10 = new javax.swing.JButton();
        btn11 = new javax.swing.JButton();
        btn12 = new javax.swing.JButton();
        btn20 = new javax.swing.JButton();
        btn21 = new javax.swing.JButton();
        btn22 = new javax.swing.JButton();
        lblPlayer1Score = new javax.swing.JLabel();
        lblPlayer2Score = new javax.swing.JLabel();
        btnReset = new javax.swing.JButton();
        btnReload = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tic-tac-toe");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel1.setText("Users Symbol: O   AND     AI Symbol: X");

        pnlBoard.setBorder(new javax.swing.border.LineBorder(java.awt.Color.red, 3, true));
        pnlBoard.setLayout(new java.awt.GridLayout(3, 3));

        btn00.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        btn00.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn00ActionPerformed(evt);
            }
        });
        pnlBoard.add(btn00);

        btn01.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        btn01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn01ActionPerformed(evt);
            }
        });
        pnlBoard.add(btn01);

        btn02.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        btn02.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn02ActionPerformed(evt);
            }
        });
        pnlBoard.add(btn02);

        btn10.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        btn10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn10ActionPerformed(evt);
            }
        });
        pnlBoard.add(btn10);

        btn11.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        btn11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn11ActionPerformed(evt);
            }
        });
        pnlBoard.add(btn11);

        btn12.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        btn12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn12ActionPerformed(evt);
            }
        });
        pnlBoard.add(btn12);

        btn20.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        btn20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn20ActionPerformed(evt);
            }
        });
        pnlBoard.add(btn20);

        btn21.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        btn21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn21ActionPerformed(evt);
            }
        });
        pnlBoard.add(btn21);

        btn22.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        btn22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn22ActionPerformed(evt);
            }
        });
        pnlBoard.add(btn22);

        lblPlayer1Score.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        lblPlayer1Score.setText("Users Score : 0");

        lblPlayer2Score.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        lblPlayer2Score.setText("AI Score :  0");

        btnReset.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnReload.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        btnReload.setText("Reload");
        btnReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ttt/ttthead.png"))); // NOI18N

        jMenu1.setText("File");

        jMenuItem2.setText("Close");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Help");

        jMenuItem1.setText("About");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBoard, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnReset)
                        .addGap(44, 44, 44)
                        .addComponent(btnReload))
                    .addComponent(lblPlayer2Score)
                    .addComponent(lblPlayer1Score))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(pnlBoard, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(71, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(lblPlayer1Score)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblPlayer2Score)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnReset)
                            .addComponent(btnReload))
                        .addGap(103, 103, 103))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(MainGui.this, "Tic-Tac-Toe game developed using minmax algorithm and java swing pakage\nAI devoloped by Ashish Pandey\nGraphics devoloped by Bhushan Tandale and Venktesh Patange");
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void btn02ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn02ActionPerformed
        // TODO add your handling code here:
        
           // player 1
            btn02.setEnabled(false);
            btn02.setText("O");
            board[0][2] = 'O';
     
            if(b.hasOWon()){
              lockboard();
              P1Score++;
             lblPlayer1Score.setText("Users score "+ P1Score);
            }
            Point userMove = new Point(0, 2);
            b.placeAMove(userMove, 2); //2 for O and O is the user
            
            turn++;
           ai.turn();
        
    }//GEN-LAST:event_btn02ActionPerformed
    
    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                b.board1[i][j]=0;
            }
        }
        turn =0;
        P1Score = 0;
        P2Score = 0;
        
        unlockBoard();
        
        lblPlayer1Score.setText("Users Score : 0");
        lblPlayer2Score.setText("AI Score : 0");
        
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadActionPerformed
        
         for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                b.board1[i][j]=0;
            }
        unlockBoard();
        turn =0;
        
        
    }//GEN-LAST:event_btnReloadActionPerformed
    }
    private void btn00ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn00ActionPerformed
        
        {// player 1
            btn00.setEnabled(false);
            btn00.setText("O");
            board[0][0] = 'O';
            if(b.hasOWon()){
              lockboard();
              P1Score++;
             lblPlayer1Score.setText("Users score "+ P1Score);
            }
            Point userMove = new Point(0, 0);
            b.placeAMove(userMove, 2); //2 for O and O is the user
            
            turn++;
            
    }//GEN-LAST:event_btn00ActionPerformed
                                          
        ai.turn();
}
        
    private void btn01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn01ActionPerformed
        // TODO add your handling code here:
      // player 1
            btn01.setEnabled(false);
            btn01.setText("O");
            board[0][1] = 'O';
            if(b.hasOWon()){
              lockboard();
              P1Score++;
             lblPlayer1Score.setText("Users score "+ P1Score);
            }
            Point userMove = new Point(0, 1);
            b.placeAMove(userMove, 2); //2 for O and O is the user
            
            turn++;
            ai.turn();
    }//GEN-LAST:event_btn01ActionPerformed

    private void btn10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn10ActionPerformed
        // TODO add your handling code here:
           // player 1
            btn10.setEnabled(false);
            btn10.setText("O");
            board[1][0] = 'O';
            if(b.hasOWon()){
              lockboard();
              P1Score++;
             lblPlayer1Score.setText("Users score "+ P1Score);
            }
            Point userMove = new Point(1, 0);
            b.placeAMove(userMove, 2); //2 for O and O is the user
            turn++;
            ai.turn();
    }//GEN-LAST:event_btn10ActionPerformed

    private void btn11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn11ActionPerformed
        // TODO add your handling code here:
           // player 1
            btn11.setEnabled(false);
            btn11.setText("O");
            board[1][1] = 'O';
            
            if(b.hasOWon()){
              lockboard();
              P1Score++;
             lblPlayer1Score.setText("Users score "+ P1Score);
            }
            Point userMove = new Point(1, 1);
            b.placeAMove(userMove, 2); //2 for O and O is the user
            
            turn++;
            ai.turn();
    }//GEN-LAST:event_btn11ActionPerformed

    private void btn12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn12ActionPerformed
        // TODO add your handling code here:
    // player 1
            btn12.setEnabled(false);
            btn12.setText("O");
            board[1][2] = 'O';
            if(b.hasOWon()){
              lockboard();
              P1Score++;
             lblPlayer1Score.setText("Users score "+ P1Score);
            }
            Point userMove = new Point(1, 2);
            b.placeAMove(userMove, 2); //2 for O and O is the user
            
            turn++;
            ai.turn();
    }//GEN-LAST:event_btn12ActionPerformed

    private void btn20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn20ActionPerformed
        // TODO add your handling code here
      // player 1
            btn20.setEnabled(false);
            btn20.setText("O");
            board[2][0] = 'O';
            if(b.hasOWon()){
              lockboard();
              P1Score++;
             lblPlayer1Score.setText("Users score "+ P1Score);
            }
            Point userMove = new Point(2, 0);
            b.placeAMove(userMove, 2); //2 for O and O is the user
            
            turn++;
            ai.turn();
    }//GEN-LAST:event_btn20ActionPerformed

    private void btn21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn21ActionPerformed
        // TODO add your handling code here:
         // player 1
            btn21.setEnabled(false);
            btn21.setText("O");
            board[2][1] = 'O';
            if(b.hasOWon()){
              lockboard();
              P1Score++;
             lblPlayer1Score.setText("Users score "+ P1Score);
            }
            Point userMove = new Point(2, 1);
            b.placeAMove(userMove, 2); //2 for O and O is the user
            
            turn++;
            ai.turn();
    }//GEN-LAST:event_btn21ActionPerformed

    private void btn22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn22ActionPerformed
        // TODO add your handling code here:
           // player 1
            btn22.setEnabled(false);
            btn22.setText("O");
            board[2][2] = 'O';
            if(b.hasOWon()){
              lockboard();
              P1Score++;
             lblPlayer1Score.setText("Users score "+ P1Score);
            }
            Point userMove = new Point(2, 2);
            b.placeAMove(userMove, 2); //2 for O and O is the user
         
            turn++;
            ai.turn();
    }//GEN-LAST:event_btn22ActionPerformed
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainGui().setVisible(true);
            }
        });
    }
    
    void lockboard(){     //select button by get funtion
        for(int i=0;i<9;i++){  
            pnlBoard.getComponent(i).setEnabled(false);
        }
            
    }
    
    void unlockBoard(){
        for(int i=0;i<9;i++){
            pnlBoard.getComponent(i).setEnabled(true);
        }
        
        btn00.setText("");
        btn01.setText("");
        btn02.setText("");
        
        btn10.setText("");
        btn11.setText("");
        btn12.setText("");
        
        btn20.setText("");
        btn21.setText("");
        btn22.setText("");
        
        turn =0;
       
        for(int i=0;i<3;i++){  //reset board
            for(int j=0;j<3;j++){
                board[i][j] = ' ';
            }
        }
    
    }
    
    
    
    char [][]board = new char[3][3];
    int turn =0;
    int P1Score = 0;
    int P2Score = 0;
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn00;
    private javax.swing.JButton btn01;
    private javax.swing.JButton btn02;
    private javax.swing.JButton btn10;
    private javax.swing.JButton btn11;
    private javax.swing.JButton btn12;
    private javax.swing.JButton btn20;
    private javax.swing.JButton btn21;
    private javax.swing.JButton btn22;
    private javax.swing.JButton btnReload;
    private javax.swing.JButton btnReset;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JLabel lblPlayer1Score;
    private javax.swing.JLabel lblPlayer2Score;
    private javax.swing.JPanel pnlBoard;
    // End of variables declaration//GEN-END:variables
}