import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Main extends JPanel implements ActionListener {
    public static JFrame j = new JFrame();
    public JTextField text = new JTextField(15);
    private static RedBlackTree tree = new RedBlackTree();
    paintTree treePanel = new paintTree(tree);
    static ArrayList<Integer> array;
    JPanel Printpanel = new JPanel();
    JLabel label = new JLabel();


    public Main() {
        MainPanel();
    }


    private void MainPanel() {

        super.setLayout(new BorderLayout());

        /////////////BUTTONS

        JButton btn_ins = new JButton("INSERT");
        btn_ins.setBackground(new Color(40, 73, 22));
        btn_ins.setForeground(Color.WHITE);
        btn_ins.addActionListener(this);


        JButton btn_del = new JButton("DELETE");
        btn_del.setBackground(new Color(141, 17, 17));
        btn_del.setForeground(Color.WHITE);
        btn_del.addActionListener(this);


        JButton btn_clr = new JButton("CLEAR");
        btn_clr.setBackground(Color.BLACK);
        btn_clr.setForeground(Color.WHITE);
        btn_clr.addActionListener(this);


        JButton btn_print = new JButton("PRINT");
        btn_print.setBackground(Color.GRAY);
        btn_print.setForeground(Color.WHITE);
        btn_print.addActionListener(this);


        JPanel Bottompanel = new JPanel();
        Bottompanel.add(btn_print);
        Bottompanel.add(btn_ins);
        Bottompanel.add(text);
        Bottompanel.add(btn_del);
        Bottompanel.add(btn_clr);
        add(Bottompanel, BorderLayout.NORTH);
//////////////////////////////////////////////////

        //SCROLL

        treePanel.setPreferredSize(new Dimension(800, 800));
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(treePanel);
        jScrollPane.setPreferredSize(new Dimension(800, 600));
        add(jScrollPane, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("INSERT")) {
            if (text.equals("")) return;

            //TODO if tree already contains the data
            if (tree.search(Integer.parseInt(text.getText())).data == Integer.parseInt(text.getText())) {
                JOptionPane.showMessageDialog(null, "This element already exist!");
                return;
            }
            tree.insert(Integer.parseInt(text.getText()));
            treePanel.repaint();
        } else if (e.getActionCommand().equals("DELETE")) {
            if (text.equals("")) return;
            if (tree.search(Integer.parseInt((text.getText()))).data != Integer.parseInt((text.getText()))) {
                JOptionPane.showMessageDialog(null, "Not found!");
                return;
            }
            tree.delete(Integer.parseInt(text.getText()));
            treePanel.repaint();
        } else if (e.getActionCommand().equals("CLEAR")) {
            if (tree.isEmpty())
                JOptionPane.showMessageDialog(null, "The Tree is already empty");
            else {

                tree.clear();
                treePanel.repaint();
            }
        } else {
            j.remove(label);
            Printpanel.remove(label);
            if (tree.isEmpty())
                JOptionPane.showMessageDialog(null, "The Tree is empty");
            else {

                String str = new String("");
                array = tree.inorder();
                for (int i = 0; i < array.size(); i++) {
                    str = str + array.get(i) + "   ";
                }
                label.setText(str);

                Printpanel.add(label);
                array.clear();
                add(Printpanel, BorderLayout.SOUTH);

            }
            treePanel.repaint();
        }
    }

    public static void main(String args[]) {

        j.setTitle("RBTree GUI");
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.add(new Main());
        j.setVisible(true);
        j.pack();
    }
}


