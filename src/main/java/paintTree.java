import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class paintTree extends JPanel {
    private RedBlackTree tree;
    private Node NULL;

    paintTree(RedBlackTree tree){
        this.tree = tree;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        nodes.clear();
        if (tree.getRoot()==null) return;
        drawTree(tree.getRoot(),500,0,getGap(), (Graphics2D) g,-1,-1);

        for(ArrayList<Integer> node:nodes){
            drawNode((Graphics2D) g, node.get(0), node.get(1), node.get(2),node.get(3));
            drawNodeData((Graphics2D) g,node.get(1),node.get(2),node.get(3));
        }
    }

    ArrayList<ArrayList<Integer>> nodes = new ArrayList<>();
    private void drawTree(Node root, int x, int y, int k, Graphics2D g, int parentX, int parentY){
        if (root==null) return ;
        if (root.left == NULL && root.right == NULL)
            return;
        if(parentX != -1) {
            drawLine(g,x,y,parentX,parentY);
        }
        ArrayList<Integer> node  = new ArrayList<Integer>();
        node.add(root.color);
        node.add(x);
        node.add(y);
        node.add(root.data);
        nodes.add(node);

        drawTree(root.left,x-k,y+50,k/2,g,x,y);
        drawTree(root.right,x+k,y+50,k/2,g,x,y);
    }
    private void drawNode(Graphics2D g, int isRed, int x, int y, int data){
        g.setColor(isRed==1?Color.RED:Color.BLACK);
        g.drawOval(x,y,30,30);
        g.fillOval(x,y,30,30);
    }
    private void drawNodeData(Graphics2D g, int x, int y, int data){
        g.setColor(Color.WHITE);
        g.drawString(Integer.toString(data),x+10,y+20);
    }
    int r=50;
    private void drawLine(Graphics2D g, int childX, int childY, int parentX, int parentY){
        g.setColor(Color.BLACK);
        g.drawLine(childX+5,childY+5,parentX+20,parentY+25);
    }
    private int getGap() {
        int depth = getHieght(tree.getRoot());
        int multiplier = 30;
        float exponent = (float) 1.4;

        if (depth > 6) {
            multiplier += depth * 3;
            exponent += .1;
        }

        return (int) Math.pow(depth, exponent) * multiplier;
    }
    private int getHieght(Node root) {
        if (root==null) return 0;
        else if (root.left == NULL && root.right == NULL)
            return 0;
        return Math.max(getHieght(root.left)+1, getHieght(root.right)+1);
    }



}